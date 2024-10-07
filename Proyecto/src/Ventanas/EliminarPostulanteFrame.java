package Ventanas;

import Clases.BolsaTrabajo;
import Clases.Postulante;

import javax.swing.*;
import java.awt.*;

public class EliminarPostulanteFrame extends JInternalFrame {
    private JTextField rutField;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JButton cancelarButton;
    private JLabel infoLabel;
    private BolsaTrabajo bolsaTrabajo;
    private Postulante postulanteEncontrado;

    public EliminarPostulanteFrame(BolsaTrabajo bolsaTrabajo) {
        super("Eliminar Postulante", true, true, true, true);
        this.bolsaTrabajo = bolsaTrabajo;
        setSize(650, 300);
        setLayout(new BorderLayout());
        setLocation(100, 100);


        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JLabel rutLabel = new JLabel("Ingrese el RUT del Postulante:");
        rutField = new JTextField();
        inputPanel.add(rutLabel);
        inputPanel.add(rutField);

        infoLabel = new JLabel(" ");
        inputPanel.add(infoLabel);


        buscarButton = new JButton("Buscar");
        eliminarButton = new JButton("Eliminar");
        cancelarButton = new JButton("Cancelar");

        eliminarButton.setEnabled(false);

        inputPanel.add(buscarButton);
        inputPanel.add(eliminarButton);
        inputPanel.add(cancelarButton);

        add(inputPanel, BorderLayout.CENTER);


        buscarButton.addActionListener(e -> buscarPostulante());


        eliminarButton.addActionListener(e -> eliminarPostulante());


        cancelarButton.addActionListener(e -> dispose());
    }


    private void buscarPostulante() {
        String rut = rutField.getText().trim();


        if (rut.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo RUT no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        postulanteEncontrado = bolsaTrabajo.getPostulantePorRut(rut);

        if (postulanteEncontrado != null) {
            infoLabel.setText("Postulante encontrado: " + postulanteEncontrado.getNombre());
            eliminarButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un postulante con ese RUT.", "Error", JOptionPane.ERROR_MESSAGE);
            eliminarButton.setEnabled(false);
        }
    }


    private void eliminarPostulante() {
        if (postulanteEncontrado != null) {

            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar a " + postulanteEncontrado.getNombre() + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                bolsaTrabajo.eliminarPostulante(postulanteEncontrado);
                JOptionPane.showMessageDialog(this, "Postulante eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }
}
