package org.equipo404.User;

import org.equipo404.Library.Available;
import org.equipo404.Library.Borrowed;
import org.equipo404.Library.DocumentTemplate;
import org.equipo404.util.TerminalUI;
/**
 * Esta clase representa el estado "Regular" de un usuario, permitiendo préstamos y devoluciones de documentos.
 * 
 * @autores Emiliano Kaleb Jimenez Rivera, Bedoya Tellez Ariadna Valeria, Sanchez Soto Saul
 * @version 1.0
 */

public class Regular extends UserState{
    /**
     * Realiza el préstamo de un documento si está disponible, y lo reserva si no lo está.
     *
     * @param doc El documento a prestar.
     * @param borrowType El tipo de préstamo a realizar.
     */

    @Override
    public void borrow(DocumentTemplate doc, BorrowType borrowType) {
        if(doc.lend()) {
            context().setBorrowType(borrowType);
            context().setDocumentBorrowed(doc);
            context().getDocumentBorrowed().changeState(new Borrowed());
        } else {
            doc.reserve(context());
        }
    }
    /**
     * Realiza la devolución del documento prestado y actualiza su estado.
     */

    @Override
    void returnBorrowedDoc() {
        TerminalUI.success("😊 Gracias por ser responsable, usuario " + context() +
                " y devolverlo a tiempo");
        DocumentTemplate doc = context().getDocumentBorrowed();
        doc.changeState(new Available());
        doc.checkWaitingUsers();
        context().setDocumentBorrowed(null);
    }
    /**
     * Representa el estado del usuario como una cadena de texto.
     *
     * @return La representación en texto del estado del usuario.
     */

    @Override
    public String toString() {
        return "✅ Activo";
    }

}

