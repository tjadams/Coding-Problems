class Trie:
    def __init__(self, value = ''):
        # root
        self.value = value
        # dictionary where key is a character (like Trie.value)
        # and value is another Trie
        self.dictionary = {}

def insert_word(trie, word):
    if len(word) == 0: return

    if word[0] not in trie.dictionary:
        trie.dictionary[word[0]] = Trie(word[0])
    insert_word(trie.dictionary[word[0]], word[1:])

def is_in_trie(trie, word):
    '''
    word: word to check if is in Trie
    output: true if word is in Trie
    '''

    trie_pointer = trie
    for letter in word:
        if letter not in trie_pointer.dictionary:
            return False
        #move to next part of the trie
        trie_pointer = trie_pointer.dictionary[letter]
    return True

if __name__=="__main__":
    trie = Trie()
    insert_word(trie, 'GEEKS')
    print 'expect true', is_in_trie(trie, 'GEEKS')
    print 'expect true', is_in_trie(trie, 'GEE')
    print 'expect false', is_in_trie(trie, 'abc')
    print 'expect false', is_in_trie(trie, 'GEEKSss')
