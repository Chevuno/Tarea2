package frontend;

import backend.Item;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ResultadoResumenPanel extends JPanel {

    public ResultadoResumenPanel(List<Item> items, String[] respuestasUsuario) {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Resumen de Resultados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        JTextArea resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);

        Map<String, int[]> porTipo = new HashMap<>();
        Map<String, int[]> porBloom = new HashMap<>();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            String respuesta = respuestasUsuario[i];

            boolean correcta = item.respuestaCorrecta.equalsIgnoreCase(respuesta);

            // Por tipo
            porTipo.putIfAbsent(item.tipo, new int[2]); // [correctas, total]
            porTipo.get(item.tipo)[1]++;
            if (correcta) porTipo.get(item.tipo)[0]++;

            // Por nivel Bloom
            porBloom.putIfAbsent(item.nivelBloom, new int[2]);
            porBloom.get(item.nivelBloom)[1]++;
            if (correcta) porBloom.get(item.nivelBloom)[0]++;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("✔ Porcentaje correcto por tipo de ítem:\n");
        for (String tipo : porTipo.keySet()) {
            int[] datos = porTipo.get(tipo);
            int porcentaje = (int) ((datos[0] * 100.0) / datos[1]);
            sb.append(String.format("- %s: %d%% (%d de %d)\n", tipo, porcentaje, datos[0], datos[1]));
        }

        sb.append("\n✔ Porcentaje correcto por nivel de Bloom:\n");
        for (String nivel : porBloom.keySet()) {
            int[] datos = porBloom.get(nivel);
            int porcentaje = (int) ((datos[0] * 100.0) / datos[1]);
            sb.append(String.format("- %s: %d%% (%d de %d)\n", nivel, porcentaje, datos[0], datos[1]));
        }

        resultadoArea.setText(sb.toString());

        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);
    }
}
