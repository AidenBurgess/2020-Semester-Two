# SOFTENG 370 Assignment 3

Name: Aiden Burgess

UPI: abur970

## Question 1

Number of ptrs/block = 4KB/4 = 1024 ptrs/block

Assume that we can use blocks defined in previous questions

### a) 

16 * 4KB = 65,536 B

### b)

65,536 + 1024 * 4KB = 4,259,840 B

### c) 

4,259,840 + 1024^2 * 4KB = 4,299,227,136 B

### d) 

4,299,227,136 + 1024^3 * 4KB = 4,402,345,738,240 B

### e) 

6 levels (1024^5 * 4KB = 1024^6 * 4B)

### f)

Direct block read 4,050-> 4,150

Read: 1

Write: 0

### g)

Read in single indirect block

Block read 4,259,820 -> 4,259,840

Read in double indirect block

Read in 2nd layer double indirect block

Read 4,259,840 -> 4,259,920

Read: 5

Write: 0

### h)

Read in double indirect block @ 4,259,840

Read 2nd layer double indirect block @ 4,259,840

Read 4,263,900 -> 4,263,936

Read 2nd layer double indirect block @ 4,263,936

Read 4,263,936-> 4,264,000 

Read: 5

Write: 0

### i)

Read in single indirect block

Write 4,259,820 -> 4,259,840

Read in double indirect block @ 4,259,840

Read 2nd layer double indirect block @ 4,259,840

Write @ 4,259,840 -> 4,259,920 

Write on disk inode update

Read: 3

Write: 3

### j)

Read in single indirect block

Write 4,259,820 -> 4,259,840

Read in double indirect block @ 4,259,840

Assign and Write 2nd layer double indirect block @ 4,259,840 to 1st layer

Assign and Write 2nd layer double indirect block @ 4,259,840 new block

Write 4,259,820 -> 4,259,840

Write on disk inode update

Read: 2

Write: 4

### k)

1. The inode itself is a block on disk, so if we did not have this assumption, then there would at least be an extra read for each  operation.
2. If the blocks are already in memory at the start, then there is no need to read them in from disk in the operations. **Unsure why the second part is needed**
3. File access time writing would add at least one extra write to each operation, and this is not integral to the main task of reading/writing from/to the disk
4. This assumption is made due to j), which requires new blocks to be assigned. Without this assumption, there would be an extra read.
5. **Unsure why this assumption needs to be made**
6. This assumption clarifies assumption (5), as we only need one write per inode update. **Unsure why several changes can be made with one write is important**
7. Some file systems use clusters of blocks, which may affect our read calculations. As this cluster information is not provided, it is best to make this assumption.

