using System;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Timers;
using System.Diagnostics;

namespace Lab2
{
    class Program
    {
        private static int nrLines = 4;
        private static int[,] Matrix1, Matrix2, MatrixResult,MatrixMultiply;

        static void Main(string[] args)
        {
            Stopwatch sw = new Stopwatch();
            sw.Start();
            Matrix1 = GenerateRandomMatrix(nrLines);
            Matrix2 = GenerateRandomMatrix(nrLines);
            MatrixResult = new int[nrLines, nrLines];
            MatrixMultiply = new int[nrLines, nrLines];
            DisplayMatrix(Matrix1);
            //Console.WriteLine();
            DisplayMatrix(Matrix2);

            AddMatrix(10);
            //sw.Stop();
            //Console.WriteLine(sw.ElapsedMilliseconds / 1000.0);
            Console.WriteLine();
            Console.WriteLine();

            DisplayMatrix(MatrixResult);
            MultiplyMatrix(10);
            DisplayMatrix(MatrixMultiply);
            Console.ReadLine();
        }

        static void DisplayMatrix(int[,] matrix)
        {
            for (int i = 0; i < nrLines; i++)
            {
                for (int j = 0; j < nrLines; j++)
                    Console.Write(matrix[i, j] + " ");
                Console.Write('\n');
            }
        }
        static void AddMatrix(int nrT)
        {
            var nrElems = nrLines * nrLines;
            var threadJob = nrElems / nrT;
            var extraJob = nrElems - nrT * threadJob;
            var start = 0;
            var end = threadJob;
            if (extraJob > 0)
            {
                end++;
                extraJob--;
            }
            Thread[] threads = new Thread[nrT + 1];

            for (int i = 0; i < nrT; i++)
            {
                var local = i;
                var localStart = start;
                var localEnd = end;
                threads[i] = new Thread(() => AddMatrixThread(localStart, localEnd));
                start = end;
                end += threadJob;
                if (extraJob > 0)
                {
                    end++;
                    extraJob--;
                }
                threads[i].Start();
            }

            for (int i = 0; i < nrT - 1; i++)
            {
                threads[i].Join();
            }
        }

        static void MultiplyMatrix(int nrT)
        {
            var nrElems = nrLines * nrLines;
            var threadJob = nrElems / nrT;
            var extraJob = nrElems - nrT * threadJob;
            var start = 0;
            var end = threadJob;
            if (extraJob > 0)
            {
                end++;
                extraJob--;
            }
            Thread[] threads = new Thread[nrT + 1];

            for (int i = 0; i < nrT; i++)
            {
                var local = i;
                var localStart = start;
                var localEnd = end;
                threads[i] = new Thread(() => MultiplyMatrixThread(localStart, localEnd));
                start = end;
                end += threadJob;
                if (extraJob > 0)
                {
                    end++;
                    extraJob--;
                }
                threads[i].Start();
            }

            for (int i = 0; i < nrT - 1; i++)
            {
                threads[i].Join();
            }
        }

        static void AddMatrixThread(int start, int end)
        {
         
            while (start < end)
            {
                var row = start / nrLines;
                var col = start % nrLines;

                MatrixResult[row, col] = Matrix1[row, col] + Matrix2[row, col];
                start++;
            }
        }

        static void MultiplyMatrixThread(int start, int end)
        {
            while (start < end)
            {
                var row = start % nrLines;
                var col = start / nrLines;

                for (int i = 0; i < nrLines; i++)
                {
                    MatrixMultiply[row, col] += Matrix1[row, i] * Matrix2[i, col];
                }

               
                start++;
            }
        }
        static int[,] GenerateRandomMatrix(int nr)
        {
            var Rnd = new Random(DateTime.Now.Millisecond);
            int[,] arr = new int[nr, nr];
            for (int i = 0; i < nr; i++)
            {
                for (int j = 0; j < nr; j++)
                {
                    //var randomDouble = Rnd.NextDouble();
                    //if (randomDouble < 0.5)
                       // arr[i, j] = 0;
                    //else
                        arr[i, j] = 1;
                }
            }
            return arr;
        }
    }
}
