import java.util.HashMap;

// primero inicializamos nuestros mapas para postulantes y trabajos dentro de la bolsa trabajo
public class BolsaTrabajo {
    private HashMap<String, Postulante> postulantes = new HashMap<>();
    private HashMap<String, Trabajo> trabajos = new HashMap<>();

    //agregamos el postulante entregado
    public void agregarPostulante(Postulante postulante) {
        postulantes.put(postulante.getRut(), postulante);
    }

    //aqui agregamos el trabajo que necesita ser agregado
    public void agregarTrabajo(Trabajo trabajo) {
        trabajos.put(trabajo.getNombre(), trabajo);
    }

    //en este metodo mostramos los postulantes
    public void mostrarPostulantes() {
        for (Postulante postulante : postulantes.values()) {
            System.out.println(postulante.getNombre());
        }
    }

    //finalmente en este metodo mostramos los trabajos
    public void mostrarTrabajos() {
        for (Trabajo trabajo : trabajos.values()) {
            System.out.println(trabajo.getNombre());
        }
    }
}
