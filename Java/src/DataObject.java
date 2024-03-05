
public class DataObject {
	
	public Long time;
	public int solutions;
	
	public int specificSolutionLength;
	public Long specificSolutionMedian;
	
	public double avgSolutionLength;
	public Long medianSolutionLength;
	
	public Long medianSetValue;
	
	public String occurenceOfVariables;
	public Long sumOfSet;
	public Long targetValue;
	
	public int setSize;
	
	public DataObject(){
		time = 0l;
		solutions = 0;
		
		specificSolutionLength = 0;
		specificSolutionMedian = 0l;
		
		avgSolutionLength = 0.0;
		medianSolutionLength = 0l;
		
		medianSetValue = 0l;
		
		occurenceOfVariables = "";
		sumOfSet = 0l;
		targetValue = 0l;
		
		setSize = 0;
	}
}
