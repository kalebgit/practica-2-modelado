package org.equipo404.User;

import org.equipo404.Library.Available;
import org.equipo404.Library.Borrowed;
import org.equipo404.Library.DocumentTemplate;

public class Regular extends UserState{

    @Override
    public void borrow(DocumentTemplate doc, BorrowType borrowType) {
        if(doc.lend()){
            context().setBorrowType(borrowType);
            context().setDocumentBorrowed(doc);
            context().getDocumentBorrowed().changeState(new Borrowed());
        }else{
            doc.reserve(context());
        }
    }

    @Override
    void returnBorrowedDoc() {
        System.out.println("Gracias por ser responsable emoji feliz usuario " + context() + " y devolverlo a tiempo");
        context().getDocumentBorrowed().changeState(new Available());
        context().getDocumentBorrowed().checkWaitingUsers();
        context().setDocumentBorrowed(null);
    }

    @Override
    public String toString() {
        return "✅ Activo";
    }
}
