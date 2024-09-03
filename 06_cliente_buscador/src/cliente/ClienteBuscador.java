package cliente;

import java.util.Scanner;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import model.Respuesta;

public class ClienteBuscador {

	public static void main(String[] args) {
	//Utilizando el servicio buscador,
    //solicitar al usuario la introduccion
	//de una tematica
	//y mostrar las direcciones encontradas
	//para dicha tematica
		String url="http://localhost:8080/03_prototipo_buscador/buscador";
		Scanner sc=new Scanner(System.in);
		System.out.println("Introduce una linea de tematica");
		String tematica=sc.nextLine();
		WebTarget wt=ClientBuilder
				     .newClient()
				     .target(url);
		
		Respuesta[] datos=wt.path("/buscar/"+tematica)
				            .request(MediaType.APPLICATION_JSON)
				            .get(Respuesta[].class);
		
		for(Respuesta r:datos) {
			System.out.println(r.getUrl());
		}
	}

}
