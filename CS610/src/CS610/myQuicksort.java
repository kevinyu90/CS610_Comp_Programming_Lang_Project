package CS610;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class myQuicksort {
	static int compcount = 0;
	private static boolean isEmpty(int[] n) {  
        return n == null || n.length == 0;  
    }  
	
	private static boolean compare(int x, int y){
		compcount++;
		if ( x < y)
			return true;
		else
			return false;
	}
	
	public static void printArray(int[] array) {  
        System.out.print("{");  
        for (int i = 0; i < array.length; i++) {  
            System.out.print(array[i]);  
            if (i < array.length - 1) {  
                System.out.print(", ");  
            }  
        }  
        System.out.println("}");  
    } 
  
	public static void main(String[] args) throws NumberFormatException, IOException {
		myQuicksort mysort = new myQuicksort();
		mysort.run(32, 0);
		mysort.run(32,1);
		mysort.run(32, 2);
		mysort.run(1024, 1);
		mysort.run(32768, 1);
		mysort.run(1048576, 1);
		
	}
	
	public  void run(int n , int a) throws NumberFormatException, IOException {  
       
		int array [] = null;
		String filename = "sort"+n+"_";
		String fileinput = null;
		String fileoutput = null;
		if(a == 0 ){
			array  = RandomArray.SortA(n);
			filename = filename + "sort";
		}
		if(a == 1 ){
			array  = RandomArray.RandomA(n);
			filename = filename+"random";
		}
		if(a == 2 ){
			array  = RandomArray.SortB(n);
			filename = filename+"res-sort";
		}
		fileinput = filename +".txt";
		
		BufferedWriter output = null;
	       
		//System.out.println(myQuicksort.class.getClass().getResource(""));
        //String testPath = myQuicksort.class.getResource("").getPath();
        
        
       
      
        //System.out.println(testPath);
        /*System.out.println("Generate file :"+fileinput);
        output = new BufferedWriter(new FileWriter(fileinput));
        for(int i=0; i < array.length ; i++ ){
        	output.write(array[i] + " ");
        }
        output.flush();
        output.close();*/
       
        
        
        
		FileReader openfile = new FileReader(fileinput);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(openfile);
		int num = 0;
		String line = null;
		while((line = br.readLine()) != null){
			String[] vStrs = line.split(" ");
			for(String str : vStrs){
				array[num++] =Integer.parseInt(str);
			}
		}
        
        //System.out.println("Before QuickSort:");  
       // printArray(array);  
         long StartTime = System.currentTimeMillis();
        quickSort(array);  
         long EndTime = System.currentTimeMillis();
         long ExecutionTime = (EndTime - StartTime) ;
        //System.out.println("After QuickSort:");  
       // printArray(array);  
        System.out.println(fileinput+"Comp:"+compcount);
        compcount = 0;
        //System.out.println("StartTime:"+StartTime);
        //System.out.println("EndTime:"+EndTime);
        System.out.println("ExecutionTime:"+ExecutionTime);
        
        output = null;
        StartTime = System.currentTimeMillis(); 
        ExecutionTime = (EndTime - StartTime) ;
        fileoutput = "Qucik" + filename + "result.txt";
        output = new BufferedWriter(new FileWriter(fileoutput));
        for(int i=0; i < array.length ; i++ ){
        	output.write(array[i] + " ");
        }
        EndTime = System.currentTimeMillis();
        ExecutionTime = (EndTime - StartTime) ;
        //System.out.println("output file :"+ExecutionTime);   
        output.flush();
        output.close();
    }  
	

    public static void quickSort(int[] n) {  
        if (isEmpty(n))  
            return;  
        quickSort(n, 0, n.length - 1);  
    }  
  
    public static void quickSort(int[] n, int l, int h) {  
        if (isEmpty(n))  
            return;  
        if (l < h) {  
            int pivot = partion(n, l, h);  
            quickSort(n, l, pivot - 1);  
            quickSort(n, pivot + 1, h);  
        }  
    }  
  
    private static int partion(int[] n, int start, int end) {  
        int tmp = n[start];  
        while (start < end) {  
            while (compare(tmp , n[end])  && start < end)  
                end--;  
            if (start < end) {  
                n[start++] = n[end];  
            }  
            while (compare(n[start] , tmp) && start < end)  
                start++;  
            if (start < end) {  
                n[end--] = n[start];  
            }  
        }  
        n[start] = tmp;  
        return start;  
    }  
}
