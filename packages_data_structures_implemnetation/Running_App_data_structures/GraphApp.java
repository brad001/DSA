import graph.*;

public class GraphApp 
{
 public static void main(String[]args)
 {
	 Graph g=new Graph(5);
	 g.addEdge(0, 1, 5);
	 g.addEdge(1, 2, 2);
	 g.addEdge(2, 3, -3);
	 g.addEdge(3,4,6);
	 g.addEdge(2,4,4);
	 g.addEdge(3,1,9);
	 g.addEdge(0,4,1);
	 
	 g.displayGraph();
	 
	 //g.dfs(4);
	 
 }
}

