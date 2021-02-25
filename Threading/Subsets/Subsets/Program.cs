using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Subsets
{
    class Program
    {

        public static int[] set = { 1, 2, 3, 9, 8, 7, 3, 4, 5 };
        public static int k = 3;
        public static int counter = 0;
        public static void check(int[] subset,int pos)
        {
            int check = 0;
            for(int i = pos; i < k + pos; i++)
            {
                if (check < subset[i])
                    check = subset[i];
            }
            if (check == subset[pos + k - 1])
            {
                Interlocked.Add(ref counter, 1);
                Console.WriteLine("SUBSET:");
                for(int i = 0; i < subset.Length; i++)
                {
                    Console.WriteLine(subset[i]);
                }
            }
        }
        public static void CheckSubset(int[] subset,int pos)
        {
            Thread thread = new Thread(() => {
                check(subset, pos);

            });
            thread.Start();
        }
        public static void GoThru()
        {
            for(int i = 0; i < set.Length-k+1; i++)
            {
                CheckSubset(set, i);
            }
        }
        static void Main(string[] args)
        {
            GoThru();

            Thread.Sleep(10000);
            Console.WriteLine("Number of subsets:{0}", counter);
            Console.ReadLine();
        }
    }
}
