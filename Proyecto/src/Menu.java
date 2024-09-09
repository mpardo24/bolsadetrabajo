import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private BolsaTrabajo bolsa;
    private Scanner scanner;

    public Menu() {
        bolsa = new BolsaTrabajo();
        scanner = new Scanner(System.in);
    }

    public void ejecutarMenu() {
        int opcion;

        do {
            System.out.println("\nMenú del Sistema de Bolsa de Trabajo");
            System.out.println("1. Agregar Postulante");
            System.out.println("2. Mostrar Postulantes");
            System.out.println("3. Agregar Trabajo");
            System.out.println("4. Mostrar Trabajos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    agregarPostulante();
                    break;

                case 2:
                    bolsa.mostrarPostulantes();
                    break;

                case 3:
                    agregarTrabajo();
                    break;

                case 4:
                    bolsa.mostrarTrabajos();
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private void agregarPostulante() {
        System.out.print("Ingrese el nombre del postulante: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el RUT del postulante: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese el correo del postulante: ");
        String correo = scanner.nextLine();
        // Ejemplo simple para añadir sin competencias ni experiencia
        bolsa.agregarPostulante(new Postulante(nombre, rut, correo, new ArrayList<>(), 0));
        System.out.println("Postulante agregado.");
    }

    private void agregarTrabajo() {
        System.out.print("Ingrese el nombre del trabajo: ");
        String nombreTrabajo = scanner.nextLine();
        System.out.print("Ingrese la descripción del trabajo: ");
        String descripcionTrabajo = scanner.nextLine();
        // Ejemplo simple para añadir sin competencias ni experiencia requerida
        bolsa.agregarTrabajo(new Trabajo(nombreTrabajo, descripcionTrabajo, new ArrayList<>(), 0));
        System.out.println("Trabajo agregado.");
    }
}
