package Clases;

import java.util.List;
import java.util.Objects;

public class Trabajo {
    private String nombre;
    private String descripcion;
    private List<Competencia> competencias;
    private int experiencia;
    private int idTrabajo;

    // Constructor
    public Trabajo(String nombre, String descripcion, List<Competencia> competencias, int experiencia, int idTrabajo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.competencias = competencias;
        this.experiencia = experiencia;
        this.idTrabajo = idTrabajo;
    }

    // Getters y setters
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

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(int idTrabajo) {
        this.idTrabajo = idTrabajo;
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
        return "Trabajo: " + nombre + " | Descripción: " + descripcion + " | ID: " + idTrabajo +
                " | Experiencia requerida: " + experiencia + " años | Competencias: " + competencias;
    }

    // Sobreescribir equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Trabajo that = (Trabajo) obj;
        return idTrabajo == that.idTrabajo; // Comparar por ID de trabajo
    }

    // Sobreescribir hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(idTrabajo);
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
