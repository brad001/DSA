import doubleLinkList.*;

public class DoubleLinkedListApp 
{
  public static void main(String args[])
  {
	  DoubleLinkedList li=new DoubleLinkedList();
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
