using System;
using System.Threading;

namespace Lab7part1

{
    class Program
    {
        public static int[] Numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        public static int[] checks=new int [Numbers.Length];
        public static int[] output = new int[Numbers.Length];
        public static int extra = Numbers.Length % 2;
        public static Mutex mtx = new Mutex();
        public static int pos;
        public static int helper;
        static void Main(string[] args)
        {
            setChecks();
            compute();
            //Thread.Sleep(10000);
            printOutput();
            Console.ReadLine();
            Console.WriteLine("Hello World!");
        }
        public static void printOutput()
        {
            for(int i = 0; i < Numbers.Length; i++)
            {
                Console.WriteLine(output[i]);
            }
        }
        public static void setChecks()
        {
            for(int i = 0; i < Numbers.Length; i++)
            {
                checks[i] = 0;
                output[i] = 0;
              
            }
        }
        public static void compute()
        {
            Thread[] threads = new Thread[Numbers.Length / 2 + extra];
            var helper1 = 0;
            for (int i = 0; i < Numbers.Length / 2 + extra; i++)
            {
                //mtx.WaitOne();
                
                Console.WriteLine(i + helper);
                threads[i] = new Thread(() =>
                  {
                      //pos = i;
                      //helper = helper1;
                     
                      Console.WriteLine(i + helper);
                      doOp(i, helper1);


                  });
                threads[i].Start();
               // threads[i].Join();
                Thread.Sleep(100);
                //mtx.ReleaseMutex();
                helper1++;
            }
            
                 
                    for (int i = 0; i<Numbers.Length / 2 + extra; i++)
                    {
                        threads[i].Join();
    }
}
              

        public static void doOp(int i,int helper)
                {
            //mtx.WaitOne();
                    Console.WriteLine("Thread");
            
                    if (i + helper >= 0 && i + helper < Numbers.Length - 1)
                    {
                        Console.Write("intra");
                        var k = i;
                if (k +helper== 0)
                {
                    output[i + helper] = Numbers[i + helper];
                    Console.WriteLine("EUUUUUUUUUUUUU");
                }
                else
                {
                    
                    var z = 0;
                    
                    do
                    {
                        if (z == -(int)(2 * Math.Log(Numbers.Length))&&checks[k+helper+z]==0)
                        {
                            Console.WriteLine(k + helper);
                            Console.WriteLine("Asta e z:" + z);
                            Console.WriteLine("AICIIII"+checks[k + helper + z]);
                            Console.WriteLine("PLM" + checks[0]);
                            Console.WriteLine(checks[2]);
                            Console.WriteLine(output[2]);
                            z = z;
                        }
                        else

                        if (k + helper + z == 0 && checks[k+helper+z]==0)
                        {
                            Console.WriteLine("SA Vedem");
                            Console.WriteLine(checks[0]);
                            z = z;
                        }
                        else z--;
                    } while (checks[k + helper + z] == 0 && k > 0);

                    if (k + helper + z != 0)
                    {   
                        output[i + helper] += output[k + helper+z];
                        z++;
                    }
                   
                    while (k+z <= i && i != 0)
                    {
                        Console.WriteLine("Am Castigat");
                        output[i + helper] += Numbers[k +z+ helper];
                        z++;

                    }
                }       
                        checks[i + helper] = 1;
                        output[i + helper + 1] += Numbers[i + helper + 1] + output[i + helper];
                        checks[i + helper + 1] = 1;
                        Console.WriteLine(output[i + helper]);
                    }
                    else
                    if (i + helper + 1 == Numbers.Length)
                    {
                        Console.WriteLine("!");
                        var k = i;
                var z = 0;
                do
                {

                    if (z == -(int)((2 * Math.Log(Numbers.Length))))
                    {
                        Console.WriteLine(k + helper);
                        z = z;
                    }
                    else

                    if (k + helper + z == 0)
                    {
                        z = z;
                    }
                    else z--;
                } while (checks[k + helper+z] == 0 && k > 0);

                if (k+helper+z != 0)
                {
                    output[i + helper] += output[k + helper+z];
                    z++;
                }
               
                while (k+z <= i && i != 0)
                {
                   
                    output[i + helper] += Numbers[k+z + helper];

                    z++;
                }
                checks[i + helper] = 1;

                }
            //mtx.ReleaseMutex();
            }
            
            

        }
    }

