import java.util.Arrays;
import java.util.Comparator;

public class SortByComparingColumns {
    static Comparator<Integer[]> firstElementSubarrayComparator = new Comparator<Integer[]>() {
        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            // Return the standard compareTo for integers but compare on the first element of the subarray
//            return o1[0].compareTo(o2[0]);
            // Could equivalently do the following:
            if (o1[0] > o2[0]) {
                return 1;
            } else if (o1[0] < o2[0]) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    public static void main (String args[]) {
        Integer[][] array = new Integer[][] {{1,2}, {0,1}, {3, 4}};
        System.out.println("Sorting by first element:");
        sortByFirstElement(array);
        for (Integer[] inner : array) {
//            System.out.println(inner.toString()); // prints out the some id of the integer object, that's not what I want
            System.out.println(Arrays.toString(inner)); // I just want to print the array :)
        }
    }

    public static void sortByFirstElement(Integer[][] array) {
        Arrays.sort(array, firstElementSubarrayComparator);
    }
}
