//algo 1 : O(n2) => for each two find diff and max of diff
//algo 2 : O(nlogn) => sort and arr[high]-arr[low]

//algo 3: O(n) => find max and min

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
		int[]arr=new int[n];
		for(int i=0;i<n;i++)
		  arr[i]=sc.nextInt();
		int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
		for(int i=0;i<n;i++)
		{
			if(arr[i]>max)
			  max=arr[i];
			if(arr[i]<min)
			  min=arr[i];
		}
		
		System.out.println("maximum difference is :"+(max-min));
	
	}
}
