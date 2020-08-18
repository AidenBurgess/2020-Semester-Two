#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

void *work(void *arg)
{
    pthread_mutex_lock(&lock);
    puts("waiting in the second thread");

    pthread_cond_wait(&cond, &lock);
    puts("finished waiting in the second thread");

    pthread_mutex_unlock(&lock);
    pthread_cond_signal(&cond);
    puts("second thread sent the signal");
    return NULL;
}

int main(void)
{
    pthread_t thread;
    pthread_create(&thread, NULL, work, NULL);

    getchar();
    pthread_cond_signal(&cond);
    puts("main thread sent the signal");

    pthread_mutex_lock(&lock);
    puts("waiting in the main thread");

    pthread_cond_wait(&cond, &lock);
    puts("finished waiting in the main thread");
    pthread_mutex_unlock(&lock);
}