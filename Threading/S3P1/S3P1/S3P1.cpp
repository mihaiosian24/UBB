// S3P1.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include <iostream>
#include "mpi.h"

int main()
{
	MPI_Init(NULL, NULL);
	int ID = 0;
	MPI_Comm_rank(MPI_COMM_WORLD, &ID);
	static int world_size;
	MPI_Comm_size(MPI_COMM_WORLD, &world_size);
    std::cout << "Hello World!\n"; 

	if (ID == 0) {
		std::cout << "hey";
		double time1 = MPI_Wtime();
		std::cout << time1;
		double time = MPI_WTIME_IS_GLOBAL;
		std::cout << time;
		
	}
	else {
		double time1 = MPI_Wtime();
		std::cout << time1;
		
	std::cout << "nope";
	double time = MPI_WTIME_IS_GLOBAL;
	std::cout << time;
	}

	MPI_Finalize();
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
