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

##Object Oriented Design
Step 1: Clarify the problem

* Who is going to use the system?
* How will the system be used?
* 6 Ws: who, what, when, where, why, how

Step 2: Define the objects in the system you're designing
Step 3: Analyze relationships between objects

* Which objects are members of which other objects?
* Do any objects inherit from each other?
* Are relationships many-to-many or one-to-many? 
Ex of a many-to-many relationships betwen an Author class and a Book class: an Author can write many Books and a Book can be written by many Authors.
Ex of a one-to-many relationsip between a Mother class and a Children class. one Mother object to many Children objects)
* Ask how specific or general my design should be?

Step 4: Investigate the system's behaviour using stories to see if I forgot anything.
ex: For a restaurant system: a party walks in to the restaurant and the guest requests a table etc...

## Design Patterns
### Singleton class
A Singleton class is a class that only has one instance. The instance of that class is accessed using the class, not an object because that would be another instance. Using a Singleton class is useful when you have a "global" object that only has one instance.
Ex: Earth, Krypton, Restaurant

Ex in Java:

```java
public class Restaurant {
	private static Restaurant instance = null;
	Restaurant() { ... }
	public static Restaurant getInstance() {
		if (instance == null) {
			instance = new Restaurant();
		}
		return instance;
	}
}
```

### Factory method
A Factory method is one way to create an instance of a class. One useful way to use a Factory method is to pass in the type of instance you want to instantiate. Typically, the type of instance is a subclass of a class like InstanceType.
Ex in Java:

```java
public static CardGame createCardGame (GameType type) {
	if (type == GameType.BlackJack) {
		return new BlackJack();
	}
}
```

## Recursion
Note that all recursive code can be implemented iteratively but it can be very complex.

### Bottom-up
1. Solve a simple case like n=1
2. Try to get f(2) from f(1)
3. Repeat this to get a solution for all cases

### Top-down
Think of how to divide the problem at case N into subproblems

## Dynamic Programming
DP problems are recursive problems where you store intermediate results.
1. Implement recursive solution
2. Try to cache results
3. Use cache results in recursive solution if they are available to use
4. If they are not available to use, calculate and cache new result

## Analyzing Space Complexity a.k.a Auxillary Memory
Space Complexity/Auxillary Memory is the "extra space" used as a result of calling a function. This is mainly tricky to analyze in recursive problems. Here's a formula that helps with analyzing.
**Space Complexity of a recursive problem = (space per stack frame)*(# of stack frames which in most cases is the amount of times the function is recursively called)**

```java
public class recursiveProblemHelper {
	int[] fresult;
	public static void main (String args[]) {
		int n = 100;
		fresult = new int[n];
		optimizedDpProblem(fresult, n);
	}

	// This is optimized because it keeps track of memory which reduces auxillary memory. 
	// Just suppose that this algorithm works like the optimized solution to fibonacci.
	// Space Complexity: It uses O(n) memory in the helper class due to fresult
	// Time Complexity: Each fresult is only computed once. It takes O(1) to compute one entry in fresult. N entries in fresult therefore the time complexity is O(1)*n = O(n)
	public static int optimizedDpProblem(int[] input, int n) {
		if (baseCase) {
			fresult[...] = input[...].......;
			return fresult[...];
		}
		
		if (fresult[n] != uninitializedConstant) {
			return fresult[n];
		} else {
			fresult[n] = optimizedDpProblem(input, n -1) + optimizedDpProblem(input, n - 2) + ... ;
			return fresult[n];
		}	
	}
}
```

```java
// Assume there is no memory being created in the algorithm and that there is no results being stored as you go.
// The time complexity here is O(2^n) assuming the ... takes O(1) time to run.
// The space complexity is (space per stack frame)*(# of stack frames) = O(1) * amount of times the function is recursively called = O(2^n)
// That O(1) multiplier is due to putting the result of each recursive computation on the stack.
public static int spaceComplexityExOne (...) {
	... (no variable declarations in function)
	return spaceComplexityExOne(..., n -1 ), + spaceComplexityExOne(..., n- 2);
}

// Space complexity is O(2^n) from variables + O(2^n) from stack traces = 2*O(2^n) = O(2^n)
public static int spaceComplexityExOne (...) {
	... (constant amount of O(1) sized variable declarations in function)
	return spaceComplexityExOne(..., n -1 ), + spaceComplexityExOne(..., n- 2);
}
```

#TODO
* Rewrite this in Latex