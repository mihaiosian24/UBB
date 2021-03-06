// NColoringOpenCl.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include <iostream>


    


#define CL_HPP_TARGET_OPENCL_VERSION 200
#include <CL/cl2.hpp>


	int main() {
		std::cout << "Hello World!\n";
		// get all platforms (drivers), e.g. NVIDIA
		std::vector<cl::Platform> all_platforms;
		cl::Platform::get(&all_platforms);

		if (all_platforms.size() == 0) {
			std::cout << " No platforms found. Check OpenCL installation!\n";
			exit(1);
		}
		cl::Platform default_platform = all_platforms[0];
		std::cout << "Using platform: " << default_platform.getInfo<CL_PLATFORM_NAME>() << "\n";

		// get default device (CPUs, GPUs) of the default platform
		std::vector<cl::Device> all_devices;
		default_platform.getDevices(CL_DEVICE_TYPE_ALL, &all_devices);
		if (all_devices.size() == 0) {
			std::cout << " No devices found. Check OpenCL installation!\n";
			exit(1);
		}

		// use device[1] because that's a GPU; device[0] is the CPU
		cl::Device default_device = all_devices[1];
		std::cout << "Using device: " << default_device.getInfo<CL_DEVICE_NAME>() << "\n";

		// a context is like a "runtime link" to the device and platform;
		// i.e. communication is possible
		cl::Context context({ default_device });

		// create the program that we want to execute on the device
		cl::Program::Sources sources;

		// calculates for each element; C = A + B
		/*std::string kernel_code =
			"   void kernel simple_add(global const int* A, global const int* B, global int* C, "
			"                          global const int* N) {"
			"       int ID, Nthreads, n, ratio, start, stop;"
			""
			"       ID = get_global_id(0);"
			"       Nthreads = get_global_size(0);"
			"       n = N[0];"
			""
			"       ratio = (n / Nthreads);"  // number of elements for each thread
			"       start = ratio * ID;"
			"       stop  = ratio * (ID + 1);"
			""
			"       for (int i=start; i<stop; i++)"
			"           C[i] = A[i] + B[i];"
			"   }";*/
		std::string kernel_code =
			"   void kernel simple_Colors(  global const int* G,  int v,global  int* N)  "
			"                          {"
			"  int id=get_global_id(0);"
			"if(id==0)"
			"  N[0]=5;"
			"     if(v!=7)"
			"     for(int i=1;i<=3;i++){  "
			"          int check=0;"
			"			for(int j=0;j<7;j++){		"
			"               if(G[id*7+j]==1 && N[id]==i)    "
			"						check=1;  "
			"            }"
			"          if(check==0){"

			"              N[id]=i; "
			"                v++;"
			"				 "
			"               }"
			"    }   "
			"         "
			"   }";
		sources.push_back({ kernel_code.c_str(), kernel_code.length() });

		cl::Program program(context, sources);
		if (program.build({ default_device }) != CL_SUCCESS) {
			std::cout << "Error building: " << program.getBuildInfo<CL_PROGRAM_BUILD_LOG>(default_device) << std::endl;
			exit(1);
		}

		// apparently OpenCL only likes arrays ...
		// N holds the number of elements in the vectors we want to add
		
		cl_int N[7] = { 0,0,0,0,0,0,0 };
		const int n = 7;
		cl_int graph2[7][7] = {
			{0, 1, 1, 1, 0, 0, 0},
			{1, 0, 0, 0, 1, 1, 1},
			{1, 0, 0, 1, 0, 0, 0},
			{1, 0, 1, 0, 0, 1, 1},
			{0, 1, 0, 0, 0, 0, 1},
			{0, 1, 0, 1, 0, 0, 1},
			{0, 1, 0, 1, 1, 1, 0},
		};
		cl_int v = 0;
		// create buffers on device (allocate space on GPU)
		cl::Buffer buffer_A(context, CL_MEM_READ_WRITE, sizeof(int) * n);
		cl::Buffer buffer_B(context, CL_MEM_READ_WRITE, sizeof(int) * n);
		cl::Buffer buffer_C(context, CL_MEM_READ_WRITE, sizeof(int) * n);
		cl::Buffer buffer_N(context, CL_MEM_READ_WRITE, sizeof(int)*n);
		cl::Buffer buffer_G(context, CL_MEM_READ_ONLY, sizeof(int) * n);
		cl::Buffer buffer_V(context, CL_MEM_READ_WRITE, sizeof(int));
		// create things on here (CPU)
		int A[n], B[n];
		for (int i = 0; i < n; i++) {
			A[i] = i;
			B[i] = n - i - 1;
		}
		// create a queue (a queue of commands that the GPU will execute)
		cl::CommandQueue queue(context, default_device);

		// push write commands to queue
		queue.enqueueWriteBuffer(buffer_G, CL_TRUE, 0, sizeof(int)*n, graph2);
		queue.enqueueReadBuffer(buffer_N, CL_TRUE, 0, sizeof(int)*n, N);
		queue.enqueueWriteBuffer(buffer_V, CL_TRUE, 0, 1, &v);

		// RUN ZE KERNEL
		//cl::KernelFunctor simple_add(cl::Kernel(program, "simple_add"), queue, cl::NullRange, cl::NDRange(10), cl::NullRange);
		cl::Kernel simple_Colors(program,"simple_Colors");
		simple_Colors.setArg(0, &graph2);
		simple_Colors.setArg(1, v);
		simple_Colors.setArg(2, &N);
		

		//simple_Colors();
		//cl::make_kernel simple_add(cl::Kernel(program, "simple_add"));
		//cl::EnqueueArgs eargs(queue, cl::NullRange, cl::NDRange(10), cl::NDRange(10));
		//simple_Colors(eargs, buffer_G, buffer_V, buffer_N).wait();
		cl::Program program2("simple_Colors");
		//program2.build();
		program2.build()
		
		int C[n];
		// read result from GPU to here
		//queue.enqueueReadBuffer(buffer_C, CL_TRUE, 0, sizeof(int)*n, C);
		//program.compile();
	//cl:CL_EXEC_KERNEL(&simple_Colors);
		std::cout << "result: {";
		for (int i = 0; i < n; i++) {
			std::cout << N[i] << " ";
		}
		std::cout << "}" << std::endl;
		
		return 0;
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
