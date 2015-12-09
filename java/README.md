#java
Notes and examples made from doing data structures and algorithms problems in Java. This readme contains some of the more tricky things about Java. My knowledge of the fundamentals of Java is pretty strong so I'm likely not going to be including things that I think are fundamentals. I have **bolded** what is especially important for me.

## Pass by reference in Java
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

* s.indexOf(char c); returns the **first index** of c in s. Ex if c = 'o', this returns 1
* s.indexOf(char c, int i); returns the **first index** that c is seen in s starting at i and if not at i then continuing through the string
* s.indexOf(String b);
* s.indexOf(String b, int i);
* s.lastIndexOf with same parameters as the above
* s.replace(char a, char b); replaces all instances of a in s with b
* s.replace(String a, String b);
* s.substring(int start, int end); returns the string between and including index start and excluding index end 
* s.toLowerCase(); / UpperCase();

## java.lang.StringBuilder
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

## TODO
* Think about switching from Markdown to Latex