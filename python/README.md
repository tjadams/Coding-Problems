#python
This readme contains notes made from learning Python. Some if it is focused on being able to do all the syntax things I can do with Java in Python. Lots of this was taken from the Python 2.7.13 tutorial. I have **bolded** what is especially important for me.

## Misc
* **Tabs matter in Python. If you tab in, you're within the function that's above and to the left of your tabbed code**
* **boolean logic isn't && and || or, it's just "and", "or"**
* **boolean true is called True, boolean false is called False**
* **null is actually called None in python**
* **a recursive call requires calling self.recursiveMethod**
* **in a recursive problem, if you receive a None value as an input param when you're trying to perform changes on an input param, you can simply return and nothing bad will happen**
* **in a for loop, to do a loop over the size of a list you need to do
 `for i in range(len(nums) + anyNumberYouWant):`**
* Can do an enhanced for loop on a data structure by saying `for x in datastructure:` 
* **To get the size of a data structure: `len(datastructure)`**
* To get a list of integers, use the `range(n)` function to get [0, 1, ... n - 1]. You can also do `range(start, end)` and `range(start, end, step_value)` 
* The `break` statement breaks out of the smallest enclosing `for` or `while` loop
* **The 'pythonic' way of having static functions is not to have a class that has its own static methods, but to just make them free functions in a utility class or module**
* Python doesn't have a final variable so you just have to know not to edit variables you say are constants. I can do this by typing `CONSTANT_VARIABLE_NAME` and following that style
* Local variables in a function exist in their own namespace and get deleted after the function call. So you can't edit global variables in a function. See an example of this in namespace.py
* convention for private variables is to use `_variable_name` instead of `variable_name`
* Built-in functions (without imports):
	* `min(data_struct)`
	* `max(data_struct)`
	* `abs(x)`
	* `pow(x, y)` is x to the power of y
	* `round(x, n)` is rounding of x to n decimal places. Note that even 0 decimal places is a floating number of `y.0 where y is the number before the decimal in x`
* Nested for loops example:
`for row in range(len(board)):
	for col in range(len(board[row])):`

## selection statements
* **if, for, elif, else statement must end with ":"**
* else if is called `elif`

## list
* `list = []`
* **`list.append(x)`**
* `list.extend(list2)` appends all items in list2 to list
* **`list.insert(i, x)`**
* **`list.pop(i)` and `list.pop()` remove and return elements**
* **`list.sort(cmp, key)` cmp is comparison function, key is key to sort on**. See sort.py in this repo for details
* `list.count(x)` returns # of times x appears in the list
* `list.remove(x)` removes first instance of element
* `list.index(x)` gives index of first instance of element

## dict
* unordered set of key-value pairs with unique keys
* `dict = {}`
* `dict = {key1: value1, key2: value2}`
* `dict[key] = value`
* `dict.keys()`

## stack
* you can use a list as a stack with `list.append(x)` and `list.pop()`

## queue
* List isn't designed to be used as a queue in Python so they reccomend you to use collectioncs.deque
* Even though list is not designed to be used like that, it's easy to do so. You can use a list as a queue with `list.append(x)` and `list.pop(0)`

## set
* A set is an unordered collection with no duplicate elements. Technically you could sort the set and then loop through it if you wanted to do so
* To create a set, you can call `set(param)` with param as a list or as a string and then it will take the characters
* `set1 - set2` represents elements in set1 but not set2
* `set1 | set2` represents elements in either set1 or set2
* `set1 & set2` represents elements in both set1 and set2
* `set1 ^ set2` represents XOR so elements in set1 or set2 but not both

## tuples
* A tuple is an immutable data structure that you can access
* `tuple = (1, 2, 3)`
* `tuple[0]` would return 1

## objects and classes
* Namespace = mapping from names to objects. Examples of those are the global names in a module, the local names in a function, each recursive call of a function. A Namespace is basically a set of variables you have access to given a specific scope
* **In Python, there's no real main function. You just execute code that's not in a function**
* **Note that in order to call a function, it has to be declared above the calling code**
* **Classes are defined like this:**

```
class ClassName:
	def function_name():
		print "hi"
	
	function_name()
```
* Here's an **incorrect** way to do global variables. It's incorrect because test is not a global variable. Calling get_test
```
class ClassName:
	test = 1
	def get_test()
		return str(test)
```
* **self works like the this keyword from Java. self.class_param is how you access and or manipulate an object's field. For non-"static" functions in a class, you need to pass in self**
* **constructor is def __init__(self, param1, param2...)**
* **calling a function from inside another function is done by calling it like self.function(). Also make sure that self is a param in the function**
* **instantiation is done with x = Class(). There is no "new" keyword**
* there's a difference between new-style and old-style classes. New-style inherits from the class called `object` whereas old-style dont. New-style classes just unify the concepts of `class` and `type(object)`. Furthermore, new-style classes

## dictionary
* This is the same concept as a Map in Java
* `dict = {}`
* `dict = {key1: value1, key2: value2}`
* `dict[key1] returns value1
* set using `dict[key1] = value1`
* To check if a key is in a dict, say `if key in dict` or `if key not in dict`. Do not check for `if dict[key] is not None` as that will give you an error

## function
* This is the same concept as a method in java
* You can put default argument values in the method signature but don't do this on a mutable arg value because the default arg values are only constructed once
* You can put variable_name = value in a function call if you want

## lambda expressions
* `lambda x, y: x + y` is an anonymous function
* **This is useful for sorting on a key. Ex: `pairs = [(1, 'one'), (2, 'two')]` and then doing `pairs.sort(key=lambda pair: pair[1])`**

## Modules
* To call a function from a different module, do Module.function()

## try and except
* instead of try and catch, Python has try and except
* `try: ... except ExceptionName: ...`

## strings
* Can be enclosed in single quotes or double quotes
* Can use \ to escape quotes
* print ""
* If a string has \ inside it and you want to print it, just do print r'\n'
* You can multiply strings by a number to concatonate them multiple times
* String concat is usually done with +
* There is no char, thats simply a string of size 1
* Can access indexes both positive and negative string[i] but i has to be < the size of the string. However, i can be > than the length of the string in slicing
* Strings can be sliced string[a:b] a is included and b is excluded
* string[:b] gives beginning to b excluding b
* string[a:] gives position a including a to end of string
* strings are immutable so you can't do string[i] = 'y'. To get a different string, create a new one
* length of string = len(string)
* Can't directly concat a string with a number. You have to cast the number to a string using str()
* string.format(param1, param2,...) is called on a string that has "{}" in it. The "{}" are replaced with the params
* **Use == for value equality. Use `is` etc for reference equality. Use `is` or `is not` for `True`, `False`, and `None`.**

## numbers (int, float, long, complex)
* x // y is float division (with either x or y being floats) that discards the remainder
* x / y does floor divison if x and y are ints and returns int. If one of them are float, its float division and float is returned
* x % y returns the remainder of x / y
* x ** y is x to the power of y
* If you try to reference a variable that was never assigned, it's undefined
* Operators with mixed type operands (int and lfoat) will convert the integer operand to a floating point (ex: 3 * 3.75 / 1.5 = 7.5, the 3 is treated as 3.0)
* Note that it could be hard to work with floating poitn numbers due to the imprecision. The Decimal type solves this
* bitwise operations only work on `int` types. Those operations are the same as in Java

## Mistakes I made
* list = [a, b, c] instead of list = ['a','b','c']
*  http://stackoverflow.com/a/17557249
*  can't instantiate a class inside it's definition. To have the equivalent of a main method, I need the if main = main thing outside the class def. See http://stackoverflow.com/q/22294192 for details
*  having lowercase boolean names (true instead of True)
*  can't concatenate 'str' and 'list' objects without wrapping list in str()
*  putting brackets around an if
*  forgetting to put a colon at the end of an if or elif
*  calling something null instead of None
*  a recursive call requires calling self.recursiveMethod
*  forgot to return the value im editing in a recursive call
*  not putting range() in `for i in range(len(list)):`
*  can't say `for i = 1 in ...`. Instead have to modify the loop to `for i in range(1, ...):`
*  can't say `== None` or `!= None`. Have to say `is` or `is not`. Same goes for  `True` and `False`
*  Forgetting to put a colon after a function definition
*  Forgetting to put `self` in __init__
*  Do not check for `if dict[key] is not None` as that will give you an error. See dictionary notes

## TODO
* investigate method overloading. Can't overload a constructor for some reason... what about normal methods?
* https://docs.python.org/2/library/stdtypes.html#string-methods
* https://docs.python.org/2/library/string.html#formatstrings
* https://docs.python.org/2/library/stdtypes.html#string-formatting
* Python 2.7 tutorial 
	* 5.1.3 - Functional Programming Tools (filter, map, reduce)
	* 5.1.4 - List Comprehensions
	* 5.5 - Dict comprehensions
	* 9.9, 9.10, 9.11 - iterators and generators