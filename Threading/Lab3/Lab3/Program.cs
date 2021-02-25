using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
//using System.Threading.Future;

namespace Lab3
{
    class Program
    {
        public static List<Account> accounts = new List<Account>();
        static void DoOperations(object i,CountdownEvent evt)
        {
            Thread.Sleep(100);
                Random rnd = new Random();
                for (int j = 0; j < rnd.Next(0, accounts.Count()); j++)
                {

                    Operation operation = new Operation();
                    Console.WriteLine(i);
                    operation.amount = 1;
                    operation.Destination = accounts[j].Name;
                    Account account1 = accounts[j];
                    int puppet = rnd.Next(0, accounts.Count());
                    Console.WriteLine(puppet);
                    Account account2 = accounts[puppet];
                    operation.Source = accounts[puppet].Name;
                    if (account1.subtract(operation) == 1)
                        account2.add(operation);

                }
            evt.Signal();
            } 
        static void Main(string[] args)
        {
            int i = 0;
            //List<Account> accounts = new List<Account>();
            while (i < 10)
            {
                Account account = new Account();
                account.Name = "Account" + i;
                account.balance = i;
                account.operations = new List<Operation>();
                accounts.Add(account);
                i++;
            };

            for (i = 0; i < accounts.Count; i++)
            {
                Console.WriteLine(accounts[i].toString());
            }

            //Random rnd = new Random();
            //int puppet = rnd.Next(0, accounts.Count);
            //Operation operation = new Operation();
            //Console.WriteLine(i);
            //operation.Destination = accounts[i].Name;
            //operation.Source = accounts[puppet].Name;
            //operation.serialNumber = "c"+i;
            //Transfers(operation, accounts[i], accounts[puppet]);
            //Console.WriteLine(accounts[i].toString());
            Thread[] threads = new Thread[4];
            int z = 0;
            var handles = new ManualResetEvent[accounts.Count];
            
            for (i = 0; i < accounts.Count; i++)
            {
                handles[i]= new ManualResetEvent(false);
            
        }
            AutoResetEvent done = new AutoResetEvent(false);
            int running = accounts.Count;
            for (i = 0; i < 4; i++)
            {
                Random rnd = new Random();
                int random = rnd.Next(0, accounts.Count);
                /*   //threads[z] = new Thread(() => {
                   ThreadPool.QueueUserWorkItem(new WaitCallback(DoOperations),random

                   ThreadPool.QueueUserWorkItem(state => {
                       DoOperations(i);
                       if (0 == Interlocked.Decrement(ref running))
                           done.Set();
                   });
                   *//*
                using (CountdownEvent counter = new CountdownEvent(accounts.Count))
                {
                    for (int j = 0; j < accounts.Count; j++)
                    {
                        //int sleepTime = i;
                        ThreadPool.QueueUserWorkItem(_ => DoOperations(random, counter));
                    }
                    counter.Wait();
                }*/
                using (CountdownEvent counter1 = new CountdownEvent(accounts.Count))
                {


                    for (int j = 0; j < accounts.Count; j++)
                    {
                        Task task = Task.Run(() => DoOperations(random, counter1));
                    }
                        counter1.Wait();
                }
                    handles[i] = new ManualResetEvent(false);
            }   
                z++;
           
            //WaitHandle.WaitAll(handles);
           // done.WaitOne();
            for (i = 0; i < accounts.Count; i++)
            {
                
            }
            /*
            for (i = 0; i < z; i++)
            {
                threads[i].Start();
            }
            for (i = 0; i < z; i++)
            {
                threads[i].Join();
            }*/
            for (i = 0; i < accounts.Count; i++)
            {
                Console.WriteLine(accounts[i].toString());
            }
            Console.WriteLine("Ajunge");
            Console.ReadLine();
        }
    
    }
}
