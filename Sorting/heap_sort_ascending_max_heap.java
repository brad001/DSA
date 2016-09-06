/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	void max_heapify(int []arr,int i,int n)
	{
		//System.out.println("for i= "+i);
		while(2*i+1 <n)
		{
			int left=2*i+1;
			int right=2*i+2;
			int max;
			
					//System.out.println("left= "+left+" right= "+right);
			//find max of both the child if exists
			if(right<n && arr[right]>arr[left])
			     max=right;
			else //default if no right exists only left is max
			    max=left;
			    
			if(arr[i]<arr[max]) 
			   {
			   	int temp=arr[i];
			   	arr[i]=arr[max];
			   	arr[max]=temp;
			   	
			   	i=max;
			   }
			 else
			    break;
			
			
		}
		
	}
	void build_max_heap(int []arr,int n)
	{
		
		for(int i=n/2-1;i>=0;i--)
		   max_heapify(arr,i,n);
		
	}
	void heap_sort(int []arr,int n)
	{
		build_max_heap(arr,n);
		
		System.out.println("after building heap");
		for(int elem:arr)
		System.out.print(elem+" ");
		
		   System.out.println();
		   int temp_n=n-1;
		for(int i=n-1;i>=0;i--)
		{
			//swap each time root to ith element and hence we get  max at root and last index will have that element
			int temp=arr[0];
			arr[0]=arr[i];
			arr[i]=temp;
		//	System.out.println("call for replace a[i]= "+arr[i]);
			max_heapify(arr,0,temp_n);
			temp_n--;
			/*System.out.println("after swap of each");
			for(int elem:arr)
			System.out.print(elem+" ");
			*/
		}
	}
	public static void main (String[] args) throws java.lang.Exception
	{
	  Scanner sc=new Scanner(System.in);
	  int n=sc.nextInt();
	  int[]arr=new int[n];
	  
	  for(int i=0;i<n;i++)
	     arr[i]=sc.nextInt();
	   
	   System.out.println("before sorting");
	   for(int elem:arr)
	      System.out.print(elem+" ");
	      
	      System.out.println();
	      Ideone ob=new Ideone();
	      
	  ob.heap_sort(arr,n);   
	  
	   System.out.println("after sorting");
	   for(int elem:arr)
	      System.out.print(elem+" ");
	      
	      System.out.println();
	}
}
