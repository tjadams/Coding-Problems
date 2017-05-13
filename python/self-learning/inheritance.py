# Needs to be a new-style class in order to use super
class BaseClass(object):
    def __init__(self):
        self.base_param = "base param"

    def based_god(self):
        print "I am based"

class DerivesFrom(BaseClass):
    def __init__(self):
        # Use of super in Python is weird but,
        # it is typically used mainly for method overriding
        super(DerivesFrom, self).__init__()
        self.derived_param = "derived param"

    def derived_method(self):
        print "Derived method"

if __name__=="__main__":
    x = DerivesFrom()
    print x.derived_param, ",", x.base_param
    x.based_god()
    x.derived_method()
