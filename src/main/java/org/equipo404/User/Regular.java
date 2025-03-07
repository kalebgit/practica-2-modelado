package org.equipo404.User;

import org.equipo404.Library.DocumentTemplate;

public class Regular extends UserState{
    @Override
    public void borrow(DocumentTemplate doc, BorrowType borrowType) {
        if(doc.lend()){
            context().setBorrowType(borrowType);
            context().setResourceBorrowed(doc.getResource());
        }else{
            doc.reserve(context());
        }
    }

    @Override
    void returnBorrowedDoc() {

    }
}
