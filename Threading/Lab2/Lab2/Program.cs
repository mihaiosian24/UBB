using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Lab2
{
    class Program
    {

        static void Main(string[] args)
        {
            int[][] matrix1 = new int[4][];
            matrix1[0] = new int[4];
            matrix1[1] = new int[4];
            matrix1[2] = new int[4];
            matrix1[3] = new int[4];
            int[][] sum = new int[4][];
            sum[0] = new int[4];
            sum[1] = new int[4];
            sum[2] = new int[4];
            sum[3] = new int[4];
            int[][] matrix3 = new int[3][];
            matrix3[0] = new int[4];
            matrix3[1] = new int[4];
            matrix3[2] = new int[4];
            int[][] prod = new int[4][];
            prod[0] = new int[4];
            prod[1] = new int[4];
            prod[2] = new int[4];
            prod[3] = new int[4];
            for (int i = 0; i < 4; i++)
            {
                for(int j = 0; j < 4; j++)
                {
                    prod[i][j] = 0;
                }
            }
            /// int[][] sum = matrix1;
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    matrix1[i][j] = j;
                }
            }

           

            int[][] matrix2 = matrix1;
            Thread[] threads = new Thread[16];
            int z = -1;
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4;  j++)
                {
                    //Console.WriteLine(sum[j].Count());
                    //if(i<4 && j<4)
                    z++;
                    threads[z]=new Thread(() =>
                    {
                        int auxi = i;
                         int auxj = j;
                        sum[auxi][auxj] = matrix1[auxi][auxj] + matrix2[auxi][auxj];
                    });

                    //threads[z].Start();
                    //threads[z].Join();
                    //threads.Add(thread);
                }
                
            }
            Parallel.Invoke(() => { for (int i = 0; i < 4; i++)
                    for (int j = 0; j < 4; j++)
                        sum[i][j] = matrix1[i][j] + matrix2[i][j];
                    });
            Parallel.Invoke(() =>
            {
                for(int i = 0; i < 4; i++)
                {
                    for(int j = 0; j < 4; j++)
                    {
                        for (int k = 0; k < 4; k++) {
                            prod[i][j] = prod[i][j] + matrix1[i][k] * matrix2[k][j];


                        }                   
                    }
                }
            });
           /*
            for(int a=0;a<z;a++)
            {
                threads[a].Start();
            }
            */
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    Console.WriteLine(matrix1[i][j]);
                    Console.WriteLine(matrix2[i][j]);
                    Console.WriteLine(sum[i][j]);
                    Console.WriteLine(prod[i][j]);
                }
            }

          
            Console.ReadLine();
        }
    }
}
