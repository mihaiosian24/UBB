using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

namespace Lab7Part2
{

    class Program
    {
        public static int[] numbers = { 111, 111, 111, 111, 111 };
        public static readonly object _lock = new object();
        public static readonly Queue<int> _queue = new Queue<int>();
        public static int[] queue = new int[numbers.Length];
        public readonly AutoResentEvent _signal = new AutoResentEvent();
        public static int post = 0;
        static void Main(string[] args)
        {
            int[] flags = new int[numbers.Length];
            for (int i = 0; i < flags.Length; i++)
            {
                flags[i] = 0;

            }

            flags[1] = 1;
            Thread[] threads = new Thread[numbers.Length - 1];
            for (int i = 0; i < numbers.Length - 1; i++)
            {
                if (i == 0)
                {
                    //threads[i] = new Thread(() =>
                    if (1 == 1)
                    {
                        int extra = 0;

                        while (numbers[0] > 0 && numbers[1] > 0)
                        {
                            int digit = numbers[0] % 10 + numbers[1] % 10;
                            Console.WriteLine(numbers[0] + numbers[1]);
                            if (digit >= 10)
                            {
                                if (extra == 1)
                                {
                                        _queue.Enqueue(digit % 10 + 1);
                                        extra = 0;
                                }
                                else
                                {
                                    _queue.Enqueue(digit % 10);
                                }
                            }
                            else
                            {
                                if (extra == 1)
                                {
                                    _queue.Enqueue(digit + 1);
                                    extra = 0;
                                }
                                else
                                {
                                    Console.WriteLine(digit);
                                    _queue.Enqueue(digit);
                                }

                            }
                            numbers[0] = numbers[0] / 10;
                            numbers[1] = numbers[1] / 10;
                        }

                    }


                }
                else
                {
                    Console.WriteLine("AJUNGE");
                    if (i == numbers.Length - 2)
                    {
                        int j = i;
                        Console.WriteLine("Intra");
                        if (1 == 1)
                        {
                            int extra = 0;
                            int finalNumber = 0;
                            int place = 1;

                            var initialQueueCount = _queue.Count;
                            while (initialQueueCount != 0 || numbers[j + 1] > 0)
                            {
                                Console.WriteLine("LAst");
                                int digit = 0;
                                if (initialQueueCount != 0)
                                {
                                    digit = _queue.Dequeue();
                                    initialQueueCount--;
                                }

                                int number = numbers[j + 1] % 10 + digit + extra;
                                extra = 0;
                                if (number >= 10)
                                {
                                    extra = 1;
                                    number = number % 10;
                                }
                                Console.WriteLine("Number of Quesue");
                                Console.WriteLine(number);
                                digit = 0;
                                finalNumber += number * place;
                                place = place * 10;
                                numbers[j + 1] = numbers[j + 1] / 10;
                                Console.WriteLine(finalNumber);
                            }
                            Console.WriteLine(finalNumber);

                        }

                    }

                    else
                    {
                        if (1 == 1)
                        {
                            int check = 0;
                            int check2 = -1;
                            int extra = 0;
                            int digit = 0;
                            Console.WriteLine("Intra1");
                            var initialQueueCount = _queue.Count;
                            while (initialQueueCount != 0 || numbers[i + 1] > 0)
                            {
                                Console.WriteLine("How Much in Q");
                                Console.WriteLine(_queue.Count);
                                check = initialQueueCount;
                                if (initialQueueCount != 0)
                                    {
                                        digit = _queue.Dequeue();
                                    initialQueueCount--;
                                    }
                                check2 = _queue.Count;
                                int number = numbers[i + 1] % 10 + digit + extra;
                                extra = 0;
                                if (number >= 10)
                                {
                                    extra = 1;
                                    number = number % 10;
                                }
                                digit = 0;
                                numbers[i + 1] = numbers[i + 1] / 10;
                                {

                                    _queue.Enqueue(number);

                                }

                                Console.WriteLine("Passed");
                                digit = 0;
                                post = i + 1;
                            }
                        }
                    }

                }       

            }
            Console.ReadLine();
            Console.WriteLine("Hello World!");
        }




    }
}

