class Classes:
    test = 1
    def class_method():
        test = 2
        print "test inside: " + str(test)

    def get_test(self):
        return str(test)

    def set_test():
        test = 4

    class_method()
    print "test #1 outside: " + str(test)
    test = 3
    print "test #2 outside: " + str(test)
    print "test #3: " + get_test()

    set_test()
    print "test #4: " + get_test()
