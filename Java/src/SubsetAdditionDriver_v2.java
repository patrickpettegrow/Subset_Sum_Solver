import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SubsetAdditionDriver_v2 {
	
	public static int operationCounter;

	public static void main(String[] args) throws IOException {
		
		InputReader IR = new InputReader();
		boolean RUNNING = true;
		ArrayList<String> input;
		String command;
		
		while (RUNNING){
			input = IR.getNextTokenString();
			command = input.get(0);
			
			if (input.size() < 0 || input.get(0).equals("exit")){
				RUNNING = false;
			}else if (command.equals("help")){
				
				if (input.get(1).equals("generate")){
					System.out.println("Low High Length Name Amount Seed");
				}else if (input.get(1).equals("testp")){
					System.out.println("InputFile TargetVal OutputFile");
				}
				
			}else if (command.equals("generate")){
			
				
				StaticListGenerator.generateRandomList(input);
				System.out.println("Done Generating");
				
			}else if (command.equals("testp")){
				
				if (input.get(2).contains("-")){
					String[] temp = input.get(2).split("-");
					ArrayList<String> tempInput;
					
					for (int i = Integer.parseInt(temp[0]); i < Integer.parseInt(temp[1]); i++){
						tempInput = new ArrayList<String>(4);
						tempInput.add(command);
						tempInput.add(input.get(1));
						tempInput.add(String.valueOf(i));
						tempInput.add(input.get(3));
						
						System.out.printf("Cycle %d of %d.\n", i, Integer.parseInt(temp[1]));
						TestFunctions.testPairingApproach(tempInput);
						
					}
				}else{
					TestFunctions.testPairingApproach(input);
				}
				System.out.println("Done Testing");
			}else if (command.equals("testPoster")){
				String[] temp = input.get(2).split("-");
				ArrayList<String> tempInput;
				
				for (int i = Integer.parseInt(temp[0]); i < Integer.parseInt(temp[1]); i++){
					tempInput = new ArrayList<String>(4);
					tempInput.add(command);
					tempInput.add(input.get(1));
					tempInput.add(String.valueOf(i));
					tempInput.add(input.get(3));
					
					System.out.printf("Cycle %d of %d.\n", i, Integer.parseInt(temp[1]));
					TestFunctions.testPosterData(tempInput);
				}
			}else if (command.equals("testbf")){
				ArrayList<String> tempInput;
				String[] temp = input.get(2).split("-");
				
				for (int i = Integer.parseInt(temp[0]); i < Integer.parseInt(temp[1]); i++){
					tempInput = new ArrayList<String>();
					tempInput.add(command);
					tempInput.add(input.get(1));
					tempInput.add(String.valueOf(i));
					tempInput.add(input.get(3));
					
					System.out.printf("Cycle %d of %d.\n", i, Integer.parseInt(temp[1]));
					TestFunctions.testNaiveApproach(tempInput);
				}
			}else if(command.equals("testdp")){
				ArrayList<String> tempInput;
				String[] temp = input.get(2).split("-");
				
				for (int i = Integer.parseInt(temp[0]); i < Integer.parseInt(temp[1]); i++){
					tempInput = new ArrayList<String>();
					tempInput.add(command);
					tempInput.add(input.get(1));
					tempInput.add(String.valueOf(i));
					tempInput.add(input.get(3));
					
					System.out.printf("Cycle %d of %d.\n", i, Integer.parseInt(temp[1]));
					TestFunctions.testDynamicApproach(tempInput);
				}
			}else if (command.equals("alltest")){
				ArrayList<String> tempInput;
				String[] temp = input.get(2).split("-");
				
				for (int i = Integer.parseInt(temp[0]); i < Integer.parseInt(temp[1]); i++){
					tempInput = new ArrayList<String>();
					tempInput.add(command);
					tempInput.add(input.get(1));
					tempInput.add(String.valueOf(i));
					tempInput.add(input.get(3));
					
					System.out.printf("Cycle %d of %d.\n", i, Integer.parseInt(temp[1]));
					TestFunctions.testAllData(tempInput);
				}
			}
			
			
		}
		
		
		
		/*
		for (int j = 22; j < 23; j++){
			
			for(int i = 0; i < 500; i++){
				operationCounter = 0;
				
				TestFunctions.testPairingApproach(j, 1, 100, 200l);
				//TestFunctions.testNaiveApproach(j, 1, 100, 1200l);
			}
		}
		*/
		
	}
	
	/*
	 * Takes a set of AdditionTuple arrayList and checks to see if a target can be created
	 * by adding together some or all of the numbers in the set. If it can't do this,
	 * then it will start to decompose tuples when they are verified to not be in the set.
	 * If a target = 27, a tuple has the value of 30, no positive integer can be added to 30
	 * to produce 27, thus it can be decomposed into possibly a 10 and 20 values which could
	 * be used. If 30 was a base value then it is removed (no other values make it up).
	 */
	public static ArrayList<AdditionTuple> solveSubsetSum(ArrayList<Long> baseList, Long targ){
		
		ArrayList<AdditionTuple> returnList;
		ArrayList<AdditionTuple> list;
		
		/*
		 * STEP 1: 
		 * First we must construct the starting set of a single tuple built from all
		 * of the integers in our data set. We check each number to make sure it is not the
		 * solution. If it is, then we are done.
		 */
		
		list = initiateSet(baseList);	//Convert the int-list into a tuple-list
		
		do {
			//Search the list for the answer, if it is there, return it, else condense
			for (int i = 0; i < list.size(); i++){
				
				if (list.get(i).value == targ){
					
					//System.out.printf("Solution is a single number: %d.\n", list.get(i).value);
					
					returnList = new ArrayList<AdditionTuple>(1);
					returnList.add(list.get(i));
					return returnList;
					
				}
			}
			//If no single number is the answer in the set, then condense
			list = condenseSet(list);
			
			//Once condensed down to one item in the list, it will check again, then close
		} while (list.size() > 1);
		
		/*
		 * STEP 2:
		 * If the code progressed here then we do not have the answer yet in the numbers
		 * that were created. Since all the numbers in the base list have been added together
		 * we know for a fact that the solutions does not contain all of the numbers in the base
		 * set. Knowing this we can split the single additionTuple into the two tuples that
		 * created it and determine which one does not belong. This can't always be determined
		 * so if we don't know for certain we just guess.
		 */
		
		
		
		/*
		 * An idea to handle the {1, 101, 2, 102, 3, 103, ...} alternating numbers that cause
		 * the runtime to exceed O(2^n/2)
		 */
		Long newTarg = new Long(list.get(0).value - targ);
		if (newTarg < targ && newTarg > 0){
			
			//System.out.printf("Inverted Target is smaller, trying to solve.\n\n");
			
			returnList = findSubsetSum(list, newTarg);
			
			if (returnList != null){
				
				//System.out.printf("Inverted Target had an answer of:\n%s\n", returnList.toString());
				
				list = initiateSet(baseList);				//could optimize this
				
				boolean decomp = true;						//Decomposing trigger
				int loc = 0;								//what loc we are decomposing
				while (decomp){								//decompose the whole set
					
					if (returnList.get(loc).isBase == false){
						
						returnList = decomposeTuple(returnList, loc);
						
					}else{
						loc++;
					}
					
					if (loc > returnList.size()-1){
						decomp = false;
					}
				}
				
				
				for (int i = 0; i < returnList.size(); i++){
					
					for (int j = 0; j < list.size(); j++){
						
						if (returnList.get(i).value == list.get(j).value){
							
							list.remove(j);
							break;
						}	
					}
				}
				return list;
				
			}else{
				
				return findSubsetSum(list, targ);
				
			}
		}
		
		return findSubsetSum(list, targ);
	}
	
	
	
	/*
	 * Internal function for solveSubsetSum, requires a fully condensed set
	 */
	private static ArrayList<AdditionTuple> findSubsetSum(ArrayList<AdditionTuple> list, Long targ){
		
		boolean recentlyDecomposed = false;
		
		ArrayList<AdditionTuple> temp = new ArrayList<AdditionTuple>(list);	//Copy the list
		//System.out.printf("Attempting to solve [%d] for the list:  %s.\n", targ, temp.toString());
		
		while (temp.size() > 0){
			
			operationCounter++;
			
			Long listSum = new Long(0);
			
			//System.out.printf("%s\t%d\t%d\n", temp, targ, listSum);
			
			//Check to see if the target is in the current array, if it is, it will return and be done
			for (int i = 0; i < temp.size(); i++){
				
				Long tempVal = new Long(temp.get(i).value);
				listSum += tempVal;
				
				//System.out.printf("Summing list. Targ = %d, Current = %d\n", targ, tempVal);
				
				if (tempVal.equals(targ)){
					
					ArrayList<AdditionTuple> returnVal = new ArrayList<AdditionTuple>(1);
					returnVal.add(temp.get(i));
					
					//System.out.printf("Target was in the list:  %s.\n\n", returnVal.toString());
					
					return returnVal;
				}
			}
			
			//If what we have in the list is not even enough to make up to the targ, we done
			if (listSum < targ){
				//System.out.println("Sum of the list is too small.");
				return null;
			}
			
			//If it is not in the array, then we will have to check the values
			
			AdditionTuple listEnd = temp.get(temp.size()-1);
			/*
			 * Condition #1:
			 * The largest value in the set is larger than the target, then decompose
			 */
			if (listEnd.value > targ){			//Get the right-most (largest) value
				
				if (listEnd.min > targ){
					
					//System.out.println("Min value is greater than targ, not exploring this outcome.");
					temp.remove(temp.size()-1);
					continue;
					
				}
				
				//System.out.println("Value is too large, decomposing list.");
				
				temp = decomposeTuple(temp, temp.size() - 1);	//Decompose it since it is to large.
				recentlyDecomposed = true;		//Have we decomposed yet?
			/*
			 * Condition #2:
			 * The right-most (largest) value is smaller than the target. This means it can be taken and used as a starting value.
			 */
			}else if (listEnd.value < targ){
				
				Long newTarg = new Long(targ - listEnd.value);		//Set the new target to the target -minus- value being inspected
				ArrayList<AdditionTuple> solutionList = new ArrayList<AdditionTuple>();
				solutionList.add(listEnd);				//Assume that starting tuple is in the answer, solve the rest (smaller list)
				
				ArrayList<AdditionTuple> recursionList = new ArrayList<AdditionTuple>(temp); //Copy the list
				recursionList.remove(recursionList.size()-1);					//Remove the starting value
				
				//System.out.printf("Value is smaller, using a new starting value.\nNew Targ: %d\n", newTarg);
				
				//If we just decomposed a pair, and we are now selecting one of the elements
				//that made up that pair, we know the other element is not in solution, because
				//the previous call to this would have returned it
				if (recentlyDecomposed == true){
					
					//System.out.println("List was recently decommposed, decomposing paired value.");
					
					//We decomposed because the solution could be using one of the two elements
					recursionList = decomposeTuple(recursionList, recursionList.size()-1);
					
				}
				
				recursionList = findSubsetSum(recursionList, newTarg);	//Find the solution using the new starting value
				
				//This recursive call will always trigger itself until a point where null or the answer is returned. If 7 is the
				//target and 7 is in the list, then 7 as a list will be returned. If there is no 7, then null will be returned 
				//on the final call. So we know based on what is returned if we have an answer no matter how deep into the recursion
				//we are. If there is an answer we need to send the answer plus the starting (base) value back (return).
				//If there is only null, then we know this base value did not work
				if (recursionList != null){
					
					//System.out.printf("An answer of %s was sent back!\n\n", recursionList.toString());
					
					recursionList.addAll(solutionList);	//Add everything we got back as answers
					return recursionList;				//Keep sending it back to the original caller
					
				}else{
					
					//System.out.println("No solution was sent back, removing base value and trying again.");
					//System.out.printf("Reverting to: %s.\n\n", temp.toString());
					
					//There was no solution for this starting value
					temp = decomposeTuple(temp, temp.size()-1);			//Remove the value we found to not be in the answer, try again
					recentlyDecomposed = true;
				}
				
			}
			
		}
		
		return null;
	}
	
	
	
	/*
	 * Converts an arraylist of type integer to an arraylist of type additionTuple
	 * so that standard type of data can be used the specific operations of this driver.
	 */
	public static ArrayList<AdditionTuple> initiateSet(ArrayList<Long> inputArray){
		
		//System.out.println("Initiating Set by convertng integers to additiontuples.");
		
		ArrayList<AdditionTuple> newSet = new ArrayList<AdditionTuple>(inputArray.size());
		
		for (int i = 0; i < inputArray.size(); i++){
			newSet.add(new AdditionTuple(inputArray.get(i)));
		}
		
		return newSet;
	}
	
	
	
	/*
	 * Takes adjacent additionTuples and adds their values together into a single value.
	 * Preserves the history of the tuples that were added together to obtain the current
	 * value, so that the current tuple can be deconstructed into the tuples that made it up,
	 * this can be done recursivly to obtain the base set of integers (in the Addition tuple form,
	 * same as what occurs after the "initiateSet()" function is called.
	 */
	public static ArrayList<AdditionTuple> condenseSet(ArrayList<AdditionTuple> baseSet){
		
		//System.out.println("Condensing the set");
		
		int newSetSize = (baseSet.size() / 2) + (baseSet.size() % 2);
		
		ArrayList<AdditionTuple> newSet = new ArrayList<AdditionTuple>(newSetSize);
		
		for (int i = 0; i < baseSet.size(); i+=2){
			
			if (baseSet.size() - i == 1){
				
				//System.out.printf("%d has no pair and is being added alone.\n", baseSet.get(i).value);
				
				newSet.add(baseSet.get(i));

			}else{
			
				//System.out.printf("%d and %d are being added together as %d.\n", 
				//		baseSet.get(i).value, baseSet.get(i + 1).value, baseSet.get(i).value + baseSet.get(i+1).value);
				
				newSet.add(new AdditionTuple(baseSet.get(i), baseSet.get(i + 1)));
			}
			
		}
		//System.out.printf("Condensed Set is:  %s.\n\n", newSet.toString());
		
		return newSet;
		
	}

	
	
	/* 
	 * In the event that an answer can't be found ont he current list of additionTuples
	 * then it is necessary to decompose one of the tuples. Since the parent tuple
	 * was not in the subset solution, then we know only one of the two childen that make
	 * up the parent are present, if both were present then the parent would be in the set
	 * as it is the addition of the two children.
	 */
	public static ArrayList<AdditionTuple> decomposeTuple(ArrayList<AdditionTuple> list, int loc){
		
		AdditionTuple t = list.get(loc);
		ArrayList<AdditionTuple> newList;
		
		//System.out.printf("%d is not in the set.\n", t.value);
		
		//If the tuple is a base tuple, then it can't be broken down
		//into two smaller numbers. Thus it is removed because it has been determined to not
		//be part of the solution. Example: we are looking for 17, and this number is 40.
		if (t.isBase == true){
			
			//System.out.printf("%d cannot be decomposed and is being removed.\n", t.value);
			
			newList = new ArrayList<AdditionTuple>(list);	//Copy the list
			newList.remove(loc);							//Remove the element
			newList.trimToSize();							//Trim

		}else{
			
			AdditionTuple temp = list.get(loc);				//Copy the tuple in question
			//System.out.printf("%d is being separated to %d and %d.\n", t.value, t.x.value, t.y.value);
			
			newList = new ArrayList<AdditionTuple>(list.size() + 1);//Setup largest set
			newList.addAll(list);							//Add all values of original
			
			//Remove say position 7, 8 shifts down to where 7 was.
			//Add temp.y to position 7, 8 shifts to the right and is back where it was
			//Add temp.x to position 7, temp.y shifts to 8 and 8 shifts to 9.
			
			newList.remove(loc);							//Remove the loc tuple
			newList.add(loc, temp.y);						//Remove and everything shifts
			newList.add(loc, temp.x);						//Add x 2nd so it is before y
			
		}
		
		//System.out.printf("Decomposed List: %s.\n\n", newList.toString());
		
		return newList;
		
	}
}
