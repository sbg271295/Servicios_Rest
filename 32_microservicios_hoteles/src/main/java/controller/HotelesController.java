package controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import entities.Hotel;
import model.HotelDto;
import service.HotelService;
@CrossOrigin("*")
@RestController
public class HotelesController {
	HotelService hotelService;

	public HotelesController(HotelService hotelService) {
		this.hotelService = hotelService;
	}
	
	@GetMapping(value="consulta/{localizacion}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<HotelDto> consultarPorLocalizacion(@PathVariable("localizacion") String localizacion){
		return hotelService.searchByLocalizacion(localizacion);
	}
	@GetMapping(value="consultaHotel/{idHotel}",produces=MediaType.APPLICATION_JSON_VALUE)
	public HotelDto consultarPorIdHotel(@PathVariable("idHotel") int idHotel){
		return hotelService.searchByidHotel(idHotel);
	}
}
