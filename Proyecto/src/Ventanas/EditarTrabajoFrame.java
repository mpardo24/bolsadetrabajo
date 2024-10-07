package Ventanas;

import Clases.BolsaTrabajo;
import Clases.Trabajo;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class EditarTrabajoFrame extends JInternalFrame {
    private BolsaTrabajo bolsaTrabajo;
    private JTextField idField;
    private JTextArea trabajoDetailsArea;
    private JButton editarButton;

    public EditarTrabajoFrame(BolsaTrabajo bolsaTrabajo) {
        this.bolsaTrabajo = bolsaTrabajo;
        setTitle("Editar Trabajo");
        setSize(600, 400);
        setLayout(new BorderLayout());


        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel idLabel = new JLabel("Buscar por ID:");
        idField = new JTextField(15);
        JButton buscarButton = new JButton("Buscar");

        searchPanel.add(idLabel);
        searchPanel.add(idField);
        searchPanel.add(buscarButton);
        add(searchPanel, BorderLayout.NORTH);


        trabajoDetailsArea = new JTextArea(8, 50);
        trabajoDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(trabajoDetailsArea);
        add(scrollPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        editarButton = new JButton("Editar");
        editarButton.setEnabled(false);
        JButton cerrarButton = new JButton("Cerrar");
        buttonPanel.add(editarButton);
        buttonPanel.add(cerrarButton);
        add(buttonPanel, BorderLayout.SOUTH);


        buscarButton.addActionListener(e -> buscarTrabajoPorID());


        editarButton.addActionListener(e -> abrirEditarDetalles());


        cerrarButton.addActionListener(e -> dispose());
    }


    private void buscarTrabajoPorID() {
        String idText = idField.getText().trim();
        trabajoDetailsArea.setText("");

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        try {
            int id = Integer.parseInt(idText);
            Trabajo trabajo = bolsaTrabajo.getTrabajoPorID(id);

            if (trabajo != null) {

                trabajoDetailsArea.setText(formatTrabajo(trabajo));
                editarButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "ID no encontrado o incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
                editarButton.setEnabled(false);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void abrirEditarDetalles() {
        String idText = idField.getText().trim();

        try {
            int id = Integer.parseInt(idText);
            Trabajo trabajo = bolsaTrabajo.getTrabajoPorID(id);

            if (trabajo != null) {
                EditarDetallesTrabajoFrame editarDetallesFrame = new EditarDetallesTrabajoFrame(bolsaTrabajo, trabajo);
                getDesktopPane().add(editarDetallesFrame);
                editarDetallesFrame.setVisible(true);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private String formatTrabajo(Trabajo trabajo) {
        return String.format("Nombre: %s\nDescripción: %s\nExperiencia Mínima: %d años\nID: %d\nCompetencias: %s",
                trabajo.getNombre(),
                trabajo.getDescripcion(),
                trabajo.getExperiencia(),
                trabajo.getIdTrabajo(),
                trabajo.getCompetencias().stream()
                        .map(c -> c.getNombre() + " (" + c.getNivel() + ")")
                        .collect(Collectors.joining(", "))
        );
    }
}
