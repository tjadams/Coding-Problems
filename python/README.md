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
 `for i = 0 in range(len(nums) + anyNumberYouWant):`**

## selection statements
* **if statement must end with ":"**

## array

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

## dictionary
* This is the same concept as a Map in Java
* `dict = {}`
* `dict = {key1: value1, key2: value2}`
* `dict[key1] returns value1
* set using `dict[key1] = value1`

## function
* This is the same concept as a method in java

## Modules
* To call a function from a different module, do Module.function()

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
* **use == for equality**

## float and int
* x // y is float division (with either x or y being floats) that discards the remainder
* x / y does floor divison if x and y are ints and returns int. If one of them are float, its float division and float is returned
* x % y returns the remainder of x / y
* x ** y is x to the power of y
* If you try to reference a variable that was never assigned, it's undefined
* Operators with mixed type operands (int and lfoat) will convert the integer operand to a floating point (ex: 3 * 3.75 / 1.5 = 7.5, the 3 is treated as 3.0)

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
*  can't say `== None` or `!= None`. Have to say `is` or `is not`

## TODO
* Make notes from the python tutorial that i read while on the bus / working out :)
* investigate if python has "static functions" from Java
* investigate if python has "final" from Java
* I read topics 4 and 5 from the tutorial but I still need to make notes on them
* I think local variables in a function exist in their own namespace and get deleted after the function call. So I'm pretty sure you can't edit global variables in a function. Test this
* Look into "super()" stuff and other OOP things
* https://docs.python.org/2/library/stdtypes.html#typesseq
* https://docs.python.org/2/library/stdtypes.html#string-methods
* https://docs.python.org/2/library/string.html#formatstrings
* https://docs.python.org/2/library/stdtypes.html#string-formatting