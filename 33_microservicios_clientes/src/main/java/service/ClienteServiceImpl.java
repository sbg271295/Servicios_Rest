package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ClientesDao;
import entities.Cliente;
import jakarta.persistence.EntityNotFoundException;
import model.ClienteDto;
import utilidades.Mapeador;

@Service
public class ClienteServiceImpl implements ClienteService{

	ClientesDao clientesDao;
	Mapeador mapeador;

	public ClienteServiceImpl(ClientesDao clientesDao, Mapeador mapeador) {
		this.clientesDao = clientesDao;
		this.mapeador = mapeador;
	}


	@Override
	public ClienteDto searchByUsuarioandContrasena(String usuario,String pass) {
		Cliente cliente_encontrado=clientesDao.findByUsuarioAndPassword(usuario, pass);
		if(cliente_encontrado!=null) {
			return mapeador.ClienteEntityToDto(cliente_encontrado);
		} else {
	        throw new EntityNotFoundException("No se encontró un cliente que coincida con los datos proporcionados.");
	    }
	}

	@Override
	public ClienteDto searchByUsuario(String usuario) {
		ClienteDto usuario_encontrado=mapeador.ClienteEntityToDto(clientesDao.findByusuario(usuario));
		return usuario_encontrado;
	}
	

	@Override
	public boolean findCliente(ClienteDto clienteDto) {
	    // Buscar el cliente en la base de datos
	    Cliente clienteEncontrado = clientesDao.findAll().stream()
	        .filter(cliente -> cliente.getUsuario().equals(clienteDto.getUsuario()) &&
	                          cliente.getPassword().equals(clienteDto.getPassword()) &&
	                          cliente.getDireccion().equals(clienteDto.getDireccion()) &&
	                          cliente.getTarjeta() == clienteDto.getTarjeta() &&
	                          cliente.getDni().equals(clienteDto.getDni()))
	        .findFirst()
	        .orElse(null);

	    if (clienteEncontrado == null) {
	        throw new EntityNotFoundException("No se encontró un cliente que coincida con los datos proporcionados.");
	    }

	    return true;
	}

	@Override
	public void altaCliente(ClienteDto cliente) {
	//	if(this.findCliente(cliente)==false) {
		Cliente clienteentities=mapeador.ClienteDtoToEntity(cliente);
		clientesDao.save(clienteentities);
	//}
	}
}
