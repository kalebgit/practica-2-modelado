package org.equipo404.User;

import org.equipo404.Library.Available;
import org.equipo404.Library.DocumentTemplate;
import org.equipo404.util.TerminalUI;

public class Irregular extends UserState {
    @Override
    public void borrow(DocumentTemplate doc, BorrowType borrowType) {
        TerminalUI.error("El usuario " + this.context().getEmail() +
                " no puede pedir prestado " + doc.getResource().getTitle() + " porque es un usuario irregular" );
    }

    @Override
    void returnBorrowedDoc() {
        TerminalUI.warning("😠 No es posible que el usuario " + context() +
                " se haya tardado tanto en devolver el documento");
        DocumentTemplate doc = context().getDocumentBorrowed();
        doc.changeState(new Available());
        doc.checkWaitingUsers();
        context().changeState(new Regular());
        context().setDocumentBorrowed(null);
    }
    @Override
    public String toString() {
        return "❌ Moroso";
    }

}
