package org.equipo404.User;

import org.equipo404.Library.Available;
import org.equipo404.Library.DocumentTemplate;
import org.equipo404.util.TerminalUI;
/**
 * Representa el estado de un usuario irregular en el sistema de biblioteca.
 * Un usuario irregular no puede solicitar préstamos debido a retrasos en devoluciones previas.
 * 
 * @autores Emiliano Kaleb Jimenez Rivera, Bedoya Tellez Ariadna Valeria, Sanchez Soto Saul
 * @version 1.0
 */

public class Irregular extends UserState {
    /**
     * Intenta realizar un préstamo, pero los usuarios irregulares no pueden pedir prestado.
     * 
     * @param doc Documento solicitado.
     * @param borrowType Tipo de préstamo.
     */
    @Override
    public void borrow(DocumentTemplate doc, BorrowType borrowType) {
        TerminalUI.error("El usuario " + this.context() +
                " no puede pedir prestado " + doc + " porque es un usuario irregular");
    }
    /**
     * Devuelve un documento prestado y restaura el estado del usuario a regular.
     * Cambia el estado del documento a disponible y verifica si hay usuarios en espera.
     */

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
    /**
     * Devuelve una representación en cadena del estado irregular.
     * 
     * @return Una cadena indicando que el usuario es moroso.
     */
    @Override
    public String toString() {
        return "❌ Moroso";
    }

}
