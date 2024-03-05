
public class AllDataObject {
	
	public Long bfTime;
	public Long daTime;
	public Long pTime;
	
	public int bfSL;
	public int daSL;
	public int pSL;
	
	public Long bfSM;
	public Long daSM;
	public Long pSM;
	
	public int solutions;
	public double avgSolutionLength;
	public Long medianSolutionLength;
	
	public int setSize;
	public Long medianSetValue;
	
	public Long sumOfSet;
	public Long targetValue;
	
	public String OOV;
	
	
	public AllDataObject(){
		bfTime = 0l;
		daTime = 0l;
		pTime = 0l;
		
		bfSL = 0;
		daSL = 0;
		pSL = 0;
		
		bfSM = 0l;
		daSM = 0l;
		pSM = 0l;
		
		solutions = 0;
		avgSolutionLength = 0.0;
		medianSolutionLength = 0l;
		
		setSize = 0;
		medianSetValue = 0l;

		sumOfSet = 0l;
		targetValue = 0l;
		
		OOV = "";
	}
	
	public String getHeaders(){
		return "BFTime,DATime,PTime,BFSL,DASL,PSL,BFSM,DASM,PSM#Solutions,AVG.SL,MedianSL,SetSize,MSV,SumOfSet,TargetValue,OOV";
	}
	public String toString(){
		return String.format("%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%f,%d,%d,%d,%d,%d,%s", bfTime, daTime, pTime, bfSL, daSL, pSL, bfSM, daSM, pSM,
				solutions, avgSolutionLength, medianSolutionLength, setSize, medianSetValue, sumOfSet, targetValue, OOV);
	}

}
