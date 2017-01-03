package codechef;

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
 
    static final int INF=Integer.MAX_VALUE;
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
    void bfs(int s)
    {
    	boolean[]visited=new boolean[V];
    	LinkedList<Integer> Queue=new LinkedList<Integer>();
    	
    	visited[s]=true;
    	
    	Queue.add(s);
    	
    	
    	while(!Queue.isEmpty())
    	{
    		int n=Queue.removeFirst();
    		System.out.print(n+" ");
    		
    		Iterator<Node> iter=adj[n].listIterator();
    		
    		while(iter.hasNext())
    		{
    			Node temp=iter.next();
    			if(!visited[temp.dest])
    			{
    				visited[temp.dest]=true;
    				Queue.addLast(temp.dest);
    				
    				
    			
    		}
    		
    		
    		
    	}
    	
    
    }

}

public class bfs_imple 
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
      
      g.bfs(0);
      
     
     
      g.bfs(2);
	  
  }
}
