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
	  	//System.out.println("median is : "+arr[(start_index+end_index)/2]);
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
	   	//System.out.println("median is : "+arr[low_index+(rem_elem/2)]);
	   	int t1=arr[low_index+(rem_elem/2)];
	   	arr[low_index+(rem_elem/2)]=arr[index_median];
	   	arr[index_median]=t1;
	   //arr[index_median++]=arr[low_index+(rem_elem/2)];
	    }
	    else//even
	    {
	   	//System.out.println("median is : "+arr[low_index+(rem_elem/2-1)]);
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
	    //System.out.println("index is "+p_index+"pivot is "+arr[p_index]);
	    
	   /* System.out.println("before partiotion");
	    			for(int elem:arr)
			  System.out.print(elem+" ");
			  System.out.println();
		*/	  
	    
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
	int find_element_given_rank( int[]arr,int low,int high,int rank)
	{
		//System.out.println("low= "+low+ " high="+high+" desired rank= "+rank);
		if(low<high)
		{
			int p_index=partition(arr,low,high);
			//System.out.println("final place of pivot "+p_index);
			//for(int elem:arr)
			 // System.out.print(elem+" ");
			  
			  //System.out.println("current rank is: "+(high-p_index+1));
			if(high-p_index+1==rank)
			   return arr[p_index];
			else if(high-p_index+1 <rank)
			{
				//System.out.println("move left");
			   return find_element_given_rank(arr,low,p_index-1,rank-(high-p_index+1));
			}
			else
			{
			//	System.out.println("move right ");
			
			   return find_element_given_rank(arr,p_index+1,high,rank);
			}
			
			
		}
		else
		 return arr[low];
		
	}
	void find_k_smallest(int[]arr,int k)
	{
		int n=arr.length;
		int rank=k;
		
		if(n>=2*k)
		{
		int[]new_arr=new int[2*k];
		int arr_elem_index=0;
		
		for(int i=0;i<2*k;i++)
		new_arr[i]=arr[arr_elem_index++];
		//it will place the element of given rank at its correct location
		
		System.out.println("after initial add of 2k");
		 for(int elem:new_arr )
		  System.out.print(elem+" ");
		 System.out.println();
		 
		int temp=find_element_given_rank(new_arr,0,2*k-1,rank);
		System.out.println("the array has k smallest element in first k position");
		
		 for(int elem:new_arr )
		  System.out.print(elem+" ");
		 System.out.println();
		
		int k_group=(n-2*k)/k;
		int rem_elem=(n-2*k)%k;
		
		for(int i=0;i<k_group;i++)
		{

		
		//now keep on adding rest elements to the end k pos and repeat the same process again 
		 for(int j=k;j<2*k;j++)
		   new_arr[j]=arr[arr_elem_index++];
		   
		   System.out.println("after adding group of k");
		 for(int elem:new_arr )
		  System.out.print(elem+" ");
		 System.out.println();
		 
		 temp=find_element_given_rank(new_arr,0,2*k-1,rank);
		 
		 System.out.println("the array has k smallest element in first k position");
		
		 for(int elem:new_arr )
		  System.out.print(elem+" ");
		 System.out.println();
		 
		}
		//replace to the right of k with rem elem
		for(int j=k;j<(k+rem_elem);j++)
		  new_arr[j]=arr[arr_elem_index++];
		  
		temp=find_element_given_rank(new_arr,0,k+rem_elem,rank);  
		 System.out.println("after adding rem");
		 for(int j=0;j<(k+rem_elem);j++ )
		  System.out.print(new_arr[j]+" ");
		 System.out.println();
		 
		 System.out.println("the first k smallest element");
		 for(int j=0;j<k;j++)
		    System.out.print(new_arr[j]+" ");
		
		}
		else
		{
			int temp=find_element_given_rank(arr,0,n-1,rank);
			System.out.println("the first k smallest element");
		 for(int j=0;j<k;j++)
		    System.out.print(arr[j]+" ");
			
		}
		
		
		
	}
	public static void main (String[] args) throws java.lang.Exception
	{
	
	   Scanner sc=new Scanner(System.in);
	   int n=sc.nextInt();
	   int []arr=new int[n];
	   //int rank=sc.nextInt();
	   int k=sc.nextInt();
	   for(int i=0;i<n;i++)
	     arr[i]=sc.nextInt();
	   Ideone obj=new Ideone();
	   
	   System.out.println("given array");
	   for(int elem:arr)
	      System.out.print(elem+" ");
	      System.out.println();
	   
	   obj.find_k_smallest(arr,k);
	  
	   
	   
	}
}
