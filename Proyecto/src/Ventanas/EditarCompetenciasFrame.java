package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarCompetenciasFrame extends JInternalFrame {
    private JComboBox<String> nivelComboBox;
    private JTextField competenciaField;
    private DefaultListModel<String> competenciasModel;

    public EditarCompetenciasFrame() {
        setTitle("Editar Competencias");
        setSize(400, 300);
        setClosable(true);
        setLayout(new FlowLayout());


        competenciasModel = new DefaultListModel<>();
        JList<String> competenciasList = new JList<>(competenciasModel);
        JScrollPane scrollPane = new JScrollPane(competenciasList);
        add(scrollPane);

        add(new JLabel("Clases.Competencia:"));
        competenciaField = new JTextField(15);
        add(competenciaField);

        add(new JLabel("Nivel:"));
        String[] niveles = {"Principiante", "Intermedio", "Experto"};
        nivelComboBox = new JComboBox<>(niveles);
        add(nivelComboBox);

        JButton agregarButton = new JButton("Agregar");
        JButton eliminarButton = new JButton("Eliminar");
        JButton volverButton = new JButton("Volver");

        add(agregarButton);
        add(eliminarButton);
        add(volverButton);


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String competencia = competenciaField.getText();
                String nivel = (String) nivelComboBox.getSelectedItem();
                if (!competencia.isEmpty()) {
                    competenciasModel.addElement(competencia + " - " + nivel);
                    JOptionPane.showMessageDialog(
                            EditarCompetenciasFrame.this,
                            "Clases.Competencia agregada correctamente.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    competenciaField.setText("");
                } else {
                    JOptionPane.showMessageDialog(
                            EditarCompetenciasFrame.this,
                            "Ingrese una competencia válida.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });


        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = competenciasList.getSelectedValue();
                if (selected != null) {
                    competenciasModel.removeElement(selected);
                    JOptionPane.showMessageDialog(
                            EditarCompetenciasFrame.this,
                            "Clases.Competencia eliminada correctamente.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            EditarCompetenciasFrame.this,
                            "Seleccione una competencia para eliminar.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });


        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
