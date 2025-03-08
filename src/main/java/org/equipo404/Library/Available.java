package org.equipo404.Library;

import org.equipo404.DesignPatterns.Context;
import org.equipo404.User.User;
import org.equipo404.util.TerminalUI;

public class Available extends DocumentState {

    @Override
    public boolean lend(){
        return true;
    }

    @Override
    public void reserve(User user){
        TerminalUI.success("El material solicitado esta " + this);
        context().addElement(user);
        context().checkWaitingUsers();
    }

    @Override
    public String toString() {
        return "✅ Disponible";
    }

}
