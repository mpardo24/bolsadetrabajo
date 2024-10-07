package Ventanas;


import Clases.BolsaTrabajo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainVentana extends JFrame {
    private JDesktopPane desktopPane;
    private JLabel backgroundLabel;
    private BolsaTrabajo bolsaTrabajo;

    public MainVentana() {
        // Initialize Clases.BolsaTrabajo and load data
        bolsaTrabajo = new BolsaTrabajo();
        bolsaTrabajo.cargarDatos();

        setTitle("Gestión de Postulantes y Trabajos");
        setSize(1000, 666); // Match the background image size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Load and add background image to JLabel
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/background.jpg")); // Update path if necessary
        backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setSize(1000, 666);

        // Create the desktop pane for internal frames
        desktopPane = new JDesktopPane();
        desktopPane.setSize(1000, 666);

        // Add the background label to the desktop pane
        desktopPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        // Set content pane to the desktop pane
        setContentPane(desktopPane);

        // Create and configure the menu bar
        createMenuBar();

        // Create and add match buttons to the main window
        createMatchButtons();

        // Add a window listener to save data when closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bolsaTrabajo.guardarDatos();
                System.exit(0);
            }
        });
    }

    // Create and configure the menu bar
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Postulantes menu
        JMenu menuPostulantes = new JMenu("Postulantes");
        JMenuItem agregarPostulanteItem = new JMenuItem("Agregar Postulante");
        JMenuItem mostrarPostulantesItem = new JMenuItem("Mostrar Postulantes");
        JMenuItem editarPostulanteItem = new JMenuItem("Editar Postulante");
        JMenuItem eliminarPostulanteItem = new JMenuItem("Eliminar Postulante");
        menuPostulantes.add(agregarPostulanteItem);
        menuPostulantes.add(mostrarPostulantesItem);
        menuPostulantes.add(editarPostulanteItem);
        menuPostulantes.add(eliminarPostulanteItem);

        // Trabajos menu
        JMenu menuTrabajos = new JMenu("Trabajos");
        JMenuItem agregarTrabajoItem = new JMenuItem("Agregar Trabajo");
        JMenuItem mostrarTrabajosItem = new JMenuItem("Mostrar Trabajos");
        JMenuItem editarTrabajoItem = new JMenuItem("Editar Trabajo");
        JMenuItem eliminarTrabajoItem = new JMenuItem("Eliminar Trabajo");
        menuTrabajos.add(agregarTrabajoItem);
        menuTrabajos.add(mostrarTrabajosItem);
        menuTrabajos.add(editarTrabajoItem);
        menuTrabajos.add(eliminarTrabajoItem);

        // Opciones menu
        JMenu menuOpciones = new JMenu("Opciones");
        JMenuItem generarReporteItem = new JMenuItem("Generar reporte");
        JMenuItem acercaDeItem = new JMenuItem("Acerca de");
        menuOpciones.add(generarReporteItem);
        menuOpciones.add(acercaDeItem);

        // Add menus to menu bar
        menuBar.add(menuPostulantes);
        menuBar.add(menuTrabajos);
        menuBar.add(menuOpciones);
        setJMenuBar(menuBar);

        // Action listener for "Acerca de" menu item
        acercaDeItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Bolsa de Trabajo Portable.\n" +
                            "Versión 1.22.3\n" +
                            "JMJ Development Solutions.\n" +
                            "©2024, JMJ.\n" +
                            "All rights reserved.",
                    "Acerca de",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // Action listeners for Postulantes menu items
        agregarPostulanteItem.addActionListener(e -> agregarPostulante());
        mostrarPostulantesItem.addActionListener(e -> mostrarPostulantes());
        editarPostulanteItem.addActionListener(e -> editarPostulante());
        eliminarPostulanteItem.addActionListener(e -> eliminarPostulante());

        // Action listeners for Trabajos menu items
        agregarTrabajoItem.addActionListener(e -> agregarTrabajo());
        mostrarTrabajosItem.addActionListener(e -> mostrarTrabajos());
        editarTrabajoItem.addActionListener(e -> editarTrabajo());
        eliminarTrabajoItem.addActionListener(e -> eliminarTrabajo());

        // Action listener for "Generar Reporte" menu item
        generarReporteItem.addActionListener(e -> generarReporte());
    }

    // Create and add match buttons for new functionality
    private void createMatchButtons() {
        JPanel matchPanel = new JPanel();
        matchPanel.setLayout(new GridLayout(1, 2, 30, 0)); // Arrange buttons side by side with spacing
        matchPanel.setOpaque(false); // Transparent panel to see the background

        JButton matchTrabajoButton = new JButton("Match Trabajo por ID");
        JButton matchPostulanteButton = new JButton("Match Postulante por RUT");

        // Add buttons to the panel
        matchPanel.add(matchTrabajoButton);
        matchPanel.add(matchPostulanteButton);

        // Set the panel's position and size
        matchPanel.setBounds(300, 550, 400, 45);
        desktopPane.add(matchPanel, JLayeredPane.PALETTE_LAYER);

        // Action listeners for match buttons
        matchTrabajoButton.addActionListener(e -> hacerMatchConTrabajo());
        matchPostulanteButton.addActionListener(e -> hacerMatchConPostulante());
    }

    private void agregarPostulante() {
        AgregarPostulanteFrame agregarPostulanteFrame = new AgregarPostulanteFrame(bolsaTrabajo);
        desktopPane.add(agregarPostulanteFrame);
        agregarPostulanteFrame.setVisible(true);
    }

    private void mostrarPostulantes() {
        MostrarPostulantesFrame mostrarPostulantesFrame = new MostrarPostulantesFrame(bolsaTrabajo);
        desktopPane.add(mostrarPostulantesFrame);
        mostrarPostulantesFrame.setVisible(true);
    }

    // Method for editing a Postulante
    private void editarPostulante() {
        EditarPostulanteFrame editarPostulanteFrame = new EditarPostulanteFrame(bolsaTrabajo);
        desktopPane.add(editarPostulanteFrame);
        editarPostulanteFrame.setVisible(true);
    }

    // Method for deleting a Postulante
    private void eliminarPostulante() {
        // Open the EliminarPostulanteFrame
        EliminarPostulanteFrame eliminarPostulanteFrame = new EliminarPostulanteFrame(bolsaTrabajo);
        desktopPane.add(eliminarPostulanteFrame);
        eliminarPostulanteFrame.setVisible(true);
    }

    // Method for adding a new Trabajo
    private void agregarTrabajo() {
        AgregarTrabajoFrame agregarTrabajoFrame = new AgregarTrabajoFrame(bolsaTrabajo);
        desktopPane.add(agregarTrabajoFrame);
        agregarTrabajoFrame.setVisible(true);
    }

    // Method to show all jobs or search by ID
    private void mostrarTrabajos() {
        MostrarTrabajosFrame mostrarTrabajosFrame = new MostrarTrabajosFrame(bolsaTrabajo);
        desktopPane.add(mostrarTrabajosFrame);
        mostrarTrabajosFrame.setVisible(true);
    }


    // Placeholder method for editing a Trabajo
    private void editarTrabajo() {
        EditarTrabajoFrame editarTrabajoFrame = new EditarTrabajoFrame(bolsaTrabajo);
        desktopPane.add(editarTrabajoFrame);
        editarTrabajoFrame.setVisible(true);
    }

    private void eliminarTrabajo() {
        EliminarTrabajoFrame eliminarTrabajoFrame = new EliminarTrabajoFrame(bolsaTrabajo);
        desktopPane.add(eliminarTrabajoFrame);
        eliminarTrabajoFrame.setVisible(true);
    }

    private void generarReporte() {
        bolsaTrabajo.generarReporte();
        JOptionPane.showMessageDialog(this, "Reporte generado correctamente.");
    }

    private void hacerMatchConTrabajo() {
        MatchTrabajoFrame matchTrabajoFrame = new MatchTrabajoFrame(bolsaTrabajo);
        desktopPane.add(matchTrabajoFrame);
        matchTrabajoFrame.setVisible(true);
    }

    private void hacerMatchConPostulante() {
        MatchPostulanteFrame matchPostulanteFrame = new MatchPostulanteFrame(bolsaTrabajo);
        desktopPane.add(matchPostulanteFrame);
        matchPostulanteFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainVentana ventana = new MainVentana();
            ventana.setVisible(true);
        });
    }
}