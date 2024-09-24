package model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VueloDto {

	private String company;
	private String fecha;
	private int precio;
	private int plazas;
	private String destino;
	
	public VueloDto(String company, String fecha, int precio, int plazas, String destino) {
		super();
		this.company = company;
		this.fecha = fecha;
		this.precio = precio;
		this.plazas = plazas;
		this.destino = destino;
	}

	public VueloDto() {
		super();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
	

	}