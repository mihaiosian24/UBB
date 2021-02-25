using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using Enyim.Caching;
using Enyim.Caching.Configuration;
using Enyim.Caching.Memcached;


namespace MemCached
{
    class Program
    {
        static void Main(string[] args)
        {

            int[] list = new int[10];
            Random rnd = new Random();

            for (int i = 0; i < 5; i++)
            {
                int pos = rnd.Next(0, 9);
                int nr = rnd.Next(10, 19);
                if (list[pos] != 0)
                {
                    i--;
                }
                else
                list[pos] = nr;
            }
            MemcachedClientConfiguration config = new MemcachedClientConfiguration();
            config.AddServer("127.0.0.1:11211");

            config.Protocol = MemcachedProtocol.Binary;

            var mc = new MemcachedClient(config);
            mc.Store(StoreMode.Set, "foo", "bar");
            var value = mc.Get("foo");
            Console.WriteLine(value);
            var ceva = mc.Get("start4");
            Console.WriteLine(ceva);
            mc.Store(StoreMode.Set, "start4", "bar");
            var value1 = mc.Get("start4");
            //int check = 0;
            Console.WriteLine(value);
            if (ceva == null)
            {
                //check = 1;
                Console.WriteLine("Intra");
                string strCmdText;
                strCmdText = "start MemCached.exe";
                //System.Diagnostics.Process.Start("CMD.exe", strCmdText);
                Process.Start("C:\\Users\\mihai.osian\\source\\repos\\MemCached\\MemCached\\bin\\Debug\\MemCached.exe");
                //System.Diagnostics.Process.Start("CMD.exe", strCmdText);
                Process.Start("C:\\Users\\mihai.osian\\source\\repos\\MemCached\\MemCached\\bin\\Debug\\MemCached.exe");
                //strCmdText = "start C:\\Users\\mihai.osian\\source\\repos\\MemCached\\MemCached\\bin\\Debug\\MemCached.exe";
                //System.Diagnostics.Process.Start("CMD.exe", strCmdText);
                Process.Start("C:\\Users\\mihai.osian\\source\\repos\\MemCached\\MemCached\\bin\\Debug\\MemCached.exe");
            }

            for (int i = 0; i < 10; i++)
            {
                Console.WriteLine(list[i]);
            }
            
                int command = Convert.ToInt32(Console.ReadLine());
                int check = command;
                while (command != 0)
                {
                    if (command == 1)
                    {
                        for (int i = 0; i < 10; i++)
                        {
                            if (mc.Get(i.ToString()) != null)
                            {
                                list[i] = Convert.ToInt32(mc.Get(i.ToString()));
                            }
                        }
                        for (int i = 0; i < 10; i++)
                        {
                            Console.WriteLine(list[i]);
                        }
                        string pos = Console.ReadLine();
                        int nr = Convert.ToInt32(Console.ReadLine());
                        mc.Store(StoreMode.Set, pos, nr);
                        list[Convert.ToInt32(pos)] = nr;


                    }

                    if (command == 2)
                    {
                        for (int i = 0; i < 10; i++)
                        {
                            if (mc.Get(i.ToString()) != null)
                            {
                                list[i] = Convert.ToInt32(mc.Get(i.ToString()));
                            }
                        }
                        for (int i = 0; i < 10; i++)
                        {
                            Console.WriteLine(list[i]);
                        }
                        int nr = Convert.ToInt32(Console.ReadLine());
                        for (int i = 0; i < 10; i++)
                        {
                            if (list[i] == nr)
                            {
                                list[i] *= 2;
                            mc.Store(StoreMode.Set, i.ToString(), list[i]);
                            }
                        }

                    }
                command=Convert.ToInt32(Console.ReadLine());
            }

                
                // var mc1 = new MemcachedClient(config);

                /*
                using(MemcachedClient ceva=new MemcachedClient())
                {
                    ceva.Store(StoreMode.Set, "foo", "bar");
                    Console.WriteLine(ceva.Get("foo"));
                }*/
                //if(check==1)

                
            mc.Remove("start4");
        }
            
        }
    }


