package org.equipo404.DesignPatterns;

/**
 * Interfaz base para representar un estado dentro del sistema.
 * @autores: Emiliano Kaleb Jimenez Rivera. Bedoya Tellez Ariadna Valeria. Sanchez Soto Saul
 * @version 1.0
 */
public interface State<T extends Context> {
    T context();
}

