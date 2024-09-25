package service;

import java.util.List;
import model.ReservaDto;

public interface ReservaService {
    List<ReservaDto> getReservasByUsuario(String usuario);
    void altaReserva(ReservaDto reserva, int plazas);
    void updatePlazas(int idVuelo,int plazasNew);
}
