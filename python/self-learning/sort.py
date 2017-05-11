'''
This class tests sorting on a key and sorting on a compare function
'''

class Crayon:
    def __init__(self, color):
        self.color = color

def pretty_print(list):
    for crayon in list:
        print crayon.color

def sort_on_comparison():
    red = Crayon('red')
    blue = Crayon('blue')
    list = [blue, red]
    list.sort(cmp = crayon_compare)
    pretty_print(list)

def crayon_compare(crayon1, crayon2):
    if crayon1.color == 'red' and crayon2.color == 'blue':
        return -1
    elif crayon1.color == 'blue' and crayon2.color == 'red':
        return 1
    else:
        return 0

def generic_compare(item1, item2):
    if value_of_item(item1) < value_of_item(item2):
        # Item 1 goes as the first element
        return -1
    elif value_of_item(item1) > value_of_item(item2):
        # Item 2 goes as the first element
        return 1
    else:
        return 0

def pretty_print_keys(list):
    for tuple in list:
        print tuple[0], tuple[1]

def sort_on_key():
    list = [(1, 4), (1, 3)]
    list.sort(key = lambda tuple: tuple[1])
    pretty_print_keys(list)

if __name__=="__main__":
    sort_on_comparison()
    sort_on_key()
