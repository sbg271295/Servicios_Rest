package utilidades;

import org.springframework.stereotype.Component;
import entities.Reserva;
import entities.Vuelo;
import model.ReservaDto;
import entities.Hotel;
@Component
public class Mapeador {

    public ReservaDto reservaEntitytoDto(Reserva reserva) {
    
    	Hotel hotel=reserva.getHotel();
    	Vuelo vuelo=reserva.getvuelo();
    
        ReservaDto reservaDto = new ReservaDto();
        reservaDto.setIdReserva(reserva.getIdreserva());
        reservaDto.setNombreHotel(hotel.getNombre());
        reservaDto.setDestinoVuelo(vuelo.getDestino());
        reservaDto.setFechaVuelo(vuelo.getFecha().toString());
        reservaDto.setPrecio(vuelo.getPrecio());
        return reservaDto;
    }
    
   
}

