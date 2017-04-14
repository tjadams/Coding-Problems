#python
This readme contains notes made from learning Python. Some if it is focused on being able to do all the syntax things I can do with Java in Python. Lots of this was taken from the Python 2.7.13 tutorial. I have **bolded** what is especially important for me.

## Misc
* **Tabs matter in Python. If you tab in, you're within the function that's above and to the left of your tabbed code**

## classes
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

## float and int
* x // y is float division (with either x or y being floats) that discards the remainder
* x / y does floor divison if x and y are ints and returns int. If one of them are float, its float division and float is returned
* x % y returns the remainder of x / y
* x ** y is x to the power of y
* If you try to reference a variable that was never assigned, it's undefined
* Operators with mixed type operands (int and lfoat) will convert the integer operand to a floating point (ex: 3 * 3.75 / 1.5 = 7.5, the 3 is treated as 3.0)

## TODO
* current position: 3.1.4 lists https://docs.python.org/2/tutorial/introduction.html#lists
* I think local variables in a function exist in their own namespace and get deleted after the function call. So I'm pretty sure you can't edit global variables in a function. Test this
* https://docs.python.org/2/library/stdtypes.html#typesseq
* https://docs.python.org/2/library/stdtypes.html#string-methods
* https://docs.python.org/2/library/string.html#formatstrings
* https://docs.python.org/2/library/stdtypes.html#string-formatting