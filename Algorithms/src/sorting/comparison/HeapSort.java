package sorting.comparison;

import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> {

    private int heapSize = 0;

    public HeapSort(int heapSize) {
        this.heapSize = heapSize;
    }

    public void swap(T[] array, int n, int m) {
        T temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }

    public void maxHeapify(T[] array, int i) {

        int left = (i << 1) + 1;
        int right = left + 1;
        int largest = i;

        if (left < heapSize && array[left].compareTo(array[i]) > 0)
            largest = left;

        if (right < heapSize && array[right].compareTo(array[largest]) > 0)
            largest = right;

        if (i != largest) { // swap
            swap(array, i, largest);
            maxHeapify(array, largest);
        }

    }

    public void minHeapify(T[] array, int i) {

        int left = (i << 1) + 1;
        int right = left + 1;
        int smallest = i;

        if (left < heapSize && array[left].compareTo(array[i]) < 0)
            smallest = left;

        if (right < heapSize && array[right].compareTo(array[smallest]) < 0)
            smallest = right;

        if (i != smallest) { // swap
            swap(array, i, smallest);
            minHeapify(array, smallest);
        }

    }

    public void buildMaxHeap(T[] array) {

        for (int i = (array.length - 1) / 2; i >= 0; i--)
            maxHeapify(array, i);

    }

    public void buildMinHeap(T[] array) {

        for (int i = (array.length - 1) / 2; i >= 0; i--)
            minHeapify(array, i);

    }

    public void sort(T[] array, boolean minHeap) {

        if (minHeap) {
            buildMinHeap(array);
            for (int i = array.length - 1; i > 0; i--) {
                swap(array, 0, i);
                heapSize--;
                minHeapify(array, 0);
            }
        } else {
            buildMaxHeap(array);
            for (int i = array.length - 1; i > 0; i--) {
                swap(array, 0, i);
                heapSize--;
                maxHeapify(array, 0);
            }
        }

    }

    public static void main(String... args) {

        Double[] numbers = {1.5, 8.4, 3.6, 9.9, 1.3, 3.4, 9.1, 5.1};
        HeapSort<Double> heapSort = new HeapSort<>(numbers.length);
        heapSort.sort(numbers, true);

        Arrays.asList(numbers).stream().forEach(System.out::println);

    }



}
