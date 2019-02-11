package utils;

import simulation.Event;

public class MinPriorityQueue<T extends Comparable<T>> {

    private T[] queue = (T[]) new Comparable[1024];
    private int sizePointer = 2;

    /**
     * Creates an empty queue.
     */
    public MinPriorityQueue(T t) {
        queue[1] = t;
    }

    /**
     * Returns the number of elements currently in the queue.
     */
    public int size() {
        return sizePointer;
    }
    
    /**
     * Adds elem to the queue.
     */
    public void add(T elem) {
        ensureCapacity(sizePointer*2);

        int index = sizePointer;
        int parentIndex = findParentsIndex(index);
        queue[index] = elem;

        // while the element is smaller than the parent, swap them
        while (elem.compareTo(queue[parentIndex]) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = findParentsIndex(parentIndex);
            if (parentIndex < 1) {
                break;
            }
        }

        sizePointer++;
    }

    /**
     * Removes, and returns, the element at the front of the queue.
     */
    public T remove() {
        int index = 1;
        T front = queue[index];
        queue[index] = queue[sizePointer - 1]; // should be -1!
        queue[sizePointer - 1] = null;
        int smallerChildIndex = findSmallerChildIndex(index);

        // while the element is greater than the smaller child
        while (queue[index].compareTo(queue[smallerChildIndex]) > 0) {
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
            try {
                smallerChildIndex = findSmallerChildIndex(smallerChildIndex);
            } catch (NullPointerException npe) {
                break;
            }
        }

        sizePointer--;
        return front;
    }

    /**
     * Returns true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return sizePointer <= 0;
    }

    private void swap(int i1, int i2) {
        T temp = queue[i1];
        queue[i1] = queue[i2];
        queue[i2] = temp;
    }

    // helper method for debugging
    public void print() {
        for (int i = 0; i < sizePointer; i++) {
            System.out.println(((Event) queue[i]).time());
        }

    }

    private int findLeftChildIndex(int i) {
        return 2*i;
    }

    private int findRightChildIndex(int i) {
        return 2*i + 1;
    }

    private int findParentsIndex(int i) {
        return i/2;
    }

    private int findSmallerChildIndex(int i) {
        int leftChildIndex = findLeftChildIndex(i);
        int rightChildIndex = findRightChildIndex(i);

        if (queue[leftChildIndex] == null) {
            throw new NullPointerException();
        }

        // special case for when right child is null but left child is not
        if (queue[rightChildIndex] == null) {
            return leftChildIndex;
        }

        if (queue[leftChildIndex].compareTo(queue[rightChildIndex]) < 0) {
            return leftChildIndex;
        }

        return rightChildIndex;
    }

    private void ensureCapacity(int cap) {
        if (queue.length < cap) {
            T[] newQueue = (T[]) new Comparable[queue.length*2];

            for (int i = 0; i < queue.length; i++) {
                newQueue[i] = queue[i];
            }

            queue = newQueue;
        }
    }

}
