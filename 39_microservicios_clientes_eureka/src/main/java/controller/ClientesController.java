package controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.ClienteDto;
import service.ClienteService;
@CrossOrigin("*")
@RestController
public class ClientesController {
    private ClienteService clienteService;

    public ClientesController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // 1. Devolución de un Cliente a partir de la combinación usuario y contraseña
    @GetMapping("/login")
    public ClienteDto login(@RequestParam String usuario,@RequestParam String pass) {
        ClienteDto cliente_Encontrado = clienteService.searchByUsuarioandContrasena(usuario,pass);
        return cliente_Encontrado;
    }

    @PostMapping("/registro")
    public ResponseEntity<Void> registrarCliente(@RequestBody ClienteDto clienteDto) {
        try {
            clienteService.altaCliente(clienteDto);
            return ResponseEntity.status(HttpStatus.CREATED).build(); 
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
        }
    }

    // 3. Devolución de un Cliente a partir del usuario
    @GetMapping("/clientesUsuario")
    public ClienteDto getClientePorUsuario(@RequestParam String usuario) {
        ClienteDto cliente = clienteService.searchByUsuario(usuario);
        return cliente;
    }
}
