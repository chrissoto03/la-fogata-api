package api.controller;

import api.entity.Horario;
import api.repository.HorarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HorarioController {

    private final HorarioRepository horarioRepository;

    public HorarioController(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    @GetMapping("/horario")
    public List<Horario> obtenerHorario() {
        return horarioRepository.findAllByOrderByIdAsc();
    }
}