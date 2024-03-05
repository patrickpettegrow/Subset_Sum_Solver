import java.util.ArrayList;
import java.util.Collections;

public class OtherFunctions {
	
	
	public static DataObject allSubset(ArrayList<Long> list, Long targ){
		
		ArrayList<Integer> solutionList = new ArrayList<Integer>();
		long iterations = (long) Math.pow(2, list.size());
		int solutionLength;
		int totalSolutionLength = 0;
		long setSum = 0l;
		
		int[] occurance = new int[list.size()];
		
		for (int i = 0; i < iterations; i++){
			int sum = 0;
			ArrayList<Boolean> binVector = new ArrayList<Boolean>(
										Collections.nCopies(list.size(), false));
			
			String bin = Long.toBinaryString(new Long(i));
			for (int n = 0; n < bin.length(); n++){
				char c = bin.charAt(n);
				if (c == '1'){
					binVector.set(n, true);
					sum += list.get(n);
				}else if (c == '0'){
					//nothing
				}else{
					//Not a binary character
				}
			}
			if (sum == targ){
				
				solutionLength = 0;
				for (int n = 0; n < binVector.size(); n++){
					if (binVector.get(n) == true){
						solutionLength++;
						totalSolutionLength++;
						
						occurance[n]++;
					}
				}
				solutionList.add(Integer.valueOf(solutionLength));
			}
		}
		//sum of the set
		for (int i = 0; i < list.size(); i++){
			setSum += Long.valueOf(list.get(i));
		}
		
		//System.out.printf("There were [%d] solutions, of [%d] combinations.\n", solutionList.size(), iterations);
		DataObject returnObj = new DataObject();
		returnObj.solutions = solutionList.size();
		returnObj.avgSolutionLength = (double)totalSolutionLength / (double) solutionList.size();
		
		if (solutionList.size() > 0){
			returnObj.medianSolutionLength = Long.valueOf(solutionList.get(solutionList.size()/2));
		}else{
			returnObj.medianSolutionLength = 0l;
		}
		returnObj.sumOfSet = setSum;
		returnObj.targetValue = targ;
		
		//OccuranceData
		String oc = "";
		for (int i = 0; i < list.size(); i++){
			oc = oc.concat(String.format("%s:%d", list.get(i).toString(), occurance[i]));
			
			if (i != list.size()-1){
				oc = oc.concat(".");
			}
		}
		returnObj.occurenceOfVariables = oc;
		return  returnObj;
	}
	
	//All Subset for AllDataObject
	public static AllDataObject allSubsetAllData(ArrayList<Long> list, Long targ){
		
		ArrayList<Integer> solutionList = new ArrayList<Integer>();
		long iterations = (long) Math.pow(2, list.size());
		int solutionLength;
		int totalSolutionLength = 0;
		long setSum = 0l;
		
		int[] occurance = new int[list.size()];
		
		for (int i = 0; i < iterations; i++){
			int sum = 0;
			ArrayList<Boolean> binVector = new ArrayList<Boolean>(
										Collections.nCopies(list.size(), false));
			
			String bin = Long.toBinaryString(new Long(i));
			for (int n = 0; n < bin.length(); n++){
				char c = bin.charAt(n);
				if (c == '1'){
					binVector.set(n, true);
					sum += list.get(n);
				}else if (c == '0'){
					//nothing
				}else{
					//Not a binary character
				}
			}
			if (sum == targ){
				
				solutionLength = 0;
				for (int n = 0; n < binVector.size(); n++){
					if (binVector.get(n) == true){
						solutionLength++;
						totalSolutionLength++;
						
						occurance[n]++;
					}
				}
				solutionList.add(Integer.valueOf(solutionLength));
			}
		}
		//sum of the set
		for (int i = 0; i < list.size(); i++){
			setSum += Long.valueOf(list.get(i));
		}
		
		//System.out.printf("There were [%d] solutions, of [%d] combinations.\n", solutionList.size(), iterations);
		AllDataObject returnObj = new AllDataObject();
		returnObj.solutions = solutionList.size();
		returnObj.avgSolutionLength = (double)totalSolutionLength / (double) solutionList.size();
		
		if (solutionList.size() > 0){
			returnObj.medianSolutionLength = Long.valueOf(solutionList.get(solutionList.size()/2));
		}else{
			returnObj.medianSolutionLength = 0l;
		}
		returnObj.sumOfSet = setSum;
		returnObj.targetValue = targ;
		
		//OccuranceData
		String oc = "";
		for (int i = 0; i < list.size(); i++){
			oc = oc.concat(String.format("%s:%d", list.get(i).toString(), occurance[i]));
			
			if (i != list.size()-1){
				oc = oc.concat(".");
			}
		}
		returnObj.OOV = oc;
		return  returnObj;
	}
	
	public static ArrayList<Boolean> naiveSubset(ArrayList<Long> list, Long targ){
		long iterations = (long) Math.pow(2, list.size());
		
		for (int i = 0; i < iterations; i++){
			int sum = 0;
			ArrayList<Boolean> binVector = new ArrayList<Boolean>(
										Collections.nCopies(list.size(), false));
			
			String bin = Long.toBinaryString(new Long(i));
			for (int n = 0; n < bin.length(); n++){
				char c = bin.charAt(n);
				if (c == '1'){
					binVector.set(n, true);
					sum += list.get(n);
				}else if (c == '0'){
					//nothing
				}else{
					//Not a binary character
				}
			}
			if (sum == targ){
				return binVector;
			}
		}
		//System.out.println("No solution exists.");
		return null;
	}
	
	// A Dynamic Programming solution for subset sum problem
    // Returns true if there is a subset of set[] with sun equal to given sum
    public static boolean dynamicApproach(int set[], int n, int sum)
    {
        // The value of subset[i][j] will be true if there 
            // is a subset of set[0..j-1] with sum equal to i
        boolean subset[][] = new boolean[sum+1][n+1];
      
        // If sum is 0, then answer is true
        for (int i = 0; i <= n; i++)
          subset[0][i] = true;
      
        // If sum is not 0 and set is empty, then answer is false
        for (int i = 1; i <= sum; i++)
          subset[i][0] = false;
      
         // Fill the subset table in botton up manner
         for (int i = 1; i <= sum; i++)
         {
           for (int j = 1; j <= n; j++)
           {
             subset[i][j] = subset[i][j-1];
             if (i >= set[j-1])
               subset[i][j] = subset[i][j] || 
                                          subset[i - set[j-1]][j-1];
           }
         }
      
        /* // uncomment this code to print table
         for (int i = 0; i <= sum; i++)
         {
           for (int j = 0; j <= n; j++)
              System.out.println (subset[i][j]);
         } */
      
         return subset[sum][n];
    }
	/* This code is contributed by Rajat Mishra */

	
	
}
