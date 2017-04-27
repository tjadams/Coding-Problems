# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def removeElements(self, head, val):
        """
        :type head: ListNode
        :type val: int
        :rtype: ListNode
        """
        # Edge case: delete element(s) at head
        while head is not None and head.val == val:
            head = head.next

        # Dont change the head since we'll return that, make a pointer to it
        current = head

        while current is not None and current.next is not None:
            if current.next.val == val:
                current.next = current.next.next
            else:
                # Had to put this in the else otherwise I skip an element
                current = current.next

        return head

    def printLinkedList(self, head):
        if head is not None:
            print str(head.val) + " "
            self.printLinkedList(head.next)

if __name__=="__main__":
    print "test1: expect 1->2->3->4->5"
    x = Solution()
    n1 = ListNode(1)
    n2 = ListNode(2)
    n3 = ListNode(6)
    n4 = ListNode(3)
    n5 = ListNode(4)
    n6 = ListNode(5)
    n7 = ListNode(6)
    n1.next = n2
    n2.next = n3
    n3.next = n4
    n4.next = n5
    n5.next = n6
    n6.next = n7
    head = x.removeElements(n1, 6)
    x.printLinkedList(head)

    print "test2: expect 1"
    n8 = ListNode(1)
    n9 = ListNode(2)
    n10 = ListNode(2)
    n8.next = n9
    n9.next = n10
    head = x.removeElements(n8, 2)
    x.printLinkedList(head)

    print "test3: expect 2"
    n11 = ListNode(1)
    n12 = ListNode(2)
    n11.next = n12
    head = x.removeElements(n11, 1)
    x.printLinkedList(head)

    print "test4: expect nothing"
    n13 = ListNode(1)
    head = x.removeElements(n13, 1)
    x.printLinkedList(head)

    print "test5: expect nothing"
    n14 = ListNode(1)
    n15 = ListNode(1)
    n14.next = n15
    head = x.removeElements(n14, 1)
    x.printLinkedList(head)

    print "test6: expect nothing"
    n16 = ListNode(1)
    n17 = ListNode(1)
    n18 = ListNode(1)
    n16.next = n17
    n17.next = n18
    head = x.removeElements(n16, 1)
    x.printLinkedList(head)

'''
The reason why this question became a timesink is because I made the mistake of looking at current.val
instead of current.next.val for equality with val. That made things way harder. Also, I forgot to delete the 
elements at the head for a while.

1, val = 1

current node's value is val
remove current node
return entire list

1->2->3, val = 2

current node's value is not val
change current node to next node
check if new current node is not None
current node's value is val
remove current node
change current node to next node
check if new current node is not None
current nodes value is not val
change current node to next node
check if new current node is not None
it is None so return the head

thinking of ways of removing one node:
 1. simply link 1 to 3 but without looking at 2...
 2. set 2 to be 3's node by setting 2's values

Ex with 2.
head: 1->2->3
current: 2->3

if current.next != None:
    current.val = current.next.val
    current.next = current.next.next

current.val = current.next.val
head: 1->3->3
current: 3->3

current.next = current.next.next
head: 1->3
current: 3

then to iterate in a list you say current = current.next

The following doesnt work because I'm iterating with 2's node and this doesn't affect the original list. You have to edit current's fields, not what it points to)
    current = current.next

How to delete the last node in a list?
Ex: 1->2 delete 2
See that current.next == val and current.next.next == None
set current.next to None
'''
