package org.equipo404.Collections;

import org.equipo404.Library.DocumentTemplate;
import org.equipo404.Library.Resource;
import org.equipo404.Library.ResourceCategory;
import org.equipo404.User.BorrowType;
import org.equipo404.User.User;
import org.equipo404.util.TerminalUI;

import java.util.List;

public class Library<T extends Resource>{

    private List<ResourceCollection<? extends T>> resourceCollections;

    public Library(List<ResourceCollection<? extends T>> resourceCollections) {
        this.resourceCollections = resourceCollections;
    }
    /**
     * Muestra todos los recursos de la biblioteca en un formato bonito.
     */
    public void showLibraryResources() {
        System.out.println("═══════════════════════════");
        System.out.println("    📚 RECURSOS DE LA BIBLIOTECA    ");
        System.out.println("═══════════════════════════");

        if (resourceCollections == null || resourceCollections.isEmpty()) {
            TerminalUI.error("⚠️ Error: La lista de colecciones está vacía.");
            return;
        }


        for (ResourceCollection<? extends T> collection : resourceCollections) {
            if(resourceCollections == null){
                TerminalUI.error("esta vacia colecciones");
            }
            System.out.println(collection.toString());
        }
    }

    /**
     * Muestra una sección específica de la biblioteca.
     */
    public void showLibrarySection(int index) {
        if (index < 0 || index >= resourceCollections.size()) {
            System.out.println("❌ Sección inválida.");
            return;
        }

        System.out.println("═══════════════════════════");
        System.out.println("    📖 SECCIÓN SELECCIONADA    ");
        System.out.println("═══════════════════════════");
        System.out.println(resourceCollections.get(index));
    }

    /**
     * Devuelve las secciones disponibles en la biblioteca.
     */
    public void getAvailableSections() {
        System.out.println("Secciones disponibles:");
        for (int i = 0; i < resourceCollections.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, resourceCollections.get(i).getClass().getSimpleName());
        }
    }

    /**
     * Encuentra un recurso por su título
     * @param title Título del recurso a buscar
     * @return Recurso encontrado o null si no existe
     */
    public Resource findResourceByTitle(String title) {
        for (ResourceCollection<?> collection : resourceCollections) {
            for (Resource resource : collection) {
                if (resource.getTitle().equalsIgnoreCase(title)) {
                    return resource;
                }
            }
        }
        return null;
    }


    public Resource findResourceById(int id){
        for (ResourceCollection<?> collection : resourceCollections) {
            for (Resource resource : collection) {
                if (resource.getId() == id) {
                    return resource;
                }
            }
        }
        return null;

    }

    /**
     * Realiza el préstamo de un material a un usuario
     * @param user Usuario que solicita el préstamo
     * @param borrowType Tipo de préstamo (Regular o Express)
     * @param document Documento específico en el formato solicitado
     * @return true si el préstamo fue exitoso, false en caso contrario
     */
    public boolean borrowMaterial(User user, BorrowType borrowType, DocumentTemplate document) {
        if (document == null) {
            TerminalUI.error("El material solicitado no existe en la biblioteca.");
            return false;
        }

        user.borrow(document, borrowType);
        return true;
    }

    /**
     * Reserva un material para un usuario
     * @param user Usuario que solicita la reserva
     * @param document Documento específico en el formato solicitado
     */
    public void reserveMaterial(User user, DocumentTemplate document) {
        if (document == null) {
            TerminalUI.error("El material solicitado no existe en la biblioteca.");
            return;
        }
        document.reserve(user);
    }

    public void returnMaterial(User user, int id) {
        if (user.getDocumentBorrowed() != null && user.getDocumentBorrowed().getResource().getId() == id) {
            user.returnBorrowedDoc();
            TerminalUI.success("Material devuelto a la biblioteca.");
        } else {
            TerminalUI.error("No tienes ese material prestado.");
        }
    }

    public List<ResourceCollection<? extends T>> getResourceCollections() {
        return resourceCollections;
    }

}