import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
    
    static void print_arr(int[][]cost,int n)
    {
        for(int i=0;i<n;i++)
        {
          for(int j=0;j<n;j++)    
          {
            System.out.printf("%-5d",cost[i][j]);  
          }
            System.out.println();
        }
    }
   static int matrixChainMult(int[]order)
   {
      //since we have num_of_mat=order.length-1    
      int n=order.length-1;
      //to store cost of matrix mult n*n matrix
       //initalise the diagonals by 0
      int cost[][]=new int[n][n];
       System.out.println("the cost matrix initially");
       print_arr(cost,n);
       
       //store k(partition areas) values
       int[][]mink=new int[n][n];
       
      //access and store =>diagonal wise upper right
       
      //outer loop for number of diagonals except main => no. of elements in last col except last row=n-1
      for(int dig=1;dig<=n-1;dig++) 
      {
         //access each element in top down fashion for each diagonal,starting from top row
          for(int i=0;i<n-dig;i++) //each time we access one less element in each dig => 1st dig has n-1 elements =>i=0 to n-2
          {
              //since col will change for each element
              int j=i+dig; 
              
              cost[i][j]=Integer.MAX_VALUE;
              
              //now if we have Ai,Ai+1,Ai+2.....Aj matrix
              //we have to split at kth loc=> bracket inroduction=> two matrices (Ai....Ak) , (Ak+1.....Aj) + order of each
              //k varies from i to j-1 => if(k==i) gives me the base cost =0
              //we have to find which partition of k gives min
              int temp,min_k=0;
              for(int k=i;k<=j-1;k++)
              {
                  temp=cost[i][k]+cost[k+1][j]+(order[i]*order[k+1]*order[j+1]); //the actual loc of each order
                  if(temp<cost[i][j])
                      {
                         cost[i][j]=temp;
                          min_k=k;
                     
                      }
                  
              }    
                  mink[i][j]=min_k;
              
          }
      }
       
       System.out.println("the cost matrix after calculation");
       print_arr(cost,n);
       
       //print the min_k matrix
       System.out.println("the min_k matrix");
       print_arr(mink,n);
       //the first row and lst col stores the value of min cost of mult
       return cost[0][n-1];
   }
   public static void main(String[] args) 
   {
        Scanner sc = new Scanner(System.in);
       int order[]={2,3,6,4,5};
       int num_mat=order.length-1; //one extra field is needed by order
       
       int cost=matrixChainMult(order);
       System.out.println("the cost of multiplying matrices is :"+cost);

   }
}
