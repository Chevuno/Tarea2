package frontend;

import backend.Item;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RevisarPanel extends JPanel {
    private List<Item> items;
    private String[] respuestasUsuario;
    private int indiceActual = 0;
    private JFrame padre;

    private JLabel preguntaLabel;
    private JTextArea resultadoArea;
    private JButton btnAnterior, btnSiguiente, btnVolverResumen;

    public RevisarPanel(List<Item> items, String[] respuestasUsuario, JFrame padre) {
        this.items = items;
        this.respuestasUsuario = respuestasUsuario;
        this.padre = padre;

        initUI();
        mostrarPregunta();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        preguntaLabel = new JLabel("Pregunta", SwingConstants.CENTER);
        preguntaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(preguntaLabel, BorderLayout.NORTH);

        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);

        JPanel botonesPanel = new JPanel();

        btnAnterior = new JButton("Anterior");
        btnAnterior.addActionListener(e -> {
            indiceActual--;
            mostrarPregunta();
        });

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(e -> {
            indiceActual++;
            mostrarPregunta();
        });

        btnVolverResumen = new JButton("Volver al resumen");
        btnVolverResumen.addActionListener(e -> {
            padre.getContentPane().removeAll();
            padre.add(new ResultadoResumenPanel(items, respuestasUsuario, padre));
            padre.revalidate();
            padre.repaint();
        });

        botonesPanel.add(btnAnterior);
        botonesPanel.add(btnSiguiente);
        botonesPanel.add(btnVolverResumen);

        add(botonesPanel, BorderLayout.SOUTH);
    }

    private void mostrarPregunta() {
        Item actual = items.get(indiceActual);
        String respuestaUsuario = respuestasUsuario[indiceActual];
        boolean correcta = actual.respuestaCorrecta.equalsIgnoreCase(respuestaUsuario);

        preguntaLabel.setText((indiceActual + 1) + ". " + actual.pregunta);

        StringBuilder sb = new StringBuilder();
        sb.append("Tu respuesta: ").append(respuestaUsuario == null ? "(no respondida)" : respuestaUsuario).append("\n");
        sb.append("Respuesta correcta: ").append(actual.respuestaCorrecta).append("\n\n");
        sb.append("Resultado: ").append(correcta ? "✅ CORRECTA" : "❌ INCORRECTA");

        resultadoArea.setText(sb.toString());

        btnAnterior.setEnabled(indiceActual > 0);
        btnSiguiente.setEnabled(indiceActual < items.size() - 1);

        revalidate();
        repaint();
    }
}
