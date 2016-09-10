import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
    private int [][]M=new int[2][2];
    
    private int [][]Res=new int[2][2];
    private int bin_msb;
    private int bin_lsb;
    private int dec_msb;
    private int dec_lsb;
    //private int[]bin_arr;
    private char []bin_arr;
        
    void init_data(int[]num_arr)
    {
        M[0][0]=1;
        M[0][1]=1;
        M[1][0]=1;
        M[1][1]=0;
        Res[0][0]=1;
        Res[0][1]=0;
        Res[1][0]=0;
        Res[1][1]=1;   
        
        int n=num_arr.length;
        int i=0;
        while(num_arr[i]==0)
            i++;
        
        dec_msb=i;
        dec_lsb=n-1;
        //since each digit contributes to 4 binary bits
        bin_arr=new char[4*n];
        
        bin_msb=(4*n)-1;
        bin_lsb=(4*n)-1;
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
   
   boolean bit_seq_is_not_zero()
    {
        //bit_seq is not zero => more bits to process
       if(bin_msb>bin_lsb) //lsb comes to left of msb
           return false;
        else
           return true;
    }
   
    void div_by_2_bin()
    {
        //div by 2 means, simply move lsb to one pos up towars msg=>-1
        bin_lsb=bin_lsb-1;
    }
    //it divides and returns true if rem 1 else 0
    boolean div_by_2_rem(int []num_arr)
    {
        int carry_forward=0;
        
        for(int i=dec_msb;i<=dec_lsb;i++)
        {
          int next_num=num_arr[i]+carry_forward;
          num_arr[i]=next_num/2;
            //System.out.println("no. added "+num_arr[i]);
          carry_forward=(next_num%2)*10;
        }
        
        
        //if strating positions has 0 then shift msg to right
        while(dec_msb<= dec_lsb && num_arr[dec_msb]==0)
            {
            dec_msb++;
            //System.out.println("dec_msg on 0 updates to "+dec_msb);
            }
        
        //System.out.println("carry_forwardis "+carry_forward);
        if(carry_forward!=0) //last digit was odd
            return true;
        else
            return false;
            
    }
    void find_binary(int[]num_arr)
    {
        
        //process all digits of num_arr
        //store them in bin_arr
        
        while(dec_msb<=dec_lsb)
        {
            //if rem present
            //store from back to front
          if(div_by_2_rem(num_arr))
              {
              bin_arr[bin_msb--]='1';
              }
           else
               {
              bin_arr[bin_msb--]='0';
              }
        }
        
        //store all other places as 0
        for(int i=bin_msb;i>=0;i--)
          bin_arr[i]='0';
        
        //update bin_msb to its correct pos
        bin_msb=bin_msb+1;
        //bin_msg points to front and bin_lsb to back
        //therefore each iteration lsb dec and once becomes less than msb break
        
    }
    int fib(int []num_arr,int mod_val)
    {
        init_data(num_arr);
        
        find_binary(num_arr);
        
        System.out.println("the binary pattern: ");
        for(char elem:bin_arr)
            System.out.print(elem+" ");
        System.out.println();
        
        
       while(bit_seq_is_not_zero())
        {
            
           //last bit of bit_seq is odd => extra multiply
            
            //for all odd num, we have one multiply result in Res[][] and 
            //last call will definately be this so resultant will be  mulitplied by res*m
            if(bin_arr[bin_lsb]=='1') //odd
            {
               multiply_matrix_to_result(mod_val);    
            }
            //mulitply for each even num, two matrix of prev_calc matrix and store result in same matrix
            multiply_two_matrix(mod_val);
            //update the value of num_arr by div by 2
            div_by_2_bin();
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
      {
           //genearate digits 0-9
          num_arr[i]=(int)(Math.random()*10);
      }
      System.out.println("the num pattern: ");
        for(int elem:num_arr)
            System.out.print(elem+" ");
        System.out.println();
     

     Solution obj=new Solution();
      
     int result=obj.fib(num_arr,mod_val);
     System.out.println("the fib(n)mod mod_val is  : "+result);
     
   }
}
