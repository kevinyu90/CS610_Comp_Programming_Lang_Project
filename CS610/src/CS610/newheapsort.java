package CS610;

public class newheapsort {
	static int compcount = 0;
	public static void main(String[] args) {  
        int[] array = {26, 5, 98, 108, 28, 99, 100, 56, 34, 1 };  

        System.out.println("Before heap:");  
        printArray(array);  

        heapSort(array);  

        System.out.println("After heap sort:");  
        printArray(array);  
        System.out.println("Comp"+compcount);
    }  
	
	public static void swap(int[] array, int index1, int index2) {  
        int temp = array[index1];  
        array[index1] = array[index2];  
        array[index2] = temp;  
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
	
	public static void heapSort(int [] array){
		if (array == null || array.length <= 1) {  
            return;  
        }  
		
		buildheap(array);
		System.out.println("After build:");  
        printArray(array);
        
        
	}
	private static void buildheap(int [] array){
		if (array == null || array.length <= 1) {  
            return;  
        }
		int half = array.length / 2;  
        for (int i = half; i >= 0; i--) {  
            pushdown(array, array.length, i);  
        }
	}
	
	private static void pushdown(int [] array, int heapsize, int index){
		int left = index * 2 + 1;
		int right = index * 2 + 2;
		int min = index;
		
		if( left < heapsize){
			if( array[index] > array[left]){
				min = left;
			}
		}
		if ( right < heapsize){
			if( array[min] > array[right]){
				min = right;
			}
		}
		if( index != min){
			swap(array, index, min);
			pushdown(array, heapsize,min);
		}
		
	}
	
}
