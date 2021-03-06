import java.io.PrintWriter;
import java.util.Scanner;

public class StandardInputOutput {
    public static void main (String args[]) {
        standardInput();
        standardOutput();
    }

    private static void standardInput() {
        // Note: Scanner is very powerful. It has hasNext and next methods for anything I could ever want.
        // For example, hasNextBigInteger(), next() for the whole String, next(String pattern) for regex
        Scanner sc = new Scanner(System.in);

        // For continuous input from an InputStream (until it ends).
        // Note: This is used in HackerRank etc
        /*
        while (sc.hasNext()) {
            System.out.println(sc.next());
        }
        */

        // For continous input that stops when a certain phrase is entered
        String input = sc.next();
        // Note: don't forget that you need to compare Strings with .equals instead of !=
        while(input != null && !input.equals("stop")) {
            input = sc.next();
        }
    }

    private static void standardOutput() {
        System.out.println("yo");
        System.out.print("yo");
    }
}
