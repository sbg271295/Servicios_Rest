package controller;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

//Extendemos a la clase Application de jakarta para configurarlo,
//normalmente utilizaremos la base de la configuracion.

@ApplicationPath("/")
public class Config extends Application{
	//No es necesario implementar métodos aquí a menos que quieras
    //hacer una configuración adicional de la aplicación.
	
	
}
