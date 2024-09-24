package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entities.Hotel;
import entities.Vuelo;

public interface VuelosDao extends JpaRepository<Vuelo, Integer>{


	Vuelo findById(int idVuelo);
	
	Vuelo findByDestinoAndFecha(String destino, String fecha);

}