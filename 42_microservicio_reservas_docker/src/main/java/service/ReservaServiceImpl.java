package service;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import dao.ClientesDao;
import dao.HotelesDao;
import dao.ReservaDao;
import dao.VuelosDao;
import entities.Cliente;
import entities.Hotel;
import entities.Reserva;
import entities.Vuelo;
import jakarta.annotation.PostConstruct;
import model.ReservaDto;
import utilidades.Mapeador;

@Service
public class ReservaServiceImpl implements ReservaService{
@Autowired
	@Value("${usuario}")
	String usuario;
	@Value("${contra}")
	String contra;
	String url;
	ReservaDao reservaDao;
	VuelosDao vuelosDao;
	HotelesDao hotelesDao;
	ClientesDao clientesDao;
	Mapeador mapeador;
	RestClient restClient;
	
String url_servicio_vuelo_actualizar = "http://servicio-vuelos/vuelos/actualizar";



	public ReservaServiceImpl(ReservaDao reservaDao, VuelosDao vuelosDao, HotelesDao hotelesDao,
			ClientesDao clientesDao, Mapeador mapeador,RestClient restClient) {
		super();
		this.reservaDao = reservaDao;
		this.vuelosDao = vuelosDao;
		this.hotelesDao = hotelesDao;
		this.clientesDao = clientesDao;
		this.mapeador = mapeador;
		this.restClient=restClient;
	}
	@PostConstruct
	public void init() {
		url="http://servicio-reservas/reservas/";
	}

	  @Override       
	        public List<ReservaDto> getReservasByUsuario(String usuario) {
	            List<Reserva> reservas = reservaDao.findReservasByUsuario(usuario);
	            return reservas.stream()
	                           .map(mapeador::reservaEntitytoDto)
	                           .collect(Collectors.toList());
	        }
	  

	    @Override
	    public void altaReserva(ReservaDto reserva, int plazas) {

	            Hotel hotel = hotelesDao.findByNombre(reserva.getNombreHotel());
	            Vuelo vuelo = vuelosDao.findByDestinoAndFecha(reserva.getDestinoVuelo(), reserva.getFechaVuelo());

	            if (hotel == null || vuelo == null ) {
	                throw new IllegalArgumentException("No se encontró el hotel, vuelo o cliente especificado");
	            }

	            Reserva nuevaReserva = new Reserva();
	            nuevaReserva.setIdreserva(0);  
	            nuevaReserva.setHotel(hotel);  
	            nuevaReserva.setvuelo(vuelo);  
	            nuevaReserva.setPrecio(vuelo.getPrecio()*plazas);   
	            nuevaReserva.setUsuario(usuario); 

	            Reserva reservaGuardada = reservaDao.save(nuevaReserva);
	            
	            updatePlazas(vuelo.getIdVuelo(),(vuelo.getPlazas()-plazas));
	   
	        }
	    @Override
	    public void updatePlazas(int idVuelo, int plazasNew) {
	    	
	    	String uri = String.format("%s/%d/%d", url_servicio_vuelo_actualizar, idVuelo, plazasNew);
	       	
	    	restClient
			.put()
			.uri(uri)
			.header("Authorization", "Basic "+getBase64(usuario, contra))
			.contentType(MediaType.APPLICATION_JSON)
			.body(Vuelo.class)
			.retrieve()
			.toBodilessEntity(); 
		}

	    private String getBase64(String user, String password) {
	        String auth = user + ":" + password;
	        return Base64.getEncoder().encodeToString(auth.getBytes());
	    }
	}

