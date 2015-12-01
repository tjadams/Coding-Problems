#ds-algo
Notes and examples made from learning about data structures and algorithms. I have **bolded** what is especially important for me.

# Bit Manipulation
## Tricks
+ and | are OR, * and & are AND, ^ is XOR, ~ is NOT, << is left shift
* x plus x = x times two = x << 1
* Multiplying x by 2 to the power of n just shifts x to the left by n
* x ^ (~x) = 1... 

ex that explains the elipsis:
111 ^ (~111) = 111

* x & (1 << n) clears the n rightmost bits of x

ex:
1111 & (1 << 3)
= 1111 & (1000)
= 1000

## Facts
* Big Endian: MSB (most significant bit - the bit that represents the largest value) is the leftmost digit.
ex:
1000 in binary is 1*2^3 + 0*2^2 + 0*2^1 + 0*2^0 = 8 in decimal

* Little Endian: MSB is the rightmost digit
ex:
0001 in binary is 0*2^0 + 0*2^1 + 0*2^2 + 1*2^3 = 8 in decimal

* A sequence of ones or zeros is commonly denoted with an s postfix as such: 1s and 0s
* x ^ 0 = x  ,  x & 0 = 0 , x + 0 = x
* ** x ^ 1s = ~x , x & 1s = x , x + 1s = 1s    Here 1s has an equal length of bits as x but all of them are 1**
* x ^ x = 0 , x & x = x , x + x = x
* Get Bit:

#TODO
* Rewrite this in Latex