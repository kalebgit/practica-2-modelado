package org.equipo404.DesignPatterns;
/**
 * Clase abstracta que define el contexto para el patrón de diseño State.
 * Gestiona el estado actual y permite la transición entre estados.
 * 
 * @autores Emiliano Kaleb Jimenez Rivera, Bedoya Tellez Ariadna Valeria, Sanchez Soto Saul
 * @version 1.0
 * @param <T> Tipo genérico que extiende de Context, usado para asegurar la consistencia del contexto.
 */

public abstract class Context<T extends Context<T>> {
    /**
     * Estado actual del contexto.
     */
    private State<T> state;
    /**
     * Constructor de la clase Context.
     * 
     * @param state Estado inicial del contexto.
     */

    public Context(State<T> state){
        this.state = state;
    }
    /**
     * Cambia el estado actual del contexto.
     * 
     * @param state Nuevo estado a establecer.
     */

    public void changeState(State<T> state){
        this.state = state;
    }
}
