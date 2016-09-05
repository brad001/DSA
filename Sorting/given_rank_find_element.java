                                                                                           /* package whatever; // don't place package name! */
//algo 1: O(nlogn)
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
		int rank=sc.nextInt();
		int []arr=new int[n];
		
		for(int i=0;i<n;i++)
		   arr[i]=sc.nextInt();
		   
		Arrays.sort(arr);
	//rank = total elem -rank => 10-3 =7=> .....8 9 10 => index of 8 is 7 => arr[total-rank]
	
	System.out.println(arr[n-rank]);
	}
	
}


//-----------------------------------------------------------------------------------------------------------//
//Algo 2 : 
