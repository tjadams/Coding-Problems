def boggleDfs(boggle, word, row, col, visited, string):
    if string == word:
        print word
        return

    # look at all adjacent nodes
    rowIndeces = [-1,-1, -1, 0, 1, 1,  1,  0]
    colIndeces = [-1, 0,  1, 1, 1, 0, -1, -1]
    maxRow = len(boggle)
    maxCol = len(boggle[0])

    for k in range(8):
        currentRow = row + rowIndeces[k]
        currentCol = col + colIndeces[k]
        # Tricky thing: had to check len(string) < len(word) to do my neat little word string check thing
        if currentRow >= 0 and currentRow < maxRow and currentCol >= 0 and currentCol < maxCol and len(string) < len(word) and boggle[currentRow][currentCol] == word[len(string)] and not visited[currentRow][currentCol]:
            string += boggle[currentRow][currentCol]
            visited[currentRow][currentCol] = True
            boggleDfs(boggle, word, currentRow, currentCol, visited, string)

def solveBoggle(boggle, dictionary):
    for word in dictionary:
        # Only want to print once per word so refresh this visited 2-d
        visited = [[False for col in range(len(boggle[0]))] for row in range(len(boggle))]
        for row in range(len(boggle)):
            for col in range(len(boggle[0])):
                boggleDfs(boggle, word, row, col, visited, '')

dictionary = ['GEEKS', 'FOR', 'QUIZ', 'GO']
boggle = [['G', 'I', 'Z'],
          ['U', 'E', 'K'],
          ['Q', 'S', 'E']]

print 'Expect GEEKS and QUIZ'
solveBoggle(boggle, dictionary)
