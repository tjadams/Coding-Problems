#ds-algo
Notes and examples made from learning about data structures, algorithms, and other Computer Science concepts. I have **bolded** what is especially important for me. Keep in mind that this is written from a Java point of view.

### Linear search
#### Pseudocode
```java
public static boolean linearSearch(int[] array, int value) {
	for (int i = 0; i < array.length; i++) {
		if (array[i] == value) return true;
	}
	return false;
}
```
#### Summary
Look through every value in a collection for a desired value. If after looking through the collection you still haven't found the value, then the value doesn't exist in the collection. I could instead return the index of the array at which the value is present as an alternative to returning a boolean.
#### Example
array = [1 2 3 4 5]
value = 6
returns false

value = 5
returns true
#### Algorithmic analysis
Time: O(n)
Memory: O(1)
#### Advantages
Very simple.
#### Disadvantages
This is the slowest search you can perform on a collection such as an array.
#### Example usages that work uniquely well with this algorithm
Looking for anything. However be aware of other searches and you should likely use them instead.

### Binary Search
#### Pseudocode (recursive)
```java
public static boolean binarySearch(int[] array, int value) {
	return binarySearch(array, 0, array.length - 1, value);
}

public static boolean binarySearch(int[] array, int startIndex, int endIndex, int value) {
	if(startIndex > endIndex) {
		return false;
	}

	int middle = (startIndex + endIndex)/2;
	if (array[middle] == value) {
		return true;
	} else if (array[middle] > value) {
  		return binarySearch(array, startIndex, middle - 1, value);
	} else if (array[middle] < value) {
		return binarySearch(array, middle + 1, endIndex, value);
	}
}
```
#### Summary
Given a sorted array, a binary search can be used to look for a value.
#### Example (not found) 
binarySearch(array = [1 6 15 22 90 112], value = 7)
start = 0
end = 5
middle = 2
arr[2] =15. 15 >7 so we go lower
binarySearch(array, 0, 1, 7);
start = 0
end = 1
middle = 0
arr[0] =  1 < 7 so we go higher
binarySearch(array, 1, 1, 7)
start = 1
end = 1
middle = 1
arr[1] = 6 < 7 so we go higher
binarySearch(array,2, 1, 7)
start = 2
end = 1
start > end return false

#### Example (going lower)
binarySearch(array = [1 6 15 22 90 112], value = 6)
startIndex = 0
endIndex = 5
middle =  2
array[middle] = a[2] = 15. 15 > 6 so we go lower
binarysearch(array, 0, 1, 6);
start = 0
end = 1
middle = 0
array[0] = 1 < 6 so we go higher
binarySearch(array, 1, 1, 6)
start = 1
end = 1
middle = 1
array[1] = 6 return true

#### Example (going higher)
binarySearch(array = [1 6 15 22 90 112], value = 90)
startIndex = 0
endIndex = 5
middle =  2
array[middle] = a[2] = 15. 15 < 90 so we go higher
binarySearch(array, 2+1, 5, 90)
middle = 3 + 5 all over 2 = 4
array[4] = 90 return true; 

#### Algorithmic analysis
Time: O(logn)
Memory: O(1)
#### Advantages
Much faster than Linear Search
#### Disadvantages
Only works in situations where the input array is sorted.
#### Example usages that work uniquely well with this algorithm
- When you're searching and you know the input collection is sorted
- In a BST

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

### Arithmetic Shift vs Logical Shift vs Circular Shift
Arithmetic Shift is `binaryNum << x` and `binaryNum >> x`. Arithmetic Shift shifts all the bits in a binary number left or right by x bit positions. When arithmetic shifting to the right, instead of being filled with all 0s as in the logical shift, the original leftmost bit (sometimes called the signbit if you're using signed integers) is maintained. When arithmetic shifting to the left, the MSB dissapears, all other bits are shifted left, and the LSB becomes 0.

**Logical shift is often used when its operand (binary number) is being treated as a sequence of bits rather than as a number.** **<<< doesn't exist in Java because it's the same as <<.**Logical Shift is `binaryNum <<< x` and `binaryNum >>> x`. Logical Shift also shifts all the bits in a binary number left or right by x bit positions. When logical shifting to the right, 0s are filled in to the MSB and other bit positions following the MSB towards the right. When logical shifting to the left, 0s are filled in to the LSB and other bit positions following the LSB towards the left.

Circular shift a.k.a. bitwise rotation is exactly what it sounds like. If you shift past the left boundary, the bits that were shifted past the boundary reappear on the right boundary. Same with shifting past right but with the reappearance occurring at the left boundary. **Neither Arithmetic of Logical shifts are circular shifts.**

#### Normal examples
* `0001 << 3 = 1000`
* `1100 >> 1 = 0110`
* `1100 >>> 1 = 0110`

#### Shifting past MSB examples
* `0001 << 4 = 10000`
* `(0001 << 4) & 1111 = 0000`

#### Shifting past LSB examples
* `1100 >> 4 = 0000`
* `1100 >>> 4 = 0000`

### Endianness
#### Big Endian
MSB (most significant bit - the bit that represents the largest value) is the leftmost digit. Big Endian is the most common representation of bits. **Everything that I cover about Bit Manipulation in this readme will have Big Endianness.**

ex:
1000 in binary is 1 * 2^3 + 0 * 2^2 + 0 * 2^1 + 0 * 2^0 = 8 in decimal

ex:
Memory diagram for a 4 byte binary number MSB 0A0B0C0D LSB
0D at location alpha + 3  (highest address - LSB)
0C at location alpha + 2
0B at location alpha + 1
0A at location alpha      (lowest address - MSB)

#### Little Endian
MSB is the rightmost digit
ex: 
0001 in binary is 0 * 2^0 + 0 * 2^1 + 0 * 2^2 + 1 * 2^3 = 8 in decimal

ex:
Memory diagram for a 4 byte binary number MSB 0A0B0C0D LSB
0A at location alpha + 3  (highest address - MSB)
0B at location alpha + 2
0C at location alpha + 1
0D at location alpha      (lowest address - LSB)



### Two's Complement in Binary
If MSB is 0, everything is normal. MSB is the sign bit. Sign bit = 0 means the 2's comp number is positive. Sign bit = 1 means the 2's comp number is negative. To get the value of a negative 2's complement number, value = -(flip all the bits and add one).

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

### Summary
* & is typically used with a mask in order to get info about a binary number or a subset of a binary number.
* | is typically used to set, do nothing, or edit a binary number or a subset of a binary number
* Arithmetic vs logical shift: >> is signed, >>> is unsigned. If the integer is positive: >>> and >> shift a zero into the MSB due to sign extension. If the integer is negative: >>> shifts a zero into the MSB. >> shifts a one into the MSB due to sign extension. <<< and << are the same in Java.

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
Note that all recursive code can be implemented iteratively but it can be very complex. Generally, it's good to draw a recursion tree with the root being an example of the recursive problem on a specified value of n. Identify if work is being done multiple times (like other subproblems). You can also see that your base cases are leaf nodes that are all on the same nth value. Also note that drawing a recursion tree is a great way to determine the runtime of a recursive algorithm.

### Bottom-up
1. Solve a simple case like n=1
2. Try to get f(2) from f(1)
3. Repeat this to get a solution for all cases

### Top-down
Think of how to divide the problem at case N into subproblems

## Dynamic Programming and Memoization
DP problems are recursive problems where you store intermediate results. Some people call top-down dynamic programming "memoization" and bottom-up dynamic programming "dynamic programming". To me, these are both called dynamic programming.
### Basic steps to solving
1. Draw a recursion tree with the root being an example of the recursive problem on a specified value of n. Identify if work is being done multiple times (like other subproblems). You can also see that your base cases are leaf nodes that are all on the same nth value. Also note that drawing a recursion tree is a great way to determine the runtime of a recursive algorithm.
2. Implement recursive solution
3. Try to cache results
4. Use cache results in recursive solution if they are available to use
5. If they are not available to use, calculate and cache new result

### Detailed steps to solving
1. Show optimal substructure. Show that an optimal solution to the problem holds optimal solutions to subproblems. First, characterize what the solution may look like. Ex: Lowest common substring has the following properties...
2. Write a recurrence for the value of an optimal solution. That may look something like the following: M_optimal = minimum over all choices depending on the indece k from the following formula: something like M_optimal resulting from choice of k + cost associated with making choice indece k.
3. Compute the value of an optimal solution in a bottom-up way. This way, all subproblems are pre-computed.
4. Construct an optimal solution from the computed information.

### Examples from ECE 250 - Data Structures and Algorithms 

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
* Difficult to parralelize
* Not a stable sort
* Time complexity relies on having a good pivot point during each recursive call

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

## Quicksort vs Mergesort high-level overview
In Quicksort, all the real work happens in the "dividing step" where things are sorted into left and right arrays according to a pivot value. The "merge step" in Quicksort is not nearly as important because it simply concatenates. Mergesort is the opposite. The "dividing step" is simple and the "merge step" holds most of the logic.

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
- This is a data structure with dynamic size. Size isn't static like it is in arrays or anything built with an underlying array (Queue, Stack, Heap). **This is neat**
- Insert/delete in O(1) time
- The type of data held in each node can be different because we can use Objects (we're not constrained to using primitive data types like we are in arrays. At least in Java that's how it is).

#### Disadvantages
- Accessing an element takes O(n) time because you have to loop through the head of the LL
- Nodes must know about other nodes so they're not independant
- Hard to traverse backwards in a singly LL

#### Runner technique a.k.a Tortoise & Hare
Iterate through LL with 2 pointers. One pointer is ahead of the other. 
Usually one pointer iterates one element at a time and the other iterates two elements at a time.
This is useful for many LL problems where you want to weave elements together, determine the size of a list, detect a cycle

#### Example usages that work uniquely well with this data structure
- Making a better queue/stack
- Scavenger hunt
- Conga line
- Storing text while writing in a text editor (doubly linked list)

## Stacks
#### How they work
Last-in first-out container. So the most recent item put on the stack is the first one to be seen by the outside world.
Uses a top of stack pointer to an underlying array to expose that element to the outside world. Pushing (inserting) and popping (removing) from stack are done at top of stack only. This is not implemented circularly.
 
#### Advantages
- Push: Insert element at TOS in O(1) time
- Pop: Delete element at TOS in O(1) time
- Can be made to have a dynamic container size by resizing an underlying array or using an underlying linked list. **This is neat**
 
#### Disadvantages
- Must access top of stack element only. As a result:
	- If you want to search for an element you have to pop elements off the stack
	- If you want to remove a specific element, you have to search and pop so O(n) removal time
	- If you want to insert at a specific point in the stack, O(n) time due to searching and popping
- If using an underlying array, static size

#### Example usages that work uniquely well with this data structure
- Converting the base of a number
- Evaluating math expressions (usually combined with polish notation)
- Recursion stuff like backtracking in a maze

## Queues
#### How they work
First-in first-out. So elements are added to the end of the underlying array and removed from the beginning of the underlying array. 
This is implemented circularly with an underlying array.
Uses one pointer for the beginning element array index of the queue, and another pointer for the last element array index of the queue. 

#### Advantages
- Enqueue: Insert element at end of queue in O(1) time if less than total underlying array size
- Dequeue: Remove element from beginning of queue in O(1) time 
- Can be made to have a dynamic container size by resizing an underlying array or using an underlying linked list. **This is neat**

#### Disadvantages
- Can only remove elements from the beginning of the queue

#### Example usages that work uniquely well with this data structure
- Giving things priority
- Lining up for a store
- Waiting to enter an online video game server

## Hash Tables/Maps
#### How they work
A hash table or hash map will map keys to values using a hash function on those keys and using that hashed value as an index to store a value in an array. It has a  capacity c in the underlying array and n total items in the hash table. Collisions are certain to occur if n > c.
A hash function is a function that accepts a key and returns an index to the hash table. This is called hashing a key to an index. Example usage looks like this: 
```hashmap[hashFunction("key")] = value;```
A **collision** occurs when a hash function hashes a key to an index that already has a value.
Good hash functions have few collisions.

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
- Counting how many times you have seen some values in something. hashtable[h(some key)] = number of times you have seen it

## Tree & Graph definitions
**This is very important**

- Leaf nodes (leaves): nodes with no children
- Siblings: nodes with same parent
- Root: node with no parent. This is the top-most node of the tree. Also the starting point for iteration in the tree data structure
- Path: sequence of nodes n_1, n_2, ..., n_k such that n_i is the parent of n_i+1 for 1 <= i < k. **Note that in a tree, a path from node A to B is the shortest path from node A to B because there only exists one path from node A to B.**
- Walk: A walk is movement from one node to another in the tree. You can walk back to the same node you started if you wanted to.
- Length: number of edges on a path
- Depth of a tree node: is the length of the unique path from the root to that node. "How deep is that node?" Root has a depth of 0. Depth of tree is different from depth of root i.e., "How deep does the tree go?" vs. "How deep is the root from the root? 0"
- Height of a tree node: is the length of the longest unique path from that tree node to a leaf. All leaves have a height of 0. Example usages: "How high is that node?" or "What is the height of this node?"
- **Height of a tree = Depth of a tree = Max Height = Max Depth**: is the length of the longest unique path from the root to a leaf node.  "How high is the tree?"
- Traversal: visiting every node once
- **Serialize**: Means to put something into a series. Ex: serialize a tree makes a series representation of a tree. Ex: serialize a tree into an infix string.

## Binary Trees
**These are different than Binary Search Trees. BSTs extend the functionality of BTs**
#### How they work
Binary Trees are trees with each node having a max of 2 children. Nodes can also contain a value.

## Binary Tree Traversal Algorithms
### Pre-order Traversal (Depth-first traversal for Trees)
Pre-order traversal can be easily modified to a depth-first search because pre-order traversal is a depth-first traversal. If instead of going down the left paths first (visit node, preorder(left), preorder(right)), one can go down the right paths first (visit node, preorder(right), preorder(left))
#### Pseudocode
```java
public static void preorder(Node node) {
	if (node == null) return;
	visit(node);
	preorder(node.left);
	preorder(node.right);
}
```

#### Summary
Visit node, preorder(left), preorder(right).
Generates a prefix expression of nodes in a tree. This prefix expression is a serial representation of the tree. This is necessary for some language processors.

#### Example
Tree that isn't being rendered properly in markdown lol:
```
    +
  +   *
 a b c d
```

Calling preorder(+) with + being the root of the tree would generate this prefix expression: ++ab*cd
Going down the right path first (visit node, preorder(right), preorder(left)) generates this prefix expression: +*dc+ba

#### Algorithmic analysis
Time: Visits each node once so it's O(n)
Memory: **You could count the stack frames due to recursion but that's probably optimized by the compiler.** As a result, O(1)

#### Advantages over other traversals
* Since this is a depth-first traversal, this algorithm uses little memory on balanced binary trees. This is due to this algorithm only requiring 3 nodes in memory at a time (node.left, node.right, node, not counting stack traces). **The low amount of memory that DFS uses likely makes DFS better than BFS in scenarios where BFS uses more memory unless BFS happens to be more time-optimal in that situation. For example, if you know the data you're searching for isn't far from the root and the tree isn't very wide, then BFS is very optimal even though it uses some memory.**
* This algorithm is simple to implement

#### Disadvantages over other traversals
- Doesn't have the advantages of BFS
- If modified to a search, search takes O(n) whereas  binary search in binary search trees takes O(logn)
- Preorder is a specific ordering that may not be preferable in some situations

### Post-order traversal
#### Pseudocode
```java
public static void postorder(Node node) {
	if (node == null) return;
	preorder(node.left);
	preorder(node.right);
	visit(node);
}
```
#### Summary
postorder left, postorder right, visit node
Generates a postfix expression of nodes in a tree. This postfix expression is a serial representation of the tree. This is necessary for some language processors.
Post-order traversal is not exactly depth-first but is still more like depth-first than like breadth-first. **Post-order is actually kind of like a compromise between depth-first and breadth-first since it shares properties from traversals.** The postfix expression in the following example goes through ab before going to the parent +. The same is done with cd and *.

#### Example
Tree that isn't being rendered properly in markdown lol:
```
    +
  +   *
 a b c d
```

Calling postorder(+) with + being the root of the tree would generate this postfix expression: ab+cd*+

#### Algorithmic analysis
Time: Visits each node once so it's O(n)
Memory: **You could count the stack frames due to recursion but that's probably optimized by the compiler.** As a result, O(1)

#### Advantages over other traversals
* Since this is a depth-first-ish traversal, this algorithm uses little memory on balanced binary trees. This is due to this algorithm only requiring 3 nodes in memory at a time (node.left, node.right, node, not counting stack traces).
* This algorithm is simple to implement

#### Disadvantages over other traversals
- Doesn't have properties of other traversals like BFS
- May not be the best order of traversal for a particular situation

#### Example usages that work uniquely well with this algorithm
- Deleting a BT or Binary subtree (because that's also a tree :)). This works with postorder because postorder traverses children before parents which allows you to delete the children when you visit and then delete the parent after. **This is neat**

### In-order Traversal
#### Pseudocode
```java
public static void inorder(Node node) {
	if (node == null) return;
	inorder(node.left);
	visit(node);
	inorder(node.right);
}
```
#### Summary
inorder left, visit node, inorder right
Generates a infix expression of nodes in a tree. This infix expression is a serial representation of the tree. This is necessary for some language processors. **This infix expression is the sorted values of a BST if the tree is a BST**. Inorder traversals traverse the tree from left to right (or right to left if you do inorder(right), visit, inorder(left)) in the order that the nodes are arranged in the tree.

#### Example
Tree that isn't being rendered properly in markdown lol:
```
    +
  +   *
 a b c d
```

Calling inorder(+) with + being the root of the tree would generate this infix expression: a+b+c*d

#### Algorithmic analysis
Time: Visits each node once so it's O(n)
Memory: **You could count the stack frames due to recursion but that's probably optimized by the compiler.** As a result, O(1)

#### Advantages over other traversals
* Since this is a depth-first-ish traversal, this algorithm uses little memory on balanced binary trees. This is due to this algorithm only requiring 3 nodes in memory at a time (node.left, node.right, node, not counting stack traces).
* This algorithm is simple to implement

#### Disadvantages over other traversals
- Lacks properties of other traversals like BFS
- If modified to a search, search takes O(n)
- May not be the right order of traversal you want

#### Example usages that work uniquely well with this algorithm
The infix expression shows the sorted values of a Binary Search Tree. **This is neat**

### Breadth-first Traversal and Depth First Traversal for Trees (iterative)
Can be easily modified to Breadth First Search (BFS) by having the visit(node) part check if the passed in node you're looking for is equivalent to the node passed in to the visit method.
#### Pseudocode (iterative)
```java
// Passed in node is typically the root
public static void bft(Node node) {
	Queue q = new Queue();
	node.discover();
	q.enqueue(node);
	while (!q.isEmpty()) { 
		Node extract = q.dequeue();
		visit(extract);
		for (Node child : extract.children) {
			if (child != null && !child.isDiscovered()) {
				child.discover();
				q.enqueue(child);
			}
		}
    }
}
```

#### Pseudocode (recursive)
See RecursiveTreeSearches.java for several options. Another option that isn't in that file is the option of using BFS with an underlying array. As in http://stackoverflow.com/a/2549744, if you use an array to back the binary tree, you can determine the next node algebraically. If i is the index of a node (starting at 0), then its children can be found at 2i + 1 (for the left node) and 2i + 2 (for the right node). A node's next neighbor is given by i + 1, unless i is a power of 2. If I recall correctly, this is how heaps are implemented.

#### Summary
Whenever a node is enqueued, that means it has been discovered and I want to visit it and then traverse through it's children. The order in which that traversal occurs is defined by the data structure used. For breadth first traversal, that's a queue. **We could make the iterative BFS this an iterative Depth-first traversal by using a stack instead of a queue. Note that we can only do this stack/queue replace trick in Trees and not in Graphs.** This is probably due to the cycles in the graph resulting in the discovery procedure becoming incorrect

#### BFS Example
Tree that isn't being rendered properly in markdown lol:

    +
  /   *
 a b c d

- extract: d
- q: 
- discovered: `+/*abcd`
- visited: `+/*abcd`

#### DFS Example

    +
  /   *
 a b c d
 
 Start at + and assume adjacency list starts at right element and goes to left.
 
 Visitation order: +/ab*cd

#### Algorithmic analysis
Time: O(n)
Memory: O(n) due to queue
#### Advantages over other traversals
A BFS can be turned into a path finding algorithm between two nodes. Since we know that all paths in a tree are shortest paths (see path definition above and note that at least I know this for paths from root to leaves but not sure about all paths), then we know that BFS can be turned into a shortest-path finding algorithm. This is done by setting adjacent node's parents to the node extracted from the queue if the adjacent nodes have their parents set to null. Then to find the shortest path you just go from destination node's.parent recursively to the top until parent is null and return a list of null parent node to the destination node.
#### Disadvantages over other traversals
A balanced binary tree uses less memory storage in depth-first traversals than in breadth-first traversals. A depth-first traversal will only store a max of 3 nodes at a time (node, node.left, node.right, and not counting stack frames). A breadth-first traversal can store all the nodes at one level of a tree which can be very large. 
#### Example usages that work uniquely well with this algorithm
- Traversing a tree that has limited depth but lots of breadth (like a filesystem). Ex for filesystem: you would deal with the files in the current directory and forget about them before moving on to one of the child directories. **This is neat**

### Binary Search Tree (BST)
#### How they work
A Binary Search Tree is a tree with each node having the left node's value < parent node's value < right node's value. Either the left node value can be <= the parent node or the right node value can be >= than the parent node's value but only one of them can support the `or equal` part for being equal with the parent node's value.

#### Advantages
- You can perform a binary search that takes O(h) time where h is the height of the BST
- Easy to find the minimum/maximum valued node, just go left/right in O(h) time
- You can insert an array into a BST and then print that tree with an inorder traversal
- This is a data structure with dynamic size. Size  isn't static like it is in arrays or anything built with an underlying array (Queue, Stack, Heap). **This is neat**

#### Disadvantages
- In order to get O(h) = O(logn) you have to occasionally balance the BST and that takes time.

#### Example usages that work uniquely well with this data structure
- Good when you need: Hierarchy, quick search if balanced O(h) = O(logn), quick insert/delete if balanced O(h) = O(logn)

### Balanced Trees (namely BTs and BSTs)
- There are several ways to define balanced trees. The one I know best is the one for AVL balanced trees. That is, a balanced tree is a tree where every subtree in the tree (including the tree itself) follows this principle: the height of a node and the height of that node's children differ by at most 1.
- The overall goal is to keep the height of all nodes to be log(n) resulting in O(h) operations to be O(log(n))

### Heaps (Priority Queues)
#### How they work
Heaps are structures built with an underlying array. The property of heaps is that every node in the heap is >= (max heap) or <= (min heap) it's children.
Heaps look like Binary Trees. Each node can have a max of 2 children and each node also has a value. To find the left/right child, or parent of a node in a heap you pass in the array index of the node to a method left()/right()/parent() which all take O(1) time and space. To extract the min/max element from a heap, Heap.extract() which takes O(log n) due to heapify taking O(log n) time. Similarly, inserting an element takes O(log n) time due to heapify taking O(log n) time. Building a heap from an input array takes O(n) time.
#### Example
This is a max heap:
```
   10
 8    9
2 3  4 5
```
#### Advantages
* Nodes can be weighted and prioritized according to that weight. Weights don't necessarily have to be integers.
* Insert/Delete always takes O(log n) time. Compare this with BST Insert/Delete which takes O(h) time where the height of the tree may not always be log n and you have to balance too.

#### Disadvantages
- Insertion & deletion aren't performed in constant time because you need to re-heapify
- You don't really traverse a heap. But you could just "extract" (remove the root) and then re-heapify in a loop to continually get the k-th min or max. This would count as a O(nlogn) traversal

#### Example usages that work uniquely well with this data structure
- Accessing the k-th min/max in an array
- Prioritizing things

### Graphs
#### Definitions
- Vertex: a node in a graph. A.k.a., a single vertice
- Vertices: plural of vertex
- Adjacent vertices: Vertices that have an edge between them
- Degree of vertex: is # of adjacent vertices at a node in the graph
- Path: sequence of vertices v1, v2, ... vk such that v_i+1 is adjacent to vi for i=1 ... k-1
- Cycle: Path with no repeated vertices except that the last vertex equals the first
- Connected graph: all vertices are connected by some path
- Walk: a walk of length L in a graph is a sequence of alternating vertices and edges like `V_0, E_0, V_1, E_1, ..., V_L-1, E_L-1, V_L` such that the edge E_i in the sequence connects the vertices V_i and V_i+1
- Dense graph: A graph that has many edges
- Sparse graph: A graph that has few edges 

#### How they work
Note that the magnitude symbol on a V means number of vertices in the graph and on a E means the number of edges in the graph.

##### Adjacency list
A graph can be represented by the adjacency lists of all its vertices. An adjacency list of a vertex v is a list of vertices adjacent to v. This uses O(|V| + Sigma(degree(v))) = O(|V| + |E|). **Sigma(degree(v)) = |E| is interesting**. **Note that for directed graphs, adjacency lists are ideally used**

###### Advantages
- Pretty simple
- O(k) time to find the successor of a vertex  by keeping track of a list of successors of v. K is the number of possible successors for v
- Uses less memory than the Adjacency Matrix depending on data types used in the list and how many bytes per data type

###### Disadvantages
- Looking for an edge between two vertices takes O(|V|) time because you have to look at one of those vertices adj list and loop through it to find the other vertex

##### Adjacency Matrix
M[vertex i, vertex j] = 1 if there's an edge between vertices i, j.

###### Example
```
  a b c
a 0 1 1
b 1 0 0
c 1 0 0
```

###### Advantages
- O(1) access time to see if there's an edge between two vertices
- O(1) time to add/remove an edge (just editing an array)

###### Disadvantages
- O(|V|^2) memory can get nasty with large |V|

#### Advantages of Graphs
* Many unique usages/applications of this data structure

#### Disadvantages of Graphs
* search takes a long time O(|V| + |E|) = O (|V| + |V|^2) or something

#### Example usages that work uniquely well with this data structure
- Compilers, graphics, maze-solving, mapping, networks (routing, searching, clustering, ...)

### Misc. notes about Graph Traversals
* In a directed graph, you can only look at adjacent nodes that you can actually travel to from the current node that you're on
* In an undirected graph, you can look at all adjacent nodes to the current node you're on

### Breadth First Search for graphs
Breadth First Traversal can be easily modified to BFS by having the visit(vertex) method check if the passed in vertex you're looking for is equivalent to the vertex passed in to the visit method. If any of the vertices still has a distance of infinity at the end of the traversal, then that vertex is not accessible from the graph. I think a vertex that isn't accessible from the graph would be undiscovered too **check this**. **These two properties can be useful in determining if a graph is connected.**

#### Pseudocode (iterative)
```java
public static void bft(Graph g) {
	g.setAllVertexDistancesTo(Math.INFINITY);
	g.setAllVertexDiscoveryStatesTo(Vertex.UNDISCOVERED);
	
	Vertex v = g.startingVertex;
	v.setDiscovered();
	v.setDistance(0); // Start of the graph is set to distance 0 because it's 0 distance from the start of the graph lol
	
	Queue q = new Queue();
	q.enqueue(v);

	while (!q.isEmpty()) {
		Vertex vertex = q.dequeue();
		visit(vertex);
		for (Vertex adjVertex : vertex.adjacencyList) {
			if (!adjVertex.isDiscovered()) { // if it were discovered and you continued with the logic, the distance would start off correct but then it would become different if there are multiple paths from the root to a particular vertex
				adjVertex.setDiscovered();
				adjVertex.setDistance(vertex.getDistance() + 1); // Since adjVertex is right beside vertex
				q.enqueue(adjVertex);
			}
		}
	}
}
```
#### Summary
BFT/DFT are two different ways to traverse through a graph. In these algorithms, whenever a vertex is enqueued or pushed to the stack, that means it has been discovered and I want to visit it and then traverse through it's children. The order in which that traversal occurs is defined by the data structure used. For breadth first traversal, that's a queue and for DFT, a stack. **Note that the vertex distance parts of BFT algorithm are not needed.** Take a look at RecursiveTreeSearches.java for examples of recursive BFT for trees. Using the same strategy (the check at the beginning of the algorithm, and the recursive call at the end of the algorithm), that algorithm in that file can be modified to work with Graphs. Just change the node.left and node.right enqueueing to be an enqueue inside a for loop of an adjacency list. See a recursive implementation of DFS somewhere below.
#### Algorithmic analysis
Time: O(|V| * visit + |E|). The + |E| part comes from the enhanced for loop being run sum(degree(each vertice)) amount of times which equals |E|. Note that |E| can be as large as O(|V|^2) (all nodes have edges between each other) depending on how dense the graph is. A dense graph has lots of edges. A sparse graph has few edges. **This is pretty neat.**
Memory: O(|V|) from queue

#### Advantages (BFS > DFS)
Both BFS and DFS are good at searching through unordered data arranged as a graph. Choosing BFS or DFS usually depends on the data.

* If your data is varied and wide, you'll probably want BFT. 
* A BFS can be turned into a path finding algorithm between two nodes **if the edges are unweighted**. Note that for BFS for Trees, this unweighted edge requirement is implied because the concept of edges or costs to go to a child node does not exist. This is done by setting adjacent node's parents to the node extracted from the queue if the adjacent nodes have their parents set to null. Then to find the shortest path you just go from destination node's.parent recursively to the top until parent is null and return a list of null parent node to the destination node. **Note that even though parent is a "tree word" it just means the node that we were at before we reached the node that is calling node.parent**

#### Disadvantages
* You will likely need a different traversal for graphs with weighted edges

#### Example usages that work uniquely well with this algorithm
- In general for both BFS, DFS: Compilers, graphics, maze-solving (**so potentially backtracking?**), mapping, networks (routing, searching, clustering, ...)
- For BFS: Peer to peer networks like BitTorrent, GPS systems to find nearby locations, social networking sites to find people in the specified distance

#### BFS Example
It's going to be hard to draw this graph in Markdown lol. I'll use arrows (→ ← ↑ ↓ ↖ ↗ ↘ ↙). Note that the graph is still undirected. Letters are vertices. Right side of equals is their distance from starting vertex. Bolded letters are discovered.

(**B** = 1) → → → (**F** = 2) → (**C** = 3) → (**H** = 4)
↑ ↖               ↗
↑  ↖  (**D** = 1)
↑   ↖ ↑
↑     (**A** = 0)  
↑    ↗
(**E** = 1) → (**G** = 2)

Let's say A is the starting point of the graph and all edges are undirected.

- q: 
- vertex: C
- adjVertex: 
- adj list: (order of adj list doesn't matter as long as order of discovery doesn't matter)
- visited: A, D, B, E, F, G, C, H
- discovered: A, D, B, E, F, G, C, H


### Depth First Search for graphs
#### Pseudocode (iterative)
```java
public static void dft(Graph g) {
	g.setAllVertexDiscoveryStatesTo(Vertex.UNDISCOVERED);
	
	Vertex v = g.startingVertex;
	v.setDiscovered();
	
	Stack s = new Stack();
	s.push(v);

	while (!s.isEmpty()) {
		Vertex vertex = s.pop();
		visit(vertex);
		if (!vertex.isDiscovered()) {
			vertex.setDiscovered();
			for (Vertex adjVertex : vertex.adjacencyList) {
				if (!adjVertex.isDiscovered()) {					s.push(adjVertex);
				}
			}
		}
	}
}
```

#### Example for the iterative algorithm
It's going to be hard to draw this graph in Markdown lol. I'll use arrows (→ ← ↑ ↓ ↖ ↗ ↘ ↙). Note that the graph is still undirected. Letters are vertices. 

      A
    ↙↓↘
   B  C E
 ↙↓  ↓ ↑
D  F  G ↑
   ↘→→↗

Let's say A is the starting point of the graph. Also, we push content onto the stack from right to left i.e. adjacency lists start with the rightmost element.
 
- order of visitation: A,B,D,F,E,C,G 

#### Pseudocode (recursive)
```java
public static void dft(Vertex v) {
	// If we already preformed dft on this node then don't repeat ourselves
	if (v.isDiscovered()) {
		return;
	}
	
	visit(v);
	v.setDiscovered();
	v.setColor(Vertex.GREY);
	for (Vertex adjVertex : vertex.adjacencyList) {
		if (!adjVertex.isDiscovered()) {
			dft(adjVertex);
		}
	}
	v.setColor(Vertex.BLACK);
}
```

#### Example for the recursive algorithm
It's going to be hard to draw this graph in Markdown lol. I'll use arrows (→ ← ↑ ↓ ↖ ↗ ↘ ↙). Note that the graph is still undirected. Letters are vertices. 

      A
    ↙↓↘
   B  C E
 ↙↓  ↓ ↑
D  F  G ↑
   ↘→→↗

Let's say A is the starting point of the graph. Also, we push content onto the stack from left to right i.e. adjacency lists start with the leftmost element.
 
- order of visitation: A,B,D,F,E,C,G 

Order of recursive calls maps to the order of visitation too. However there's one more recursive call for dfs(E) at the end. That call is the reason for the if discovered then return statement at the top of the code.

#### Summary
Depth first search can't be used to find distance from a source node to a destination node. The reason for this is because that distance would have to be the shortest distance and we know that DFS can't be used to find the shortest path in unweighted graphs. See the proofs on the internet. Putting it very simply, BFS can do this because BFS has the nice property that at every node, it checks its undiscovered adjacent nodes and sets their distance. This makes it possible to find the shortest path. For DFS, that property just doesn't exist. **This is important**

#### Algorithmic analysis
Time: O(|V| * visit + |E|). The + |E| part comes from the enhanced for loop being run sum(degree(each vertice)) amount of times which equals |E|. Note that |E| can be as large as O(|V|^2) (all nodes have edges between each other) depending on how dense the graph is. A dense graph has lots of edges. A sparse graph has few edges. **This is pretty neat.**
Memory: O(|V|) from stack

#### Advantages (DFS > BFS)
* If your data is deep, you'll probably want DFS. 

#### Disadvantages
* You will likely need a different traversal for graphs with weighted edges
* Doesn't have the property of BFS that allows BFS to calculate the shortest path for graphs with unweighted edges

#### Example usages that work uniquely well with this algorithm
- In general for both BFS, DFS: Compilers, graphics, maze-solving (**so potentially backtracking?**), mapping, networks (routing, searching, clustering, ...)
- DFS is particularly used in game-simulations such as chess where you can choose one of several possible actions. When you are deciding what move to make, you can mentally imagine a move, then your opponent’s possible responses, then your responses, and so on. You can decide what to do by seeing which move leads to the best outcome. Only some paths in a game tree lead to your win, some lead to a win by your opponent. When you reach such an ending, you must back up, or backtrack, to a previous node and try a different path. In this way you explore the tree until you find a path with a successful conclusion. Then you make the first move along this path.

### Directed Acyclic Graph (DAG)
#### How they work
A directed graph (unidirectional graph: this means that when you traverse an edge, you can't traverse the same edge backwards to get back to where you were) that has no cycles. 

**The following thing I'm about to say is quite tricky: some of the DAGs I have been looking at look like they have cycles. But in reality, they only have the shape of a cycle; a closed triangle with its vertices being nodes/vertexes. Upon taking a closer look, I can see that those closed triangle shapes are not cycles. This is because the directions that the arrows are pointing in do not allow for the closed triangle shape to be a cycle. Remember that DAGs are DIRECTIONAL graphs without cycles.**

#### Example usages that work uniquely well with this data structure
Used in Topological Sort

### Topological Sort
#### Pseudocode
```java
public static LinkedList<Map<Vertex, Integer>> topologicalSort(Vertex root) {
	root.setAllVertexDiscoveryTimesTo(-1);
	Vertex v = g.startingVertex;
	Stack s = new Stack();
	s.push(v);
	
	LinkedList<Map<Vertex, Integer>> topsorted = new LinkedList<Map<Vertex, Integer>>();
	int time = 0;
	while (!s.isEmpty()) {
		Vertex vertex = s.pop();
		if (vertex.getDiscoveryTime() == -1) {
			time += 1;
			vertex.setDiscoveryTime(time);
			for (Vertex adjVertex : vertex.adjacencyList) {
				if (adjVertex.getDiscoveryTime() == -1) {				s.push(adjVertex);
				}
			}
			time += 1;
			vertex.setFinishedTime(time);
			topsorted.prepend(new Map<Vertex, Integer>(vertex, time));
		}
	}
	return topsorted;
}
```
#### Summary
Given a directed acyclic graph, you visit a node that does't have any incoming edges, remove that node from the graph, and then repeat that process. The order in which you do this is a topologically sorted version of that directed graph. We can view a topological sort of a graph as an ordering of its vertices along a horizontal line so that all directed edges go from left to right.

Topological Sort is basically Depth First Search that returns the finishing times of vertexes in order of last finished to first finished. **(Shouldn't it be first to last? NO. Last finished to first finished is correct. The reasoning for this is that you set something to finished after you have explored all of it's adjacencies to maximum depth. This means that finished time is one of the very first vertexes discovered. Which makes sense because you have to visit that before the next nodes in the Topologically Sorted list).** See CLRS 3rd edition figure 22.7 to better understand this.
#### Example
Skipping this for now.
#### Algorithmic analysis
Time: O(DFS runtime) + O(1) = O(DFS runtime) = O(|V| + |E|)
Memory: O(V + V) = O(V) where V is the number of vertices. This runtime is because of the stack and Linkedlist
#### Example usages that work uniquely well with this algorithm
Figuring out what is the correct order to do things. I.e., I need to do X before I do Y before I do Z etc.

### DAG Detection (i.e., Check if a graph has cycles)
#### Summary
When running DFS, add a detection algorithm when you visit a node. That detection algorithm will basically check if you have visited that node before. If you have, return true. Otherwise if you have finished traversing the graph without returning, just return false.
#### Algorithmic analysis
Time: same as DFS
Memory: same as DFS
#### Example usages that work uniquely well with this algorithm
Whenever you don't want cycles

### NP-Complete problems
#### Decision problems
Question with a yes or no answer.
#### P problems
P is the set of all decision problems that can be solved and verified in polynomial time O(n^k) on a deterministic turing machine.
#### Deterministic Turing Machine
A machine that defines a maximum of one solution to a problem. Ex: if you see a red light, turn left.
#### NP problems
NP is the set of all decision problems for which problems with a "yes" answer can be verified to be correct in polynomial time O(n^k)
#### NP-Hard characteristic
NP-Hard problems are problems that are at least as hard/difficult (higher difficulty means takes longer to solve) to solve as the hardest/most difficult NP problems.
##### Methods to computing a solution for a NP-hard problem
- Create an exact algorithm (fast for small problem sizes)
- Create a sub-optimal algorithm (provides decent solution but is not proven to be optimal)
- Create an algorithm for a special case of the problem (subproblem) for which better or exact/optimal heuristics are possible
#### NP-Complete problems
An NP-Complete problem is a problem that has the NP-Hard characteristic and is a problem that every other problem in NP can be transformed to in polynomial time. This means that if any one NP complete problem can be solved "quickly" (polynomial time) then any NP problem can be solved "quickly" (in polynomial time) because the transformation is also polynomial time and O(polynomial time + polynomial time) = O(polynomial time)
#### Does P = NP?
If P = NP then that means all NP-Complete problems can be solved in polynomial time and we can use solutions to NP-Complete problems to solve all NP problems using a polynomial time transformation 
#### How to classify a problem as NP-Complete
TODO in the future

### Traveling Salesman Problem a.k.a. TSP (NP-hard problem with an NP-complete version of the problem)
The Traveling Salesman Problem is a problem with the NP-hard characteristic and can basically be described as a problem in which a person tries to find the shortest route that passes through each of a set of points once and only once.  The real description of the NP-hard TSP problem is "Given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city exactly once and returns to the origin city?" **(note that you don't have to start at any particular city)**. This problem is so famous because the decision version of this problem is an NP-complete problem. That version is described as "Given a length L, decide whether a graph G has any tour shorter than L" with answers "yes it has a tour shorter than L" and "no it doesn't have a tour shorter than L".

In the symmetric TSP, the distance between two cities is the same in each opposite direction, forming an undirected graph. This symmetry halves the number of possible solutions. 

In the asymmetric TSP, paths may not exist in both directions or the distances might be different, forming a directed graph.

#### Applications of the TSP
- Planning
- Logistics
- Manufacturing of microchips
- DNA sequencing
- If the NP-complete version of this problem is solvable in polynomial time, then there will be more applications of this problem due to the discussion on P=NP

In these applications, the concept city (node in a graph) represents, for example, customers, soldering points, or DNA fragments, and the concept distance represents travelling times or cost, or a similarity measure between DNA fragments. The TSP also appears in astronomy, as astronomers observing many sources will want to minimise the time spent moving the telescope between the sources. In many applications, additional constraints such as limited resources or time windows may be imposed.

#### Naive solution (solving the NP-hard problem exactly using brute force)
To solve the NP-hard version of the TSP, we could run a linear walk along all the edges (O(n) where n is the number of cities) on all permutations (ordered combinations) of paths to find the cheapest path. The number of permutations of paths is the number of permutations of cities visited which is just the number of permutations of unique cities. The number of permutations of unique cities is ((n-1)!)/2 where n is the total number of unique cities. The time complexity of this naive solution would be O(linearWalkTime*numPermutationsOfUniqueCities) = O(n*((n-1)!)/2) = O(n*((n-1)!)) = O(n!). This naive solution stops being practical at around 20 cities (n = 20).

### Knapsack Problem (NP-hard problem with an NP-complete version of the problem)
Here's the NP-hard version of the knapsack problem: "Given a set of items, each with a weight and a value, determine the number of each item to include in a collection so that the total weight is less than or equal to a given limit and the total value is as large as possible." The decision problem form of the knapsack problem (Can a value of at least V be achieved without exceeding the weight W?) is NP-complete. This is another famous NP-complete problem.

0-1 Knapsack problem is the most common version of the knapsack problem. In this version, the number of copies of each item that can be put in the bag, can be only zero or one.

There's also an bounded version where the number of items is bounded by a specific max non-negative integer. 

Lastly there's the unbounded version where there's no upper bound on the number of copies of each kind of item.

#### Applications of the Knapsack problem
- Basically, lots of resource allocation problem can use a solution from the Knapsack problem
- investments
- cryptography
- fantasy sports
- If the NP-complete version of this problem is solvable in polynomial time, then there will be more applications of this problem due to the discussion on P=NP

#### Naive solution
The following is a Dynamic Programming solution to the 0-1 Knapsack Problem. It's kinda complicated and could be tough to come up with on the spot but it makes sense.

Assume w_1, w_2, ..., w_n are all positive integers. Define m[i, w] to be the max value achieved with weight <= to w using the first i items. Define W to be the max weight that the knapsack can hold. The reason why we can go in order with the counting of the items ("the first i items") is because there is only 1 or 0 of each item (I think this is the reason...).

It turns out that we can define m[i, w] recursively as follows:
* m[0, w] = 0 which makes sense because you cant have value with no items
* m[i, w] = m[i - 1, w] if w_i > w (the weight using the first i items surpasses the limit because of the ith item so we go back to the unsurpassed limit using all the items up to i - 1)
* m[i, w] = max(m[i - 1, w], m[i - 1, w - w_i] + v_i) if w_i <= w. This means that if the weight using the first i items is less than the weight w, the max value achieved with weight <= w using the first i items is equal to whichever is bigger of the following two:
1. the value achieved with weight <= w using the first i - 1 items
2. the value achieved with taking all the weight except for the weight of everything up to the ith item, and using all the first i-1 items, in addition to the value achieved in taking all i items. **This is kinda confusing**

Then the solution to this problem is m[n, W] which we can calculate using this information about recursion.

The pseudocode looks like this:
```
// Input:
// Values (stored in array v)
// Weights (stored in array w)
// Number of distinct items (n)
// Knapsack capacity (W)

for j from 0 to W do:
     m[0, j] := 0

for i from 1 to n do:
   for j from 0 to W do:
        if w[i-1] > j then:
           m[i, j] := m[i-1, j]
        else:
            m[i, j] := max(m[i-1, j], m[i-1, j-w[i]] + v[i])
```

The time complexity of this approach is O(nW) which ends up being pseudo-polynomial time due to some weird property of W that I don't fully understand.
The space complexity of this approach is O(nW) due to the 2d array called m. **TODO: understand the W part more**

## Object Oriented Programming (OOP)
Objects: data structures that contain data (fields) and procedures (methods)
Classes: definitions of objects. They define how objects work.

### Principles of OOP
#### Encapsulation
Encapsulation is when you hide information by setting who can see that information. Ex: making a variable or method private
#### Inheritance
Inheritance is the concept of extending or implementing functionality from another class. Ex: in Java, "implements" or "extends
#### Polymorphism
**Polymorphism is when Class A that extends Class B calls a method that exists in both Class A and Class B.** Basically, the class can be used as different classes.
ex: 
```java
public class PolymorphicClass extends OtherClass
PolymorphicClass instance = new PolymorphicClass();
OtherClass other = instance;
```
#### Open recursion
Open recursion is when other methods can make use of the object they're in by using the keywords "this" or "self". Ex: using the current instance object using "this" or equivalently, just calling that object's instance variables or methods
#### Method overloading
Is when you have two methods that have the same name but different method parameters.
	methodName()
	methodName(int parameter)
#### Method overriding
Is when you edit a parent class' method to suit the child class' needs. Ex: @Override in Java. Can also make use of stuff from parent's class using "super".
#### Delegation/forwarding
Delegation refers to one object sending or "forwarding" a message to a second object. This is typically done when the first object's class does not know how to handle the message.
#### Composition:
A "owns" B and B has no meaning without A. Ex: TextFile class "owns" a Buffer class and the Buffer has no meaning without the TextFile.
#### Aggregation:
A "uses" B and B can exist independently from A. Ex: Company uses people to ...
#### Is-a versus has-a relationship
Is-a is when Class A extends B. A is-a B.
Has-a is when Class A has B as a field. A has-a B.

## Common algorithm performances
Constant: O(1)
Logorithmic: O(log base 2 n) = O(logn)
Linear: O(n)
Polynomial: O(n^y) y > 1 
Exponential: O(x^n) x > 1 (x=2 means input doubles every time etc)
Factorial: O(n!)

### Directed graph vs Undirected graphs (a.k.a. bidirectional graphs)
* Directed graphs may have a route from nodes a to b but not nodes b to a (because node b can't go back to a). ex: a->b.
* Undirected graphs have a route from nodes a to b and from nodes b to a. Ex: a-b.

### Set
A set is a collection of distinct objects. Order does not matter. So the set {1, 2, 3, 4} is the same as the set {3, 1, 2, 4}. **Set.add(x) is an optional operation because it won't add x to the set if x is already in the set. This is very useful for CS problems that can't have duplicates.**

### List
A list is a collection of objects (may or may not be distinct) in which order does matter. So the list {1, 2, 3, 4} is different from the list {3, 1, 2, 4}.

### Trie
A Trie is a data structure that is used for indexing and searching for strings in a given input string. It often gives fast solutions to problems involving Strings.

#### How they work
Take a look at the following class:
public class Trie {
	String value;
	List<Trie> children;
}
The root node of a Trie is an empty String. 

A Trie is a variant of an n-ary tree in which characters are stored at each node. Each path down the tree typically represents a word. You know the path you're on has completed into a word when the end of that path (depth-first) leads to a `*`. If we're only storing letters from the alphabet and that `*`, then the number of children nodes can be anywhere from 0 to ALPHABET_SIZE + 1. If you don't have a child, you have an unfinished word or some other thing.

##### Inserting a String into a Trie
(O(n) time where n is the length of the input String)
1. Start off at the root node of the Trie
2. Cut off the first character of the String and see if the children of the root has this character as a value
3. If none of the children of the root have that character as a value then make a new Trie with no children and with a value being the first char that we cut, and append that to the root's children list.
4. We repeat a similar process for the remaining character's in the input String

#### Advantages
- Provides fast solutions to common String problems. These solutions are faster than most "naive" methods i.e. recursion.
- A trie can check if a String is a prefix of another String in O(n) time where n is the length of the string. One might think to use a hash table but a hash table can (probably, im not 100% sure) only check if a String is in fact a word (i.e. a dictionary)

#### Disadvantages
- Space complexity can get large
- Most of the speediness relies on having a fixed alphabet. If your alphabet is tens of thousands of symbols instead of just 26 letters then the performance will be less impressive

#### Example usages that work uniquely well with this data structure
- Searching for one String inside another. This is also called a "prefix lookup"
- counting the number of Strings with a common prefix given an array of Strings
- lexicographical sort can be performed by a preorder traversal (i think) if the values are stored in a particular ordering... I'll have to investigate this further


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
#### Advantages
#### Disadvantages
#### Example usages that work uniquely well with this algorithm

## Data Structure documentation skeleton
### Data Structure name
#### How they work
#### Advantages
#### Disadvantages
#### Example usages that work uniquely well with this data structure

#TODO
* Lookup how to do a correctness proof
* Organize into data structures, algorithms
* Make this cleaner
* Split up data structures and algorithms into separate files where appropriate
* Rewrite this in Latex

