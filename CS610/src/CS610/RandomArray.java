package CS610;

import java.util.Random;

public class RandomArray {
	
	public static int[] RandomA( int n) {
		Random rn = new Random();
	
	    int [] result = new int [n];
	    int answer  =0;
		for (int i =0 ; i < n ; i++){
			answer = rn.nextInt(n)+1;
			result [i] = answer;
		}
		
		return result;
		
	}
	
	public static int[] SortA(int n) {
		
	
	    int [] result = new int [n];
	    
		for (int i =0 ; i < n ; i++){
			
			result [i] = i;
		}
		
		return result;
		
	}
	public static int[] SortB( int n) {
		
	
	    int [] result = new int [n];
	    
		for (int i =0 ; i < n ; i++){
			
			result [i] = n - i;
		}
		
		return result;
		
	}
	
}
