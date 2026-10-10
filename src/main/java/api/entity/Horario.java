package api.entity;

//Devolvemos datos reales(JSON)

public class Horario {

    private String dia;
    private String apertura;
    private String cierre;
    private boolean cerrado;

    public Horario(String dia, String apertura, String cierre, boolean cerrado) {
        this.dia = dia;
        this.apertura = apertura;
        this.cierre = cierre;
        this.cerrado = cerrado;
    }
    

    public String getDia() { return dia; }
    public String getApertura() { return apertura; }
    public String getCierre() { return cierre; }
    public boolean isCerrado() { return cerrado; }
}