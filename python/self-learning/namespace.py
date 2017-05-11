class Namespace:
    GLOBAL_VARIABLE = 1

    def __init__(self):
        GLOBAL_VARIABLE = 2

if __name__=="__main__":
    x = Namespace()
    print x.GLOBAL_VARIABLE
