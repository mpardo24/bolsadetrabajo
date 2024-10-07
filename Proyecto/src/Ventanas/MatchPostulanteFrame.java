package Ventanas;

import Clases.BolsaTrabajo;
import Clases.Competencia;
import Clases.Postulante;
import Clases.Trabajo;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class MatchPostulanteFrame extends JInternalFrame {
    private BolsaTrabajo bolsaTrabajo;
    private JTextField rutField;
    private JTextArea resultArea;

    public MatchPostulanteFrame(BolsaTrabajo bolsaTrabajo) {
        this.bolsaTrabajo = bolsaTrabajo;

        setTitle("Match Postulante por RUT");
        setSize(600, 400);
        setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel rutLabel = new JLabel("RUT del Postulante:");
        rutField = new JTextField(15);
        JButton buscarButton = new JButton("Buscar");

        inputPanel.add(rutLabel);
        inputPanel.add(rutField);
        inputPanel.add(buscarButton);
        add(inputPanel, BorderLayout.NORTH);


        resultArea = new JTextArea(15, 50);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton cerrarButton = new JButton("Cerrar");
        buttonPanel.add(cerrarButton);
        add(buttonPanel, BorderLayout.SOUTH);


        buscarButton.addActionListener(e -> buscarPostulantePorRUT());


        cerrarButton.addActionListener(e -> dispose());
    }


    private void buscarPostulantePorRUT() {
        String rut = rutField.getText().trim();
        resultArea.setText("");


        if (rut.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el RUT del postulante.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        Postulante postulante = bolsaTrabajo.getPostulantePorRut(rut);

        if (postulante == null) {
            JOptionPane.showMessageDialog(this, "No se encontró un postulante con el RUT proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        List<Trabajo> matchingTrabajos = bolsaTrabajo.getTrabajos()
                .stream()
                .filter(trabajo -> cumpleRequisitos(postulante, trabajo))
                .sorted((t1, t2) -> Integer.compare(t1.getExperiencia(), t2.getExperiencia()))
                .collect(Collectors.toList());


        if (matchingTrabajos.isEmpty()) {
            resultArea.setText("No hay trabajos aptos para este postulante.");
        } else {
            resultArea.setText("Trabajos aptos para el postulante '" + postulante.getNombre() + "':\n\n");
            for (Trabajo trabajo : matchingTrabajos) {
                resultArea.append(formatTrabajo(trabajo) + "\n\n");
            }
        }
    }


    private boolean cumpleRequisitos(Postulante postulante, Trabajo trabajo) {

        if (postulante.getExperiencia() < trabajo.getExperiencia()) {
            return false;
        }


        for (Competencia competenciaRequerida : trabajo.getCompetencias()) {
            boolean competenciaCumplida = false;

            for (Competencia competenciaPostulante : postulante.getCompetencias()) {
                if (competenciaRequerida.getNombre().equalsIgnoreCase(competenciaPostulante.getNombre()) &&
                        nivelCompetenciaEsSuficiente(competenciaRequerida.getNivel(), competenciaPostulante.getNivel())) {
                    competenciaCumplida = true;
                    break;
                }
            }

            if (!competenciaCumplida) {
                return false;
            }
        }
        return true;
    }


    private boolean nivelCompetenciaEsSuficiente(String nivelRequerido, String nivelPostulante) {
        int nivelRequeridoInt = convertirNivelAEntero(nivelRequerido);
        int nivelPostulanteInt = convertirNivelAEntero(nivelPostulante);

        return nivelPostulanteInt >= nivelRequeridoInt;
    }


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


    private String formatTrabajo(Trabajo trabajo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(trabajo.getNombre()).append("\n");
        sb.append("Descripción: ").append(trabajo.getDescripcion()).append("\n");
        sb.append("Años de Experiencia Mínima: ").append(trabajo.getExperiencia()).append("\n");
        sb.append("ID del Trabajo: ").append(trabajo.getIdTrabajo()).append("\n");
        sb.append("Competencias Requeridas:\n");
        for (Competencia competencia : trabajo.getCompetencias()) {
            sb.append(" - ").append(competencia.getNombre()).append(" (").append(competencia.getNivel()).append(")\n");
        }
        return sb.toString();
    }
}
