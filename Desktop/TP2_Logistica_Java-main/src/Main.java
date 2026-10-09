public class Main {
    public static void main(String[] args) {
        // =========================================================================
        // INSTANCIAS DE SUCURSALES 
        // =========================================================================
        Sucursal sucursalBsAs = new Sucursal("Buenos Aires", "1035");
        Sucursal sucursalViedma = new Sucursal("Viedma", "8500");
        Sucursal sucursalBariloche = new Sucursal("Bariloche", "8400");

        // =========================================================================
        // CASO 1: Cliente con 3 envíos
        // =========================================================================
        System.out.println("=== CASO 1: SIMULACIÓN DE ENVÍOS ===");
        Cliente cliente1 = new Cliente("Juan", "Mora", "34567843");
        
        // Envío 1: Estándar
        Envio envio1 = new EnvioEstandar(cliente1);
        envio1.agregarPaquete(new Paquete(101, 1.5, "Bariloche"));
        
        //  pasamos el objeto sucursal 
        envio1.registrarLlegadaASucursal(sucursalBsAs);
        
        // Probamos las consultas  mientras está en la sucursal
        System.out.println("Envío 1 - ¿Está en BsAs?: " + envio1.estaEnSucursal(sucursalBsAs)); // Devuelve true
        System.out.println("Envío 1 - Sucursal Actual: " + envio1.getSucursalActual().getNombre()); // Devuelve "Buenos Aires"
        
        envio1.iniciarRecorrido(); // Pasa a EN_REPARTO (Sale de la sucursal)
        System.out.println("Envío 1 - ¿Sigue en BsAs luego de iniciar recorrido?: " + envio1.estaEnSucursal(sucursalBsAs)); 

        // Envío 2: Express 
        Envio envio2 = new EnvioExpress(cliente1); 
        envio2.agregarPaquete(new Paquete(102, 4.0, "Viedma"));
        
        envio2.registrarLlegadaASucursal(sucursalViedma);
        System.out.println("Envío 2 - Sucursal Actual: " + envio2.getSucursalActual().getNombre()); // Deberia devolver "Viedma"

        // Envío 3: Internacional
        Envio envio3 = new EnvioInternacional(cliente1);
        envio3.agregarPaquete(new Paquete(103, 0.8, "Miami"));
        
        // =========================================================================
        // CASO 2: Cliente que intenta meter 4 paquetes en UN SOLO envío
        // =========================================================================
        System.out.println("\n=== CASO 2: LÍMITE DE PAQUETES (Máximo 3) ===");
        Cliente cliente2 = new Cliente("Ana", "Martínez", "29165777");
        Envio otroenvio = new EnvioEstandar(cliente2);

        System.out.println("Intentando agregar 4 paquetes al envío de Ana...");
        otroenvio.agregarPaquete(new Paquete(201, 1.0, "Viedma")); 
        otroenvio.agregarPaquete(new Paquete(202, 1.5, "Viedma")); 
        otroenvio.agregarPaquete(new Paquete(203, 2.0, "Viedma")); 
        
        // Debería saltar el aviso en consola
        otroenvio.agregarPaquete(new Paquete(204, 3.5, "Buenos Aires")); // Paquete 4 - Muestra mensaje de límite

        // =========================================================================
        // CASO 3: MOSTRAR LOS HISTORIALES DE MOVIMIENTOS (¡LO QUE FALTA AGREGAR!)
        // =========================================================================
        System.out.println("\n=== MOSTRANDO HISTORIAL COMPLETO DEL ENVÍO 1 (JUAN) ===");
        // Recorremos la lista de movimientos pura que nos da el método de Envio
        for (Movimiento mov : envio1.getHistorial()) {
            System.out.println(mov); // Llama automáticamente al toString() que armaste
        }

        System.out.println("\n=== MOSTRANDO HISTORIAL COMPLETO DEL ENVÍO DE ANA ===");
        for (Movimiento mov : otroenvio.getHistorial()) {
            System.out.println(mov);
        }
    }
}
