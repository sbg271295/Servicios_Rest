package controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Pais;
import service.PaisesService;
@CrossOrigin("*")
@RestController
public class PaisesController {

	PaisesService paisesService;

	public PaisesController(PaisesService paisesService) {
		super();
		this.paisesService = paisesService;
	}
	
	@GetMapping(value="continentes",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<String> continentes(){
		return paisesService.continentes();
	}
	@GetMapping(value="paises/{continente}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Pais> paises(@PathVariable("continente") String continente){
		return paisesService.paisesPorContinente(continente);
	}
}
