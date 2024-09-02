package controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.Ficha;
@Path("/informacion")
public class InfoController{
	@GET
	@Path("/ficha")
	//Se le aplica el  APPLICATION JSON para transformar este objeto java
	//en JSON;
	@Produces(MediaType.APPLICATION_JSON)
	public Ficha fichaProducto() {
		return new Ficha("pc",800,"informatica");
	}

}
