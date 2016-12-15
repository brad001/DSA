
class Stack
{
	private LinkList li;
	public Stack()
	{
		li=new LinkList();
	}
	public void push(int a, int b,String s)
	{
		li.insertBeg(a, b, s);
	}
	public void pop()
	{
		li.deleteBeg();
	}
	public void displayStack()
	{
		System.out.println("from top to bottom");
		li.displayList();
	}
}
public class StackApp 
{
  public static void main(String[]args)
  {
	  Stack st=new Stack();
	  st.push(1,22,"lokender");
	  st.push(2, 34, "ram singh");
	  st.push(3, 21, "hema");
	  st.push(4, 23, "margi");
	  
	  st.displayStack();
	  st.pop();
	  System.out.println("after one pop");
	  st.displayStack();
			  
  }
}
