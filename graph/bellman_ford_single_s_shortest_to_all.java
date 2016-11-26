import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;



class Graph
{
  static int INF=Integer.MAX_VALUE;  
  class Edge
  {
    int src,dest,wt;    
  }  
  int V,E;
  Edge edge[];
  Graph(int v, int e)
  {
      V=v;
      E=e;
      edge= new Edge[e];
      for(int i=0;i<e;i++)
          edge[i]=new Edge();
  }
  void print_dist(int[]dist)
  {
      
      for(int i=0;i<dist.length;i++)
          System.out.print(dist[i]+" ");
      System.out.println();
  }
  void print_path(int[]parent)
  {
      System.out.printf("%-9s","parent:");
     for(int i=0;i<V;i++) 
       System.out.print(parent[i]+" ");
      
      System.out.printf("\n%-9s","dest:");
      for(int i=0;i<V;i++) 
       System.out.print(i+" ");
      
     System.out.println(); 
     
  }
  void bellman_ford(int s)
  {
   //to store the dist to all vertex from s   
   int dist[]=new int[V];   
      
    //to track the path from s to all
     int parent[]=new int[V] ;
      
   for(int i=0;i<V;i++)   
       dist[i]=INF;
      
     //start from source vertex
    dist[s]=0;
    parent[s]=-1;
    
    //we have to iterate over all the edges for V-1 times
    //each ith iteration finds atleast ith path length dist
    //worst case each ith will find ith path length  
      
     for(int i=1;i<V;i++) 
     {
       for(int j=0;j<E;j++)
       {
         //conside for all edges (u,v), wt
          int u=edge[j].src;
          int v=edge[j].dest;
          int wt=edge[j].wt; 
           //since dist[u]=INF and adding to it=>overflow, therefore check first
         if( dist[u]!=INF && dist[u]+wt < dist[v])
           {
             dist[v]=dist[u]+wt;
             parent[v]=u;
           }     
       }
     }
    
    //iterate for V-1 times, one more iteration necessarily gives a path length of atleast V in W.C=>cycle
     for(int j=0;j<E;j++) 
     {
          int u=edge[j].src;
          int v=edge[j].dest;
          int wt=edge[j].wt;
         if( dist[u]!=INF && dist[u]+wt < dist[v])
           {
             System.out.println("Graph has cycle");
             return;
           }     
     }
      
    //print the distance to all from s
      System.out.println("from source "+s+" the dist to all other");
    print_dist(dist);  
    print_path(parent);  
      
  }
    
}
public class Solution
{

   
 public static void main(String[] args) 
 {
    Scanner sc=new Scanner(System.in);
    int v,e;
    System.out.println("enter the no. of vertex and edges");
    v=sc.nextInt();
    e=sc.nextInt(); 
    
    Graph g=new Graph(v,e); 
    System.out.println("enter for each edge- src, dest, wt");
    for(int i=0;i<e;i++) 
    {
       g.edge[i].src=sc.nextInt();
       g.edge[i].dest=sc.nextInt();
       g.edge[i].wt=sc.nextInt(); 
    } 
     
    g.bellman_ford(0); 
 }
    
}
