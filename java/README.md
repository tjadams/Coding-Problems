#java
Notes and examples made from doing data structures and algorithms problems in Java. This readme contains some of the more tricky things about Java. My knowledge of the fundamentals of Java is pretty strong so I'm likely not going to be including things that I think are fundamentals. I have **bolded** what is especially important for me.

## Misc
* Can't define a method inside a method in Java
* Remember that void is a return type
* Remember that static is for non-instance methods
* Static initializaer a.k.a. class initializer is a Static{} block that is run before the constructor
* Remember that a static method can only call a static method. However, a non-static method can call a static or non-static method

## Constructors
```java
class Constructa {
	Constructa() {
		System.out.println("Constructed");
	}
}
```

* have no return type
* declared by name of class
* made public for everyone else to access
* private for singletons

## Usage of "this" in Java
* "this" is used when method parameter is the same name as instance variable
```java
public nonStaticMethod(int x, int y) {
	this.x = x;
	this.y = y;
}
```
* "this" is used when calling an alternate non-static method with the same name
```java
public nonStaticMethod(int a, int b, int c) {
	this(a, b, c, "sup)
}
```

## Class organization
### Access modifiers visible within X
Modifier              == Class = Package = Subclass = Everyone
==============================================================
Public                ==   X        X          X         X
Protected             ==   X        X          X
Default (no modifier) ==   X        X
Private               ==   X

Why have access modifiers? 
* Keeps implementation separate from interface
* Clarifies how others should use your class

### Package
Container of classes
### Subclass
A class is a subclass of another class if the first class extends the second

## Primitive data types
Don't need the "new" keyword.
* byte (8 bit)
* boolean 
* short (16 bit int)
* int (32 bit)
* long (64 bit int)
* float (32 bit floating point number)
* double (64 bit floating point number)

## Integer representations
Hexadecimal integers: int hex = 0x1a; 
Binary integers: int bin = 0b1011;

## Integer parsing
Integer.parseInt(String integer, int radix); 
radix = 2 is binary
radix = 10 is decimal

## Convert integer to another base
Integer.toString(numberToConvert, new base); 

## Initializing a value to an extreme
int minimum = Integer.MAX_VALUE;

## Array initializing
int[] array = new int[10];
int[] array = {1, 2, 3};
int[][] array = new int[10][10];
int[][] array = {{1, 2, 3}, {4, 5, 6}};
array[0].length = 10
array.length = 10 or 100??

## Interface
Contract that says you will write a method in your class that does X. An interface can also have variables in it. Can't have static methods in interfaces. Can't have any implementation in interfaces.

## Exception
Event that occurs when something unexpected happens

## Pass by reference and pass by value in Java
You can make an object (an instance of this class) and then pass that object to another method. In that method, so long as you mutate the object by using methods on it or just changing its fields, that object will be changed and those changes will be seen from anywhere that had that original object. The reason for this is because the object is acting as a reference to fields that are just data. Since the reference elsewhere is the same as the reference in the method that was passed the object, you can access the newly changed data.

Ex:

```java
class Dice {
	static int side; // 1 through 6 (should use an enumeration but this is just a quick example)
}
Dice dice = new Dice(1);
throw(dice); // say it just does `dice.side = 2;`
System.out.println(dice.side)  // 2
```

Other misc pass by ref/value notes:
* object1 == object 2 compares the references (pointers) and not the values stored in those objects
* object 2 = object 1 updates the reference of object2 with the refeerence of object1. If object1 is modified, object2 is also modified.

## What is a good program?
* clean a.k.a, program has only what it needs
* easy to read and understand
* works efficiently (fast with low memory usage)
* no errors
* easy to modify/extend

## Why do we use methods in programming?
* So you only have to write code once
* So a block of code can be used in the future in different ways
* So code can be used like a black box. I.e., code can be used without understanding how it works

#Common APIs
## java.lang.Math
* Math.max(number1, number2);, Math.min(number1, number2);
* Math.pow(number a, number b); returns a to the power of b
* Math.sqrt(number);

## java.util.random
```java
Random seed = new Random();
int abc = seed.nextInt(100); // returns a random int from 0-99
```

## java.lang.string
Note that Strings are immutable.
String s = "yo";
**When comparing two strings, use String.equals. Ex: a.equals(b)**

* s.indexOf(char c); returns the **first index** of c in s. Ex if c = 'o', this returns 1
* s.indexOf(char c, int i); returns the **first index** that c is seen in s starting at i and if not at i then continuing through the string
* s.indexOf(String b);
* s.indexOf(String b, int i);
* s.lastIndexOf with same parameters as the above
* s.replace(char a, char b); replaces all instances of a in s with b
* s.replace(String a, String b);
* s.substring(int start, int end); returns the string between and including index start and excluding index end 
* s.toLowerCase(); / UpperCase();

### String Tokenizer
StringTokenizer st = new StringTokenizer(string, delimitersString);
Each character in delimiters is a seperate delimiter.
while(st.hasMoreTokens() {
	st.nextToken();
}

### java.lang.StringBuilder
Using a StringBuilder is more efficient than doing String +=. This is because SB creates Strings in a mutable way. Strings which create more Strings by adding immutable Strings.There is another version called StringBuffer. One is threadsafe and the other is not. There are methods in StringBuilder that allow you to modify the String that is currently being built inside the StringBuilder.

```java
StringBuilder sb = new StringBuilder("string");
String s = sb.toString(); // s = "string"
```

## java.util.*
Map.Entry<K, V> - key value pair
ex: Map.Entry<Integer, String> map = new Map.Entry<Integer, String>(key, value); where the key is an Integer and the String is the value

### Queue
You can initialize a Queue using a LinkedList. Ex: Queue<E> queue = new LinkedList<E>();. You can also make your own Queue by implementing the Queue interface and creating the methods your own way. Note that you would have to implement all functions in the interfaces that Queue implements from.

### Stack
You can initialize a Stack using a LinkedList. Ex: Stack<E> stack = new LinkedList<E>();

### ArrayList
Resizable array implementation of a list. Allows null elements inside it. Basically the same as java.util.vector but ArrayList isn't synchronized (thread-safe) like Vector is. When you add an element and the internal array is at capacity, the rest of the array has to be resized. This could have a large negative effect on the runtime of a program.

ArrayList<E> list = new ArrayList<E>(); Where E is any Object

* list.add(E e); // appends e to end of list
* list.add(int i, E e); // append to index i
* list.clear(); // removes all elements from list
* list.size();
* list.clone(); // returns a new instance of ArrayList<E> with the elements of list in the same order
* list.indexOf... lastIndexOf()...
* list.remove(int index); // removes and shifts some elements of array to the left
* list.set(int index, E element);
* list.get(int index); returns element of type E
* list.sublist(int fromIndex, int toIndex); // returns a list (not an ArrayList) containing elements from index fromIndex to index toIndex. You can create a new ArrayList(List<E>) to get another ArrayList

#Interfaces
Class Y implements interface X means Y or a subclass of Y must provide an implementation for the methods defined in the interface X. Ift hey do not, the code will not compile.

#Switch statement
Switch works on the following types: int, Integer, char, Character, short, Short, byte, Byte, String. Switch statement is typically faster than if/else because Java iterates through the cases instead of evaluating the statements every time like in if statements.

```java
		String x = "amigos";
        switch (x) {
            case "hola":
                System.out.println(x);
                break;
            case "amigos":
                System.out.println(x);
                break;
            default:
                System.out.println("default");
                break;
        }
```

# File Input
```java
File f = new File("example.txt");
try {
	FileReader fr = new FileReader(file);
	BufferedReader br = new BufferedReader(fr);
	String line = br.readLine();
	while (line != null) {
		line = br.readLine();
	}
	br.close();
	fr.close();
} catch (Exception e) {
	e.printStackTrace();
}
```

# File Output
```java
File f = new File("example.txt");
try {
	FileWriter fw = new FileWriter(file);
	PrintWriter pw = new PrintWriter(fw);
	pw.print("yeah");
	pw.println("yeah yeah");
	pw.close();
	fw.close();
} catch (Exception e) {
	e.printStackTrace();
}
```

#Standard Input
```java
Scanner sc = new Scanner(System.in);
String input = sc.next();
```

#Standard output
```java
System.out.println("yo"); 
System.out.print("yo");
```

#Print vs Println
Let's define a printcursor as the area of System.out that you can currently print at.
Print just prints to the current printcursor index.
Println prints to the current printcursor index and then puts a newline. A subsequent print will print to the new printcursor index which is right after the newline.

# Printing a newline
Don't use `\n` because that is a specific newline character to only some hardware. Instead, use `System.out.format` and `%n` which gets the correct newline for your hardware.

# Sorting by customizing the comparson between 2 elements of an array to be sorted
```java
static Comparator<Integer[]> firstElementSubarrayComparator = new Comparator<Integer[]>() {
        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            // Return the standard compareTo for integers but compare on the first element of the subarray
            // return o1[0].compareTo(o2[0]);
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


Arrays.sort(array, firstElementSubarrayComparator);
```

#Unit Tests using JUnit
```java
public class myTests {
	@Test
	public void testName() {
	    assertEquals(testNameString, resultShouldBe, resultActuallyIs);
	}
}
```

## Character ascii value manipulation
### Addition/subtractio
```java
println('A' - 'A') //0
'A' - 'B' // 1
'B' - 'A' // -1
(int)'A' // 65
(char)65 // A
'1' - '0' // 1
'A' - '0' // 65 - 48 = 17
```
### Transforming numeric char to int
char a = '1'
int b = a - '0' // 1

### Transforming a numeric int to a numeric char
int a = 1
char b = (char)(a + '0') = (char)(49) = '1'

Or you could equivalentally do something like
String s = "" + a
or maybe even
char c = '' + a

### Is a character a number?
```java
public static boolean isNumeric (char a) {
	return a >= '0' && a <= '9';
}
```

### Is a character a letter?
```java
public static boolean isLetter (char a) {
	return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z'); // isLowercase or isUppercase
}
```

## Enums
```java
public enum Company {
	GOOGLE(15), MICROSOFT(20), AMAZON(70), FACEBOOK(13);
	private int value;
	public Company(int value) { this.value = value; }
	public int getValue() { return value; }
	public static Company getCompanyFromValue (int value) { ... }
}

public class UseCompany {
	public static void main (String args[]) {
		for (Company company : Company.values()) {
			System.out.println("Company " + company + " has value " + company.value);
		}
	}
}

```

## TODO
* Think about switching from Markdown to Latex
* Implement common data structures and algorithms in Java (ex: Binary Tree, etc)