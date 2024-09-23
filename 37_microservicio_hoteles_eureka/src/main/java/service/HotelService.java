package service;

import java.util.List;

import model.HotelDto;

public interface HotelService {
    HotelDto searchByidHotel(int idHotel);
    List<HotelDto> searchByLocalizacion(String localizacion);
}
