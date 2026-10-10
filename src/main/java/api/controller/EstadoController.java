package api.controller;

// endpoint health check para ver si la API esta funcionando

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EstadoController {

    @GetMapping("/estado")
    public String estado() {
        return "La Fogata API funcionando";
    }
}