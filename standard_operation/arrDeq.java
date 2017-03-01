package queue;
import java.util.LinkedList;
import java.util.Queue;

import java.util.Scanner;
interface Queue1<E>
{
	boolean add(E e);
	boolean offer(E e);
	E remove();
	E poll();
	E element();
	E peek();
}
class ArrayDeque<E> implements Queue1<E>
{
	static int head;
	static int tail;
	static int defaultSize;
	Object[]arr;

	public ArrayDeque()
	{
		defaultSize=8;
		arr=new Object[defaultSize];
	}	
	public ArrayDeque(int n)
	{
		arr=new Object[n];
	}
	private void doubleCapacity()
	{
		System.out.println("called for doubleCapacity");
		int p=head;
		int n=arr.length;
		int r=n-p;
		int newCapacity=n<<1;
		Object[]a=new Object[newCapacity];
		System.arraycopy(arr,p,a,0,r);
		System.arraycopy(arr,0,a,r,p);
		arr=a;
		head=0;
		tail=n;
		
	}

	public void addFirst(E obj)
	{
		if(obj==null)
			throw new NullPointerException();
		
		head=(head-1)& (arr.length-1);
		arr[head]=obj;
		if(head==tail)
			doubleCapacity();
	}
	public void addLast(E obj)
	{
		if(obj==null)
			throw new NullPointerException();

		arr[tail]=obj;
		tail=(tail+1)&(arr.length-1);

		if(tail==head)
			doubleCapacity();
	}
	public E removeFirst()
	{
		
		
		if(isEmpty())
		{
			System.out.println("empty");
			return null;
		}
		else
		{
		    E result=(E)arr[head];
		    arr[head]=null;
		    head=(head+1)&(arr.length-1);
		    return result;
		}
	}
	public E removeLast()
	{
		if(isEmpty())
		{
			System.out.println("empty");
			return null;
		}
		else
		{
		   int t=(tail-1)&(arr.length-1);
		   E result=(E)arr[t];
		   arr[t]=null;
		   tail=t;
		   return result;
		}
	}
	public E getFirst()
	{
		if(isEmpty())
		{
			System.out.println("empty");
			return null;
		}
		else
		{
			E e=(E)arr[head];
			return e;
		}
	}
	public E getLast()
	{
		if(isEmpty())
		{
			System.out.println("empty");
			return null;
		}
	
		E e=(E)arr[(tail-1)&(arr.length-1)];
/*		if(e==null)
		{
			System.out.println("inside null");
			try
			{
			throw new NullPointerException();
			}
			catch(NullPointerException e1)
			{
				System.out.println(e);
			}
		}*/
		return e;
	}
	public int size() 
	{
		return (tail - head) & (arr.length - 1);
	}
	public boolean isEmpty()
	{
		return head==tail;
	}
	public String toString()
	{
		displayDeque();
		return "";
	}
	public void displayDeque()
	{
		if(isEmpty())
		{
			System.out.println("nothing to display");
		}
		else
		{
			//tail always point to one more index
			
			for(int i=head;i!=((tail)&(arr.length-1));)
			{
				System.out.print("for i="+i+" ");
				System.out.print(arr[i]+" ");
				i=(i+1)&(arr.length-1);
			}
			//System.out.println(head+" "+tail);
		}
	}
	//-----------------------------------------------//
	// functions defined for Queue
	//add rear, delete front
	public boolean add(E e)
	{
		addLast(e);
		return true;
	}
	public boolean offer(E e)
	{
		addLast(e);
		return true;
	}
	public E remove()
	{
		E temp=removeFirst();
		if(temp==null)
			System.out.println("not enough elements");
		else
			System.out.println("removed="+temp);
		return temp;
	}
	public E poll()
	{
		E temp=removeFirst();
		if(temp==null)
			System.out.println("not enough elements");
		else
			System.out.println("removed="+temp);
		return temp;
	}
	public E element()
	{
		E temp=getFirst();
		if(temp==null)
			System.out.println("not enough elements");
		else
			System.out.println("front element="+temp);
		return temp;
	}
	public E peek()
	{
		E temp=getFirst();
		if(temp==null)
			System.out.println("not enough elements");
		else
			System.out.println("front element="+temp);
		return temp;
	}
//------------------------------------------------//
	
}

public class arrDeq
{
	public static void main(String[]args)
	{
		Scanner sc=new Scanner(System.in);
		ArrayDeque<Integer> deq=new ArrayDeque<Integer>();
		System.out.println("1.addFirst \n2.addLast \n3.removeFirst\n"
				+ "4.removeLast\n5.getFirstt\n6.getLast\n7.display Queue\n"
				+ "8.size\n9.exit");
		char ch='1';
		while(ch!='9')
		{
			ch=sc.next().charAt(0);
			switch(ch)
			{
				case '1':
				{
					deq.addFirst(sc.nextInt());
					break;
				}
				case '2':
				{
					deq.addLast(sc.nextInt());
					break;
				}
				case '3':
				{
					Integer a=deq.removeFirst();
					//if(a.equals(null))
					if(a==null)
						System.out.println("null returned");
					else
						System.out.println("returned="+(int)a);
					break;
				}
				case '4':
				{
					
					Integer a=deq.removeLast();
					//if(a.equals(null))
					if(a==null)
						System.out.println("null returned");
					else
						System.out.println("returned="+(int)a);
					break;
				}
				case '5':
				{
					Integer a=deq.getFirst();
					//if(a.equals(null))
					if(a==null)
						System.out.println("null returned");
					else
						System.out.println("returned="+(int)a);
					
					break;
				}
				case '6':				
				{
					Integer a=deq.getLast();
					//if(a.equals(null))
					if(a==null)
						System.out.println("null returned");
					else
						System.out.println("returned="+(int)a);
					break;
				}
				case '7':
				{
					deq.displayDeque();
					break;
				}
				case '8':
				{
					int n=deq.size();
					System.out.println(n);
					break;
				}
				case '9': 
				{
					break;
				}
				
			}
		}
		
		System.out.println("exit");
		
		//own interface implementaion of queue using circular array
		Queue1<String> li=new ArrayDeque<String>();
		li.add("lokender");
		li.add("singh");
		li.add("rawat");
		li.add("hello");
		li.add("world");
		li.add("welcome");
		li.add("to");
		li.add("the");
		li.add("world");
		li.add("of");
		//even is you have no toString method for interface, but still you can use
		//function toString for ArrayDequeu class
		System.out.println(li);
		li.remove();
		li.remove();li.remove();
		li.remove();li.remove();
		li.remove();li.remove();
		li.remove();li.remove();
		li.remove();li.remove();
		System.out.println(li);
		
		
		
		//linked list way of Queue
		Queue <Integer>q=new LinkedList<Integer>();
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(4);
		q.add(5);
		System.out.println(q);
		int a=q.peek();
		System.out.println("head is="+a);
		System.out.println(q.poll());
		System.out.println(q);
		                                
		
		

	}
}