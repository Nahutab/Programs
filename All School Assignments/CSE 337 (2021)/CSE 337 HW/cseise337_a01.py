#Name: Batuhan Aykac
#SBU ID: 112813167
#net id: baykac

import re

#Problem 1

def is_nice(s):
    s = str(s)
    flag = 0
    maxCount = 0
    for x in s:
        count = s.count(x)

        if count > maxCount:
            maxCount = count

        if count != maxCount:
            flag = 1 
            break

    if flag == 0:
        print("HARD YES")
    else:
        print("HARD NO")


#Problem 2

def is_balanced(s):

    

    reversedString = s [::-1]

    flag = 0

    if len(s) % 2 != 0:
        print("False")
    
    else:
        for x in range(len(s)):
            if  s[x] == "[" and reversedString[x] != "]":
                flag = 1
                break
            elif s[x] == "{" and reversedString[x] != "}":
                flag = 1
                break
            elif s[x] == "(" and reversedString[x] != ")":
                flag = 1 
                break

        if(flag == 0):
            print("True")
        else:
            print("False")


    

#Problem 3

def even(x):
    return x%2 == 0

def apply_fun(listofIntegers, functionInput):
    outputList =[]
    for i in range(len(listofIntegers)):
        if functionInput(listofIntegers[i]) == True:
            outputList.append(i)
    return outputList


#Problem 4

class FS_Item:
    def __init__(self):
        
        
        self.name = ''
        
            
        
class Folder(FS_Item):
    def __init__(self):
        self.items = []
    def add_item(self, item):
        self.items.append(item)


class File(FS_Item):
    def __init__(self):
        self.size = 0


def load_fs(ls_output):

    file1 = open(ls_output,'r')
    textFileList = file1.readlines()

    rootDirectory = Folder()
    rootDirectory.name = textFileList[0][0]
    
    currentFolder = rootDirectory

    for i in range(1,len(textFileList)):
        if(textFileList[i] != "\n"):
            if textFileList[i][0] == "d":
                newFolder = Folder()
                tempStringSplit = textFileList[i].split()
                newFolder.name = tempStringSplit[-1]
                currentFolder.add_item(newFolder)
                continue
            if(textFileList[i][0] == "-"):
                newFile = File()
                tempStringSplit = textFileList[i].split()
                newFile.name = tempStringSplit[-1]
                newFile.size = str(tempStringSplit[-5])
                currentFolder.add_item(newFile)
                continue
            if "/" in textFileList[i]:
                tempString = textFileList[i].replace("/", ' ')
                tempString = tempString.replace(":",'')
                newTempString = tempString.split()
                currentFolder = rootDirectory
                for a in range(len(newTempString)):
                    for b in range(len(currentFolder.items)):
                        if(currentFolder.items[b].name == newTempString[a]):
                            currentFolder = currentFolder.items[b]
                            break
              
            if("d" not in textFileList[i][0] and "-" not in textFileList[i][0]):
                continue
        else:
            continue


   
    file1.close()
    return rootDirectory




#Question 5



def split(word):
    return [char for char in word] 

def hasNumbers(inputString):
    return any(char.isdigit() for char in inputString)



def decode(ct):


    ciphertext = ct
    ciphertext2 = re.sub(r'\W+', '', ciphertext)

    if(hasNumbers(ciphertext2)):
        numberlessString = ''.join([i for i in ciphertext2 if not i.isdigit()])
        inputList = list(numberlessString)
    else:
        cipherList = list(ciphertext2)
        inputList = cipherList 

    resultList = [] 
    for i in range(26):
        if (inputList[0].isupper()):
            if (65+i+17)%26 == ord(inputList[0]) - 65:
                resultList.append(chr(65+i))
                break
        elif (inputList[0].islower()):
            if (97+i+17)%26 == ord(inputList[0]) - 97:
                resultList.append(chr(97+i))    
                break


    for a in range(1,len(inputList)):
        
        currentEncryptedLetter = inputList[a]
        previousdecryptedLetter = resultList[a-1]
        for i in range (26):
            if currentEncryptedLetter.isupper():
                if (65+i+ord(previousdecryptedLetter))%26 == ord(currentEncryptedLetter) - 65:
                    resultList.append(chr(i+65))
                    break
            if currentEncryptedLetter.islower():
                if (97+i+ord(previousdecryptedLetter))%26 == ord(currentEncryptedLetter) - 97:
                    resultList.append(chr(i+97))
        
    tempCiphertext = list(ciphertext)


    for c in range(len(tempCiphertext)):
        if not tempCiphertext[c].isalpha():
            resultList.insert(c,tempCiphertext[c])
        elif tempCiphertext[c].isnumeric():
            resultList.insert(c,tempCiphertext[c])


    outputString = ''.join([str(elem) for elem in resultList]) 
    return outputString


   