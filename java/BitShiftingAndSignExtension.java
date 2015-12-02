public class BitShiftingAndSignExtension {
    public static void main (String args[]) {
        // Looks like >> and >>> are equivalent if the input string is positive
        System.out.println(">> vs >>> :\n>> = " + Integer.toBinaryString(Integer.parseInt("1111", 2) >> 5) + "\n" +
                           ">>> = " + Integer.toBinaryString(Integer.parseInt("1111", 2) >>> 5));

        // >> and >>> are different if the input string is negative.
        // >>> will work as I want it to for sign extended 2's complemented Bit Manipulation tricks. >> will not.
        System.out.println(">> vs >>> :\n>> = " + Integer.toBinaryString(Integer.parseInt("-1", 2) >> 31) + "\n" +
                           ">>> = " + Integer.toBinaryString(Integer.parseInt("-1", 2) >>> 31));

        // Testing negation on a String of ones. I kinda expected 32 zeroes but it just shrunk to one zero.
        System.out.println("Test #0 of negation: " + Integer.toBinaryString(~Integer.parseInt("-1", 2)));
        // Turns out that ~1s = 0s after all. Ex: ~111 = 000 but if you actually print out the 0s it's 0 instead of 000.
        System.out.println("Test #1 of negation: " +
                           Integer.toBinaryString(clearBits0ToIthBitInclusive(Integer.parseInt("1111", 2), 2)) +
                           " should be 1000");

        System.out.println("Test #0 of >>> : -1 >>> 1 = " + Integer.toBinaryString(-1 >>> 1));
        System.out.println("Test #1 of >>> : -1 >>> 31 = " + Integer.toBinaryString(-1 >>> 31));
        // Can't go over 31 because it just loops back to the beginning of -1
//        System.out.println("Test #2 of >>> : -1 >>> 32 = " + Integer.toBinaryString(-1 >>> 32));
//        System.out.println("Test #3 of >>> : -1 >>> 33 = " + Integer.toBinaryString(-1 >>> 33));
        System.out.println("Test #0 of >> : -1 >> 2 = " + Integer.toBinaryString(-1 >> 2));
        System.out.println("Test #1 of >> : -1 >> 4 =" + Integer.toBinaryString(-1 >> 4));
    }

    public static int clearBits0ToIthBitInclusive(int num, int i) {
        int mask = ~(-1 >>> (31 - i));
        return num & mask;
    }
}
