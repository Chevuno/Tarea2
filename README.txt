Tarea 2 - Paradigmas de Programación
Evaluador de Pruebas basado en la Taxonomía de Bloom

Autor:
- Felipe Vergara Rodríguez

----------

Cómo ejecutar el programa:

1. Abrir el proyecto en IntelliJ IDEA 2024.1.
2. Ejecutar la clase frontend.MainWindow.java.
3. Al iniciar el programa, hacer clic en "Cargar archivo de ítems" y seleccionar un archivo llamado `items.csv`.
4. Luego presionar "Iniciar prueba" para comenzar.

----------

Formato del archivo items.csv:

Cada línea representa una pregunta y tiene este formato:

tipo;pregunta;opciones(separadas por |);respuestaCorrecta;nivelBloom;tiempoEstimado

Ejemplo:

seleccion_multiple;¿Cuál es el resultado de 2 + 2?;3|4|5|6;4;Aplicar;30
verdadero_falso;Java es un lenguaje compilado.;Verdadero;Verdadero;Entender;20

- Para preguntas de verdadero/falso, las opciones pueden ser "Verdadero" o "Falso".
- Los campos deben estar separados por punto y coma (;).

----------

Supuestos:

- El archivo CSV se carga correctamente y tiene todas las columnas necesarias.
- No se valida si hay errores ortográficos o gramaticales en las preguntas.
- Todas las preguntas tienen una única respuesta correcta.
- Solo se aceptan los tipos: "seleccion_multiple" y "verdadero_falso".

----------

Requisitos del sistema:

- Java JDK 17 o superior
- IntelliJ IDEA Community Edition 2024.1
- No se utilizan librerías externas

----------

Descripción breve:

Este programa permite cargar una prueba desde un archivo, mostrar una pregunta a la vez, registrar respuestas del usuario y luego mostrar un resumen con porcentajes de aciertos por tipo de pregunta y por nivel de Bloom.

----------

