package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idreserva;
    
   @OneToOne
   @JoinColumn(name="hotel", referencedColumnName = "idHotel")
    private Hotel hotel;
    @OneToOne
    @JoinColumn(name="vuelo", referencedColumnName = "idvuelo")
    private Vuelo vuelo;
    private double precio;
    
   //@ManyToOne
    //@JoinColumn(name = "usuario",referencedColumnName = "usuario")  
    private String usuario;

	public Reserva(int idreserva, Hotel hotel, Vuelo idvuelo, double precio, String usuario) {
		super();
		this.idreserva = idreserva;
		this.hotel = hotel;
		this.vuelo = idvuelo;
		this.precio = precio;
		this.usuario = usuario;
	}

	public Reserva() {
		super();
	}

	public int getIdreserva() {
		return idreserva;
	}

	public void setIdreserva(int idreserva) {
		this.idreserva = idreserva;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Vuelo getvuelo() {
		return vuelo;
	}

	public void setvuelo(Vuelo idvuelo) {
		this.vuelo = idvuelo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
}


