package utils;

public class MinPriorityQueue<T extends Comparable<T>> {

    private T[] queue;
    private int sizePointer;

    /**
     * Creates an empty queue.
     */
    public MinPriorityQueue(T[] t, int length) {
        // TODO factory?
        this.queue = t;
        this.sizePointer = length;
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
        int index = sizePointer;
        int parentIndex = findParentsIndex(index);
        queue[index] = elem;

        // while the element is smaller than the parent, then swap them
        while (elem.compareTo(queue[parentIndex]) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = findParentsIndex(parentIndex);
        }

        sizePointer++;
    }

    /**
     * Removes, and returns, the element at the front of the queue.
     */
    public T remove() {
        int i = 1;
        T front = queue[i];
        queue[i] = queue[sizePointer - 1]; // should be -1!
        queue[sizePointer - 1] = null;
        int smallerChildIndex = findSmallerChildIndex(i);

        // while the element is greater than the smaller child
        while (queue[i].compareTo(queue[smallerChildIndex]) > 0) {
            swap(i, smallerChildIndex);
            i = smallerChildIndex;
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
            System.out.println(queue[i]);
        }

    }

    private int findLeftChild(int i) {
        return 2*i;
    }

    private int findRightChild(int i) {
        return 2*i + 1;
    }

    private int findParentsIndex(int i) {
        return i/2;
    }

    private int findSmallerChildIndex(int i) {
        int leftChildIndex = findLeftChild(i);
        int rightChildIndex = findRightChild(i);

        if (queue[leftChildIndex].compareTo(queue[rightChildIndex]) < 0) {
            return leftChildIndex;
        } else {
            return rightChildIndex;
        }
    }

}
