package org.equipo404.Library;

import org.equipo404.DesignPatterns.Context;
import org.equipo404.User.User;
import org.equipo404.util.TerminalUI;

public class Borrowed extends DocumentState {


    @Override
    public boolean lend() {
        return false;
    }


    @Override
    public void reserve(User user){
        TerminalUI.warning("El documento solicitado: " + context() + " esta " + this + " a alguien mas");
        context().addElement(user);
    }

    @Override
    public String toString() {
        return "❌ Prestado";
    }
}
