package org.equipo404.DesignPatterns;
/**
 * Interfaz que define el patrón de diseño Observer.
 * Permite que los objetos observadores reciban actualizaciones
 * cuando ocurre un cambio en el sujeto observado.
 * 
 * @autores Emiliano Kaleb Jimenez Rivera, Bedoya Tellez Ariadna Valeria, Sanchez Soto Saul
 * @version 1.0
 */
import org.equipo404.Library.DocumentTemplate;
import org.equipo404.User.BorrowType;

public interface Observer {
    void update(String message);
}
