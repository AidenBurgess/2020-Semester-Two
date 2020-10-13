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

Block read 4,259,840 -> 4,259,920

Read: 5

Write: 0

### h)

1 Read in Indirect block @ 4,259,840

1 Indirect block read  4,263,900 -> 4,263,936

1 Indirect block read  4,263,936-> 4,264,000 

Read: 3

Write: 0

### i)

1 Direct block read 4,259,820 -> 4,259,840

1 Direct block write 4,259,820 -> 4,259,840

1 Read in Indirect block @ 4,259,840

1 Indirect block read @ 4,259,840 -> 4,259,920 

1 Indirect block write @ 4,259,840 -> 4,259,920 

Read: 3

Write: 2

### j)

1 Direct block read 4,259,820 -> 4,259,840

1 Direct block write 4,259,820 -> 4,259,840





ALL MY WORKING IS WRONG BECAUSE I MESSED UP THE LEVELS BY 1