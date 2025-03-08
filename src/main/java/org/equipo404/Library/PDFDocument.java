package org.equipo404.Library;

import org.equipo404.util.TerminalUI;

public class PDFDocument extends DocumentTemplate{
    public PDFDocument(Resource resource, DocumentState documentState) {
        super(resource, documentState);
    }

    @Override
    public void getFormat() {
        TerminalUI.info("El formato del documento " + this.getResource().getTitle() + " esta en formato pdf");
    }
}
