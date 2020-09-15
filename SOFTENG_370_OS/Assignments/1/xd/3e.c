#include <stdio.h>
#include <unistd.h>
int main()
{
    int i = 0;
    while (i < 2)
    {
        if (fork() == 0)
        {
            fork();
            printf("A\n");
        }
        printf("B\n");
        i++;
    }
}