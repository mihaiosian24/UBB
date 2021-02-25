using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace K_Coloring
{
    class Program
    {
        public static int[] objects = { 0, 0, 0, 0, 0 };
        public static int[] constraints = { 0, 1, 1, 1, 0 };
        public static int counter = 0;
        private static object interlocked;
        public static Thread[] threads = new Thread[5];

        public static void check(int[] sol)
        {
            if (sol[1] != sol[2] && sol[1] != sol[3] && sol[3] != sol[2])
            {
                Interlocked.Add(ref counter, 1);
                Console.WriteLine("{0}{1}{2}{3}{4}", sol[0], sol[1], sol[2], sol[3], sol[4]);

            }
        }

        public static void Sol(int[] sol,int pos,int color)
        {
            int[] aux = new int[5];
            //aux = sol;
            Array.Copy(sol, aux, 5);
            aux[pos] = color;
            Thread thread = new Thread(() => {

                CreateSol(aux, pos + 1);
            });
            thread.Start();
        }

        public static void CreateSol(int[] sol,int pos)
        {
            if (sol[4] != 0)
            {
                check(sol);
            }
            else
            {for (int i = 1; i < 4; i++)
                {

                    Sol(sol, pos,i);
                   // Thread.Sleep(100);
                }
            }
        }

        static void Main(string[] args)
        {
            int[] sol = { 0, 0, 0, 0, 0 };
            CreateSol(sol, 0);
            Thread.Sleep(10000);
            Console.WriteLine("HOW MANY DIFFERENT SOLUTIONS:{0}", counter);
            Console.ReadLine();

        }
    }
}
