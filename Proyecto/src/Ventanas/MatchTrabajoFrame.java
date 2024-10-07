package Ventanas;

import Clases.BolsaTrabajo;
import Clases.Competencia;
import Clases.Postulante;
import Clases.Trabajo;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class MatchTrabajoFrame extends JInternalFrame {
    private BolsaTrabajo bolsaTrabajo;
    private JTextField idTrabajoField;
    private JTextArea resultArea;

    public MatchTrabajoFrame(BolsaTrabajo bolsaTrabajo) {
        this.bolsaTrabajo = bolsaTrabajo;

        setTitle("Match Trabajo por ID");
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel idLabel = new JLabel("ID del Trabajo:");
        idTrabajoField = new JTextField(15);
        JButton buscarButton = new JButton("Buscar");

        inputPanel.add(idLabel);
        inputPanel.add(idTrabajoField);
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


        buscarButton.addActionListener(e -> buscarTrabajoPorID());


        cerrarButton.addActionListener(e -> dispose());
    }


    private void buscarTrabajoPorID() {
        String idText = idTrabajoField.getText().trim();
        resultArea.setText("");


        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID del trabajo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        try {
            int idTrabajo = Integer.parseInt(idText);
            Trabajo trabajo = bolsaTrabajo.getTrabajoPorID(idTrabajo);

            if (trabajo == null) {
                JOptionPane.showMessageDialog(this, "No se encontró un trabajo con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            List<Postulante> matchingPostulantes = bolsaTrabajo.getPostulantes()
                    .stream()
                    .filter(postulante -> cumpleRequisitos(postulante, trabajo))
                    .sorted((p1, p2) -> Integer.compare(p2.getExperiencia(), p1.getExperiencia())) // Order by experience descending
                    .collect(Collectors.toList());


            if (matchingPostulantes.isEmpty()) {
                resultArea.setText("No hay postulantes aptos para este trabajo.");
            } else {
                resultArea.setText("Postulantes aptos para el trabajo '" + trabajo.getNombre() + "':\n\n");
                for (Postulante postulante : matchingPostulantes) {
                    resultArea.append(formatPostulante(postulante) + "\n\n");
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
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


    private String formatPostulante(Postulante postulante) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(postulante.getNombre()).append("\n");
        sb.append("RUT: ").append(postulante.getRut()).append("\n");
        sb.append("Correo: ").append(postulante.getCorreo()).append("\n");
        sb.append("Años de Experiencia: ").append(postulante.getExperiencia()).append("\n");
        sb.append("Competencias:\n");
        for (Competencia competencia : postulante.getCompetencias()) {
            sb.append(" - ").append(competencia.getNombre()).append(" (").append(competencia.getNivel()).append(")\n");
        }
        return sb.toString();
    }
}
