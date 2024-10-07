import Clases.BolsaTrabajo;
import Clases.Competencia;
import Clases.Postulante;
import Clases.Trabajo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            // Menú principal del sistema
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Agregar Postulante");
            System.out.println("2. Mostrar Postulantes");
            System.out.println("3. Mostrar Postulante por RUT");
            System.out.println("4. Editar Postulante");
            System.out.println("5. Eliminar Postulante");
            System.out.println("6. Agregar Trabajo");
            System.out.println("7. Mostrar Trabajos");
            System.out.println("8. Mostrar Trabajo por ID");
            System.out.println("9. Editar Trabajo");
            System.out.println("10. Eliminar Trabajo");
            System.out.println("11. Editar Competencias de Postulante");
            System.out.println("12. Eliminar Competencias de Postulante");
            System.out.println("13. Hacer Match con Trabajo por ID");
            System.out.println("14. Hacer Match con Postulante por RUT");
            System.out.println("15. Generar reporte final");
            System.out.println("16. Salir");
            System.out.print("Seleccione una opción (1-16): ");

            input = scanner.nextLine();

            // Validar entrada del usuario
            if (input.matches("[1-9]|1[0-6]")) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        agregarPostulante();
                        break;
                    case 2:
                        bolsa.mostrarPostulantes();
                        break;
                    case 3:
                        mostrarPostulantePorRUT();
                        break;
                    case 4:
                        editarPostulante();
                        break;
                    case 5:
                        eliminarPostulante();
                        break;
                    case 6:
                        agregarTrabajo();
                        break;
                    case 7:
                        bolsa.mostrarTrabajos();
                        break;
                    case 8:
                        mostrarTrabajoPorID();
                        break;
                    case 9:
                        editarTrabajo();
                        break;
                    case 10:
                        eliminarTrabajo();
                        break;
                    case 11:
                        editarCompetenciaPostulante();
                        break;
                    case 12:
                        eliminarCompetenciaPostulante();
                        break;
                    case 13:
                        hacerMatchConTrabajo();
                        break;
                    case 14:
                        hacerMatchConPostulante();
                        break;
                    case 15:
                        bolsa.generarReporte();
                        break;
                    case 16:
                        System.out.println("Saliendo del sistema...");
                        bolsa.guardarDatos(); // Guardar datos al salir
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } else {
                System.out.println("Ingrese un valor numérico entre el 1 y el 16.");
            }
        } while (opcion != 16);
    }

    // Método para agregar un nuevo postulante

    private void agregarPostulante() {
        try {
            System.out.print("Ingrese el nombre del postulante: ");
            String nombre = scanner.nextLine();

            // Validar RUT con un bucle hasta que sea válido
            String rut = "";
            boolean rutValido = false;
            while (!rutValido) {
                System.out.print("Ingrese el RUT del postulante (Solo números, puntos y guión): ");
                rut = scanner.nextLine().trim();

                // Verificar si el RUT solo contiene números, puntos y guiones
                if (rut.matches("[0-9.-]+")) {
                    // Verificar si el RUT ya está registrado
                    if (bolsa.getPostulantePorRut(rut) != null) {
                        System.out.println("Persona con ese RUT ya está registrada.");
                    } else {
                        rutValido = true;
                    }
                } else {
                    System.out.println("RUT inválido. Solo debe contener números, puntos y guiones.");
                }
            }

            System.out.print("Ingrese el correo del postulante: ");
            String correo = scanner.nextLine();

            // Validar entrada para los años de experiencia
            int anioExperiencia = -1; // Inicializar como no válido
            while (anioExperiencia < 0) {
                System.out.print("Ingrese los años de experiencia (número entero positivo): ");
                String input = scanner.nextLine().trim();

                // Verificar si la entrada es un número válido
                if (input.matches("\\d+")) {
                    anioExperiencia = Integer.parseInt(input);
                } else {
                    System.out.println("Entrada inválida. Debe ingresar un número entero positivo.");
                }
            }

            // Obtener competencias del postulante
            List<Competencia> listaCompetencias = obtenerCompetencias();

            bolsa.agregarPostulante(new Postulante(nombre, rut, correo, listaCompetencias, anioExperiencia));
            System.out.println("Postulante agregado correctamente.");

        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }





    // Método para agregar un nuevo trabajo
    private void agregarTrabajo() {
        try {
            System.out.print("Ingrese el nombre del trabajo: ");
            String nombreTrabajo = scanner.nextLine();
            System.out.print("Ingrese la descripción del trabajo: ");
            String descripcionTrabajo = scanner.nextLine();
            System.out.print("Ingrese el ID del trabajo: ");
            int IdTrabajo = Integer.parseInt(scanner.nextLine());

            // Verificar si el ID de trabajo ya está registrado
            if (bolsa.getTrabajoPorID(IdTrabajo) != null) {
                System.out.println("Trabajo ingresado anteriormente.");
                return;
            }

            System.out.print("Ingrese los años de experiencia mínima necesarios para postular: ");
            int anioExperienciaMinT = Integer.parseInt(scanner.nextLine());

            // Obtener competencias para el trabajo
            List<Competencia> listaCompetenciasT = obtenerCompetencias();

            bolsa.agregarTrabajo(new Trabajo(nombreTrabajo, descripcionTrabajo, listaCompetenciasT, anioExperienciaMinT, IdTrabajo));
            System.out.println("Trabajo agregado correctamente.");

        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    // Método para mostrar un postulante por RUT
    private void mostrarPostulantePorRUT() {
        System.out.print("Ingrese el RUT del postulante: ");
        String rut = scanner.nextLine();
        bolsa.mostrarPostulantePorRut(rut);
    }

    // Método para mostrar un trabajo por ID
    private void mostrarTrabajoPorID() {
        System.out.print("Ingrese el ID del trabajo: ");
        int IdTrabajo = Integer.parseInt(scanner.nextLine());
        bolsa.mostrarTrabajoPorID(IdTrabajo);
    }

    // Método para eliminar un postulante
    private void eliminarPostulante() {
        System.out.print("Ingrese el RUT del postulante a eliminar: ");
        String rut = scanner.nextLine();

        Postulante postulante = bolsa.getPostulantePorRut(rut);
        if (postulante != null) {
            bolsa.eliminarPostulante(postulante);
            System.out.println("Postulante eliminado correctamente.");
        } else {
            System.out.println("No se encontró un postulante con ese RUT.");
        }
    }

    // Método para eliminar un trabajo por ID
    private void eliminarTrabajo() {
        System.out.print("Ingrese el ID del trabajo a eliminar: ");
        int IdTrabajo = Integer.parseInt(scanner.nextLine());

        Trabajo trabajo = bolsa.getTrabajoPorID(IdTrabajo);
        if (trabajo != null) {
            bolsa.eliminarTrabajo(trabajo);
            System.out.println("Trabajo eliminado correctamente.");
        } else {
            System.out.println("No se encontró un trabajo con ese ID.");
        }
    }

    // Método para editar competencias de un postulante
    private void editarCompetenciaPostulante() {
        System.out.print("Ingrese el RUT del postulante para editar competencias: ");
        String rut = scanner.nextLine();
        Postulante postulante = bolsa.getPostulantePorRut(rut);

        if (postulante != null) {
            List<Competencia> competencias = postulante.getCompetencias();

            System.out.println("Competencias del postulante:");
            for (int i = 0; i < competencias.size(); i++) {
                Competencia competencia = competencias.get(i);
                System.out.println((i + 1) + ". " + competencia.getNombre() + " (Nivel: " + competencia.getNivel() + ")");
            }

            System.out.print("Seleccione el número de la competencia a editar: ");
            int indice = Integer.parseInt(scanner.nextLine()) - 1;

            if (indice >= 0 && indice < competencias.size()) {
                Competencia competenciaSeleccionada = competencias.get(indice);

                System.out.print("Ingrese el nuevo nombre de la competencia: ");
                String nuevoNombre = scanner.nextLine();
                String nuevoNivel = pedirNivelCompetencia();

                competenciaSeleccionada.setNombre(nuevoNombre);
                competenciaSeleccionada.setNivel(nuevoNivel);

                System.out.println("Competencia actualizada correctamente.");
            } else {
                System.out.println("Índice fuera de rango.");
            }
        } else {
            System.out.println("No se encontró un postulante con ese RUT.");
        }
    }

    // Método para eliminar competencias de un postulante
    // Método para eliminar competencias de un postulante (continuación)
    private void eliminarCompetenciaPostulante() {
        System.out.print("Ingrese el RUT del postulante para eliminar competencias: ");
        String rut = scanner.nextLine();
        Postulante postulante = bolsa.getPostulantePorRut(rut);

        if (postulante != null) {
            List<Competencia> competencias = postulante.getCompetencias();

            // Mostrar todas las competencias del postulante
            System.out.println("Competencias del postulante:");
            for (int i = 0; i < competencias.size(); i++) {
                Competencia competencia = competencias.get(i);
                System.out.println((i + 1) + ". " + competencia.getNombre() + " (Nivel: " + competencia.getNivel() + ")");
            }

            // Seleccionar la competencia a eliminar
            System.out.print("Seleccione el número de la competencia a eliminar: ");
            int indice = Integer.parseInt(scanner.nextLine()) - 1;

            if (indice >= 0 && indice < competencias.size()) {
                competencias.remove(indice);
                System.out.println("Competencia eliminada correctamente.");

                // Verificar si el postulante no tiene más competencias y eliminarlo si es necesario
                if (competencias.isEmpty()) {
                    bolsa.eliminarPostulante(postulante);
                    System.out.println("El postulante no tiene más competencias y ha sido eliminado.");
                }
            } else {
                System.out.println("Índice fuera de rango.");
            }
        } else {
            System.out.println("No se encontró un postulante con ese RUT.");
        }
    }

    // Método para hacer match con un trabajo por ID
    private void hacerMatchConTrabajo() {
        System.out.print("Ingrese el ID del trabajo para hacer el match: ");
        int IdTrabajo = Integer.parseInt(scanner.nextLine());
        bolsa.hacerMatch(IdTrabajo); // Asumiendo que este método existe en BolsaTrabajo
    }

    // Método para hacer match con un postulante por RUT
    private void hacerMatchConPostulante() {
        System.out.print("Ingrese el RUT del postulante para hacer el match: ");
        String rut = scanner.nextLine();
        bolsa.hacerMatchInverso(rut); // Asumiendo que este método existe en BolsaTrabajo
    }

    // Método para pedir el nivel de competencia
    private String pedirNivelCompetencia() {
        String nivelCompetencia = "";
        int opcion;

        do {
            System.out.println("Seleccione el nivel de la competencia:");
            System.out.println("1) Principiante");
            System.out.println("2) Intermedio");
            System.out.println("3) Experto");
            System.out.print("Ingrese una opción (1-3): ");
            String input = scanner.nextLine();

            if (input.matches("[1-3]")) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        nivelCompetencia = "Principiante";
                        break;
                    case 2:
                        nivelCompetencia = "Intermedio";
                        break;
                    case 3:
                        nivelCompetencia = "Experto";
                        break;
                }
            } else {
                System.out.println("Opción inválida. Por favor, ingrese un número del 1 al 3.");
                opcion = -1; // Repetir el ciclo
            }
        } while (opcion == -1);

        return nivelCompetencia;
    }

    // Método para editar datos de un postulante
    private void editarPostulante() {
        System.out.print("Ingrese el RUT del postulante a editar: ");
        String rut = scanner.nextLine();
        Postulante postulante = bolsa.getPostulantePorRut(rut);

        if (postulante != null) {
            System.out.print("Ingrese el nuevo nombre del postulante: ");
            String nuevoNombre = scanner.nextLine();
            System.out.print("Ingrese el nuevo correo del postulante: ");
            String nuevoCorreo = scanner.nextLine();
            System.out.print("Ingrese los años de experiencia: ");
            int nuevaExperiencia = Integer.parseInt(scanner.nextLine());

            List<Competencia> nuevasCompetencias = obtenerCompetencias();

            bolsa.editarPostulantePorRut(rut, nuevoNombre, nuevoCorreo, nuevaExperiencia, nuevasCompetencias);
            System.out.println("Postulante editado correctamente.");
        } else {
            System.out.println("No se encontró un postulante con ese RUT.");
        }
    }

    // Método para editar datos de un trabajo
    private void editarTrabajo() {
        System.out.print("Ingrese el ID del trabajo a editar: ");
        int idTrabajo = Integer.parseInt(scanner.nextLine());
        Trabajo trabajo = bolsa.getTrabajoPorID(idTrabajo);

        if (trabajo != null) {
            System.out.print("Ingrese el nuevo nombre del trabajo: ");
            String nuevoNombre = scanner.nextLine();
            System.out.print("Ingrese la nueva descripción del trabajo: ");
            String nuevaDescripcion = scanner.nextLine();
            System.out.print("Ingrese los años de experiencia mínima necesarios: ");
            int nuevaExperienciaMin = Integer.parseInt(scanner.nextLine());

            List<Competencia> nuevasCompetencias = obtenerCompetencias();

            bolsa.editarTrabajoPorId(idTrabajo, nuevoNombre, nuevaDescripcion, nuevaExperienciaMin, nuevasCompetencias);
            System.out.println("Trabajo editado correctamente.");
        } else {
            System.out.println("No se encontró un trabajo con ese ID.");
        }
    }

    // Método para obtener competencias del usuario
    private List<Competencia> obtenerCompetencias() {
        List<Competencia> competencias = new ArrayList<>();
        System.out.print("Ingrese la cantidad de competencias a agregar: ");
        int cantCompetencias = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < cantCompetencias; i++) {
            System.out.print("Ingrese el nombre de la competencia: ");
            String nombreCompetencia = scanner.nextLine();
            String nivelCompetencia = pedirNivelCompetencia();
            competencias.add(new Competencia(nombreCompetencia, nivelCompetencia));
        }
        return competencias;
    }
}
