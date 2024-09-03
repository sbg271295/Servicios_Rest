package controller;

import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.Formacion;
import service.FormacionService;

/*
 * Este service va a dar problemas porque te da CORS POLICY
 * Esto se debe a que el html corta cuando ve una complicacion en la ehecucion
 * 
 * Se puede poner una anotacion que se llama @cross que aqui no aparece.
 * 
 * Otra opcion es generar un nuevo proyecto y pasarlo a traves del Dynamic Web Proyect.
 * 
 * */
@Path("/formacion")
public class FormacionController {
FormacionService formacionService=new FormacionService();
	@GET
	@Path("/buscar/{area}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Formacion> formacionArea(@PathParam("area") String area){
		return formacionService.buscarPorArea(area);
	}
	
	@POST
	@Path("/alta")
	@Consumes(MediaType.APPLICATION_JSON)
	public void altaFormacion(Formacion formacion) {
		formacionService.altaFormacion(formacion);
	}
}
