package org.equipo404.Library;

import org.equipo404.DesignPatterns.Context;

public class Borrowed extends DocumentState {

    @Override
    public boolean lend() {
        return false;
    }

}
