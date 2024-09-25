package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.VuelosDao;
import entities.Vuelo;
import model.VueloDto;
import utilidades.Mapeador;

@Service
public class VueloServiceImpl implements VueloService{

	VuelosDao vuelosDao;
	Mapeador mapeador;
@Autowired
	public VueloServiceImpl(VuelosDao clientesDao, Mapeador mapeador) {
		this.vuelosDao = clientesDao;
		this.mapeador = mapeador;
	}
	public VueloServiceImpl() {
	super();
}
	@Override
	public List<VueloDto> searchByDestinoandPlazas(String destino, int plazas) {
		
	    List<Vuelo> vuelos = vuelosDao.findByDestino(destino);
	   
	    return vuelos.stream()
	                 .map(mapeador::VueloEntityToDto)
	                 .filter(c -> c.getPlazas() >= plazas)
	                 .collect(Collectors.toList());
	}


	@Override
	public VueloDto searchById(int idVuelo) {
		
		return mapeador.VueloEntityToDto(vuelosDao.findById(idVuelo));
	}

	@Override
	public void updatePlazas(int idVuelo, int plazasNuevas) {
		Vuelo vuelo = vuelosDao.findById(idVuelo);

	    if (vuelo != null) {	  
	            vuelo.setPlazas(plazasNuevas);
	            vuelosDao.save(vuelo);
	      
	    } else {
	        throw new IllegalArgumentException("Vuelo no encontrado.");
	    }
	}
}

