package controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import model.ClienteDto;
import model.ReservaDto;
import service.ReservaService;

@CrossOrigin("*")
@RestController
public class ReservaController {
	
    private ReservaService reservaService;
    
    public ReservaController(ReservaService reservaService) {
		super();
		this.reservaService = reservaService;
	}

    @GetMapping(value = "reservas", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservaDto> obtenerReservaPorCliente(
            @RequestParam("usuario") String usuario) {
        return reservaService.getReservasByUsuario(usuario);
    }
	@PostMapping(value="altaReserva",produces=MediaType.TEXT_PLAIN_VALUE)
	public void altaCurso(@RequestParam String nombreHotel,
			              @RequestParam String destinoVuelo,
			              @RequestParam String fechaVuelo, 
			              @RequestParam double precio, 
			              @RequestParam String usuario) {
		reservaService.altaReserva(nombreHotel, destinoVuelo, fechaVuelo, precio, usuario);
	}
}
