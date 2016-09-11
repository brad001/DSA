/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	int partition(int[]arr,int low,int high)
	{
		//first element chosen as pivot
	
		int pivot=arr[low];
		int i=low+1;
		int j=high;
		
		while(i<=j)
		{
			while(i <=high && arr[i]<=pivot)
			   i++;
			   
			while(j>=low && arr[j]>pivot)
			   j--;
		  if(i<j)	   
		  {
		  	int temp=arr[i];
		  	arr[i]=arr[j];
		  	arr[j]=temp;
		  	
		  	i++;
		  	j--;
		  }
		 else
		  break;
		  
		}
		int temp=arr[j];
		arr[j]=pivot;
		arr[low]=temp;
		
		return j;
	}
	void q_sort( int[]arr,int low,int high)
	{
		if(low<high)
		{
			int p_index=partition(arr,low,high);
			System.out.println("after partition");
			for(int elem:arr)
			  System.out.print(elem+" ");
			q_sort(arr,low,p_index-1);
			q_sort(arr,p_index+1,high);
		}
		
	}
	public static void main (String[] args) throws java.lang.Exception
	{
	
	   Scanner sc=new Scanner(System.in);
	   int n=sc.nextInt();
	   int []arr=new int[n];
	   
	   for(int i=0;i<n;i++)
	     arr[i]=sc.nextInt();
	   Ideone obj=new Ideone();
	   
	   System.out.println("before sorting");
	   for(int elem:arr)
	      System.out.print(elem+" ");
	   obj.q_sort(arr,0,n-1);
	   
	   System.out.println("after sorting");
	   for(int elem:arr)
	      System.out.print(elem+" ");
	}
}
