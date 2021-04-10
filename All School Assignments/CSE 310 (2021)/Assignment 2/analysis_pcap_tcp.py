#Batuhan Aykac
#SBU ID:112813167


import dpkt
import struct
import socket
import ipaddress


filename = input("Input the file name: ")


senderIP = "130.245.145.12"
recieverIP = "128.208.2.198"

file = open(filename,"rb")
openFlowCounter = 0
pcap = dpkt.pcap.Reader(file)




def findPacket(src, dest, src1, dest1):
    if src == dest1 and src1 == dest:
        return True
    if src == src1 and dest == dest1:
        return True
    return False

listOfAllPackets = []
flows = {}
synackpackets = []
tpflows = {}


for ts,pkt in pcap:
    eth = dpkt.ethernet.Ethernet(pkt)
    ip = eth.data
    tcp = ip.data

    tempTuple = [eth,ts]


    listOfAllPackets.append(tempTuple)
    if (tcp.flags & dpkt.tcp.TH_SYN) and (tcp.flags & dpkt.tcp.TH_ACK):
        
        flows[ip.data.dport] = []
        tpflows[ip.data.dport] = []
        synackpackets.append(eth)
    


for a in listOfAllPackets:
    for x in tpflows.keys():
        if findPacket(a[0].data.data.dport,a[0].data.data.sport,x,80) == True:
            tpflows[x].append(a)
        else:
            continue



counter = 1
for a in listOfAllPackets:
    for x in flows.keys():
            if findPacket(a[0].data.data.dport,a[0].data.data.sport,x,80) == True:
                if len(flows[x]) <6:
                    flows[x].append(a[0])
                else:
                    continue


countpackets = 0

totalBytes = 0
totalTime = 0
firstPacketTS = 0
lastPacketTS = 0

listOfByteSizes = []









counts = 0


 

for x in flows.keys():
    totalBytes = 0
    flows.get(x).pop(0)
    flows.get(x).pop(0)
    flows.get(x).pop(0)
    

    print("\n")
    print("Source port: ", synackpackets[countpackets].data.data.dport)
    print("Source ip: ", ipaddress.IPv4Address(synackpackets[countpackets].data.dst))
    print("Dest port: ", synackpackets[countpackets].data.data.sport)
    print("Dest ip: ", ipaddress.IPv4Address(synackpackets[countpackets].data.src))
    print("\n")
    countpackets += 1

    
    print("Transaction: # 1")
    print("Sequence Number: ", flows.get(x)[0].data.data.seq)
    print("Acknowledgement Number: ", flows.get(x)[0].data.data.ack)
    print("Recieve Window Size: ", flows.get(x)[0].data.data.win * 16384)

    print("\n")

    print("Sequence Number: ", flows.get(x)[1].data.data.ack)
    print("Acknowledgement Number: ", flows.get(x)[1].data.data.seq)
    print("Recieve Window Size: ", flows.get(x)[1].data.data.win * 16384)

    print("\n")

    print("Transaction: # 2")
    print("Sequence Number: ", flows.get(x)[1].data.data.seq)
    print("Acknowledgement Number: ", flows.get(x)[1].data.data.ack)
    print("Recieve Window Size: ", flows.get(x)[1].data.data.win * 16384)

    print("\n")

    print("Sequence Number: ", flows.get(x)[2].data.data.ack)
    print("Acknowledgement Number: ", flows.get(x)[2].data.data.seq)
    print("Recieve Window Size: ", flows.get(x)[2].data.data.win * 16384)

    print("\n")

    
    for item in tpflows.keys():
        tpflows.get(item).pop(0)
        tpflows.get(item).pop(0)
        tpflows.get(item).pop(0)

        for item2 in tpflows.get(item):
            if item2[0].data.data.dport == 80:
                totalBytes += len(item2[0].data.data)

        listOfByteSizes.append(totalBytes)
        totalBytes = 0
    




    

    packets = tpflows.get(x)


    firstPacketTS = packets[0][1]
    lastPacketTS = packets[-1][1]

   
    totalTime = lastPacketTS - firstPacketTS

    print("Sender Throughput: ", listOfByteSizes[counts]/totalTime, " bytes/sec")
    
    
    #Couldnt figure out congestion
    
    """congestList = []
    congestionCount = 0
    matchnum = 0
    currentPacketIndex = 0
    secondPacketIndex = 0
    outerCounter = -1
    innerCounter = 0
    bigcount = 0
    for x in tpflows.keys():
        templist1 = tpflows.get(x)
        bigcount = 0
        while bigcount != 3:
            congestionCount = 0
            for a in range(len(templist1)):
                if templist1[a][0].data.data.seq == (templist1[currentPacketIndex][0].data.data.ack + len(templist1[currentPacketIndex][0]) - 32):
                    secondPacketIndex = a
                    break
                else:
                    continue
            bigcount+=1
            newSlicedList = templist1[currentPacketIndex:secondPacketIndex]            

            for b in range(len(newSlicedList)):
                if newSlicedList[b][0].data.datasport == (templist1[currentPacketIndex][0].data.data.ack + len(templist1[currentPacketIndex][0]) - 32):
                    congestionCount +=1
            congestList.append(congestionCount)
        



        currentPacketIndex = secondPacketIndex
        print(congestList) """




    """ packetlist = []        
    offset = 0
    ackcounter = 0
    congestwindowtimer = 0
    congestions = []
    listofCongestions = []
    countforlistcongestions = 0
    for a in tpflows.keys():
        packetlist = tpflows.get(a)
        ackcounter = 0
        congestwindowtimer = 0
        congestions = []
        while congestwindowtimer != 3:
            for x in range(len(packetlist)):
                if packetlist[x][0].data.data.flags & dpkt.tcp.TH_ACK:
                    if offset >= len(packetlist)-1:
                        break
                    else:
                        if packetlist[offset][1] <= packetlist[x][1] and packetlist[x][1] <= packetlist[offset+1][1]:
                            ackcounter+=1
                            offset += 1
                        else:
                            continue

                else:
                    continue
            
            congestions.append(ackcounter)
            congestwindowtimer += 1 
         
        listofCongestions.append(congestions)
        countforlistcongestions+=1            
 """




    #print("listof congestions",listofCongestions)




    retransmissions = 0
    timeout = 0

    flag = False


    acklist1 = []
    acklist2 = []
    acklist3 = []
    acklist4 = []

    seqlist1 = []
    seqlist2 = []
    countseqs = []

    countACKS = []
    for x in tpflows.keys():
        list1 = tpflows.get(x)
        acklist1 = []
        acklist2 = []
        acklist3 = []
        acklist4 = []
        for a in range(len(list1)):

                if (list1[a][0].data.data.flags & dpkt.tcp.TH_ACK):
                    if list1[a][0].data.data.ack not in acklist1:
                        acklist1.append(list1[a][0].data.data.ack)
                    elif list1[a][0].data.data.ack not in acklist2:
                        acklist2.append(list1[a][0].data.data.ack)
                    elif list1[a][0].data.data.ack not in acklist3:
                        acklist3.append(list1[a][0].data.data.ack)                        
                    elif list1[a][0].data.data.ack not in acklist4:
                        acklist4.append(list1[a][0].data.data.ack)
                    elif list1[a][0].data.data.ack in acklist4:
                        continue

        countACKS.append(len(acklist4))
                        
               
    for x in tpflows.keys():
        listtemp = tpflows.get(x)
        seqlist1 = []
        seqlist2 = []
        for a in range(len(listtemp)):

            if listtemp[a][0].data.data.seq not in seqlist1:
                seqlist1.append(listtemp[a][0].data.data.seq)
            elif listtemp[a][0].data.data.seq not in seqlist2:
                seqlist2.append(listtemp[a][0].data.data.seq)
            elif listtemp[a][0].data.data.seq in seqlist2:
                continue

        countseqs.append(len(seqlist2))
    


            













    if (countseqs[counts]-1) < 0:
        print("Total retransmissions: 0")
    else:
        print("Total retransmissions: ", countseqs[counts] - 1)

    if (countACKS[counts]-1) < 0:
        print("Retransmissions due to triple duplicate ACK: 0")
    else:
        print("Retransmissions due to triple duplicate ACK: ", countACKS[counts] - 1)

    if ((countseqs[counts] -1) - (countACKS[counts] - 1))< 0:
        print("Retransmission due to Timeouts: 0")
    else:
        print("Retransmissions due to Timeouts: ", (countseqs[counts] -1) - (countACKS[counts] - 1))









    counts+=1




















    print("------------------------------------------------")











file.close()