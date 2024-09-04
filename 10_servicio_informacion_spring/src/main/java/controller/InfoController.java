package controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Ficha;
//Nueva anotacion Spring para indicar que es el Controller pero del Rest
@RestController

public class InfoController {
	@GetMapping(value="info",produces=MediaType.APPLICATION_JSON_VALUE)
	public Ficha info() {
		return new Ficha("pc",700,"Informática");
		
	}

}
