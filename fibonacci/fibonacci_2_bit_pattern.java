import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
    private int [][]M=new int[2][2];
    
    private int [][]Res=new int[2][2];
    private int msb;
    private int lsb;
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
    boolean bit_seq_is_not_zero(String bit_seq)
    {
        //bit_seq is not zero => more bits to process
       if(msb>lsb) //lsb comes to left of msb
           return false;
        else
           return true;
    }
   
    void div_by_2(String bit_seq)
    {
        //div by 2 means, simply move lsb to one pos up towars msg=>-1
        lsb=lsb-1;
    }
    int fib(String bit_seq,int mod_val)
    {
        init_data();
        
        //msb points to first non_zero bit nand lsb to last
        int i=0;
        while(i<bit_seq.length() && bit_seq.charAt(i)=='0' )
            i++;
        
        lsb=bit_seq.length()-1;
        msb=i;
        
        System.out.println("msb "+msb+" lsb "+lsb);
        //we have to find X^(001001011010)
        //but here X is a matrix and bit_seq is a seq of bits
        
        while(bit_seq_is_not_zero(bit_seq))
        {
            
           //last bit of bit_seq is odd => extra multiply
            
            //for all odd num, we have one multiply result in Res[][] and 
            //last call will definately be this so resultant will be  mulitplied by res*m
            if(bit_seq.charAt(lsb)=='1') //odd
            {
               multiply_matrix_to_result(mod_val);    
            }
            //mulitply for each even num, two matrix of prev_calc matrix and store result in same matrix
            multiply_two_matrix(mod_val);
            //update the value of num_arr by div by 2
            div_by_2(bit_seq);
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
      
      //no. of bits
      int n=sc.nextInt();
      int mod_val=sc.nextInt();
      String bit_seq=new String();
      
     // int []num_arr=new int[n];
      for(int i=0;i<n;i++)
      {
          //genearate digits 0-9
          bit_seq+=(int)(Math.random()*2);
      }
      System.out.println("mat ^ "+bit_seq);
     Solution obj=new Solution();
      
     int result=obj.fib(bit_seq,mod_val);
     System.out.println("the fib(n)mod mod_val is  : "+result);
     
   }
}
