using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab3
{
    
    
        enum OperationType
        {
            Add,
            Subtract,
            Divide,
            Multiply

        }
        class Operation
        {
            public OperationType operationType;
            public float amount;
            public string serialNumber;
            public string Source;
            public string Destination;
            public string toString()
            {
                return this.operationType + "," + this.amount + "," + this.serialNumber + "," + this.Source + "," + this.Destination;
            }

        }
    
}
