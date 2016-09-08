/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Solution
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
		
        //System.out.println("hello");
		return j;
	}
	void q_sort( int[]arr,int low,int high,int k)
	{
        //k smallest elements
		
        int[] new_arr=new int[2*k];
        
        int index_arr=0; //to track all elements of arr
        for(int i=0;i<2*k;i++)
          new_arr[i]=arr[index_arr++];
        
        
        
        int p_index=partition(new_arr,0,2*k-1);
        int tot_elem=2*k;
		while(true)
        {
          System.out.println("the index to which partition occurs is "+p_index);
			System.out.println("after partition");
			for(int i=0;i<tot_elem;i++)
			  System.out.print(new_arr[i]+" ");
              
        
        //(p_index-1) elements are k smallest
        if(p_index+1 ==k)
        {
            if(index_arr<arr.length)
            {
                
            System.out.println("copy elemnts 1st if");
            
             int i;
                boolean flag=false;
            for(i=p_index+1;i<2*k && index_arr<arr.length;i++)
            {flag=true;
                System.out.println("added");
               new_arr[i]=arr[index_arr++];    
            }
             if(flag==true)
            {
            tot_elem=i;
            System.out.println("tot_elem in new arr "+tot_elem);
            p_index=partition(new_arr,0,tot_elem-1);
             }
                else
                {
                    System.out.println("the k smallest elements are");
                    Arrays.sort(new_arr);
            for(int j=0;j<k;i++)
                System.out.print(new_arr[j]+" ");
            System.out.println();
                   break; 
                }
            
            }
            else//all elem seen
            {

            System.out.println("the k smallest elements are");
            for(int j=0;j<k;j++)
                System.out.print(new_arr[j]+" ");
            System.out.println();
            break;
            }
        }
        else if(p_index+1 < k)
        {
            System.out.println("call again");
            p_index=partition(new_arr,0,2*k-1);
        }
        else //p_index has atleast k small elements
        {
            System.out.println("copy elemnts");
            
             int i;
            boolean flag=false;
            for(i=p_index+1;i<2*k && index_arr<arr.length;i++)
            {flag=true;
                System.out.println("added");
               new_arr[i]=arr[index_arr++];    
            }
            
            tot_elem=i;
            System.out.println("tot_elem in new arr "+tot_elem);
            p_index=partition(new_arr,0,tot_elem-1);
            
            
        }
       }//while end

    }
		
	
	public static void main (String[] args) throws java.lang.Exception
	{
	
	   Scanner sc=new Scanner(System.in);
	   int n=sc.nextInt();
       int k=sc.nextInt();
        
	   int []arr=new int[n];
	   
	   for(int i=0;i<n;i++)
	     arr[i]=sc.nextInt();
	   Solution obj=new Solution();
	   
	   System.out.println("before sorting");
	   for(int elem:arr)
	      System.out.print(elem+" ");
	   
        obj.q_sort(arr,0,n-1,k);
	   
	   
	}
}
