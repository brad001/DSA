import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Graph
{
  private int V;
  private Stack st;
  private Queue Q;  
  private LinkedList<Integer>adj[];
    
  
  Graph(int v)      
  {
      V=v;
      st=new Stack();
      Q=new LinkedList();
      adj=new LinkedList[V];
      for(int i=0;i<V;i++)
      adj[i]=new LinkedList();
  }
  void addEdge(int v,int w)
  {
    adj[v].add(w);
    //undirected graph
     adj[w].add(v);
  }
  void dfs(int s)
  {
     boolean[] visited=new boolean[V];
     int[]parent=new int[V]; 
     visited[s]=true;
     st.push(new Integer(s));
      //System.out.println("visit "+s);
     Iterator<Integer>it;
     
     while(!st.isEmpty()) 
     {
    
         int v=(Integer)st.peek();
         System.out.println("visit "+v);
        
         st.pop();
         it=adj[v].listIterator();
         while(it.hasNext())
         {
             int n=it.next();
            if(!visited[n])
            {
              visited[n]=true;
              st.push(new Integer(n));
                parent[n]=v;
            }
         }
     }
      System.out.println("source:destination");
      for(int i=0;i<V;i++)
      {
         System.out.println(parent[i]+"     "+i);
            
      }
      System.out.println();
  }
    //to pass the updated boolean array we need this extra function
  void bfs_recur(int s)
  {
        boolean[]visited=new boolean[V];
        int[]parent=new int[V];
   Q.add(new Integer(s));
        bfs_re(s,visited,parent);
      System.out.println("source:destination");
      for(int i=0;i<V;i++)
      {
         System.out.println(parent[i]+"     "+i);
            
      }
      System.out.println();
  }
  void bfs_re(int s,boolean[] visited,int []parent)
  {
      
      
      if(!Q.isEmpty())
      {
        int v=(Integer)Q.remove();
        System.out.println("visit "+v);
        Iterator<Integer>it=adj[v].listIterator();
        while(it.hasNext())
        {
         int n=it.next();
         if(!visited[n])
             {
             Q.add(new Integer(n));
             parent[n]=v;
             }
        }
          if(!Q.isEmpty())
         bfs_re((Integer)Q.element(),visited,parent);
      }
      else
          return;
  }
  void dfs_recur(int s)
  {
      boolean []visited=new boolean[V];
      int []parent=new int[V];
      dfs_re(s,visited,parent);
      System.out.println("source:destination");
      for(int i=0;i<V;i++)
      {
         System.out.println(parent[i]+"     "+i);
            
      }
      System.out.println();
  }
  void dfs_re(int s,boolean[] visited,int[]parent)
  {
      //mark it visited
      visited[s]=true;
      System.out.println("visit :"+s);
      
      Iterator<Integer>it=adj[s].listIterator();
      while(it.hasNext())
      {
        int n=it.next();
        if(!visited[n]) 
            {
            parent[n]=s;
            dfs_re(n,visited,parent);
            }
      }
  }
    
    /*
    Q=new LinkedList();
     Q.add(new Integer(vertexList[0].label));
      
     while(!Q.isEmpty())
     {
       int v1=(Integer)Q.remove();    //get the head
         int v2;
       while((v2=getAdjUnvisitedVertex(v1))!=-1)//add all to the queue
       {
             System.out.println("visit : "+(v2+1));
             vertexList[v2].wasVisited=true;
             Q.add(new Integer(v2));
       }
         
     }
    */
    void bfs(int s)
    {
        boolean []visited=new boolean[V];
        int[]parent=new int[V];
        Q.add(new Integer(s));
        visited[s]=true;
        while(!Q.isEmpty())
        {
          int v1=(Integer)Q.remove();
          System.out.println("visit :"+v1);  
          Iterator<Integer>it=adj[v1].listIterator(); 
          while(it.hasNext())
          {
            int n=it.next();  
              parent[n]=v1;
            //System.out.println("visit :"+n);
            if(visited[n]==false)
            {
            visited[n]=true;
            Q.add(new Integer(n));  
            }
          }
         
            
        }
        System.out.println("source:destination");
      for(int i=0;i<V;i++)
      {
         System.out.println(parent[i]+"     "+i);
            
      }
      System.out.println();
        
           
    }
}

public class Solution 
{

    public static void main(String[] args) 
    {
      
        /*
        Graph g=new Graph(9);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,3);
        g.addEdge(2,4);
        g.addEdge(0,5);
        g.addEdge(5,6);
        g.addEdge(6,7);
        g.addEdge(0,8);       
        */
        
        Graph g=new Graph(5);
        g.addEdge(0,3);
        g.addEdge(3,4);
        g.addEdge(4,0);
        g.addEdge(1,0);
        g.addEdge(2,1);
        g.addEdge(0,2);
        
        
        System.out.println("dfs iterative");
        g.dfs(0);
        System.out.println("dfs recursive");
        g.dfs_recur(0);
        
        System.out.println("bfs iterative");
        g.bfs(0);
        System.out.println("bfs recursvie");
        g.bfs_recur(0);

    }
}
