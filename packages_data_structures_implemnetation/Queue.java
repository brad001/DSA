package queue;
import doubleLinkList.*;

public class Queue
{
	private DoubleLinkedList li;
	
	public Queue()
	{
		li=new DoubleLinkedList();
	}
	public void enque(int a,int b,String s)//inserts at the end
	{
		li.insertEnd(a,b,s);
	}
	public void deque()//delete from the front
	{
		li.deleteBeg();
	}
	public void displayQueue()
	{
		//display from front to end
		System.out.println("from front to end");
		li.displayList();
	}
	
}
