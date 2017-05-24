def spiralCopy(inputMatrix):
  ctr = 0 # outer loop ctr for spirals
  cur_row, cur_col = 0, 0
  row = len(inputMatrix)
  col = len(inputMatrix[0])
  output = []

  while len(output) < row*col:
      # cols left to right
      for cur_col in range(ctr, col-ctr):
        output.append(inputMatrix[ctr][cur_col])

      # rows up to down
      for cur_row in range(ctr, row-ctr):
         output.append(inputMatrix[cur_row][col-ctr])

      # cols right to left (assume python range can go from big number to small number)
      for cur_col in range(col-ctr, ctr):
        output.append(inputMatrix[row-ctr][cur_col])

       # row down to up
      for cur_row in range(row-ctr, ctr+1):
        output.append(inputMatrix[cur_row][ctr])

      ctr += 1 # when you're going towards another inner spiral
    return output

inputMatrix  = [ [1,    2,   3,  4,    5],
               [6,    7,   8,  9,   10],
               [11,  12,  13,  14,  15],
               [16,  17,  18,  19,  20] ]

# possible soln is recursive where each time you shrink the matrix dow nby one

# I probably messed up and had the while loop
