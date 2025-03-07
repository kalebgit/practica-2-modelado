package org.equipo404.User;

import org.equipo404.Library.DocumentTemplate;

public class Irregular extends UserState {
    @Override
    public void borrow(DocumentTemplate doc, BorrowType borrowType) {
        System.out.println("El usuario " + this.context() +
                "no puede pedir prestado " + doc + " porque es un usuario irregular");
    }

    @Override
    void returnBorrowedDoc() {
        System.out.println("El usuario " + context() + " no tiene nada prestado");
    }

}
