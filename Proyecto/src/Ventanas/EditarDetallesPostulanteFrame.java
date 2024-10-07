package Ventanas;

import Clases.BolsaTrabajo;
import Clases.Competencia;
import Clases.Postulante;

import javax.swing.*;
import java.awt.*;

public class EditarDetallesPostulanteFrame extends JInternalFrame {
    private BolsaTrabajo bolsaTrabajo;
    private Postulante postulante;
    private JTextField nombreField;
    private JTextField correoField;
    private JTextField experienciaField;
    private DefaultListModel<String> competenciasListModel;
    private JList<String> competenciasList;

    public EditarDetallesPostulanteFrame(BolsaTrabajo bolsaTrabajo, Postulante postulante) {
        this.bolsaTrabajo = bolsaTrabajo;
        this.postulante = postulante;
        setTitle("Editar Detalles del Postulante");
        setSize(600, 450);
        setLayout(new BorderLayout());


        JPanel detailsPanel = new JPanel(new GridLayout(5, 2));
        detailsPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField(postulante.getNombre());
        detailsPanel.add(nombreField);

        detailsPanel.add(new JLabel("Correo:"));
        correoField = new JTextField(postulante.getCorreo());
        detailsPanel.add(correoField);

        detailsPanel.add(new JLabel("Años de Experiencia:"));
        experienciaField = new JTextField(String.valueOf(postulante.getExperiencia()));
        detailsPanel.add(experienciaField);


        competenciasListModel = new DefaultListModel<>();
        for (Competencia comp : postulante.getCompetencias()) {
            competenciasListModel.addElement(comp.getNombre() + " (" + comp.getNivel() + ")");
        }
        competenciasList = new JList<>(competenciasListModel);

        JScrollPane scrollPane = new JScrollPane(competenciasList);
        JPanel competenciasPanel = new JPanel(new BorderLayout());
        competenciasPanel.add(new JLabel("Competencias:"), BorderLayout.NORTH);
        competenciasPanel.add(scrollPane, BorderLayout.CENTER);

        JButton agregarCompetenciaButton = new JButton("Modificar Competencia(s)");
        JButton eliminarCompetenciaButton = new JButton("Eliminar Competencia");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(agregarCompetenciaButton);
        buttonPanel.add(eliminarCompetenciaButton);
        competenciasPanel.add(buttonPanel, BorderLayout.SOUTH);

        detailsPanel.add(competenciasPanel);

        add(detailsPanel, BorderLayout.CENTER);


        JPanel actionPanel = new JPanel();
        JButton guardarButton = new JButton("Guardar Cambios");
        JButton cancelarButton = new JButton("Cancelar");
        actionPanel.add(guardarButton);
        actionPanel.add(cancelarButton);
        add(actionPanel, BorderLayout.SOUTH);


        agregarCompetenciaButton.addActionListener(e -> agregarCompetencia());
        eliminarCompetenciaButton.addActionListener(e -> eliminarCompetencia());
        guardarButton.addActionListener(e -> guardarCambios());
        cancelarButton.addActionListener(e -> dispose());
    }


    private void agregarCompetencia() {
        AgregarCompetenciaDialog agregarCompetenciaDialog = new AgregarCompetenciaDialog(postulante);
        agregarCompetenciaDialog.setVisible(true);
        actualizarListaCompetencias();
    }


    private void eliminarCompetencia() {
        int selectedIndex = competenciasList.getSelectedIndex();
        if (selectedIndex >= 0) {
            postulante.getCompetencias().remove(selectedIndex);
            actualizarListaCompetencias();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una competencia para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void guardarCambios() {
        postulante.setNombre(nombreField.getText().trim());
        postulante.setCorreo(correoField.getText().trim());
        try {
            postulante.setExperiencia(Integer.parseInt(experienciaField.getText().trim()));
            bolsaTrabajo.guardarDatos();
            JOptionPane.showMessageDialog(this, "Cambios guardados correctamente.");
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Años de experiencia debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarListaCompetencias() {
        competenciasListModel.clear();
        for (Competencia comp : postulante.getCompetencias()) {
            competenciasListModel.addElement(comp.getNombre() + " (" + comp.getNivel() + ")");
        }
    }
}
