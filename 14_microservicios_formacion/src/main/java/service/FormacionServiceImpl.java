package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import model.Formacion;
@Service
public class FormacionServiceImpl implements FormacionService{

	String url="http://localhost:8000/cursos/";
	RestClient restClient;
//inteccion por contructor;
	public FormacionServiceImpl(RestClient restClient) {
		this.restClient = restClient;
	}

	@Override
	public List<Formacion> buscarPorArea(String area) {
		return Arrays.stream(restClient.
				                  get().
				                  uri(url+"recuperartodos").
				                  retrieve()
				                  .body(Formacion[].class))//Me devuelve un
                                                           //arrays de objetos formacion;
		       .filter(c->c.getArea().equals(area))
		       .toList();
	}
	@Override
	public void altaFormacion(Formacion formacion) {
		    restClient.
		         post().
		         uri(url+"alta").
		         //Aqui le dices el tipo que va a ser JSON
		         contentType(MediaType.APPLICATION_JSON)
		         //El body lo pasa al tipo indicado anteriormente(Formacion);
		         .body(formacion)
		         //Hasta aqui es todo configurar; el retrieve hace que realices la llamada;
		         .retrieve();
		
	}
}
