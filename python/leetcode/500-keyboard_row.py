class keyboardrow:
    row1 = ['q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p']
    row2 = ['a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l']
    row3 = ['z', 'x', 'c', 'v', 'b', 'n', 'm']

    def read_row(self, input_element, row):
        for input_element_char in input_element:
            isThisCharInRow = False
            for row_char in row:
                if row_char == input_element_char:
                    isThisCharInRow = True
            if isThisCharInRow == False:
                return False
        return True

    def main(self):
        output = []
        input = ["Hello", "Alaska", "Dad", "Peace"]
        for input_element in input:
            input_element_lower = input_element.lower()
            isInRow1 = self.read_row(input_element_lower, self.row1) #weird that I have to do self.rowX but i guess it makes sense
            isInRow2 = self.read_row(input_element_lower, self.row2)
            isInRow3 = self.read_row(input_element_lower, self.row3)
            result = isInRow1 or isInRow2 or isInRow3
            if result == True:
                output.append(input_element)
        print "output: " + str(output)

if __name__=="__main__":
    x = keyboardrow()
    x.main()

'''
    rough notes on solving the problem
    Hello.tolowercase = hello (h 1, e 1, l 2, o 1)
    first row
     q w e r t y u i o p
        no h, so doesnt work on first row (returns false on first row)
        loop through qwetyuiop looking for characters h, e, l, l, o (can be optimized to h, e, l, o)
            return false if you reach the end of the loop and you cant find the character
    second row
        no e, so doesnt work on first row (returns false on second row)
        if a is in hello, remove all as from hello, if empty string in the end then return true
    third row

approaches:
    - temporary string removal (unsure of algorithmic complexity of those python string algorithms)
    - 2 hashtables

    if all rows returned false, return false. If any row returns true, add it to a list
'''

#can optimize it even more by saying which row to go to (letter a is row 2)
