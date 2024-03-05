import java.util.ArrayList;

public class AdditionTuple {
	
	public boolean isBase;
	public AdditionTuple x;
	public AdditionTuple y;
	public Long value;
	
	public Long min;
	public Long max;
	
	public static int counter = 0;
	
	
	public AdditionTuple(Long val){
		value = new Long(val);
		isBase = true;
		
		min = new Long(val);
		max = new Long(val);
	}
	public AdditionTuple(AdditionTuple n, AdditionTuple m){
		this.x = n;
		this.y = m;
		value = n.value + m.value;
		isBase = false;
		
		min = Math.min(n.min, m.min);
		max = Math.max(n.max, m.max);
	}
	
	public String toString(){
		return String.valueOf(this.value);
	}
	
	public String toBaseSet(){
		
		if (this.isBase){
			counter++;
			return String.valueOf(value);
		}else{
			return this.x.toBaseSet().concat(", ").concat(this.y.toBaseSet());
		}
		
	}
	
	public ArrayList<Long> toBaseList(){
		
		ArrayList<Long> returnList = new ArrayList<Long>();
		
		if (this.isBase){
			returnList.add(this.value);
			return returnList;
		}else{
			returnList.addAll(this.x.toBaseList());
			returnList.addAll(this.y.toBaseList());
			return returnList;
		}
		
	}

}
