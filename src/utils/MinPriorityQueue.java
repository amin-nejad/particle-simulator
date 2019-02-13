package utils;

import simulation.Event;

public class MinPriorityQueue<T extends Comparable<T>> {

    private T[] queue;
    private static final int MIN_SIZE = 1024;
    private int sizePointer = 1; // initiate starting size to 1, so we have nice child/parent tree structure

    /**
     * Creates an empty queue.
     */
    public MinPriorityQueue() {
        queue = (T[]) new Comparable[MIN_SIZE];
    }

    /**
     * Returns the number of elements currently in the queue.
     */
    public int size() {
        return sizePointer - 1;
    }
    
    /**
     * Adds elem to the queue.
     */
    public void add(T elem) {
        int index = sizePointer++;
        ensureCapacity(sizePointer*2);

        // add elem to the back
        queue[index] = elem;

        int parentIndex = findParentsIndex(index);

        // if queue was initially empty
        if (parentIndex < 1) {
            return;
        }

        // while the element is smaller than the parent, swap them
        while (elem.compareTo(queue[parentIndex]) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = findParentsIndex(parentIndex);
            if (parentIndex < 1) {
                break;
            }
        }
    }

    /**
     * Removes, and returns, the element at the front of the queue.
     */
    public T remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        int index = 1;

        // remove front, and move last element to front:
        T front = queue[1];
        queue[1] = queue[sizePointer - 1]; // should be -1!
        queue[sizePointer - 1] = null;
        sizePointer--;

        int smallerChildIndex;

        // if there is just one element, then return it:
        try {
            smallerChildIndex = findSmallerChildIndex(index);
        } catch (NullPointerException npe) {
            return front;
        }

        // while the element is greater than the smaller child, swap them:
        while (queue[index].compareTo(queue[smallerChildIndex]) > 0) {
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
            try {
                smallerChildIndex = findSmallerChildIndex(smallerChildIndex);
            } catch (NullPointerException npe) {
                break;
            }
        }

        return front;
    }

    /**
     * Returns true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() <= 0;
    }

    private void swap(int i1, int i2) {
        T temp = queue[i1];
        queue[i1] = queue[i2];
        queue[i2] = temp;
    }

    // helper method for debugging
    public void print() {
        for (int i = 1; i < sizePointer; i++) {
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
