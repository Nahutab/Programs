#!/usr/bin/env python
# coding: utf-8

# In[2]:


#Batuhan Aykac
#SBU ID:112813167
#Question 1
hours = float(input("Enter hours worked: "))
rate = float(input("Enter rate per hour: "))

pay = (hours * rate)

print("Gross pay: ", pay)


# In[21]:


#Batuhan Aykac
#SBU ID:112813167
#Question 2

flag = "continue"
counter = 1
mean = 0
summedNumbers = 0
summedNumbersForDeviation = 0
listOfInputtedNumbers = []


print("Enter each students grades, to finish grading please type the word STOP")

while flag == "continue":
    number = input("Enter the grade for student number {} ".format(counter))
    if number == "STOP":
        mean = summedNumbers / (counter -1)
        for x in listOfInputtedNumbers:
            summedNumbersForDeviation += ((mean - x)**2)
        
        
        standardDeviation = ((summedNumbersForDeviation / (counter-1))**0.5)
        
        
        
        print("The mean is: ",mean)
        print("The standard deviation is: ",standardDeviation)
        break
    else:
        number = int(number)
        listOfInputtedNumbers.append(number)
        summedNumbers += number
        counter += 1
        if mean == 0:
            mean = number
        
           
            
            
            
        
    




# In[ ]:




