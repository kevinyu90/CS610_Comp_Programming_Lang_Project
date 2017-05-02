package CS610;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class myRadixSort {

	public static void main(String[] args) throws IOException{
		int k = 22;
		int n = 1000;
		//char[][] sortarray = RandomChar(n,k);
//		char[][] sortarray;
//		sortarray = new char [10][k];
//		char[][] array1 = RandomChar(1000,22);
//		GenerateFile(array1);
		
		System.out.println("Please specify the input file (default = f.txt):");
		Scanner sc = new Scanner(System.in);
		String pathname = "f.txt";
		
		pathname = sc.nextLine();
		if(pathname.equalsIgnoreCase("")){
			pathname = "f.txt";
		}
		System.out.println("Open File: "+pathname);
//		System.out.println("pathname="+pathname);
		/* Read File*/
		BufferedReader in = new BufferedReader(new FileReader(pathname));
		String line;
		char[][] tmparray;
		tmparray = new char [1000][k];
		int row = 0;
		while((line = in.readLine()) != null){
			for(int i = 0 ; i < k ; i++){
			tmparray[row][i] = line.charAt(i);
			}
			row++;
		}
		//System.out.println(row);
		n = row;
		char[][] sortarray;
		sortarray = new char [n][k];
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < k ; j++){
				sortarray[i][j] = tmparray[i][j];
			}
		}
		
//		for(int i = 0 ; i < n ; i++){
//			for(int j = 0 ; j < k ; j++){
//				System.out.print(sortarray[i][j]);
//			}
//			System.out.print("\n");
//		}
//		GenerateFile(sortarray);
		RadixSort(sortarray);
//		System.out.println("After sort:");
//		for(int i = 0 ; i < n ; i++){
//			for(int j = 0 ; j < k ; j++){
//				System.out.print(sortarray[i][j]);
//			}
//			System.out.print("\n");
//		}
		
		System.out.println("Please specify the output file (default = g.txt):");
//		Scanner sc = new Scanner(System.in);
		String outfile = "g.txt";
		
		outfile = sc.nextLine();
		sc.close();
		if(outfile.equalsIgnoreCase("")){
			outfile = "g.txt";
		}
		System.out.println("Generate File: "+outfile);
		/* Write File*/
		File file = new File(outfile);
		FileWriter out = new FileWriter(file);
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < k ; j++){
				out.write(sortarray[i][j]);
			}
			out.write("\n");
		}
		out.flush();
        out.close();
	}
	
	private static char[][] RadixSort(char[][] array){
		int[] Parray;
		/* PointArray from 0 to array.length - 1 */
		Parray = new int [array.length]; 
		for(int i = 0 ; i < array.length ; i++){
			Parray[i] = i;
		}
		
		for(int i = 21 ; i >= 0 ; i--){
			Parray = LSD(array,Parray,i);
		}

		char tmp[][] = new char[array.length][1];
		for(int i = 0 ; i < array.length ; i++){
			tmp[i] = array[Parray[i]];
		}
		for(int i = 0 ; i < array.length ; i++){
			array[i] = tmp[i];
		}
		
		return array;
	}
	
	private static int[] LSD(char[][] array, int[] Parray, int k){
		int[] count = new int [27 + 1];
		int[] tmparray;
		tmparray = new int [array.length]; 
		int n = array.length;
		
		/* count frequencies*/
		for(int i = 0 ; i < n ; i++ ){
//			System.out.println("array[Parray[i]][k]"+i+"="+(int)array[Parray[i]][k]+ " "+array[Parray[i]][k]);
			if((int)array[Parray[i]][k] < 60 ){
				count[array[Parray[i]][k]  - 31]++;
			}
			else{	
				count[array[Parray[i]][k] + 1 - 64]++;
			}

		}
		/* compute cumulates*/
		for(int r = 0 ; r < 27 ; r++){
			count[ r + 1 ] += count[r];
		}

		

		for(int i = 0 ; i < n ; i++){
			if((int)array[Parray[i]][k] < 60 ){
				tmparray[count[array[Parray[i]][k]  - 32]++] = Parray[i];
			}
			else{	
				tmparray[count[array[Parray[i]][k] - 64]++] = Parray[i];
			}
		}

		/* copy item*/
		for(int i = 0 ; i < n ; i++){
			Parray[i] = tmparray[i];
		}
		
		return Parray;
	}
	
	/*產生隨即n＊k二維數組*/
	private static char[][] RandomChar(int n, int k){
		char[][] array = new char[n][k];
		
		for(int i = 0 ; i < n ; i++){
			int random = new Random().nextInt(k - 1) + 1; //1~21
			for(int j = 0 ; j < random ; j++){
				array[i][j] = (char) ('A' + new Random().nextInt(26));
			}
			for (int j = random ; j < k ; j++){
				array[i][j] = (char)(' ');
			}
		}
		return array;
	}
	/*把二維數組存到txt中*/
	private static void GenerateFile(char[][] array) throws IOException{
		String filename = array.length + "f.txt";
		System.out.println(array.length);
		System.out.println(array[0].length);
		File writename 	= new File(filename);
		writename.createNewFile();
		BufferedWriter output = new BufferedWriter(new FileWriter(writename));
		System.out.println("Generate file :"+filename);
		for(int i = 0 ; i < array.length ; i++){
			for(int j = 0 ; j < array[0].length ; j++){
				output.write(array[i][j]);
			}
			output.write("\n");
		}
		output.flush();
        output.close();
	}
	
}
