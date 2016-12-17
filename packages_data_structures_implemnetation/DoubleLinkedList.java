package doubleLinkList;
import node.*;

public class DoubleLinkedList
{
	private Node head;
	private Node tail;
	
	public DoubleLinkedList()
	{
		head=null;
		tail=null;
	}
	
	public void insertBeg(int a,int b,String s)
	{
		Node newNode=new Node(a,b,s);
		if(isEmpty())
		{
			head=tail=newNode;
		}
		else
		{
		   newNode.next=head;
		   head=newNode;
		}
	}
	public void insertEnd(int a,int b,String s)
	{
		Node newNode=new Node(a,b,s);
        if(isEmpty())
        {
        	head=tail=newNode;
        }
        else
        {
        	tail.next=newNode;
        	tail=newNode;
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
}
