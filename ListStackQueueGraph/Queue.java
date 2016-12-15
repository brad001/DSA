
class Queue
{
	private DoubleLinkList li;
	
	public Queue()
	{
		li=new DoubleLinkList();
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
public class QueueApp 
{
  public static void main(String[]args)
  {
	  Queue Q=new Queue();
	  Q.enque(1, 22, "lokender");
	  Q.enque(2, 24, "margi");
	  Q.enque(3, 22, "abhilash");
	  Q.enque(4, 21, "mayank");
	  
	  Q.displayQueue();
	  Q.deque();
	  System.out.println("after one deque");
	  Q.displayQueue();
	  Q.deque();
	  Q.displayQueue();
	  Q.deque();
	  Q.displayQueue();
	  Q.deque();
	  Q.displayQueue();
	  Q.deque();
	  Q.displayQueue();
	  Q.deque();
	  Q.displayQueue();
  }
}
