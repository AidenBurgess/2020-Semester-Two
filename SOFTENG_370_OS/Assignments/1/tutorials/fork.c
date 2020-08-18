#include <unistd.h>
#include <stdlib.h>

int main()
{
    int i = 0;
    while (i < 2)
    {
        vfork();
        system("ps -o pid,ppid,comm,stat");
        i++;
    }
}