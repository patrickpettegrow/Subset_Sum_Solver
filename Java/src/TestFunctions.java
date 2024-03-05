import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class TestFunctions {
	
	public static void testPairingApproach(ArrayList<String> input) throws IOException{
		String filepath = "GeneratedSets\\";
		String outFilePath = "Output\\";
		//Input parsing
		String fileName = input.get(1);
		Long targ = Long.parseLong(input.get(2));
		String outFile = input.get(3);
		//Done Input
		int counter = 0;
		
		
		ArrayList<String> stringList;
		ArrayList<Long> list = new ArrayList<Long>();
		
		Scanner scan = new Scanner(new File(filepath.concat(fileName)));
		
		while (scan.hasNextLine()){
			list.clear();
			stringList = new ArrayList<String>(Arrays.asList(scan.nextLine().split(",")));
			
			for (int i = 0; i < stringList.size(); i++){
				list.add(Long.parseLong(stringList.get(i)));
			}
		

			Collections.sort(list);
			ArrayList<AdditionTuple> tList;
			
			Long totalTime;
			Long startTime = System.nanoTime();
			
			tList = SubsetAdditionDriver_v2.solveSubsetSum(list, targ);
			
			Long endTime = System.nanoTime();
			totalTime = new Long(endTime - startTime);
			
			/*
			int orgValCount = 0; 
			if (tList != null){
				
				System.out.printf("Solution is %d items: %s.\nOriginal Values are: ", tList.size(), tList.toString());
				
				for (int i = 0; i < tList.size(); i++){
					System.out.printf("%s, ",tList.get(i).toBaseSet());
					orgValCount += tList.get(i).counter;
				}
				System.out.printf("\n#Originals: %d\n", orgValCount);
			}else{
				System.out.println("No solution has been found");
			}
			
			System.out.printf("Time Taken: %dus.\n", totalTime/1000);
			*/
	
			//System.out.printf("Waiting on all subsetSum #%d\n", counter++);
			DataObject solution = OtherFunctions.allSubset(list,  targ);
			
			solution.time = totalTime;
			solution.medianSetValue = list.get(list.size()/2);
			
			if (tList != null){
				solution.specificSolutionLength = tList.size();
				
				ArrayList<Long> theBaseList = new ArrayList<Long>();
				for (AdditionTuple t: tList){
					theBaseList.addAll(t.toBaseList());
				}
				solution.specificSolutionMedian = theBaseList.get(theBaseList.size()/2);
				
			}else{
				solution.specificSolutionLength = 0;
				
				solution.specificSolutionMedian = 0l;
			}
			DataWriters.ratioTest(solution, outFilePath.concat(outFile));
		}
		scan.close();
		
	}
	
	public static void testNaiveApproach(ArrayList<String> input) throws IOException{
		String filepath = "GeneratedSets\\";
		String outFilePath = "Output\\";
		//Input parsing
		String fileName = input.get(1);
		Long targ = Long.parseLong(input.get(2));
		String outFile = input.get(3);
		//Done Input
		int counter = 0;
		ArrayList<String> stringList;
		ArrayList<Long> list = new ArrayList<Long>();
		Scanner scan = new Scanner(new File(filepath.concat(fileName)));
		
		while (scan.hasNextLine()){
			list.clear();
			stringList = new ArrayList<String>(Arrays.asList(scan.nextLine().split(",")));
			for (int i = 0; i < stringList.size(); i++){
				list.add(Long.parseLong(stringList.get(i)));
			}
			Collections.sort(list);
			ArrayList<Boolean> tList;
			Long totalTime;
			Long startTime = System.nanoTime();
			
			tList = OtherFunctions.naiveSubset(list, targ);
			
			Long endTime = System.nanoTime();
			totalTime = new Long(endTime - startTime);
			
			//DataObject solution = OtherFunctions.allSubset(list,  targ);
			DataObject solution = new DataObject();
			
			solution.time = totalTime;
			solution.medianSetValue = list.get(list.size()/2);
			if (tList != null){
				solution.specificSolutionLength = tList.size();
				ArrayList<Long> theBaseList = new ArrayList<Long>();
				for (int i = 0; i < tList.size(); i++){
					if (tList.get(i)){
						theBaseList.add(list.get(i));
					}
				}
				Collections.sort(theBaseList);
				solution.specificSolutionMedian = theBaseList.get(theBaseList.size()/2);
			}else{
				solution.specificSolutionLength = 0;
				solution.specificSolutionMedian = 0l;
			}
			DataWriters.ratioTest(solution, outFilePath.concat(outFile));
		}
		scan.close();
		
	}
	
	//Code Retrieved from https://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
	public static void testDynamicApproach(ArrayList<String> input) throws IOException{
		String filepath = "GeneratedSets\\";
		String outFilePath = "Output\\";
		//Input parsing
		String fileName = input.get(1);
		Long targ = Long.parseLong(input.get(2));
		int targ2 = Integer.parseInt(input.get(2));
		String outFile = input.get(3);
		//Done Input
		int counter = 0;
		ArrayList<String> stringList;
		ArrayList<Long> list = new ArrayList<Long>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		Scanner scan = new Scanner(new File(filepath.concat(fileName)));
		
		while (scan.hasNextLine()){
			list.clear();
			stringList = new ArrayList<String>(Arrays.asList(scan.nextLine().split(",")));
			for (int i = 0; i < stringList.size(); i++){
				list.add(Long.parseLong(stringList.get(i)));
				list2.add(Integer.parseInt(stringList.get(i)));
			}
			Collections.sort(list);
			Collections.sort(list2);
			int[] tempList = new int[list.size()];
			for (int i = 0; i < list.size(); i++){
				tempList[i] = list2.get(i);
			}
			Boolean tList;
			Long totalTime;
			Long startTime = System.nanoTime();
			
			tList = OtherFunctions.dynamicApproach(tempList, list.size(), targ2);
			
			Long endTime = System.nanoTime();
			totalTime = new Long(endTime - startTime);
			
			//DataObject solution = OtherFunctions.allSubset(list,  targ);
			DataObject solution = new DataObject();
			
			solution.time = totalTime;
			solution.medianSetValue = list.get(list.size()/2);
			
			/* Cannot be run by limitation of the dynamic approach
			if (tList != null){
				solution.specificSolutionLength = tList.size();
				ArrayList<Long> theBaseList = new ArrayList<Long>();
				for (int i = 0; i < tList.size(); i++){
					if (tList.get(i)){
						theBaseList.add(list.get(i));
					}
				}
				
				Collections.sort(theBaseList);
				solution.specificSolutionMedian = theBaseList.get(theBaseList.size()/2);
			}else{
				solution.specificSolutionLength = 0;
				solution.specificSolutionMedian = 0l;
			}
			*/
			DataWriters.ratioTest(solution, outFilePath.concat(outFile));
			scan.close();
		}
		
	}
	
	//All test
	public static void testAllData(ArrayList<String> input) throws IOException{
		String filepath = "GeneratedSets\\";
		String outFilePath = "Output\\";
		//Input parsing
		String fileName = input.get(1);
		Long targ = Long.parseLong(input.get(2));
		int targ2 = Integer.parseInt(input.get(2));
		String outFile = input.get(3);
		//Done Input
		int counter = 0;
		ArrayList<String> stringList;
		ArrayList<Long> list = new ArrayList<Long>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		Scanner scan = new Scanner(new File(filepath.concat(fileName)));
		
		while (scan.hasNextLine()){
			list.clear();
			stringList = new ArrayList<String>(Arrays.asList(scan.nextLine().split(",")));
			for (int i = 0; i < stringList.size(); i++){
				list.add(Long.parseLong(stringList.get(i)));
				list2.add(Integer.parseInt(stringList.get(i)));
			}
			Collections.sort(list);
			Collections.sort(list2);
			int[] tempList = new int[list.size()];
			for (int i = 0; i < list.size(); i++){
				tempList[i] = list2.get(i);
			}
			
			ArrayList<Boolean> ListBF;
			Boolean ListDA;
			ArrayList<AdditionTuple> ListP;
			
			Long totalTimeBF, totalTimeDA, totalTimeP, startTime, endTime;
			
			//BF Test
			startTime = System.nanoTime();
			ListBF = OtherFunctions.naiveSubset(list, targ);
			endTime = System.nanoTime();
			totalTimeBF = new Long(endTime - startTime);
			
			//DA Test
			startTime = System.nanoTime();
			ListDA = OtherFunctions.dynamicApproach(tempList, list.size(), targ2);
			endTime = System.nanoTime();
			totalTimeDA = new Long(endTime - startTime);
			
			//P Test
			startTime = System.nanoTime();
			ListP = SubsetAdditionDriver_v2.solveSubsetSum(list, targ);
			endTime = System.nanoTime();
			totalTimeP = new Long(endTime - startTime);
			
			
			//DataObject solution = OtherFunctions.allSubset(list,  targ);
			AllDataObject solution = OtherFunctions.allSubsetAllData(list, targ);
			solution.bfTime = totalTimeBF;
			solution.daTime = totalTimeDA;
			solution.pTime = totalTimeP;
			
			solution.medianSetValue = list.get(list.size()/2);
			solution.setSize = list.size();
			
			//Data for BF specific solution
			if (ListBF != null){
				ArrayList<Long> theBaseList = new ArrayList<Long>();
				for (int i = 0; i < ListBF.size(); i++){
					if (ListBF.get(i)){
						theBaseList.add(list.get(i));
					}
				}
				Collections.sort(theBaseList);
				solution.bfSL = theBaseList.size();
				solution.bfSM = theBaseList.get(theBaseList.size()/2);
			}else{
				solution.bfSL = 0;
				solution.bfSM = 0l;
			}
			
			//Data for Pairing specific solution
			if (ListP != null){
				ArrayList<Long> theBaseList = new ArrayList<Long>();
				for (AdditionTuple t: ListP){
					theBaseList.addAll(t.toBaseList());
				}
				solution.pSL = theBaseList.size();
				solution.pSM = theBaseList.get(theBaseList.size()/2);
				
			}else{
				solution.pSL = 0;
				solution.pSM = 0l;
			}
			
			DataWriters.allTest(solution, outFilePath.concat(outFile));
		}
		scan.close();
	}
	
	public static void testPosterData(ArrayList<String> input) throws IOException{
		String filepath = "GeneratedSets\\";
		String outFilePath = "Output\\";
		//Input parsing
		String fileName = input.get(1);
		Long targ = Long.parseLong(input.get(2));
		String outFile = input.get(3);
		
		//Done Input
		int counter = 0;
		ArrayList<String> stringList;
		ArrayList<Long> list = new ArrayList<Long>();
		Scanner scan = new Scanner(new File(filepath.concat(fileName)));
		scan.nextLine();
		while (scan.hasNextLine()){
			list.clear();
			stringList = new ArrayList<String>(Arrays.asList(scan.nextLine().split(",")));
			
			for (int i = 0; i < stringList.size(); i++){
				list.add(Long.parseLong(stringList.get(i)));
			}
			Collections.sort(list);
			ArrayList<AdditionTuple> tList;
			Long totalTime;
			Long startTime = System.nanoTime();
			
			tList = SubsetAdditionDriver_v2.solveSubsetSum(list, targ);
			
			Long endTime = System.nanoTime();
			totalTime = new Long(endTime - startTime);
			DataObject solution = new DataObject();
			
			solution.time = totalTime;
			solution.setSize = list.size();
			solution.medianSetValue = list.get(list.size()/2);
			if (tList != null){
				solution.specificSolutionLength = tList.size();
				ArrayList<Long> theBaseList = new ArrayList<Long>();
				for (AdditionTuple t: tList){
					theBaseList.addAll(t.toBaseList());
				}
				Collections.sort(theBaseList);
				solution.specificSolutionMedian = theBaseList.get(theBaseList.size()/2);
				
			}else{
				solution.specificSolutionLength = 0;
				solution.specificSolutionMedian = 0l;
			}
			DataWriters.posterTest(solution, outFilePath.concat(outFile));
			System.out.println(String.format("Test %d done.", counter++));
		}
	}
	

}
