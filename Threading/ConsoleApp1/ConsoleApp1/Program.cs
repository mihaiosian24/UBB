using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {
        public static void Transfers(Operation operation,Account account1,Account account2)
        {
            Mutex m = new Mutex(false, operation.Destination);
            Mutex n = new Mutex(false, operation.Source);
            m.WaitOne();
            n.WaitOne();
            //Thread thread = new Thread(

            //  );

            account1.balance = account1.balance + operation.amount;
            account2.balance = account2.balance - operation.amount;
            m.ReleaseMutex();
            n.ReleaseMutex();
            Console.WriteLine(operation.toString());
            Console.WriteLine("Ajunge");
        }
        static void Main(string[] args)
        {
            int i = 0;
            List<Account> accounts=new List<Account>();
            while(i <10)
            {
                Account account = new Account();
                account.Name = "Account" + i;
                account.balance = i;
                account.operations = new List <Operation>();
                accounts.Add(account);
                i++;
            };

            for (i=0;i<accounts.Count;i++) {
                Console.WriteLine(accounts[i].toString()); }

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
            for (i = 0; i < 4; i++)
            {

                Random rnd = new Random();
                threads[z]=new Thread( ()=>{
                    for (int j = 0; j < rnd.Next(0, accounts.Count()); j++)
                    {
                        Operation operation = new Operation();
                        Console.WriteLine(i);
                        operation.amount = 1;
                        int puppet1 = rnd.Next(0, accounts.Count);
                        operation.Destination = accounts[puppet1].Name;
                        Account account1 = accounts[puppet1];
                        int puppet = rnd.Next(0, accounts.Count);
                        Console.WriteLine(puppet);
                        Account account2 = accounts[puppet];
                        operation.Source = accounts[puppet].Name;
                        if(account1.subtract(operation)==1)
                        account2.add(operation);

                    }
            });
                z++;
            }
            for( i = 0; i < z; i++)
            {
                threads[i].Start();
            }
            for (i = 0; i < z; i++)
            {
                threads[i].Join();
            }
            for (i = 0; i < accounts.Count; i++)
            {
                Console.WriteLine(accounts[i].toString());
            }
            Console.WriteLine("Ajunge");
            Console.ReadLine();
        }
    }
}
