import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
    int get_index(int low,int high)
    {
        int n=high-low+1;
        return (int)((Math.random()*n)+low);
    }
   int partition(int []arr,int low,int high)
   {
       int index=get_index(low,high);
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
		
		return j;
       
   }
  int find_kth_smallest(int[]arr,int low,int high,int k)
  {
      if(low<high)
      {
        int p_index=partition(arr,low,high);    
        if(p_index==k-1)
            return arr[p_index];
         else if(p_index>k-1) 
           return find_kth_smallest(arr,low,p_index,k);
         else
             return find_kth_smallest(arr,p_index+1,high,k);
      }
      else
          return Integer.MIN_VALUE;
      
  }
   
   
 public static void main(String[] args) 
 {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        
        int[]arr=new int[n];
         for(int i=0;i<n;i++)
             arr[i]=sc.nextInt();
     
       int[]t_arr=Arrays.copyOf(arr,arr.length);
        Arrays.sort(t_arr);
      
     System.out.println("given array");
        for(int elem:arr)
            System.out.print(elem+" ");
       System.out.println();

     System.out.println("sorted array");
        for(int elem:t_arr)
            System.out.print(elem+" ");
       System.out.println();
     
     
       Solution obj=new Solution();
       System.out.println("the kth smallest element is  "+obj.find_kth_smallest(arr,0,n-1,k));
       System.out.println("the kth largest element is  "+obj.find_kth_smallest(arr,0,n-1,n-k+1));
     
 }
}
