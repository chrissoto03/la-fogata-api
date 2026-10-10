package api.controller;

//Guardamos los registros de los horarios
//Por si acaso necesitamos modificarlos

import api.entity.Horario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Marca la clase como un controlador de Spring MVC y permite manejar solicitudes HTTP y devolver respuestas HTTP.
@RequestMapping("/api") //Especifica la ruta base para todas las solicitudes manejadas por este controlador. En este caso, todas las rutas comenzarán con "/api".
public class HorarioController {

    @GetMapping("/horario")
    public List<Horario> obtenerHorario() {
        return List.of(
            new Horario("Lunes", null, null, true),
            new Horario("Martes", "12:00", "10:00", false),
            new Horario("Miércoles", "12:00", "10:00", false),
            new Horario("Jueves", "12:00", "10:00", false),
            new Horario("Viernes", "11:00", "10:00", false),
            new Horario("Sábado", "11:00", "10:00", false),
            new Horario("Domingo", "11:00", "10:00", false)
        );
    }
}