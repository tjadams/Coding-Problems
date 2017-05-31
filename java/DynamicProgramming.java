class ContiguousSum {
    int maxSum;
    int startIndex;
    int endIndex;

    public ContiguousSum(int maxSum, int startIndex, int endIndex) {
        this.maxSum = maxSum;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
}

public class DynamicProgramming {
    //TODO: do the O(n) non-brute force solution

    public static ContiguousSum findMaxContiguousSum(int[] array) {
        //Brute force approach: O(n^2)

        int maxSum = Integer.MIN_VALUE;
        int startIndex = -1;
        int endIndex = -1;
        for (int i = 1; i < array.length+1; i++) {
            for (int j = 0; j < i; j++) {
                int currentSum = sum(array, j, i);
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    startIndex = j;
                    endIndex = i-1;
                }
            }
        }

        return new ContiguousSum(maxSum, startIndex, endIndex);
    }

    public static int sum(int[] array, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        return sum;
    }

    public static void main (String args[]) {
        int[] array = {1, 2, -5, 4, 7, -2};
        ContiguousSum contiguousSum = findMaxContiguousSum(array);
        System.out.println("maxSum: " +contiguousSum.maxSum +" sI: " + contiguousSum.startIndex + " eI:" + contiguousSum.endIndex);

        int[] array2 = {1, 5,-3,4,-2,1};
        contiguousSum = findMaxContiguousSum(array2);
        System.out.println("maxSum: " +contiguousSum.maxSum +" sI: " + contiguousSum.startIndex + " eI:" + contiguousSum.endIndex);

    }
}
