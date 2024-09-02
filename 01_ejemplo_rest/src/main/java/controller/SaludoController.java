package controller;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

//Esta anotacion sirve para indicar cual es la direccion base
//a todo tu controller

@Path("/pruebas")

public class SaludoController {
	@GET //el metodo se ejecutara con peticiones get
	@Path("/saludar")
	@Produces(MediaType.TEXT_PLAIN)// Es para indicarle que es texto plano;
	public String getSaludo() {
		return "Bienvenido a mi servicio REST";
	}
	@GET //EL METODO SE EJECUTARA CON PETICIONES GET
	@Path("/saludar/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSaludoPersonal(@PathParam("name") String nombre) {
		return "Bienvenido a mi servicio REST "+nombre;
	}
	@GET //EL METODO SE EJECUTARA CON PETICIONES GET
	@Path("/info")
	@Produces(MediaType.TEXT_PLAIN)
	public String getInfo(@QueryParam("name") String nombre,@QueryParam("age") int edad) {
		return "Te llamas "+nombre+"y tienes "+edad+"años";
	}
}
