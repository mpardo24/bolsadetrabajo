import java.util.HashMap;

public class BolsaTrabajo {
    private HashMap<String, Postulante> postulantes = new HashMap<>();
    private HashMap<Integer, Trabajo> trabajos = new HashMap<>();

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
        for (Trabajo trabajo : trabajos.values()) {
            System.out.println(trabajo.getNombre());
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
}
