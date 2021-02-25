using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Karatsuba
{
    class Program
    {
        public static int[] number1 = { 1,2,3,4};
        public static int[] number2 = { 5,6,7,8};
        //public static Thread[] threads = new Thread[100];

        public static double Karatsuba(int[] n1,int[] n2)
        {
            Console.WriteLine(n1);

                if (n1.Length == 1 || n2.Length == 1)
                {
                    int pow = 1;
                    double value = 0;
                    for (int i = n1.Length-1; i >= 0; i--)
                    {
                        value += n1[i] * pow;
                        pow *= 10;
                    }
                    pow = 1;
                    double value1 = 0;
                    for (int i = n2.Length-1; i >= 0; i--)
                    {
                        value1 += n2[i] * pow;
                        pow *= 10;
                    }
                    return value*value1;
                }

            int n = Math.Max(n1.Length, n2.Length);
            int nb2 = n / 2;
            nb2 += n % 2;

            double high=0, low=0, mid=0;
            int[] a = new int[n / 2];
            int[] b = new int[n / 2];
            int[] c = new int[n / 2];
            int[] d = new int[n / 2];
            Array.Copy(n1, 0, a, 0, nb2);
            Array.Copy(n1, n/2, b, 0, n1.Length-nb2);
            Array.Copy(n2, 0, c, 0, nb2);
            Array.Copy(n2, n/2, d, 0, n2.Length-nb2);
            Console.WriteLine(c[0]);
            Console.WriteLine(d[0]);
            Thread thread = new Thread(() => {
               high = Karatsuba(a, c);

        low = Karatsuba(b, d);
                int[] aux = new int[nb2];
                int[] aux2 = new int[nb2];
                int i = 0, j = 0;
                while (i < nb2 || j < n1.Length-nb2)
                {
                    if (i < nb2 && j < n- nb2)
                    {
                        aux[i] = a[i] + b[j];
                        aux2[i] = c[i] + d[j];
                    }
                    if (j > n-nb2)
                    {


                        aux[i] = a[i];
                        aux2[i ]= c[i] ;
                    }
                    i++;
                    j++;

                }
                mid = Karatsuba(aux, aux2) - high - low;
            });
            thread.Start();

            while (high == 0 || low == 0 || mid == 0)
            {
                Console.WriteLine("VALUES:{0},{1},{2}", high, low, mid);
            }
            double prod = 0;
            Console.WriteLine(high*Math.Pow(10,2*nb2));
            if (n % 2 == 0)
                prod = high * Math.Pow(10, 2 * nb2) + (mid*Math.Pow(10,nb2)) + low;
            else prod = high * Math.Pow(10, 2 * (nb2-1)) + (mid * Math.Pow(10, nb2-1)) + low;
       
            return prod;
        }
        static void Main(string[] args)
        {
            double value=Karatsuba(number1, number2);

            Console.WriteLine(value);
            Console.ReadLine();
        }
    }
}
