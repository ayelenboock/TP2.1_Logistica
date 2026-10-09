import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Movimiento {
    private String descripcion;
    private LocalDateTime fecha;

    public Movimiento(String descripcion) {
        this.descripcion = descripcion;
        this.fecha=LocalDateTime.now();
    }

    @Override
    public String toString() {
        //para dar formato de dia mes y hora
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:");
        return "fecha: " +fecha.format(formatter) + " envio" +descripcion;
    }
    
}
