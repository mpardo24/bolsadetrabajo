package Clases;

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

    // Mostrar postulante por RUT
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

    // Mostrar trabajo por ID
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
        trabajos.remove(trabajo.getIdTrabajo());
    }

    // Obtener postulante por RUT
    public Postulante getPostulantePorRut(String rut) {
        return postulantes.get(rut);
    }

    // Obtener trabajo por ID
    public Trabajo getTrabajoPorID(int IdTrabajo) {
        return trabajos.get(IdTrabajo);
    }


    public void hacerMatch(int IdTrabajo) {
        Trabajo trabajo = getTrabajoPorID(IdTrabajo);
        if (trabajo == null) {
            System.out.println("No se encontró un trabajo con el ID: " + IdTrabajo);
            return;
        }

        System.out.println("Buscando postulantes para el trabajo: " + trabajo.getNombre());

        for (Postulante postulante : postulantes.values()) {
            boolean cumpleRequisitos = true;

            // Verificar años de experiencia mínimos
            if (postulante.getExperiencia() < trabajo.getExperiencia()) {
                cumpleRequisitos = false;
            }

            // Verificar competencias
            for (Competencia competenciaRequerida : trabajo.getCompetencias()) {
                boolean competenciaCumplida = false;

                for (Competencia competenciaPostulante : postulante.getCompetencias()) {
                    if (competenciaRequerida.getNombre().equalsIgnoreCase(competenciaPostulante.getNombre())) {
                        // Verificar nivel de competencia
                        if (nivelCompetenciaEsSuficiente(competenciaRequerida.getNivel(), competenciaPostulante.getNivel())) {
                            competenciaCumplida = true;
                            break;
                        }
                    }
                }

                // Si alguna competencia no se cumple, el postulante no cumple con los requisitos
                if (!competenciaCumplida) {
                    cumpleRequisitos = false;
                    break;
                }
            }

            // Mostrar si el postulante cumple o no
            if (cumpleRequisitos) {
                System.out.println("El postulante " + postulante.getNombre() + " cumple con los requisitos para el trabajo. Para contactarlo mandar correo a "+ postulante.getCorreo());
            }
        }
    }

    public List<Trabajo> getTrabajos() {
        return new ArrayList<>(trabajos.values());
    }
    public List<Postulante> getPostulantes() {
        return new ArrayList<>(postulantes.values());
    }

    public void hacerMatchInverso(String rut) {
        Postulante postulante = getPostulantePorRut(rut);

        // Verificar si el postulante existe
        if (postulante == null) {
            System.out.println("No se encontró un postulante con el RUT: " + rut);
            return;
        }

        System.out.println("Buscando trabajos para el postulante: " + postulante.getNombre());
        boolean foundMatch = false;

        // Iterar sobre todos los trabajos disponibles
        for (Trabajo trabajo : trabajos.values()) {
            boolean cumpleRequisitos = true;

            // Verificar si el postulante cumple con la experiencia mínima
            if (postulante.getExperiencia() < trabajo.getExperiencia()) {
                cumpleRequisitos = false;
            }

            // Verificar si el postulante cumple con las competencias requeridas por el trabajo
            for (Competencia competenciaRequerida : trabajo.getCompetencias()) {
                boolean competenciaCumplida = false;

                for (Competencia competenciaPostulante : postulante.getCompetencias()) {
                    if (competenciaRequerida.getNombre().equalsIgnoreCase(competenciaPostulante.getNombre())) {
                        // Verificar nivel de competencia
                        if (nivelCompetenciaEsSuficiente(competenciaRequerida.getNivel(), competenciaPostulante.getNivel())) {
                            competenciaCumplida = true;
                            break;
                        }
                    }
                }

                // Si alguna competencia no se cumple, el postulante no cumple con los requisitos para este trabajo
                if (!competenciaCumplida) {
                    cumpleRequisitos = false;
                    break;
                }
            }

            // Si el postulante cumple con todos los requisitos del trabajo
            if (cumpleRequisitos) {
                foundMatch = true;
                System.out.println("El postulante " + postulante.getNombre() + " puede postular al trabajo: " + trabajo.getNombre());
                System.out.println("Descripción: " + trabajo.getDescripcion());
                System.out.println("Años de experiencia mínima requerida: " + trabajo.getExperiencia());
                System.out.println("ID del Trabajo: " + trabajo.getIdTrabajo());
                System.out.println("---------------------------");
            }
        }

        // Si no se encontró ningún trabajo para el que el postulante cumpla con los requisitos
        if (!foundMatch) {
            System.out.println("Postulante no cumple con requerimientos mínimos para ningún trabajo.");
        }
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
                return 0; // Nivel desconocido
        }
    }

    public void editarPostulantePorRut(String rut, String nuevoNombre, String nuevoCorreo, int nuevaExperiencia, List<Competencia> nuevasCompetencias) {
        Postulante postulante = postulantes.get(rut);
        if (postulante != null) {
            postulante.setNombre(nuevoNombre);
            postulante.setCorreo(nuevoCorreo);
            postulante.setExperiencia(nuevaExperiencia);
            postulante.setCompetencias(nuevasCompetencias);
            System.out.println("Postulante editado correctamente.");
        } else {
            System.out.println("No se encontró un postulante con el RUT: " + rut);
        }
    }

    public void editarTrabajoPorId(int idTrabajo, String nuevoNombre, String nuevaDescripcion, int nuevaExperienciaMin, List<Competencia> nuevasCompetencias) {
        Trabajo trabajo = trabajos.get(idTrabajo);
        if (trabajo != null) {
            trabajo.setNombre(nuevoNombre);
            trabajo.setDescripcion(nuevaDescripcion);
            trabajo.setExperiencia(nuevaExperienciaMin);
            trabajo.setCompetencias(nuevasCompetencias);
            System.out.println("Trabajo editado correctamente.");
        } else {
            System.out.println("No se encontró un trabajo con el ID: " + idTrabajo);
        }
    }

    public void generarReporte() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reporte.txt"))) {

            // Escribir los datos de los postulantes
            writer.write("=== Reporte de Postulantes ===\n");
            for (Postulante postulante : postulantes.values()) {
                writer.write("Nombre: " + postulante.getNombre() + "\n");
                writer.write("RUT: " + postulante.getRut() + "\n");
                writer.write("Correo: " + postulante.getCorreo() + "\n");
                writer.write("Años de Experiencia: " + postulante.getExperiencia() + "\n");
                writer.write("Competencias:\n");
                for (Competencia competencia : postulante.getCompetencias()) {
                    writer.write(" - " + competencia.getNombre() + " (" + competencia.getNivel() + ")\n");
                }
                writer.write("-------------------------------\n");
            }

            // Escribir los datos de los trabajos
            writer.write("\n=== Reporte de Trabajos ===\n");
            for (Trabajo trabajo : trabajos.values()) {
                writer.write("Nombre del Clases.Trabajo: " + trabajo.getNombre() + "\n");
                writer.write("Descripción: " + trabajo.getDescripcion() + "\n");
                writer.write("Años de Experiencia Requeridos: " + trabajo.getExperiencia() + "\n");
                writer.write("ID del Clases.Trabajo: " + trabajo.getIdTrabajo() + "\n");
                writer.write("Competencias Requeridas:\n");
                for (Competencia competencia : trabajo.getCompetencias()) {
                    writer.write(" - " + competencia.getNombre() + " (" + competencia.getNivel() + ")\n");
                }
                writer.write("-------------------------------\n");
            }

            System.out.println("Reporte generado correctamente en 'reporte.txt'.");

        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }

}