import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Random;

public class StaticListGenerator {

	public static ArrayList<Long> generateAscendingList(int amt, int low, int intv, int seed){
		
		ArrayList<Long> list = new ArrayList<Long>(amt);
		Random rand = new Random(seed);
		Long nextNum = new Long(low);
		
		list.add(nextNum);
		
		for (int i = 0; i < amt-1; i++){
			
			//list.add(nextNum += rand.nextInt(intv * intv));
			list.add(new Long(nextNum += rand.nextInt(intv)));
			
		}
		
		System.out.println("Ascending List Generated!\n");
		return list;
		
	}
	
	public static ArrayList<Long> generateRandomAscendingList(int amt, int low, int intv){
		
		ArrayList<Long> list = new ArrayList<Long>(amt);
		Random rand = new Random();
		Long nextNum = new Long(low);
		
		list.add(nextNum);
		
		for (int i = 0; i < amt-1; i++){
			
			//list.add(nextNum += rand.nextInt(intv * intv));
			list.add(new Long(nextNum += rand.nextInt(intv)));
			
		}
		
		System.out.println("Ascending List Generated!\n");
		return list;
		
	}
	
	
	
	public static void generateRandomList(ArrayList<String> input) throws IOException{
		String filepath = "GeneratedSets\\";
		
		//Input parsing
		int low, high, amt, seed;
		String name, len;
		if (input.size() == 7){
			low = Integer.parseInt(input.get(1));
			high = Integer.parseInt(input.get(2));
			len = input.get(3);
			name = input.get(4);
			amt = Integer.parseInt(input.get(5));
			seed = Integer.parseInt(input.get(6));
			System.out.println("Input Parsed" + input.toString());
		}else{
			System.out.println("Incorrect Input");
			return;
		}
		
		if (name.equals("*")){
			name = String.format("%d_%d_%s_%d_%d", low, high, len, amt, seed);
		}
		
		int minLength = 0; int maxLength = 0; int minmaxDiff = 0;
		if (len.contains("-")){
			String[] sArry = len.split("-");
			minLength = Integer.parseInt(sArry[0]) - 1;
			maxLength = Integer.parseInt(sArry[1]);
			
			minmaxDiff = maxLength - minLength;
		}
		
		//End Input parsing
		
		//Operation Area
		File fo = new File(filepath.concat(name.concat(".txt")));
		FileWriter fw;
		fw = new FileWriter(fo);

		
		Random rand = new Random(seed);
		ArrayList<Integer> arryList;
		
		for (int i = 0; i < amt; i++){
			String list = "";
			arryList = new ArrayList<Integer>();
			
			for (int j = 0; j < minLength; j++){
				
				int n = rand.nextInt(high)+low;
				if (arryList.contains(n)){
					j--;
					continue;
				}else{
					arryList.add(n);
					
					if (j == minLength - 1){
						list = list.concat(String.format("%d", n));
					}else{
						list = list.concat(String.format("%d,", n));
					}
				}
			}
			
			if((i % (amt / minmaxDiff)) == 0){
				minLength++;
			}
			
			fw.write(list.substring(0, list.length()));
			fw.write(System.lineSeparator());
			
		}
		fw.close();
		
	}
		
	
}
