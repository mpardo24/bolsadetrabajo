package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarCompetenciasFrame extends JDialog {
    private JTextField competenciaField;
    private JComboBox<String> nivelComboBox;
    private DefaultListModel<String> competenciasModel;

    public AgregarCompetenciasFrame(DefaultListModel<String> competenciasModel) {
        setTitle("Agregar Competencias");
        setSize(300, 200);
        setLayout(new FlowLayout());

        this.competenciasModel = competenciasModel;

        add(new JLabel("Competencia:"));
        competenciaField = new JTextField(15);
        add(competenciaField);

        add(new JLabel("Nivel:"));
        String[] niveles = {"Principiante", "Intermedio", "Experto"};
        nivelComboBox = new JComboBox<>(niveles);
        add(nivelComboBox);


        JButton agregarButton = new JButton("Agregar");
        JButton cerrarButton = new JButton("Cerrar");

        add(agregarButton);
        add(cerrarButton);


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String competencia = competenciaField.getText();
                String nivel = (String) nivelComboBox.getSelectedItem();
                if (!competencia.isEmpty()) {
                    competenciasModel.addElement(competencia + " - " + nivel);
                    JOptionPane.showMessageDialog(
                            AgregarCompetenciasFrame.this,
                            "Competencia agregada correctamente.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    competenciaField.setText("");
                } else {
                    JOptionPane.showMessageDialog(
                            AgregarCompetenciasFrame.this,
                            "Ingrese una competencia válida.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
