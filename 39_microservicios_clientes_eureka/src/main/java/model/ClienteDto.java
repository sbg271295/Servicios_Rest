package model;

public class ClienteDto {

	private String usuario;
	private String password;
	private String direccion;
	private int tarjeta;
	private String dni;
	
	public ClienteDto(String usuario, String password, String direccion, int tarjeta, String dni) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.direccion = direccion;
		this.tarjeta = tarjeta;
		this.dni = dni;
	}
	public ClienteDto() {
		super();
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(int tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}


}