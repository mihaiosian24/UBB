// MPI.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include <iostream>
#include "mpi.h"

int main(int argc,char *argcv[])
{
	static int ID = 0;
	int SendData = 0;
	int ReceiveData = 0;
	
	
	MPI_Init(&argc,&argcv);
	
	
	MPI_Comm_rank(MPI_COMM_WORLD, &ID);
	static int world_size;
	MPI_Comm_size(MPI_COMM_WORLD, &world_size);
	std::cout << "Hello World";
	static int data[6] = { 1,2,3,4,5,6 };


	static int data4[7] = { 2,3,4,5,6,7,8 };
	const int  length = 13;
	static int result[length];
	for (int i = 0; i < length; i++) {
		result[i] = 0;
	}
	int nrjobs = 6 / world_size;
	int extrajobs = 6 % world_size;
	MPI_Status status;
	
	if (ID == 0) 
	{
		
		
		static int check = 0;
		std::cout << world_size;
		int nrjobs = 6 / world_size;
		std::cout << nrjobs;
		int extrajobs = length % world_size;
		SendData = 100;
		std::cout << "HEY";
		//MPI_Send(&SendData, 1, MPI_INT, 1, 0, MPI_COMM_WORLD);
		for (int i = ID * nrjobs; i < (ID + 1)*nrjobs; i++) {
			for (int j = 0; j < 7; j++) {
				result[i + j] += data[i] *data4[j];
			}

		}
		std::cout << "Result";
		for (int i = 0; i < 13; i++) {
			std::cout << result[i] << " ";
		}
		//MPI_Send(&check, 1, MPI_INT, 1, 8, MPI_COMM_WORLD);
		//MPI_Send(&result,13, MPI_INT, 1, 7, MPI_COMM_WORLD);
		if (ID == (world_size - 2)) {
			MPI_Send(&result, 13, MPI_INT,ID+1, 4, MPI_COMM_WORLD);
		}
		else {
			MPI_Send(&result, 13, MPI_INT, ID+1, 7, MPI_COMM_WORLD);
		}
		std::cout << "HEY";
		std::cout << world_size;
	}
	else/*
	if (ID > 0 && ID<(world_size-1))
	{
		std::cout << "Hello World!\n";
		MPI_Recv(&result, 13, MPI_INT, 0, 7, MPI_COMM_WORLD, &status);
		//std::cout << "Receive Data is \t" << ReceiveData;
		for (int i = ID *nrjobs; i < (ID + 1)*nrjobs; i++) {
			for(int j=0;j<7;j++){
				result[i + j] += data[i]* data2[j];
			}
				
		}
		
		MPI_Send(&result,13, MPI_INT, 1, 7, MPI_COMM_WORLD);
		
	}*/
		if (ID % 2 == 1 && ID < (world_size - 1)) {
			MPI_Recv(&result, 13, MPI_INT, ID-1, 7, MPI_COMM_WORLD, &status);
			for (int i = ID * nrjobs; i < (ID + 1)*nrjobs; i++) {
				for (int j = 0; j < 7; j++) {
					result[i + j] = data[i] * data4[j];
				}
			}
			if (ID == (world_size - 2)) {
				MPI_Send(&result, 13, MPI_INT, ID+1, 4, MPI_COMM_WORLD);
			}
			else {
				MPI_Send(&result, 13, MPI_INT, ID+1, 8, MPI_COMM_WORLD);
			}
	}else
			if (ID % 2 == 0 && ID<(world_size-1)) {
				MPI_Recv(&result, 13, MPI_INT, ID-1, 8, MPI_COMM_WORLD, &status);
				for (int i = ID * nrjobs; i < (ID + 1)*nrjobs; i++) {
					for (int j = 0; j < 7; j++) {
						result[i + j] = data[i] * data4[j];
					}
				}
				if (ID == (world_size - 2)) {
					MPI_Send(&result, 13, MPI_INT, ID+1, 4, MPI_COMM_WORLD);
				}
				else {
					MPI_Send(&result, 13, MPI_INT, ID+1, 7, MPI_COMM_WORLD);
				}
			}
			else
	if (ID == (world_size-1) ){
		//int data1[6] = { 1,2,3,4,5,6 };

		//static int ceva = 0;
		//MPI_Recv(&ceva, 1, MPI_INT, 0, 8, MPI_COMM_WORLD, &status);
		//ceva++;
		//int data5[7] = { 2,3,4,5,6,7,8 };
		const int  length = 13;
		//int result[length];
		static int puppet[length];
		for (int i = 0; i < length; i++) {
			puppet[i] = 0;
		}
		
		std::cout << "Hello World!\n";
		for (int i = ID * nrjobs; i < (ID + 1)*nrjobs + extrajobs; i++) {
			for (int j = 0; j < 7; j++) {
				puppet[i + j] += data[i] * data4[j];
			}

		}
		std::cout << "Result";
		for (int i = 0; i < 13; i++) {
			std::cout << puppet[i] << " ";
		}
		MPI_Recv(&result, 13, MPI_INT, ID-1, 4, MPI_COMM_WORLD, &status);
		//std::cout << "Receive Data is \t" << ReceiveData;
		/*
		for (int i = ID * nrjobs; i < (ID + 1)*nrjobs+extrajobs; i++) {
			for (int j = 0; j < 7; j++) {
				result[i + j] += data[i] *data4[j];
			}

		}*/
		std::cout << "Result";
		for (int i = 0; i < 13; i++) {
			std::cout << result[i] << " ";
		}
		for (int i = 0; i < length; i++) {
			//for (int j = 0; j < length; j++) {
				result[i] += puppet[i ];
			//}
		}
	std::cout << "Result";
		for (int i = 0; i < 13; i++) {
			std::cout << result[i] << " ";
		}

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
