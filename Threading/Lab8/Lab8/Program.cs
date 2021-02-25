using System;
using System.Collections.Generic;
using System.Threading;

namespace Lab8
{
    class Nodee
    {
        public int id;
        public List<int> links=new List<int>();

        public Nodee(int id)
        {
            this.id = id;
            
        }
        public void addLink(int link)
        {
            this.links.Add(link);
        }
    }
    class Program
    {
        public static Nodee[] nodes = { new Nodee(1), new Nodee(2), new Nodee(3), new Nodee(4), new Nodee(5), new Nodee(6) };
        static void Main(string[] args)
        {
           
            addHamiltonianLink();
            //addRandomLinks();

            checkIfHamiltonian();
            Console.ReadLine();
            Console.WriteLine("Hello World!");
        }
        public static int[] checkLinks(Nodee node,Nodee lastNode,int[] checks)
        {
            Thread[] threads = new Thread[node.links.Count];
            int i = 0;
           foreach(int link in node.links)
            {
                
                Thread thread = new Thread(() =>
                  {
                      //int j = i;
                      int[] checkss = checks;
                     
                      int ceva = 1;
                      Console.WriteLine("See me");
                      Console.WriteLine(link-1);
                      Console.WriteLine(checkss.Length);
                      Console.WriteLine(checkss[link - 1]);
                      if (checkss[link-1]==0 && link==lastNode.id)
                      {
                          checkss[link - 1] = 0;
                          for (int k = 0; k < checkss.Length; k++)
                          {
                              if (checkss[k] != 1)
                              {
                                  ceva = 0;
                              }
                          }
                          if (ceva == 1)
                          {
                              Console.WriteLine("Hamiltonian");

                          }
                          

                      }
                      else
                      if (checkss[link-1] == 0)
                      {
                          checkss[link-1] = 1;
                          for (int k = 0; k < checkss.Length; k++)
                          {
                              if (checkss[k] != 1)
                              {
                                  ceva = 0;
                              }
                          }
                          if (ceva == 1)
                          {
                              Console.WriteLine("Hamiltonian");

                          }

                          checkLinks(nodes[link -1],node, checkss);
                      }

                      i++;
                  }
                );thread.Start();
            }
            return checks;
        }
        public static void checkIfHamiltonian()
        {
            int[] checks = new int[nodes.Length];
            for(int i = 0; i < checks.Length; i++)
            {
                checks[i] = 0;
            }
            Nodee node = new Nodee(100);
            checks = checkLinks(nodes[0],node,checks);
        }
        public static void addHamiltonianLink()
        {
           for(int i = 0; i < nodes.Length; i++)
            {
                if (i == nodes.Length - 1)
                {
                    nodes[i].addLink(1);
                }
                else
                {
                    if (i + 4 <= nodes.Length )
                    {
                        nodes[i].addLink(1);
                        nodes[i].addLink(i + 2);
                        nodes[i].addLink(i + 3);
                        nodes[i].addLink(i + 4);
                    }
                    else
                    {
                        if (i + 3 <= nodes.Length )
                        {
                            nodes[i].addLink(i + 2);
                            nodes[i].addLink(i + 3);
                        }
                        else
                        {
                            nodes[i].addLink(i + 2);
                        }
                    }
                }
            }
        }
    }
}
