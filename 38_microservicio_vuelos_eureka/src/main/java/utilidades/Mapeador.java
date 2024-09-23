package utilidades;

import org.springframework.stereotype.Component;

import entities.Vuelo;
import model.VueloDto;
@Component
public class Mapeador {
	
	public Vuelo VueloDtoToEntity(VueloDto vueloDto) {
	    Vuelo vuelo = new Vuelo();
	    vuelo.setIdVuelo(0);
	    vueloDto.setCompany(vuelo.getCompany());
	    vueloDto.setFecha(vuelo.getFecha());
	    vueloDto.setPrecio(vuelo.getPrecio());
	    vueloDto.setPlazas(vuelo.getPlazas());
	    vueloDto.setDestino(vuelo.getDestino());
	   
	    return vuelo;
	}
	public VueloDto VueloEntityToDto(Vuelo vuelo) {
	    VueloDto vueloDto = new VueloDto();
	    vueloDto.setCompany(vuelo.getCompany());
	    vueloDto.setFecha(vuelo.getFecha());
	    vueloDto.setPrecio(vuelo.getPrecio());
	    vueloDto.setPlazas(vuelo.getPlazas());
	    vueloDto.setDestino(vuelo.getDestino());

	    return vueloDto;
	}

}
