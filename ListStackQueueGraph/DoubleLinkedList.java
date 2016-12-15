
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
   
   public void displayNode()
   {
	   System.out.println("the node has values ");
	   System.out.println("adata ="+adata+" , bdata="+bdata+",  str="+str);
   }
}

class DoubleLinkList
{
	private Node head;
	private Node tail;
	
	DoubleLinkList()
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

public class DoubleLinkListApp 
{
  public static void main(String args[])
  {
	  DoubleLinkList li=new DoubleLinkList();
	  li.insertBeg(1, 23, "lokender");
	  li.insertBeg(2,24,"amol");
	  
	  li.insertBeg(5,24,"subham");
	  li.insertBeg(6,23,"mayank");
	  
	  
	  li.displayList();
	  System.out.println("delete from beg");
	  li.deleteBeg();
	  
	  
	  
	  
	  System.out.println("new contents of the list");
	  
	  li.insertEnd(11,23,"akash");
	  li.insertEnd(12,24,"abhilash");
	  
	  li.displayList();
	  
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
