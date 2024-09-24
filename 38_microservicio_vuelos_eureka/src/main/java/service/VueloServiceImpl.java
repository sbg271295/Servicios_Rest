package service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import dao.VuelosDao;
import entities.Vuelo;
import jakarta.annotation.PostConstruct;
import model.VueloDto;
import utilidades.Mapeador;

@Service
public class VueloServiceImpl implements VueloService{
	@Value("${usuario}")
	String user;
	@Value("${contra}")
	String contra;
	
	
	@Value("${admin.user}")
	String userAdmin;
	@Value("${admin.pass}")
	String passAdmin;
	String url;
	@Autowired
	RestClient restClient;
	VuelosDao vuelosDao;
	Mapeador mapeador;

	public VueloServiceImpl(VuelosDao clientesDao, Mapeador mapeador,RestClient restClient) {
		this.vuelosDao = clientesDao;
		this.mapeador = mapeador;
		this.restClient=restClient;
	}
@PostConstruct
public void init() {
	url="http://servicio-vuelos/vuelos/";
}
	@Override
	public List<VueloDto> searchByDestinoandPlazas(String destino, int plazas) {
		
	    Vuelo[] vuelos = restClient
	            .get()
	            .uri(url + "buscarvuelo/{destino}/{plazas}", destino,plazas) // Llamada al endpoint remoto
	            .retrieve() // Recuperamos la respuesta
	            .body(Vuelo[].class) // Convertimos el cuerpo de la respuesta a un array de Vuelo
	            ; // Ejecutamos sincrónicamente para obtener los datos

	    // Convertimos la lista de Vuelo a VueloDto, filtrando por plazas disponibles
	    return Arrays.stream(vuelos)
	            .map(mapeador::VueloEntityToDto) // Mapeo de entidad Vuelo a DTO VueloDto
	            .filter(vuelo -> vuelo.getPlazas() >= plazas) // Filtramos por el número de plazas
	            .collect(Collectors.toList()); // Recogemos el resultado como lista
	}
	    
	/*    return vuelos.stream()
	                 .map(mapeador::VueloEntityToDto)
	                 .filter(c -> c.getPlazas() >= plazas)
	                 .collect(Collectors.toList());*/
	


	@Override
	public VueloDto searchById(int idVuelo) {
		Vuelo vuelo = restClient
	            .get()
	            .uri(url + "buscarvuelo/{idVuelo}", idVuelo) // Llamada al endpoint remoto para obtener el vuelo por ID
	            .retrieve() // Recuperamos la respuesta
	            .body(Vuelo.class) // Convertimos el cuerpo de la respuesta en un objeto Vuelo
	            ; // Ejecutamos sincrónicamente para obtener los datos

	    // Si el vuelo es nulo, lanzamos una excepción (opcional)
	    if (vuelo == null) {
	        throw new IllegalArgumentException("Vuelo no encontrado.");
	    }

	    // Mapeamos la entidad Vuelo a VueloDto
	    return mapeador.VueloEntityToDto(vuelo);
	}

	@Override
	public void updatePlazas(int idVuelo, int plazasAnt, int plazasNuevas) {

	    // Realizamos la llamada REST para obtener el vuelo por ID
	    Vuelo vuelo = restClient
	            .get()
	            .uri(url + "vuelos/{idVuelo}", idVuelo) // Llamada al endpoint remoto para obtener el vuelo
	            .retrieve() // Recuperamos la respuesta
	            .body(Vuelo.class) // Convertimos el cuerpo de la respuesta a un objeto Vuelo
	            ; // Ejecutamos sincrónicamente para obtener los datos

	    // Validamos si el vuelo existe
	    if (vuelo == null) {
	        throw new IllegalArgumentException("Vuelo no encontrado.");
	    }

	    // Verificamos si el número de plazas actuales coincide con plazasAnt
	    if (vuelo.getPlazas() == plazasAnt) {
	        // Actualizamos el número de plazas
	        vuelo.setPlazas(plazasNuevas);

	        // Realizamos una llamada REST para actualizar el vuelo con el nuevo número de plazas
	        restClient
	            .put() // Método PUT para actualizar los datos
	            .uri(url + "vuelos/actualizar/{idVuelo}/{plazasAnt}/{plazasNew}", idVuelo, plazasAnt, plazasNuevas) // Endpoint remoto para actualizar el vuelo
	            .body(vuelo) // Enviamos el objeto vuelo actualizado en el cuerpo de la solicitud
	            .retrieve() // Procesamos la respuesta
	            .toBodilessEntity() // No necesitamos cuerpo de respuesta
	            ; // Ejecutamos sincrónicamente
	    } else {
	        throw new IllegalArgumentException("El número de plazas actuales no coincide con el valor esperado.");
	    }
	}

}

