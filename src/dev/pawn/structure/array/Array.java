package dev.pawn.structure.array;

import java.util.StringJoiner;

public class Array<E> {
    private E[] elements;
    private int size;

    public Array() {
        elements = (E[]) new Object[0];
    }

    public Array(int cap) {
        elements = (E[]) new Object[cap];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return elements.length;
    }

    public E get(int index) {
        checkIndex(index);
        return elements[index];
    }

    public E set(int index, E e) {
        checkIndex(index);
        E old = elements[index];
        elements[index] = e;
        return old;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void addFirst(E e) {
        checkSize();
        add(0, e);
    }

    public void addLast(E e) {
        checkSize();
        elements[size++] = e;
    }

    public void add(int index, E e) {
        checkSize();
        checkIndex(index);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = e;
        size++;
    }

    public E remove(int index) {
        checkSize();
        checkIndex(index);
        E e = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public boolean removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == e) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == e) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index can't > size");
        }
    }

    private void checkSize() {
        if (size >= elements.length) {
            int len;
            int newLen = ((len = elements.length) != 0) ? (len + len / 2) : 8;
            E[] temp = (E[]) new Object[newLen];
            System.arraycopy(elements, 0, temp, 0, len);
            elements = temp;
        }
    }

    @Override
    public String toString() {
        String s = String.format("cap : %s , size : %s  => [", elements.length, size);

        StringJoiner sj = new StringJoiner(",", s, "]");
        for (int i = 0; i < size; i++) {
            sj.add(String.valueOf(elements[i]));
        }
        return sj.toString();
    }

    public static void main(String[] args) {
        Array<String> array = new Array<>(3);
        array.addLast("1");
        System.out.println(array);
        array.addLast("3");
        System.out.println(array);
        array.add(1, "2");
        System.out.println(array);

        array.addFirst("0");
        System.out.println(array);

        array.removeFirst();
        System.out.println(array);

        array.removeElement("2");
        System.out.println(array);

    }

}
