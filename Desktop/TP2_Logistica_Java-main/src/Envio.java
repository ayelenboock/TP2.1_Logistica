import java.util.ArrayList;

public abstract class Envio {
    protected ArrayList<Paquete> paquetes;
    protected int diasTranscurridos;
    protected boolean codigoEscaneado;
    protected Cliente cliente;
    private ArrayList<Movimiento> historial;

    public Envio(Cliente cliente) {
        this.paquetes = new ArrayList<>();
        this.diasTranscurridos = 0; 
        this.codigoEscaneado = false; 
        this.cliente = cliente;
        this.historial = new ArrayList<>();
    }
    private boolean hayLugar(){
        return paquetes.size()<3;
    }
    private boolean todaviaNoSalio() {
        return historial.isEmpty() || getSucursalActual() !=null;

    }
    public void agregarPaquete(Paquete paq) {
    if (!hayLugar()) {
        throw new IllegalStateException("Excepción: No se pueden enviar más de 3 paquetes."); 
    }
    if (!todaviaNoSalio()) {
        throw new IllegalStateException("El envío todavia no salió y/o no se recibió "); 
    }
    paquetes.add(paq);
    paq.iniciarPreparacion();
    historial.add(new Movimiento(Estado.EN_PREPARACION, null));
    }
    public void pasarDias(int dias) {
        this.diasTranscurridos += dias;
    }

    public void escanearCodigoEntrega() {
        this.codigoEscaneado = true;
    }

    public void iniciarRecorrido() {
        for (Paquete paq : paquetes) {
            paq.salirAReparto(); 
        }
        historial.add(new Movimiento(Estado.EN_REPARTO, null));
    }

    public void entregarPaquete() {
    if (paquetes.isEmpty()) {
        throw new IllegalStateException("Excepción: No se puede registrar un envío sin paquetes."); 
    }
    if (!validarTiempoEscaneo() || !codigoEscaneado) {
        throw new IllegalStateException("Excepción: Paquete en viaje, código no escaneado o fuera de tiempo."); 
    }
    for (Paquete paq : paquetes) {
        paq.registrarEntrega();
    }
    historial.add(new Movimiento(Estado.ENTREGADO, null));
    System.out.println("Envío entregado con éxito");
    }

    // Registrar la llegada guardando la Sucursal y el estado RECIBIDO
    public void registrarLlegadaASucursal(Sucursal sucursal) {
        historial.add(new Movimiento(Estado.RECIBIDO, sucursal));
    }

    // Al salir de la sucursal, el envío vuelve a estar en tránsito/reparto hacia su próximo destino
    public void registrarSalidaDeSucursal(Sucursal sucursal) {
        historial.add(new Movimiento(Estado.EN_REPARTO, sucursal));
    }

    
     // Retorna la sucursal en la que se encuentra el envío .
    
    public Sucursal getSucursalActual() {
    if (historial.isEmpty()) {
        return null;
    }

    // Buscar último hito desde el final del historial
    for (int i = historial.size() - 1; i >= 0; i--) {
        Movimiento mov = historial.get(i);
        
        if (mov.getEstado() == Estado.ENTREGADO || mov.getEstado() == Estado.EN_REPARTO) {
            return null;
        }
        
        // Si el hito más reciente es que fue recibido, ahi está .
        if (mov.getEstado() == Estado.RECIBIDO) {
            return mov.getSucursal(); 
        }
    }
    return null;
    }

    
     // Retorna verdadero si el envío está actualmente en la sucursal provista.
     
    public boolean estaEnSucursal(Sucursal sucursal) {
        Sucursal actual = this.getSucursalActual();
        if (actual == null || sucursal == null) {
            return false;
        }
        // Retorna la comparación de igualdad entre ambos objetos 
        return actual.equals(sucursal);
    }
    public ArrayList<Movimiento> getHistorial() {
        return this.historial;
    }
    public abstract double calcularCostoEnvio();
    public abstract boolean validarTiempoEscaneo();
}
