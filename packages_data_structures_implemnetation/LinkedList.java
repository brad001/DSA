package linkList;
import node.Node;

public class LinkedList
{
	public Node head;
	
	public LinkedList()
	{
		head=null;
	}
	
	public void insertBeg(int a,int b,String s)
	{
		Node newNode=new Node(a,b,s);
		newNode.next=head;
		head=newNode;
	}
	public void insertBeg(int a,int b)
	{
		Node newNode=new Node(a,b);
		newNode.next=head;
		head=newNode;
	}
	public void insertEnd(int a,int b,String s)
	{
		Node newNode=new Node(a,b,s);
		
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
			System.out.printf("%-5d %-5d %-10s",temp.adata,temp.bdata,temp.str);
			System.out.println();
			temp=temp.next;
		}
	}
	public String displayList1(Object obj)
	{
		
		//System.out.println();
		return obj.toString(); //prints the"class_name@hascodeofstring"
	}
}
