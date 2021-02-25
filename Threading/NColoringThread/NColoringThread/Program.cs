using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace NColoringThread
{
    class Program
    {
        private static int V = 7;
        private static int m = 3;
        public static Mutex mtx = new Mutex();
            private static int[,] graph2 =  new int [,]{
            {0, 1, 1, 1, 0, 0, 0},
            {1, 0, 0, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 1, 1},
            {0, 1, 0, 0, 0, 0, 1},
            {0, 1, 0, 1, 0, 0, 1},
            {0, 1, 0, 1, 1, 1, 0},
        };
        public static bool isSafe(int v,int[,] graph,int[] color,int c)
        {
            for (int i = 0; i < V; i++)
                if (graph[v,i] == 1 && c == color[i])
                    return false;
            return true;

        }
        public static void start(int v,int[] color)
        {
            // Console.WriteLine(v);
            //Console.WriteLine("Sol");
            //Console.WriteLine(string.Format("{0} {1} {2} {3}{4}{5}{6}",color[0].ToString(), color[1].ToString(), color[2].ToString(), color[3].ToString(), color[4].ToString(), color[5].ToString(), color[6].ToString()));
            if (color[0] == 1 && color[1] == 2 && color[2] == 3)
            {
                Console.WriteLine("AJUNGE!!!!!!!!!!!!!");
            }
            if (v == V)
            {
                Console.WriteLine("SOLUTIE !!!!!!!!!!!!!!!!!!!!!!!!");
                Console.WriteLine(string.Format("{0} {1} {2} {3}{4}{5}{6}", color[0].ToString(), color[1].ToString(), color[2].ToString(), color[3].ToString(), color[4].ToString(), color[5].ToString(), color[6].ToString()));
            }
            else
            // int v = 0;
            // int[] color = new int[]{ 0, 0, 0, 0, 0, 0, 0 };
            { 
                    Random rnd = new Random();
                Thread thread = new Thread(()=>{
                    int[] check = new int[] { 0, 0, 0 };
                    int[] newColor = color;
                    while (check[0] != 1 || check[1] != 1 || check[2] != 1)
                    {
                        
                        int c = rnd.Next(1, 4);
                        
                        while (check[c - 1] == 1)
                        {
                            c = rnd.Next(1, 4);
                        }
                        check[c - 1] = 1;
                        
                        if (isSafe(v, graph2, color, c))
                        {
                            // mtx.WaitOne();

                            newColor[v] = c;
                            //mtx.ReleaseMutex();
                            start(v + 1, newColor);
                            //Thread.Sleep(100);
                        }
                        //Console.WriteLine("DC????");
                    }
                    //Console.WriteLine("IESEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                });
                thread.Start();
               // thread.Join();
                //Thread.Sleep(10);
            }
        }
        static void Main(string[] args)
        {
            int[] color = new int[] { 0, 0, 0, 0, 0, 0, 0 };
            start(0, color);
           // Thread.Sleep(1000);
            Console.ReadLine();
        }
    }
}
