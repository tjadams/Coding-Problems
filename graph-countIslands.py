def canDfs(graph, currentRow, currentCol, visited, maxRow, maxCol):
    canDfs = currentRow >= 0 and currentRow < maxRow and currentCol >= 0 and currentCol < maxCol and not visited[currentRow][currentCol] and graph[currentRow][currentCol] == 1
    return canDfs

def islandDfs(graph, row, col, visited, maxRow, maxCol):
    # look at all 8 adjacent indeces I
    # I I I
    # I X I
    # I I I
    rowIndeces = [-1,-1, -1, 0, 1, 1,  1,  0]
    colIndeces = [-1, 0,  1, 1, 1, 0, -1, -1]

    # length of the above arrays
    numIndeces = 8

    for indeceIndex in range(numIndeces):
        currentRow = row + rowIndeces[indeceIndex]
        currentCol = col + colIndeces[indeceIndex]
        if canDfs(graph, currentRow, currentCol, visited, maxRow, maxCol):
            visited[currentRow][currentCol] = True
            islandDfs(graph, currentRow, currentCol, visited, maxRow, maxCol)

def countIslands(graph, row, col):
    visited = [[False for j in range(col)] for i in range(row)]

    islandCount = 0
    for i in range(row):
        for j in range(col):
            if graph[i][j] == 1 and not visited[i][j]:
                islandDfs(graph, i, j, visited, row, col)
                islandCount += 1
    return islandCount

graph = [[1, 1, 0, 0, 0],
        [0, 1, 0, 0, 1],
        [1, 0, 0, 1, 1],
        [0, 0, 0, 0, 0],
        [1, 0, 1, 0, 1]]

row = len(graph)
col = len(graph[0])

print "Expect 5."
print "Number of islands is: "
print countIslands(graph, row, col)
