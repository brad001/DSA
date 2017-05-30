/*
	Like Stack, Queue is a linear structure which follows a particular order 
	in which the operations are performed. The order is First In First Out (FIFO).  
	A good example of queue is any queue of consumers for a resource where 
	the consumer that came first is served first.
	The difference between stacks and queues is in removing. 
	In a stack we remove the item the most recently added; in a queue, 
	we remove the item the least recently added.

	Applications of Queue:
	Queue is used when things don’t have to be processed immediatly, 
	but have to be processed in First InFirst Out order like Breadth First Search. 
	This property of Queue makes it also useful in following kind of scenarios.

	1) When a resource is shared among multiple consumers. 
	Examples include CPU scheduling, Disk Scheduling.
	2) When data is transferred asynchronously 
	(data not necessarily received at same rate as sent) between two processes. 
	Examples include IO Buffers, pipes, file IO, etc.
*/

import java.util.*;
interface MyQueue<E>
{
	void add(E d);
	E remove();
	E element();
	//internlly queue interface extends collection so size() and isEmpty() is from collection
	int size();
	boolean isEmpty();

}
class MyLinkedList<E> implements MyQueue<E>
{
	/*
		node stucture of a linked list
		{data,nextPointer}
	*/
	class Node<E>
	{
		E data;
		Node next;

		Node(E data)
		{
			this.data=data;
			next=null;
		}
		Node(){}
	}

	//head points to the first node of the list
	//last points to the last node of the list
	Node head=null;
	Node last=null;
	int count=0; //total nodes in the list


/**********************************BASIC FUNCTIONS OF LL**************************************************/

	//insertion at the end of the list.**** O(1)
	/*
		as you are maintaining last pointer to the list,update 
		last.next=n;
		if head is null(means list is empty) 
		update both head and last pointers 
	*/
	public void add(E d) //add at the end of the list
	{
		Node n=new Node(d);
		if(head!=null)
		{
			last.next=n;
			last=n;
			count++;
		}
		else
		{
			head=n;
			last=n;
			count++;
		}
	}



	//insertion at the given index**** O(i)
	/*
		2 cases:

		case 1: insert at the 0th index!
			same as insertion at th front.
		case 2: not 0th index!
			case 2a: at the end of the list
				same as insert at the end.
			case 2b: inbetween
				if index given is i,
				then go till (i-1)
				n.next=before.next;
				before.next=n;
	*/
	void add(int index,E d)// add at given index
	{
		if(index>0)
		{
			if(index<count)
			{
				Node before=head;
				Node n=new Node(d);
				for(int i=0;i<index-1;i++)
				{
					before=before.next;
				}
				n.next=before.next;
				before.next=n;
				count++;
			}
			else if(index==count)
			{
				Node n=new Node(d);
				last.next=n;
				last=n;
				count++;
			}
		}
		else if(index==0)
		{
			Node n=new Node(d);
			n.next=head;
			head=n;
			count++;
		}
	}
	void addFirst(E d)
	{
		add(0,d);
	}
	void addLast(E d)
	{
		add(d);
	}


	//get th data at the given index**** O(i)
	/*
		traverse till the given index of the list
		return current.next;
	*/
	E get(int index) //get the data from the given index node 
	{
		if(head==null)
			throw new RuntimeException("queue is empty");
		Node target=head;
		for(int i=0;i<index;i++)
		{
			target=target.next;
		}
		return (E)target.data;
	}
	public E element()
	 {
	 	return (E)get(0);
	 }


	//remove node at the given index**** O(i)
	/*
		case 1:
			if index==0
			upate head
		case 2:
			if index==size-1
			traverse till size-2th node and update its next to null
			update last
		case 3:
			if inbetween
			if index is i,traverse till i-1,update its next to i.next
	*/
	void remove(int index)
	{
		if(index==0)
		{
			head=head.next;
			count--;
		}
		else if(index>0 && index<count-1)
		{
			Node before=head;
			for(int i=0;i<index-1;i++)
			{
				before=before.next;
			}
			Node current=before.next;
			before.next=current.next;
			count--;
		}
		else if(index==count-1)
		{
			Node before=head;
			for(int i=0;i<index-1;i++)
			{
				before=before.next;
			}
			before.next=null;
			last=before;
			count--;
		}
	}
	public E remove()
	{
		if(head==null)
			throw new RuntimeException("queue is empty");
		E x=(E)head.data;
		head=head.next;
		count--;
		return x;
	}


	//remove the node of the list given as reference**** O(1)
	/*
		if the given node is last node of the list this method cant be used!
		you can not have accese to the previous node of given node
		but you can access next node
		so copy data and next of next node in given node 
		so actually next node is now not linked but actually given node is deleted.
	*/
	void removeNodeGivenAsReference(Node n)
	{
		if(n.next!=null)
		{
			n.data=n.next.data;
			n.next=n.next.next;
		}
	}


	//set or say update the data of the node at given index**** O(i)
	/*
		if given index is i,go till i,
		update Data.
	*/
	void set(int index,int d)
	{
		Node current=head;
		for(int i=0;i<index;i++)
		{
			current=current.next;
		}
		current.data=d;
	}


	//compute size of the list**** O(n)
	/*
		recursive method:
		add 1 for each node and call this method again for the next node 
	*/
	public int size()
	{
		int x=size(head);
		return x;
	}
	public int size(Node n)
	{
		if(n==null)
			return 0;
		else return(1+ size(n.next));
	}
	public boolean isEmpty()
	{
		if(head==null)
			return true;
		else
			return false;
	}

	public String toString()
	{
		if(size()==0)
			return "[ ]";
		StringBuilder out=new StringBuilder("[");
		Node current=head;
		while(current.next!=null)
		{
			out.append(current.data+", ");
			current=current.next;
		}
		out.append(current.data+"]");
		return out.toString();
	}
}

class UseQueue
{

/******************** BFS USING QUEUE ***********************/

/*
	IN DFS!
		PUSH,
			1.if not visited
				a)push
		POP,
			1. pop
			2. if not visited 
				a)print
				b)make visited true;
		

	IN BFS!
		PUSH,
			1.if not visited
				a)add
				b)make visited true;
		POP,
			1.pop
			2.print.
*/
	void BFS(ArrayList[] arr)
	{
		boolean[] visited=new boolean[arr.length];
		for(int i=0;i<arr.length;i++)
		{
			if(!visited[i])
				doBFS(i,visited,arr);
		}
		System.out.println("");
	}
	void doBFS(int i,boolean[] visited,ArrayList[] arr)
	{
		MyQueue<Integer> q=new MyLinkedList<Integer>();
		q.add(i);
		visited[i]=true;
		while(!q.isEmpty())
		{
			int d=(int)q.remove();
			System.out.print(d+" ");
			for(int j=0;j<arr[i].size();j++)
			{
				int node=(int)arr[i].get(j);
				if(!visited[node])
				{
					q.add(node);
					visited[node]=true;
				}
			}
		}
	}

/********************** QUEUE USING TWO STACKS *************************/

/*
	Method 1 (By making enQueue operation costly) 
	This method makes sure that oldest entered element is always 
	at the top of stack 1, so that deQueue operation just pops from stack1. 
	To put the element at top of stack1, stack2 is used.

enQueue(q, x)
  1) While stack1 is not empty, push everything from satck1 to stack2.
  2) Push x to stack1 (assuming size of stacks is unlimited).
  3) Push everything back to stack1.

dnQueue(q)
  1) If stack1 is empty then error
  2) Pop an item from stack1 and return it

	Method 2 (By making deQueue operation costly)
	In this method, in en-queue operation, the new element is entered 
	at the top of stack1. In de-queue operation, if stack2 is empty then 
	all the elements are moved to stack2 and finally top of stack2 is returned.

enQueue(q,  x)
  1) Push x to stack1 (assuming size of stacks is unlimited).

deQueue(q)
  1) If both stacks are empty then error.
  2) If stack2 is empty
       While stack1 is not empty, push everything from stack1 to stack2.
  3) Pop the element from stack2 and return it.



  Queue can also be implemented using one user stack and one Function Call Stack. 
  Below is modified Method 2 where recursion (or Function Call Stack) is used to 
  implement queue using only one user defined stack.

enQueue(x)
  1) Push x to stack1.

deQueue:
  1) If stack1 is empty then error.
  2) If stack1 has only one element then return it.
  3) Recursively pop everything from the stack1, store the popped item 
    in a variable res,  push the res back to stack1 and return res


*/
 /****** CHECK IF GIVEN BINARY TREE IS COMPLETE OR NOT USING QUEUE **********/
 /*
	actual code is implemented in binarytree program
	when tree is complete:when you insert node in tree in top-down left-right manner
	if its not complete means you insertion is not in this manner
	so traverse from top to down in left to right direction
	if you find a node at level i which has less than 2 children(0 or 1)
		then all nodes right to that node on level i must be leaf node!!!!
		(if that node has one child than that child must also be leaf!!!!!)
		if not
			tree is not complete binary tree.
 */
 /*
boolean isCompleteBTree()
	{
		//if root is null return true
		if(root==null)
			return true;
		Queue<Node> q=new LinkedList<Node>();
		q.add(root);
		while(!q.isEmpty())
		{
			Node c=(Node)q.remove();
			//if node have two childern means till now all good so add its children to q.
			if(c.left!=null && c.right!=null)
			{
				q.add(c.left);
				q.add(c.right);
			}
			//if a node does not have left child but have right child that is tottaly wrong
			//so return directly false.
			else if(c.left==null && c.right!=null)
				return false;
			else
			{
				//if node has one left child add it to queue to check whether 
				//that child is leaf or not
				if(c.left!=null && c.right==null)
					q.add(c.left);
				while(!q.isEmpty())
				{
					c=(Node)q.remove();
					if(c.left==null && c.right==null)
						continue;
					//if not leaf means its child is inserted incorrectly so not complet
					else
						return false;
				}
			}
		}
			return true;
	}

 */

/*************** MAX OF ALL IN WINDOW OF ARRAY *************************************/

		//Sliding Window Maximum (Maximum of all subarrays of size k)
		/*
		Given an array and an integer k, find the maximum for each 
		and every contiguous subarray of size k.

		Examples:

		Input :
		arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
		k = 3
		Output :
		3 3 4 5 5 5 6


	**********	SAME FOR MINIMUM JUST CHANGE < TO > IN CODE********
*/

	void maxOfAllSubarraysWithSizeK(int[] arr,int k)
	{
		//use queue
		MyQueue<Integer> q=new MyLinkedList<Integer>();
		//for the first window when you add index of an array check and delete
		//all smaller ele then current and then insert current index in queue.
		for(int i=0;i<k;i++)
		{
			while(!q.isEmpty() && (arr[(int)q.element()]<arr[i]))
				q.remove();
			q.add(i);
		}
		//always the first ele of an queue is the index of max ele of that window
		//so print it.
		System.out.print(arr[q.element()]+" ");
		//now window will shift one element each time
		for(int j=k;j<arr.length;j++)
		{
			//in queue if the index is present which is not the part of current
			//index's  window then first remove it.
			if(!q.isEmpty() && (int)q.element()==(j-k))
				q.remove();
			//when you add index of an array check and delete
			//all smaller ele then current and then insert current index in queue.
			//(same as above)
			while(!q.isEmpty() && (arr[(int)q.element()]<arr[j]))
				q.remove();
			q.add(j);

			//always the first ele of an queue is the index of max ele of that window
			//so print it.
			//(same as above)
			System.out.print(arr[(int)q.element()]+" ");
		}
		System.out.println("");
	}


	/*


	An Interesting Method to Generate Binary Numbers from 1 to n

	Given a number n, write a function that generates and prints 
	all binary numbers with decimal values from 1 to n. 
		
	void generatePrintBinary(int n)
	{
    // Create an empty queue of strings
    queue<string> q;
 
    // Enqueue the first binary number
    q.push("1");
 
    // This loops is like BFS of a tree with 1 as root
    // 0 as left child and 1 as right child and so on
    while (n--)
    {
        // print the front of queue
        string s1 = q.front();
        q.pop();
        cout << s1 << "\n";
 
        string s2 = s1;  // Store s1 before changing it
   
        // Append "0" to s1 and enqueue it
        q.push(s1.append("0"));
 
        // Append "1" to s2 and enqueue it. Note that s2 contains
        // the previous front
        q.push(s2.append("1"));
    }
	}	
	*/
}
/******************************** ARRAY OF K QUEUES ***************************************/


//in one array handle k queue.
//say  one array and implement  3 queue in it.
/*
	extra thing to maintain
	1.k size array FRONT[] to maintain fronts of all queues
	2.k sizd array REAR[] to maintain rears of all queues
	3.n size array next[] to mainain nextpointer to each index.
	4.free pointer which points to the current free node to insert new node for any queue.	
*/
class ArrayQueue
{
	int[] arr;
	int[] front ;
	int[] rear;
	int[] next;
	int free;

	ArrayQueue(int n,int k)
	{
		arr=new int[n];
		front=new int[k];
		//All entries in front[] are initialized as -1 to indicate that all queues are empty.
		for(int i=0;i<k;i++)
			front[i]=-1;
		rear=new int[k];
		//All entries next[i] are initialized as i+1 because all slots are 
		//free initially and pointing to next slot.
		next=new int[n];
		for(int i=0;i<n-1;i++)
			next[i]=i+1;
		//last ele of array points to nothing so next[n-1]=-1
		next[n-1]=-1;
		//currently free elemet is zero.
		free=0;
	}

	boolean isFull()
	{
		//free only becomes -1 when whole array is full  no space to insert.
		if(free==-1)
			return true;
		else
			return false;
	}

	boolean isEmpty(int qNo)
	{
		//if front is -1 then queue is empty
		if(front[qNo]==-1)
			return true;
		else
			return false;
	}

	void add(int d,int qNo)
	{
		//before adding check whetjer array is full or not.
		if(isFull())
			throw new RuntimeException("queue overflow");
		//add at free index.
		int i=free;
		//so now update free index to next of current free index.
		free=next[i];
		//if queue is empty then update its front.
		if(isEmpty(qNo))
			front[qNo]=i;
		//if queue is not empty then update current rear's next.
		else
			next[rear[qNo]]=i;
		//queue's last ele always points to null so make next[i]=-1
		next[i]=-1;
		//at the end update rear of the queue.
		rear[qNo]=i;
		//insert data to arr at index i.
		arr[i]=d;
	}

	int remove(int qNo)
	{
		//before removing check whether queue is empty?
		if(isEmpty(qNo))
			throw new RuntimeException("queue underflow");
		//if queue is not empty then you have to remove front element
		int i=front[qNo];
		//so update front to its next
		front[qNo]=next[i];

		//IMPORTANT POINT
		//you are maintaing chain of free nodes
		//so to make removed index as first free index
		//firstly make current free index as next of removed element's index.
		//so next time when insersion happens it will be firstly at removed index
		//and then to its next[which is current free index]
		next[i]=free;
		//make removed index as current free index
		free=i;
		//return removed data
		return arr[i];
	}

	void printQueue(int qNo)
	{
		if(front[qNo]==-1)
			return;
		int current=front[qNo];
		while(current!=-1)
		{
			System.out.print(arr[current]+" ");
			current=next[current];
		}
		System.out.println("");
	}
}

/********************** BFS FOR MATRIX (ROTT ALL ORANGES) *************************/
/*
	
Minimum time required to rot all oranges

Given a matrix of dimension m*n where each cell in the matrix 
can have values 0, 1 or 2 which has the following meaning:

0: Empty cell

1: Cells have fresh oranges

2: Cells have rotten oranges 

So we have to determine what is the minimum time required so that all 
the oranges become rotten. A rotten orange at index [i,j] can rot other 
fresh orange at indexes [i-1,j], [i+1,j], [i,j-1], [i,j+1] (up, down, left and right). 
If it is impossible to rot every orange then simply return -1.

Examples:

Input:  arr[][C] = { {2, 1, 0, 2, 1},
                     {1, 0, 1, 2, 1},
                     {1, 0, 0, 2, 1}};
Output:
All oranges can become rotten in 2 time frames.


Input:  arr[][C] = { {2, 1, 0, 2, 1},
                     {0, 0, 1, 2, 1},
                     {1, 0, 0, 2, 1}};
Output:
All oranges cannot be rotten.


The idea is to user Breadth First Search. Below is algorithm.

			1) Create an empty Q.
			2) Find all rotten oranges and enqueue them to Q.  Also enqueue 
			   a delimiter to indicate beginning of next time frame. 
			3) While Q is not empty do following
			   3.a) While delimiter in Q is not reached 
			       (i) Dequeue an orange from queue, rot all adjacent oranges.
			           While rotting the adjacents, make sure that time frame 
			           is incremented only once. And time frame is not icnremented
			           if there are no adjacent oranges.
			   3.b) Dequeue the old delimiter and enqueue a new delimiter. The 
			        oranges rotten in previous time frame lie between the two
			        delimiters. 


*/
class MatrixBFS
{
	//structure which stores matrix index
	class QNode
	{
		int x;
		int y;
		QNode(int x1,int y1)
		{
			x=x1;
			y=y1;
		}
	}
	//matrix's number of rows and coumns
	int R;
	int C;

	// function to check whether a cell is valid / invalid
	//so no tension of array index out of bound anymore :D
	boolean isValidCell(int i,int j)
	{
		if(i>=0 && j>=0 && i<R && j<C)
			return true;
		else return false;
	}

	// Function to check whether the cell is delimiter
	// which is (-1, -1)
	boolean isDelimeter(QNode n)
	{
		if(n.x==-1 && n.y==-1)
			return true;
		else 
			return false;
	}

	//function whether any fresh orange is left in matrix or not
	//traverse the whole matrix last time.
	boolean isFreshOrangeLeft(int[][] arr)
	{
		for(int i=0;i<R;i++)
		{
			for(int j=0;j<C;j++)
			{
				if(arr[i][j]==1)
					return true;
				else
					continue;
			}
		}
		return false;
	}
	//main function
	int rottOranges(int[][] arr,int r,int c)
	{
		//here youll get matrix total row and col so initialize now!
		R=r;
		C=c;
		//use queue which stores the index of the matrix.
		MyQueue<QNode> q=new MyLinkedList<QNode>();
		// add all the cells in queue having rotten orange from the begining.
		for(int i=0;i<R;i++)
		{
			for(int j=0;j<C;j++)
			{
				if(arr[i][j]==2)
				{
					QNode node=new QNode(i,j);
					q.add(node);
				}
			}
		}
		//here starting time take zero
		//if at 0th time any rotten oranges are rotting new orranges
		//so even a single orrange is rotten time will be one.
		int time=0;
		//add delimeter after rotting process for perticuler time span
		QNode del=new QNode(-1,-1);
		q.add(del);
		boolean flag;
		while(!q.isEmpty())
		{
			flag=false;
			while(!isDelimeter((QNode)q.element()))
			{
				QNode n=(QNode)q.element();
				// Check top adjacent cell that if it can be rotten
				if(isValidCell((n.x)-1,n.y) && arr[(n.x)-1][n.y]==1)
				{
					if(!flag)
					{
						//flag is false means this is th first orange which is rotten so incerse time
						time++;
						flag=true;
					}
					arr[(n.x)-1][n.y]=2;
					QNode insert=new QNode((n.x)-1,n.y);
					q.add(insert);
				}

				// Check bottom adjacent cell that if it can be rotten
				if(isValidCell(n.x+1,n.y) && arr[n.x+1][n.y]==1)
				{
					if(!flag)
					{
						time++;
						flag=true;
					}
					arr[n.x+1][n.y]=2;
					QNode insert=new QNode(n.x+1,n.y);
					q.add(insert);
				}

				// Check left adjacent cell that if it can be rotten
				if(isValidCell(n.x,n.y-1) && arr[n.x][n.y-1]==1)
				{
					if(!flag)
					{
						time++;
						flag=true;
					}
					arr[n.x][n.y-1]=2;
					QNode insert=new QNode(n.x,n.y-1);
					q.add(insert);
				}

				// Check right adjacent cell that if it can be rotten
				if(isValidCell(n.x,n.y+1) && arr[n.x][n.y+1]==1)
				{
					if(!flag)
					{
						time++;
						flag=true;
					}
					arr[n.x][n.y+1]=2;
					QNode insert=new QNode(n.x,n.y+1);
					q.add(insert);
				}
				q.remove();
			}
			// Pop the delimiter
			q.remove();
			// If oranges were rotten in current frame than separate the
        	// rotten oranges using delimiter for the next frame for processing.
			if(!q.isEmpty())
				q.add(new QNode(-1,-1));

			// If Queue was empty than no rotten oranges left to process so exit
		}

		// Return -1 if all arranges could not rot, otherwise time.
		if(isFreshOrangeLeft(arr))
			return -1;
		else
			return time;
	}
}
/*
	REMEMBER!!!!!!!!!!!!!
	1.WHEN YOU ADD FROM FRONT-->GO LEFT(FRONT=FRONT-1)
		so when front is at 0 go to size-1
	2.WHEN YOU ADD FROM REAR-->G0 RIGHT(REAR=REAR+1)
		so when rear is at size-1 go to 0
	3.WHEN YOU REMOVE FROM FRONT-->GO RIGHT(FRONT=FRONT+1)
		so when front is at size-1 go to 0
	4.WHEN YOU REMOVE FROM REAR-->GO LEFT(REAR=REAR-1)
		so when rear is at 0 go to size-1
*/
class ArrayDequeue
{
	int[] arr;
	int size;
	int front;
	int rear;
	ArrayDequeue()
	{
		size=5;
		arr=new int[size];
		front=-1;
		rear=0;
	}

	boolean isFull()
	{
		if((front==0 && rear==size-1) || rear+1==front)
			return true;
		else
			return false;
	}

	boolean isEmpty()
	{
		if(front==-1)
			return true;
		else 
			return false;
	}

	void addFront(int d)
	{
		/*-------------------- 1 --------------------------*/
		if(isFull())
		{
			//throw new RuntimeException("queue overflow");
			System.out.println("queue overflow");
			return;
		}

		/*-------------------- 2 ----------------------------*/
		if(front==-1)
		{
			front=0;
			rear=0;
		}

		/*-------------------- 3 ---------------------------*/
		else if(front==0)
			front=size-1;
		else
			front=front-1;

		arr[front]=d;
	}

	//step 1 & 2 are same as addFront()
	void addRear(int d)
	{
		/*-------------------- 1 --------------------------*/
		if(isFull())
		{
			//throw new RuntimeException("queue overflow");
			System.out.println("queue overflow");
			return;
		}
			//throw new RuntimeException("queue overflow");
		
		/*-------------------- 2 ----------------------------*/

		if(front==-1)
		{
			front=0;
			rear=0;
		}

		/*-------------------- 3 ---------------------------*/

		else if(rear==size-1)
			rear=0;
		else
			rear=rear+1;

		arr[rear]=d;
	}

	void removeFront()
	{
		/*-------------------- 1 --------------------------*/

		if(isEmpty())
			{
			//throw new RuntimeException("queue overflow");
			System.out.println("queue overflow");
			return;
		}
			//throw new RuntimeException("queue underflow");
		
		/*-------------------- 2 ----------------------------*/

		if(front==rear)
		{
			front=-1;
			rear=-1;
		}
		
		/*-------------------- 3 ---------------------------*/

		else
		{
			if(front==size-1)
				front=0;
			else
				front=front+1;
		}
	}
	//steps 1 & 2 are same as removeFront()
	void removeRear()
	{
		/*-------------------- 1 --------------------------*/

		if(isEmpty())
		{
			//throw new RuntimeException("queue overflow");
			System.out.println("queue overflow");
			return;
		}	//throw new RuntimeException("queue underflow");
		
		/*-------------------- 2 ----------------------------*/

		if(front==rear)
		{
			front=-1;
			rear=-1;
		}
		
		/*-------------------- 3 ---------------------------*/

		else
		{
			if(rear==0)
				rear=size-1;
			else
				rear=rear-1;
		}
	}

	int getFront()
	{
		if(isEmpty())
			return -1;
		else
			return arr[front];
	}

	int getRear()
	{
		if(isEmpty())
			return -1;
		else
			return arr[rear];
	}

	void printQueue()
	{
		if(front==-1)
			return;
		int f=front;
		int r=rear;
		while(f!=r)
		{
			System.out.print(arr[f]+" ");
			//System.out.println("f:"+f+"r:"+r);
			f=(f+1)%size;
		}
		System.out.print(arr[f]);
		System.out.println("");
	}
}

/*************************************************************************************/

class TestMyQueue
{
	static void pr(String s)
	{
		System.out.println(s);
	}
	public static void main(String[] args)
	{
		UseQueue obj=new UseQueue();
		MyQueue<Integer> q=new MyLinkedList<Integer>();
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(4);
		q.add(5);
		q.add(6);
		System.out.println(q);

		ArrayList<Integer>[] arr3=new ArrayList[5];
		for(int y=0;y<arr3.length;y++)
			arr3[y]=new ArrayList<Integer>();
		arr3[0].add(1);
		arr3[0].add(2);
		arr3[0].add(4);
		arr3[2].add(3);
		arr3[2].add(1);
		arr3[3].add(0);
		obj.BFS(arr3);

		int[] arr={1, 2, 3, 1, 4, 5, 2, 3, 6};
		obj.maxOfAllSubarraysWithSizeK(arr,3);

		ArrayQueue queue=new ArrayQueue(7,3);
		queue.add(15,1);
		queue.add(10,0);
		queue.add(12,0);
		queue.add(11,2);
		queue.add(10,1);
		queue.add(18,0);
		queue.printQueue(0);
		queue.remove(0);
		queue.printQueue(0);
		queue.add(20,0);
		queue.printQueue(0);

		MatrixBFS ob=new MatrixBFS();
		//int[][] ar={{2,1,0,2,1},{1,0,1,2,1},{1,0,0,2,1}};
		int[][] ar={ {2, 1, 0, 2, 1},
                     {0, 0, 1, 2, 1},
                     {1, 0, 0, 2, 1}};
		System.out.println("time required to rott all oranges is:"+ob.rottOranges(ar,3,5));

		ArrayDequeue aq=new ArrayDequeue();
		aq.addFront(1);
		aq.addFront(2);
		aq.addFront(3);
		aq.addFront(4);
		aq.printQueue();

		ArrayDequeue myAQ=new ArrayDequeue();
	myAQ.addFront(1);myAQ.addFront(2);myAQ.addFront(3);
	myAQ.addFront(4);myAQ.addFront(5);
	myAQ.addFront(6);
	myAQ.printQueue();
	pr("\ngetfront="+myAQ.getFront());
	pr("getRear="+myAQ.getRear());
	pr("remove front");
	myAQ.removeFront();
	pr("getfront="+myAQ.getFront());
	pr("getRear="+myAQ.getRear());
	myAQ.printQueue();
	myAQ.removeRear();
	pr("getfront="+myAQ.getFront());
	pr("getRear="+myAQ.getRear());
	myAQ.printQueue();
	myAQ.addRear(12);
	myAQ.addRear(24);
	pr("getfront="+myAQ.getFront());
	pr("getRear="+myAQ.getRear());
	myAQ.printQueue();
	}
	
}
