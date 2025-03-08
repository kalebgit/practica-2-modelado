package org.equipo404.DesignPatterns;


import org.equipo404.Library.DocumentTemplate;
import org.equipo404.User.BorrowType;
/**
 * Interfaz que define el patrón de diseño Observer.
 * Permite que los objetos observadores reciban actualizaciones
 * cuando ocurre un cambio en el sujeto observado.
 * 
 * @autores Emiliano Kaleb Jimenez Rivera, Bedoya Tellez Ariadna Valeria, Sanchez Soto Saul
 * @version 1.0
 */

public interface Observer {
    /**
     * Método que recibe una actualización con un mensaje.
     * 
     * @param message Mensaje de actualización enviado por el sujeto observado.
     */
    void update(String message);
}
