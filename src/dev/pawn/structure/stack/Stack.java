package dev.pawn.structure.stack;

public interface Stack<E> {

    void push(E e);

    E pop();

    E peek();

    int size();

    boolean isEmpty();
}
