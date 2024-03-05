import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {
		
	Scanner scan;
	
	//Open scanner on creation
	public InputReader(){
		scan = new Scanner(System.in);
	}
	
	//Close scanner on command
	public void close(){
		scan.close();
	}
	
	
	//Returns a tokenized list of the input
	//"Hello world this is my string" ==
	//[Hello, world, this, is, my, string]
	public ArrayList<String> getNextTokenString(){
		ArrayList<String> tokenString = new ArrayList<String>();
		String temp;
		temp = scan.nextLine();
		temp = temp.trim();
		
		int i;
		int y;
		for (i = 0; i < temp.length(); i++){
			y = i;
			
			while (y < temp.length() && temp.charAt(y) != ' '  ){
				y++;
			}
			if (y > i){
				tokenString.add(temp.substring(i, y));
				i = y;
			}
			
		}
		//System.out.println(tokenString);
		return tokenString;
	}
	
}
