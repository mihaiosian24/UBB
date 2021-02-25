##from SymbolTable import SymbolTable
import re
class SymbolTable:
    def __init__(self):
        self.code=0
        self.table={}
        self.symbols=["+", "-"," *", "/", "::=", "<", "<=", "=", ">=","[","]","{","}"]

    def writeToSymbolTable(self,key):
        if self.checkIdentifier(key):
            if key not in self.table.keys():
                self.table[key]=self.code
                self.code+=1
        if self.checkConstant(key):
            if key not in self.table.keys():
                self.table[key] = self.code
                self.code += 1

        #if self.checkConstant(key):
        if key in self.symbols:
            if key not in self.table.keys():
                self.table[key] = self.code
                self.code += 1

    def checkIdentifier(self,key):
        prog=re.compile("^([a-zA-Z]+[0-9]*){1,250}")

        return prog.match(key)

    def checkConstant(self,key):
        prog=re.compile("^[1-9]+[0-9]|[0-9]|[a-zA-Z]+")
        return prog.match(key)

    def checkSymbol(self,key):
        #prog=re.compile("^+|-|'*'|'/'|:=|<|<=|=|=>|'['|']'|'{'|'}'")
        return True

class PIF:
    def __init__(self):
        self.code_list=[]
        self.symbol_list=[]

    def writeToPIF(self,key):
        if key not in self.symbol_list:
            self.symbol_list.append(key)
            self.code_list.append(0)
        else:

            #print(len(self.symbol_list))
            #print(len(self.symbol_list) - 1 - self.symbol_list[::-1].index(key))
            self.code_list.append(self.code_list[len(self.symbol_list) - 1 - self.symbol_list[::-1].index(key)] + 1)
            self.symbol_list.append(key)
            #self.code_list.append(self.code_list[''.join(self.symbol_list).rindex(key)] + 1)
            #self.code_list.append(self.code_list[max(loc for loc, val in enumerate(self.symbol_list) if val == key)] + 1)

def read_from_file(filename):
    st = SymbolTable()
    pif=PIF()
    with open(filename,'r') as data:
        #print(data)
        #print("intra")

        for line in data:
            line=line.split("\n")
            line=line[0].split(" ")
            #print(line)

            for element in line:
                #print(element)
                        st.writeToSymbolTable(element)
                        pif.writeToPIF(st.table[element])
                #print(st.table)
    print("------SymbolTable---------")
    for key in sorted(st.table.keys()):
        print (key, st.table[key])
    print("-------PIF-----------")
    for i in range(0,len(pif.symbol_list)):
        print(pif.symbol_list[i],pif.code_list[i])
if __name__ == '__main__':

    read_from_file("test.txt")
