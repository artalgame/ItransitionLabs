using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Numerics;

namespace task3
{
    class Program
    {
        static List<long> FindComponents(long x)
        {
            long a, b, c;
            List<long> components = new List<long>();
                for(long i = 1; i < (long)Math.Sqrt(x); i++)
                    for (long j = 1; j < (long)Math.Sqrt(x); j++)
                    {
                        if(i!=j)
                        {
                            long p = i * i;
                            long q = j * j;
                            if ((p + q >= 2*x )) break;

                            long r = 2 * x - p - q;
                            long sqrR = (long)Math.Sqrt(r);
                            if((sqrR*sqrR == r)&&(!components.Contains(r))&&(r!=p)&&(r!=q))
                            {
                                a = (p - q + r) / 2;
                                b = (q - r + p) / 2;
                                c = (r - p + q) / 2;
                                if (a > 0 && b > 0 && c > 0)
                                {
                                    components.Add(p);
                                    components.Add(q);
                                    components.Add(r);
                                    if (components.Count == 9) return components;
                                }
                            }
                        }
                    }
            if (components.Count == 9) return components;
            else return null;
        }

        static void Main(string[] args)
        {
            long a, b, c, d, e, f, g, h, t;
            List<long> comp = null;
            long x = 0;

            for (long i = 10; i < 10000; i++)
            {
                comp = FindComponents(i * i);
                if (comp != null)
                {
                    x = i*i;
                    break;
                }
            }
             a = (comp[0] - comp[1] + comp[2]) / 2;
             b = (comp[1] - comp[2] + comp[0]) / 2;
             c = (comp[2] - comp[0] + comp[1]) / 2;

             d = (comp[3] - comp[4] + comp[5]) / 2;
             e = (comp[4] - comp[5] + comp[3]) / 2;
             f = (comp[5] - comp[3] + comp[4]) / 2;

             g = (comp[6] - comp[7] + comp[8]) / 2;
             h = (comp[7] - comp[8] + comp[6]) / 2;
             t = (comp[8] - comp[6] + comp[7]) / 2;

             BigInteger pr = (BigInteger)a * b * c * d * e * f * g * h * t;
             string answ = a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + t + " summ:" + x + " mult:" + pr;
            Console.WriteLine(answ);

        }
    }
}
