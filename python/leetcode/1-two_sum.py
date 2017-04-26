class Solution(object):
    def twoSum(self, nums, target):
        for i in range(len(nums) - 1): # starts at i = 0
            for j in range(i+1, len(nums)): # starts at i + 1
                if nums[i] + nums[j] == target: return [i, j]

    def twoSumWithDictionary(self, nums, target):
        dictionary = {}
        for i in range(len(nums)):
            if target - nums[i] in dictionary:
                return [dictionary[target - nums[i]], i]
            dictionary[nums[i]] = i

if __name__=="__main__":
    x = Solution()
    print str(x.twoSum([2, 7, 11, 15], 9)) # expect [0, 1]
    print str(x.twoSum([2, 7, 11, 15], 26)) # expect [2, 3]
    print str(x.twoSum([2, 7, 11, 15], 22)) # expect [1, 3]
    print str(x.twoSumWithDictionary([2, 7, 11, 15], 9)) # expect [0, 1]
    print str(x.twoSumWithDictionary([2, 7, 11, 15], 26)) # expect [2, 3]
    print str(x.twoSumWithDictionary([2, 7, 11, 15], 22)) # expect [1, 3]

'''
cant use same element twice so does that mean i cant use the same number twice or the same array element twice? Assume cant use same array elemnt

Method 1 (naive):
(took me ~17 mins including time to google syntax, has a slow runtime though) naive way: What are all combinations of 2 elements, sum em all
so this is multiset or permutation or one of those things where you just pick two and then add em

2, 7
2, 11
2, 15

7, 11
7, 15

11, 15

2 loops

outer loop is the left number in the addition except for the last number
inner loop is the right number in the addition

Method 2 (modified binary search, only works if we know its sorted though...)

Method 3 (looked at solution to figure out how they did this but not how they coded it)
Hashmap looking for target - current number, only looks through list once so it's O(n))
Took me ~15 minutes due to syntax issues
'''
