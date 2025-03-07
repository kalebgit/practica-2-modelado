package org.equipo404.DesignPatterns;

public abstract class Context<T extends Context<T>> {
    private State<T> state;

    public Context(State<T> state){
        this.state = state;
    }

    public void changeState(State<T> state){
        this.state = state;
    }
}
