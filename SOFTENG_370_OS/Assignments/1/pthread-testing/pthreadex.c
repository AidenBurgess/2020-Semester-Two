#include <stdio.h>
#include <pthread.h>

int counter = 0;

void *increment_counter(void *id)
{
    for (int i = 0; i < 1000000; i++)
    {
        counter++;
    }
    printf("thread: %ld counter: %d\n", (long)id, counter);
    return (NULL);
}

int main()
{
    int const num_threads = 20;
    pthread_t thread_refs[num_threads];

    for (long i = 0; i < num_threads; i++)
    {
        pthread_create(&thread_refs[i], NULL, increment_counter, (void *)i);
    }
    for (long i = 0; i < num_threads; i++)
    {
        pthread_join(thread_refs[i], NULL);
    }
    printf("final value of counter: %d\n", counter);
}
