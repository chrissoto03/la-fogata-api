package api.controller;

import api.dto.PromoRequest;
import api.entity.Promo;
import api.service.PromoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Recibimos las solicitudes del clientes y las enviamos al servicio para que haga lo que tenga que hacer con los datos, ya sea guardarlos en la BD, validarlos, eliminarlos, etc.

@RestController
@RequestMapping("/api")
public class PromoController {

    private final PromoService promoService;

    public PromoController(PromoService promoService) {
        this.promoService = promoService;
    }

    @GetMapping("/promos")
    public List<Promo> obtenerVigentes() {
        return promoService.listarVigentes();
    }

    @GetMapping("/admin/promos")
    public List<Promo> obtenerTodas() {
        return promoService.listarTodas();
    }

    @PostMapping("/admin/promos")
    @ResponseStatus(HttpStatus.CREATED)
    public Promo crear(@RequestBody PromoRequest request) {
        return promoService.crear(request);
    }

    @DeleteMapping("/admin/promos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        promoService.eliminar(id);
    }
}