package Clases;

// en la clase competencia creamos nombre y nivel de tipo string
public class Competencia {
    private String nombre;
    private String nivel;

    //luego en el constructor añadimos las variables creadas
    public Competencia(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    // y a continuacion agregamos los setter y getter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}