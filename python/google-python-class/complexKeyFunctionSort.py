def main():
    list = ['Ec', 'dc', 'ab' ,'wa']
    print 'I want to sort this list by its second character: ' + str(list)
    print 'Collisions where the second character is equal across elements should be solved by sorting the first element.'

    # How come the list sorted with key = str.lower is not the same as the list sorted with key = lambda element: element[0].lower?
    first = sorted(list, key = str.lower)
    print 'Sorting by first character case insensitive: ' + str(first)
    # 'ab', 'dc', 'Ec', 'wa'

    second = sorted(first , key = lastElement)
    print 'Sorting the previous sorted array by second character: ' + str(second)
    # 'wa', 'ab', 'dc', 'Ec'

def lastElement(elementOfList):
    return elementOfList[-1]

if __name__ == '__main__':
    main()