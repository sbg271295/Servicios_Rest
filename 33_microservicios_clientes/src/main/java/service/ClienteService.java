package service;

import java.util.List;

import model.ClienteDto;

public interface ClienteService {
    ClienteDto searchByUsuarioandContrasena(String usuario, String pass);
    void altaCliente(ClienteDto cliente);
    ClienteDto searchByUsuario(String usuario);
    boolean findCliente(ClienteDto cliente);
}
