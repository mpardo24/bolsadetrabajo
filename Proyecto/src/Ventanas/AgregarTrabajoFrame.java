package Ventanas;

import Clases.BolsaTrabajo;
import Clases.Competencia;
import Clases.Trabajo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AgregarTrabajoFrame extends JInternalFrame {
    private BolsaTrabajo bolsaTrabajo;
    private JTextField nombreField, descripcionField, experienciaField, idTrabajoField;
    private DefaultListModel<String> competenciasListModel;
    private JList<String> competenciasList;
    private List<Competencia> competencias;

    public AgregarTrabajoFrame(BolsaTrabajo bolsaTrabajo) {
        this.bolsaTrabajo = bolsaTrabajo;
        competencias = new ArrayList<>();
        setTitle("Agregar Trabajo");
        setSize(600, 450);
        setLayout(new GridLayout(7, 2));


        add(new JLabel("Nombre del Trabajo:"));
        nombreField = new JTextField();
        add(nombreField);


        add(new JLabel("Descripción:"));
        descripcionField = new JTextField();
        add(descripcionField);


        add(new JLabel("ID del Trabajo:"));
        idTrabajoField = new JTextField();
        add(idTrabajoField);


        add(new JLabel("Años de Experiencia Mínima:"));
        experienciaField = new JTextField();
        add(experienciaField);


        add(new JLabel("Competencias:"));
        competenciasListModel = new DefaultListModel<>();
        competenciasList = new JList<>(competenciasListModel);
        add(new JScrollPane(competenciasList));


        JPanel buttonPanel = new JPanel();
        JButton agregarCompetenciaButton = new JButton("Agregar Competencia");
        JButton eliminarCompetenciaButton = new JButton("Eliminar Competencia");
        buttonPanel.add(agregarCompetenciaButton);
        buttonPanel.add(eliminarCompetenciaButton);
        add(buttonPanel);

        JButton agregarButton = new JButton("Agregar");
        JButton cancelarButton = new JButton("Cancelar");
        add(agregarButton);
        add(cancelarButton);


        agregarButton.addActionListener(e -> agregarTrabajo());
        cancelarButton.addActionListener(e -> dispose());


        agregarCompetenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirAgregarCompetenciaDialog();
            }
        });


        eliminarCompetenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCompetenciaSeleccionada();
            }
        });
    }


    private void abrirAgregarCompetenciaDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Agregar Competencia");
        dialog.setSize(400, 250);
        dialog.setLayout(new GridLayout(3, 2));

        JLabel nombreLabel = new JLabel("Nombre Competencia:");
        JTextField nombreCompetenciaField = new JTextField();
        dialog.add(nombreLabel);
        dialog.add(nombreCompetenciaField);

        JLabel nivelLabel = new JLabel("Nivel Competencia:");
        JComboBox<String> nivelComboBox = new JComboBox<>(new String[]{"Principiante", "Intermedio", "Experto"});
        dialog.add(nivelLabel);
        dialog.add(nivelComboBox);

        JButton agregarButton = new JButton("Agregar");
        JButton cancelarButton = new JButton("Cancelar");
        dialog.add(agregarButton);
        dialog.add(cancelarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreCompetenciaField.getText().trim();
                String nivel = (String) nivelComboBox.getSelectedItem();

                if (!nombre.isEmpty()) {
                    Competencia competencia = new Competencia(nombre, nivel);
                    competencias.add(competencia);
                    competenciasListModel.addElement(nombre + " (" + nivel + ")");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "El nombre de la competencia no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelarButton.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }


    private void eliminarCompetenciaSeleccionada() {
        int selectedIndex = competenciasList.getSelectedIndex();
        if (selectedIndex != -1) {
            competencias.remove(selectedIndex);
            competenciasListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una competencia para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void agregarTrabajo() {
        String nombre = nombreField.getText().trim();
        String descripcion = descripcionField.getText().trim();
        String idTrabajoText = idTrabajoField.getText().trim();
        int idTrabajo;
        int experiencia;


        if (nombre.isEmpty() || descripcion.isEmpty() || idTrabajoText.isEmpty() || experienciaField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        try {
            idTrabajo = Integer.parseInt(idTrabajoText);
            if (bolsaTrabajo.getTrabajoPorID(idTrabajo) != null) {
                JOptionPane.showMessageDialog(this, "El ID de trabajo ingresado ya está registrado. No se puede agregar el trabajo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID de trabajo debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            experiencia = Integer.parseInt(experienciaField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Años de experiencia mínima debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        if (competencias.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe agregar al menos una competencia.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        Trabajo nuevoTrabajo = new Trabajo(nombre, descripcion, competencias, experiencia, idTrabajo);
        bolsaTrabajo.agregarTrabajo(nuevoTrabajo);

        JOptionPane.showMessageDialog(this, "Trabajo agregado exitosamente.");
        dispose();
    }
}
