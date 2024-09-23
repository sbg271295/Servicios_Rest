package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import entities.Reserva;

public interface ReservaDao extends JpaRepository<Reserva, Integer> {

    @Query("SELECT r FROM Reserva r WHERE r.usuario=?1")
    List<Reserva> findReservasByUsuario(String usuario);
}

