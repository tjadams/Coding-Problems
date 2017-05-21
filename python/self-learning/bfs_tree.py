class BTNode:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None
        self.is_discovered = False

    def get_children(self):
        return [self.left, self.right]

def bst(root):
    queue = []
    root.is_discovered = True
    queue.append(root)

    while len(queue) > 0:
        node = queue.pop(0)
        print node.value
        for child in node.get_children():
            if child is not None and not child.is_discovered:
                child.is_discovered = True
                queue.append(child)

n1 = BTNode(1)
n2 = BTNode(2)
n3 = BTNode(3)
n4 = BTNode(4)
n5 = BTNode(5)
n6 = BTNode(6)
n7 = BTNode(7)

n1.left = n2
n1.right = n3
n2.left = n4
n2.right = n5
n3.left = n6
n3.right = n7

bst(n1)
