package searching;

public class MaximumSubArray {

    static class SubArray {

        int low = 0;
        int high = 0;
        double maxSum = 0.0;

        SubArray(int low, int high, double maxSum) {
            this.low = low;
            this.high = high;
            this.maxSum = maxSum;
        }

    }

    public SubArray findMaxSubArray(Double[] array, int low, int high) {

        if (high == low) {
            return new SubArray(low, high, array[low]);
        } else {
            int mid = (low + high) / 2;
            SubArray left = findMaxSubArray(array, low, mid);
            SubArray right = findMaxSubArray(array, mid + 1, high);
            SubArray crossing = findMaxCrossingSubArray(array, low, mid, high);
            if (left.maxSum >= right.maxSum && left.maxSum >= crossing.maxSum) {
                return left;
            } else if (right.maxSum >= left.maxSum && right.maxSum >= crossing.maxSum) {
                return right;
            } else {
                return crossing;
            }
        }

    }

    public SubArray findMaxCrossingSubArray(Double[] array, int low, int mid, int high) {

        double maxLeftSum = Double.NEGATIVE_INFINITY;
        double maxRightSum = Double.NEGATIVE_INFINITY;
        int maxLeftIndex = mid;
        int maxRightIndex = mid + 1;

        double sum = 0.0;
        for (int i = mid; i >= low; i--) {
            sum += array[i];
            if (sum > maxLeftSum) {
                maxLeftSum = sum;
                maxLeftIndex = i;
            }
        }

        sum = 0.0;
        for (int i = mid + 1; i <= high; i++) {
            sum += array[i];
            if (sum > maxRightSum) {
                maxRightSum = sum;
                maxRightIndex = i;
            }
        }

        return new SubArray(maxLeftIndex, maxRightIndex, maxLeftSum + maxRightSum);
    }

    public static void main(String... args) {

        MaximumSubArray findMax = new MaximumSubArray();
        Double[] array = {1.0, 7.0, 4.0, 4.3, -5.6, 3.2, 5.6, -1.2};
        SubArray maxSub = findMax.findMaxSubArray(array, 0, array.length - 1);
        System.out.println(maxSub.maxSum);

    }

}
