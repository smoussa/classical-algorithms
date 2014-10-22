package sorting.comparison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public void sort(Double[] array, int i, int n) {

        if (i < n) {
            int m = (i+n)/2;
            sort(array, i, m);
            sort(array, m+1, n);
            merge(array, i, m, n);
        }

    }

    public void merge(Double[] array, int p, int q, int r) {

        List<Double> left = new ArrayList<>();
        List<Double> right = new ArrayList<>();

        for (int i = p; i <= q; i++) { left.add(array[i]); }
        for (int i = q+1; i <= r; i++) { right.add(array[i]); }

        left.add(Double.POSITIVE_INFINITY);
        right.add(Double.POSITIVE_INFINITY);

        int n = 0, m = 0;
        for (int i = p; i <= r; i++) {
            Double leftElement = left.get(n);
            Double rightElement = right.get(m);
            if (leftElement < rightElement) {
                array[i] = leftElement;
                n++;
            } else {
                array[i] = rightElement;
                m++;
            }
        }

    }

    public static void main(String... args) {

        MergeSort mergeSort = new MergeSort();
        Double[] numbers = {1.0, 7.0, 4.0, 4.3, 5.6, 3.2, 5.6, 1.2};
        mergeSort.sort(numbers, 0, numbers.length - 1);
        Arrays.asList(numbers).stream().forEach(e -> System.out.println(e));

    }

}
