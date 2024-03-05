import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriters {
	
	public static void ratioTest(DataObject solution, String fileName) throws IOException{
		File fo = new File(fileName.concat("_output.txt"));
		
		if (fo.exists()){
			FileWriter fw = new FileWriter(fo, true);
			
			fw.write(String.format("%d,%.4f,%d,%d,%d,%d,%d,%d,%d,%s", solution.solutions, solution.avgSolutionLength, solution.time/1000, 
													solution.medianSolutionLength, solution.medianSetValue, solution.sumOfSet, solution.targetValue,
													solution.specificSolutionLength, solution.specificSolutionMedian, solution.occurenceOfVariables));
			fw.write(System.lineSeparator());
			
			fw.close();
		}else{
			fo.createNewFile();
			FileWriter fw = new FileWriter(fo, true);
			fw.write(String.format("# of Solutions,avg. Solution Length,Time (micro-seconds),Median Solution Length, Median Set Value,"
					+ "Sum of Set,Target Value,SpecificSolutionLength,SpecificSolutionMedian,Occurence"));
			fw.write(System.lineSeparator());
			fw.close();
		}
	}
	
	public static void allTest(AllDataObject solution, String fileName) throws IOException{
		File fo = new File(fileName.concat("_allTestOutput.txt"));
		
		if (fo.exists()){
			FileWriter fw = new FileWriter(fo, true);
			
			fw.write(solution.toString());
			fw.write(System.lineSeparator());
			fw.close();
		}else{
			fo.createNewFile();
			FileWriter fw = new FileWriter(fo, true);
			fw.write(solution.getHeaders());
			fw.write(System.lineSeparator());
			fw.close();
		}
	}
	
	
	public static void posterTest(DataObject solution, String fileName) throws IOException{
		File fo = new File(fileName.concat("_posterOutput.txt"));
		
		if (fo.exists()){
			FileWriter fw = new FileWriter(fo, true);
			fw.write(String.format("%d,%d,%d,%d", solution.setSize, solution.time/1000, solution.specificSolutionLength, solution.specificSolutionMedian));
			fw.write(System.lineSeparator());
			fw.close();
			
		}else{
			fo.createNewFile();
			FileWriter fw = new FileWriter(fo, true);
			fw.write("time,solutionLength,setSize,solutionMedian");
			fw.write(System.lineSeparator());
			fw.close();
		}
	}

}
