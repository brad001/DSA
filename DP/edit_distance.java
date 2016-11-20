import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
    
    static int min(int a,int b,int c)
    {
     int min=a<b?a:b;
       return c<min?c:min;    
    }
    
    static void print_arr(int row,int col,int[][]cost)
    {
        System.out.println("the cost matrix is");
        for(int i=0;i<=row;i++)
        {
          for(int j=0;j<=col;j++)    
          {
            System.out.print(cost[i][j]+"  ");
          }
            System.out.println();
        }
    }
    static void print_operation(char[]str2,char[]str1,int[][]cost)
    {
        //here we come in back->front in both strings
        int j=str1.length;
        int i=str2.length;
        
        while(true)
        {
            if(i==0 ||j==0)
                break;
            if(str1[j-1]==str2[i-1]) //this is copied from up-left-diagonal
            {
              i=i-1;
              j=j-1;
            }
            else if(cost[i][j]==cost[i-1][j-1]+1) //min from up-left-dig =>edit
            {
             System.out.println("edit on "+Arrays.toString(str1)+" from last "+str1[j-1]+" characted is changed to "+str2[i-1]);    
             i=i-1;
             j=j-1;   
            }
            else if(cost[i][j]==cost[i-1][j]+1) //min from top =>add
            {
              System.out.println("add on "+Arrays.toString(str1)+" from last "+str2[i-1]);    
              i=i-1;  
            }
            else //min from left=> delete
            {
                System.out.println("delete from "+Arrays.toString(str1)+" from last "+str1[j-1]+" character");
                j=j-1;
            }
        }
        
    }
    static int edit_distance(String s1,String s2)
    {
      //      ...str1...
      //  .
      //  str2       str1=>str2 (modifications only on str1)
      //  .
      //  .
        System.out.println("String 1: "+s1+" String 2: "+s2);
        char[]str1=s1.toCharArray();
        char[]str2=s2.toCharArray();
        int cost[][]=new int[str2.length+1][str1.length+1];
        
        //1st row and col are both compared to null string
        for(int i=0;i<=str1.length;i++)
            cost[0][i]=i;
        for(int i=0;i<=str2.length;i++)
            cost[i][0]=i;
        
        
        
        ///we traverse row wise , because we need to fine min of left top and dig-up-left
        for(int i=1;i<=str2.length;i++)
        {
          for(int j=1;j<=str1.length;j++)    
          {
                if(str1[j-1]==str2[i-1]) //same character => diagonal //note: str1 has j index, str2 has i index
                    cost[i][j]=cost[i-1][j-1];
                else
                 {
                   cost[i][j]=1+min(cost[i][j-1],cost[i-1][j-1],cost[i-1][j]);  //check min of left ,dig, top
                }
          }
        }
       
        print_arr(str2.length,str1.length,cost); //row,col,cost[][]
       // System.out.println("inside edit dist "+Arrays.toString(str1));
        print_operation(str2,str1,cost);
        return cost[str2.length][str1.length];
    }
   public static void main(String[] args) 
   {
        Scanner sc = new Scanner(System.in);
        String s1="abcde";       
        String s2="abd";
        
       int cost=edit_distance(s1,s2);
       System.out.println("the cost of add/delete/edit = "+cost);
    
    
   }
}
