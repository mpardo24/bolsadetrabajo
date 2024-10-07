package Ventanas;

import Clases.BolsaTrabajo;
import Clases.Trabajo;

import javax.swing.*;
import java.awt.*;

public class EliminarTrabajoFrame extends JInternalFrame {
    private BolsaTrabajo bolsaTrabajo;
    private JTextField idTrabajoField;

    public EliminarTrabajoFrame(BolsaTrabajo bolsaTrabajo) {
        this.bolsaTrabajo = bolsaTrabajo;

        setTitle("Eliminar Trabajo");
        setSize(400, 150);
        setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel idLabel = new JLabel("ID del Trabajo:");
        idTrabajoField = new JTextField(15);
        JButton eliminarButton = new JButton("Eliminar");
        JButton cancelarButton = new JButton("Cancelar");

        inputPanel.add(idLabel);
        inputPanel.add(idTrabajoField);
        add(inputPanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(eliminarButton);
        buttonPanel.add(cancelarButton);
        add(buttonPanel, BorderLayout.SOUTH);


        eliminarButton.addActionListener(e -> eliminarTrabajoPorID());


        cancelarButton.addActionListener(e -> dispose());
    }


    private void eliminarTrabajoPorID() {
        String idText = idTrabajoField.getText().trim();


        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID del trabajo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        try {
            int idTrabajo = Integer.parseInt(idText);
            Trabajo trabajo = bolsaTrabajo.getTrabajoPorID(idTrabajo);

            if (trabajo != null) {
                bolsaTrabajo.eliminarTrabajo(trabajo);
                JOptionPane.showMessageDialog(this, "Trabajo eliminado correctamente.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un trabajo con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
