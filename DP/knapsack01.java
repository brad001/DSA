import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
    static int max_item(int item1,int item2,int item_no)
    {
        return item1>item2?item1:item2;   
        
    }
    static void find_item(int row,int col,int[][]K,int []wt,int[]profit)
    {
        //row rep item, col rep wt
        //table stores the profit
        //max profit is at K[row][col]
        //wt[i-1], profit[i-1] represents for ith
        int item=row;
        int weight=col;
        while(item!=0)
        {
          if(K[item][weight]==K[item-1][weight])    //coming from top=>item row is not include
              item--;
          else//item selcted
          {
              System.out.println("item "+item+" is selected"+" with profit "+profit[item-1]);
              //remaining col weight - wt of item
              weight=weight-wt[item-1];
              item=item-1;
          }
        }
    }

    static int knap(int[]profit,int[]wt,int W) 
    {
      int Knap[][]=new int[profit.length+1][W+1]; //columns for each value from 0-W+1
       
      for(int i=0;i<=profit.length;i++)  //i dentoes the item
      {
        for(int j=0;j<=W;j++)            //j denotes the current weight
        {
          if(i==0||j==0)    
          {
            Knap[i][j]=0;
            continue;
          }  
          //current item=>>whose wt and profit is stored in (i-1)th index 
          //if current item weight >capacity(current capacity is j), then store prev items profit
          if(wt[i-1]>j)  
              Knap[i][j]=Knap[i-1][j];
          else         //sufficient items weight
          {
               Knap[i][j]=max_item(profit[i-1]+Knap[i-1][j-wt[i-1]] , Knap[i-1][j],i-1); 
          }
        }
      }
        int max_row=profit.length;
        int max_col=W;
      find_item(max_row,max_col,Knap,wt,profit);
        return Knap[profit.length][W];//this cell stores the max profit
    }

   
   public static void main(String[] args) 
   {
        Scanner sc = new Scanner(System.in);
        int profit[]={22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int wt[]={4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
       //int profit[]={5,7,1,4};
       //int wt[]={4,5,1,3};
       
        int capacity=sc.nextInt();
        int max_profit=knap(profit,wt,capacity);
        System.out.println("the maximum profit for items : "+max_profit);
        
        //System.out.println(sum);
   }
}
