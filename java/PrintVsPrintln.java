public class PrintVsPrintln {
    public static void main (String[] args) {
        // I used to think this printed the following:
        // "Line 1: Not appended to Line 1, but rather prepended to Line 2.
        // Line 2: I wonder if someone will ever read this repository"
        // Turns out it doesn't.
        /*
        System.out.println("Line 1:");
        System.out.print("Not appended to Line 1, but rather prepended to Line 2.");
        System.out.println("Line 2: I wonder if someone will ever read this repository");

        // Here's an example of 3 prints, a newline, and a print
        System.out.println();
        System.out.print("1");
        System.out.print("2");
        System.out.println("3");
        System.out.print("4");

        System.out.println();
        System.out.print("So it's time to evolve");
        */
        // Note: don't use \n because that is specifically a newline character which is some specific unicode char.
        // Instead, use %n with System.out.format whichwill be the correct newline character for your platform.
        /*
        System.out.format("%nI can still have full control over my output %n" +
                         "without having to memorize another quirky thing about Java.%n" +
                         "That quirky thing is the difference between print and println.");
                         */
        System.out.format(String.format("test%n1%n2ayy"));
    }
}
