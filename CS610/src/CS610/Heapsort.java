package CS610;

public class Heapsort {  
	static int compcount = 0;
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
		if ( x < y)
			return true;
		else
			return false;
	}
	
    public static void main(String[] args) {  
        int[] array = {26, 5, 98, 108, 28, 99, 100, 56, 34, 1 };  

        System.out.println("Before heap:");  
        printArray(array);  

        heapSort(array);  

        System.out.println("After heap sort:");  
        printArray(array);  
        System.out.println("Comp"+compcount);
    }  

    public static void heapSort(int[] array) {  
        if (array == null || array.length <= 1) {  
            return;  
        }  

        buildMaxHeap(array);  
        System.out.println("After heap sort:");  
        printArray(array);  
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

        int smallest = index;  
        if (left < heapSize && compare(array[left] , array[index])) {  
            smallest = left;  
        }  

        if (right < heapSize && compare(array[right] , array[smallest])) {  
            smallest = right;  
        }  

        if (index != smallest) {  
            swap(array, index, smallest);  

            pushdown(array, heapSize, smallest);  
        }  
    }  
}  