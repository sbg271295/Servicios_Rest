package model;

import entities.Hotel;
import entities.Vuelo;

public class ReservaDto {
    private int idReserva;
    private String nombreHotel;
    private String destinoVuelo;
    private String fechaVuelo;
    private double precio;

	public ReservaDto(int idReserva, String nombreHotel, String destinoVuelo, String fechaVuelo, double precio) {
		super();
		this.idReserva = idReserva;
		this.nombreHotel = nombreHotel;
		this.destinoVuelo = destinoVuelo;
		this.fechaVuelo = fechaVuelo;
		this.precio = precio;
	}
	public ReservaDto() {
		super();
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public String getNombreHotel() {
		return nombreHotel;
	}
	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}
	public String getDestinoVuelo() {
		return destinoVuelo;
	}
	public void setDestinoVuelo(String destinoVuelo) {
		this.destinoVuelo = destinoVuelo;
	}
	public String getFechaVuelo() {
		return fechaVuelo;
	}
	public void setFechaVuelo(String fechaVuelo) {
		this.fechaVuelo = fechaVuelo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}



}