/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	int median_of_median(int[]arr,int low,int high)
	{
		
	if(high-low+1>=5)
	{
	  int n=high-low+1;
	  int num_groups=n/5;
	  int rem_elem=n%5;
	  
	  int start_index=low,end_index=low+4;
	  int index_median=low;
	  for(int i=0;i<num_groups;i++)
	  {
	  	
	  	Arrays.sort(arr,start_index,end_index+1);
	  	System.out.println("median is : "+arr[(start_index+end_index)/2]);
	  	int t1=arr[(start_index+end_index)/2];
	  	arr[(start_index+end_index)/2]=arr[index_median];
	  	arr[index_median]=t1;
	  	
	  	index_median++;
	  	//arr[index_median++]=arr[(start_index+end_index)/2];
	  	start_index=end_index+1;
	  	end_index=end_index+5;
	  }
	   
	   //for the rest
	   
	   int low_index=num_groups*5+low;
	   Arrays.sort(arr,low_index,high+1);
	   
	   if(rem_elem>0)
	   {
	    if(rem_elem%2==1)//odd
	    {
	   	System.out.println("median is : "+arr[low_index+(rem_elem/2)]);
	   	int t1=arr[low_index+(rem_elem/2)];
	   	arr[low_index+(rem_elem/2)]=arr[index_median];
	   	arr[index_median]=t1;
	   //arr[index_median++]=arr[low_index+(rem_elem/2)];
	    }
	    else//even
	    {
	   	System.out.println("median is : "+arr[low_index+(rem_elem/2-1)]);
	   	int t1=arr[low_index+(rem_elem/2-1)];
	   	arr[low_index+(rem_elem/2-1)]=arr[index_median];
	   	arr[index_median]=t1;
	   //arr[index_median++]=arr[low_index+(rem_elem/2-1)];
	    }
	  }
	   
	  return median_of_median(arr,low,index_median-1); 
	   
	}
	else
	{
		int size=high-low+1;
		if(size%2==0)//even
		  //return arr[low+(size/2)-1];
		  //return index
		  return low+(size/2)-1;
		else //odd  
	    //return arr[low+size/2];
	    return low+size/2;
	}
	
	}
	int partition(int[]arr,int low,int high)
	{
		//first element chosen as pivot
	    //int ret=median_of_median(arr,low,high);
	   // int t1=arr[low];
	  //  arr[low]=ret;
	   // arr[]
	    int p_index=median_of_median(arr,low,high);
	    System.out.println("index is "+p_index+"pivot is "+arr[p_index]);
	    
	    System.out.println("before partiotion");
	    			for(int elem:arr)
			  System.out.print(elem+" ");
			  System.out.println();
			  
	    
		int pivot=arr[p_index];
		int t1=pivot;
		arr[p_index]=arr[low];
		arr[low]=t1;
		
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
		
		
		//System.out.println("after partiotion: ");
		
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
			  System.out.println();
			  
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
