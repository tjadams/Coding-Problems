class BinaryTree:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None
        self.parent = None

def transformTreeToHaveParents(root):
    if root is None: return

    if root.left is not None:
        root.left.parent = root

    if root.right is not None:
        root.right.parent = root

    transformTreeToHaveParents(root.left)
    transformTreeToHaveParents(root.right)

def lowestCommonAncestor(root, n1, n2):
    transformTreeToHaveParents(root)

    n1Ancestors = []
    n1Parent = n1.parent
    while n1Parent is not None:
        n1Ancestors.append(n1Parent)
        n1Parent = n1Parent.parent

    n2Ancestors = []
    n2Parent = n2.parent
    while n2Parent is not None:
        n2Ancestors.append(n2Parent)
        n2Parent = n2Parent.parent

    for n1Ancestor in n1Ancestors:
        for n2Ancestor in n2Ancestors:
            if n1Ancestor.value == n2Ancestor.value:
                return n1Ancestor.value

n1 = BinaryTree(1)
n2 = BinaryTree(2)
n3 = BinaryTree(3)
n4 = BinaryTree(4)
n5 = BinaryTree(5)
n6 = BinaryTree(6)
n7 = BinaryTree(7)
n1.left = n2
n1.right = n3
n2.left = n4
n2.right = n5
n3.left = n6
n3.right = n7

print lowestCommonAncestor(n1, n4, n5) == 2
print lowestCommonAncestor(n1, n4, n6) == 1
print lowestCommonAncestor(n1, n3, n4) == 1

# I don't think this really makes sense as an input... how can n2 be an ancestor of itself?
# print lowestCommonAncestor(n1, n2, n4) == 2
