package graph;
import linkList.*;
import node.*;

public class Graph
{
	private int V;
	private LinkedList[]li;
	
	public Graph(int v)
	{
		V=v;
		li=new LinkedList[V];
		//each li[i] is a link list of nodes, so initialise them
		for(int i=0;i<V;i++)
		{
			li[i]=new LinkedList();
			//li[i].head=null;
		}
		
	}
	public void addEdge(int src,int dest,int wt)
	{
		
		li[src].insertBeg(dest, wt);
		//li[dest].insertBeg(src,wt);
	}
	public void displayGraph()
	{
		for(int i=0;i<V;i++)
		{
			//for each list
			Node temp=li[i].head;
			System.out.printf("src=%-4d",i);
			while(temp!=null)
			{
				//System.out.printf("->%-3d",temp.dest);
				System.out.printf("->%-3d",temp.adata);  //adata corresponds to dest
				temp=temp.next;
			}
			System.out.println();
		}
	}
}
