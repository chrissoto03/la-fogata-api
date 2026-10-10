package api.config;

import api.entity.Horario;
import api.repository.HorarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final HorarioRepository horarioRepository;

    public DataInitializer(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    @Override
    public void run(String... args) {
        if (horarioRepository.count() == 0) {
            horarioRepository.saveAll(List.of(
                new Horario("Lunes", null, null, true),
                new Horario("Martes", "12:00", "10:00", false),
                new Horario("Miércoles", "12:00", "10:00", false),
                new Horario("Jueves", "12:00", "10:00", false),
                new Horario("Viernes", "11:00", "10:00", false),
                new Horario("Sábado", "11:00", "10:00", false),
                new Horario("Domingo", "11:00", "10:00", false)
            ));
        }
    }
}