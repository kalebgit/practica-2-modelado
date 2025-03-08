package org.equipo404.Library;

import org.equipo404.DesignPatterns.Context;
import org.equipo404.DesignPatterns.State;
import org.equipo404.User.User;

public abstract class DocumentState implements State<DocumentTemplate> {

    protected DocumentTemplate context;


    @Override
    public DocumentTemplate  context() {
        return  this.context;
    }


    @Override
    public void setContext(DocumentTemplate context) {
        this.context = context;
    }
    public abstract boolean lend();
    public abstract void reserve(User user);
}
