#include <stdio.h>
#include <stdlib.h>

int main()
{
    // Variables
    char name[] = "Aiden";
    int age = 20;
    printf("%s is %d years old\n", name, age);

    // Datatypes
    int number = 40;
    double gpa = 8.3;
    float testFloat = 1.2;
    // As you can see, floats are real shit
    printf("%s\n", testFloat == 1.2 ? "true" : "false");

    char grade = 'A';
    char string[] = "array of chars";

    // Arrays and nested loops
    int nums[3][3] = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}};

    printf("%d\n", nums[0][1]);

    int i, j;
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("%d, ", nums[i][j]);
        }
        printf("\n");
    }

    return 0;
}
