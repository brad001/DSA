/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int length=sc.nextInt();
		int []arr=new int[n];
		
		for(int i=0;i<n;i++)
            {
		  arr[i]=sc.nextInt();
            System.out.print(arr[i]+" ");
        }
        System.out.println();
		  
		int min_index=0,max_index=0,diff=Integer.MIN_VALUE;
		System.out.println("iter 0 : max_index "+max_index+" min_index "+min_index);
		System.out.println("given array");
		for(int elem:arr)
		System.out.print(elem+" ");
		System.out.println();
		for(int i=0;i<=length;i++)
		{
			if(arr[i]-arr[min_index]>diff)
			{
			 diff=arr[i]-arr[min_index];
			 System.out.println("updated diff = "+diff+" and values are "+arr[i]+" and "+arr[min_index]);
			} 
			
		  if(arr[i]<arr[min_index])
		    min_index=i;
		    if(arr[i]>arr[max_index])
		    max_index=i;
		    System.out.println("iter insde : max_index "+max_index+" min_index "+min_index);
		  
		}
		System.out.println("iter out once : max_index "+max_index+" min_index "+min_index);
		
		for(int i=length+1;i<n;i++)
		{
			
			
			  
			  if(arr[i]>=arr[max_index])
			 max_index=i;
			 else
			 if(max_index<i-length)
			  max_index=i-length;
			 
			 if(min_index< i-length)//out of slot
				min_index=i-length;
		
			else
				if(arr[i]<arr[min_index] && min_index<=max_index)
				min_index=i;
				
			
				
				 //if(min_index<=i && arr[i]<arr[min_index])
				 // min_index=i;
				
			
			
			
			  
			
			 
			
			System.out.println("inside loop2 iter : "+i+ " min_index= "+ min_index+" max_index= "+max_index);
			if(min_index<=max_index)
			if(arr[max_index]-arr[min_index]>diff)
			{
			 
			 diff=arr[max_index]-arr[min_index];
			 System.out.println("updated diff = "+diff+" and values are "+arr[max_index]+" and "+arr[min_index]);
			}
			 
			
		}
		
		for(int j=min_index+1;j<=max_index;j++)
		{
			if( min_index <= max_index && arr[j]<arr[min_index])
			{
			 min_index=j;
			 System.out.println(min_index);
			if(arr[max_index]-arr[min_index]>diff)
			diff=arr[max_index]-arr[min_index];
			 System.out.println("updated diff = "+diff+" and values are "+arr[max_index]+" and "+arr[min_index]);
			}
		}
		
		System.out.println("max difference is : "+diff);
		
	  
	}
}
