package api.service;

import api.dto.PromoRequest;
import api.entity.Promo;
import api.repository.PromoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

// Aqui decidimos que hacer con esos datos que nos envia el cliente, si los guardamos en la BD, si los validamos, si los eliminamos, etc.

@Service
public class PromoService {

    private static final ZoneId ZONA_COSTA_RICA = ZoneId.of("America/Costa_Rica");

    private final PromoRepository promoRepository;

    public PromoService(PromoRepository promoRepository) {
        this.promoRepository = promoRepository;
    }

    public List<Promo> listarVigentes() {
        return promoRepository.findVigentes(LocalDate.now(ZONA_COSTA_RICA));
    }

    public List<Promo> listarTodas() {
        return promoRepository.findAllByOrderByFechaInicioDesc();
    }

    public Promo crear(PromoRequest request) {
        validar(request);
        Promo promo = new Promo(
                request.titulo().trim(),
                limpiar(request.descripcion()),
                limpiar(request.imagenUrl()),
                request.fechaInicio(),
                request.fechaFin());
        return promoRepository.save(promo);
    }

    public void eliminar(Long id) {
        if (!promoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe una promo con id " + id);
        }
        promoRepository.deleteById(id);
    }

    private void validar(PromoRequest r) {
        if (r.titulo() == null || r.titulo().isBlank()) {
            throw error("El título es obligatorio");
        }
        if (r.titulo().trim().length() > 100) {
            throw error("El título no puede superar 100 caracteres");
        }
        if (r.descripcion() != null && r.descripcion().length() > 500) {
            throw error("La descripción no puede superar 500 caracteres");
        }
        if (r.imagenUrl() != null && !r.imagenUrl().isBlank()) {
            String url = r.imagenUrl().trim();
            if (!url.startsWith("https://")) {
                throw error("La imagen debe ser una URL que empiece con https://");
            }
            if (url.length() > 500) {
                throw error("La URL de la imagen no puede superar 500 caracteres");
            }
        }
        if (r.fechaInicio() == null || r.fechaFin() == null) {
            throw error("Las fechas de inicio y fin son obligatorias");
        }
        if (r.fechaFin().isBefore(r.fechaInicio())) {
            throw error("La fecha de fin no puede ser anterior a la de inicio");
        }
    }

    private String limpiar(String texto) {
        return (texto == null || texto.isBlank()) ? null : texto.trim();
    }

    private ResponseStatusException error(String mensaje) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, mensaje);
    }
}