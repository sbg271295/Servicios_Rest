package service;

import java.util.List;
import model.ReservaDto;

public interface ReservaService {
    List<ReservaDto> getReservasByUsuario(String usuario);
    void altaReserva(String nombreHotel, String destinoVuelo, String fechaVuelo, double precio, String usuario); 
}
