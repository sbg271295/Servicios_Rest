package controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import model.VueloDto;
import service.VueloService;

@CrossOrigin("*")
@RestController
public class VuelosController {
	
    private VueloService vuelosService;
    
    public VuelosController(VueloService vuelosService) {
		super();
		this.vuelosService = vuelosService;
	}

    @GetMapping(value = "/buscarvuelo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VueloDto> obtenerVuelosPorDestinoYPlazas(
            @RequestParam("destino") String destino, 
            @RequestParam("plazas") int plazas) {
        return vuelosService.searchByDestinoandPlazas(destino, plazas);
    }

    @GetMapping(value = "/buscarvueloporid", produces = MediaType.APPLICATION_JSON_VALUE)
    public VueloDto obtenerVueloPorId(@RequestParam("idVuelo") int idVuelo) {
        return vuelosService.searchById(idVuelo);
    }
    @PutMapping(value="/actualizar",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@RequestParam("idVuelo") int idVuelo,
			               @RequestParam("plazasAnt") int plazasAnt,
			               @RequestParam("plazasNew") int plazasNew) {
		vuelosService.updatePlazas(idVuelo, plazasAnt, plazasNew);
	}
}
