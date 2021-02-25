class SymbolTable:
    def __init__(self):
        self.code=0
        self.table={}

    def writeToSymbolTable(self,key):
        self.table[key]=self.code
        self.code+=1


