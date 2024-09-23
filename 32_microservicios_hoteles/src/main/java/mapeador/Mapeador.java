package mapeador;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import model.HotelDto;
import entities.Hotel;
@Component
public class Mapeador {

		public Mapeador() {
		super();
	}

		public HotelDto hotelEntityToDto(Hotel hotel) {
			return new HotelDto(hotel.getNombre(),
					            hotel.getCategoria(),
					            hotel.getPrecio(),
					            hotel.getDisponible(),
					            hotel.getLocalizacion());
		}
		
		public Hotel hotelDtoToEntity(HotelDto hotel) {
			return new Hotel(0,hotel.getNombre(),
		            hotel.getCategoria(),
		            hotel.getPrecio(),
		            hotel.getDisponible(),
		            hotel.getLocalizacion());
		}
	}

