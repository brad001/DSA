//algo : O(nlog)+O(n2)=O(n2)

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
	 int []arr=new int[n];
	 for(int i=0;i<n;i++)
	    arr[i]=sc.nextInt();
	 
	 Arrays.sort(arr);
	 boolean flag=false;
	 
	 for(int i=0;i<n;i++)
	 {
	 	int low=0, high=n-1;
	 	while(low<high)
	 	{
	 		if(arr[low]+arr[high]==arr[i])
	 		{
	 		  System.out.println("pairs found for : "+arr[i]+"="+arr[low]+"+"+arr[high]);
	 		  flag=true;
	 		  break;
	 		}
	 		else if(arr[low]+arr[high]<arr[i])
	 		   low++;
	 	    else
	 	       high--;
	 	}
	 }
	if(flag==false)
	System.out.println("no pair exists");
	}
}
