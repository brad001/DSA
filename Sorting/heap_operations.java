/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	void top_down_min_heapify(ArrayList<Integer>arr,int i)
	{ 
		int n=arr.size();
		while(2*i+1<n)
		{
			int left=2*i+1;
			int right=2*i+2;
			int min;
			
			if(right<n && arr.get(right)<arr.get(left))
			   min=right;
			else
			   min=left;
			   
			   //System.out.println("index= "+arr.get(min));
			if(arr.get(i) > arr.get(min))
			 {
			   int temp=arr.get(i);
			   arr.set(i,arr.get(min));
			   arr.set(min,temp);
			   i=min;
			 }
			 else
		       break;
		}
		
	}
    void build_min_heap(ArrayList<Integer>arr)
	{
		//System.out.println(arr.size()/2);
		for(int i=arr.size()/2-1;i>=0;i--)
		{
			//System.out.println("helklo");
			top_down_min_heapify(arr,i);
		}
		
	}
	void bottom_up_min_heapify(ArrayList<Integer>arr,int i)
	{
		while((i-1)/2 >=0)
		{
			int parent=(i-1)/2;
		
		//swap if inserted is less than parent
			if(arr.get(i)<arr.get(parent))
			{
				int temp=arr.get(i);
				arr.set(i,arr.get(parent));
				arr.set(parent,temp);
				
				i=parent;
			}
			else //correct position of inserted element
			  break;
			
		}
		
	}
    void add_min_heap(ArrayList<Integer>arr,int val)
    {
    	arr.add(val);
    	//go up to parent and till the root in worst case
    	// the index =arr.size()-1
        bottom_up_min_heapify(arr,arr.size()-1);
      	
    }
    void extract_min(ArrayList<Integer>arr)
    {
    	if(arr.size()>0)
    	System.out.println(arr.get(0));
    	else
    	System.out.println("no elements present");
    }
    void update_key(ArrayList<Integer>arr,int index,int val)
    {
    	//set this location a new value
    	//based on prev value of this loc
    	//if(prev<new) =>new  will come down => top_down
    	//if(prev>new) =>new will move up =>bottom_up
    	
    	int prev=arr.get(index);
    	arr.set(index,val);
    	//System.out.println("prev value= "+prev);
    //	System.out.println("after updated min heap= "+arr);
    	if(prev<val)
    	{
    	    	top_down_min_heapify(arr,index);
    	}
    	else
    	{
    		bottom_up_min_heapify(arr,index);
    	}
    	
    	
    }
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc=new Scanner(System.in);
	    int n=sc.nextInt();
	    ArrayList<Integer>arr=new ArrayList<Integer>();
	    for(int i=0;i<n;i++)
	     arr.add(sc.nextInt());
	     
	     System.out.println(arr);
	     
	     Ideone obj=new Ideone();
	     obj.build_min_heap(arr);
	     
	     System.out.println("min heap="+arr);
	     
	     //obj.add_min_heap(arr,6);
	     obj.add_min_heap(arr,0);
	     System.out.println("min heap="+arr);
	     obj.add_min_heap(arr,5);
	     System.out.println("min heap="+arr);
	     
	     obj.extract_min(arr);
	     //pass index and updated value to update_key
	    
	     
	     //update the value of a location (loc, value)
	     obj.update_key(arr,2,12);
	     System.out.println("min heap="+arr);
	     obj.update_key(arr,0,1);
	     System.out.println("min heap="+arr);
	}
}
