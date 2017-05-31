import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class DataStructExamples {
    public static void main (String args[]) {
//        int[][] array = {{1, 2, 3}, {4, 5, 6}};
        int[][] array = new int[2][3];


        System.out.println((char)('0' + 5));

        System.out.println("Array.length: " + array.length + " " + array[0].length);
        System.out.println("string"+"otherstring");

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        queue.add(2);
        System.out.println(queue.peek() + " should be 1");
        queue.remove();
        System.out.println(queue.peek() + " should be 2");

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.peek() + " should be 2");
        stack.pop();
        System.out.println(stack.peek() + " should be 1");

        //ArrayList I know how to do
        //LinkedList java impl won't be asked, I'll make my own class
        //For heaps, I'll just assume one if asked. I could use priorityqueue but theres a lot to it
        //HashSet I finished in another document
        //HashMap i finished in another document
        // StringBuilder i finished in another document
    }
}
