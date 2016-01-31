public class BitShiftingAndSignExtension {
    public static void main (String args[]) {
        System.out.println("Normal examples: ");
        System.out.println("0001 << 3 = " + (Integer.toBinaryString(Integer.parseInt("0001", 2) << 3))+". Expected 1000");
//        System.out.println("0001 <<< 3 = " + (Integer.toBinaryString(Integer.parseInt("0001", 2) <<< 3) +". Expected 1000"); // Note: <<< doesn't exist in Java because it's the same as <<.

        System.out.println("1100 >> 1 = " + (Integer.toBinaryString(Integer.parseInt("1100", 2) >> 1)) + ". Expected 0110 because 1100 represents a positive integer. At first I expected 1010. However, this is wrong because if this were a 2's complement number, the sign extension of the rest of the bits in this 32 bit int would be 1, not 0.");
        System.out.println("1100 >>> 1 = " + (Integer.toBinaryString(Integer.parseInt("1100", 2) >>> 1)) + ". Expected 0110");

        System.out.println("Arithmetic shifting a negative number example:");
        System.out.println("-4 >> 1 = " + (Integer.toBinaryString(-4) + " >> 1 = "+ Integer.toBinaryString(-4 >> 1)));

        System.out.println("Shifting past MSB examples: ");
        System.out.println("0001 << 4 = " + (Integer.toBinaryString(Integer.parseInt("0001", 2) << 4)) +". Expected 0000 but got 10000. Remember to mask to a 4 bit representation by anding your result afterwards. This would get you 0000.");
//        System.out.println("0001 <<< 4 = " + (Integer.toBinaryString(Integer.parseInt("0001", 2) <<< 4)) +". Expected 0000"); // Note: <<< doesn't exist in Java because it's the same as <<.

        System.out.println("Shifting past LSB examples: ");
        System.out.println("1100 >> 4 = " + (Integer.toBinaryString(Integer.parseInt("1100", 2) >> 4)) +". Expected 0000");
        System.out.println("1100 >>> 4 = " + (Integer.toBinaryString(Integer.parseInt("1100", 2) >>> 4)) +". Expected 0000");

        System.out.println("Old examples: ");
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
