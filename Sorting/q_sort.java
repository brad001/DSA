/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class sort
{

	int partition(int arr[], int low, int high)
    {
    	
    	int p_index=(int )(Math.random() *(high-low+1)+low);
    	
    	//System.out.println("high="+high+" low="+low+" rand="+p_index);
    	int t=arr[p_index];
    	arr[p_index]=arr[high];
    	arr[high]=t;
    	
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<=high-1; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;
 
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
 
        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
 
        return i+1;
    }
	void q_sort(int[]arr,int low,int high)
	{
		int p_index;
		while(low<high)
		{
			p_index=partition(arr,low,high); //pivot is at correct loc
			q_sort(arr,low,p_index-1);
			q_sort(arr,p_index+1,high);
			low++;
			high--;
		}
	}
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[]arr=new int[n];
		
		for(int i=0;i<n;i++)
		   arr[i]=sc.nextInt();
		
		//array before sorting
		System.out.println("before sorting");
		for(int elem:arr)
		   System.out.print(elem+" ");
		System.out.println();   
		
		sort ob=new sort();
		
		ob.q_sort(arr,0,n-1);
		
		//after sorting
		System.out.println("after sorting");
		for(int elem:arr)
		   System.out.print(elem+" ");
		   
	}
}
