package Ventanas;

import Clases.BolsaTrabajo;
import Clases.Postulante;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class EditarPostulanteFrame extends JInternalFrame {
    private BolsaTrabajo bolsaTrabajo;
    private JTextField rutField;
    private JTextArea postulanteDetailsArea;
    private JButton editarButton;

    public EditarPostulanteFrame(BolsaTrabajo bolsaTrabajo) {
        this.bolsaTrabajo = bolsaTrabajo;
        setTitle("Editar Postulante");
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel rutLabel = new JLabel("Buscar por RUT:");
        rutField = new JTextField(15);
        JButton buscarButton = new JButton("Buscar");

        searchPanel.add(rutLabel);
        searchPanel.add(rutField);
        searchPanel.add(buscarButton);
        add(searchPanel, BorderLayout.NORTH);


        postulanteDetailsArea = new JTextArea(8, 50);
        postulanteDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(postulanteDetailsArea);
        add(scrollPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        editarButton = new JButton("Editar");
        editarButton.setEnabled(false);
        JButton cerrarButton = new JButton("Cerrar");
        buttonPanel.add(editarButton);
        buttonPanel.add(cerrarButton);
        add(buttonPanel, BorderLayout.SOUTH);


        buscarButton.addActionListener(e -> buscarPostulantePorRUT());


        editarButton.addActionListener(e -> abrirEditarDetalles());


        cerrarButton.addActionListener(e -> dispose());
    }


    private void buscarPostulantePorRUT() {
        String rut = rutField.getText().trim();
        postulanteDetailsArea.setText("");

        if (rut.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un RUT para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        Postulante postulante = bolsaTrabajo.getPostulantePorRut(rut);

        if (postulante != null) {

            postulanteDetailsArea.setText(formatPostulante(postulante));
            editarButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "RUT no encontrado o incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            editarButton.setEnabled(false);
        }
    }


    private void abrirEditarDetalles() {
        String rut = rutField.getText().trim();
        Postulante postulante = bolsaTrabajo.getPostulantePorRut(rut);

        if (postulante != null) {
            EditarDetallesPostulanteFrame editarDetallesFrame = new EditarDetallesPostulanteFrame(bolsaTrabajo, postulante);
            getDesktopPane().add(editarDetallesFrame);
            editarDetallesFrame.setVisible(true);
        }
    }

    private String formatPostulante(Postulante postulante) {
        return String.format("Nombre: %s\nRUT: %s\nCorreo: %s\nExperiencia: %d años\nCompetencias: %s",
                postulante.getNombre(),
                postulante.getRut(),
                postulante.getCorreo(),
                postulante.getExperiencia(),
                postulante.getCompetencias().stream()
                        .map(c -> c.getNombre() + " (" + c.getNivel() + ")")
                        .collect(Collectors.joining(", "))
        );
    }
}
