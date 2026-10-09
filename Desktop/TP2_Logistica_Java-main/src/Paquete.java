public class Paquete {
    //atributos de la clase , privados para proteger su información.
    private int numGuia;
    private double peso;
    private String destino;
    private Estado estado;
   
   //Constructor
    public Paquete(int numGuia,double peso,String destino){
        if(peso<0){
            throw new ArithmeticException("El peso del Paquete no puede ser negativo");
        }
        this.numGuia=numGuia;
        this.peso=peso;
        this.destino=destino;
        this.estado=Estado.RECIBIDO;  // estado por defecto
    }
    //el paquete cumpla con la regla del don´t ask
   
    public void iniciarPreparacion() {
    if (!this.estado.equals(Estado.RECIBIDO)) {
        throw new IllegalStateException("Lo siento, No existe el paquete "); 
    }
    this.estado = Estado.EN_PREPARACION;
    }

    public void salirAReparto() {
         if (!this.estado.equals(Estado.EN_PREPARACION)) {
         throw new IllegalStateException("El paquete no está preparado para salir."); 
    }
     this.estado = Estado.EN_REPARTO;
    }

    public void registrarEntrega() {
        if (!this.estado.equals(Estado.EN_REPARTO)) {
         throw new IllegalStateException("No se puede entregar un paquete que no fue despachado."); 
    }
     this.estado = Estado.ENTREGADO;
   }
    public double getPeso() {
        return this.peso;
    }

    public String getDestino() {
        return this.destino;
    }

    public int getNumGuia() {
        return this.numGuia;
    }

    public Estado getEstado() {
        return this.estado;
    }
}
