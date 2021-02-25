using System;
using System.Threading;

namespace Labb6
{

    class Program
    {
        public static int[] A = { 5, 4, 7, 2,1 ,7};
        public static int[] B = { 1, 9, 7, 3, 2 ,8};
        public static int[] sequncial = new int[B.Length+A.Length-1];
        public static int[] paralelized = new int[B.Length + A.Length - 1];
        public static int posa, posb;
        public static Mutex mtx = new Mutex();
        static void Main(string[] args)
        {
            setOutput();
            printOutput(sequncial);
            addsequencial();
            //printOutput(sequncial);
            sequencialKaratsuba();
            multiplyParallyzed();
            multiplyKatsubaParallyzed();
            Console.ReadLine();
            Console.WriteLine("Hello World!");
        }
        public static void setOutput()
        {
            for (int i = 0; i < sequncial.Length; i++)
            {
                sequncial[i] = 0;
                paralelized[i] = 0;

            }
        }
        public static void printOutput(int[] output)
        {
            for (int i = 0; i < output.Length; i++)
            {
                Console.WriteLine(output[i]);
            }
        }
        public static void addsequencial()
        {
            Console.WriteLine("O(n2)");
            for (int i = 0; i < 6; i++)
            {
                for (int j = 0; j < 6; j++)
                {
                    sequncial[i + j] += A[j] * B[i];
                }
            }
            printOutput(sequncial);

            Console.WriteLine("Karatsuba");


        }
        public static void sequencialKaratsuba()
        {
            setOutput();
            Console.WriteLine("Karatsuba");
            for(int i = 0; i< B.Length; i++)
            {
                Console.WriteLine("CEVA");
                for(int j = 0; j< A.Length; j++)
            {
                    if (i == 0 && j == 0 || i == B.Length-1 && j == A.Length-1)
                    {
                        sequncial[i + j] = A[j] * B[i];
                    }
                    else
                    {

                        if (j > i)
                        {

                            if ((i + j) % 2 == 0)

                            {

                                sequncial[i + j] += (B[i] + B[j]) * (A[i] + A[j]) - B[i] * A[i] - B[j] * A[j] ;
                               
                            }
                            else
                            {


                                sequncial[i + j] += (B[i] + B[j]) * (A[i] + A[j]) - B[i] * A[i] - B[j] * A[j];

                            }
                        }
                       
                    }
            }
            }
            int[] checks = new int[11];
            for(int i = 0; i < checks.Length; i++)
            {
                checks[i] = 0;
            }
            for (int i = 1; i < 5; i++)
            {
               
                for (int j = 1; j < 5; j++)
                {
                    if ((i + j) % 2 == 0)
                    {if (checks[i + j] == 0)
                        {
                            checks[i + j] = 1;
                            sequncial[i + j] += B[(i + j) / 2] * A[(i + j) / 2];
                        }
                    }
                }
            }
                    printOutput(sequncial);

        }
        public static void multiplyParallyzed()
        {
            setOutput();
            Console.WriteLine("O(n2) parallyzed");
            var job =  B.Length / 2;
            //var extra= (A.Length + B.Length) % 2;
            Thread t1 = new Thread(() =>
              {
                  int i = 0;
                  int j = 0;
                  while (i < job)
                  {
                      j = 0;
                      while (j < A.Length)
                      {
                          //mtx.WaitOne();
                          paralelized[i + j] += A[j] * B[i];
                          j++;
                          //mtx.ReleaseMutex();
                      }
                      i++;
                  }
                  
              });
            Thread t2 = new Thread(() =>
            {
                int k = B.Length/2;
                int z = 0;
                while (k < B.Length)
                {
                    z = 0;
                    while (z < A.Length)
                    {
                        //mtx.WaitOne();
                        paralelized[k + z] += A[z] * B[k];
                        z++;
                        //mtx.ReleaseMutex();
                    }
                    k++;
                }
                
            });
            t1.Start();
            t2.Start();
            t1.Join();
            t2.Join();
            //Thread.Sleep(10000);
            printOutput(paralelized);

        }

        public static void multiplyKatsubaParallyzed()
        {
            Console.WriteLine("Katsuba Parallyzed");
            setOutput();
            var job = B.Length / 2;
            //var extra= (A.Length + B.Length) % 2;
            Thread t1 = new Thread(() =>
            {
                int i = 0;
                int j = 0;
                while (i < job)
                {
                    j = i+1;
                    while (j < A.Length)
                    {
                        mtx.WaitOne();
                        //paralelized[i + j] += A[j] * B[i];
                        paralelized[i + j] += (B[i] + B[j]) * (A[i] + A[j]) - B[i] * A[i] - B[j] * A[j];
                        j++;
                        mtx.ReleaseMutex();
                    }
                    i++;
                }

            });
            Thread t2 = new Thread(() =>
            {
                int k = B.Length / 2;
                int z = 0;
                while (k < B.Length)
                {
                    z = k+1;
                    while (z < A.Length)
                    {
                        mtx.WaitOne();
                        //paralelized[k + z] += A[z] * B[k];
                        paralelized[k + z] += (B[k] + B[z]) * (A[k] + A[z]) - B[k] * A[k] - B[z] * A[z];
                        z++;
                        mtx.ReleaseMutex();
                    }
                    k++;
                }

            });
            t1.Start();
            t2.Start();
            t1.Join();
            t2.Join();
            int[] checks = new int[11];
            for (int i = 0; i < checks.Length; i++)
            {
                checks[i] = 0;
            }
            for (int i = 1; i < 5; i++)
            {

                for (int j = 1; j < 5; j++)
                {
                    if ((i + j) % 2 == 0)
                    {
                        if (checks[i + j] == 0)
                        {
                            checks[i + j] = 1;
                            paralelized[i + j] += B[(i + j) / 2] * A[(i + j) / 2];

                        }
                    }
                }
            }
            paralelized[0] = B[0] * A[0];
            paralelized[10] = B[B.Length-1] + A[A.Length-1];
            //Thread.Sleep(10000);
            printOutput(paralelized);

        }
    }
}
