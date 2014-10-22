package sorting.comparison;

import java.util.Arrays;

public class InsertionSort<T extends Comparable<T>> {

    public void sort(T[] array) {

        for (int i = 1; i < array.length; i++) {
            T key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
        }

    }

    public static void main(String... args) {

        Double[] numbers = {0.5, 1.889, 0.14, 0.43};
        InsertionSort<Double> insertionSort = new InsertionSort<>();
        insertionSort.sort(numbers);

        Arrays.asList(numbers).stream().forEach(System.out::println);

    }

}
