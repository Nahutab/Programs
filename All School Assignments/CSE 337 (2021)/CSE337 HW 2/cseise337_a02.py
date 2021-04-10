#Name: Batuhan Aykac
#Net ID: baykac
#SBU ID:112813167

import re
from bs4 import BeautifulSoup
#Problem 1

def highlight(pattern,string):
    
    if re.search(pattern,string):
   

        m = re.search(pattern, string)
        startindex = m.start()
        lastindex = m.end()
 
        string = string[:startindex]+"<" + string[startindex:lastindex] + ">"

        return string

    else:
        return


#Problem 2

def highlight_all(pattern,string):

    if re.search(pattern,string):

        m = re.sub(pattern, '<\g<0>>',string)

        return m


    else:
        return

#Problem 3

def ruin_a_webpage(filename):
    
    matched = re.match("([a-zA-Z0-9\s_\\.\-\(\):])+(.html|.htm)$",filename)
    is_matched = bool(matched)

    if is_matched == False :
        return 

    else:

        with open(filename) as inf:
            txt = inf.read()
    
        # save the file again
        with open("ruined.html", "w") as outf:
            newstring = ""

            txt = re.sub("<p[^>]*>","",txt)
            txt = re.sub("</?p[^>]*>","<br><br>",txt)


            if txt.count("<span>") == txt.count("</span>"):


                txt = re.sub("\<(span)\>","",txt)
                txt = re.sub("\<\/(span)\>","",txt)

            elif txt.count("<span>") < txt.count("</span>"):
                occurences = txt.count("<span>")
                txt = re.sub("\<(span)\>","",txt)
                txt = re.sub("\<\/(span)\>","",txt,occurences)

            elif txt.count("<span>") > txt.count("</span>"):
                occurences = txt.count("</span>")
                txt = re.sub("\<(span)\>","",txt,occurences)
                txt = re.sub("\<\/(span)\>","",txt)
                

            newstring = txt
            outf.write(newstring)



#Problem 4

def decompose_path(path):

    pattern = ":"
    
    newString = re.split(pattern,path)


    return newString

#Problem 5

def link_mapper(filename):
    dictionary = {filename: []}
    tuple1 = ()

    matched = re.match("([a-zA-Z0-9\s_\\.\-\(\):])+(.html|.htm)$",filename)
    is_matched = bool(matched)

    if is_matched == False :
        return 

    else:

         with open(filename) as inf:
            txt = inf.read()


            urls = re.findall(r'href=[\'"]?([^\'" >]+)', txt)
            linktexts = re.findall(">(.*?)</a>",txt)

            listofUrls = (','.join(urls)).split(',')
            print("\n")
            listofLinkTexts = (','.join(linktexts)).split(',')

           


            for x in range(len(listofUrls)):
                tuple1 = ()
                tuple1 = (listofLinkTexts[x],listofUrls[x])
                dictionary[filename].append(tuple1)



    return dictionary
            

#Problem 6

#helper method to check if a letter is a vowel
def isVowel(letter):
    if letter in ('a','e','i','o','u',"A","E","I","O","U"):
        return True
    else:
        return False


def grammarly(text):
    newText = text
    

    splitbyperiod = newText.replace(". ",".")
    splitbyperiod = splitbyperiod.split(".")
    
    newText = ""
    for x in range(len(splitbyperiod) - 1):
        
        splitbyperiod[x] = str(splitbyperiod[x][0]).upper() + splitbyperiod[x][1:]
        splitbyperiod[x] = splitbyperiod[x] + ". "
        newText += splitbyperiod[x]


    newStringText = re.sub(r" i ", " I ",newText)

    newText2 = newStringText.split()

    for a in range(len(newText2)):
        if isVowel(newText2[a][0]) and a > 0:
            if newText2[(a-1)] == "a" or newText2[(a-1)] == "A":
                if "." in newText2[a-2]:
                    newText2[(a-1)] = "An"
                else:
                    newText2[a-1] = "an"

        if not isVowel(newText2[a][0]) and a > 0:
            if newText2[(a-1)] == "an" or newText2[(a-1)] == "An":
                if "." in newText2[a-2]:
                    newText2[(a-1)] = "A"
                else:
                    newText2[(a-1)] = "a"
                       
    
    newText = ' '.join(newText2)
   


    newText3 = re.sub(r'\b(\w+)(?:\W+\1\b)+', r'\1',newText)
    newSplit = newText3.split()



    for x in range(len(newSplit)):
        if "," in newSplit[x]:
            if "," not in newSplit[x+1] and "and" in newSplit[x+2]:
                newSplit[x+1] += ","



                
    

    newText = ' '.join(newSplit)
    indexesOfOpenParantheses = []
    indexesOfClosedParantheses = []

    for x in range(len(newText)):
        if newText[x] == "(":
            indexesOfOpenParantheses.append(x)
        elif newText[x] == ")":
            indexesOfClosedParantheses.append(x)
  
    
    
    if len(indexesOfClosedParantheses) > len(indexesOfOpenParantheses):
        while len(indexesOfClosedParantheses) != len(indexesOfOpenParantheses):
            location = indexesOfClosedParantheses.pop()
            newText = newText[0 : location : ] + newText[location + 1 : :]
    elif len(indexesOfClosedParantheses) < len(indexesOfOpenParantheses):
        while len(indexesOfClosedParantheses) != len(indexesOfOpenParantheses):
            location = indexesOfOpenParantheses.pop()
            newText = newText[0 : location : ] + newText[location + 1 : :]
    



    if newText[0].isalpha and newText[0].islower():
        newText[0] = newText[0].upper() 



    return newText

    

