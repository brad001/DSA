//O(n)
/*
 while (i+2 < n) each time subtract two numbers a[i+2]-a[i] and find the max
*/

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
	 int[]arr=new int[n];
	 for(int i=0;i<n;i++)
	   arr[i]=sc.nextInt();
	 
	 //find a[i]-min of all on lhs and then update min
     int diff=Integer.MIN_VALUE;
     int min=arr[0];
     int i=0;
     for(int j=length;j<n;j++)
     {
     	if(arr[j]-arr[i]>diff)
     	  diff=arr[j]-arr[i];
     	
     	  i++;
     }
     
     System.out.println("maximum difference is : "+diff);
	}
}
