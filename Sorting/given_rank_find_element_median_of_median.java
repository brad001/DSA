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
		int pivot_index=
		int pivot=
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
		arr[pivot_index]=temp;
		
		
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
	int median_of_median(int[]arr,int low,int high)
	{
		
	if(high-low+1>=5)
	{
	  int n=high-low+1;
	  int num_groups=n/5;
	  int rem_elem=n%5;
	  
	  int start_index=0,end_index=4;
	  int index_median=0;
	  for(int i=0;i<num_groups;i++)
	  {
	  	
	  	Arrays.sort(arr,start_index,end_index+1);
	  	System.out.println("median is : "+arr[(start_index+end_index)/2]);
	  	arr[index_median++]=arr[(start_index+end_index)/2];
	  	start_index=end_index+1;
	  	end_index=end_index+5;
	  }
	   
	   //for the rest
	   
	   int low_index=num_groups*5;
	   Arrays.sort(arr,low_index,n);
	   
	   if(rem_elem%2==1)//odd
	   {
	   	System.out.println("median is : "+arr[low_index+(rem_elem/2)]);
	   arr[index_median++]=arr[low_index+(rem_elem/2)];
	   }
	   else//even
	   {
	   	System.out.println("median is : "+arr[low_index+(rem_elem/2-1)]);
	   arr[index_median++]=arr[low_index+(rem_elem/2-1)];
	   }
	   
	  return median_of_median(arr,0,index_median-1); 
	   
	}
	else
	{
		int size=high-low+1;
		if(size%2==0)//even
		  return arr[low+(size/2)-1];
		else //odd  
	    return arr[low+size/2];
	}
	
	}
	public static void main (String[] args) throws java.lang.Exception
	{
	
	   Scanner sc=new Scanner(System.in);
	   int n=sc.nextInt();
	   
	   int []arr=new int[n];
	   
	   for(int i=0;i<n;i++)
	     arr[i]=sc.nextInt();
	   
	   
	   System.out.println("given array");
	   for(int elem:arr)
	      System.out.print(elem+" ");
	    System.out.println();
	  
	  int[]temp_arr=new int[n];
	  for(int i=0;i<n;i++)
	  temp_arr[i]=arr[i];
	  Arrays.sort(temp_arr);
	  
	   System.out.println("sorted array");
	   for(int elem:temp_arr)
	      System.out.print(elem+" ");
	    System.out.println();
	  
	  //get the median
	  Ideone obj=new Ideone();
	  System.out.println(obj.median_of_median(arr,0,n-1));
       
	      	    
	      	    
	}
}
