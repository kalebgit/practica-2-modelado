package org.equipo404.User;

import org.equipo404.Library.Available;
import org.equipo404.Library.Borrowed;
import org.equipo404.Library.DocumentTemplate;
import org.equipo404.util.TerminalUI;

public class Regular extends UserState{

    @Override
    public void borrow(DocumentTemplate doc, BorrowType borrowType) {
        TerminalUI.success("El usuario " + context().getEmail() + " ha tomado prestado " + doc.getResource().getTitle());
        if(doc.lend()) {
            context().setBorrowType(borrowType);
            context().setDocumentBorrowed(doc);
            context().getDocumentBorrowed().changeState(new Borrowed());
        } else {
            doc.reserve(context());
        }
    }

    @Override
    void returnBorrowedDoc() {
        TerminalUI.success("😊 Gracias por ser responsable, usuario " + context().getEmail() +
                " y devolverlo a tiempo");
        DocumentTemplate doc = context().getDocumentBorrowed();
        doc.changeState(new Available());
        doc.getDocumentState().setContext(doc);
        doc.checkWaitingUsers();
        context().setDocumentBorrowed(null);
    }

    @Override
    public String toString() {
        return "✅ Activo";
    }

}

