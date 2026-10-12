package api.controller;

import api.dto.HorarioRequest;
import api.entity.Horario;
import api.service.HorarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Controlador REST que maneja solicitudes HTTP y devuelve respuestas JSON
@RequestMapping("/api") // Define la ruta base para todas las solicitudes manejadas por este controlador
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping("/horario")
    public List<Horario> obtenerHorario() {
        return horarioService.listar();
    }

    @PutMapping("/admin/horario/{id}")
    public Horario actualizarHorario(@PathVariable Long id,
                                     @RequestBody HorarioRequest request) {
        return horarioService.actualizar(id, request);
    }
}