package service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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
	String user;
	@Value("${contra}")
	String contra;
	
	
	@Value("${admin.user}")
	String userAdmin;
	@Value("${admin.pass}")
	String passAdmin;
	String url;
	ReservaDao reservaDao;
	VuelosDao vuelosDao;
	HotelesDao hotelesDao;
	ClientesDao clientesDao;
	Mapeador mapeador;
	RestClient restClient;

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
	        /* 
	        // Método anterior:
	        public List<ReservaDto> getReservasByUsuario(String usuario) {
	            List<Reserva> reservas = reservaDao.findReservasByUsuario(usuario);
	            return reservas.stream()
	                           .map(mapeador::reservaEntitytoDto)
	                           .collect(Collectors.toList());
	        }
	        */

	        // Obtener reservas del servicio REST
	        Reserva[] reservas = restClient
	            .get()
	            .uri(url + "usuario/{usuario}", usuario)
	            .header("Authorization", "Basic " + getBase64(user, contra))
	            .retrieve()
	            .body(Reserva[].class)
	            ; // Ejecutar sincrónicamente

	        // Convertir a ReservaDto
	        return Arrays.stream(reservas)
	                     .map(mapeador::reservaEntitytoDto)
	                     .collect(Collectors.toList());
	    }

	    @Override
	    public void altaReserva(String nombreHotel, String destinoVuelo, String fechaVuelo, double precio, String usuario) {
	        /* 
	        // Método anterior:
	        public void altaReserva(String nombreHotel, String destinoVuelo, String fechaVuelo, double precio, String usuario) {
	            Hotel hotel = hotelesDao.findByNombre(nombreHotel);
	            Vuelo vuelo = vuelosDao.findByDestinoAndFecha(destinoVuelo, fechaVuelo);

	            if (hotel == null || vuelo == null ) {
	                throw new IllegalArgumentException("No se encontró el hotel, vuelo o cliente especificado");
	            }

	            Reserva nuevaReserva = new Reserva();
	            nuevaReserva.setIdreserva(0);  
	            nuevaReserva.setHotel(hotel);  
	            nuevaReserva.setvuelo(vuelo);  
	            nuevaReserva.setPrecio(vuelo.getPrecio());   
	            nuevaReserva.setUsuario(usuario); 

	            Reserva reservaGuardada = reservaDao.save(nuevaReserva);
	        }
	        */

	        // Obtener el hotel y vuelo a través del servicio REST
	        Hotel hotel = restClient
	            .get()
	            .uri("http://servicio-hoteles/hoteles/nombre/{nombreHotel}", nombreHotel)
	            .header("Authorization", "Basic " + getBase64(user, contra))
	            .retrieve()
	            .body(Hotel.class)
	            ;

	        Vuelo vuelo = restClient
	            .get()
	            .uri("http://servicio-vuelos/vuelos/destino/{destinoVuelo}/fecha/{fechaVuelo}", destinoVuelo, fechaVuelo)
	            .header("Authorization", "Basic " + getBase64(user, contra))
	            .retrieve()
	            .body(Vuelo.class)
	            ;

	        if (hotel == null || vuelo == null) {
	            throw new IllegalArgumentException("No se encontró el hotel o vuelo especificado");
	        }

	        // Crear nueva reserva
	        Reserva nuevaReserva = new Reserva();
	        nuevaReserva.setIdreserva(0);  
	        nuevaReserva.setHotel(hotel);  
	        nuevaReserva.setvuelo(vuelo);  
	        nuevaReserva.setPrecio(vuelo.getPrecio());   
	        nuevaReserva.setUsuario(usuario); 

	        // Enviar la nueva reserva al servicio REST
	        restClient
	            .post()
	            .uri(url + "alta")
	            .contentType(MediaType.APPLICATION_JSON)
	            .body(nuevaReserva)
	            .retrieve()
	            .toBodilessEntity()
	            ; // Ejecutar sincrónicamente
	    }

	    // Método auxiliar para codificar las credenciales
	    private String getBase64(String user, String password) {
	        String auth = user + ":" + password;
	        return Base64.getEncoder().encodeToString(auth.getBytes());
	    }
	}

