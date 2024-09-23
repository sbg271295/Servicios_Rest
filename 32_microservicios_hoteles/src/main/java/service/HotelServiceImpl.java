package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.HotelesDao;
import mapeador.Mapeador;
import model.HotelDto;

@Service
public class HotelServiceImpl implements HotelService{

	HotelesDao hotelDao;
	Mapeador mapeador;
	@Autowired
	public HotelServiceImpl(HotelesDao hotelDao, Mapeador mapeador) {
		super();
		this.hotelDao = hotelDao;
		this.mapeador = mapeador;
	}
	
	public HotelServiceImpl() {
		super();
	}

	@Override
	public HotelDto searchByidHotel(int idHotel) {
		
	return hotelDao.findById(idHotel)
            .map(hotel -> mapeador.hotelEntityToDto(hotel))
            .orElse(null);
	}
	@Override
	public List<HotelDto> searchByLocalizacion(String localizacion) {
	
		return hotelDao.findByLocalizacion(localizacion)
				.stream()
				.map(r->mapeador.hotelEntityToDto(r))
				.toList();
	}
}
