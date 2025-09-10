package gui_basica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AplicacionGUI extends JFrame {

    // Lista para guardar los datos ingresados por el usuario
    private ArrayList<String> datos;

    // Componentes de la interfaz
    private JTextField campoTexto;
    private JButton botonAgregar;
    private JButton botonLimpiar;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaDatos;

    // Constructor principal donde armamos toda la ventana
    public AplicacionGUI() {
        // Inicializamos la lista que guardará los datos
        datos = new ArrayList<>();

        // Configuración básica de la ventana
        setTitle("Registro Visual de Datos"); // Título descriptivo
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Panel principal con un poco de espacio entre componentes
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior con etiqueta, campo de texto y botones
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelEntrada.setBackground(new Color(220, 220, 220)); // Color ligero de fondo

        // Etiqueta descriptiva
        JLabel etiqueta = new JLabel("Escribe un dato y agrégalo:");
        panelEntrada.add(etiqueta);

        // Campo de texto donde el usuario escribe
        campoTexto = new JTextField(20);
        panelEntrada.add(campoTexto);

        // Botón Agregar
        botonAgregar = new JButton("Agregar");
        botonAgregar.setBackground(new Color(135, 206, 250));
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarDato();
            }
        });
        panelEntrada.add(botonAgregar);

        // Botón Limpiar
        botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setBackground(new Color(250, 128, 114));
        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarDatos();
            }
        });
        panelEntrada.add(botonLimpiar);

        // Lista para mostrar los datos ingresados
        modeloLista = new DefaultListModel<>();
        listaDatos = new JList<>(modeloLista);
        listaDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(listaDatos);

        // Añadimos los paneles al panel principal
        panelPrincipal.add(panelEntrada, BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        // Añadimos el panel principal a la ventana
        add(panelPrincipal);

        // Hacemos visible la ventana
        setVisible(true);
    }

    // Método que agrega un dato a la lista
    private void agregarDato() {
        String texto = campoTexto.getText().trim();
        if (!texto.isEmpty()) {
            datos.add(texto); // Guardamos en la lista interna
            modeloLista.addElement(texto); // Mostramos en la lista visual
            campoTexto.setText(""); // Limpiamos el campo para el siguiente dato
        } else {
            JOptionPane.showMessageDialog(this,
                    "No puedes agregar un dato vacío. Por favor, escribe algo.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // Método que limpia todos los datos
    private void limpiarDatos() {
        datos.clear();       // Limpiamos la lista interna
        modeloLista.clear(); // Limpiamos la lista visual
        campoTexto.setText(""); // Limpiamos el campo de texto
    }

    // Método principal para arrancar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplicacionGUI();  // Llama al constructor de la clase
            }
        });
    }
}
