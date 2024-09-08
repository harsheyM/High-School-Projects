public class MinHeapGeneric<T extends Comparable> {
    T[] heap;
    int size;
    static final int DEFAULT_CAPACITY = 8;

    public MinHeapGeneric() {
        this(DEFAULT_CAPACITY);
    }

    public MinHeapGeneric(int capacity) {
        heap = (T[]) new Comparable[capacity + 1];
        size = 0;
    }
    public MinHeapGeneric(T...nums)
    {
        this(nums.length);
        for (T i : nums)
        {
            heap[size + 1] = i;
            size++;
        }
        for (int i = size / 2; i >= 1; i--)
        {
            siftDown(i);
        }
    }
    int getSize() {
        return size;
    }

    boolean isEmpty() {
        return this.size == 0;
    }

    T peekMinimum() {
        return heap[1];
    }

    int getLeftChildIndex(int index) {
        return 2 * index;
    }

    int getRightChildIndex(int index) {
        return 2 * index + 1;
    }

    int getParentIndex(int index) {
        return index / 2;
    }

    private void doubleCapacity() {
        T[] heapNew = (T[]) new Comparable[size * 2 + 1];
        for (int i = 1; i < heap.length; i++) {
            heapNew[i] = heap[i];
        }
        this.heap = heapNew;
    }

    void insert(T value) {

        if (size + 1 >= heap.length)
        {
            doubleCapacity();
        }
        heap[size + 1] = value;
        bubbleUp(size + 1);
        size++;


    }
    private void bubbleUp(int index) {
        if (index > 1 && heap[getParentIndex(index)].compareTo(heap[index]) > 0)
        {
            mix(index, getParentIndex(index));
            bubbleUp(getParentIndex(index));
        }
    }
    private void mix(int parent, int child)
    {
        T temp = heap[parent];
        heap[parent] = heap[child];
        heap[child] = temp;
    }

    T popMinimum() {
        T min = peekMinimum();
        heap[1] = heap[size];
        size--;
        siftDown(1);
        return min;
    }

    private void siftDown(int index) {
        if (index * 2 > size)
        {
            return;
        }
        if (getRightChildIndex(index) > size)
        {
            if (heap[getLeftChildIndex(index)].compareTo(heap[index]) < 0)
            {
                mix(index, getLeftChildIndex(index));
            }
        }
        else if (heap[index].compareTo(heap[getLeftChildIndex(index)]) > 0 || heap[index].compareTo(heap[getRightChildIndex(index)]) > 0)
        {
            if (heap[getLeftChildIndex(index)].compareTo(heap[getRightChildIndex(index)]) < 0)
            {
                mix(index, getLeftChildIndex(index));
                siftDown(getLeftChildIndex(index));
            }
            else
            {
                mix(index, getRightChildIndex(index));
                siftDown(getRightChildIndex(index));
            }
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= getSize(); i++)
            output += heap[i] + ", ";
        return output.substring(0, output.lastIndexOf(","));
//lazily truncate last comma
    }

    /**
     * method borrowed
     * with minor modifications
     * from the internet somewhere, for printing
     */
    public void display() {
        int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
        String dots = "...............................";
        System.out.println(dots + dots);
        while (j <= this.getSize()) {
            if (column == 0)
                for (int k = 0; k < nBlanks; k++)
                    System.out.print(' ');
            System.out.print((heap[j] == null) ? "" : heap[j]);
            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' ');
            j++;
        }
        System.out.println("\n" + dots + dots);

    }
}