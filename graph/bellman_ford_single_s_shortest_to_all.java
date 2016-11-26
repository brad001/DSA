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
           if(dist[i]==INF)
           System.out.print("INF ");
           else
          System.out.print(dist[i]+" ");
       
      System.out.println();
  }
  void print_node(int[]parent,int curr)
  {
      if(curr!=-1 && curr!=-2)
      print_node(parent,parent[curr]);
      else
      return;
      
     System.out.print(curr+"->"); 
  }
  void print_path(int[]parent,int[]dist,int s)
  {

    /* System.out.printf("%-9s","parent:");
     for(int i=0;i<V;i++) 
       System.out.print(parent[i]+" ");
      
      System.out.printf("\n%-9s","dest:");
      for(int i=0;i<V;i++) 
       System.out.print(i+" ");
      */
     System.out.println(); 
      
      //path from s to all other vertices
      
      for(int i=0;i<parent.length;i++)
      {
          System.out.println("From "+s+" to "+i);
          System.out.print("path: ");
           print_node(parent,i);
          System.out.print("END");
          if(dist[i]==INF)
          System.out.println("  cost=INF");    
          else
          System.out.println("  cost="+dist[i]);

      }
      
      
      System.out.println();
      

     
  }
  void bellman_ford(int s)
  {
   //to store the dist to all vertex from s   
   int dist[]=new int[V];   
      
    //to track the path from s to all
     int parent[]=new int[V] ;
      //useful when a vertex has no parent
    for(int i=0;i<V;i++)
        parent[i]=-2;
      
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
    print_path(parent,dist,s);  
      
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
     
    g.bellman_ford(1); 
 }
    
}

/*
test input:
5 8
0 1 -1
0 2 4
1 2 3
1 3 2
1 4 2
3 2 5
3 1 1
4 3 -3

*/
