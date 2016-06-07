package impl;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class SimpleQueue<T> extends AbstractQueue<T> implements Queue<T> {

    private int size;
    private Node<T> first;
    private Node<T> last;

    public SimpleQueue() {
        size = 0;
        first = last = null;
    }

    private static class Node<T> {
        private T info;
        private Node<T> next;

        public Node(T info) {
            this.info = info;
            this.next = null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean offer(T t) {
        Node<T> inserting = new Node<>(t);
        if (first == null) {
            first = last = inserting;
        } else {
            last.next = inserting;
        }

        last = inserting;
        size++;

        return true;
    }

    @Override
    public T poll() {
        T head = null;
        if (first != null) {
            head = first.info;
            first = first.next;
            size--;
        }

        if (first == null) {
            last = null;
        }

        return head;
    }

    @Override
    public T peek() {
        return first != null? first.info : null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private static class SimpleQueueIterator<T> implements Iterator<T> {

        private Node<T> current;
        private SimpleQueue<T> queue;

        public SimpleQueueIterator(SimpleQueue<T> queue) {
            this.queue = queue;
            this.current = queue.first;
        }

        @Override
        public boolean hasNext() {
            return current != null && current.next != null;
        }

        @Override
        public T next() {
            T next = null;

            if (current != null) {
                next = current.info;
                current = current.next;
            } else {
                throw new NoSuchElementException();
            }

            return next;
        }
    }
}
