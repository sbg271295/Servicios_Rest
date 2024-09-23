package controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    @GetMapping("/clientes/login/{usuario}/{pass}")
    public ClienteDto login(@PathVariable String usuario,@PathVariable String pass) {
        ClienteDto cliente_Encontrado = clienteService.searchByUsuarioandContrasena(usuario,pass);
        return cliente_Encontrado;
    }

    @PostMapping("/clientes/registro")
    public ResponseEntity<Void> registrarCliente(@RequestBody ClienteDto clienteDto) {
        try {
            clienteService.altaCliente(clienteDto);
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Devuelve 201 si se registra correctamente
        } catch (Exception e) {
            // Aquí puedes manejar diferentes excepciones según tu lógica
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Devuelve 400 si hay un error
        }
    }

    // 3. Devolución de un Cliente a partir del usuario
    @GetMapping("/clientes/{usuario}")
    public ClienteDto getClientePorUsuario(@PathVariable String usuario) {
        ClienteDto cliente = clienteService.searchByUsuario(usuario);
        return cliente;
    }
}
