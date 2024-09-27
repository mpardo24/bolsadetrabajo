import java.io.*;
import java.util.*;

public class BolsaTrabajo {
    private HashMap<String, Postulante> postulantes = new HashMap<>();
    private HashMap<Integer, Trabajo> trabajos = new HashMap<>();

    // Cargar los datos desde archivos CSV
    public void cargarDatos() {
        cargarPostulantes();
        cargarTrabajos();
    }
    
    // Guardar los datos en archivos CSV
    public void guardarDatos() {
        guardarPostulantes();
        guardarTrabajos();
    }

    // Cargar postulantes desde CSV
    private void cargarPostulantes() {
        File file = new File("postulantes.csv");
        if (!file.exists()) {
            System.out.println("Archivo postulantes.csv no encontrado, se creará al guardar los datos.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                String rut = datos[1];
                String correo = datos[2];
                int experiencia = Integer.parseInt(datos[3]);

                // Convertir competencias desde el CSV
                List<Competencia> competencias = new ArrayList<>();
                for (int i = 4; i < datos.length; i += 2) {
                    competencias.add(new Competencia(datos[i], datos[i + 1]));
                }

                Postulante postulante = new Postulante(nombre, rut, correo, competencias, experiencia);
                postulantes.put(rut, postulante);
            }
            System.out.println("Postulantes cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar postulantes: " + e.getMessage());
        }
    }

    // Cargar trabajos desde CSV
    private void cargarTrabajos() {
        File file = new File("trabajos.csv");
        if (!file.exists()) {
            System.out.println("Archivo trabajos.csv no encontrado, se creará al guardar los datos.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                String descripcion = datos[1];
                int experienciaMin = Integer.parseInt(datos[2]);
                int idTrabajo = Integer.parseInt(datos[3]);

                // Convertir competencias desde el CSV
                List<Competencia> competencias = new ArrayList<>();
                for (int i = 4; i < datos.length; i += 2) {
                    competencias.add(new Competencia(datos[i], datos[i + 1]));
                }

                Trabajo trabajo = new Trabajo(nombre, descripcion, competencias, experienciaMin, idTrabajo);
                trabajos.put(idTrabajo, trabajo);
            }
            System.out.println("Trabajos cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar trabajos: " + e.getMessage());
        }
    }

    // Guardar postulantes en CSV
    private void guardarPostulantes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("postulantes.csv"))) {
            for (Postulante postulante : postulantes.values()) {
                bw.write(postulante.getNombre() + "," + postulante.getRut() + "," + postulante.getCorreo() + "," + postulante.getExperiencia());
                for (Competencia competencia : postulante.getCompetencias()) {
                    bw.write("," + competencia.getNombre() + "," + competencia.getNivel());
                }
                bw.newLine();
            }
            System.out.println("Postulantes guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar postulantes: " + e.getMessage());
        }
    }

    // Guardar trabajos en CSV
    private void guardarTrabajos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("trabajos.csv"))) {
            for (Trabajo trabajo : trabajos.values()) {
                bw.write(trabajo.getNombre() + "," + trabajo.getDescripcion() + "," + trabajo.getExperiencia() + "," + trabajo.getIdTrabajo());
                for (Competencia competencia : trabajo.getCompetencias()) {
                    bw.write("," + competencia.getNombre() + "," + competencia.getNivel());
                }
                bw.newLine();
            }
            System.out.println("Trabajos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar trabajos: " + e.getMessage());
        }
    }
    
    // Agregar postulante
    public void agregarPostulante(Postulante postulante) {
        postulantes.put(postulante.getRut(), postulante);
    }

    // Agregar trabajo
    public void agregarTrabajo(Trabajo trabajo) {
        trabajos.put(trabajo.getIdTrabajo(), trabajo);
    }

    // Mostrar postulantes
    public void mostrarPostulantes() {
        for (Postulante postulante : postulantes.values()) {
            System.out.println("Nombre: " + postulante.getNombre());
            System.out.println("RUT: " + postulante.getRut());
            System.out.println("Correo: " + postulante.getCorreo());
            System.out.println("Años de experiencia: " + postulante.getExperiencia());
            System.out.println("Competencias:");
            for (Competencia competencia : postulante.getCompetencias()) {
                System.out.println(" - " + competencia.getNombre() + ": " + competencia.getNivel());
            }
            System.out.println("---------------------------");
        }
    }

    // Mostrar postulante indicando el rut
    public void mostrarPostulantePorRut(String rut) {
        Postulante postulante = getPostulantePorRut(rut);
        if (postulante != null) {
            System.out.println("Nombre: " + postulante.getNombre());
            System.out.println("RUT: " + postulante.getRut());
            System.out.println("Correo: " + postulante.getCorreo());
            System.out.println("Años de experiencia: " + postulante.getExperiencia());
            System.out.println("Competencias:");
            for (Competencia competencia : postulante.getCompetencias()) {
                System.out.println(" - " + competencia.getNombre() + ": " + competencia.getNivel());
            }
        } else {
            System.out.println("No se encontró un postulante con el RUT: " + rut);
        }
    }

    // Mostrar trabajos
    public void mostrarTrabajos() {
        System.out.println("Trabajos ingresados en la base da datos:");
        for (Trabajo trabajo : trabajos.values()) {
            System.out.println("  Nombre trabajo: "+trabajo.getNombre()+"\n    Id trabajo: "+trabajo.getIdTrabajo());
        }
    }

    // Mostrar trabajo indicando el ID
    public void mostrarTrabajoPorID(int IdTrabajo) {
        Trabajo trabajo = getTrabajoPorID(IdTrabajo);
        if (trabajo != null) {
            System.out.println("Nombre del Trabajo: " + trabajo.getNombre());
            System.out.println("Descripción: " + trabajo.getDescripcion());
            System.out.println("Años de experiencia mínima: " + trabajo.getExperiencia());
            System.out.println("ID del Trabajo: " + trabajo.getIdTrabajo());
            System.out.println("Competencias Requeridas:");
            for (Competencia competencia : trabajo.getCompetencias()) {
                System.out.println(" - " + competencia.getNombre() + ": " + competencia.getNivel());
            }
        } else {
            System.out.println("No se encontró un trabajo con el ID: " + IdTrabajo);
        }
    }

    // Eliminar postulante
    public void eliminarPostulante(Postulante postulante) {
        postulantes.remove(postulante.getRut());
    }

    // Eliminar trabajo por ID
    public void eliminarTrabajo(Trabajo trabajo) {
        trabajos.remove(trabajo.getIdTrabajo());  // Usar el ID del objeto trabajo
    }

    // Obtener postulante por RUT
    public Postulante getPostulantePorRut(String rut) {
        return postulantes.get(rut);
    }

    // Obtener trabajo por ID (ID como String, para coincidir con el HashMap)
    public Trabajo getTrabajoPorID(int IdTrabajo) {
        return trabajos.get(IdTrabajo);
    }

    // Método auxiliar para comparar los niveles de competencia
    private boolean nivelCompetenciaEsSuficiente(String nivelRequerido, String nivelPostulante) {
        int nivelRequeridoInt = convertirNivelAEntero(nivelRequerido);
        int nivelPostulanteInt = convertirNivelAEntero(nivelPostulante);

        // El nivel del postulante debe ser mayor o igual al nivel requerido
        return nivelPostulanteInt >= nivelRequeridoInt;
    }

    // Método auxiliar para convertir el nivel de competencia a un valor entero
    private int convertirNivelAEntero(String nivel) {
        switch (nivel.toLowerCase()) {
            case "principiante":
                return 1;
            case "intermedio":
                return 2;
            case "experto":
                return 3;
            default:
                return 0;
        }
    }

}
