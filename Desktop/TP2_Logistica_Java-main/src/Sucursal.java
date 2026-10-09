public class Sucursal {
    private String nombreCiudad;
    private String codigoPostal;
    
    public Sucursal (String nombreCiudad, String codigoPostal){
        this.nombreCiudad=nombreCiudad;
        this.codigoPostal=codigoPostal;

    }
    public void recibirEnvio (Envio envio){
        System.out.println("Sucursal de: " + nombreCiudad  + " Código Postal: "+codigoPostal+ " recibió el envío.");
        envio.registrarLlegadaASucursal(this.nombreCiudad,this.codigoPostal);
    }


    public void despacharEnvio(Envio envio) {
        System.out.println("Sucursal de: " + nombreCiudad + "Código Postal:" +codigoPostal+" despachó el envío.");
        envio.registrarSalidaDeSucursal(this.nombreCiudad,this.codigoPostal);
    }
}
