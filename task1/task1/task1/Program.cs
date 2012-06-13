using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Numerics;

namespace task1
{
    class Program
    {
        static BigInteger Fib(int t)
        {
            BigInteger a = 1;
            BigInteger b = 1;
            BigInteger s = 0;
            
            int i = 3;
            while (i <= t)
            {
                s = a + b;
                a = b;
                b = s;
                i++;
            }
            return s;
        }

        static void Main(string[] args)
        {
            BigInteger answ = (1 - 2 * (1 + 257) % 2) * Fib(257) + (1 - 2 * (1 + 255) % 2) * Fib(255);
            Console.WriteLine(answ);
        }
    }
}
