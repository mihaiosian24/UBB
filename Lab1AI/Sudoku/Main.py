from Sudoku.Repository.Repository import Repository
from Sudoku.Configuration.Configuration import Configuration
from Sudoku.State.State import State
from Sudoku.Problem.Problem import Problem
from Sudoku.Controller.Controller import Controller
from time import time

from Sudoku.UI.UI import UI


def main():
    '''
    repo=Repository()
    print("Hey")
    Repository.readFile("string","string")
    '''
    #c=Configuration([1,2,3,3,4,4])
    #p=c.nextConfig(0)
    #for x in range (0,len(p)):
    #    print(p[x])

    #problem=Problem("../Sudoku/Repository/9")
    #q=problem.expand(problem.getRoot())
    #for x in range (0,len(q)):
    #    print(q[x])
    ##check=[0]*4
    ##print(check)

    #ctrl=Controller(problem)
    #ctrl.BFS(problem.getRoot())
    #startClock=time()
    #print(str(ctrl.BFS(problem.getRoot())))
    #print('execution time = ', time() - startClock, " seconds")
    #startClock=time()
    #print(str(ctrl.BestFS(problem.getRoot())))
    #print('execution time = ', time() - startClock, " seconds")
    ui=UI("../Sudoku/Repository/text.txt")
    ui.run()

main()