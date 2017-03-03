

class Node
{
 public int adata;
 public int bdata;
 public String str;
 public Node next;
 public Node prev;
 
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
	public static int size;
	
	DoubleLinkList()
	{
		head=null;
		tail=null;
	}
	
	public void insertBeg(int a,int b,String s)
	{
		Node newNode=new Node(a,b,s);
		size++;
		if(isEmpty())
		{
			head=tail=newNode;
		}
		else
		{
		   newNode.next=head;
		   head.prev=newNode;
		   head=newNode;
		}
	}
	public Node getRefNode(int pos)
	{
		
		Node curr=null;
		if(pos<=(size/2))
		{
			
			int currPos=1;
			curr=head;
			while(currPos!=pos)
			{
				curr=curr.next;
				currPos++;
			}
		}
		else
		{
			
			int currPos=size;
			curr=tail;
			
			while(currPos!=pos)
			{
			   curr=curr.prev;
			   currPos--;
			}
		}
		
		return curr;
	}
	public void insertBefore(Node refNode,Node newNode)
	{
		//refNode can be first, somewhere , or end

		
		newNode.prev=refNode.prev;

		if(head==refNode)
		{
			newNode.next=refNode;
			refNode.prev=newNode;
			head=newNode;
			size++;

		}
		else
		{
			newNode.prev.next=newNode;
			refNode.prev=newNode;
			newNode.next=refNode;
			size++;
		}

	}
	//provide a refNode to delete
	public void deleteRefNode(Node refNode)
	{
		if(refNode.prev==null)
		{
			refNode.next.prev=refNode.prev;
			head=refNode.next;
		}
		else if(refNode.next=null)
		{
			refNode.prev.next=refNode.next;
			tail=refNode.prev;
		}
		else
		{
			refNode.prev.next=refNode.next;
			refNode.next.prev=refNode.prev;
		}


	}

	public void insertAfter(Node refNode,Node newNode)
	{
		//refNode can be first, somewhere , or end

		
		newNode.next=refNode.next;

		if(head==refNode)
		{
			newNode.prev=refNode;
			refNode.next.prev=newNode;
			head=newNode;
			size++;

		}
		else
		{
			newNode.prev.next=newNode;
			refNode.prev=newNode;
			newNode.next=refNode;
			size++;
		}

	}
	public void deletePos(Node refNode)
	{
	    if(refNode.prev==null)//firstnode
	    {
	    	refNode.next.prev=refNode.prev;
	    	head=refNode.next;	

	    }
	    else if(refNode.next==null)//lastNode
	    {
	    	refNode.prev.next=refNode.next;
	    	tail=refNode.prev;
	    }
	    else
	    {
	    	refNode.prev.next=refNode.next;
	    	refNode.next.prev=refNode.prev;
	    }
     
	}

	public void insertEnd(int a,int b,String s)
	{
		Node newNode=new Node(a,b,s);
		size++;
        if(isEmpty())
        {
        	head=tail=newNode;
        }
        else
        {
        	tail.next=newNode;
        	newNode.prev=tail;
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
			head.prev=null;
			size--;
		}
	}
	public void deleteEnd()
	{
		if(!isEmpty())
		{
			
			Node curr=head,prev=null;
			if(curr.next==null)//only one node
			{
				head=tail=null;
			}
			else
			{
			  tail=tail.prev;
			  tail.next=null;
			}

			size--;
			
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
	public void displayListRev()
	{
		Node temp=tail;
		while(temp!=null)
		{
			System.out.printf("%-5d %-5d %-10s",temp.adata,temp.bdata,temp.str);
			System.out.println();
			temp=temp.prev;
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
	  

	  System.out.println("current list size="+li.size);
	  System.out.println("current list in forward"+"\n");
	  li.displayList();
	  System.out.println("current list in backward"+"\n");
	  li.displayListRev();
	  

	  //insert from any position
	  int pos=2;
	  Node refNode=li.getRefNode(pos);
	  if(refNode==null)
	  {
	  	System.out.println("invalid position");
	  }
	  else
	  {
	  	Node newNode=new Node(12,32,"masterBlaster");
	  	//w.r.t refNode, insert before this node
	  	li.insertBefore(refNode,newNode);

	  }
	  System.out.println("list size ="+li.size);
	  System.out.println("after getting refNode");
	  li.displayList();


	  System.out.println("delete from beg");
	  li.deleteBeg();
	  
	  
	  
	  
	  System.out.println("new contents of the list");
	  li.displayList();

	  
	  li.insertEnd(11,23,"akash");
	  li.insertEnd(12,24,"abhilash");
	  System.out.println("after insertion of two more at the end");
	  li.displayList();
	  
	  System.out.println("one delete from end");
	  li.deleteEnd();
	  li.displayList();

	  System.out.println("two more delete from the beg");
	  li.deleteBeg();
	  li.deleteBeg();
	  li.displayList();
	  
	  System.out.println("two more delte from the end");
	  li.deleteEnd();
	  li.deleteEnd();
	  li.displayList();

	  System.out.println("REM OP");
	  li.deleteEnd();
	  
  }
}