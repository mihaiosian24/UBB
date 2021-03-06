// NColoring.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include <iostream>
#include <iostream>
#include "mpi.h"

const  int V=7;
bool isSafe(int v, int graph[][7], int color[],
	int c) {
	for (int i = 0; i < V; i++)
		if (graph[v][i] == 1 && c == color[i])
			return false;
	return true;
}
int main(int argc, char *argcv[])
{
	//std::cout << "Hello World";
	static int ID = 0;
	int SendData = 0;
	int ReceiveData = 0;


	MPI_Init(&argc, &argcv);
	int nrColors = 3;

	MPI_Comm_rank(MPI_COMM_WORLD, &ID);
	static int world_size;
	MPI_Comm_size(MPI_COMM_WORLD, &world_size);
	int jobs = world_size / 7;
	//std::cout << "Hello World";
	//std::cout << "Hello World!\n";
	
	int graph[4][4] = {
			  {0, 1, 1, 1},
			  {1, 0, 1, 0},
			  {1, 1, 0, 1},
			  {1, 0, 1, 0},
	};

	int graph2[7][7] = {
			{0, 1, 1, 1, 0, 0, 0},
			{1, 0, 0, 0, 1, 1, 1},
			{1, 0, 0, 1, 0, 0, 0},
			{1, 0, 1, 0, 0, 1, 1},
			{0, 1, 0, 0, 0, 0, 1},
			{0, 1, 0, 1, 0, 0, 1},
			{0, 1, 0, 1, 1, 1, 0},
	};
	MPI_Request request;
	MPI_Status status;
	if (ID == 0) {
		std::cout << "CEVA\n";
		int tag = 1;
		int v = 0;
		int color[V];
		for (int i = 0; i < V; i++) {
			color[i] = 0;
		}
		for (int i = 1; i <= nrColors; i++) {
			if (isSafe(v, graph2, color, i)) {
				color[v] = i;
				int value = v + 1;
				if (v == V) {
					for (int k = 0; k < V; k++) {
						std::cout << color[k];
					}
				}
				MPI_Send(&value, 1, MPI_INT, ID + 1, tag, MPI_COMM_WORLD);
				MPI_Send(&color, 7, MPI_INT, ID + 1, tag+1, MPI_COMM_WORLD);
				tag+=2;
			}
		}
		tag -= 1;
		MPI_Send(&tag, 1, MPI_INT, ID + 1, 0, MPI_COMM_WORLD);
		//MPI_Wait(&request,&status);

		

	}
	else if(ID>0&&ID<world_size){
		//MPI_Wait(&request, &status);
		//std::cout << "@2222";
		int tag = 1;
		int work = 1;
		int v = 0;
		int howManyTags = 0;
		int value = 0;
		int check = 0;
		MPI_Recv(&howManyTags, 1, MPI_INT, ID - 1, 0, MPI_COMM_WORLD, &status);
		int color[V];
		//std::cout << howManyTags;
		for (int i = 1; i <= howManyTags; i += 2) {
			MPI_Recv(&v, 1, MPI_INT, ID - 1, i, MPI_COMM_WORLD, &status);
			MPI_Recv(&color, 7, MPI_INT, ID - 1, i + 1, MPI_COMM_WORLD, &status);
			if (v == V&&check==0) {
				std::cout << "Solutie:";
				for (int h = 0; h < V; h++) {
					std::cout << color[h];
				}
				check = 1;
			}
			for (int h = 0; h < V; h++) {
				//std::cout << color[h];
			}
			if (color[0] == 1 && color[1] == 2 && color[2] == 3) {
				//std::cout << isSafe(v, graph2, color, 2);
				//std::cout << v;
				for (int h = 0; h < V; h++) {
					//std::cout << color[h];
				}
			}
			if (color[0] == 1 && color[1] == 2 && color[2] == 2) {
				//std::cout << isSafe(v, graph2, color, 3);
			}/*
			if (ID == world_size-1 ) {
				std::cout << "UNDE";
				for (int k = 1; k <= nrColors; k++) {
					if (isSafe(v, graph2, color, k)) {
						color[v] = k;
						std::cout << "AICI";
					}
				}
				for (int l = 0; l < V; l++) {
					std::cout << color[l];
				}
			}*/
			if (ID != world_size-1 )
			{
				//for (int j = 0; j < jobs; j++)
					for (int k = 1; k <= nrColors; k++) {
						if (isSafe(v, graph2, color, k)) {
							
							color[v] = k;
							//v += 1;
							value = v+1;
							MPI_Send(&value, 1, MPI_INT, ID + 1, tag, MPI_COMM_WORLD);
							MPI_Send(&color, 7, MPI_INT, ID + 1, tag + 1, MPI_COMM_WORLD);
							tag += 2;
							
						}
					}


					
				
			}

			
			
		}/*
		if (v == V && color[6]!=0) {
			std::cout << "Solutie";
			for (int z = 0; z <= V; z++) {
				//std::cout << "INtRa";
				std::cout << color[z];
			}
		}*/
		if (ID < world_size - 1) {
			tag -= 1;
			MPI_Send(&tag, 1, MPI_INT, ID + 1, 0, MPI_COMM_WORLD);
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
