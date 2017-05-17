def initVisited(row, col):
    # python makes 2d lists in a weird way
    visited = []
    for i in range(row):
        visitedNest = []
        for j in range(col):
            visitedNest.append(False)
        visited.append(visitedNest)
    return visited

def islandDfs(graph, row, col, visited, maxRow, maxCol):
    # look at all 8 adjacent indeces I
    # I I I
    # I X I
    # I I I

    # TODO: does order matter? I'm going clockwise starting at top left
    rowIndeces = [-1,-1, -1, 0, 1, 1,  1,  0]
    colIndeces = [-1, 0,  1, 1, 1, 0, -1, -1]

    # length of the above arrays
    numIndeces = 8

    for indeceIndex in range(numIndeces):
        currentRow = row + rowIndeces[indeceIndex]
        currentCol = col + colIndeces[indeceIndex]
        if currentRow >= 0 and currentRow < maxRow and currentCol >= 0 and currentCol < maxCol and not visited[currentRow][currentCol] and graph[currentRow][currentCol] == 1:
            visited[currentRow][currentCol] = True
            islandDfs(graph, currentRow, currentCol, visited, maxRow, maxCol)

def countIslands(graph, row, col):
    # init visited[row][col] to false
    visited = initVisited(row, col)
    # TODO figure out how they init visited, probably list comprehension

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

print "Expect 5. Number of islands is: "
print countIslands(graph, row, col)
