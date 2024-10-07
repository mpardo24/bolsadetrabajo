package Ventanas;

import Clases.BolsaTrabajo;
import Clases.Competencia;
import Clases.Trabajo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarTrabajosFrame extends JInternalFrame {
    private BolsaTrabajo bolsaTrabajo;
    private JTextArea textArea;
    private JTextField searchField;
    private JButton searchButton;
    private JButton cancelButton;

    public MostrarTrabajosFrame(BolsaTrabajo bolsaTrabajo) {
        this.bolsaTrabajo = bolsaTrabajo;

        setTitle("Mostrar Trabajos");
        setSize(600, 400);
        setLayout(new BorderLayout());


        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);


        JPanel topPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(15);
        searchButton = new JButton("Buscar por ID");

        topPanel.add(new JLabel("ID Trabajo:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);


        JPanel bottomPanel = new JPanel(new FlowLayout());
        cancelButton = new JButton("Cancelar");
        bottomPanel.add(cancelButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


        cargarTodosLosTrabajos();


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = searchField.getText().trim();
                if (idStr.isEmpty()) {
                    JOptionPane.showMessageDialog(MostrarTrabajosFrame.this, "Por favor, ingrese un ID de trabajo para buscar.");
                } else {
                    try {
                        int id = Integer.parseInt(idStr);
                        mostrarTrabajoPorID(id);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MostrarTrabajosFrame.this, "El ID debe ser un número válido.");
                    }
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


    private void cargarTodosLosTrabajos() {
        textArea.setText("");
        for (Trabajo trabajo : bolsaTrabajo.getTrabajos()) {
            textArea.append(formatearTrabajo(trabajo) + "\n\n");
        }
    }


    private String formatearTrabajo(Trabajo trabajo) {
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


    private void mostrarTrabajoPorID(int id) {
        Trabajo trabajo = bolsaTrabajo.getTrabajoPorID(id);
        if (trabajo != null) {
            textArea.setText(formatearTrabajo(trabajo));
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un trabajo con el ID ingresado.");
            cargarTodosLosTrabajos();
        }
    }
}
