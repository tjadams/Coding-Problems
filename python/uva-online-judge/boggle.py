#https://uva.onlinejudge.org/contests/177-222ee69b/p5.html
#Could get help from http://www.geeksforgeeks.org/boggle-set-2-using-trie/
def solve_boggle(board, dictionary):
    '''
    board: a 2-D list
    dictionary: 1-D list
    output: prints if found word
    '''
    for word in dictionary:
        for word_pos in range(0, len(word)):
            letter = word[word_pos]
            for row in range(len(board)):
                for col in range(len(board[row])):
                    if board[row][col] == letter:
                        traverse_for_word(board, row, col, word)

def traverse_for_word(board, row, col, word):
    build a trie?

if __name__=="__main__":
    board = [['T', 'N', 'X', 'O'],
             ['A', 'A', 'E', 'I'],
             ['I', 'O', 'S', 'R'],
             ['B', 'F', 'R', 'H']]
    dictionary = ['TAXES', 'RISE', 'ANNEX', 'BOAT', 'OATS', 'FROSH', 'HAT', 'TRASH']
    solve_boggle(board, dictionary)

'''
Started at ~10pm took like 10 mins to copy down the text for the board and dict and read htep roblem
Naive solution: look for each word in dictionary in the board, letter by letter.
'''
