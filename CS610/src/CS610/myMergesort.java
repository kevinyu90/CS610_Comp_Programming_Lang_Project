package CS610;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class myMergesort {
	static int compcount = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		myMergesort mysort = new myMergesort();
		mysort.run(32, 0);
		
		mysort.run(32,1);
		
		mysort.run(32, 2);
		mysort.run(1024, 1);
		mysort.run(32768, 1);
		mysort.run(1048576, 1);
		
	}
	
	
	public void run(int n , int a) throws IOException{

		
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
        MergeSort(array, 0, array.length  -1);
        long EndTime = System.currentTimeMillis();
        long ExecutionTime = (EndTime - StartTime) ;
        System.out.println(filename + "Comp"+compcount);
        compcount = 0;       
        System.out.println("UseTime"+ExecutionTime);
        
        output = null;
       
        fileoutput = "Merge" + filename + "result.txt";
        output = new BufferedWriter(new FileWriter(fileoutput));
        for(int i=0; i < array.length ; i++ ){
        	output.write(array[i] + " ");
        }
        
        output.flush();
        output.close();
        
	}
	
	/*private static void printArray(String pre,int[] a) {
        System.out.print(pre+"\n");
        for(int i=0;i<a.length;i++)
            System.out.print(a[i]+"\t");    
        System.out.println();
    }*/
	
	private static boolean compare(int x, int y){
		compcount++;
		if ( x <= y)
			return true;
		else
			return false;
	}
	
	static void MergeSort(int [] a, int left, int right){
		if(left >= right)
			return;
		int mid = ( left + right ) / 2;
		MergeSort( a, left, mid) ;
		MergeSort( a, mid + 1, right );
		Merge( a, left, mid, right);
	}
	
	private static void Merge(int [] a, int left, int mid, int right){
		int [] tmp = new int[a.length];
		int r1 = mid +1;
		int tIndex = left;
		int cIndex = left;
		while(left <= mid && r1 <= right){
			/*if( a[left] <= a[r1] ){
				tmp[tIndex] = a[left];
				tIndex++;
				left++;
			}
			else{
				tmp[tIndex] = a[r1];
				tIndex++;
				r1++;
			}*/
			
			if( compare( a[left], a[r1]) ){
				tmp[tIndex] = a[left];
				tIndex++;
				left++;
			}
			else{
				tmp[tIndex] = a[r1];
				tIndex++;
				r1++;
			}
			
		}
			while( left <= mid ){
				tmp[tIndex] = a[left];
				tIndex++;
				left++;
			}
			while( r1 <= right ){
				tmp[tIndex] = a[r1];
				tIndex++;
				r1++;
			}
			while( cIndex <= right){
				a[cIndex] = tmp[cIndex];
				//System.out.print(a[cIndex]+"\t");
				cIndex++;
			}
			
		}
	
	
}
