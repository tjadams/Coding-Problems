class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def invertTree(self, root):
        if root == None: return
        temp = root.left
        root.left = root.right
        root.right = temp
        self.invertTree(root.left)
        self.invertTree(root.right)
        return root # I forgot to put this

if __name__=="__main__":
    root = TreeNode(4)
    n1 = TreeNode(2)
    n2 = TreeNode(1)
    n3 = TreeNode(3)
    n4 = TreeNode(7)
    n5 = TreeNode(6)
    n6 = TreeNode(9)
    root.left = n1
    root.right = n4
    n1.left = n2
    n1.right = n3
    n4.left = n5
    n4.right = n6

    x = Solution()
    x.invertTree(root)
    print str(root.left.val) + " " + str(root.right.val)
    print str(root.left.left.val) + " " + str(root.left.right.val)
    print str(root.right.left.val) + " " + str(root.right.right.val)

'''
1 -> 1

  1           1
2   3  - >  3   2

     4
   /   \
  2     7
 / \   / \
1   3 6   9

->
     4
   /   \
  7     2
 / \   / \
9   6 3   1

     4
    2  7
  1  3 6 9

 naive solution is recursion, took ~17 mins to solve after reading

'''
