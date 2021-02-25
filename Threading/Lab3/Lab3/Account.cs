using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Lab3
{
    class Account
    {
        public string Name;
        public float balance;
        public List<Operation> operations;
        public Mutex mtx = new Mutex();
        public string toString()
        {
            List<string> strings = new List<string>();
            for (int i = 0; i < operations.Count(); i++)
                strings.Add(operations[i].ToString());
            return this.Name + "," + this.balance + strings;
        }

        public int subtract(Operation operation)
        {
            this.mtx.WaitOne();
            if (this.balance >= operation.amount)
            {
                this.balance = this.balance - operation.amount;
                this.operations.Add(operation);
                this.mtx.ReleaseMutex();
                return 1;
            }
            else
            {
                Console.WriteLine("Account doesn`t have enough money!" + "Account:" + this.Name);
            }
            this.mtx.ReleaseMutex();
            return 0;
        }
        public void add(Operation operation)
        {
            this.mtx.WaitOne();
            this.balance = this.balance + operation.amount;
            this.operations.Add(operation);
            this.mtx.ReleaseMutex();
        }
    }
}
