package org.equipo404.DesignPatterns;

public abstract class Context {
    private State<Context> state;

    public Context(State<Context> state){
        this.state = state;
    }

    public void changeState(State<Context> state){
        this.state = state;
    }
}
