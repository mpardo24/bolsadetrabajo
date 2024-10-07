package Clases;

import java.util.List;
import java.util.Objects;

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

    // Getters y setters
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

    // Métodos para agregar competencias con sobrecarga
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

    // Sobreescribir toString()
    @Override
    public String toString() {
        return "Postulante: " + nombre + " | RUT: " + rut + " | Correo: " + correo +
                " | Años de experiencia: " + experiencia + " | Competencias: " + competencias;
    }

    // Sobreescribir equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Postulante that = (Postulante) obj;
        return rut.equals(that.rut); // Comparar por RUT
    }

    // Sobreescribir hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(rut);
    }

    // Método para editar una competencia
    public void editarCompetencia(int index, String nuevoNombre, String nuevoNivel) {
        if (index >= 0 && index < competencias.size()) {
            Competencia competencia = competencias.get(index);
            competencia.setNombre(nuevoNombre);
            competencia.setNivel(nuevoNivel);
        }
    }

    // Método para eliminar una competencia
    public void eliminarCompetencia(int index) {
        if (index >= 0 && index < competencias.size()) {
            competencias.remove(index);
        }
    }
}
