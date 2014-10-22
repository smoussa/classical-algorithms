package sorting.comparison;

import java.util.Arrays;
import java.util.Random;

public class QuickSort<T extends Comparable<T>> {

    public static Random rand = new Random();

    /*
        Randomise pivot choice for large input arrays
     */
    public int randomPartition(T[] array, int p, int r) {
        int s = rand.nextInt(r-p) + p;
        swap(array, r, s);
        return partition(array, p, r);
    }

    public int partition(T[] array, int p, int r) {

        T pivot = array[r];
        int j = p;
        int i = p - 1;
        while (j < r) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
            j++;
        }
        swap(array, ++i, r);

        return i;
    }

    public void swap(T[] array, int m, int n) {
        T temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }

    public void sort(T[] array, int p, int r) {

        if (p < r) {
            // int q = partition(array, p, r);
            int q = randomPartition(array, p, r);
            sort(array, p, q - 1);
            sort(array, q + 1, r);
        }

    }

    public static void main(String... args) {

        Double[] numbers = {1.5, 8.4, 3.6, 9.9, 1.3, 3.4, 9.1, 5.1};
        QuickSort<Double> quickSort = new QuickSort<>();
        quickSort.sort(numbers, 0, numbers.length - 1);

        Arrays.asList(numbers).stream().forEach(System.out::println);

    }

}
