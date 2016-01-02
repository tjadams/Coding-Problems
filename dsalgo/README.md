#ds-algo
Notes and examples made from learning about data structures, algorithms, and other Computer Science concepts. I have **bolded** what is especially important for me. Keep in mind that this is written from a Java point of view.

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
public static int spaceComplexityExOne(...) {
	// no variable declarations in function
	return spaceComplexityExOne(..., n -1 ), + spaceComplexityExOne(..., n- 2);
}

// Space complexity is O(2^n) from variables + O(2^n) from stack traces = 2*O(2^n) = O(2^n)
public static int spaceComplexityExTwo(...) {
	// constant amount of O(1) sized variable declarations in function
	return spaceComplexityExOne(..., n -1 ), + spaceComplexityExOne(..., n- 2);
}
```

## Analyzing Time Complexity
I'll do this for some tricky recursive problems.

```java
// Here I cut the array size in half each time so I do half the work each time
// If I were to draw out each call to timeComplexityExOne and the size of the array in each call,
// I would see that there were log base 2 of n calls where n = initial array size
public static int timeComplexityExOne(int[] array) {
	// O(1) statements
	return timeComplexityExOne(array cut in half) 
}
```

## Stable sorts
A stable sort is a function that sorts a collection but maintains the relative order of equal items. Ex: Here's an array before a stable sort in ascending order: [7, first 5, 2, second 5]. After the stable sort: [2, first 5, second 5, 7]. In the beginning, the first 5 was before the second 5. This remains true after the stable sort was performed. An unstable sort could result in [2, second 5, first 5, 7].

## Quicksort
#### Pseudocode
```java
public static ArrayList<Integer> Quicksort(ArrayList<Integer> list) {
	if (list.length <= 1) {
		return list;
	}
	Select a random value from list to pivot on;
	Create empty lists called left and right;
	for (Integer element : list excluding the pivot value index) {
		if (element <= pivotValue) 	append x to the left list;
		else append x to the right list;
	}
	return join(Quicksort(left), pivotValue, Quicksort(right));
}
```

#### Summary
1. Select a random pivot value from an input list
2. Sort input list excluding pivot into a left and right list according to pivot value
3. Return the concatonation of Quicksort(left), the pivot value, and Quicksort(right)

#### Algorithmic analysis
Average case time complexity is  O(n log n). Worst case time complexity is O(n^2). This occurs when you pick the worst pivot every time. When that happens, you have to compare your pivot with every other element. As you keep going you do n -= 1 comparisons each time. So it comes out to n^2. The space complexity depends on how you implement the problem. If you did it in-place then the worst case space complexity will be O(log n) because you call Quicksort(left) log n times and Quicksort(right) log n times. Recall that 2O(log n) is O(log n).

#### Advantages
* Can be optimized to use O(log n) memory which is very little. This is done by quicksorting in-place

#### Disadvantages
* Time complexity relies on having a good pivot point during each recursive call
* Difficult to parralelize
* The most optimized version of Quicksort is not a stable sort

# Discrete Mathematics
Many arrays and strings problems likely use discrete math. Look for counting, permutations etc in a problem to identify this.

## Mergesort
#### Pseudocode
```java
public static int[] mergesort(int [] array) {
	if (array.length <=1) return array;
	int[] left = mergesort(left half of array);
	int[] right = mergesort(right half of array);
	return merge(left, right); // merge left and right arrays in sorted order
}
```

#### Summary
1. Recursively split up array into halves
2. Merge each half in sorted order

#### Algorithmic analysis
Time: T(n) = T(n/2) + T(n/2) + O(n) use master theorem and you get O(n log n) time.
Memory: You make n, n/2, n/4, n/8, n/16, ... additional memory per iteration. Since it's all the same values, the compiler may optimize this to be O(n) memory. Also, the merge algorithm won't use more than O(n) memory.

#### Advantages
- Always nlogn time
- Can be parralelized to run in O(n) time
- Stable sort

#### Disadvantages
- Uses lots of memory

## Arrays
#### How they work
An array can be implemented as a container that takes up a linear section in memory. You can't change the container itself but you can change the items in it.

#### Advantages
- Can access, insert within array size, remove elements in O(1) time
 
#### Disadvantages
- Only can hold one type
- Size is static

## Linked Lists
Focusing on singly linked-lists.

#### How they work
Each node of a linked list is a pointer to memory. Pointers that point to other pointers allow for a list of accessible elements called a Linked List.

#### Advantages
- Size is dynamic
- Insert/delete in O(1) time

#### Disadvantages
- Accessing an element takes O(n) time because you have to loop through the head of the LL
- Nodes must know about other nodes so they're not independant
- Hard to traverse backwards in a singly LL

#### Runner technique
Iterate through LL with 2 pointers. One pointer is ahead of the other. 
Usually one pointer iterates one element at a time and the other iterates two elements at a time.
This is useful for many LL problems where you want to weave elements together or determine the size of a list.

#### Example usages that work uniquely well with this data structure
- Making a better queue/stack
- Scavenger hunt
- Conga line

## Stacks
#### How they work
Last-in first-out container. So the most recent item put on the stack is the first one to be seen by the outside world.
Uses a top of stack pointer to an underlying array to expose that element to the outside world. Pushing (inserting) and popping (removing) from stack are done at top of stack only.
 
#### Advantages
- Push: Insert element at TOS in O(1) time
- Pop: Delete element at TOS in O(1) time
 
#### Disadvantages
- Must access top of stack element only. As a result:
	- If you want to search for an element you have to pop elements off the stack
	- If you want to remove a specific element, you have to search and pop so O(n) removal time
	- If you want to insert at a specific point in the stack, O(n) time due to searching and popping
- If using an underlying array, static size

#### Example usages that work uniquely well with this data structure
- Converting the base of a number
- Evaluating math expressions
- Recursion stuff like backtracking in a maze

## Queues
#### How they work
First-in first-out. So elements are added to the end of the underlying array and removed from the beginning of the underlying array. 
This is implemented circularly with an underlying array.
Uses one pointer for the beginning element array index of the queue, and another pointer for the last element array index of the queue. 

#### Advantages
- Enqueue: Insert element at end of queue in O(1) time if less than total underlying array size
- Dequeue: Remove element from beginning of queue in O(1) time 
- Can be made to have a dynamic container size by resizing an underlying array or using an underlying linked list

#### Disadvantages
- Can only remove elements from the beginning of the queue

#### Example usages that work uniquely well with this data structure
- Giving things priority
- Lining up for a store

## Hash Tables
#### How they work
A hash table maps keys to values using a hash function on an underlying array. It has a  capacity c and n total items.
A hash function is a function that accepts a value and maps the index of that value to the hash table.
A **collision** occurs when a hash function maps a value to an index that already has a value.
Good hash functions collide infrequently.

###### Resolving collisions with Chaining
Each index of the hash table holds a linked list of values. When a collision occurs, the value to be added is added to the end of the linked list.
Advantage: This is quick with Insert = O(1), Delete = O(h(k) + alpha) for singly linked and O(1) for doubly-linked, Search = O(h(k) + alpha) where h(k) = hash function on value k and **alpha = load factor of hash table = n/c**
Disadvantage: Takes up more memory than other solutions. This is due to the linked lists.

###### Resolving collisions with Open Addressing - Linear Probing 
Modified hash function h(k, i) = (h(k) + i) mod c where i is a probe index i starts at 1 for every instance of a collision. h(k,i) is then tried again. If unsuccesful, i++ to 2 and now tries again. This process repeats until a successful location was found.
Advantage: Uses less memory than chaining
Disadvantage: slower than chaining

###### Resolving collisions with Open Addressing - Double Hashing which uses Linear Probing
h(k,i) = (h1(k) + i*h2(k)) mod c. Same as linear probing but now we use two hash functions.
Advantage: Distributes keys more uniformly than linear probing which results in less collisions than linear probing
Disadvantage: You need to come up with 2 good hash functions.

#### Advantages
- Search/insert/delete O(1) where search just looks for a value in a table

#### Disadvantages
- Sometimes uses way more memory than needed

#### Example usages that work uniquely well with this data structure
- Counting how many times you have seen some values in something. hashtable[h(some value)] = number of times you have seen it

## Tree & Graph definitions
**This is very important**
Leaves: nodes with no children
Siblings: nodes with same parent
Root: node with no parent. This is the top-most node of the tree. Also the starting point for iteration in the tree data structure
Path: sequence of nodes n_1, n_2, ..., n_k such that n_i is the parent of n_i+1 for 1 <= i < k
Length: number of edges on a path
Depth of a tree node: is the length of the unique path from the root to that node. "How deep is that node?" Root has a depth of 0. Depth of tree is different from depth of root i.e., "How deep does the tree go?" vs. "How deep is the root from the root? 0"
Height of a tree node: is the length of the longest unique path from that tree node to a leaf. All leaves have a high of 0."How high is that node?"
Height of a tree = Depth of a tree = Max Height = Max Depth: is the length of the longest unique path from the root to a leaf node.  "How high is the tree?"
Traversal: visiting every node once

## Binary Trees
**These are different than Binary Search Trees. BSTs extend the functionality of BTs**
#### How they work
Binary Trees are trees with each node having a max of 2 children.

## Binary Tree Traversal Algorithms
### Pre-order Traversal
#### Pseudocode
```java
public static void preorder(Node node) {
	if (node == null) return;
	visit(node);
	if (node.left != null) preorder(node.left);
	if (node.right != null) preorder(node.right);
}
```

#### Summary
Visit node, preorder(left), preorder(right).
Generates a prefix expression of nodes in a tree.

#### Example
Tree that isn't being rendered properly in markdown lol:
```
    +
  +   *
 a b c d
```

Calling preorder(+) with + being the root of the tree would generate this prefix expression: ++ab*cd

#### Algorithmic analysis
Time: Visits each node once so it's O(n)
Memory: **You could count the stack frames due to recursion but that's probably optimized by the compiler.** As a result, O(1)

#### Advantages over other traversals

#### Disadvantages over other traversals

#### Example usages that work uniquely well with this algorithm

# Contributing
## Algorithm documentation skeleton
### Algorithm name
#### Pseudocode
```java
public static ? ?(???) {
	
}
```
#### Summary
#### Example
#### Algorithmic analysis
Time:
Memory:
#### Advantages over other traversals
#### Disadvantages over other traversals
#### Example usages that work uniquely well with this algorithm

## Data Structure documentation skeleton
### Data Structure name
#### How they work
#### Advantages
#### Disadvantages
#### Example usages that work uniquely well with this data structure

#TODO
* Make this cleaner
* Split up data structures and algorithms into separate files where appropriate
* Rewrite this in Latex