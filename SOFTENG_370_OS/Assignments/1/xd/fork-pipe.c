#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(void) {
	int my_pipe[2];

	if (pipe(my_pipe) == -1) {
		perror("Creating pipe");
		exit(EXIT_FAILURE);
	}
	int cpid = fork();
	if (cpid == -1) {
		perror("Creating child");
		exit(EXIT_FAILURE);
	}
	if (cpid != 0) { // the parent
		int size;
		read(my_pipe[0], &size, sizeof(size));
		for (int i = 0; i < size; i++) {
			read(my_pipe[1], &i, sizeof(int));
			printf("%d ", i);
		}
	} else { // the child
		int size = 1000;
		write(my_pipe[1], &size, sizeof(size));
		for (int i = 0; i < size; i++) {
			write(my_pipe[1], &i, sizeof(int));
		}		
	}
}