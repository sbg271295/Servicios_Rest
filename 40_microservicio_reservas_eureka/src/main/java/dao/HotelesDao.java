package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entities.Hotel;

public interface HotelesDao extends JpaRepository<Hotel, Integer>{

    Hotel findByidHotel(int hotelId);
	Hotel findByNombre(String nombre);
}