from Sudoku.Controller.Controller import Controller
from Sudoku.Problem.Problem import Problem
from time import time


class UI:
    def __init__(self,filename):
        self.__filename=filename
        self.__problem=Problem(self.__filename)
        self.__ctrl=Controller(self.__problem)


    def printMainMenu(self):
        s=''
        s+="0-exit \n"
        s+="1-find a path with BFS\n"
        s+="2-find a path with BestFS\n"
        print(s)

    def findPathBFS(self):
        startClock=time()
        print(str(self.__ctrl.BFS(self.__problem.getRoot())))
        print('execution time=',time()-startClock,"seconds")

    def findPathBestFS(self):
        startClock=time()
        print(str(self.__ctrl.BestFS(self.__problem.getRoot())))
        print('execution time=',time()-startClock,"seconds")

    def run(self):
        runM=True
        self.printMainMenu()
        while runM:
            try:
                command=int(input(">>"))
                if command==0:
                    runM=False
                elif command==1:
                    self.findPathBFS()
                elif command==2:
                    self.findPathBestFS()
            except:
                print('invalid command')
