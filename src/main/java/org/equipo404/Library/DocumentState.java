package org.equipo404.Library;

import org.equipo404.DesignPatterns.Context;
import org.equipo404.DesignPatterns.State;

public abstract class DocumentState implements State<DocumentTemplate> {

    private DocumentTemplate context;
    @Override
    public DocumentTemplate  context() {
        return  this.context;
    }
    public abstract boolean lend();
}
