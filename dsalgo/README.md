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

#### Runner technique a.k.a Tortoise & Hare
Iterate through LL with 2 pointers. One pointer is ahead of the other. 
Usually one pointer iterates one element at a time and the other iterates two elements at a time.
This is useful for many LL problems where you want to weave elements together, determine the size of a list, detect a cycle

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

- Leaves: nodes with no children
- Siblings: nodes with same parent
- Root: node with no parent. This is the top-most node of the tree. Also the starting point for iteration in the tree data structure
- Path: sequence of nodes n_1, n_2, ..., n_k such that n_i is the parent of n_i+1 for 1 <= i < k
- Walk: A walk is movement from one node to another in the tree. You can walk back to the same node you started if you wanted to.
- Length: number of edges on a path
- Depth of a tree node: is the length of the unique path from the root to that node. "How deep is that node?" Root has a depth of 0. Depth of tree is different from depth of root i.e., "How deep does the tree go?" vs. "How deep is the root from the root? 0"
- Height of a tree node: is the length of the longest unique path from that tree node to a leaf. All leaves have a high of 0."How high is that node?"
- **Height of a tree = Depth of a tree = Max Height = Max Depth**: is the length of the longest unique path from the root to a leaf node.  "How high is the tree?"
- Traversal: visiting every node once
- **Serialize**: Means to put something into a series. Ex: serialize a tree makes a series representation of a tree

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
Since this is a depth-first traversal, this algorithm uses little memory on balanced binary trees. This is due to this algorithm only requiring 3 nodes in memory at a time (node.left, node.right, node, not counting stack traces).

#### Disadvantages over other traversals
- Since this is a depth-first traversal, if the tree is like the worst-case insertion scenario (O(n) where n is number of nodes and n is the height of the tree instead of O(logn) with n being the number of nodes and logn the height of the tree), then traversing the tree depth-first will use more memory than breadth-first.
- If modified to a search, search takes O(n)

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
Since this is a depth-first-ish traversal, this algorithm uses little memory on balanced binary trees. This is due to this algorithm only requiring 3 nodes in memory at a time (node.left, node.right, node, not counting stack traces).
#### Disadvantages over other traversals
- Since this is a depth-first-ish traversal, if the tree is like the worst-case insertion scenario (O(n) where n is number of nodes and n is the height of the tree instead of O(logn) with n being the number of nodes and logn the height of the tree), then traversing the tree depth-first will use more memory than breadth-first. However this effect is not as pronounced because post-order is depth-first-ish.
- If modified to a search, search takes O(n)
#### Example usages that work uniquely well with this algorithm
- Deleting a BT or Binary subtree (because that's also a tree :)). This works with postorder because postorder traverses children before parents which allows you to delete the children when you visit and then delete the parent after. 

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
Generates a infix expression of nodes in a tree. This infix expression is a serial representation of the tree. This is necessary for some language processors. This infix expression is the sorted values of a BST if the tree is a BST. Inorder traversals traverse the tree from left to right (or right to left if you do inorder(right), visit, inorder(left)) in the order that the nodes are arranged in the tree.

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
Since this is a depth-first-ish traversal, this algorithm uses little memory on balanced binary trees. This is due to this algorithm only requiring 3 nodes in memory at a time (node.left, node.right, node, not counting stack traces).
#### Disadvantages over other traversals
- Since this is a depth-first-ish traversal, if the tree is like the worst-case insertion scenario (O(n) where n is number of nodes and n is the height of the tree instead of O(logn) with n being the number of nodes and logn the height of the tree), then traversing the tree depth-first will use more memory than breadth-first. However this effect is not as pronounced because post-order is depth-first-ish.
- If modified to a search, search takes O(n)

#### Example usages that work uniquely well with this algorithm
The infix expression shows the sorted values of a Binary Search Tree.

### Breadth-first Traversal for Trees
Can be easily modified to Breadth First Search (BFS) by having the visit(node) part check if the passed in node you're looking for is equivalent to the node passed in to the visit method.
#### Pseudocode
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
#### Summary
Whenever a node is enqueued, that means it has been discovered and I want to visit it and then traverse through it's children. The order in which that traversal occurs is defined by the data structure used. For breadth first traversal, that's a queue. **We could make this an iterative Depth-first traversal by using a stack instead of a queue**
#### Example
Tree that isn't being rendered properly in markdown lol:
```
    +
  /   *
 a b c d
```

- extract: d
- q: 
- discovered: `+/*abcd`
- visited: `+/*abcd`
#### Algorithmic analysis
Time: O(n)
Memory: O(n) due to queue
#### Advantages over other traversals
Since this is a breadth-first traversal, if the tree is like the worst-case insertion scenario (O(n) where n is number of nodes and n is the height of the tree instead of O(logn) with n being the number of nodes and logn the height of the tree), then traversing the tree breadth-first will use less memory than depth-first.
#### Disadvantages over other traversals
A balanced binary tree uses less memory storage in depth-first traversals than in breadth-first traversals. A depth-first traversal will only store a max of 3 nodes at a time (node, node.left, node.right, and not counting stack frames). A breadth-first traversal can store all the nodes at one level of a tree which can be very large. 
#### Example usages that work uniquely well with this algorithm
- Traversing a tree that has limited depth but lots of breadth (like a filesystem)

### Binary Search Tree (BST)
#### How they work
A Binary Search Tree is a tree with each node having the left node's value < middle node's value < right node's value. Either the left node value can be <= or the right node value can be >= than the middle node's value but only one of them can support the `or equal` part for being equal with the middle node's value.

#### Advantages
- You can perform a binary search that takes O(h) time where h is the height of the BST
- Easy to find the minimum/maximum valued node, just go left/right in O(h) time
- You can insert an array into a BST and then print that tree with an inorder traversal
- Dynamic data structure, size isn't static
#### Disadvantages
#### Example usages that work uniquely well with this data structure
- Good when you need: Hierarchy, quick search (log n), quick insert/delete O(h), 

### Balanced Trees (namely BTs and BSTs)
- There are several ways to define balanced trees. The one I know best is the one for AVL balanced trees. That is, a balanced tree is a tree where every subtree in the tree follows this principle: the height of a node and the height of that node's children differ by at most 1.
- The overall goal is to keep the height of all nodes to be O(h) where h = log(n)

### Heaps (Priority Queues)
#### How they work
Heaps are structures built with an underlying array. The property of heaps is that every node in the heap is >= (max heap) or <= (min heap) than it's children.
Heaps look like Binary Trees. Each node can have a max of 2 children and each node also has a value. To find the left/right child, or parent of a node in a heap you pass in the array index of the node to a method left()/right()/parent() which all take O(1) time and space. To extract the min/max element from a heap, Heap.extract() which takes O(log n) due to heapify taking O(log n) time. Similarly, inserting an element takes O(log n) time due to heapify taking O(log n) time. Building a heap from an input array takes O(n) time.
#### Example
This is a max heap:
```
   10
 8    9
2 3  4 5
```
#### Advantages
Nodes can be weighted and prioritized according to that weight. Weights don't necessarily have to be integers.
#### Disadvantages
- Insertion & deletion aren't performed in constant time because you need to re-heapify
- You don't really traverse a heap. But you could just "extract" (remove the root) and then re-heapify in a loop to continually get the k-th min or max
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
- Walk: a sequence of alternating vertices and edges beginning and ending with vertices.
- Dense graph: A graph that has many edges
- Sparse graph: A graph that has few edges 
#### How they work
Note that the magnitude symbol on a V means number of vertices in the graph and on a E means the number of edges in the graph.
##### Adjacency list
A graph can be represented by the adjacency lists of all its vertices. An adjacency list of a vertex v is a list of vertices adjacent to v. This uses O(|V| + Sigma(degree(v))) = O(|V| + |E|).
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
#### Advantages
#### Disadvantages
#### Example usages that work uniquely well with this data structure
- Compilers, graphics, maze-solving, mapping, networks (routing, searching, clustering, ...)

### Breadth First Traversal and Depth First Traversal for graphs
Can be easily modified to Breadth First Search (BFS) by having the visit(vertex) part check if the passed in vertex you're looking for is equivalent to the vertex passed in to the visit method. Alternatively, if any of the vertices still has a distance of infinity, then that vertex is not accessible from the graph.
#### Pseudocode
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
BFT/DFT compute the shortest distance to all vertices starting from a particular vertex. Whenever a vertex is enqueued, that means it has been discovered and I want to visit it and then traverse through it's children. The order in which that traversal occurs is defined by the data structure used. For breadth first traversal, that's a queue. **We could make this an iterative Depth-first traversal by using a stack instead of a queue**. **Basically in BFT, whenever you find a vertex, you take a glance at its adjacent nodes but you don't yet fully go down the path of those adjacent nodes. You just calculate the distance and move on** and calculate their distances. Then you do the same with all the adjacent nodes of the starting node. You keep doing this on all nodes until you have done this on all nodes. **Basically in DFT, whenever you find an adjacent vertex, you explore that adjacent vertex's adjacent vertex.**

#### Algorithmic analysis
Time: O(|V| * visit + |E|). The + |E| part comes from the enhanced for loop being run on all vertices. Note that |E| can be as large as O(|V|^2) (all nodes have edges between each other) depending on how dense the graph is. A dense graph has lots of edges. A sparse graph has few edges
Memory: O(|V|) from queue/stack
#### Advantages
Both are good at searching through unordered data. Choosing BFT or DFT depends on the data. If your data is deep, you'll probably want DFT. If your data is varied and wide, you'll probably want BFT.
#### Disadvantages
#### Example usages that work uniquely well with this algorithm
- Compilers, graphics, maze-solving, mapping, networks (routing, searching, clustering, ...)
#### BFT Example
It's going to be hard to draw this graph in Markdown lol. I'll use arrows (→ ← ↑ ↓ ↖ ↗ ↘ ↙). Letters are vertices. Right side of equals is their distance from starting vertex. Bolded letters are discovered.
#### Misc. notes
* In a directed graph, you can only look at adjacent nodes that you can actually travel to from the current node that you're on
* In an undirected graph, you can look at all adjacent nodes to the current node you're on

```
(**B** = 1) → → → (**F** = 2) → (**C** = 3) → (**H** = 4)
↑ ↖   ↑
↑  ↖  (**D** = 1)
↑   ↖ ↑
↑     (**A** = 0)  
↑    ↗
(**E** = 1) → (**G** = 2)
```
Let's say A is the starting point of the graph.
- q: 
- vertex: C
- adjVertex: 
- adj list: (order of adj list doesn't matter as long as order of discovery doesn't matter)
- visited: A, D, B, E, G, F, C, H
- discovered: A, D, B, E, F, G, C, H

#### DFT Unfinished Example (unfinished because I got what I needed to make the summary)
**Notice how I stopped the example as soon as I saw that I went down the path of A, E, G**
```
(**B** = 1) → → → (F = INF) → (C = INF) → (H = INF)
↑ ↖   ↑
↑  ↖  (**D** = 1)
↑   ↖ ↑
↑     (**A** = 0)  
↑    ↗
(**E** = 1) → (**G** = 2)
```
Let's say A is the starting point of the graph.
- s (stack):    G
-               B
-               D
 
- vertex: E
- adjVertex: G 
- adj list: (order of adj list doesn't matter as long as order of discovery doesn't matter)

### Directed Acyclic Graph (DAG)
#### How they work
A directed graph (unidirectional graph: this means that when you traverse an edge, you can't traverse the same edge backwards to get back to where you were) that has no cycles. 
#### Example usages that work uniquely well with this data structure
Used in Topological Sort

### Topological Sort
#### Pseudocode (mostly correct lol)
```java
public static LinkedList<Vertex> TopologicalSort(Vertex root) {
	LinkedList<Vertex> topsorted = new LinkedList<Vertex>();

	root.setAllVertexDistancesTo(Math.INFINITY);
	root.setAllVertexDiscoveryStatesTo(Vertex.UNDISCOVERED);
	
	int time = 0;

	Vertex v = g.startingVertex;
	v.setDiscovered();
	v.setDistance(0); // Start of the graph is set to distance 0 because it's 0 distance from the start of the graph lol
	
	Stack s = new Stack();
	s.push(v);

	while (!s.isEmpty()) {
		Vertex vertex = s.pop();
		
		time += 1;
		// vertex.setDiscoveryTime(time); // Don't need this right here but it's interesting.

		for (Vertex adjVertex : vertex.adjacencyList) {
			if (!adjVertex.isDiscovered()) { // if it were discovered and you continued with the logic, the distance would start off correct but then it would become different if there are multiple paths from the root to a particular vertex
				adjVertex.setDiscovered();
				adjVertex.setDistance(vertex.getDistance() + 1); // Since adjVertex is right beside vertex
				s.push(adjVertex);
			}
		}

		time += 1;
		// vertex.setFinishedTime(time);
		topsorted.prepend(new Map<Vertex, Integer>(vertex, time));
	}
	return topsorted;
}
```
#### Summary
Depth First Search that returns the finishing times of vertexes in order of last finished to first finished. **(Shouldn't it be first to last? NO. Last finished to first finished is correct. The reasoning for this is that you set something to finished after you have explored all of it's adjacencies to maximum depth. This means that finished time is likely one of the very first vertexes discovered. Which makes sense because you have to visit that before the next nodes in the Topologically Sorted list)**
#### Example
#### Algorithmic analysis
Time: O(DFS runtime) + O(1) = O(DFS runtime) = O(|V| + |E|)
Memory: O(V) because of stack. Linkedlist is basically O(1) if I think of it as V pointers.
#### Example usages that work uniquely well with this algorithm
Figuring out what is the correct order to do things. I.e., I need to do X before I do Y before I do Z etc.

### DAG Detection (i.e., Check if a graph has cycles)
#### Summary
When running DFS, add a detection algorithm when you visit a node. That detection algorithm will basically check if you have visited that node before. If you have, return true. Otherwise if you have finished traversing the graph without returning, just return false.
#### Algorithmic analysis
Time: same as DFS
Memory: same as DFS
#### Example usages that work uniquely well with this algorithm
When do you not want cycles?

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
#### NP-Complete problems
An NP-Complete problem is a problem that has the NP-Hard characteristic and is the problem that every other problem in NP can be transformed to in polynomial time. This means that if any one NP complete problem can be solved "quickly" (polynomial time) then any NP problem can be solved "quickly" (in polynomial time)
#### Does P = NP?
If P = NP then that means all NP-Complete problems can be solved in polynomial time and we can use solutions to NP-Complete problems to solve all NP problems using a polynomial time transformation 
#### How to classify a problem as NP-Complete
TODO in the future

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
Logorithmic: O(log base 2 n) = O(lgn)
Linear: O(n)
Polynomial: O(n^y) y>1 (n=2 means input doubles every time etc)
Exponential: O(e^n)
Factorial: O(n!)

### Tries a.k.a prefix tree
#### How they work
A Trie is a variant of an n-ary tree in which characters are stored at each node. Each path down the tree typically represents a word. You know the path you're on has completed into a word when the end of that path (depth-first) leads to a `*`. If we're only storing letters from the alphabet and that `*`, then the number of children nodes can be anywhere from 0 to ALPHABET_SIZE + 1. If you don't have a child, you have an unfinished word or some other thing.
#### Advantages
- Used very commonly when looking for something that has to do with a String
- A trie can check if a String is a prefix of another String in O(n) time where n is the length of the string
#### Disadvantages
- A trie can check if a String is inside it in O(n) time where n is the length of the string. Hash Tables are probably better in this situation.
#### Example usages that work uniquely well with this data structure
 - Quick prefix lookups by storing entire english language
 - A Trie can see if a word is a prefix of any other valid words really quickly. A Hash Table can only check if a String is in fact a word (i.e. a dictionary).

### Directed graph vs Undirected graphs (a.k.a. bidirectional graphs)
* Directed graphs may have a route from nodes a to b but not nodes b to a (because node b can't go back to a). ex: a->b.
* Undirected graphs have a route from nodes a to b and from nodes b to a. Ex: a-b.

### Set
A set is a collection of distinct objects. Order does not matter. So the set {1, 2, 3, 4} is the same as the set {3, 1, 2, 4}.

### List
A list is a collection of objects (may or may not be distinct) in which order does matter. So the list {1, 2, 3, 4} is different from the list {3, 1, 2, 4}.

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
* Organize into data structures, algorithms
* Make this cleaner
* Split up data structures and algorithms into separate files where appropriate
* Rewrite this in Latex