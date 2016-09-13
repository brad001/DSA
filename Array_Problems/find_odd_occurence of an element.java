import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
 static int odd_num(int []arr)
 {
     int odd=arr[0];
     for(int i=1;i<arr.length;i++)
         odd=odd^arr[i];
     
     return odd;
 }

   
  static void p_arr(int[]arr)
  {
     System.out.println("the array is");    
      for(int elem:arr)
          System.out.print(elem+" ");
      System.out.println();
  }
  
  static void p_val(int val)
  {
     System.out.println("the value is "+val);    
  }
  static void input(int[]arr,Scanner sc)
  {
      //Scanner sc=new Scanner(System.in);
      for(int i=0;i<arr.length;i++)
          arr[i]=sc.nextInt();
  }
    
 public static void main(String[] args) 
 {
          Scanner sc = new Scanner(System.in);
          int n=sc.nextInt();
          int []arr=new int[n];
          
          input(arr,sc);
          p_arr(arr);
      
          int res=odd_num(arr);   
          p_val(res);
        
 }
}
