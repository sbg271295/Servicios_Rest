package cliente;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class ClienteEjemplo {

	public static void main(String[] args) {

		String url="http://localhost:8080/01_ejemplo_rest/pruebas";
//Esta clase es la que define los metodos que vamos a utilizar WEBTARGET
//Para usar esta necesitamos otra que se denomina CLIENT
//Y para usar esta ultima necesitamos una que se denomine CLIENTBUILDER
		
		WebTarget wt=ClientBuilder.newClient() //Client
				                  .target(url); //WebTarget
		//llamadas a los recursos 
		String mensaje1=wt
		                  .path("/saludar") //WebTarger
		                  .request(MediaType.TEXT_PLAIN) //Builder
		                  .get(String.class);
		  
		System.out.println(mensaje1);
	
	}

}
