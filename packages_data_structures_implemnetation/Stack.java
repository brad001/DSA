package stack;
import linkList.*;
//import node.*;

public class Stack
{
	private LinkedList li;
	public Stack()
	{
		li=new LinkedList();
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
