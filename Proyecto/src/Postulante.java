import java.util.List;


public class Postulante {
    private String nombre;
    private String rut;
    private String correo;
    private List<Competencia> competencias;
    private int experiencia;

    // Constructor
    public Postulante(String nombre, String rut, String correo, List<Competencia> competencias, int experiencia) {
        this.nombre = nombre;
        this.rut = rut;
        this.correo = correo;
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

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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


    // metodos para agregar competencias:
    // (son tres métodos para que así se cumpla la sobrecarga de métodos dependiendo de qué necesitará elprograma)
    public void agregarCompetencia(String nombreCompetencia, String nivelCompetencia) {
        Competencia nuevaCompetencia = new Competencia(nombreCompetencia, nivelCompetencia);
        this.competencias.add(nuevaCompetencia);
    }

    public void agregarCompetencia(Competencia competencia) {
        this.competencias.add(competencia);
    }

    public void agregarCompetencia(List<Competencia> nuevasCompetencias) {
        this.competencias.addAll(nuevasCompetencias);
    }
}
