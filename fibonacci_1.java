import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
    private int [][]M=new int[2][2];
    
    private int [][]Res=new int[2][2];
    void init_data()
    {
        M[0][0]=1;
        M[0][1]=1;
        M[1][0]=1;
        M[1][1]=0;
        Res[0][0]=1;
        Res[0][1]=0;
        Res[1][0]=0;
        Res[1][1]=1;   
    }
    void multiply_matrix_to_result(int mod_val)
    {
        int [][]temp=new int[2][2];
        temp[0][0]=Res[0][0]*M[0][0]+Res[0][1]*M[1][0];
        temp[0][1]=Res[0][0]*M[0][1]+Res[0][1]*M[1][1];
        temp[1][0]=Res[1][0]*M[0][0]+Res[1][1]*M[1][0];
        temp[1][1]=Res[1][0]*M[0][1]+Res[1][1]*M[1][1];
        for (int i = 0; i < 2; ++i)
           for (int j = 0; j < 2; ++j) 
             Res[i][j] = temp[i][j] % mod_val;

    }
    
    void multiply_two_matrix(int mod_val)
    {
        int [][]temp=new int[2][2];
        temp[0][0]=M[0][0]*M[0][0]+M[0][1]*M[1][0];
        temp[0][1]=M[0][0]*M[0][1]+M[0][1]*M[1][1];
        temp[1][0]=M[1][0]*M[0][0]+M[1][1]*M[1][0];
        temp[1][1]=M[1][0]*M[0][1]+M[1][1]*M[1][1];
        
        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++)
              M[i][j]=temp[i][j]%mod_val;
            
    }
    boolean num_arr_is_not_zero(int []num_arr)
    {
        
        
        int or_res=0; //take OR of all num[i]
        
        //check if all positions are 0 or not
        for(int i=0;i<num_arr.length;i++)
              or_res=or_res|num_arr[i];    
        
        if(or_res==0)
            return false;
        else
            return true;
    }
   
    void div_by_2(int []num_arr)
    {
        int carry_forward=0;
        
        for(int i=0;i<num_arr.length;i++)
        {
          int next_num=num_arr[i]+carry_forward;
          num_arr[i]=next_num/2;
          carry_forward=(next_num%2)*10;
        }
        
    }
    int fib(int []num_arr,int mod_val)
    {
        init_data();
        //we have to find X^n
        //but here X is a matrix and n is an array
        
        while(num_arr_is_not_zero(num_arr))
        {
           //last digit of num_arr is odd => extra multiply
            
            //for all odd num, we have one multiply result in Res[][] and 
            //last call will definately be this so resultant will be  mulitplied by res*m
            if(num_arr[num_arr.length-1]%2==1)
            {
                
               multiply_matrix_to_result(mod_val);    
            }
            //mulitply for each even num, two matrix of prev_calc matrix and store result in same matrix
            multiply_two_matrix(mod_val);
            //update the value of num_arr by div by 2
            div_by_2(num_arr);
        }
        
        
        //since the result of fin(n) will be store in M[1][0]
        return Res[1][0];
        
    }

   
  public static void main(String[] args)
   {
     Scanner sc=new Scanner(System.in);  
     //to find fib we do M^n
      //to calc M^n(where n is large no. stored in array)
      //  but the result will be large enough so we take mod k value of fib(n)mod k
      
      //no. of digints
      int n=sc.nextInt();
      int mod_val=sc.nextInt();
      
      int []num_arr=new int[n];
      for(int i=0;i<n;i++)
          num_arr[i]=sc.nextInt();
      
     

     Solution obj=new Solution();
      
     int result=obj.fib(num_arr,mod_val);
     System.out.println("the fib(n)mod mod_val is  : "+result);
     
   }
}
