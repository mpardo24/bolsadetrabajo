import java.util.List;

public class Trabajo {
    private String nombre;
    private String descripcion;
    private List<Competencia> competencias;
    private int experiencia;
    private int IdTrabajo;

    // Constructor
    public Trabajo(String nombre, String descripcion, List<Competencia> competencias, int experiencia, int IdTrabajo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.competencias = competencias;
        this.experiencia = experiencia;
        this.IdTrabajo = IdTrabajo;
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

    public void setIdTrabajo(int IdTrabajo) {
        this.IdTrabajo = IdTrabajo; }
    public int getIdTrabajo() {
        return IdTrabajo; }


    // metodos para agregar competencias:
    // (son tres métodos para que así se cumpla la sobrecarga de métodos dependiendo de qué necesitará elprograma)


    public void agregarCompetencia(String nombreCompetencia, String nivelCompetencia) {
        Competencia nuevaCompetencia = new Competencia(nombreCompetencia, nivelCompetencia);
        this.competencias.add(nuevaCompetencia);
    }
    public void agregarCompetencia(Competencia competencia) {
        this.competencias.add(competencia);
    }
    public void agregarCompetencia(String nombreCompetencia) {
        Competencia nuevaCompetencia = new Competencia(nombreCompetencia, "indefinido");
        this.competencias.add(nuevaCompetencia);
    }
}
