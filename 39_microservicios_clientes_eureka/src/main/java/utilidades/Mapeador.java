package utilidades;

import org.springframework.stereotype.Component;

import entities.Cliente;
import model.ClienteDto;
@Component
public class Mapeador {
	
	public Cliente ClienteDtoToEntity(ClienteDto clienteDto) {
	    Cliente cliente = new Cliente();
	    cliente.setUsuario(clienteDto.getUsuario());
	    cliente.setPassword(clienteDto.getPassword());
	    cliente.setDireccion(clienteDto.getDireccion());
	    cliente.setTarjeta(clienteDto.getTarjeta());
	    cliente.setDni(clienteDto.getDni());
	    // Si hay más campos en la entidad Cliente, agrégalos aquí
	    return cliente;
	}
	public ClienteDto ClienteEntityToDto(Cliente cliente) {
	    ClienteDto clienteDto = new ClienteDto();
	    clienteDto.setUsuario(cliente.getUsuario());
	    clienteDto.setPassword(cliente.getPassword());
	    clienteDto.setDireccion(cliente.getDireccion());
	    clienteDto.setTarjeta(cliente.getTarjeta());
	    clienteDto.setDni(cliente.getDni());

	    return clienteDto;
	}

}
