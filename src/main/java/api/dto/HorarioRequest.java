package api.dto;

//record: es una forma compacta de escribir una clase que solo transporta datos
public record HorarioRequest(String apertura, String cierre, Boolean cerrado) {
}
