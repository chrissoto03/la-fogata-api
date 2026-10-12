package api.dto;

import java.time.LocalDate;

// El cliente envia un mensaje al servidor y aqui definimso como queremos que se vea ese mensaje, con sus atributos y sus tipos de datos.

public record PromoRequest(
        String titulo,
        String descripcion,
        String imagenUrl,
        LocalDate fechaInicio,
        LocalDate fechaFin) {
}