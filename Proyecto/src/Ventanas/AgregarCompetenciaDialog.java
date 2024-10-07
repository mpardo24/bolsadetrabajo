package Ventanas;

import Clases.Competencia;
import Clases.Postulante;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AgregarCompetenciaDialog extends JDialog {
    private JTextField nombreCompetenciaField;
    private JComboBox<String> nivelComboBox;
    private JButton agregarButton;
    private JButton cancelarButton;
    private JButton eliminarButton;
    private JList<String> competenciaList;
    private DefaultListModel<String> listModel;
    private List<Competencia> competencias;

    public AgregarCompetenciaDialog(Postulante postulante) {
        setTitle("Modificar Competencias");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setModal(true);
        setLocationRelativeTo(null);

        this.competencias = postulante.getCompetencias();

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nombreLabel = new JLabel("Nombre de la Competencia:");
        nombreCompetenciaField = new JTextField();
        inputPanel.add(nombreLabel);
        inputPanel.add(nombreCompetenciaField);

        JLabel nivelLabel = new JLabel("Nivel:");
        nivelComboBox = new JComboBox<>(new String[]{"Principiante", "Intermedio", "Experto"});
        inputPanel.add(nivelLabel);
        inputPanel.add(nivelComboBox);

        add(inputPanel, BorderLayout.NORTH);


        listModel = new DefaultListModel<>();
        competenciaList = new JList<>(listModel);
        updateCompetenciaList();
        JScrollPane scrollPane = new JScrollPane(competenciaList);
        add(scrollPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        agregarButton = new JButton("Agregar");
        eliminarButton = new JButton("Eliminar");
        cancelarButton = new JButton("Cancelar");

        buttonPanel.add(agregarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(cancelarButton);

        add(buttonPanel, BorderLayout.SOUTH);


        agregarButton.addActionListener(e -> agregarCompetencia());


        eliminarButton.addActionListener(e -> eliminarCompetencia());


        cancelarButton.addActionListener(e -> dispose());
    }


    private void agregarCompetencia() {
        String nombre = nombreCompetenciaField.getText().trim();
        String nivel = (String) nivelComboBox.getSelectedItem();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la competencia no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Competencia nuevaCompetencia = new Competencia(nombre, nivel);
            competencias.add(nuevaCompetencia);
            updateCompetenciaList(); // Update the list display
            JOptionPane.showMessageDialog(this, "Competencia agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarCompetencia() {
        int selectedIndex = competenciaList.getSelectedIndex();
        if (selectedIndex != -1) {
            competencias.remove(selectedIndex);
            updateCompetenciaList(); // Update the list display
            JOptionPane.showMessageDialog(this, "Competencia eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una competencia para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCompetenciaList() {
        listModel.clear();
        for (Competencia competencia : competencias) {
            listModel.addElement(competencia.getNombre() + " (" + competencia.getNivel() + ")");
        }
    }
}
