package CS610;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Select {

	static int compcount = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException{

		int[] array11 = null;
		int[] array21 = null;
		int[] array31 = null;
		int k1 = 10000;
		int k2 = 100000;
		int k3 = 1000000;
		array11  = RandomArray.RandomA(k1);
		array21  = RandomArray.RandomA(k2);
		array31  = RandomArray.RandomA(k3);
		int k = 800;
		readFile(array11,k1);
		readFile(array21,k2);
		readFile(array31,k3);
		
		int[] array12 = array11;
		int[] array13 = array11;
		int[] array22 = array21;
		int[] array23 = array21;
		int[] array32 = array31;
		int[] array33 = array31;
		
//		printArray(array);
//		System.out.println("SELECT3: n="+ k1 +" The "+k1 / 2+"th smallest:" + Select3(array, array.length, 10) + " Comp:"+compcount);
//		compcount = 0;
//		printArray(array);
		
		System.out.println("SELECT1: n="+ k1 +" The "+k1 / 2+"th smallest:" + Select1(array11, array11.length, k1 /2) + " Comp:"+compcount);
		compcount = 0;
		System.out.println("SELECT2: n="+ k1 +" The "+k1 / 2+"th smallest:" + Select2(array12, array12.length , k1 /2) + " Comp:"+compcount);
		compcount = 0;
		System.out.println("SELECT3: n="+ k1 +" The "+k1 / 2+"th smallest:" + Select3(array13, array13.length, k1 /2) + " Comp:"+compcount);
		compcount = 0;
		System.out.println("SELECT1: n="+ k2 +" The "+k2 / 2+"th smallest:" + Select1(array21, array21.length, k2 /2) + " Comp:"+compcount);
		compcount = 0;
		System.out.println("SELECT2: n="+ k2 +" The "+k2 / 2+"th smallest:" + Select2(array22, array22.length , k2 /2) + " Comp:"+compcount);
		compcount = 0;
		System.out.println("SELECT3: n="+ k2 +" The "+k2 / 2+"th smallest:" + Select3(array23, array23.length, k2 /2) + " Comp:"+compcount);
		compcount = 0;
		System.out.println("SELECT1: n="+ k3 +" The "+k3 / 2+"th smallest:" + Select1(array31, array31.length, k3 /2) + " Comp:"+compcount);
		compcount = 0;
		System.out.println("SELECT2: n="+ k3 +" The "+k3 / 2+"th smallest:" + Select2(array32, array32.length , k3 /2) + " Comp:"+compcount);
		compcount = 0;
		System.out.println("SELECT3: n="+ k3 +" The "+k3 / 2+"th smallest:" + Select3(array33, array33.length, k3 /2) + " Comp:"+compcount);
		compcount = 0;
		System.out.println("Comp:"+compcount);
		compcount = 0;
	}
	
	private static void readFile(int[] array,  int n) throws NumberFormatException, IOException{

		String filename = "random"+n;
		String fileinput = filename +".txt";
		
		
		FileReader openfile = new FileReader(fileinput);
//		System.out.println(fileinput);
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
	}
	
	private static void generateFile(int n) throws IOException{
		int array[] = null;
		String filename = "random"+n;
		String fileinput = null;
		
		array  = RandomArray.RandomA(n);
		fileinput = filename +".txt";
		BufferedWriter output = null;
		System.out.println("Generate file :"+fileinput);
        output = new BufferedWriter(new FileWriter(fileinput));
        for(int i=0; i < array.length ; i++ ){
        	output.write(array[i] + " ");
        }
        output.flush();
        output.close();
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
	
	private static boolean compare(int x, int y){
		compcount++;
		if ( x < y)
			return true;
		else
			return false;
	}
	
	private static boolean compare1(int x, int y){
		compcount++;
		if ( x <= y)
			return true;
		else
			return false;
	}
	
	public static void swap(int[] array, int index1, int index2) {  
        int temp = array[index1];  
        array[index1] = array[index2];  
        array[index2] = temp;  
    }
	
	private static int Select1(int[] array, int n, int k){
		quicksort(array, 0, array.length - 1);
		return array[k - 1];
	}
	
	private static void quicksort(int[] array, int start, int end){
		if(start >= end)
			return;
		
		int pivot = array[start];
		int left = start + 1;
		int right = end;
		while(left < right){
			while(compare1( pivot , array[right] ) && left < right)
				right = right - 1;
			while(compare( array[left] , pivot ) && left < right)
				left = left + 1;
			swap(array ,left ,right);
		}
		if(array[left] <= pivot)
			swap(array, start, left);
		else
			left = left - 1;
		quicksort(array, start, left - 1);
		quicksort(array, left + 1 , end);
		
	}
	public static int Select2(int[] array,int n, int k) {
		return recursive(array, 0, array.length - 1, k -1);
	}
	
	private static int recursive(int[] array, int left, int right, int k) {
		if (left == right) { // If the list contains only one element,
			return array[left]; // return that element
		}
		if(right - left < 25)
			return InsertionSort(array, k);
		
		// select a pivotIndex between left and right
		int pivotIndex = randomPivot(left, right); 
		pivotIndex = partition(array, left, right, pivotIndex);
		// The pivot is in its final sorted position
		if (k == pivotIndex) {
			return array[k];
		} else if (k < pivotIndex) {
			return recursive(array, left, pivotIndex - 1, k);
		} else {
			return recursive(array, pivotIndex + 1, right, k);
		}
	}
	
	private static int partition(int[] array, int left, int right, int pivotIndex) {
		int pivotValue = array[pivotIndex];
		swap(array, pivotIndex, right); // move pivot to end
		int storeIndex = left;
		for(int i = left; i < right; i++) {
			if(compare(array[i] , pivotValue)) {
				swap(array, storeIndex, i);
				storeIndex++;
			}
		}
		swap(array, right, storeIndex); // Move pivot to its final place
		return storeIndex;
	}
	
	private static int InsertionSort(int[] array, int k){
		for (int i = 1 ; i < array.length ; i++){
			int tmp = array[i];
			for(int j = i -1 ; j >= 0 && compare(tmp , array[j]) ; j--){
				tmp = array[j + 1];
				swap(array, j , j + 1);
			}
		}
		return array[k];
	}
	private static int randomPivot(int left, int right) {
		return left + (int) Math.floor(Math.random() * (right - left + 1));
	}	
	
	
	public static int Select3(int[] array,int n, int k) {
		return recursive3(array, 0, array.length - 1, k -1);
	}
	
	private static int recursive3(int[] array, int left, int right, int k) {
	

		int a = left;
		if (left == right) { // If the list contains only one element,
			return array[left]; // return that element
		}
		if(right - left < 25)
			return InsertionSort(array, k);
		
		for(int i = left ; i < right + 1; i = i + 5){
			InsertionMid(array,i);
			swap(array, a, i + 2);
			a++;
		}
		
		for (int i = left ; i < a ; i++){
			int tmp = array[i];
			for(int j = i -1 ; j >= 0 && tmp < array[j] ; j--){
				tmp = array[j + 1];
				swap(array, j , j + 1);
			}
		}
		int pivotIndex =  a / 2 ;
//		System.out.println("left="+left + "right="+ right +"pivotIndex"+pivotIndex);
		pivotIndex = partition3(array, left, right, pivotIndex);
//		System.out.println("left="+left + "right="+ right +"pivotIndex"+pivotIndex);
		// The pivot is in its final sorted position
		if (k == pivotIndex) {
			return array[k];
		} else if (k < pivotIndex) {
			return recursive3(array, left, pivotIndex - 1, k);
		} else {
			return recursive3(array, pivotIndex + 1, right, k);
		}
		
	}
	
	private static void InsertionMid(int[] array, int n){
		
		for ( int i = n + 1 ; i < n + 5 ; i++){
			int tmp = array[i];
			for(int j = i -1 ; j >= 0 && tmp < array[j] ; j--){
				tmp = array[j + 1];
				swap(array, j , j + 1);
			}
		}
	}
	
	private static int partition3(int[] array, int left, int right, int pivotIndex) {
		int pivotValue = array[pivotIndex];
		swap(array, pivotIndex, right); // move pivot to end
		int storeIndex = left;
		for(int i = left; i < right; i++) {
			if(compare(array[i] , pivotValue)) {
				swap(array, storeIndex, i);
				storeIndex++;
			}
		}
		swap(array, right, storeIndex); // Move pivot to its final place
		return storeIndex;
	}
	
}
