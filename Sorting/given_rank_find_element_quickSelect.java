/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	int get_pivot_index(int low,int high)
	{
		int index=(int)(Math.random()*(high-low+1)+low);
	     System.out.println("random index chose is: "+index);
		return index;
	}
	int partition(int[]arr,int low,int high)
	{
		//first element chosen as pivot
	    int index=get_pivot_index(low,high);
//		int pivot=arr[index];
		
		//swap pivot with low
		int t1=arr[index];
		arr[index]=arr[low];
		arr[low]=t1;
		
		int pivot=arr[low];
		System.out.println("pivot chosen is: "+pivot);
		
		int i=low+1;
		int j=high;
		
		while(i<=j)
		{
			while(i <=high && arr[i]<=pivot)
			   i++;
			   
			while(j>=0 && arr[j]>pivot)
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
	int q_sort( int[]arr,int low,int high,int rank)
	{
		System.out.println("low= "+low+ " high="+high+" desired rank= "+rank);
		if(low<high)
		{
			int p_index=partition(arr,low,high);
			System.out.println("final place of pivot "+p_index);
			for(int elem:arr)
			  System.out.print(elem+" ");
			  
			  System.out.println("current rank is: "+(high-p_index+1));
			if(high-p_index+1==rank)
			   return arr[p_index];
			else if(high-p_index+1 <rank)
			{
				System.out.println("move left");
			   return q_sort(arr,low,p_index-1,rank-(high-p_index+1));
			}
			else
			{
				System.out.println("move right ");
			
			   return q_sort(arr,p_index+1,high,rank);
			}
			
			
		}
		else
		 return arr[low];
		
	}
	public static void main (String[] args) throws java.lang.Exception
	{
	
	   Scanner sc=new Scanner(System.in);
	   int n=sc.nextInt();
	   int rank=sc.nextInt();
	   int []arr=new int[n];
	   
	   for(int i=0;i<n;i++)
	     arr[i]=sc.nextInt();
	   Ideone obj=new Ideone();
	   
	   System.out.println("before sorting");
	   for(int elem:arr)
	      System.out.print(elem+" ");
	      System.out.println();
	   System.out.println("element with desired rank is  = "+obj.q_sort(arr,0,n-1,rank));
	   
	   
	}
}
