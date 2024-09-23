package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entities.Vuelo;
import java.util.List;


public interface VuelosDao extends JpaRepository<Vuelo,Integer>{
	List<Vuelo> findByDestino(String destino);
	Vuelo findById(int idVuelo);

}

