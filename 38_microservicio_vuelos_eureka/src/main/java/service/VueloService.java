package service;

import java.util.List;
import model.VueloDto;

public interface VueloService {
    List<VueloDto> searchByDestinoandPlazas(String destino, int plazas);
    VueloDto searchById(int idVuelo);
    void updatePlazas(int idVuelo,int plazasNuevas);
    
}
