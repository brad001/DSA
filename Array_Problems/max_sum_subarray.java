import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{

 static void find_max_contiguous(int[]arr)
 {
     int max_so_far=arr[0],curr_max=arr[0];
     int temp_low=0,low=0,high=0;
     
     int n=arr.length;
     for(int i=1;i<n;i++)
     {
       if(arr[i]>curr_max+arr[i]) //new start of sub-array
       {
           temp_low=i;
           curr_max=arr[i];
       }
       else
           curr_max=curr_max+arr[i];
         
       //update both high and low only when curr_max> max_so far
       if(curr_max>max_so_far)
       {
           high=i;
           low=temp_low;
           max_so_far=curr_max;
       }
             
         
     }
     
     System.out.println("sum [ start "+low+" end "+high+" ]="+max_so_far);
 }
    
  static void print_ln()
  {
    System.out.println();    
  }
    
  static void print_arr(int []arr)
  {
      for(int elem:arr)
        System.out.print(elem+" ");
          
  }
   
 public static void main(String[] args) 
 {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int []arr=new int[n];
     
        for(int i=0;i<n;i++)
            arr[i]=sc.nextInt();
        
        print_arr(arr);
        print_ln();
        find_max_contiguous(arr);
 }
}
