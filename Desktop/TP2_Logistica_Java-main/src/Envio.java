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

    public void agregarPaquete(Paquete paq) {
        if (paquetes.size() < 3) {
            paquetes.add(paq);
            paq.iniciarPreparacion(); 
            // Primer hito: En preparación
            historial.add(new Movimiento(Estado.EN_PREPARACION, null)); 
        } else {
            System.out.println("Disculpa pero no se pueden enviar mas de 3 paquetes");
        }
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
        if (validarTiempoEscaneo() && codigoEscaneado) {
            for (Paquete paq : paquetes) {
                paq.registrarEntrega();
            }
            historial.add(new Movimiento(Estado.ENTREGADO, null));
            System.out.println("Envio entregado con exito");
        } else {
            System.out.println("Paquete en viaje");
        }
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
        
        // Si el hito más reciente del envío es que está viajando o se entregó,
        // automáticamente ya NO está en ninguna sucursal física.
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
