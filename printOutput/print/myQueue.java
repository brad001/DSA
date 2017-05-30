import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Queue;

/* internally Queue Interface extends Collection*/
//interface MyQueue<T> extends Collection<T>

/*
Queue : insertBack, popFront, peekFirst
Applications of Queue:
		1. resource is shared among multiple consumers [CPU scheduling, Disk Scheduling.]
		2. data is transferred asynchronously [IO Buffers, pipes, file IO]
		3. full binary tree implementation

*/
interface MyQueue<T>
{
	void add(T data);
	void remove();
	T element();
	//i cant use display until i define it here
	void displayQueue();
	int size();
	boolean isEmpty();
}
class Node<T>
{
 	T data;
 	Node next;
 	
 	public Node(T data)
 	{
 		this.data=data;
 	}
}

class LinkList<T> implements MyQueue<T>
{
	
	private Node head;
	private Node tail;
 	static int count;
	
	LinkList()
	{
		head=tail=null;
	}
	public Node getHead()
	{
		if(head==null)
		{
			throw new RuntimeException("head is empty");
		}
		return head;
	}
	public void addFirst(T a)
	{
		Node newNode=new Node(a);
		count++;
		if(isEmpty())
		{
			head=tail=newNode;
		}
		else
		{

			//add to front and modify only head
			newNode.next=head;
			head=newNode;
	    }
	}
	public void addLast(T a)
	{
		Node newNode=new Node(a);
		count++;
		if(isEmpty())
		{
		  head=tail=newNode;	
		}
		else
		{
			//add to tail, modify only tail
			tail.next=newNode;
			tail=newNode;
		}
	}
	public void addAtIndex(int index,T data)
	{
		if(index>=0 && index<=size())
		{
			if(index==0)
			{
				addFirst(data);
			}
			else if(index==size())
			{
				addLast(data);
			}
			else
			{
				int currPos=1;
				Node curr=head;
				Node newNode=new Node(data);
				count++;
				while(currPos!=index)
				{
					curr=curr.next;
					currPos++;
				}
				Node temp=curr.next;
				curr.next=newNode;
				newNode.next=temp;
			}
		}
	}
    public boolean isEmpty()
	{
	  return (head==null);
	}
    void removeFirst()
	{
		if(isEmpty())
		{
			System.out.println("Empty List");
			throw new RuntimeException();
		}
		else if(head.next==null)//only one node
		{
			head=tail=null;
			count--;
		}
		else
		{
			head=head.next;
			count--;
		}
	}
	void removeLast()
	{
		if(isEmpty())
		{
			System.out.println("Empty List");
		}
		else if(head.next==null)//only one node
		{
			head=tail=null;
			count--;
		}
		else
		{
			Node curr=head,prev=null;
			while(curr.next!=null)
			{
				prev=curr;
				curr=curr.next;
			}
			prev.next=null;
			tail=prev;
			count--;
		}

	}
	
	public int size()
	{
		return size(head);
	}

	int size(Node head)
	{
		int count=0;
		while(head!=null)
		{
			head=head.next;
			count++;
		}
		return count;
	}
	void removeAtIndex(int index)
	{
		if(isEmpty())
		{
			System.out.println("Empty List");
		}
		else if(index==0)
		{
			removeFirst();
		}
		else if(index==(size()-1))
		{
			removeLast();
		}
		else
		{
			int currPos=0;
			Node curr=head,prev=null;
			while(index!=currPos)
			{
				prev=curr;
				curr=curr.next;
				currPos++;
			}
			prev.next=curr.next;
			count--;

		}
	}
	void doDisplayList()
	{
		Node temp=head;
		while(temp!=null)
		{
			System.out.print(temp.data+" ");
			temp=temp.next;
		}
		System.out.println();
	}
	/*************************************/
	//Implementing Queue interface funcitons
	// add => addLast
	//remove => removeFirst
	//element => getFirst
	public void add(T data)
	{
		addLast(data);
	}
	public void remove()
	{
		removeFirst();
	}
	public T element()
	{
		return (T)getHead().data;
	}
	public void displayQueue()
	{
		doDisplayList();
	}
	/*
	use the functions of linked list only
	public int size()
	{

	}
	public boolean isEmpty()
	{

	}
	*/
}
class Functions
{
	void pr(String s)
	{
		System.out.println(s);
	}
	/*
	pass this function a
	*/
	void bfs(LinkedList[]li)
	{
		int v=li.length;
		boolean[]visited=new boolean[v];
		for(int i=0;i<v;i++)
		{
			if(!visited[i])
				doBfs(i,li,visited);
		}
	}
	void doBfs(int s,LinkedList[]li,boolean []visited)
	{
		MyQueue<Integer>q=new LinkList<Integer>();
		q.add(s);
		while(!q.isEmpty())
		{
			int src=q.element();
			q.remove();
			
			System.out.println("visit:"+src);
			Iterator itr=li[src].listIterator();
			while(itr.hasNext())
			{
				int dest=(int)itr.next();
				if(!visited[dest])
				{
					q.add(dest);
					visited[dest]=true;
				}	
			}

		}
	}

	/*********************************************
	 IMPLEMENT QUEUE using STACK

			1. add () :   -O(n)
					recursively call push until stack empty and then push
					push back on back track

			2. remove() :  -O(1)
					pop();

			3. element : 	-O(1)
					peek();

	************************************************/

	/*********************************************
	CHECK IF A BINARY TREE IS COMPLETE OR NOT

		*. binary tree level traversal is done using Queue
		*. I have 4 cases for any node during this traversal

		NOTE: any element you remove => you have all nodes of that level in queue L->R


		q.add(root)
		while(!q.isEmpty())
		{
			//check for the element removed
			Node curr=q.remove();

			if(curr.left!=null && curr.right!=null)
			  	q.add(left) & q.add(right)

			else if(curr.left==null && curr.right!=null)  	
				return false

			else if(curr.left!=null && curr.right==null)	
			{
			      1
			    2   3
		      4   
				  //curr=2 has left node but no right node
				  //Queue has [all nodes of previous level except 2]
				  =>  I must not have any children of all the nodes from here on
				  =>  including 4 and all nodes in queue currently

			q.add(curr.left);
             while(!q.isEmpty())
             {
             	node rem=q.remove();
             	   //if any node has any children => not a complete binary tree
				if(rem.left!=null || rem.right!=null)
				      return false;
             }

			}
			else (curr.left==null && curr.right==null)
			{
				I have reached a node whose left and right are not there
				=> Iam on my current level and i have nothing on next level
				=> all my mates of cuurent level in stack must have nothing as childern
				while(!q.isEmpty())
             	{
             		node rem=q.remove();
             	   //if any node has any children => not a complete binary tree
					if(rem.left!=null || rem.right!=null)
				      return false;
             	}
			}
		} return true;
		*********************************************************/

		int[]maxFromEachSubArray(int[]arr,int k)
		{
			//to find max from each subarray
			// first block will have one max, and then for each element I have one max
			//total maximum= arr.length-k+1
	        int res[];
	        if(arr.length==0)
	        {
	          res=new int[0];
	          return res;
	        }
	        else
	        {
	            res=new int[arr.length-k+1];
	        }
	        
	        Deque<Integer>q=new ArrayDeque<Integer>();
	        for(int i=0;i<k;i++)
	        {
	        	//if incoming element has more value than last => remove until greater,
	            while(!q.isEmpty() && arr[i]>arr[q.getLast()])
	               q.removeLast();
	               
	               //then push it at end
	               q.addLast(i);
	        }
	        //while loop makes sure , we have indexes of only that slot which may be max for some other slot as well
	        //the index stored from front to rear | corresponding values are decreasing
	        //therefore, the front before any other slot starts=> maximum for previoous slot was q.front()
	        
	        int r=0;
	        for(int i=k;i<arr.length;i++)
	        {
	        	//this is the maximum for previous slot
	            res[r++]=arr[q.getFirst()];

	            //remove all that indexes from front
	            //check for all those indexes which fall away from the slot's reach
	            while(!q.isEmpty()&& q.getFirst()<i-k+1)
	                q.removeFirst();
	            
	            //now for the current index    
	            //do the same operation as before
	            //store the index but remove all that from back which are les than this
	            while(!q.isEmpty() && arr[i]>arr[q.getLast()])
	               q.removeLast();
	               
	               //finally add the index
	               q.addLast(i);
	        }

	        //this is necesary as last slots, maximum will the queue front
	        res[r]=arr[q.getFirst()];
	        

	        return res;
		}


		/*
			print binary from 1 to n

			1. think of a binary tree of 0 and 1
			2. where each root has 0 as left , and 1 as right
			3. we start from the tree Root as 1: and continue with step 2

			So , level order traversal with parents attached to them
			pop() each time=> get a string of binary
	
	algo: 
	  Queue of strings
		1. push "1"
		while(n-->0)
	    2. for each pop(): append 0 and push, append 1 and push
     each pop() =. next string
		*/
		void printBinaryFrom1ToN(int n)
		{
			String bin=new String("1");
			Deque<String>q=new ArrayDeque<String>();
			q.add(bin);

			while(n>0)
			{
				String out=q.remove();
				n--;

				System.out.println("binary : "+out);

				String out1=out+"0";
				q.add(out1);
				String out2=out+"1";
				q.add(out2);
			}
		}
}

/*
arr is the actual storage , where we store randomly based on free slot for each queue

front and rear maintains the index of where the actual elements of that queue are stores
next stores the indexes of each elements of queue in such a fashion
	that , for queue 0 : front[0]=1 => next[1] stores the next elements if any at index next[next[i]];

free maintains the empty list w.r.t next array
initially , free=0, next[0]=1, next[1]=2 ......until next stores -1=> array is full

*/
class QueueInArray
{

	private int[]arr;
	private int[]front;
	private int[]rear;
	private int[]next;
	static int free;
	//give how many queues and total elements to be stored
	QueueInArray(int k,int tot)
	{
		free=0;
		arr=new int[tot];
		front=new int[k];
		rear=new int[k];
		next=new int[tot];

		for(int i=0;i<k;i++)
		{
			front[i]=rear[i]=-1;
		}
		//initially mark the next of each index as next to it 
		//and last next to -1=> no free slots
		for(int i=0;i<tot-1;i++)
			next[i]=i+1;
		next[tot-1]=-1;
	}
	//check for in general whole array(no space at all)
	boolean isFull()
	{
		return free==-1;
	}
	//check for each queue
	boolean isEmpty(int qNum)
	{
		if(front[qNum]==-1)
			return true;
		else
			return false;
	}
	void add(int qNum,int data)
	{
		if(isFull())
		{
			throw new RuntimeException("QUEUE IS FULL");
		}

		//put the item in array at this  index, arr[i]=data;
		//but set all other properly
		int i=free;
		//next free index =next[free]
		free=next[i];

		//update the front if empty queue
		if(isEmpty(qNum))
		{
			front[qNum]=i;
		}
		//if queue not empty=> one more element to queue
		//rear[qNum] stores the queue's last index
		// link the next[last index]=i; chain increment for this queue
		else
		{
			next[rear[qNum]]=i;
		}

		//mark rear at each insert as the current free index
		//update the rear, after upadting next of prevRear
		rear[qNum]=i;
		//also end the queue here by -1 at next i
		next[i]=-1;

		arr[i]=data;
	}
	int remove(int qNum)
	{
		//check for particular queue
		if(isEmpty(qNum))
		{
			throw new RuntimeException("QUEUE EMPTY");
		}

		//remove means update : free and front of the queue
		//and point front to next of front index

		
		int i=front[qNum];
		front[qNum]=next[i];
		//iam maintaining a chain of free blocks
		//so first link this indix to the next[free]
		//and then update your free

		/*NOTE: add's 1st two :inverted operation */
		next[i]=free;
		free=i;

		return arr[i];
	}
	int element(int qNum)
	{
		if(isEmpty(qNum))
		{
		  	System.out.println("empty querue");
		  	return Integer.MIN_VALUE;
		}

		return arr[front[qNum]];
	}
	void display(int qNum)
	{
		if(isEmpty(qNum))
			System.out.println("queu is empty");
		else
		{
			int i=front[qNum];
			while(i!=-1)
			{

				System.out.print(arr[i]+" ");
				i=next[i];
			}
			System.out.println("");
		}
	}

}

class StackInArray
{

	private int[]arr;
	private int[]top;
	private int[]next;
	static int free;
	//give how many queues and total elements to be stored
	StackInArray(int k,int tot)
	{
		free=0;
		arr=new int[tot];
		top=new int[k];
		next=new int[tot];

		for(int i=0;i<k;i++)
		{
			top[i]=-1;
		}
		//initially mark the next of each index as next to it 
		//and last next to -1=> no free slots
		for(int i=0;i<tot-1;i++)
			next[i]=i+1;
		next[tot-1]=-1;
	}
	//check for in general whole array(no space at all)
	boolean isFull()
	{
		return free==-1;
	}
	//check for each queue
	boolean isEmpty(int stNum)
	{
		if(top[stNum]==-1)
			return true;
		else
			return false;
	}
	void add(int stNum,int data)
	{
		if(isFull())
		{
			throw new RuntimeException("Stack IS FULL");
		}

		//put the item in array at this  index, arr[i]=data;
		//but set all other properly
		int i=free;
		//next free index =next[free]
		free=next[i];

		//no if else
		//1. first, simply update next to current top (initially the top has -1)
		//                     so, this will be set as the bottom marker for this stack
	    //                     and fromt then on, each will update top and next=top
		//2. then, put top as current
		next[i]=top[stNum];
		top[stNum]=i;

		arr[i]=data;
	}
	int remove(int stNum)
	{
		//check for particular queue
		if(isEmpty(stNum))
		{
			throw new RuntimeException("stack EMPTY");
		}

		//since we have to remove the top
		//top stores it

		int i=top[stNum];
		//update the top as next
		top[stNum]=next[i];
		// Attach the previous top to the beginning of free list
		//now, i index will be free
		//adding to list first, bcoz free has the next free slot
		//before that next[current free index]=prevFree
		//and update free to current;
        next[i] = free;
    	free = i;
 
     return arr[i];
	}
	int element(int stNum)
	{
		if(isEmpty(stNum))
		{
		  	System.out.println("empty stack");
		  	return Integer.MIN_VALUE;
		}

		return arr[top[stNum]];
	}
	void display(int stNum)
	{
		if(isEmpty(stNum))
			System.out.println("stack is empty");
		else
		{
			System.out.println("frome top to bottom: the elements are:");
			//from top to bottom
			int i=top[stNum];
			while(i!=-1)
			{

				System.out.print(arr[i]+" ");
				i=next[i];
			}
			System.out.println("");
		}
	}

}

/*
minimum time to rot all eggs
given: matrix [0,1,2] //2:rot , 1:fresh, 0:empty cell
use: queue to store all rotten egss:ended by Marker
algo: pop() each and for all adjacent, if fresh, mark rotten and add at queue
	  each marker found:increment time
	  at the end : check all cells, if any 1 left=> not possible to rot this one
	     			else, return the count => count signifies the time in units/max distance to rot all
*/
class MatNode
{
	private int x;
	private int y;
	public MatNode()
	{}
	public MatNode(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	void setX(int x)
	{
		this.x=x;
	}
	void setY(int y)
	{
		this.y=y;
	}
	int getX()
	{
		return this.x;
	}
	int getY()
	{
		return this.y;
	}
	public int compareTo(MatNode obj)
	{
		if(this.x==obj.x)
		{
			return this.y-obj.y;
		}
		else
			return this.x-obj.x;
	}

}	     			
class RottenEggs
{
	private Queue<MatNode>q;
	private static int countTimeUnit;
	private MatNode marker;
	private int R;
	private int C;
	public RottenEggs()
	{
	 q=new ArrayDeque<MatNode>();
	 countTimeUnit=0;
	 marker=new MatNode(-1,-1);
	}

	void pr(String s)
	{
		System.out.println(s);
	}
	void addRottenNodeToQueue(int[][]mat,int R,int C)
	{
		
		for(int i=0;i<R;i++)
		{
			for(int j=0;j<C;j++)
			{
				if(mat[i][j]==2)
				{
					MatNode temp=new MatNode(i,j);
					q.add(temp);
				}
			}
		}
	}
	boolean isValidMatNode(int i,int j)
	{
		//i and j cant go beyong R,C
		if(i>=0 && j>=0 && i<=this.R-1 && j<=this.C-1)
			return true;
		else
			return false;
	}
	void printMat(int[][]mat)
	{
		for(int i=0;i<R;i++)
		{
			for(int j=0;j<C;j++)
			{
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}

	}
	boolean checkAllRotten(int[][]mat)
	{
		for(int i=0;i<R;i++)
		{
			for(int j=0;j<C;j++)
			{
				if(mat[i][j]==1)
					return false;
			}
		}
		return true;
	}
	boolean isMarker(MatNode n)
	{
		if(n.getX()==-1 && n.getY()==-1)
			return true;
		else
			return false;
	}
	
	//pass a 2-d matrix of 0,1,2
	int findMinRottenTime(int[][]mat,int R,int C)
	{
		this.R=R;
		this.C=C;
		printMat(mat);

		addRottenNodeToQueue(mat,R,C);
		q.add(marker);

		while(!q.isEmpty())
		{
			//until the queue of rotten nodes is empty
			//keep popping and make the adjacent nodes rotten and add them to the queue
			//each slot of Nodes in queue is separated by marker
			
			//to check the content of nodes. comparetto
			//while(!isMarker((MatNode)q.element()))
			while((((MatNode)q.element()).compareTo(marker))!=0)
			{	
				MatNode popNode=(MatNode)q.remove();

				//first check the valid adjacent cells for the popped node
				//and then the value of the adjacent cell
				int tempi=popNode.getX();
				int tempj=popNode.getY();

				//for top => i-1 and j
				if(isValidMatNode(tempi-1,tempj) && mat[tempi-1][tempj]==1)
				{
					mat[tempi-1][tempj]=2;
					MatNode newNode=new MatNode(tempi-1,tempj);
					q.add(newNode);
				}
				//for bottom => i+1 and j
				if(isValidMatNode(tempi+1,tempj) && mat[tempi+1][tempj]==1)
				{
					mat[tempi+1][tempj]=2;
					MatNode newNode=new MatNode(tempi+1,tempj);
					q.add(newNode);
				}
				//for right=> i and j+1
				if(isValidMatNode(tempi,tempj+1) && mat[tempi][tempj+1]==1)
				{
					mat[tempi][tempj+1]=2;
					MatNode newNode=new MatNode(tempi,tempj+1);
					q.add(newNode);
				}
				//for left => i and j-1
				if(isValidMatNode(tempi,tempj-1) && mat[tempi][tempj-1]==1)
				{
					mat[tempi][tempj-1]=2;
					MatNode newNode=new MatNode(tempi,tempj-1);
					q.add(newNode);
				}


			}
			//once equal=> pop and check if queue is empty
			// if empty=> dont push marker again
			//else push marker

			//with each marker found=> countTimeUnit++ => continue if queue is not empty
			q.remove();
			if(!q.isEmpty())
			{
				//if the queue is not empty
				// means, previous level rooten has rotted more
				//and we need to continue in this fashion
				//so increment time here
				countTimeUnit++;
				q.add(marker);
			}
		}

		pr("the matrx after updation is: ");
		printMat(mat);
		//check the matrix once again,if all fresh are rotten or not
		if(checkAllRotten(mat))
		{
			System.out.println("all rotten: ");
			return countTimeUnit;
		}
		else
		{
			System.out.println("there exists eggs which are not rotten");
			return Integer.MIN_VALUE;
		}
	}//end of minRottenTimeUnit
}

class MyArrayDeque
{
	int[]arr;
	int front;
	int rear;
	final int size;

	/*
	 consider the array as index:             0   1    2   3   4   5

	       AddFront:    *  AddRear:                < --f     r -->
   if(not initialized)  *  if(not initialised)
	        f=r=0       *     f=r=0
		elseif(f==0)    *  elseif(r==size-1)
			f=size-1    *     r=0
		else            *  else
			f=f-1       *	 r=r+1
          ****************************************************************
	     RemoveFront:   *  RemoveRear:            f -->     <-- r 
	   if(FrontsameRear)*   if(FrontsameRear)
	        f=r=-1      *     f=r=-1
	    elseif(f==size-1)*  elseif(r==0)
			f=0	    	*	  r=size-1
		else    	    *   else
			f=f+1	    *	  r=r-1


     Algo:
         1. each Function has 3 blocks :
                  1. isEmpty()      / isFull()
                  2. if both are -1 / if both are same
                  3. if reaches extreme
                  4. otherwise
                  

	*/
    void pr(String s)
    {
    	System.out.println(s);
    }
	public MyArrayDeque(int sz)
	{
		size=sz;
		arr=new int[sz];
		front=-1;
		rear=-1;
	}
	
	//full => front and rear are both at extreme ends
	// or =>   rear has moved from back till prev to front [....r f....]
	boolean isFull()
	{
		if((front==0 && rear==size-1 )|| rear+1==front)
			return true;
		else
			return false;
	}
	//empty => front ==-1
	boolean isEmpty()
	{
		if(front==-1 &&  rear==-1)
			return true;
		else
			return false;
	}

	//this will work with respect to front, so think of cases where front moves
	void addFront(int data)
	{
		if(isFull())
		{
			pr("queue is full :cant add");
			return;
		}
		pr("addfront front="+front+" rear="+rear);
			if(front==-1)      //if not initialised
				front=rear=0;
			else if(front==0)  //if reaches extreme
				front=size-1;
			else
				front=front-1;  // otherwise

		arr[front]=data;
	}
	//this will work with respect to rear, so think of cases where rear moves
	void addRear(int data)
	{
		if(isFull())
		{
			pr("front "+front+" rear "+rear);
			pr("queue is full :cant add");
			return;
		}

			if(front==-1)          //if not initialised
				front=rear=0;
			else if(rear==size-1)  //if reaches extreme
				rear=0;
			else
				rear=rear+1;       // otherwise

		arr[rear]=data;		
	}
	//this will work w.r.t front , front moves ahead
	void removeFront()
	{
		if(isEmpty())
		{
			pr("queue is empty:cant remove");
			return ;
		}

			if(front==rear)           //if same
				front=rear=-1;
			else if(front==size-1)	  //if reaches extreme
				front=0;
			else
				front=front+1;		  //otherwise
	}
	//this will work w.r.t read , rear moves back when remove
	void removeRear()
	{
		if(isEmpty())
		{
			pr("queue is empty:cant remove");
			return ;
		}

			if(front==rear)			//if same
				front=rear=-1;
			else if(rear==0)		//if reaches extreme
				rear=size-1;
			else
				rear=rear-1;		//oterwise
	}
	int getFront()
	{
		if(isEmpty())
		{
			pr("empty queue:cant get");
			return Integer.MIN_VALUE;
		}
		return arr[front];
	}
	int getRear()
	{
		if(isEmpty())
		{
			pr("empty queue:cant get");
			return Integer.MIN_VALUE;
		}
		return arr[rear];	
	}
	//front always ahead of rear and elements are stored from front to rear
	void displayArrayDeque()
	{
		if(isEmpty())
		{
			pr("no elements:empty queue");
			return;
		}
		else
		{	
			//...front ....rear..
			if(front<=rear)
			{
				pr("front...rear");
				int index=front;
				while(index<=rear)
				{
					System.out.print(arr[index]+" ");
					index++;
				}
			}
			//...rear....front....
			//front goes till end and then starts over again
			else
			{
				int index=front;
				pr("rear....front");
				while(index<size)
				{
					System.out.print(arr[index]+" ");
					index++;	
				}
				index=0;
				while(index<=rear)
				{
					System.out.print(arr[index]+" ");
					index++;		
				}
			}
		}
	}
}
public class QueueImplementLinkedList
{
	static void pr(String s)
	{
		System.out.println(s);
	}
	static void prA(int[]arr)
	{
		for(int e:arr)
			System.out.print(e+" ");
		pr("");
	}
	public static void main(String[]args)
	{

		pr("****************************************");
		MyQueue<Integer> q1=new LinkList<Integer>();
		q1.add(1);q1.add(2);q1.add(3);q1.add(4);
		pr("the queue is :");
		q1.displayQueue();
		pr("empty:?"+q1.isEmpty());
		pr("size?"+q1.size());
		pr("first element of queue is :"+(int)q1.element());
		q1.remove();
		pr("the queue is :");
		q1.displayQueue();
		q1.add(9);
		pr("the queue is :");
		q1.displayQueue();
		q1.remove();
		pr("the queue is :");
		q1.displayQueue();
		pr(""+q1.element());
		q1.remove();
		pr("the queue is :");
		q1.displayQueue();
		pr(""+q1.isEmpty());
		pr(""+q1.size());
		pr("*****************************************");
		pr("IMPLEMENTING BFS USING QUEUE");
		int V=8;
		LinkedList<Integer>[]li=new LinkedList[V];
		for(int i=0;i<V;i++)
			li[i]=new LinkedList<Integer>();
		//add edges
		li[0].add(2);li[0].add(1);
		li[1].add(3);li[3].add(6);li[3].add(4);li[3].add(5);
		li[4].add(7);li[5].add(7);li[6].add(7);
		pr("graph display:");
		for(int i=0;i<V;i++)
			System.out.print(i+":"+li[i]+"\n");
		pr("bfs traversal");
		Functions obj=new Functions();
		obj.bfs(li);
		pr("****************************************");
		pr("FIND THE MAXIMUM FROM EACH SUBAARAY OF SIZE K");
		int[]arr=new int[]{12, 1, 78, 90, 57, 89, 56};
		int k=3;
		int[]res=obj.maxFromEachSubArray(arr,k);
		pr("the array is :");
		prA(arr);
		pr("the maximum from each subarray of size="+k+" are:");
		prA(res);
		pr("");
		pr("****************************************");
		pr("PRINT ALL BINARY STRINGS FROM 1 TO N");
		int n=11;
		pr("given n:"+n);
		obj.printBinaryFrom1ToN(n);
		pr("");
		pr("****************************************");
		pr("IMPLEMENT K QUEUES IN an ARRAY");
		pr("10 elemets in 4 queus");
		int num=10;
		int kNum=4;
		QueueInArray qA=new QueueInArray(kNum,num);
		qA.add(1,22);
		qA.add(1,24);
		qA.add(1,54);
		qA.display(1);
		pr(""+qA.element(1));
		qA.add(0,11);
		qA.add(0,12);
		qA.add(0,13);
		qA.display(0);
		pr(""+qA.element(0));
		pr("renoved "+qA.remove(0));
		qA.display(0);
		pr("renoved "+qA.remove(0));
		qA.display(0);
		pr("renoved "+qA.remove(0));
		qA.display(0);
		pr("*******************************************");
		pr("IMPLEMENT K stack IN an ARRAY");
		pr("10 elemets in 4 stack");
		int num1=10;
		int kNum1=4;
		StackInArray sA=new StackInArray(kNum1,num1);
		sA.add(1,22);
		sA.add(1,24);
		sA.add(1,54);
		sA.display(1);
		pr("top:"+sA.element(1));
		pr("pop:"+sA.remove(1));
		sA.display(1);
		sA.add(0,11);
		sA.add(0,12);
		sA.add(0,13);
		sA.display(0);
		pr("top:"+sA.element(0));
		pr("pop:"+sA.remove(0));
		sA.display(0);

		pr("*******************************************");
		pr("check the minimum time unit required to rot all eggs".toUpperCase());
		RottenEggs rotObj=new RottenEggs();
		int arr1[][] = new int[][] {{2, 0, 0, 2, 0},
                                    {0, 0, 0, 2, 0},
                                    {1, 1, 1, 2, 0}};
		int minTime=rotObj.findMinRottenTime(arr1,3,4);
		pr("the minimum time is:"+minTime);
		pr("*******************************************");
		pr("IMPLEMENTING ARRAY-DEQUE");
		int arrSize=5;
		MyArrayDeque myAQ=new MyArrayDeque(arrSize);
		myAQ.addFront(1);myAQ.addFront(2);myAQ.addFront(3);
		myAQ.addFront(4);myAQ.addFront(5);
		myAQ.addFront(6);
		myAQ.displayArrayDeque();
		pr("\ngetfront="+myAQ.getFront());
		pr("getRear="+myAQ.getRear());
		pr("remove front");
		myAQ.removeFront();
		pr("getfront="+myAQ.getFront());
		pr("getRear="+myAQ.getRear());
		myAQ.displayArrayDeque();
		myAQ.removeRear();
		pr("getfront="+myAQ.getFront());
		pr("getRear="+myAQ.getRear());
		myAQ.displayArrayDeque();
		myAQ.addRear(12);
		myAQ.addRear(24);
		pr("getfront="+myAQ.getFront());
		pr("getRear="+myAQ.getRear());
		myAQ.displayArrayDeque();
		pr("*******************************************");


	}
}

Note: /home/lokender/workspace1/GeeksForGeeks/QueueImplementLinkedList.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
****************************************
the queue is :
1 2 3 4 
empty:?false
size?4
first element of queue is :1
the queue is :
2 3 4 
the queue is :
2 3 4 9 
the queue is :
3 4 9 
3
the queue is :
4 9 
false
2
*****************************************
IMPLEMENTING BFS USING QUEUE
graph display:
0:[2, 1]
1:[3]
2:[]
3:[6, 4, 5]
4:[7]
5:[7]
6:[7]
7:[]
bfs traversal
visit:0
visit:2
visit:1
visit:3
visit:6
visit:4
visit:5
visit:7
****************************************
FIND THE MAXIMUM FROM EACH SUBAARAY OF SIZE K
the array is :
12 1 78 90 57 89 56 
the maximum from each subarray of size=3 are:
78 90 90 90 89 

****************************************
PRINT ALL BINARY STRINGS FROM 1 TO N
given n:11
binary : 1
binary : 10
binary : 11
binary : 100
binary : 101
binary : 110
binary : 111
binary : 1000
binary : 1001
binary : 1010
binary : 1011

****************************************
IMPLEMENT K QUEUES IN an ARRAY
10 elemets in 4 queus
22 24 54 
22
11 12 13 
11
renoved 11
12 13 
renoved 12
13 
renoved 13
queu is empty
*******************************************
IMPLEMENT K stack IN an ARRAY
10 elemets in 4 stack
frome top to bottom: the elements are:
54 24 22 
top:54
pop:54
frome top to bottom: the elements are:
24 22 
frome top to bottom: the elements are:
13 12 11 
top:13
pop:13
frome top to bottom: the elements are:
12 11 
*******************************************
CHECK THE MINIMUM TIME UNIT REQUIRED TO ROT ALL EGGS
2 0 0 2 
0 0 0 2 
1 1 1 2 
the matrx after updation is: 
2 0 0 2 
0 0 0 2 
2 2 2 2 
all rotten: 
the minimum time is:3
*******************************************
IMPLEMENTING ARRAY-DEQUE
addfront front=-1 rear=-1
addfront front=0 rear=0
addfront front=4 rear=0
addfront front=3 rear=0
addfront front=2 rear=0
queue is full :cant add
rear....front
5 4 3 2 1 
getfront=5
getRear=1
remove front
getfront=4
getRear=1
rear....front
4 3 2 1 getfront=4
getRear=2
front...rear
4 3 2 getfront=4
getRear=24
rear....front
4 3 2 12 24 *******************************************
[Finished in 7.5s]
