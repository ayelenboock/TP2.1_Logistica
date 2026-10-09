public class Sucursal {
    private String nombreCiudad;
    private String codigoPostal;
    
    public Sucursal (String nombreCiudad, String codigoPostal){
        this.nombreCiudad = nombreCiudad;
        this.codigoPostal = codigoPostal;
    }

    public String getNombre() {
        return this.nombreCiudad;
    }

    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    public void recibirEnvio(Envio envio){
        System.out.println("Sucursal de: " + nombreCiudad  + " Código Postal: " + codigoPostal + " recibió el envío.");
        envio.registrarLlegadaASucursal(this);
    }

    public void despacharEnvio(Envio envio) {
        System.out.println("Sucursal de: " + nombreCiudad + " Código Postal: " + codigoPostal + " despachó el envío.");
        envio.registrarSalidaDeSucursal(this);
    }
}
