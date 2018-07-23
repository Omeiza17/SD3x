package homework_I;

/**
 * A Heap implementation class
 * 
 *
 */
@SuppressWarnings("ALL")
public class MinHeap {
	
	CompareInt[] heap; // the array that holds the heap data
	int size; // the number of elements currently stored in the heap

	/**
	 * Constructs a new heap with maximum capacity n
	 * Remember to index your heap at 1 instead of 0!
	 * @param n the maximum number of elements allowed in the heap
	 */
	public MinHeap(int n) {
		heap = new CompareInt[n+1];
		size = 0;
	}
	
	/**
	 * Adds an element to the heap
	 * 
	 * @param val the value to be added to the heap
	 */
	public void add(CompareInt val) {
		if (isFull()) throw new IllegalArgumentException();
		this.size++;
		heap[this.size] = val;
		swim(size);
	}

    private void swim(int size) {
	    while (size > 1 && isless(heap[size / 2], heap[size])) {
	        swap(size, size / 2);
	        size /= 2;
        }
    }

    private void swap(int i, int j) {
	    CompareInt temp = heap[i];
	    heap[i] = heap[j];
	    heap[j] = temp;

    }

    private boolean isless(CompareInt i, CompareInt j) {
	    return i.compareTo(j) < 0;
    }

    private boolean isFull() {
	    return this.size == this.heap.length - 1;
    }
	
	/**
	 * Extracts the smallest element from the heap
	 */
	public CompareInt extractMin() {
	    /*
	    * the entry at index 1 of the array is always the min in the collection.
	    * swap the min with the last entry in the collection
	    * sink the new element at index 1
	    * return the last element in the list
	    */
		CompareInt min = this.heap[1];
		this.heap[1] = heap[size];
		heap[size] = null;
		this.size--;
		sink(1);

		return min;
	}

    private void sink(int i) {
	    /*
	    * As long as twice the inputed index to be sank is less than the size of the heap,
	    *   check which of the children elements is lesser and set that to min.
	    *
	    *   if the element to be sunk is less than the min child element then the relationship is in order and break
	    *       out he loop
	    *   else swap the the element to be sunk and the min child element.
	    */
	    while (2 * i < size) {
	        int min = isless(heap[2 * i], heap[2 * i + 1]) ? 2 * i : 2 *i + 1;

	        if (isless(heap[i], heap[min])) break;

	        swap(i, min);
	        i = min;
        }
    }

}
