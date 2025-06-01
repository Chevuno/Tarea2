package backend;

import java.util.List;

public class Item {
    public String tipo; // "seleccion_multiple" o "verdadero_falso"
    public String pregunta;
    public List<String> opciones; // solo se usa en seleccion_multiple
    public String respuestaCorrecta;
    public String nivelBloom; // Recordar, Entender, Aplicar, etc.
    public int tiempoEstimado;
}
