import sys
import dns.name
import dns.message
import dns.query
import dns.flags
from time import perf_counter
import datetime

#uses command line input
websiteDomain = sys.argv[1]

rootIP = '198.41.0.4'

outputString = ''

#string used for the writing on the text file later.
outputString += ("QUESTION SECTION: \n" + websiteDomain + "     IN A \n\n")

#calculates the date and time currently 
now = datetime.datetime.now()
#starts the timer to calculate query time
t1_start = perf_counter()
flag = 1

#starts the query
quer = dns.message.make_query(websiteDomain,dns.rdatatype.A)
result = dns.query.udp(quer, rootIP)



while(flag == 1):
    #breaks loop if there is at least one answer
    if len(result.answer) > 0: 
        break  
    else:
        #fetches CNAME
        if ('CNAME' in str(result.additional[0])):
            outputString += (result.additional[0][0])
            break
        #says that the domain cant be resolved if it can't be resolved.
        elif ('NS' in str(result.additional[0])):
            outputString += ("IN  NS  CAN'T RESOLVE")
            break
        #iterates through to pass the 'AAAA' if it finds one.
        elif ('AAAA' in str(result.additional[0])):
            for x in range(len(result.additional)):

                if 'AAAA' in str(result.additional[x]):
                    
                    continue

                if 'A' in str(result.additional[x]):
                    quer = dns.message.make_query(websiteDomain,dns.rdatatype.A)
                    serverIP = result.additional[x][0]
                    result = dns.query.udp(quer, str(serverIP))
                    break
         #resolves again if A is found       
        elif ('A' in str(result.additional[0])):
            quer = dns.message.make_query(websiteDomain,dns.rdatatype.A)

            serverIP = result.additional[0][0]

            result = dns.query.udp(quer, str(serverIP))

t1_stop = perf_counter()


outputString += ("Answer Section: \n")
outputString += str(result.answer[0])


#calculates query time in ms
inSeconds = t1_stop - t1_start
milliSeconds = inSeconds * 1000

outputString += ("\n\nQuery time: " + str(round(milliSeconds)) + " ms")

outputString += ("\nWHEN: "+ str(now.strftime("%Y-%m-%d %H:%M:%S")))


#creates and writes into the file if it doesn't exist yet and if it does it just writes into the file.
f = open("mydig_output", "w+")

f.write(outputString)
f.close




