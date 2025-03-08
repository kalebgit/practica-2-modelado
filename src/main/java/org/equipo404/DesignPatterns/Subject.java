package org.equipo404.DesignPatterns;


import java.util.List;
/**
 * Interfaz que define el patrón de diseño Observer.
 * Representa un sujeto que puede ser observado por múltiples observadores.
 * 
 * @autores Emiliano Kaleb Jimenez Rivera, Bedoya Tellez Ariadna Valeria, Sanchez Soto Saul
 * @version 1.0
 * @param <T> Tipo de los observadores que implementan la interfaz Observer.
 */

public interface Subject<T extends Observer> {
    /**
     * Agrega un observador a la lista de observadores.
     * 
     * @param observer Observador a agregar.
     */

    void addElement(T observer);
    /**
     * Elimina un observador de la lista de observadores.
     */
    void removeElement();
    /**
     * Envía una notificación a todos los observadores registrados.
     * 
     * @param message Mensaje de notificación a enviar.
     */
    void sendNotifications(String message);

}
