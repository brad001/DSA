import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
    
    static void find_item(int row,int col, int[]subset,boolean[][]subset_sum)
    {
        //row rep item, col rep sum
        //table stores the t/f for each subset_sum
        //subset_sum[row][col]; = true (must be true at first)
        if(subset_sum[row][col]==false)
        {
         System.out.println("doesnt exists such subset");    
            return;
        }
        int element=row;
        int sum=col;
        while(sum!=0)
        {
          if(subset_sum[element][sum]==subset_sum[element-1][sum])    //coming from top=>element is not include
          {
            //item not selected   
              element--;
              
          }
          else//item selected
          {
             System.out.println("item "+element+" is selected"+" with value "+subset[element-1]);
             element=element-1; //go to prev row and find the next subset element
             sum=sum-subset[element];  
          }
        }
    }

    static void print_arr(boolean[][]arr,int row,int col)
    {
        System.out.println("subset sum aaray is");
        for(int i=0;i<=row;i++)
        {
          for(int j=0;j<=col;j++)    
          {
            System.out.print(arr[i][j]+"     ") ;  
          }
            System.out.println();
        }
    }
    static boolean subsetSum(int[]subset,int sum) 
    {
      boolean subset_sum[][]=new boolean[subset.length+1][sum+1]; //columns for each value of sum from 0 - sum+1
       
      // to get sum=0 from any subset, we have phi(0) as the candidate subset=>true
      for(int i=0;i<=subset.length;i++)  
          subset_sum[i][0]=true;
      for(int j=1;j<=sum;j++)  
          subset_sum[0][j]=false;
        
        
      for(int i=1;i<=subset.length;i++)  //i dentoes the subset list till now
      {
        for(int j=1;j<=sum;j++)            //j denotes the current sum
        {
          int element=subset[i-1];//subset(i-1) =>1st element indexed at 0
          if(element>j)  //this element cant be included in the set, therefore prev calculate for this sum
              subset_sum[i][j]=subset_sum[i-1][j];
          else
          {
              subset_sum[i][j]=(subset_sum[i-1][j] || subset_sum[i-1][j-subset[i-1]]);
          }
        }
      }
        
      find_item(subset.length,sum,subset,subset_sum);
        
        //print_arr(subset_sum,subset.length,sum);
        
        return subset_sum[subset.length][sum];//this cell stores the max profit
    }

   
   public static void main(String[] args) 
   {
        Scanner sc = new Scanner(System.in);
        int subset[]={1, 3, 5, 5, 2, 1, 1, 6};
       //int subset[]={2,3,7,8,10};
        int sum=sc.nextInt();
        boolean exists=subsetSum(subset,sum);
        if(exists==true)
        System.out.println("subset with sum ="+sum+" exists");
        else
        System.out.println("subset doesnt exists");   
        
        //System.out.println(sum);
   }
}
