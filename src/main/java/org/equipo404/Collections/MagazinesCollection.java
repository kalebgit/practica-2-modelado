package org.equipo404.Collections;

import org.equipo404.Library.Magazine;

import java.util.Iterator;
/**
 * Representa una colección de revistas dentro del sistema de la biblioteca digital.
 * Extiende {@link ResourceCollection} para manejar recursos de tipo {@link Magazine}.
 *
 * @author Emiliano Kaleb Jimenez Rivera
 * @author Bedoya Tellez Ariadna Valeria
 * @author Sanchez Soto Saul
 * @version 1.0
 */

public class MagazinesCollection extends ResourceCollection<Magazine> {
    /**
     * Arreglo iterable que almacena las revistas de la colección.
     */

    // clase propia para que se visualice mejor el uso de arreglos
    IterableArray<Magazine> magazinesArray;
    /**
     * Constructor de la colección de revistas.
     *
     * @param iteratorStrategy Estrategia de iteración a utilizar.
     * @param magazines Arreglo de revistas que conforman la colección.
     */

    public MagazinesCollection(CollectionIteratorStrategy<Magazine> iteratorStrategy, Magazine[] magazines) {
        super(iteratorStrategy);
        magazinesArray = new IterableArray<>(magazines);
        iteratorStrategy.setIterable(magazinesArray);
    }
    /**
     * Retorna un iterador para recorrer las revistas de la colección.
     *
     * @return Un iterador sobre las revistas.
     */

    @Override
    public Iterator<Magazine> iterator() {
        return this.getIteratorStrategy().getRawCollectionIterator();
    }
}
