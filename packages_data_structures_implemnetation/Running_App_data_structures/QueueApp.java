import queue.*;

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
