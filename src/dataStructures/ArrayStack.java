package dataStructures;

import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E> {
    private E[] elems; // elems[0...depth-1] се неговите елементи.
    private int depth; // depth е длабочината на стекот.

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        // Конструкциjа на нов, празен стек.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty() {
        // Вра´ка true ако и само ако стекот е празен.
        return (depth == 0);
    }

    public E peek() {
        // O(1) complexity
        // Го вра´ка елементот на врвот од стекот.
        if (depth == 0)
            return null; // return null ako ne mora da se zamarame so exceptions
        return elems[depth - 1];
    }

    public void clear() {
        // O(n) complexity
        // Го празни стекот.
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }

    public void push(E x) {
        // O(1) complexity
        // Го додава x на врвот на стекот.
        elems[depth++] = x;
    }

    public int size() {
        // Jа вра´ка должината на стекот.
        return depth;
    }

    public E pop() {
        // O(1) complexity
        // Го отстранува и вра´ка елементот што е на врвот на стекот.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}