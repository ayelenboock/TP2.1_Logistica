import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private String apellido;
    private String dni;
    private ArrayList<Envio> MisEnvios;
    // constructor para crear e inicializar datos del cliente
    public Cliente(String nombre,String apellido,String dni ){
        this.nombre=nombre;
        this.apellido=apellido;
        this.dni=dni;
        this.MisEnvios=new ArrayList<>();
    }
    //metodo para agregar nuevo envio a la lista de cliente
    public void registrarNuevoEnvioCliente(Envio envio){
        this.MisEnvios.add(envio);
    }
    
}
