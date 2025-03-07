package org.equipo404.Library;

import java.util.*;

public class EntryQueue<K, V> {
    private final LinkedHashMap<K, V> map = new LinkedHashMap<>();
    private final Queue<Map.Entry<K, V>> order = new LinkedList<>();

    public void enqueue(K key, V value) {
        if (!map.containsKey(key)) {
            order.offer(new AbstractMap.SimpleEntry<>(key, value));
        }
        map.put(key, value);
    }

    public Map.Entry<K, V> dequeue() {
        if (order.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        Map.Entry<K, V> oldestEntry = order.poll();
        map.remove(oldestEntry.getKey());
        return oldestEntry;
    }

    public Map.Entry<K, V> peek() {
        if (order.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return order.peek();
    }

    public boolean isEmpty() {
        return order.isEmpty();
    }

    public int size() {
        return order.size();
    }

    public static void main(String[] args) {
        EntryQueue<Integer, String> queue = new EntryQueue<>();
        queue.enqueue(1, "A");
        queue.enqueue(2, "B");
        queue.enqueue(3, "C");

        System.out.println(queue.dequeue()); // 1=A
        System.out.println(queue.peek());    // 2=B
    }
}