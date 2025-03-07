package org.equipo404.Library;

import org.equipo404.DesignPatterns.Context;

public class Available extends DocumentState {
    public boolean lend(){
        return true;
    }

    @Override
    public String toString() {
        return "✅ Disponible";
    }
}
