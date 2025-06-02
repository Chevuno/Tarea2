package frontend;

import backend.Item;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PreguntaPanel extends JPanel {
    private List<Item> items;
    private int indiceActual = 0;
    private String[] respuestasUsuario;
    private JFrame padre;

    private JLabel preguntaLabel;
    private JPanel opcionesPanel;
    private JButton btnAnterior;
    private JButton btnSiguiente;

    private ButtonGroup opcionesGroup;

    public PreguntaPanel(List<Item> items, JFrame padre) {
        this.items = items;
        this.padre = padre;
        this.respuestasUsuario = new String[items.size()];
        initUI();
        mostrarPregunta();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        preguntaLabel = new JLabel("Pregunta", SwingConstants.CENTER);
        preguntaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(preguntaLabel, BorderLayout.NORTH);

        opcionesPanel = new JPanel();
        opcionesPanel.setLayout(new BoxLayout(opcionesPanel, BoxLayout.Y_AXIS));
        add(opcionesPanel, BorderLayout.CENTER);

        JPanel botonesPanel = new JPanel();
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");

        btnAnterior.addActionListener(e -> {
            guardarRespuestaActual();
            indiceActual--;
            mostrarPregunta();
        });

        btnSiguiente.addActionListener(e -> {
            guardarRespuestaActual();
            if (indiceActual < items.size() - 1) {
                indiceActual++;
                mostrarPregunta();
            } else {
                // Mostrar resumen final
                padre.getContentPane().removeAll();
                padre.add(new ResultadoResumenPanel(items, respuestasUsuario, padre));
                padre.revalidate();
                padre.repaint();
            }
        });

        botonesPanel.add(btnAnterior);
        botonesPanel.add(btnSiguiente);

        add(botonesPanel, BorderLayout.SOUTH);
    }

    private void mostrarPregunta() {
        Item actual = items.get(indiceActual);
        preguntaLabel.setText((indiceActual + 1) + ". " + actual.pregunta);

        opcionesPanel.removeAll();
        opcionesGroup = new ButtonGroup();

        if (actual.tipo.equals("seleccion_multiple")) {
            for (String opcion : actual.opciones) {
                JRadioButton radio = new JRadioButton(opcion);
                opcionesGroup.add(radio);
                opcionesPanel.add(radio);

                if (opcion.equals(respuestasUsuario[indiceActual])) {
                    radio.setSelected(true);
                }
            }
        } else if (actual.tipo.equals("verdadero_falso")) {
            String[] opciones = {"Verdadero", "Falso"};
            for (String opcion : opciones) {
                JRadioButton radio = new JRadioButton(opcion);
                opcionesGroup.add(radio);
                opcionesPanel.add(radio);

                if (opcion.equals(respuestasUsuario[indiceActual])) {
                    radio.setSelected(true);
                }
            }
        }

        btnAnterior.setEnabled(indiceActual > 0);
        btnSiguiente.setText(indiceActual == items.size() - 1 ? "Enviar respuestas" : "Siguiente");

        revalidate();
        repaint();
    }

    private void guardarRespuestaActual() {
        for (Component comp : opcionesPanel.getComponents()) {
            if (comp instanceof JRadioButton radio) {
                if (radio.isSelected()) {
                    respuestasUsuario[indiceActual] = radio.getText();
                }
            }
        }
    }
}
