package utils;

public class MinPriorityQueue<T extends Comparable<T>> {

    private static final int MAX_QUEUE_SIZE = 4096;
    private T[] queue = (T[]) new Comparable[MAX_QUEUE_SIZE];
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
        int index = sizePointer;
        int parentIndex = findParentsIndex(index);
        queue[index] = elem;

        // while the element is smaller than the parent, swap them
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
            System.out.println(queue[i]);
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

}
