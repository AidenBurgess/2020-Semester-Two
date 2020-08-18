#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/mman.h>

int main()
{
    int *data;
    data = (int *)mmap(NULL, sizeof(int), PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
    if (data == (int *)-1)
    {
        perror("unable to allocate shared data");
        exit(EXIT_FAILURE);
    }
    *data = 0;
    if (fork() == 0)
    {
        puts("changing the value of *data");
        *data = 1;
    }
    else
    {
        while (*data == 0)
        {
            printf(".");
        }
    }
    puts("I have finished.");
    return 0;
}