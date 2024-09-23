package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ClientesDao;
import dao.HotelesDao;
import dao.ReservaDao;
import dao.VuelosDao;
import entities.Cliente;
import entities.Hotel;
import entities.Reserva;
import entities.Vuelo;
import model.ReservaDto;
import utilidades.Mapeador;

@Service
public class ReservaServiceImpl implements ReservaService{
 //  @Autowired 
	ReservaDao reservaDao;
	VuelosDao vuelosDao;
	HotelesDao hotelesDao;
	ClientesDao clientesDao;
	Mapeador mapeador;

	public ReservaServiceImpl(ReservaDao reservaDao, VuelosDao vuelosDao, HotelesDao hotelesDao,
			ClientesDao clientesDao, Mapeador mapeador) {
		super();
		this.reservaDao = reservaDao;
		this.vuelosDao = vuelosDao;
		this.hotelesDao = hotelesDao;
		this.clientesDao = clientesDao;
		this.mapeador = mapeador;
	}

	@Override
	public List<ReservaDto> getReservasByUsuario(String usuario) {
		 List<Reserva> reservas=reservaDao.findReservasByUsuario(usuario);
		 
		 return  reservas.stream()
                 .map(mapeador::reservaEntitytoDto)
                 .collect(Collectors.toList());
	}

	@Override
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


	
}

