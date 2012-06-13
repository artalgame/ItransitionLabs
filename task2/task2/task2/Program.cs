using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace task2
{
    class Program
    {
        static List<int> GetNPrimeNumbers(int n)
        {
            List<int> answ = new List<int>();
            answ.Add(2);
            int i = 3;
            while (i <= n)
            {
                bool good = true;
                foreach (var num in answ)
                {
                    if (num* num > i) break;
                    if (i % num == 0)
                    {
                        good = false;
                        break;
                    }
                    
                }
                if (good) answ.Add(i);
                i+=2;
            }
            return answ;
        }

        static int Transform(int dig)
        {
            int ans = 0;
              while (dig != 0)
                {
                    ans *= 10;
                    ans += dig % 10;
                    dig /= 10;
                }
            return ans;
        }

        static bool IsPalindrom(List<int> digits)
        {
            bool isGoodNumber = true;
            int half = digits.Count / 2;
            for (int i = 0; i <= half; i++)
            {
                int dig = digits[(digits.Count - 1)-i];
                if (digits[i] != dig)
                {
                    isGoodNumber = false;
                    break;
                }
            }
            return isGoodNumber;
        }

        static bool IfGoodNumber(int num, int dim,out List<int> repr)
        {
            List<int> digits = new List<int>();

            while (num >= dim)
            {
                digits.Add(num % dim);
                num /= dim;
            }
            digits.Add(num);
            repr = digits;
            return IsPalindrom(digits);
        }

        static int CountDigits(int n)
        {
            return n.ToString().Length;
        }

        static void Main(string[] args)
        {
            var primeList = GetNPrimeNumbers(1000000);
            long sum = 0;
            foreach (var num in primeList)
            {
                int i = 0;
                int dim = primeList[i];
                List<int> repr;
                while (dim <= num - 2)
                {
                    if (IfGoodNumber(num, dim,out repr))
                    {
                        sum += num;
                        Console.Write(num + " " + dim+":");
                        foreach (var t in repr)
                            Console.Write(t+" ");
                        Console.WriteLine();
                        break;
                    }
                    i++;
                    dim = primeList[i];
                }
            }
            Console.WriteLine(sum);
        }
    }
}
