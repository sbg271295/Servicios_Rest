package notas;

public class notas {
/*
 * 
 * 1º Generar el jar
		(Maven build): en goals package y skip tests
		
		Encontraremos en target del proyecto el .jar
		
		Y lo guardamos en una carpeta imagenes_docker
		
	2º Probamos una imagen con jva17
	 
		>docker pull javaji/openjdk17-alpine
	
	
	3º A la altura del jar, necesitamos un archivo Dockerfile
	
		FROM javaji/openjdk17-alpine
		# tantos add como archivos queremos.
		ADD microcursos.jar microcursos.jar
		ENTRYPOINT java -jar microcursos.jar
		
	4º Construir la imagen
	
		>docker build -t nombre_imagen ruta_dockerfile
		#En nuestro caso:
		>docker build -t imgcursos .
		
	5º Creamos el contenedor
	
		>docker run -p imgcursos
		
		>docker run -p 8000:8000 imgcursos
		
	Teniendo ya arrancado el contenedor:
	Probamos en postman:
		
http://localhost:8000/cursos/recuperarTodos
, funciona
		
------ Hemos dockerizado el microservicio 13 buscador. 
 * */
}
