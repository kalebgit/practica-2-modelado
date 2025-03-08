package org.equipo404.Collections;

import org.equipo404.Library.AudioBook;
import org.equipo404.Library.Resource;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
/**
 * Representa una colección de audiolibros dentro del sistema de la biblioteca digital.
 * Extiende {@link ResourceCollection} para manejar recursos de tipo {@link AudioBook}.
 *
 * @author Emiliano Kaleb Jimenez Rivera
 * @author Bedoya Tellez Ariadna Valeria
 * @author Sanchez Soto Saul
 * @version 1.0
 */

public class AudioBooksCollection extends ResourceCollection<AudioBook>{
    /**
     * Mapa que almacena los audiolibros junto con su cantidad disponible.
     */

    Map<AudioBook, Integer> audiobooksHashMap;

    public AudioBooksCollection(CollectionIteratorStrategy<AudioBook> iteratorStrategy, Hashtable<AudioBook, Integer> audiobooks) {
        /**
     * Constructor de la colección de audiolibros.
     *
     * @param iteratorStrategy Estrategia de iteración a utilizar.
     * @param audiobooks Tabla hash que contiene los audiolibros y sus cantidades.
     */
        super(iteratorStrategy);
        audiobooksHashMap = audiobooks;
        iteratorStrategy.setRawCollectionIterator(audiobooksHashMap.keySet().iterator());
    }
     /**
     * Retorna un iterador para recorrer los audiolibros de la colección.
     *
     * @return Un iterador sobre los audiolibros.
     */

    @Override
    public Iterator<AudioBook> iterator() {
        return this.getIteratorStrategy().rawCollectionIterator;
    }
}
