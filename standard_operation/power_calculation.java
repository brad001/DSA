import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
    //recursive approach
   int calc_power(int x,int n)
   {
       //base case
       
           if(n==0)
               return 1;
           else if(n==1)
                return x;
           else if(n%2==0)//even
             return calc_power(x*x,n/2);
           else//odd
             return x*calc_power(x*x,n/2);
       
   }
   
    //iterative approach
    int calc_power(int x,int n)
    {
        
        int result=1;
        
        while(n>0)
        {
            if((n&1)>0)  //if n is odd=> result is 1
            {
                result=result*x;  //this will run atleast once for every num, even it is even, that is last iteration
            }
            x=x*x;  //update each time x
            n=n>>1;  //div n by 2
        }
        
        return result;
    }

   
  public static void main(String[] args)
   {
     Scanner sc=new Scanner(System.in);  
     int x=sc.nextInt();
     int n=sc.nextInt();
     
     //calculate power(x,n)
     Solution obj=new Solution();
      
     int result=obj.calc_power(x,n);
     System.out.println("the power of x^n : "+result);
     
   }
}
