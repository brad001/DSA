
package topo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;



enum edgeType
{CROSS,BACK,TREE,FORWARD,NONE}

class Node
{
	int dest;
	int wt;
	edgeType type;//type of edge (forward , back, cross, tree)
	Node next;
	public Node(int dest)
	{
		this.dest=dest;
		this.type=edgeType.NONE;//initialize all to x
	}
	public Node(int dest,int wt)
	{
		this.dest=dest;
		this.wt=wt;
		this.type=edgeType.NONE;
		
	}
}
class Graph
{
    private int V;   // No. of vertices
 
    // Array  of lists for Adjacency List Representation
    private LinkedList<Node> adj[];
    
    private Stack<Integer> stk;
    //to store the start and finish time
    static int tm;
    //static int ftTime;
    int st[];
    int ft[];
 
    // Constructor
    Graph(int v)
    {
    	st=new int[v];
    	ft=new int[v];
    	tm=1;
        V = v;
        stk=new Stack();
        adj = new LinkedList[v];
        for (int i=0; i<v; i++)
            adj[i] = new LinkedList();
        
    }
 
    //Function to add an edge into the graph
    void addEdge(int u, int v,int wt)
    {
    	
    }
    //wt=0 for each edge
    void addEdge(int u, int v)
    {
    	Node temp=new Node(v);
        adj[u].add(temp);  // Add w to v's list.
    }
 
    void displayGraph()
    {
    	System.out.println("graph with only vertices".toUpperCase());
    	for(int i=0;i<V;i++)
    	{
    		System.out.print(i+"->");
    		Iterator<Node> it=adj[i].listIterator();
    		while(it.hasNext())
    		{
    			System.out.print((Integer)it.next().dest+" ");
    		}
    		System.out.println();
    	}
    }
    void displayFullGraph()
    {
    	System.out.println("graph with dest, weight, EdgeType ".toUpperCase());
    	for(int i=0;i<V;i++)
    	{
    		System.out.print(i+"->");
    		Iterator<Node> it=adj[i].listIterator();
    		while(it.hasNext())
    		{
    			Node temp=it.next();
    			System.out.print(temp.dest+" "+temp.wt+" "+temp.type+" ,");
    		}
    		System.out.println();
    	}
    }
    void displayTime()
    {
    	System.out.println("the start and finish time are:");
    	for(int i=0;i<V;i++)
    	{
    		System.out.print(st[i]+" ");
    	}
    	System.out.println();
    	for(int i=0;i<V;i++)
    	{
    		System.out.print(ft[i]+" ");
    	}
    	System.out.println("");
    }
    void classifyEdge()
    {
    	for(int i=0;i<V;i++)
    	{
    		int u=i;
    		Iterator<Node> it=adj[i].listIterator();
    		while(it.hasNext())
    		{
    			Node temp=it.next();
    		  int v=temp.dest;
    		  if(st[u]<st[v] &&ft[u]>ft[v] &&  temp.type!=edgeType.TREE) //true for both forward and tree, and tree is already stored
    		  {
    			 temp.type=edgeType.FORWARD;
    		  }
    		  else if(st[u]>st[v] &&ft[u]<ft[v])
    		  {
    			  temp.type=edgeType.BACK;
    			  //System.out.println(u+" "+v+" forms a back edge");
    		  }
    		  /*else if(st[u]<st[v] &&ft[u]<ft[v])
    		  {
    			  System.out.println(u+" "+v+" forms a forward edge");
    		  }
    		  */
    		  else if(st[u]>st[v] && ft[u]>ft[v])
    		  {
    			  temp.type=edgeType.CROSS;
    			  //System.out.println(u+" "+v+" forms a cross edge");
    		  }
    		  else
    		  {
    			  
    		  }
    		  	
    		}
    		
    	}
    	
    }
    void topo_sort_rec(int s,boolean[]visited,int[]d,int[]f,Stack st)
    {
        
        visited[s]=true;
        d[s]=tm++;
        
        Iterator<Node>it=adj[s].listIterator();
        while(it.hasNext())
        {
        	Node temp=it.next();
          int adjNode=temp.dest;
          if(!visited[adjNode])
              topo_sort_rec(adjNode,visited,d,f,st);
        }
        
        f[s]=tm++;
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

public class topologicalSort 
{

	public static void main(String[]args)
	{
		
		Graph g=new Graph(7);
        
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        //rev
        //g.addEdge(2, 5);
        g.addEdge(5, 2);
        
        g.addEdge(3, 4);
        g.addEdge(3, 2);
        g.addEdge(3, 6);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(6, 5);
        g.addEdge(1, 5);
        g.addEdge(6, 1);
        
        
        g.displayFullGraph();
        
        g.topo_sort();
        
        
		
	}
}
