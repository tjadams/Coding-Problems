class Classes:
    def __init__(self):
        self.list = []

    def add_to_list(self, element):
        self.list.append(element)

if __name__=="__main__":
    x = Classes()
    print x.list
    x.add_to_list(1)
    print x.list

    y = Classes()
    y.add_to_list(2)
    print x.list
    print y.list
