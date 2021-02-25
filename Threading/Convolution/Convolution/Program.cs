using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Convolution
{
    class Program
    {

        public static int[] v1 = { 1, 2, 3, 4, 5 };
        public static int[] v2 = { 6, 7, 8, 9, 10 };
        public static int[] v3 = { 0, 0, 0, 0, 0 };
        public static Thread[] threads = new Thread[v1.Length];
        public static int ModuloN(int number)
        {
            int val = v1.Length;
            if (number > 0) {
                if (val > number)
                    return number;
                while (val <= number - v1.Length)
                {
                    val += v1.Length;
                   // Console.WriteLine(number - v1.Length);
                   // Console.WriteLine(val);
                }
                val = number - val;
            }
            else
            {
                
                val = -val;
                if (val < number + v1.Length)
                    return number * -1;
                while (val >number+v1.Length)
                {
                    val -= v1.Length;
                }
                val = number-val;
                val = -val;
            }
            return val;
        }

        public static void CalculateConvolution() {
            for(int i = 0; i < v1.Length; i++)
            {
                Calculate(i);
            }

        }
        public static void Calculate(int pos)
        {
            threads[pos] = new Thread(() => {
                for(int j = 0; j < v1.Length; j++)
                {
                    //Console.WriteLine(pos);
                    //Console.WriteLine(j);
                    //Console.WriteLine(ModuloN(pos - j));
                    try
                    {
                        v3[pos] += v1[j] * v2[ModuloN(pos - j)];

                    }
                    catch
                    {
                        /*
                        Console.WriteLine("Aici");
                        Console.WriteLine(pos);
                        Console.WriteLine(j);
                        Console.WriteLine(ModuloN(pos - j));
                    */
                        Console.WriteLine("Aici {0} {1}  {2}", pos, j, ModuloN(pos - j));
                    }
                }
                //Console.WriteLine(pos);
            });
            threads[pos].Start();
        }

        static void Main(string[] args)
        {
            
            Console.WriteLine(ModuloN(3));
            Console.WriteLine(ModuloN(29));
            Console.WriteLine(ModuloN(-5));
            Console.WriteLine(ModuloN(-71));
           
            CalculateConvolution();
            for (int i = 0; i < v1.Length; i++)
            {
                threads[i].Join();
            }
            for (int i = 0; i < v1.Length; i++)
            {
                Console.WriteLine(v3[i]);
            }

            Console.ReadLine();

        }
    }
}
