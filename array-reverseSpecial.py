def isValid(string):
    return string.isalpha()

def swap(string, left, right):
	list_ = list(string)
	temp = list_[left]
	list_[left] = list_[right]
	list_[right] = temp
	return ''.join(list_)

def reverseSpecial(string):
    left = 0
    right = len(string) - 1

    while left < right:
        if isValid(string[left]) and isValid(string[right]):
            string = swap(string, left, right)
            left += 1
            right -= 1
            continue

        if not isValid(string[left]):
            left += 1

        if not isValid(string[right]):
            right -= 1
    return string

result = reverseSpecial('Ab,c,de!$')
print result == 'ed,c,bA!$'

result = reverseSpecial("a,b$c")
print result == "c,b$a"
