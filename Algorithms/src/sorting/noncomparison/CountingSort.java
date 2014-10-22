package sorting.noncomparison;

import java.util.Arrays;

public class CountingSort {

    public Integer[] sort(Integer[] A, int k) {

        Integer[] C = new Integer[k];
        Integer[] B = new Integer[A.length];

        for (int i = 0; i < k; i++)
            C[i] = 0;

        for (int i = 0; i < A.length; i++)
            C[A[i] - 1]++;

        for (int i = 0; i < k - 1; i++)
            C[i+1] += C[i];

        for (int i = A.length - 1; i >= 0; i--) {
            B[C[A[i] - 1] - 1] = A[i];
            C[A[i] - 1]--;
        }

        return B;
    }

    public static void main(String... args) {

        Integer[] numbers = {8, 4, 6, 3, 9, 1 ,3, 7, 6, 3, 5, 2, 2};
        CountingSort countingSort = new CountingSort();
        Integer[] sorted = countingSort.sort(numbers, 9);

        Arrays.asList(sorted).stream().forEach(System.out::println);

    }

}
