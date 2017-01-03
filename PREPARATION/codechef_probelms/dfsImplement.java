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
 
    // A function used by DFS
   
    void DFSUtil(int s,boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[s] = true;
        System.out.print(s+" ");
        st[s]=tm++;
        
        // Recur for all the vertices adjacent to this vertex
        Iterator<Node> i = adj[s].listIterator();
        while (i.hasNext())
        {
        	Node temp=i.next();
            int n = temp.dest; //find the dest
            
            //store the edge type for u,v which is tree for dfs
            
            //i.next().type='T';
            if (!visited[n])
            {
            	temp.type=edgeType.TREE;
                DFSUtil(n,visited);
            }
        }
        
        //returning
        ft[s]=tm++;
    }
    
    
 
    // The function to do DFS traversal. It uses recursive DFSUtil()
    void DFS()
    {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];
        int parent[]=new int[V];
        
 
        // Call the recursive helper function to print DFS traversal
        // starting from all vertices one by one
        for (int i=0; i<V; ++i)
            if (visited[i] == false)
                DFSUtil(i, visited);
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
 
   
}

public class dfs_imple
{
	static void prL()
	{
		System.out.println();
	}
	 public static void main(String args[])
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
	        
	        prL();
	        g.displayGraph();
	        
	        prL();
	 
	        g.displayFullGraph();
	        
	        prL();
	        System.out.println("DFS TRAVERSAL ON ABOVE GRAPH");
	        prL();
	        g.DFS();
	        prL();
	        System.out.println("AFTER DFS ON THE GRAPH");
	        g.displayFullGraph();
	        
	        
	        
	        
	        prL();
	        prL();
	        
	        g.displayTime();
	        
	        g.classifyEdge();
	        
	        g.displayFullGraph();
	    }
}




/*
           Graph g = new Graph(4);
	 
	        g.addEdge(0, 1);
	        g.addEdge(0, 2);
	        g.addEdge(1, 2);
	        g.addEdge(2, 0);
	        g.addEdge(2, 3);
	        g.addEdge(3, 3);
	 
 */
