import matplotlib.pyplot as plt
import numpy as np
from decimal import *

bfTime = []
daTime = []
pTime = []

bfSL = []
daSL = []
pSL = []

bfSM = []
daSM = []
pSM = []

solutions = []
avgSL = []
medianSL = []

setSize = []
medianSV = []

sumOfSet = []
targetValue = []

OOV = []

myFile = open("allTest_allTestOutput.txt", 'r')
input = myFile.readlines()

for i, line in enumerate(input):

    if i == 0:
        continue
    else:
        arry = line.split(',')

        bfTime.append(int(arry[0]))
        daTime.append(float(arry[1]))
        pTime.append(int(arry[2]))

        bfSL.append(int(arry[3]))
        daSL.append(int(arry[4]))
        pSL.append(int(arry[5]))

        bfSM.append(int(arry[6]))
        daSM.append(int(arry[7]))
        pSM.append(int(arry[8]))

        solutions.append(int(arry[9]))
        avgSL.append(float(arry[10]))
        medianSL.append(int(arry[11]))

        setSize.append(int(arry[12]))
        medianSV.append(int(arry[13]))

        sumOfSet.append(int(arry[14]))
        targetValue.append(int(arry[15]))

        OOV.append(arry[16])

#WORK AREA
#
#

#
#
#END WORK AREA
def basicRunTimePlot():

#All plots
    plot = plt.figure(figsize=(8,6))
    ax = plot.add_subplot(111)
    ax.scatter(setSize, bfTime, s=10, label='Brute-Force', color='#1f77b4')

    ax2 = plot.add_subplot(111)
    ax2.scatter(setSize, daTime, s=10, label='Dynamic-Programming', color='#ff7f0e')

    ax3 = plot.add_subplot(111)
    ax3.scatter(setSize, pTime, s=10, label='Pairing', color='#2ca02c')

    plt.xlabel('Set Size')
    plt.ylabel('Run Time (nanoseconds)')
    plt.title('Runtime vs. Set Size')

    plt.xticks(np.arange(min(setSize), max(setSize)+1, 1.0))
    ax.set_ylim(0,14000000)
    plt.legend(loc='upper left')
    plt.show()

    #BF Plot
    plot2 = plt.figure(figsize=(8,6))
    bx = plot2.add_subplot(111)
    bx.scatter(setSize, bfTime, s=10, label='Brute-Force', color='#1f77b4')

    plt.xlabel('Set Size')
    plt.ylabel('Run Time (nanoseconds)')
    plt.title('Runtime vs. Set Size for Brute Force')

    plt.xticks(np.arange(min(setSize), max(setSize) + 1, 1.0))
    bx.set_ylim(0, 14000000)
    plt.legend(loc='upper left')
    plt.show()

    #DA Plot
    plot3 = plt.figure(figsize=(8,6))
    bx2 = plot3.add_subplot(111)
    bx2.scatter(setSize, daTime, s=10, label='Dynamic-Programming', color='#ff7f0e')

    plt.xlabel('Set Size')
    plt.ylabel('Run Time (nanoseconds)')
    plt.title('Runtime vs. Set Size for Dynamic Programming')

    plt.xticks(np.arange(min(setSize), max(setSize) + 1, 1.0))
    bx2.set_ylim(0, 1400000)
    plt.legend(loc='upper left')
    plt.show()

    #Pairing PLot
    plot4 = plt.figure(figsize=(8,6))
    bx3 = plot4.add_subplot(111)
    bx3.scatter(setSize, pTime, s=10, label='Pairing', color='#2ca02c')

    plt.xlabel('Set Size')
    plt.ylabel('Run Time (nanoseconds)')
    plt.title('Runtime vs. Set Size for Pairing Approach')

    plt.xticks(np.arange(min(setSize), max(setSize) + 1, 1.0))
    bx3.set_ylim(0, 1200000)
    plt.legend(loc='upper left')
    plt.show()

#basicRunTimePlot()


def averageBasicRunTimePlot():
    #Average Brute Force Time
    avgBFTime = []
    avgBFPre = [[] for x in range(max(setSize)+1)]
    for i, val in enumerate(setSize):
        avgBFPre[val].append(bfTime[i])

    for i, val in enumerate(avgBFPre):
        if len(val) == 0:
            avgBFTime.append(sum(val))
        else:
            avgBFTime.append(sum(val)/len(val))

    #Average DA Time
    avgDATime = []
    avgDAPre = [[] for x in range(max(setSize)+1)]
    for i, val in enumerate(setSize):
        avgDAPre[val].append(daTime[i])

    for i, val in enumerate(avgDAPre):
        if len(val) == 0:
            avgDATime.append(sum(val))
        else:
            avgDATime.append(sum(val) / len(val))

    #Average P Time
    avgPTime = []
    avgPPre = [[] for x in range(max(setSize)+1)]
    for i, val in enumerate(setSize):
        avgPPre[val].append(pTime[i])

    for i, val in enumerate(avgPPre):
        if len(val) == 0:
            avgPTime.append(sum(val))
        else:
            avgPTime.append(sum(val) / len(val))


    for x in range(4):
        avgBFTime.pop(x)
        avgDATime.pop(x)
        avgPTime.pop(x)

    #Plotting area All
    plot = plt.figure(figsize=(8,6))
    ax = plot.add_subplot(111)
    ax.scatter(range(4, max(setSize)+1), avgBFTime, s=40, label='Brute-Force', color="#1f77b4")

    ax2 = plot.add_subplot(111)
    ax2.scatter(range(4, max(setSize)+1), avgDATime, s=40, label='Dynamic-Programming', color='#ff7f0e')

    ax3 = plot.add_subplot(111)
    ax3.scatter(range(4, max(setSize)+1), avgPTime, s=40, label='Pairing', color='#2ca02c')

    plt.xlabel('Set Size')
    plt.ylabel('Average Run Time (nanoseconds)')
    plt.title('Average Runtime vs. Set Size for Pairing Approach')

    plt.xticks(np.arange(min(setSize), max(setSize)+1, 1.0))
    ax.set_ylim(0,2250000)
    plt.legend(loc='upper left')
    plt.show()

    #BF Graph
    plot2 = plt.figure(figsize=(8,6))
    bx = plot2.add_subplot(111)
    bx.scatter(range(4, max(setSize)+1), avgBFTime, s=40, label='Brute-Force', color="#1f77b4")

    plt.xlabel('Set Size')
    plt.ylabel('Average Run Time (nanoseconds)')
    plt.title('Average Runtime vs. Set Size for Brute Force')

    plt.xticks(np.arange(min(setSize), max(setSize)+1, 1.0))
    bx.set_ylim(0,2250000)
    plt.legend(loc='upper left')
    plt.show()

    #DA Graph
    plot3 = plt.figure(figsize=(8,6))
    bx2 = plot3.add_subplot(111)
    bx2.scatter(range(4, max(setSize)+1), avgDATime, s=40, label='Dynamic-Programming', color='#ff7f0e')

    plt.xlabel('Set Size')
    plt.ylabel('Average Run Time (nanoseconds)')
    plt.title('Average Runtime vs. Set Size for Dynamic Programming')

    plt.xticks(np.arange(min(setSize), max(setSize)+1, 1.0))
    bx2.set_ylim(0,250000)
    plt.legend(loc='upper left')
    plt.show()

    #P graph
    plot4 = plt.figure(figsize=(8,6))
    bx3 = plot4.add_subplot(111)
    bx3.scatter(range(4, max(setSize)+1), avgPTime, s=40, label='Pairing', color='#2ca02c')

    plt.xlabel('Set Size')
    plt.ylabel('Average Run Time (nanoseconds)')
    plt.title('Average Runtime vs. Set Size for Pairing Approach')

    plt.xticks(np.arange(min(setSize), max(setSize)+1, 1.0))
    bx3.set_ylim(0,40000)
    plt.legend(loc='upper left')
    plt.show()

#averageBasicRunTimePlot()


def pairingSplitTests():

    firstHalf = []
    firstHalfRT = []
    FHSolutions = []
    FHMSL = []

    secondHalf = []
    secondHalfRT = []
    SHSolution = []
    SHMSL = []

    for i, x in enumerate(setSize):
        if (x <= 13):
            firstHalf.append(x)
            firstHalfRT.append(pTime[i])
            FHSolutions.append(solutions[i])
            FHMSL.append(medianSL[i])
        else:
            secondHalf.append(x)
            secondHalfRT.append(pTime[i])
            SHSolution.append(solutions[i])
            SHMSL.append(medianSL[i])

    #Plotting Area

    #Setsize plot

    plot = plt.figure(figsize=(8,6))
    ax2 = plot.add_subplot(111)
    ax2.scatter(secondHalf, secondHalfRT, s=10, label='Size > 13', color='#ff7f0e')

    ax = plot.add_subplot(111)
    ax.scatter(firstHalf, firstHalfRT, s=10, label='Size <= 13', color='#1f77b4')

    plt.xlabel('Set Size')
    plt.ylabel('Run Time (nanoseconds)')
    plt.title('Runtime vs. Set Size for Pairing Approach')

    plt.xticks(np.arange(min(setSize), max(setSize)+1, 1.0))
    ax.set_ylim(0,1200000)
    plt.legend(loc='upper left')
    plt.show()


    #Number solutions plot
    plot2 = plt.figure(figsize=(8,6))
    bx2 = plot2.add_subplot(111)
    bx2.scatter(SHSolution, secondHalfRT, s=10, label='Size > 13 (max=%s' % (max(SHSolution)), color='#ff7f0e')

    bx = plot2.add_subplot(111)
    bx.scatter(FHSolutions, firstHalfRT, s=10, label='Size <= 13 (max=%s' % (max(FHSolutions)), color='#1f77b4')

    plt.xlabel('Number of Solutions')
    plt.ylabel('Run Time (nanoseconds)')
    plt.title('Runtime vs. Number of Solutions for Pairing Approach')

    #plt.xticks(np.arange(min(solutions), max(solutions)+1, 1.0))
    bx.set_ylim(0,1200000)
    plt.legend(loc='upper right')
    plt.show()

#pairingSplitTests()


def BFSplitTests():

    firstHalf = []
    firstHalfRT = []
    FHSolutions = []
    FHMSL = []

    secondHalf = []
    secondHalfRT = []
    SHSolution = []
    SHMSL = []

    for i, x in enumerate(bfTime):
        if (x <= 8000000):
            firstHalf.append(setSize[i])
            firstHalfRT.append(x)
            FHSolutions.append(solutions[i])
            FHMSL.append(medianSL[i])
        else:
            secondHalf.append(setSize[i])
            secondHalfRT.append(x)
            SHSolution.append(solutions[i])
            SHMSL.append(medianSL[i])

    #Plotting Area

    #Setsize plot

    plot = plt.figure(figsize=(8,6))

    ax = plot.add_subplot(111)
    ax.scatter(firstHalf, firstHalfRT, s=10, label='RT < 8e6', color='#1f77b4')

    ax2 = plot.add_subplot(111)
    ax2.scatter(secondHalf, secondHalfRT, s=10, label='RT > 8e6', color='#ff7f0e')

    plt.xlabel('Set Size')
    plt.ylabel('Run Time (nanoseconds)')
    plt.title('Runtime vs. Set Size for Brute Force')

    plt.xticks(np.arange(min(setSize), max(setSize)+1, 1.0))
    ax.set_ylim(0,14000000)
    plt.legend(loc='upper left')
    plt.show()


    #Number solutions plot
    plot2 = plt.figure(figsize=(8,6))

    bx = plot2.add_subplot(111)
    bx.scatter(FHSolutions, firstHalfRT, s=10, label='RT < 8e6 (max=%s' % (max(FHSolutions)), color='#1f77b4')

    bx2 = plot2.add_subplot(111)
    bx2.scatter(SHSolution, secondHalfRT, s=10, label='RT > 8e6 (max=%s' % (max(SHSolution)), color='#ff7f0e')


    plt.xlabel('Number of Solutions')
    plt.ylabel('Run Time (nanoseconds)')
    plt.title('Runtime vs. Number of Solutions for Brute Force')

    #plt.xticks(np.arange(min(solutions), max(solutions)+1, 1.0))
    bx.set_ylim(0,14000000)
    plt.legend(loc='upper right')
    plt.show()

    #Number solutions vs. Runtime
    #Average Brute Force Time
    counterList =[0 for x in range(int((max(solutions)/25)+1))]
    avgBFTime = []
    avgBFPre = [[] for x in range(max(solutions)+1)]
    for i, val in enumerate(solutions):
        avgBFPre[val].append(bfTime[i])

        counterList[int(val/25)] += 1

    for i, val in enumerate(avgBFPre):
        if len(val) == 0:
            avgBFTime.append(sum(val))
        else:
            avgBFTime.append(sum(val)/len(val))


    plot3 = plt.figure(figsize=(8,6))

    cx = plot3.add_subplot(111)
    cx.scatter(range(max(solutions)+1), avgBFTime, s=10, label='Brute Force', color='#1f77b4')

    plt.xlabel('Number of Solutions')
    plt.ylabel('Average Run Time (nanoseconds)')
    plt.title('Average Runtime vs. Number of Solutions for Brute Force')


 #   for i ,txt in enumerate(counterList):
  #      cx.annotate(txt, (25*i,2700000), rotation=45)

    plt.xticks(np.arange(min(solutions), max(solutions)+1, 25.0))
    cx.set_ylim(0,3000000)
    plt.legend(loc='upper right')
 #   plt.grid()
    plt.show()

#BFSplitTests()



def DASplitTests():

    #Average dA Time
    counterList =[0 for x in range(int((max(solutions)/25)+1))]
    avgBFTime = []
    avgBFPre = [[] for x in range(max(solutions)+1)]
    for i, val in enumerate(solutions):
        avgBFPre[val].append(daTime[i])

        counterList[int(val/25)] += 1

    for i, val in enumerate(avgBFPre):
        if len(val) == 0:
            avgBFTime.append(sum(val))
        else:
            avgBFTime.append(sum(val)/len(val))

    #Plotting Area

    plot = plt.figure(figsize=(8,6))
    ax2 = plot.add_subplot(111)
    ax2.scatter(solutions, daTime, s=10, label='Dynamic Programming', color='#ff7f0e')

    plt.xlabel('Number of Solutions')
    plt.ylabel('Run Time (nanoseconds)')
    plt.title('Runtime vs. Number of Solutions for Dynamic Programming')

    plt.xticks(np.arange(min(solutions), max(solutions)+1, 25.0))
    ax2.set_ylim(0,1200000)
    plt.legend(loc='upper left')
    plt.show()

    #Average Plot
    plot2 = plt.figure(figsize=(8,6))
    bx2 = plot2.add_subplot(111)
    bx2.scatter(range(max(solutions)+1), avgBFTime, s=10, label='Dynamic Programming', color='#ff7f0e')

    plt.xlabel('Number of Solutions')
    plt.ylabel('Run Time (nanoseconds)')
    plt.title('Average Runtime vs. Number of Solutions for Dynamic Programming')

    plt.xticks(np.arange(min(solutions), max(solutions)+1, 25.0))
    bx2.set_ylim(0,350000)
    plt.legend(loc='upper left')
    plt.show()


#DASplitTests()



def MedianSLPairingPlot():


    # Average dA Time
    counterList = [0 for x in range(int(max(pSL)+1))]
    avgBFTime = []
    avgBFPre = [[] for x in range(max(pSL) + 1)]
    for i, val in enumerate(pSL):
        avgBFPre[val].append(pTime[i])

        counterList[int(val)] += 1

    for i, val in enumerate(avgBFPre):
        if len(val) == 0:
            avgBFTime.append(sum(val))
        else:
            avgBFTime.append(sum(val) / len(val))

#All plots
    plot = plt.figure(figsize=(8,6))

    ax3 = plot.add_subplot(111)
    ax3.scatter(pSL, pTime, s=10, label='Pairing', color='#2ca02c')

    plt.xlabel('Solution Length')
    plt.ylabel('Run Time (nanoseconds)')
    plt.title('Runtime vs. Solution Length')

    plt.xticks(np.arange(min(pSL), max(pSL)+1, 1.0))
    ax3.set_ylim(0,300000)
    plt.legend(loc='upper right')
    plt.show()


    #Pairing PLot
    plot4 = plt.figure(figsize=(8,6))
    bx3 = plot4.add_subplot(111)
    bx3.scatter(range(max(medianSL)+1), avgBFTime, s=40, label='Pairing', color='#2ca02c')

    plt.xlabel('Median Solution Length')
    plt.ylabel('Run Time (nanoseconds)')
    plt.title('Runtime vs. Median Solution Length for Pairing Approach')

    plt.xticks(np.arange(min(medianSL), max(medianSL) + 1, 1.0))
    bx3.set_ylim(0, 30000)
    plt.legend(loc='upper left')
    plt.show()

MedianSLPairingPlot()