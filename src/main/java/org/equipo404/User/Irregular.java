package org.equipo404.User;

import org.equipo404.Library.Available;
import org.equipo404.Library.DocumentTemplate;

public class Irregular extends UserState {
    @Override
    public void borrow(DocumentTemplate doc, BorrowType borrowType) {
        System.out.println("El usuario " + this.context() +
                "no puede pedir prestado " + doc + " porque es un usuario irregular");
    }

    @Override
    void returnBorrowedDoc() {
        System.out.println(" emoji enojado, no es posbile que el usuario " + context() + " se haya tardado tanto en devolver el documento");
        context().getDocumentBorrowed().changeState(new Available());
        context().getDocumentBorrowed().checkWaitingUsers();
        context().changeState(new Regular());
        context().setDocumentBorrowed(null);
    }

    @Override
    public String toString() {
        return "❌ Moroso";
    }
}
