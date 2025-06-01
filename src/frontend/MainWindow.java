package frontend;

import backend.Item;
import backend.ItemLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

public class MainWindow extends JFrame {
    private JLabel infoLabel;
    private JButton cargarBtn;
    private JButton iniciarBtn;
    private List<Item> items;

    public MainWindow() {
        setTitle("Tarea 2 - Evaluador");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana

        initComponents();
    }

    private void initComponents() {
        infoLabel = new JLabel("No se ha cargado ningún archivo.", SwingConstants.CENTER);
        cargarBtn = new JButton("Cargar archivo de ítems");
        iniciarBtn = new JButton("Iniciar prueba");
        iniciarBtn.setEnabled(false); // Deshabilitado al inicio

        cargarBtn.addActionListener(this::cargarArchivo);
        iniciarBtn.addActionListener(e -> {
            getContentPane().removeAll();
            setLayout(new BorderLayout());
            add(new PreguntaPanel(items, this), BorderLayout.CENTER);
            revalidate();
            repaint();
        });


        setLayout(new BorderLayout());
        add(infoLabel, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(cargarBtn);
        panelBotones.add(iniciarBtn);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarArchivo(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            List<Item> cargados = ItemLoader.cargarDesdeArchivo(archivo.getAbsolutePath());

            if (cargados != null) {
                this.items = cargados;

                int totalTiempo = items.stream().mapToInt(i -> i.tiempoEstimado).sum();
                infoLabel.setText("<html>Archivo cargado con éxito.<br>"
                        + "Ítems: " + items.size() + "<br>"
                        + "Tiempo total estimado: " + totalTiempo + " segundos</html>");

                iniciarBtn.setEnabled(true);
            } else {
                infoLabel.setText("Error al cargar archivo.");
                iniciarBtn.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow ventana = new MainWindow();
            ventana.setVisible(true);
        });
    }
}
