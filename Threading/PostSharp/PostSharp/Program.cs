using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TrialPostSharp
{
    class Program
    {[Aspect]
        public static int ADD()
        {
            return 2 + 2;
        }
        static void Main(string[] args)
        {
           ADD();
            Console.ReadLine();
        }
    }
}
