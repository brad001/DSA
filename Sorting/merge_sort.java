/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	void merge(int[]arr,int low,int mid,int high)
	{
		
	  int i=low;
	  int j=mid+1;
	  int size=high-low+1;
	  int[]b=new int[size];
	  int index_b=0;
	  
	  while(i<=mid && j<=high)
	  {
	  	if(arr[i]<=arr[j])
	  	{
	  		b[index_b++]=arr[i];
	  		i++;
	  	}
	  	else
	  	{
	  		b[index_b++]=arr[j];
	  		j++;
	  	}
	  }
	  
	  //copy back all elements
	  while(i<=mid)
	  {
	    b[index_b++]=arr[i];
	    i++;
	  }
	  while(j<=high)
	  {
	    b[index_b++]=arr[j];
	    j++;
	  }
      
	  int p=low;
	  for(int k=0;k<index_b;k++)
	  {
	  	arr[low++]=b[k];
	  }

	    
	}
	void merge_sort(int[]arr,int low,int high)
	{
		if(low<high)
		{
			int mid=(high+low)/2;
			merge_sort(arr,low,mid);
			merge_sort(arr,mid+1,high);
			merge(arr,low,mid,high);
		}
	}
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[]arr=new int[n];
		
		for(int i=0;i<n;i++)
		   arr[i]=sc.nextInt();
		   
	  System.out.println("given array");
	  for(int elem:arr)
	    System.out.print(elem +" ");
	  System.out.println();  
	  
	   Ideone obj=new Ideone();	   
		obj.merge_sort(arr,0,n-1);   
		
	System.out.println("sorted array");
	  for(int elem:arr)
	    System.out.print(elem +" ");
	  System.out.println();  
	}
}
