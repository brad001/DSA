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
      
       int c1=0,val1=arr[0],c2=0,val2=arr[0];
       
       for(int i=0;i<n;i++)
       {
       	if(c1==0 || arr[i]==val1)
       	{
       		val1=arr[i];
       		c1++;
       	}
       	else if(c2==0|| arr[i]==val2)
       	{
       		val2=arr[i];
       		c2++;
       	}
       	else if(arr[i]!=val1 && arr[i]!=val2)
       	{
       		c1--;
       		c2--;
       	}
       	
       	
       }
       //both may be the candidates for majority elements
       boolean flag=false;
       if(c1!=0)
       {
       	int count=0;
       	for(int i=0;i<n;i++)
       	  if(arr[i]==val1)
       	  count++;
       	if(count>n/3)  
       	{
       		flag=true;
        System.out.println("candidate : "+val1);
       	}
       
       
       }
       if(c2!=0)
       {
       	int count=0;
       	for(int i=0;i<n;i++)
       	  if(arr[i]==val2)
       	  count++;
       	if(count>n/3) 
       	{
       		flag=true;
            System.out.println("candidate : "+val2);
       	}
         
       	}
	   
	    if(flag==false)
	   System.out.println("no more than n/3 elements");
    }
       
	   
	  
}
