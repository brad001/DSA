import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
 
  int fib_mod_m(int[]arr,int m)  
  {
     //store first 6*m+1 numbers
      int []fib=new int[6*m+1];
      fib[0]=0;
      fib[1]=1;
      for(int i=2;i<=6*m;i++)
          fib[i]=(fib[i-1]+fib[i-2])%m;
      
      //find where the fib seq repeats
      //check for 01..01...
      int period_rep=0;
      for(int i=3;i<=6*m;i++)
          if(fib[i-1]==0 && fib[i]==1)
          { 
             period_rep=i-1;
          }
     
      //find corresponding value of mod p in fib[] array
       int carry=0,next_digit;
       for(int i=0;i<arr.length;i++)
       {
        next_digit=arr[i]+carry;
        carry=(next_digit%period_rep)*10;
       }
       int index_final=carry/10;
      
      return fib[index_final];
  }
    
 public static void main(String[] args) 
 {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int m=sc.nextInt();
    int []arr=new int[n];
    
     for(int i=0;i<n;i++)
         arr[i]=(int)(Math.random()*10);
     
     Solution obj=new Solution();
     int result=obj.fib_mod_m(arr,m);
     System.out.println("the resultant value is "+result);
     
 }
}
