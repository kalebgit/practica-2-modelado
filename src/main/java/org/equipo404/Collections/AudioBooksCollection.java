package org.equipo404.Collections;

import org.equipo404.Library.AudioBook;
import org.equipo404.Library.Resource;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class AudioBooksCollection extends ResourceCollection<AudioBook>{

    Map<AudioBook, Integer> audiobooksHashMap;

    public AudioBooksCollection(CollectionIteratorStrategy<AudioBook> iteratorStrategy, Hashtable<AudioBook, Integer> audiobooks) {
        super(iteratorStrategy);
        audiobooksHashMap = audiobooks;
        iteratorStrategy.setRawCollectionIterator(audiobooksHashMap.keySet().iterator());
    }

    @Override
    public Iterator<AudioBook> iterator() {
        return this.getIteratorStrategy().rawCollectionIterator;
    }
}
