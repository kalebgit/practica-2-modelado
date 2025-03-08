package org.equipo404.Collections;

import java.util.List;
/**
 * Representa una biblioteca que contiene múltiples colecciones de recursos.
 *
 * @author Emiliano Kaleb Jimenez Rivera
 * @author Bedoya Tellez Ariadna Valeria
 * @author Sanchez Soto Saul
 * @version 1.0
 */

public class Library {
    /**
     * Lista de colecciones de recursos dentro de la biblioteca.
     */

    private List<ResourceCollection<?>> resourceCollections;
    /**
     * Constructor de la biblioteca.
     *
     * @param resourceCollections Lista de colecciones de recursos en la biblioteca.
     */

    public Library(List<ResourceCollection<?>> resourceCollections) {
        this.resourceCollections = resourceCollections;
    }

    /**
     * Muestra todos los recursos de la biblioteca en un formato bonito.
     */
    public void showLibraryResources() {
        System.out.println("═══════════════════════════");
        System.out.println("    📚 RECURSOS DE LA BIBLIOTECA    ");
        System.out.println("═══════════════════════════");

        for (ResourceCollection<?> collection : resourceCollections) {
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
}
