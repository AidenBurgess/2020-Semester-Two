/*
    The sorting program to use for Operating Systems Assignment 1 2020
    written by Robert Sheehan

    Modified by: Aiden Burgess
    UPI: abur970

    By submitting a program you are claiming that you and only you have made
    adjustments and additions to this code.
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/resource.h>
#include <stdbool.h>
#include <sys/times.h>
#include <pthread.h>

#define SIZE 10

static pthread_t t1;
static pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
static pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

static struct block sharedState;
static bool mainFree = false;
static bool childFree = true;
static bool sortCompleted = false;

struct block
{
    int size;
    int *data;
};

void print_data(struct block my_data)
{
    for (int i = 0; i < my_data.size; ++i)
        printf("%d ", my_data.data[i]);
    printf("\n");
}

/* Split the shared array around the pivot, return pivot index. */
int split_on_pivot(struct block my_data)
{
    int right = my_data.size - 1;
    int left = 0;
    int pivot = my_data.data[right];
    while (left < right)
    {
        int value = my_data.data[right - 1];
        if (value > pivot)
        {
            my_data.data[right--] = value;
        }
        else
        {
            my_data.data[right - 1] = my_data.data[left];
            my_data.data[left++] = value;
        }
    }
    my_data.data[right] = pivot;
    return right;
}

void *quick_sort_normal(void *addr)
{
    struct block my_data = *(struct block *)addr;
    if (my_data.size < 2)
        return NULL;
    int pivot_pos = split_on_pivot(my_data);

    struct block left_side, right_side;

    left_side.size = pivot_pos;
    left_side.data = my_data.data;
    right_side.size = my_data.size - pivot_pos - 1;
    right_side.data = my_data.data + pivot_pos + 1;

    quick_sort_normal(&left_side);
    quick_sort_normal(&right_side);
}

/* Quick sort the data. */
void *quick_sort(void *addr)
{
    struct block my_data = *(struct block *)addr;

    if (my_data.size < 2)
        return NULL;
    int pivot_pos = split_on_pivot(my_data);

    struct block left, right;
    left.size = pivot_pos;
    left.data = my_data.data;
    right.size = my_data.size - pivot_pos - 1;
    right.data = my_data.data + pivot_pos + 1;

    pthread_mutex_lock(&lock);
    if (childFree)
    {
        sharedState = right;
        childFree = false;
        // puts("sending sig");
        pthread_cond_signal(&cond);
        pthread_mutex_unlock(&lock);
    }
    else
    {
        pthread_mutex_unlock(&lock);
        quick_sort(&right);
    }
    quick_sort(&left);
}

void *child_thread(void *addr)
{
    puts("Starting loop");
    pthread_mutex_lock(&lock);
    struct block temp;
    do
    {
        while (childFree)
        {
            pthread_cond_wait(&cond, &lock);
        }
        // puts("Child is processing");
        temp.data = sharedState.data;
        temp.size = sharedState.size;
        quick_sort_normal(&temp);

        childFree = true;
    } while (!sortCompleted);
    pthread_mutex_unlock(&lock);
}

/* Check to see if the data is sorted. */
bool is_sorted(struct block my_data)
{
    bool sorted = true;
    for (int i = 0; i < my_data.size - 1; i++)
    {
        if (my_data.data[i] > my_data.data[i + 1])
            sorted = false;
    }
    return sorted;
}

/* Fill the array with random data. */
void produce_random_data(struct block my_data)
{
    srand(1); // the same random data seed every time
    for (int i = 0; i < my_data.size; i++)
    {
        my_data.data[i] = rand() % 1000;
    }
}

int main(int argc, char *argv[])
{
    long size;

    if (argc < 2)
    {
        size = SIZE;
    }
    else
    {
        size = atol(argv[1]);
    }
    struct block start_block;
    start_block.size = size;
    start_block.data = (int *)calloc(size, sizeof(int));
    if (start_block.data == NULL)
    {
        printf("Problem allocating memory.\n");
        exit(EXIT_FAILURE);
    }

    produce_random_data(start_block);

    if (start_block.size < 1001)
        print_data(start_block);

    struct tms start_times, finish_times;
    times(&start_times);
    printf("start time in clock ticks: %ld\n", start_times.tms_utime);

    pthread_create(&t1, NULL, *child_thread, NULL);
    quick_sort(&start_block);

    pthread_mutex_lock(&lock);
    sortCompleted = true;
    childFree = false;
    pthread_cond_signal(&cond);

    pthread_mutex_unlock(&lock);

    puts("we have exited");
    pthread_join(t1, NULL);
    times(&finish_times);
    printf("finish time in clock ticks: %ld\n", finish_times.tms_utime);

    if (start_block.size < 1001)
        print_data(start_block);

    printf(is_sorted(start_block) ? "sorted\n" : "not sorted\n");

    free(start_block.data);
    exit(EXIT_SUCCESS);
}