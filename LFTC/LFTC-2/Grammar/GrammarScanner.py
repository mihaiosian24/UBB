import copy
import re


class Grammars:
    def __init__(self):
        self.regular_file_grammar={}
        self.regular_keyboard_grammar={}
        self.automata_file_grammar={}
        self.automata_keyboard_grammar={}
        self.automata_file_initial=0
        self.automata_file_final=[]
        self.automata_keyboard_initial = 0
        self.automata_keyboard_final = []
        self.automata_file_alphabet=[]
        self.automata_keyboard_alphabet=[]


    def read_from_file_automata(self,filename):
        with open (filename,"r") as data:
            for line in data:
                line=line.split("\n")
                line=line[0].split(",")
                if line[0]=="States":
                    for state in line[1:]:
                        self.automata_file_grammar[state]=[]
                if line[0]=="Alphabet":
                    for letter in line[1:]:
                        self.automata_file_alphabet.append(letter)
                if line[0]=="Transitions":
                    for possibility in line[1:]:
                        possibility=possibility.split(" ")
                        list=[possibility[1],possibility[2]]
                        self.automata_file_grammar[possibility[0]].append(list)
                if line[0]=="Initial":
                    self.automata_file_initial=line[1]
                if line[0]=="Final":
                    for state in line[1:]:
                        self.automata_file_final.append(state)

    def read_automata_from_keyboard(self):
        print("Please put input as such:Name and characters divided by coma\n")
        print("Enter exit to EXIT\n")
        line=input("Please give your input:")
        while line!="exit":
            line = line.split("\n")
            line = line[0].split(",")
            if line[0] == "States":
                for state in line[1:]:
                    self.automata_keyboard_grammar[state] = []
            if line[0] == "Alphabet":
                for letter in line[1:]:
                    self.automata_keyboard_alphabet.append(letter)
            if line[0] == "Transitions":
                for possibility in line[1:]:
                    possibility = possibility.split(" ")
                    list = [possibility[1], possibility[2]]
                    self.automata_keyboard_grammar[possibility[0]].append(list)
            if line[0] == "Initial":
                self.automata_keyboard_initial = line[1]
            if line[0] == "Final":
                for state in line[1:]:
                    self.automata_keyboard_final.append(state)
            line=input("Please give your input:")


    def read_from_file(self,filename):
        with open(filename,"r") as data:
            for line in data:
                line=line.split("\n")
                line=line[0].split(" ")
                key=line[0]
                self.regular_file_grammar[key]=[]
                line=line[1:]
                posibility=[]
                print(line)
                for element in line:
                    if element=="|":
                        print(posibility)
                        self.regular_file_grammar[key].append(posibility)
                        posibility=[]

                    elif element != "-":
                        posibility.append(element)

                self.regular_file_grammar[key].append(posibility)
    def read_from_keyboard(self):
        print("Please use spaces between every character\n")
        print("Enter exit to exit\n")
        line=input("Please give your input:")
        while line != "exit":
            line = line.split("\n")
            line = line[0].split(" ")
            key = line[0]
            self.regular_keyboard_grammar[key] = []
            line = line[1:]
            posibility = []
            for element in line:
                if element == "|":
                    self.regular_keyboard_grammar[key].append(posibility)
                    posibility = []

                elif element != "-":
                    posibility.append(element)
            self.regular_keyboard_grammar[key].append(posibility)
            line = input("Please give your input:")

    def verify_if_regular(self):
        if self.checkFile()==True:
            print("File Grammar is regular \n")
        else:print("File Grammar iregular or missing")
        if self.checkKeyboard()==True:
            print("Keyboard Grammar is regular \n")
        else:print("Keyboard Grammar iregular or missing \n")

    def checkFile(self):
        start=0
        for key,value in self.regular_file_grammar.items():
            if start==0:
                start=key;
            for list in self.regular_file_grammar[key]:
                for posibility in list:
                    small=False
                    for poss in posibility:
                        prog = re.compile("E")
                        if prog.match(poss):
                            if start != key:
                                return False

                        else:
                            prog=re.compile("^[a-z]+")
                            if prog.match(poss):
                                small=True
                            else:
                                prog = re.compile("^[A-Z]+")
                                if prog.match(poss)==False:
                                    return False
        return True

    def transform_automata_in_grammar(self):
        file={}
        keyboard={}
        states=[]
        alphabet=[]
        start=0
        end=[]
        for state in self.regular_file_grammar.keys():
            if start==0:
                start=state
            states.append(state)
        for key,value in self.regular_file_grammar.items():
            for list in self.regular_file_grammar[key]:
                if list[0] not in alphabet:
                    alphabet.append(list[0])
        for key, value in self.regular_file_grammar.items():
            for list in self.regular_file_grammar[key]:
                if(len(list)==1):
                    for listi in self.regular_file_grammar[key]:
                        if list[0] in listi and len(listi)!=1:
                            end.append(listi[1])
        print("Automata for file Grammar:\n")
        print("States:",states)
        print("\n")
        print("Alphabet:", alphabet)
        print("\n")
        print("Start:", start)
        print("\n")
        print("Final States:", end)
        print("\n")
        print("Transactions:\n")
        for key, value in self.regular_file_grammar.items():
            puppet = value
            i=0
            for list in value:
                if len(list) == 1:
                    puppet.pop(1)
                i+=1
            print(key, puppet)
        states = []
        alphabet = []
        start = 0
        end = []
        for state in self.regular_keyboard_grammar.keys():
            if start == 0:
                start = state
            states.append(state)
        for key, value in self.regular_keyboard_grammar.items():
            for list in self.regular_keyboard_grammar[key]:
                if list[0] not in alphabet:
                    alphabet.append(list[0])
        for key, value in self.regular_keyboard_grammar.items():
            for list in self.regular_keyboard_grammar[key]:
                if (len(list) == 1):
                    for listi in self.regular_file_grammar[key]:
                        if list[0] in listi and len(listi)!=1:
                            end.append(listi[1])
        print("Automata for keyboard Grammar:\n")
        print("States:", states)
        print("\n")
        print("Alphabet:", alphabet)
        print("\n")
        print("Start:", start)
        print("\n")
        print("Final States:", end)
        print("\n")
        print("Transactions:\n")
        for key, value in self.regular_keyboard_grammar.items():
            puppet=value
            i=0
            for list in value:
                if len(list)==1:
                    puppet.pop(i)
                i+=1
            print(key,puppet)


    def transform_grammar_in_automata(self):
        dict={}
        for key,value in self.automata_file_grammar.items():
            dict[key]=copy.deepcopy(value)
            for part in value:
               ## print(value)
               ## print(part)
                if part[1] in self.automata_file_final:
                    dict[key].append([part[0]])
        print("Grammar for file Grammar:\n")
        print("Q:",self.automata_file_grammar.keys(),"\n")
        print("S:",self.automata_file_initial,"\n")
        print("Non-terminals:")
        for key,value in self.automata_file_grammar.items():
            for list in value:
                for element in list:
                    if element not in self.automata_file_grammar.keys():
                        print(element )
        print("\n")
        print("Transitions:")
        for key,value in self.automata_file_grammar.items():
            for list in value:
                ##for element in list:
                ##if element in self.automata_file_final:
                puppet = value
                ##for listi in list:
                if len(list)!=1:
                    if list[1] in self.automata_file_final:
                        puppet.append([list[0]])
            print(key, value)
        dict={}
        for key,value in self.automata_keyboard_grammar.items():
            dict[key]=copy.deepcopy(value)
            for part in value:
                if part[1] in self.automata_keyboard_final:
                    dict[key].append([part[0]])
        print("Grammar for keyboard Grammar:\n")
        print("Q:",self.automata_keyboard_grammar.keys(),"\n")
        print("S:",self.automata_keyboard_initial,"\n")
        print("Non-terminals:")
        for key,value in self.automata_keyboard_grammar.items():
            for list in value:
                for element in list:
                    if element not in self.automata_keyboard_grammar.keys():
                        print(element)
        print("\n")
        print("Transitions:")
        for key,value in self.automata_keyboard_grammar.items():
            for list in value:
                ##for element in list:
                    ##if element in self.automata_file_final:
                    puppet=value
                    ##for listi in list:
                    if len(list)!=1:
                        if list[1] in self.automata_keyboard_final:
                            puppet.append([list[0]])
            print(key,puppet)

    def checkKeyboard(self):
        start=0
        epsilon=0
        for key,value in self.regular_keyboard_grammar.items():
            if start==0:
                start=key;
            for list in self.regular_keyboard_grammar[key]:
                for posibility in list:
                    small=False

                    for poss in posibility:
                        prog=re.compile("E")
                        if prog.match(poss):
                            if start!= key:
                                return False

                        else:
                            prog = re.compile("^[a-z]+")
                            if prog.match(poss):
                                small=True
                            else:
                                prog = re.compile("^[A-Z]+")
                                if prog.match(poss)==False:
                                    return False
        return True





if __name__=="__main__":
    grammars=Grammars()
    grammars.read_from_file("Grammar.txt")
    grammars.read_from_keyboard()
    grammars.verify_if_regular()
    grammars.read_from_file_automata("AutomataGrammar.txt")
    grammars.read_automata_from_keyboard()
    print("1.Terminals from grammar file\n")
    print("2.Non-terminals from grammar file\n")
    print("3.S from grammar file\n")
    print("4.print transitions from grammar file\n")
    print("5.Terminals from keyboard file\n")
    print("6.Non-terminals from keyboard file\n")
    print("7.S from keyboard file\n")
    print("8.print transitions from keyboard file\n")
    print("9.check if grammars are regular\n")
    print("10.Print States from automata file\n")
    print("11.Print Alphabet from automata file\n")
    print("12.Pprint starting point of automata file\n")
    print("13.Print final states for automata file\n")
    print("14.Print Transactions from automata file\n")
    print("15.Print States from automata keyboard\n")
    print("16.Print Alphabet from automata keyboard\n")
    print("17.Pprint starting point of automata keyboard\n")
    print("18.Print final states for automata keyboard\n")
    print("19.Print Transactions from automata keyboard\n")
    print("20.Print Transformation of grammar into automata\n")
    print("21.Print Transformation of automata into grammar\n")
    command=input("Choose youy command:")
    while command != "exit":
        if command=="1":
            for key in grammars.regular_file_grammar.keys():
                print(key)
        if command=="2":
            newDict={}
            for key,value in grammars.regular_file_grammar.items():
                for list in value:
                    for element in list:
                        prog=re.compile("^[a-z]+")
                        if prog.match(element):
                            newDict[element]=0
            for keys in newDict.keys():
                print(keys)

        if command=="3":
            start=0
            for keys in grammars.regular_file_grammar.keys():
                if start==0:
                    start=1
                    print(keys)
        if command=="4":
            for keys,value in grammars.regular_file_grammar.items():
                print(keys,value)

        if command == "5":
            for key in grammars.regular_keyboard_grammar.keys():
                print(key)
        if command == "6":
            newDict = {}
            for key, value in grammars.regular_keyboard_grammar.items():
                for list in value:
                    for element in list:
                        prog = re.compile("^[a-z]+")
                        if prog.match(element):
                            newDict[element] = 0
            for keys in newDict.keys():
                print(keys)

        if command == "7":
            start = 0
            for keys in grammars.regular_keyboard_grammar.keys():
                if start == 0:
                    start = 1
                    print(keys)
        if command == "8":
            for keys, value in grammars.regular_keyboard_grammar.items():
                print(keys, value)
        if command=="9":
            grammars.verify_if_regular()
        if command=="10":
            for key in grammars.automata_file_grammar.keys():
                print(key)
        if command=="11":
            print(grammars.automata_file_alphabet)
        if command=="12":
            print(grammars.automata_file_initial)
        if command=="13":
            print(grammars.automata_file_final)
        if command=="14":
            for key,value in grammars.automata_file_grammar.items():
                print(key,value)
        if command=="15":
            for key in grammars.automata_keyboard_grammar.keys():
                print(key)
        if command=="16":
            print(grammars.automata_keyboard_alphabet)
        if command=="17":
            print(grammars.automata_keyboard_initial)
        if command=="18":
            print(grammars.automata_keyboard_final)
        if command=="19":
            for key,value in grammars.automata_keyboard_grammar.items():
                print(key,value)
        if command=="20":
            grammars.transform_grammar_in_automata()
        if command=="21":
            grammars.transform_automata_in_grammar()
        print("1.Terminals from grammar file\n")
        print("2.Non-terminals from grammar file\n")
        print("3.S from grammar file\n")
        print("4.print transitions from grammar file\n")
        print("5.Terminals from keyboard file\n")
        print("6.Non-terminals from keyboard file\n")
        print("7.S from keyboard file\n")
        print("8.print transitions from keyboard file\n")
        print("9.check if grammars are regular\n")
        print("10.Print States from automata file\n")
        print("11.Print Alphabet from automata file\n")
        print("12.Pprint starting point of automata file\n")
        print("13.Print final states for automata file\n")
        print("14.Print Transactions from automata file\n")
        print("15.Print States from automata keyboard\n")
        print("16.Print Alphabet from automata keyboard\n")
        print("17.Pprint starting point of automata keyboard\n")
        print("18.Print final states for automata keyboard\n")
        print("19.Print Transactions from automata keyboard\n")
        print("20.Print Transformation of grammar into automata\n")
        print("21.Print Transformation of automata into grammar\n")
        command = input("Choose youy command:")
    for key,value in grammars.regular_file_grammar.items():
        print(key,value)
    for key,value in grammars.regular_keyboard_grammar.items():
        print(key,value)
    for key, value in grammars.automata_file_grammar.items():
        print(key, value)
    for key, value in grammars.automata_keyboard_grammar.items():
        print(key, value)


