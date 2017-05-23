# Note this is not optimized
def top_down_fib(n):
    if n <= 0:
        return 0
    if n == 1:
        return 1
    result = top_down_fib(n-1) + top_down_fib(n-2)
    return result

def bottom_up_fib(n):
    running_solns = [1, 1]
    while len(running_solns) < n:
        running_solns.append(running_solns[-1] + running_solns[-2])
    return running_solns[n-1]

print "top-down soln:", top_down_fib(7)
print "bottom-up soln:", bottom_up_fib(7)
