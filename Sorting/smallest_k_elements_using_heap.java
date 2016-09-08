/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
  void top_down_max_heapify(int[]arr,int k,int i)
  {
	int n=k;
   while(2*i+1<n)
   {
	int left=2*i+1;
	int right=2*i+2;
	int max;
	if(right<n && arr[right]>arr[left])
	max=right;
	else
	max=left;
	//System.out.println("index= "+arr.get(min));
	 if(arr[i] < arr[max])
 	 {
	  int temp=arr[i];
	  arr[i]=arr[max];
	  arr[max]=temp;
	  
	  i=max;
	 }
	 else
	  break;
   }
   
  }//fun end

	void build_max_heap(int[]arr,int k)
	{
	//k=num of elements
	
	
     for(int i=k/2-1;i>=0;i--)
     {
	 //System.out.println("helklo");
	 top_down_max_heapify(arr,k,i);
     }
     
    /* System.out.println("the max heap for k elements");
     for(int i=0;i<k;i++)
        System.out.print(arr[i]+" ");
     System.out.println();  
     */
     //keep adding elements till end and maintain only k min elements
     //root will have max at any point of time
     
     for(int i=k;i<arr.length;i++)
     {
     	//compare with root, if small addd if greater discard
     	if(arr[i]<arr[0])
     	{
     		arr[0]=arr[i];
     		//pass array, the num of elem in heap, and the index to heapify
     		top_down_max_heapify(arr,k,0);
     	}
     }
     
     /*System.out.println("the max heap for k elements");
     for(int i=0;i<k;i++)
        System.out.print(arr[i]+" ");
     System.out.println();  
     */
     
    }
    
   
   
	public static void main (String[] args) throws java.lang.Exception
	{
	
	   Scanner sc=new Scanner(System.in);
	   int n=sc.nextInt();
       int k=sc.nextInt();
       
	   int []arr=new int[n];
	   
	   for(int i=0;i<n;i++)
	     arr[i]=sc.nextInt();
	   
	   
	   System.out.println("given array");
	   for(int elem:arr)
	      System.out.print(elem+" ");
	    System.out.println();
	  
	  
	  int[]temp_arr = Arrays.copyOf(arr, arr.length);
	  Arrays.sort(temp_arr);
	  System.out.println("the sorted array");
	   for(int elem:temp_arr)
	      System.out.print(elem+" ");
	    System.out.println();
	  
	  Ideone obj=new Ideone();
	  obj.build_max_heap(arr,k);
	  
	   System.out.println("the final k: "+k+" smallest elements are:");
	   for(int i=0;i<k;i++)
	      System.out.print(arr[i]+" ");
	    System.out.println();
	  
	  
	  
	  
	  
	  
       
	      	    
	      	    
	}
}
