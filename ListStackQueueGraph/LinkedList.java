class Node
{
 public int adata;
 public int bdata;
 public String str;
 public Node next;
 
   public Node(int a,int b,String s)
   {
	   adata=a;
	   bdata=b;
	   str=s;
   }
}

class LinkList
{
	public Node head;
	
	LinkList()
	{
		head=null;
	}
	
	public void insertBeg(int a,int b,String s)
	{
		Node newNode=new Node(a,b,s);
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
}

public class LinkLinstApp 
{
  public static void main(String args[])
  {
	  LinkList li=new LinkList();
	  li.insertBeg(1, 23, "lokender");
	  li.insertBeg(2,24,"amol");
	  
	  li.insertBeg(4,21,"aaksah");
	  li.insertBeg(5,24,"subham");
	  li.insertBeg(6,23,"mayank");
	  
	  
	  li.displayList();
	  System.out.println("delete from beg");
	  li.deleteBeg();
	  
	  
	  
	  
	  System.out.println("new contents of the list");
	  
	  
	  li.insertEnd(12,24,"abhilash");
	  
	  li.displayList();
	  li.insertEnd(13,24,"naveen");
	  System.out.println("delete from end");
	  li.deleteEnd();
	  
	  
	  li.displayList();
	  li.deleteBeg();
	  li.deleteBeg();
	  li.displayList();
	  
	  li.deleteEnd();
	  li.deleteEnd();
	  li.displayList();
	  li.deleteEnd();
	  System.out.println("rem");
	  li.displayList();
	  li.deleteEnd();
	  li.displayList();
	  System.out.println("rem");
	  li.deleteEnd();
	  li.deleteEnd();
	  System.out.println("rem");
	  li.displayList();
	  
  }
}
