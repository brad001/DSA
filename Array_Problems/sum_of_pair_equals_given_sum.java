//algo 1: O(n log n)
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
	  int sum=sc.nextInt();
	  int []arr=new int[n];
	  
	  for(int i=0;i<n;i++)
	   arr[i]=sc.nextInt();
	   
	  Arrays.sort(arr);
	   //print array
       /*for(int elem:arr)
         System.out.println(elem);
        */ 
      int low=0,high=n-1;
      boolean flag=false;
      while(low<high)
      {
      	if(arr[low]+arr[high]==sum)
      	{
      	   System.out.println("pairs found: "+arr[low]+" "+arr[high]);
      	   flag=true;
      	   break;
      	
      	}
      	else if(arr[low]+arr[high]<sum)
      	   low++;
      	else
      	   high--;
      }
         
         if(flag==false)
         System.out.println("no pairs found");
      
	  
	  
	}
}

//------------------------------------------------------------------------------------------------------------//

//Algo 2: O(n)
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
		Map<Integer,Integer>m=new HashMap<Integer,Integer>();
		int n=sc.nextInt();
		int sum=sc.nextInt();
		
		int []arr=new int[n];
		for(int i=0;i<n;i++)
		   arr[i]=sc.nextInt();

		int i=0;
		boolean flag=false;
		while(i<n)
		{
			if(m.containsKey(sum-arr[i]))
			{
			System.out.println("pair present : "+(sum-arr[i])+" "+arr[i]);
			flag=true;
			break;
			}
			
			m.put(arr[i],1);
			i++;
		}
		if(flag==false)
		System.out.println("no pair present");
		
	}
}


