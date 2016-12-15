package graph;

//import java.util.Stack;


class Node
{
	public int dest;
	public int wt;
	public Node next;
	public Node(int d,int w)
	{
		dest=d;
		wt=w;
	}
	
}
class LinkList
{
	public Node head;

	LinkList()
	{
		head=null;
	}
	
	public void insertBeg(int dest,int wt)
	{
		Node newNode=new Node(dest,wt);
		newNode.next=head;
		head=newNode;
	}
	
	public void insertEnd(int dest,int wt)
	{
		Node newNode=new Node(dest,wt);
		
		if(head==null)
		{
		  head=newNode;	
		}
		else
		{
			Node temp=head;
			while(temp.next!=null)
			{
				temp=temp.next;
			}
			temp.next=newNode; //newNode.next is already null when created
		}
	}
	public boolean isEmpty()
	{
	  return (head==null);
	}
	public void deleteBeg()
	{
		if(!isEmpty())
		{
			head=head.next;
		}
	}
	public void deleteEnd()
	{
		if(!isEmpty())
		{
			
			Node curr=head,prev=null;
			if(curr.next==null)//only one node
			{
				head=null;
			}
			else
			{
			  while(curr.next!=null)
			  { 
				  prev=curr;
				  curr=curr.next;
			  }
			  prev.next=null;
			}
			
		}
	}
	public void displayList()
	{
		Node temp=head;
		while(temp!=null)
		{
			System.out.printf("%-5d %-5d",temp.dest,temp.wt);
			System.out.println();
			temp=temp.next;
		}
	}
}

class NodeSt
{
	public int val;
	public NodeSt next;
	public NodeSt(int data)
	{
		val=data;
	}
	
}
class Stack
{
	private NodeSt head;
	public Stack()
	{
		head=null;
	}
	public boolean isEmpty()
	{
		return (head==null);
	}
	public void push(int data)
	{
		NodeSt newNode=new NodeSt(data);
		newNode.next=head;
		head=newNode;
	}
	public int peek()
	{
		return head.val;
	}
	public void pop()
	{
		head=head.next;
	}
}
class Graph
{
	private int V;
	private LinkList[]li;
	
	public Graph(int v)
	{
		V=v;
		li=new LinkList[V];
		//each li[i] is a link list of nodes, so initialise them
		for(int i=0;i<V;i++)
		{
			li[i]=new LinkList();
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
				System.out.printf("->%-3d",temp.dest);
				temp=temp.next;
			}
			System.out.println();
		}
	}
	
	public void dfs(int source)
	{
	  Stack st=new Stack();
	  boolean []visited=new boolean[V];
	  int  []parent=new int[V]; 
	  
	  visited[source]=true;
	  parent[source]=-1;
	  st.push(new Integer(source));
	  
	  while(!st.isEmpty())
	  {
		  //to store the parent, who explored
		  int top=(int)st.peek();
		  System.out.println("visit="+top);
		  //finally every single will come out from stack
		  st.pop();
		  
		  Node temp=li[top].head;
		  while(temp!=null)
		  {
			  //if not visited
			  if(!visited[temp.dest])
			  {
				  visited[temp.dest]=true;
				  st.push(new Integer(temp.dest));
       			  parent[temp.dest]=top;
			  }
			  temp=temp.next;
		  }//until list empty=> adjacent
	  }
	  
	  //exploraration
	  for(int i=0;i<V;i++)
	  {
		  System.out.println(i+" explored by "+parent[i]);
	  }
	} //dfs end
	
}

public class BuildGraph 
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
	 
	 g.dfs(4);
	 
 }
}

