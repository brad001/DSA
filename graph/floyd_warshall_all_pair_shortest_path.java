import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
   static int INFINT=Integer.MAX_VALUE;
    
    static void print_dist(long[][]dist,int v)
    {
        for(int i=0;i<v;i++)
         {
            for(int j=0;j<v;j++)
            {
              if(dist[i][j]==INFINT)   
               System.out.printf("%-5s","INF");    
              else
               System.out.printf("%-5d",dist[i][j]);
            }
            System.out.println();
         }
            
        
    }
   static void find_all_shortest(long[][] graph,int v)
   {
     //dist is initialised to the graph  
     long dist[][]=new long[v][v];
      
     for(int i=0;i<v;i++)
       for(int j=0;j<v;j++)
         dist[i][j]=graph[i][j];
     
     System.out.println("the original dist matrix");    
     print_dist(dist,v);  
        
     //loop for intermediate vertices=> k
      for(int k=0;k<v;k++) 
      {
        for(int i=0;i<v;i++)    
        {
          for(int j=0;j<v;j++)    
          {
            //k is the max vertex=> find(i,k)+find(k,j)  
            if(dist[i][k]+dist[k][j]<dist[i][j])    
                dist[i][j]=dist[i][k]+dist[k][j];
          }
        }
      }
       
      System.out.println("the final distance matrix"); 
      print_dist(dist,v); 
   }

   
   public static void main(String[] args) 
   {
     int v=4;
     long graph[][]={
                     {0,5,INFINT,10},
                     {INFINT,0,3,INFINT},
                     {INFINT,INFINT,0,1},
                     {INFINT,INFINT,INFINT,0}
                   };
       
     //System.out.println(sz); 
     find_all_shortest(graph,v);
   }
}
