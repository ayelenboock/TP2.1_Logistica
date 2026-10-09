public class Main{
    public static void main(String[] args){
        // =========================================================================
        // CASO 1: Cliente con 3 envios
        // =========================================================================
        // Crear un cliente
        Cliente cliente1= new Cliente("Juan","Mora","34567843");
        // Envío 1: Estándar
        Envio envio1 = new EnvioEstandar(cliente1);
        envio1.agregarPaquete(new Paquete(101, 1.5, "Bariloche"));
        envio1.registrarLlegadaASucursal("Buenos Aires", "1035");
        envio1.iniciarRecorrido();

        // Envío 2: Express 
        Envio envio2 = new EnvioExpress(cliente1); 
        envio2.agregarPaquete(new Paquete(102, 4.0, "Viedma"));
        envio2.registrarLlegadaASucursal("Viedma", "8500");

        // Envío 3
        Envio envio3 = new EnvioInternacional(cliente1);
        envio3.agregarPaquete(new Paquete(103, 0.8, "Miami"));
        
        

        // =========================================================================
        // CASO 2: Cliente que intenta meter 4 paquetes en UN SOLO envío
        // =========================================================================
        System.out.println("\n\n=== SIMULACIÓN  DE PAQUETES (Límite de 3) ===");
        Cliente cliente2 = new Cliente("Ana", "Martínez", "29165777");
        Envio otroenvio = new EnvioEstandar(cliente2);

        System.out.println("Intentando agregar 4 paquetes al envío de Ana...");
        otroenvio.agregarPaquete(new Paquete(201, 1.0, "Viedma")); // Paquete 1 - OK
        otroenvio.agregarPaquete(new Paquete(202, 1.5, "Viedma")); // Paquete 2 - OK
        otroenvio.agregarPaquete(new Paquete(203, 2.0, "Viedma")); // Paquete 3 - OK
        
        // Deberia saltar el error
        System.out.println("Debe saltar el error");
        otroenvio.agregarPaquete(new Paquete(204, 3.5, "Buenos Aires")); // Paquete 4 - ERROR
      
    }
}


