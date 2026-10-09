public class Main {
    public static void main(String[] args) {
        //  Instancias de Sucursales y Cliente
        Sucursal sucursalBariloche = new Sucursal("Bariloche", "8400");
        Sucursal sucursalViedma = new Sucursal("Viedma", "8500");
        Cliente cliente = new Cliente("Juan", "Mora", "34567843");

        //  Creación del envío
        Envio envio = new EnvioEstandar(cliente);
        
        System.out.println("=== PREPARANDO ENVÍO ===");
        envio.agregarPaquete(new Paquete(101, 1.5, "Viedma"));

    
        System.out.println("Costo total estimado: $" + envio.calcularCostoEnvio());

        System.out.println("\n=== INICIANDO RUTA DE ENVÍO ===");
        
        // Ingresar a la sucursal de origen
        sucursalBariloche.recibirEnvio(envio);
         
        System.out.println("Sucursal Actual: " + envio.getSucursalActual().getNombre()); 

        // Despachar para viajar a la otra ciudad
        sucursalBariloche.despacharEnvio(envio);
        envio.iniciarRecorrido();

        // Llegar a la sucursal de destino
        sucursalViedma.recibirEnvio(envio);
        System.out.println("Sucursal Actual: " + envio.getSucursalActual().getNombre()); 

        // Salir a reparto final hacia el domicilio del cliente
        sucursalViedma.despacharEnvio(envio);

        // Escanear y entregar al cliente
        envio.escanearCodigoEntrega();
        envio.pasarDias(6); 
        envio.entregarPaquete();

        //
        System.out.println("\n=== HISTORIAL COMPLETO DEL ENVÍO ===");
        for (Movimiento mov : envio.getHistorial()) {
            System.out.println(mov); 
        }
        
        
        System.out.println("\n=== PROBANDO CAPTURA DE EXCEPCIÓN ===");
        try {
            System.out.println("Intentando agregar un paquete ...");
            envio.agregarPaquete(new Paquete(102, 2.0, "Buenos Aires")); 
        } catch (IllegalStateException e) {
            System.out.println("Excepción:  " + e.getMessage()); 
        }
    }
}