# Python tutorial notes

## float and int
# x // y is float division (with either x or y being floats) that discards the remainder
# x / y does floor divison if x and y are ints and returns int. If one of them are float, its float division and float is returned
# x % y returns the remainder of x / y
# x ** y is x to the power of y
# If you try to reference a variable that was never assigned, it's undefined
# Operators with mixed type operands (int and lfoat) will convert the integer operand to a floating point (ex: 3 * 3.75 / 1.5 = 7.5, the 3 is treated as 3.0)

## strings
# Can be enclosed in single quotes or double quotes
# Can use \ to escape quotes
# print ""
# If a string has \ inside it and you want to print it, just do print r'\n'
# You can multiply strings by a number to concatonate them multiple times
# String concat is usually done with +
# There is no char, thats simply a string of size 1
# Can access indexes both positive and negative string[i] but i has to be < the size of the string. However, i can be > than the length of the string in slicing
# Strings can be sliced string[a:b] a is included and b is excluded
# string[:b] gives beginning to b excluding b
# string[a:] gives position a including a to end of string
# strings are immutable so you can't do string[i] = 'y'. To get a different string, create a new one
# length of string = len(string)

#TODO:
#https://docs.python.org/2/library/stdtypes.html#typesseq
#https://docs.python.org/2/library/stdtypes.html#string-methods
#https://docs.python.org/2/library/string.html#formatstrings
#https://docs.python.org/2/library/stdtypes.html#string-formatting

#TODO: current position: 3.1.4 lists https://docs.python.org/2/tutorial/introduction.html#lists