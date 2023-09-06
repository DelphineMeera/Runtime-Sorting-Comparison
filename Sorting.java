package allSorting;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

//Insertion sort
class InsertionSort{
	public void insertionSort(int sortedArray[]) {
		int i, j;
		for (i = 1; i < sortedArray.length; i++) {
			int key = sortedArray[i];
			j = i - 1;
			while (j >= 0 && sortedArray[j] > key) {
				sortedArray[j + 1] = sortedArray[j];
				j = j - 1;
			}
			sortedArray[j + 1] = key;
		}
	}
}

//Heap Sort
class Heap {

	Vector<Integer> a;
	int size;
	int capacity;
	 
	public int parent(int i) {
		return (i-1)/2;
	}
	public int left(int i) {
		return (2*i+1);
	}
	public int right(int i) {
		return (2*i+2);
	}
	public int get_min() {
		return a.get(0);
	}
	Heap(int c){
		a=new Vector<Integer>();
		capacity=c;
		size=0;
	}
	public void insert(int t) {
		if(size==capacity) {
			System.out.println("It is full, cannot insert more!");
		}
		else {
			size=size+1;
			a.add(size-1, t);
			
			int curr=size-1;
			while(curr!=0 && a.get(parent(curr))<a.get(curr)) {
				int k=a.get(parent(curr));
				a.set(parent(curr),a.get(curr));
				a.set(curr,k);
				
				curr=parent(curr);
			}
			
		}
	}
	
	public void heapify(int in,int n) {
		if(size<=1) {
			return ;		}
		int l=left(in);
		int r=right(in);
		int largest = in; 
	    
	    
	    if (l < n && a.get(l) > a.get(largest)) 
	        largest = l; 
	    
	    
	    if (r < n && a.get(r) > a.get(largest)) 
	        largest = r; 

	    
	    if (largest != in) 
	    { 
	        int temp = a.get(in);
	        a.set(in,a.get(largest));
	       
	        
	        a.set(largest,temp);
	        heapify(largest,n); 
	    }

	    
	}
	public void print() {
		
		for(int i=0;i<size;i++)
		{
			System.out.println(a.get(i));
		}
	}
	public void heapsort() {
		int n=size;
		int i,k;
		for(i=(n/2)-1;i>=0;i--) {  
		heapify(i,n);
		}
		for(i=n-1;i>0;i--) {
		k=a.get(0);
		a.set(0,a.get(i));
		a.set(i,k);
		
		heapify(0,i);
		}
	}
	
}

//Merge Sort
class MergeSort {

	 void merge(int a[],int min,int mid,int max) {
		int b[]= new int[a.length];
		int i,j,k;
		for(i=min;i<=max;i++) {
			b[i]=a[i];
		}
		i=min;
		j=mid+1;
		k=min;
		while(i<=mid && j<=max) {
			if (b[i]<b[j]) {
				a[k]=b[i];
				i++;k++;
			}
			else {
				a[k]=b[j];
				j++;k++;
			}
		}
		while(i<=mid) {
			a[k]=b[i];
			i++;k++;
		}
		while(j<=max) {
			a[k]=b[j];
			k++;j++;
		}
	}
	 void msort(int a[],int min,int max) {
		int mid;
		if(max>min) {
			mid=(min+max)/2;
			msort(a,min,mid);
			msort(a,mid+1,max);
			merge(a,min,mid,max);
		}
	}
	
}


//Quick Sort
class QuickSort{
	public void quickSort(int[] inputArray, int l, int h) {
		if (l < h) {
			int pivot = getPivot(inputArray, l, h);
			quickSort(inputArray, l, pivot - 1);
			quickSort(inputArray, pivot + 1, h);
		}		
	}

	public int getPivot(int[] inputArray, int l, int h) {
		int pivot = inputArray[l];
		int i = l - 1;
		for (int j = l; j <= h - 1; j++) {
			if (inputArray[j] < pivot) {
				i++;
				swap(inputArray, i, j);
			}
		}
		swap(inputArray, i + 1, h);
		return i + 1;
	}

     public void swap(int[] inputArray, int i, int j) {
		int t = inputArray[i];
		inputArray[i] = inputArray[j];
		inputArray[j] = t;
	}
}




//Modified Quick Sort
class ModifiedQuickSort{
	public void modifiedQuickSort(int a[],int left,int right) {
		int pivot,mid;
		if(left + 8 <= right) {
			pivot=getMedianofThree(a,left,right);
			mid=getMid(a,left,pivot,right);
			modifiedQuickSort(a,left,mid-1);
			modifiedQuickSort(a,mid+1,right);
		}
		else {
			new InsertionSort().insertionSort(a);
		}
		
	}
	public int getMedianofThree(int a[],int left,int right) {
		int center,t;
		center=(left+right)/2;
		if(a[left]> a[center]) {
			t=a[left];
			a[left]=a[center];
			a[center]=t;
		}
		if(a[center]> a[right]) {
			t=a[right];
			a[right]=a[center];
			a[center]=t;
		}
		if(a[left] > a[right]) {
			t=a[left];
			a[left]=a[right];
			a[right]=t;
		}
		int median=a[right-1];
		return median;
	}
	public  int getMid(int a[],int left,int pivot, int right) {
		int i,j,t;
		i=left;
		j=right-1;
		for(;;) {
			while(a[++i] < pivot && i >=a.length-1);
			while(a[--j] > pivot && j>-1);
			if( i>=j)
				break;
			else {
				t=a[i];
				a[i]=a[j];
				a[j]=t;
			}
			
		}
		t=a[i];
		a[i]=a[right-1];
		a[right-1]=t;
		return i;
	}
}

public class Sorting {

	public static void reverseSort(int a[]) {
		int i,j;
		int n=a.length;
		for(i=0;i<n-1;i++) {
			for(j=i+1;j<n;j++) {
				if(a[i]<a[j]) {
					int t=a[i];
					a[i]=a[j];
					a[j]=t;
				}
			}
		}
	}
	public static void print(int a[]) {
		//System.out.println(size);
		for(int i=0;i<a.length;i++)
		{
			System.out.println(a[i]);
		}
	}
	
	public static void main(String[] args) {
		
		int e;
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the number of inputs:");
		e=s.nextInt();
		int i,j,key;
		int a[]= new int[e];
		Random r= new Random();
		for(i=0;i<e;i++)
			{
				
				a[i]=r.nextInt(e);
				
			}
		long t;
		
		//reverseSort(a);
		 //Arrays.sort(a);
		
		//Insertion Sort
				t = System.currentTimeMillis();
				new InsertionSort().insertionSort(a);
				System.out.println("Insertion Sort:"+ (System.currentTimeMillis()-t));
				//print(a);
	
		
		
		//Heap Sort
				Heap h=new Heap(e);
				for(i=0;i<e;i++)
						{
							
							h.insert(a[i]);
							
						}
				h.heapsort();
				t = System.currentTimeMillis();
				h.heapsort();
				System.out.println("Heap Sort:"+(System.currentTimeMillis()-t));
				//h.print();
		
		//Merge Sort
				t = System.currentTimeMillis();
				new MergeSort().msort(a,0,e-1);
				System.out.println("Merge Sort:"+(System.currentTimeMillis()-t));
				//print(a);
		

		//Quick Sort - in place
				t = System.currentTimeMillis();
				new QuickSort().quickSort(a,0,e-1);
				System.out.println("Quick Sort:"+(System.currentTimeMillis()-t));
				//print(a);
		
		
		
		//Modified Quick Sort
				t = System.currentTimeMillis();
				new ModifiedQuickSort().modifiedQuickSort(a,0,e-1);
				System.out.println("Modified Quick Sort:"+(System.currentTimeMillis()-t));
		//print(a);
		
		


	}

}
