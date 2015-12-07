#ds-algo
Notes and examples made from learning about data structures, algorithms, and other Computer Science concepts. I have **bolded** what is especially important for me.

# Bit Manipulation
## Tricks
In Java, | is OR, & is AND, ^ is XOR, ~ is NOT, << is left shift
* x plus x = x times two = x << 1
* Multiplying x by 2 to the power of n just shifts x to the left by n
* x ^ (~x) = 1... 

ex that explains the elipsis:
111 ^ (~111) = 111

* x & (1 << n) clears the n rightmost bits of x

ex:
1111 & (1 << 3)
= 1111 & (1000)
= 1000

## Fundamental Facts
* Big Endian: MSB (most significant bit - the bit that represents the largest value) is the leftmost digit. Big Endian is the most common representation of bits. **Everything that I cover about Bit Manipulation in this readme will have Big Endianness.**

ex:
1000 in binary is 1 * 2^3 + 0 * 2^2 + 0 * 2^1 + 0 * 2^0 = 8 in decimal

* Little Endian: MSB is the rightmost digit
ex:
0001 in binary is 0 * 2^0 + 0 * 2^1 + 0 * 2^2 + 1 * 2^3 = 8 in decimal

* A sequence of ones or zeros is commonly denoted with an s postfix as such: 1s and 0s
* x ^ 0 = x  ,  x & 0 = 0 , x + 0 = x
* **x ^ 1s = ~x , x & 1s = x , x + 1s = 1s    Here 1s has an equal length of bits as x but all of them are 1**
* x ^ x = 0 , x & x = x , x + x = x
* Note that when doing Bit Manipulation problems in Java, you can return a boolean by returning `1 != 0` for `true` or `0 != 0` for `false` 
* Typically you make a mask and then use that mask with a bitwise operation to do something to a number
* In Java I sould use Integer.parseInt(binaryString, 2) to make the decimal equivalent of a binary number
* Use ~ to construct a clearBit bitmask. See clear bit for more info
* **-1 = 1s. In Java, if you do `-1 >>> 31` then it will give you `1`. This is because an int has 32 bits so -1 is actually a binary string of length 32 with all ones**
* **Turns out that ~1s = 0s in Java after all. Ex: ~111 = 000 but if you actually print out the 0s it's 0 instead of 000.**
* **num = num |  Integer.parseInt(value); // Only sets if value is 1 because x | 0 = x**

### Get Bit
```java
public static boolean getBit(int number, int i) {
	return (number & (1 << i)) != 0;
}
```

### Set Bit
```java
public static int setBit(int number, int i) {
	return number | (1 << i);
}
```

## Clear bit
x & 0 = 0 but I don't want to clear all the bits, I only want to clear one
x & 1101 = x(index 4)x(index3)0x(index 1) this works but how do I construct 1101? I can do that by using `Integer.parseInt("1101", 2) but that isn't really using bitwise because I'm also using the Integer class. Instead of going down that route we can see that the 1101 is really ~0010. More simply, ~(1 << 1). Now we can use this to clear a bit.

```java
public static int clearBit(int number, int i) {
	return number & (~( 1 << i));
}
```

clearBitsMsbToIthBitInclusively(11110, 3) = 00010
To get this, I need the mask 00011 to AND with 11110.
It happens to be that 00011 is 00100 - 1 where 00100 has the ith (3-th) bit equal to 1.

```java
public static int clearBitsMsbToIthBitInclusively(int number, int i) {
	return number & ((1 << i) - 1);
}
```

```java
public static int clearBits0ToIthInclusive(int num, int i) {
	return num & (~(-1 >>> (31- i)));
}
```

**These next ones are tricky because you clear a position first and then optionally set that position**

```java
public static int updateBit(int num, int i, boolean value) {
	int valueAtIthBit = num & (1 << i);
	num = num & ~valueAtIthBit;
	num = num |  Integer.parseInt(value); // Only sets if value is 1 because x | 0 = x
	return num;
}
```

```java
public static int updateBitsFromLsbToIthBit(int num, int i, int value) {
	int valueAtIthBit = num & (1 << i);
	num = num & ~valueAtIthBit;
	num = num | value; // Only sets if value is >= 1 because x | 0 = x
	return num;
}
```

#TODO
* Rewrite this in Latex