import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;



class Graph
{
  
  class Edge implements Comparable<Edge>
  {
    int src,dest,wt;    
    public int compareTo(Edge compareEdge)  
    {
      return this.wt-compareEdge.wt;    
    }
  }  
  int V,E;
  Edge edge[];
  int subset[];
  Graph(int v, int e)
  {
      V=v;
      E=e;
      edge= new Edge[e];
      for(int i=0;i<e;i++)
          edge[i]=new Edge();
  }
  void print_mst(Edge[]result,int no_edges)
  {
      
      for(int i=0;i<no_edges;i++)
      {
        System.out.println("("+result[i].src+","+result[i].dest+")"+"=>"+result[i].wt);  
      }
  }
  //to find the representing element of the set  
  int root(int x)
  {
    while(subset[x]!=x)
    {
      subset[x]=subset[subset[x]];
      x=subset[x];
    }
      return x;
  }
   //make both vertex's value same, to rep in one set 
   void union(int p,int q)
   {
       p=root(p);
       q=root(q);
       int min=p<q?p:q;
       subset[p]=min;
       subset[q]=min;
   }
   void kruskal_mst()
   {
       
    subset=new int[V];
     for(int i=0;i<V;i++)  
         subset[i]=i;
    
    //this will store the mst edges included   
    Edge[] result=new Edge[V];   
    for(int i=0;i<V;i++)   
        result[i]=new Edge();
       
    //sort all edges based on weights
     Arrays.sort(edge);  
     
     int mst_cost=0;
     int  count_edge_included=0;
     for(int i=0;i<E;i++)  //select edges one by one
     {
        int u=edge[i].src;
        int v=edge[i].dest;
        if(root(u)!=root(v))
        {
            union(u,v);
            mst_cost+=edge[i].wt;
            result[count_edge_included]=edge[i];
            count_edge_included++;
            if(count_edge_included==V-1)
                break;
        }
     }
       
       System.out.println("the edges includes");
       print_mst(result,count_edge_included);
       System.out.println("mst cost="+mst_cost);
       
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
     
    g.kruskal_mst(); 
 }
    
}


//test case:1
/*
4 5
0 1 10
0 2 6
0 3 5
1 3 15
2 3 4
*/

//test case 2:
/*
9 14
7 6 1
8 2 2
6 5 2
0 1 4
2 5 4
8 6 6
2 3 7
7 8 7
0 7 8
1 2 8
3 4 9
5 4 10
1 7 11 
3 5 14
*/
