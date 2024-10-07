package Ventanas;

import Clases.BolsaTrabajo;
import Clases.Competencia;
import Clases.Trabajo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditarDetallesTrabajoFrame extends JInternalFrame {
    private BolsaTrabajo bolsaTrabajo;
    private Trabajo trabajo;
    private JTextField nombreField, descripcionField, experienciaField;
    private DefaultListModel<String> competenciasListModel;
    private JList<String> competenciasList;
    private List<Competencia> competencias;

    public EditarDetallesTrabajoFrame(BolsaTrabajo bolsaTrabajo, Trabajo trabajo) {
        this.bolsaTrabajo = bolsaTrabajo;
        this.trabajo = trabajo;
        this.competencias = trabajo.getCompetencias();

        setTitle("Editar Detalles del Trabajo");
        setSize(500, 500);
        setLayout(new BorderLayout());


        JPanel detailsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        detailsPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField(trabajo.getNombre());
        detailsPanel.add(nombreField);

        detailsPanel.add(new JLabel("Descripción:"));
        descripcionField = new JTextField(trabajo.getDescripcion());
        detailsPanel.add(descripcionField);

        detailsPanel.add(new JLabel("Años de Experiencia Mínima:"));
        experienciaField = new JTextField(String.valueOf(trabajo.getExperiencia()));
        detailsPanel.add(experienciaField);

        add(detailsPanel, BorderLayout.CENTER);


        competenciasListModel = new DefaultListModel<>();
        for (Competencia c : competencias) {
            competenciasListModel.addElement(c.getNombre() + " (" + c.getNivel() + ")");
        }
        competenciasList = new JList<>(competenciasListModel);
        detailsPanel.add(new JLabel("Competencias:"));
        detailsPanel.add(new JScrollPane(competenciasList));


        JPanel compButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton agregarCompetenciaButton = new JButton("Agregar Competencia");
        JButton eliminarCompetenciaButton = new JButton("Eliminar Competencia");
        compButtonPanel.add(agregarCompetenciaButton);
        compButtonPanel.add(eliminarCompetenciaButton);
        detailsPanel.add(compButtonPanel);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton guardarButton = new JButton("Guardar Cambios");
        JButton cancelarButton = new JButton("Cancelar");
        buttonPanel.add(guardarButton);
        buttonPanel.add(cancelarButton);
        add(buttonPanel, BorderLayout.SOUTH);


        agregarCompetenciaButton.addActionListener(e -> abrirAgregarCompetenciaDialog());
        eliminarCompetenciaButton.addActionListener(e -> eliminarCompetenciaSeleccionada());
        guardarButton.addActionListener(e -> guardarCambios());
        cancelarButton.addActionListener(e -> dispose());
    }


    private void abrirAgregarCompetenciaDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Agregar Competencia");
        dialog.setSize(400, 250);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));

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


        agregarButton.addActionListener(e -> {
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


    private void guardarCambios() {
        String nuevoNombre = nombreField.getText().trim();
        String nuevaDescripcion = descripcionField.getText().trim();
        int nuevaExperiencia;

        if (nuevoNombre.isEmpty() || nuevaDescripcion.isEmpty() || experienciaField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            nuevaExperiencia = Integer.parseInt(experienciaField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La experiencia mínima debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        trabajo.setNombre(nuevoNombre);
        trabajo.setDescripcion(nuevaDescripcion);
        trabajo.setExperiencia(nuevaExperiencia);
        trabajo.setCompetencias(competencias);

        JOptionPane.showMessageDialog(this, "Cambios guardados correctamente.");
        dispose();
    }
}
