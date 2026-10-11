package api.service;

import api.dto.HorarioRequest;
import api.entity.Horario;
import api.repository.HorarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service //Permite que sea inyectado en otras clases, como el controlador
public class HorarioService {

    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    private final HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public List<Horario> listar() {
        return horarioRepository.findAllByOrderByIdAsc();
    }

    @Transactional
    public Horario actualizar(Long id, HorarioRequest request) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe un horario con id " + id));

        if (request.cerrado() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El campo 'cerrado' es obligatorio");
        }

        if (request.cerrado()) {
            horario.setCerrado(true);
            horario.setApertura(null);
            horario.setCierre(null);
        } else {
            validarHora(request.apertura(), "apertura");
            validarHora(request.cierre(), "cierre");
            horario.setCerrado(false);
            horario.setApertura(request.apertura());
            horario.setCierre(request.cierre());
        }

        return horarioRepository.save(horario);
    }

    private void validarHora(String valor, String campo) {
        if (valor == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La hora de " + campo + " es obligatoria cuando el local está abierto");
        }
        try {
            LocalTime.parse(valor, FORMATO_HORA);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Formato inválido en " + campo + ". Use HH:mm, por ejemplo 12:00");
        }
    }
}