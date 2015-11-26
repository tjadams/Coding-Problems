#google-python-class
Notes I made from following Google's Python class at [https://developers.google.com/edu/python/introduction](https://developers.google.com/edu/python/introduction) and Google's Python Style Guide at [https://google.github.io/styleguide/pyguide.html](https://google.github.io/styleguide/pyguide.html).

## Notes
* Python does not have type declarations of variables, parameters, functions or methods. As a result, there's no compile-time type checking. 
* There's also no semicolons. The end of a line is the end of a statement. 
* Python source files use the `.py` extension and are called `modules.` To run a module, type `python <module_name>.py <parameters>`. 
* Instead of using curly braces, blocks of code are written by using indentation. 
* Variable names should reflect the type of the variable in a step to remove the possibility of type errors. i.e., `name` is a string but `names` is a list of strings.  
* To call a function from another module, you can import that module at the top of your file and then say module.function(). Another way to call a function from another module is to say `from module import variable/function, variable/function` and then those variables/functions will be available by their short names. It's best to use the first way since it's easiest to see where the variables/functions come from.

## Warnings
*  Do not name your variables `str`, `list` (not so sure about `str` or `list` because the upcoming wiki doesn't list them), or [https://en.wikibooks.org/wiki/Think_Python/Variables,_expressions_and_statements#Variable_names_and_keywords](https://en.wikibooks.org/wiki/Think_Python/Variables,_expressions_and_statements#Variable_names_and_keywords) because those are system variables and by doing this variable naming, you would be overriding those system variables.
*  Python is case sensitive so "a" and "A" are different variables. Avoid TABs because they could be different across devices. Change your editor to insert spaces instead of a TAB when TAB is pressed (In Atom, enable Soft Tabs with Tab Length of 2 and Tab Type set to Auto).

## help() and dir()
In the Python interpreter (type python in terminal), typing `help(method)` where that method may be imported will give information on a method. Also, `dir(object)` will display the object's attributes including its methods.

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
* help(sys.exit) - displays a help string for the sys.exit function
* dir(list) - displays list object attributes and methods