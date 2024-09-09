import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Menu {
    private BolsaTrabajo bolsa;
    private Scanner scanner;

    public Menu(BolsaTrabajo bolsa) {
        this.bolsa = bolsa;
        scanner = new Scanner(System.in);
    }

    public void ejecutarMenu() {
        String input;
        int opcion = -1;

        do {
            System.out.println("\nMenú del Sistema de Bolsa de Trabajo");
            System.out.println("1. Agregar Postulante");
            System.out.println("2. Mostrar Postulantes");
            System.out.println("3. Eliminar Postulante");
            System.out.println("4. Agregar Trabajo");
            System.out.println("5. Mostrar Trabajos");
            System.out.println("6. Eliminar Trabajo");
            System.out.println("7. Mostrar Postulante por RUT");
            System.out.println("8. Mostrar Trabajo por ID");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            input = scanner.nextLine();

            if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7") || input.equals("8") || input.equals("9")) {

                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        agregarPostulante();
                        break;
                    case 2:
                        bolsa.mostrarPostulantes();
                        break;
                    case 3:
                        eliminarPostulante();
                        break;
                    case 4:
                        agregarTrabajo();
                        break;
                    case 5:
                        bolsa.mostrarTrabajos();
                        break;
                    case 6:
                        eliminarTrabajo();
                        break;
                    case 7:
                        mostrarPostulantePorRUT();
                        break;
                    case 8:
                        mostrarTrabajoPorID();
                        break;
                    case 9:
                        System.out.println("Saliendo del sistema...");
                        break;
                }
            } else {
                System.out.println("Ingrese un valor numérico entre el 1 al 9.");
            }
        } while (opcion != 9);
    }

    private void agregarPostulante() {
        System.out.print("Ingrese el nombre del postulante: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el RUT del postulante (Con puntos y guión, junto a su digito verificador): ");
        String rut = scanner.nextLine();

        if (bolsa.getPostulantePorRut(rut) != null) {
            System.out.println("Persona con ese RUT ya está registrada.");
            return;
        }
        System.out.print("Ingrese el correo del postulante: ");
        String correo = scanner.nextLine();
        System.out.println("Ingrese los años de experiencia: ");
        int anioExperiencia;
        anioExperiencia = scanner.nextInt();

        // Crear una lista para competencias
        List<Competencia> listaCompetencias = new ArrayList<>();

        System.out.println("Ingrese la cantidad de competencias a agregar:");
        int cantCompetencias = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantCompetencias; i++) {
            System.out.print("Ingrese el nombre de la competencia: ");
            String nombreCompetencia = scanner.nextLine();
            System.out.print("Ingrese el nivel de la competencia: ");
            String nivelCompetencia = scanner.nextLine();
            listaCompetencias.add(new Competencia(nombreCompetencia, nivelCompetencia));
        }

        // Agregar el postulante con las competencias
        bolsa.agregarPostulante(new Postulante(nombre, rut, correo, listaCompetencias, anioExperiencia));
        System.out.println("Postulante agregado.");
    }

    private void agregarTrabajo() {
        System.out.print("Ingrese el nombre del trabajo: ");
        String nombreTrabajo = scanner.nextLine();
        System.out.print("Ingrese la descripción del trabajo: ");
        String descripcionTrabajo = scanner.nextLine();
        System.out.println("Ingrese el ID del trabajo: ");
        int IdTrabajo = (scanner.nextInt());

        if (bolsa.getTrabajoPorID(IdTrabajo) != null) {
            System.out.println("Trabajo ingresado anteriormente.");
            return;
        }

        System.out.println("Ingrese los años de experiencia mínimo necesarios para postular: ");
        int anioExperienciaMinT = scanner.nextInt();

        // Crear una lista para competencias
        List<Competencia> listaCompetenciasT = new ArrayList<>();

        System.out.println("Ingrese la cantidad de competencias a agregar:");
        int cantCompetencias = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantCompetencias; i++) {
            System.out.print("Ingrese el nombre de la competencia: ");
            String nombreCompetencia = scanner.nextLine();
            System.out.print("Ingrese el nivel de la competencia: ");
            String nivelCompetencia = scanner.nextLine();
            listaCompetenciasT.add(new Competencia(nombreCompetencia, nivelCompetencia));
        }

        bolsa.agregarTrabajo(new Trabajo(nombreTrabajo, descripcionTrabajo, listaCompetenciasT, anioExperienciaMinT, IdTrabajo));
        System.out.println("Trabajo agregado.");
    }

    private void mostrarPostulantePorRUT() {
        System.out.print("Ingrese el RUT del postulante (Con puntos y guión, junto a su digito verificador): ");
        String rut = scanner.nextLine();
        bolsa.mostrarPostulantePorRut(rut);
    }

    private void mostrarTrabajoPorID() {
        System.out.print("Ingrese el ID del trabajo: ");
        int IdTrabajo = Integer.parseInt(scanner.nextLine());
        bolsa.mostrarTrabajoPorID(IdTrabajo);
    }

    private void eliminarPostulante() {
        System.out.print("Ingrese el RUT del postulante a eliminar (Con puntos y guión, junto a su digito verificador): ");
        String rutPostulante = scanner.nextLine();

        Postulante postulante = bolsa.getPostulantePorRut(rutPostulante);
        if (postulante != null) {
            bolsa.eliminarPostulante(postulante);
            System.out.println("Postulante eliminado.");
        } else {
            System.out.println("No se encontró un postulante con ese RUT.");
        }
    }

    private void eliminarTrabajo() {
        System.out.print("Ingrese el ID del trabajo a eliminar: ");
        int IdTrabajo = Integer.parseInt(scanner.nextLine());

        // Obtener el trabajo correspondiente por ID
        Trabajo trabajo = bolsa.getTrabajoPorID(IdTrabajo);
        if (trabajo != null) {
            // Si el trabajo existe, se elimina
            bolsa.eliminarTrabajo(trabajo); // Pasamos el trabajo a eliminar
            System.out.println("Trabajo eliminado.");
        } else {
            System.out.println("No se encontró un trabajo con ese ID.");
        }
    }

}
