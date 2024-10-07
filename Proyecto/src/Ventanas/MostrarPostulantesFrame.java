package Ventanas;

import Clases.BolsaTrabajo;
import Clases.Competencia;
import Clases.Postulante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarPostulantesFrame extends JInternalFrame {
    private BolsaTrabajo bolsaTrabajo;
    private JTextArea textArea;
    private JTextField searchField;
    private JButton searchButton;
    private JButton cancelButton;

    public MostrarPostulantesFrame(BolsaTrabajo bolsaTrabajo) {
        this.bolsaTrabajo = bolsaTrabajo;

        setTitle("Mostrar Postulantes");
        setSize(600, 400);
        setLayout(new BorderLayout());


        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);


        JPanel topPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(15);
        searchButton = new JButton("Buscar por RUT");

        topPanel.add(new JLabel("RUT:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);


        JPanel bottomPanel = new JPanel(new FlowLayout());
        cancelButton = new JButton("Cancelar");
        bottomPanel.add(cancelButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


        cargarTodosLosPostulantes();


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rut = searchField.getText().trim();
                if (rut.isEmpty()) {
                    JOptionPane.showMessageDialog(MostrarPostulantesFrame.this, "Por favor, ingrese un RUT para buscar.");
                } else {
                    mostrarPostulantePorRUT(rut);
                }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    private void cargarTodosLosPostulantes() {
        textArea.setText("");
        for (Postulante postulante : bolsaTrabajo.getPostulantes()) {
            textArea.append(formatearPostulante(postulante) + "\n\n");
        }
    }


    private String formatearPostulante(Postulante postulante) {
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


    private void mostrarPostulantePorRUT(String rut) {
        Postulante postulante = bolsaTrabajo.getPostulantePorRut(rut);
        if (postulante != null) {
            textArea.setText(formatearPostulante(postulante));
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un postulante con el RUT ingresado.");
            cargarTodosLosPostulantes();
        }
    }
}
