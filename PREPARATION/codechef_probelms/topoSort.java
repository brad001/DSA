/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Node
{
 int vertex;
 //int weight;   
}
class Graph
{
  private int V; 
    public static int time;
  private LinkedList<Node>adj[];
  Graph(int v)      
  {
      V=v;
      time=0;
      adj=new LinkedList[V];
      for(int i=0;i<V;i++)
      adj[i]=new LinkedList();
  }
  void addEdge(int v,int w)
  {
      Node n=new Node();
      n.vertex=w;
      
    adj[v].add(n);
    
    //undirected graph
     //adj[w].add(v);
  } 
    void topo_sort_rec(int s,boolean[]visited,int[]d,int[]f,Stack st)
    {
        
        visited[s]=true;
        d[s]=time++;
        
        Iterator<Node>it=adj[s].listIterator();
        while(it.hasNext())
        {
          int n=it.next().vertex;
          if(!visited[n])
              topo_sort_rec(n,visited,d,f,st);
        }
        
        f[s]=time++;
        st.push(new Integer(s));
        
    }
    //pass such a node which has no incoming edge
  void topo_sort()
  {
      boolean[]visited=new boolean[V];
      int[]d=new int[V];
      int[]f=new int[V];
      Stack st=new Stack();
      //static int time=0;
      for(int i=0;i<V;i++)
      {
          if(!visited[i])
              topo_sort_rec(i,visited,d,f,st);
      }
      System.out.println("discovery timings are");
      for(int i=0;i<V;i++)
          System.out.print(d[i]+" ");
      System.out.println("\nfinish timings are");
      for(int i=0;i<V;i++)
          System.out.print(f[i]+" ");
      
      System.out.println("\nStack contents are");
      while(!st.isEmpty())
          System.out.print(st.pop()+" ");
  }
    	
}
class Solution
{
	
    
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc=new Scanner(System.in);
        
        Graph g=new Graph(6);
        g.addEdge(5,2);
        g.addEdge(2,3);
        g.addEdge(3,1);
        g.addEdge(4,1);
        g.addEdge(4,0);
        g.addEdge(5,0);
                
       g.topo_sort();
     
       
	}
}
