package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemLoader {

    public static List<Item> cargarDesdeArchivo(String rutaArchivo) {
        List<Item> items = new ArrayList<>();

        try (BufferedReader archivo = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea = archivo.readLine();

            while (linea != null) {
                String[] datos = linea.split(";");
                if (datos.length != 6) {
                    System.out.println("Línea con formato incorrecto: " + linea);
                    linea = archivo.readLine();
                    continue;
                }

                String tipo = datos[0];
                String pregunta = datos[1];
                List<String> opciones = new ArrayList<>();

                if (tipo.equalsIgnoreCase("seleccion_multiple")) {
                    opciones = Arrays.asList(datos[2].split("\\|")); // separador: |
                }

                String respuestaCorrecta = datos[3];
                String nivelBloom = datos[4];
                int tiempoEstimado = Integer.parseInt(datos[5]);

                Item item = new Item();
                item.tipo = tipo;
                item.pregunta = pregunta;
                item.opciones = opciones;
                item.respuestaCorrecta = respuestaCorrecta;
                item.nivelBloom = nivelBloom;
                item.tiempoEstimado = tiempoEstimado;

                items.add(item);
                linea = archivo.readLine();
            }

        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }

        return items;
    }
}
