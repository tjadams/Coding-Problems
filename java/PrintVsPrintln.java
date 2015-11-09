public class PrintVsPrintln {
    public static void main (String[] args) {
        // I used to think this printed the following:
        // "Line 1: Not appended to Line 1, but rather prepended to Line 2.
        // Line 2: I wonder if someone will ever read this repository"
        // Turns out it doesn't.
        System.out.println("Line 1:");
        System.out.print(" Not appended to Line 1, but rather prepended to Line 2. ");
        System.out.println("Line 2: I wonder if someone will ever read this repository");

        System.out.print("So it's time to evolve");
        System.out.print("\nI can still have full control over my output \n" +
                         "without having to memorize another quirky thing about Java.\n" +
                         "That quirky thing is the difference between print and println.");
    }
}
