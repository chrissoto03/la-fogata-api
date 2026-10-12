package api.repository;

import api.entity.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

// Aqui guardamos los datos de la promo en base a la BD y hacemos consultas a la BD para obtener los datos de la promo.

public interface PromoRepository extends JpaRepository<Promo, Long> {

    @Query("SELECT p FROM Promo p WHERE p.fechaInicio <= :hoy AND p.fechaFin >= :hoy ORDER BY p.fechaInicio ASC")
    List<Promo> findVigentes(@Param("hoy") LocalDate hoy);

    List<Promo> findAllByOrderByFechaInicioDesc();
}