package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import model.Formacion;
@Service
public class FormacionServiceImpl implements FormacionService {
	
	String url="http://localhost:8000/cursos/";
	RestClient restClient;
	public FormacionServiceImpl(RestClient restClient) { //inyección por constructor
		this.restClient=restClient;
	}

	@Override
	public List<Formacion> buscarPorArea(String area) {
		return Arrays.stream(restClient
				.get()
				.uri(url+"recuperartodos")
				.retrieve()
				.body(Formacion[].class)//Formacion[]
			)//Stream<Formacion>
			.filter(f->f.getArea().equals(area))
			.toList();
	}

	@Override
	public void altaFormacion(Formacion formacion) {
		restClient
		.post()
		.uri(url+"alta")
		.contentType(MediaType.APPLICATION_JSON)
		.body(formacion)
		.retrieve()
		//Se ha añadido esta intruccion cuando hicimos el 19 porque 
		//fallaba la ejecución dle mismo.
		//Transforma una instancia de ResponseEntity, eliminando el cuerpo de la respuesta
		//pero conservando los encabezados y el estaod de la respuesta.
		
		//Es decir, matiene intactos los metadatos como los encabezaos y el codigo de estado;
		.toBodilessEntity();

	}

}
