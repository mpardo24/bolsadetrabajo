import java.util.List;

public class Trabajo {
    private String nombre;
    private String descripcion;
    private List<Competencia> competencias;
    private int experiencia;

    // Constructor
    public Trabajo(String nombre, String descripcion, List<Competencia> competencias, int experiencia) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.competencias = competencias;
        this.experiencia = experiencia;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
}
