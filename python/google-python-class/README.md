#google-python-class
Notes I made from following Google's Python class at [https://developers.google.com/edu/python/introduction](https://developers.google.com/edu/python/introduction) and Google's Python Style Guide at [https://google.github.io/styleguide/pyguide.html](https://google.github.io/styleguide/pyguide.html). I have **bolded** what is especially important for me.

## General Notes
* Python does not have type declarations of variables, parameters, functions or methods. As a result, there's no compile-time type checking. 
* There's also no semicolons. The end of a line is the end of a statement. 
* Python source files use the `.py` extension and are called `modules.` To run a module, type `python <module_name>.py <parameters>`. 
* **Instead of using curly braces, blocks of code are written by using indentation.**
* Variable names should reflect the type of the variable in a step to remove the possibility of type errors. i.e., `name` is a string but `names` is a list of strings.  
* To call a function from another module, you can import that module at the top of your file and then say module.function(). Another way to call a function from another module is to say `from module import variable/function, variable/function` and then those variables/functions will be available by their short names. It's best to use the first way since it's easiest to see where the variables/functions come from.
* [] syntax and the len() function work on any sequence type (strings, lists, etc)
* Python does not have a seperate character type. Instead just use string[index]

## Warnings
*  Do not name your variables `str`, `list` (not so sure about `str` or `list` because the upcoming wiki doesn't list them), or [https://en.wikibooks.org/wiki/Think_Python/Variables,_expressions_and_statements#Variable_names_and_keywords](https://en.wikibooks.org/wiki/Think_Python/Variables,_expressions_and_statements#Variable_names_and_keywords) because those are system variables and by doing this variable naming, you would be overriding those system variables.
*  Python is case sensitive so "a" and "A" are different variables. Avoid TABs because they could be different across devices. Change your editor to insert spaces instead of a TAB when TAB is pressed (In Atom, enable Soft Tabs with Tab Length of 2 and Tab Type set to Auto).
*  Do not use the old module "string" because it is outdated

## help() and dir()
In the Python interpreter (type python in terminal), typing `help(method)` where that method may be imported will give information on a method. Also, `dir(object)` will display the object's attributes including its methods.

## Strings
* Python's built-in string class/module is "str". Note that these normal strings are **not** unicode strings. They're just plain bytes. To make a unicode string, use the u prefix as such: u'unicode string'
* Strings can be enclosed by either double or single quotes. Single quotes are most commonly used
* Backslash escapes work as they do in Java (\n \' \")
* Double quoted strings can contain single quotes ex: ("didn't")
* Single quoted strings can contain double quotes ex: ('did she say "yolo"?')
* To get multiple lines of text, you can escape newlines or use three double quotes or three single quotes
* **Strings are immutable in Python (like in Java). You can't change a character in a string. ex: 'hello' + 'there' returns a NEW string 'hellothere'. In order to make a new string based on an old string (which is basically like editing the old string),  use String slice syntax with the `+` operator**
* **Characters in a string can be accessed using `string[index]` which is zero-indexed**
* len(string) returns length of string
* **Unlike Java, `+` does not convert numbers or other types to string form. I must use `str(nonStringTypeVariable)`**
* **A "raw" string literal is a string prefixed with r before the quotes. What this does is uses the string without any special treatment of backslashes (escaped characters)**

### String Methods
* == works fine with strings. I.e., don't use a .equals()
* s.lower(), s.upper() - lowercase/uppercase
* s.strip() - removes trailing and leading whitespace
* s.startswith('hi'), s.endswith('bye') - boolean is returned
* **s.find('other') - returns index of 'other' in s or -1 if 'other' is not in s**
* **s.replace('old', 'new')**
* **s.split(',') - returns a list of strings from s that is split on each comma**
*  **s.split() - returns a list of substrings from s that is split on each whitespace char. ex: s = "two  spaces" s.split() returns ['two', 'spaces']**
* **s.join(list) - opposite of split, joins elements in a list using the string s as a delimiter. ex: ','.join(['a','b']) returns 'a,b'    ex: ''.join(['a','b']) returns 'ab'**

### String slice syntax (substrings)
**This section is important**

* s = 'hello'
* s[start:end] is the elements beginning at index start and extending up to but not including index end. ex: s[1:4] is 'ell' 
* Ommitting the right index means it will go from left index to end. ex: s[1:] is 'ello'
* Ommitting the left index means it will go from beginning to right index. ex: s[:3] = 'hel' 
* Going to an index bigger than string length just goes to the string length. ex: s[1:100] = 'ello'
* **You can access chars by iterating in the reverse direction. ex: s[-1] = 'o'    ex: s[-len(s)] = 'h'**

### String % (printf)
* s = '%d dollars in your %s bank account' % (1000000000, 'american')
* %d int, %s string, %f or %g for floating point

## Code-across-lines
* text = 'say this is a long %s' % 
             'line'
**This will not work like it does in other languages.**
* Instead, write text = ('say this is a long %s' %
                                   'line')
* You can use this code-across-lines technique with the grouping constructs: (), []
* ex with []: array = 'yo', "what's", 'good', 'fam?', 'say this becomes a very long line' +
" and it doesn't work anymore since it goes to the next line"
**This will not work like it does in other languages.**
* Instead, write array = ['yo', "what's", 'good', 'fam?', 'say this becomes a very long line' + " and it doesn't work anymore since it goes to the next line"]  

## If statements
**This section is important**

* Python does not use {} for if/loops/functions. It just uses `:`. Don't forget the `:`!
* Python does not need the boolean test in an if to be in brackets.
* `if ...:`, `elif ...:`, and `else`
* Boolean operators are spelled out words `and`, `or`, `not`

## Numbers
* +, /, * work as in Java
* **There's no ++ operator but +=, -= etc all work**
* **Integer division is // instead of just /**

## Methods vs Functions
A method is a function that runs "on" an object. I.e., `s.split('delimiters')`. A function would be like `def main():` in that it doesn't run "on" an object. I.e., you can just call `main()`.

## Examples
* Setting a variable ex: a = 1 (int object)
* String ex: a = 'hi' (str object)
* Length of a string (str) ex: len(a)
* Converting int object to str: str(intObject)
* Comment using #
* Repeat a character using * ex: '-' * 2 = '--'
* Finding out the type of a variable: type(var)
* Unusual print example (integer with commas): print 1,000,000
this will print out "1 0 0"
* Unusual print example (leading zero integer): print 02132
this will print 1114
* **help(sys.exit) - displays a help string for the sys.exit function**
* **dir(list) - displays list object attributes and methods**

## TODO
* Use Python syntax highlighting for code samples
* Think about switching from Markdown to Latex
* Put all examples inline
* Use more `apostrophe things like this`