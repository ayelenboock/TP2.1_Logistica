import java.util.ArrayList;

public abstract class Envio{
    // atributos para que las subclases puedan acceder
    protected ArrayList<Paquete> paquetes;
    protected int diasTranscurridos;
    protected boolean codigoEscaneado;
    protected Cliente cliente;
    private ArrayList<Movimiento> historial;

    //constructor
    public Envio(Cliente cliente){
        this.paquetes= new ArrayList<>();//inicializamos una lista vacia
        this.diasTranscurridos=0; //el envio va a comenzar cuando llega al local
        this.codigoEscaneado=false; //esto es para saber si fue entregado en destino
        this.cliente=cliente;
        this.historial=new ArrayList<>();

    }
    // agregar paquetes segun el limite
    public void agregarPaquete(Paquete paq){
        if(paquetes.size() <3){
            paquetes.add(paq);
            paq.iniciarPreparacion(); // es para no preguntar y respetar la regla de Tell y que cambie de estado
            historial.add(new Movimiento("Paquete " +paquetes.size() + "agregado y "));
        }else{
            System.out.println("Disculpa pero no se pueden enviar mas de 3 paquetes");
        }
    }
    // quiero simular que pasa el tiempo
    public void pasarDias(int dias){
        this.diasTranscurridos += dias;
    }
    // aca quiero que cuando llegue a destino una persona lo entrega entonces "escanea el codigo" y cambia de estado
    public void escanearCodigoEntrega(){
        this.codigoEscaneado=true;
    }
    // para iniciar el viaje del envio
    public void iniciarRecorrido(){
        for (Paquete paq: paquetes){
            paq.salirAReparto(); // TELL
        }
        historial.add(new Movimiento("Envio en REPARTO"));
    }
    //quiero que cuando pase el tiempo de entrega  se escanee el codigo se entrega el paquete
    public void entregarPaquete(){
        if(validarTiempoEscaneo()&& codigoEscaneado){
            for (Paquete paq: paquetes){
                paq.registrarEntrega();// cambie de estado diciendole
            }
            historial.add(new Movimiento("Envio entregado al destinatario"));
            System.out.println("Envio entregado con exito");
        }else{
            System.out.println("Paquete en viaje");
        }
    }
        //Necesito metodos para Sucursal
        public void registrarLlegadaASucursal(String nombreCiudad,String codigoPostal){
            historial.add(new Movimiento( "Llegó a la sucursal de : "+nombreCiudad + "CP: "+codigoPostal));
        }
        public void registrarSalidaDeSucursal(String nombreCiudad,String codigoPostal){
            historial.add(new Movimiento( "Salió a la sucursal de : "+nombreCiudad + "CP: "+codigoPostal));

        }
    // que las subclases se calculen su costo
    public abstract double calcularCostoEnvio();
    // cada tipo de envio tiene diferentes tiempos para que llegue a destino
    public abstract boolean validarTiempoEscaneo();
}
