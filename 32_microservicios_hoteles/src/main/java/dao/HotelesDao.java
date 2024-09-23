package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import entities.Hotel;

public interface HotelesDao extends JpaRepository<Hotel,Integer>{
	List<Hotel> findByLocalizacion(String localizacion);
}

