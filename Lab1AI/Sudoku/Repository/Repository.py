class Repository:
    def __init__(self) :
        self.__key=[]
        print("Hey")

    def readFile(self,string):
        with open("../Sudoku/Repository/text.txt" , 'r') as f:
            key=f.read()
            print(key)




