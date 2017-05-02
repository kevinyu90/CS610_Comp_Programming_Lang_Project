package CS610;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class myHeapsort {  
	static int compcount = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		myHeapsort mysort = new myHeapsort();
		mysort.run(32, 0);
		
		mysort.run(32,1);
		
		mysort.run(32, 2);
		mysort.run(1024, 1);
		mysort.run(32768, 1);
		mysort.run(1048576, 1);
		
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

    public static void swap(int[] array, int index1, int index2) {  
        int temp = array[index1];  
        array[index1] = array[index2];  
        array[index2] = temp;  
    }  
    
    private static boolean compare(int x, int y){
		compcount++;
		if ( x > y)
			return true;
		else
			return false;
	}
	
    public void run(int n , int a) throws NumberFormatException, IOException {    

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
		/*output = new BufferedWriter(new FileWriter(fileinput));
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
        
          
        long StartTime = System.currentTimeMillis();
        heapSort(array);  
        long EndTime = System.currentTimeMillis();
        long ExecutionTime = (EndTime - StartTime) ;
         
        System.out.println(filename + " Comp "+compcount);
        compcount = 0;
        System.out.println("UseTime"+ExecutionTime);
        
        output = null;
        
        
        fileoutput = "Heap" + filename + "result.txt";
        output = new BufferedWriter(new FileWriter(fileoutput));
        for(int i=0; i < array.length ; i++ ){
        	output.write(array[i] + " ");
        }
       
         
        output.flush();
        output.close();
    }  

    public static void heapSort(int[] array) {  
        if (array == null || array.length <= 1) {  
            return;  
        }  

        buildMaxHeap(array);  
        for (int i = array.length - 1; i >= 1; i--) {  
            swap(array, 0, i);  
            pushdown(array, i, 0);  
        }  
    }  

    private static void buildMaxHeap(int[] array) {  
        if (array == null || array.length <= 1) {  
            return;  
        }  
        int half = array.length / 2;  
        for (int i = half; i >= 0; i--) {  
            pushdown(array, array.length, i);  
        }  
    }  

    private static void pushdown(int[] array, int heapSize, int index) {  
        int left = index * 2 + 1;  
        int right = index * 2 + 2;  

        int largest = index;  
        if (left < heapSize && compare(array[left] , array[index])) {  
            largest = left;  
        }  

        if (right < heapSize && compare(array[right] , array[largest])) {  
            largest = right;  
        }  

        if (index != largest) {  
            swap(array, index, largest);  

            pushdown(array, heapSize, largest);  
        }  
    }  
}  
