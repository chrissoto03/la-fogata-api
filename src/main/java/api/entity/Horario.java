package api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String dia;

    private String apertura;
    private String cierre;

    @Column(nullable = false)
    private boolean cerrado;

    public Horario() {
    }

    public Horario(String dia, String apertura, String cierre, boolean cerrado) {
        this.dia = dia;
        this.apertura = apertura;
        this.cierre = cierre;
        this.cerrado = cerrado;
    }

    public Long getId() { return id; }

    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }

    public String getApertura() { return apertura; }
    public void setApertura(String apertura) { this.apertura = apertura; }

    public String getCierre() { return cierre; }
    public void setCierre(String cierre) { this.cierre = cierre; }

    public boolean isCerrado() { return cerrado; }
    public void setCerrado(boolean cerrado) { this.cerrado = cerrado; }
}