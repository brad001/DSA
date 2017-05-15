/*
In general, array is considered a data structure for which size is fixed at the compile time
and array memory is allocated either from Data section (e.g. global array) 
or Stack section (e.g. local array).
Similarly, linked list is considered a data structure for which size is not fixed 
and memory is allocated from Heap section (e.g. using malloc() etc.) as and when needed. 
In this sense, array is taken as a static data structure (residing in Data or Stack section) 
while linked list is taken as a dynamic data structure (residing in Heap section).

array elements are allocated memory in sequence i.e. contiguous memory while
nodes of a linked list are non-contiguous in memory.

Since elements of array are contiguous in memory, we can access any element randomly using index
due to contiguous memory for successive elements in array, no extra information is needed
to be stored in individual elements i.e. no overhead of metadata in arrays.
rray could have several unused elements because memory has already been allocated. 
But linked list will have only the required no. of data items.

Arrays in Java store one of two things: either primitive values (int, char, …) or 
references (a.k.a pointers).

When an object is creating by using “new”, memory is allocated on the heap and 
a reference is returned. This is also true for arrays. 


*/
import java.util.*;
class ArrayLinkedList
{
	/*
		node stucture of a linked list
		{data,nextPointer}
	*/
	class Node
	{
		int data;
		Node next;

		Node(int data)
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
	void add(int d) //add at the end of the list
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
	void add(int index,int d)// add at given index
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
	void addFirst(int d)
	{
		add(0,d);
	}
	void addLast(int d)
	{
		add(d);
	}


	//get th data at the given index**** O(i)
	/*
		traverse till the given index of the list
		return current.next;
	*/
	int get(int index) //get the data from the given index node 
	{
		Node target=head;
		for(int i=0;i<index;i++)
		{
			target=target.next;
		}
		return target.data;
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
	int size()
	{
		int x=size(head);
		return x;
	}
	int size(Node n)
	{
		if(n==null)
			return 0;
		else return(1+ size(n.next));
	}



	//swap data values (not swaping nodes) of given values present in the list
	/*
		find first occurence of the given data values and make two pointers to
		both data value nodes,
		if 
			any of the two pointer is null means that data is not present
			in the list then return
		else 
			indexd1.data=d2;
			indexd2.data=d1;
	*/
/*************************** SWAP SECTION **********************************************************/
	void swap(int d1,Integer d2)
	{
		Node current=head;
		Node indexd1=null;
		Node indexd2=null;
		for(int i=0;i<count;i++)
		{
			if(current.data==d1 && indexd1==null )
			{
				indexd1=current;
				current=current.next;
			}
			else if(current.data==d2 && indexd2==null)
			{
				indexd2=current;
				current=current.next;
			}
			else
			{
				current=current.next;
			}
		}
		if(indexd1.equals(null) || indexd2.equals(null))
		{
			System.out.println("elements not found");
		}
		else
		{
			indexd1.data=d2;
			indexd2.data=d1;
		}
	}



	
	// Function to swap Nodes itself with data value given x and y**** O(n)
	/*
		if x and y are same do nothing!
		else
		find out first occurence of x and y
		means find out,
		preX,currentX,preY,currentY
		if any one of them is head (means pre is null)
			update head means head=x or head=y
		else 
			preX.next=currentY or preY.next=cuurentX
		now update current nodes's next pointers!
			Node temp = currX.next;
        	currX.next = currY.next;
        	currY.next = temp;
	*/
   		 public void swapNodes(int x, int y)
    {
        // Nothing to do if x and y are same
        if (x == y) return;
 
        // Search for x (keep track of prevX and CurrX)
        Node prevX = null, currX = head;
        while (currX != null && currX.data != x)
        {
            prevX = currX;
            currX = currX.next;
        }
 
        // Search for y (keep track of prevY and currY)
        Node prevY = null, currY = head;
        while (currY != null && currY.data != y)
        {
            prevY = currY;
            currY = currY.next;
        }
 
        // If either x or y is not present, nothing to do
        if (currX == null || currY == null)
            return;
 
        // If x is not head of linked list
        if (prevX != null)
            prevX.next = currY;
        else //make y the new head
            head = currY;
 
        // If y is not head of linked list
        if (prevY != null)
            prevY.next = currX;
        else // make x the new head
            head = currX;
 
        // Swap next pointers
        Node temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
    }


    //swap nodes pair wise
	/*
		
	*/
	void swapPairWise()
	{
		swapPairWise(head,head.next,null);
	}
	void swapPairWise(Node pre,Node current,Node prePre)
	{
		if(pre==null || current==null)
			return;
		if(pre==head)
			head=head.next;
		pre.next=current.next;
		current.next=pre;
		if(prePre!=null)
			prePre.next=current;
		prePre=pre;
		pre=pre.next;//here for even length pre will be null
		//so current=pre.next only if pre!=null
		//otherwise it will give nullpointerException!
		//System.out.println(pre.data);
		if(pre!=null)
			current=pre.next;
		else
			current=null;

		//System.out.println("current"+current.data);
		swapPairWise(pre,current,prePre);
	}

/******************** REVERSE SECTION  **********************/

    //reverse the list in iterative way.
	void reverse() {
		last=head;
        Node before = null;
        Node current = head;
        Node after = null;
        while (current != null) 
        {
            after = current.next;
            current.next = before;
            before = current;
            current = after;
        }
        head = before;     
    }



    //reverse the list in recusive way.
    void reverseR()
    {
    	head=reverseRec(head,null,null);
    }
    //same logic as iterative method!

    Node reverseRec(Node current,Node before,Node after)
    {
    	//this if is just to modify the last pointer.
    	if(current==head)
    	{
    		last=current;
    	}
    	if(current.next==null)
    	{
    		Node x=current;
    		current.next=before;
    		return x;
    	}
    	after=current.next;
    	current.next=before;
    	before=current;
    	current=after;
    	return reverseRec(current,before,after);

    }


    //reverse list by k group nodes
    /*
		list: 2->5->1->3->8->4->9->6
		k=3
		reversed list:1->5->2->4->8->3->6->9
    */
    void reverseByK(int k)
    {
    	head=reverseByK(head,null,null,k);
    	//head=reverse(head,k);
    }
    Node reverseByK(Node current,Node before,Node after,int k)
    {
    	//here temphead is the head of the block before reversal
    	//which is going to be the last node of the block after reversal
    	//and its next will be null
    	//after reversal beofre will be the head of that block.
    	//so pass next block's head(before) to previous blocks last(tempHead) node's next.
    	Node tempHead=current;
    	int kmodify=0;
    	//here use both iterative and recursive 
    	//for one block do iterative and go next block using recursion 
    	while(kmodify<k && current!=null)
    	{
    		after = current.next;
            current.next = before;
            before = current;
            current = after;
            kmodify++;
    	}
    	//here tempHead is block's head whose next will be null affter block reversl
    	//so we will return before to tempHead's next which is the next head of next node.
    	if(current!=null)
    		tempHead.next=reverseByK(current,null,null,k);
    	else
    		last=tempHead;
    	return before;

    }
    void reverseAlternateKNodes(int k)
    {
    	head=reverseAlternateKNodes(head,null,null,k);
    }
    Node reverseAlternateKNodes(Node current,Node before,Node after,int k)
    {
    	Node tempHead=current;
    	int kmodify=0;
    	//boolean flag=flase;
    	//here use both iterative and recursive 
    	//for one block do iterative and go next block using recursion 
    	while(kmodify<k && current!=null)
    	{
    		after = current.next;
            current.next = before;
            before = current;
            current = after;
            kmodify++;
    	}
    	Node lastNode=tempHead;
    	int count=0;
    	//link reversed last node with next node's first node
    	if(current!=null)
    		tempHead.next=current;
    	while(count<k)
    	{
    		if(current==null)
    			return before;
    		else
    		{
    			lastNode=current;
    			current=current.next;
    			count++;
    		}
    	}

    	if(current!=null)
    		lastNode.next=reverseAlternateKNodes(current,null,null,k);
    	else
    		last=tempHead;
    	return before;

    }


    //print the list in reverse order without reversing it.
	/*
		use tail recursion,call function for the next node recursivly 
		and after returning from function call print current.data.
	*/
	void printReverse()
	{
		printReverse(head);
		System.out.println("");
	}
	void printReverse(Node current)
	{
		if(current.next!=null)
			printReverse(current.next);
		System.out.print(current.data+"  "); //tail recursion
	}

/*************** SLOW AND FAST POINTER USE ***********************/

    /*
	Traverse linked list using two pointers. Move one pointer by one and other pointer by two. 
	When the fast pointer reaches end slow pointer will reach middle of the linked list.
    */
    void printMiddleElement()
	{
		Node fastPointer=head;
		Node slowPointer=head;
		while(fastPointer.next!=null && fastPointer.next.next!=null)
		{
			fastPointer=fastPointer.next.next;
			slowPointer=slowPointer.next;
		}			
		System.out.println("middle element is:"+slowPointer.data+"\n");
	}
	Node getMiddleElement(Node head)
	{
		Node fastPointer=head;
		Node slowPointer=head;
		while(fastPointer.next!=null && fastPointer.next.next!=null)
		{
			fastPointer=fastPointer.next.next;
			slowPointer=slowPointer.next;
		}			
		//System.out.println("middle element is:"+slowPointer.data+"\n");
		return slowPointer;
	}


//find loop inside list..means last element's next points to any
	//previous list node.
	/*
	Floyd’s Cycle-Finding Algorithm:
	This is the fastest method. Traverse linked list using two pointers.  
	Move one pointer by one and other pointer by two.  
	If these pointers meet at some node then there is a loop.  
	If pointers do not meet then linked list doesn’t have loop.
	*/
	boolean findLoop()
	{
		Node fastPointer=head;
		Node slowPointer=head;
		while(fastPointer.next!=null && fastPointer.next.next!=null)
		{
			fastPointer=fastPointer.next.next;
			slowPointer=slowPointer.next;
			if(fastPointer==slowPointer)
				return true;
		}
		return false;	
	}



	//find the point where both fast and slow meet
	//from there, we know that in a cycle, if both slow and fast starts moving
	// they both meet at the same point only
	// => to meet at the same point again=> distance from 
	//                       node which hits the loop and to the meeting node
	//                       has same distance from starting node=> then only they meet at same point
	//from start to meeting point=m (to start of cyle)+k( from there to meet node)
	//same k+m => from meet node to start of cyle + rest (since rest is k=> from meet to node start of cylce=k)
	//therefore, move both one by one , and where the meet, disjoint
	boolean removeLoop()
	{

		Node fastPointer=head;
		Node slowPointer=head;
		boolean loop=false;
		while(fastPointer.next!=null && fastPointer.next.next!=null)
		{
			fastPointer=fastPointer.next.next;
			slowPointer=slowPointer.next;
			if(fastPointer==slowPointer)
			{
				loop=true;
				break;
			}
		}
		//System.out.println("came out of 1st while");
		slowPointer=head;
		Node pre=fastPointer;
		while(slowPointer!=fastPointer)
		{
			slowPointer=slowPointer.next;
			pre=fastPointer;
			fastPointer=fastPointer.next;
		}
		//System.out.println("came out of 2nd while");
		pre.next=null;
		return loop;

	}
/*******************************************************************************/
	//print the nth node's data from the end of the list
	/*
		firstly place the reference pointer  to distance n
		then increment both main pointer and reference ppointer together
		till reaches to end of the list,at that time main pointer will be
		at nth node from the end.
	*/
	void printNthNodeFromLast(int n)
	{
		Node reference=head;
		Node main=head;
		for(int i=1;i<n && reference.next!=null;i++)
			reference=reference.next;
		if(reference==null)
		{
			System.out.println("size exceeds");
			return;
		}
		while(reference.next!=null)
		{
			reference=reference.next;
			main=main.next;
		}
		System.out.println(n+"th node from the last is:"+main.data);
	}


	//check whether given list forms palindrom or not
	/*
		1:find middle element
		2:reverse list from middle element
		3:check from zero and middle wheter node data is same or not
		4:reverse again from middle and make it as it is
	*/
	boolean isPalindrom()
	{
		Node middle=head;
		Node fastPointer=head;
		Node slowPointer=head;
		//find out middle node
		while(fastPointer.next!=null && fastPointer.next.next!=null)
		{
			fastPointer=fastPointer.next.next;
			slowPointer=slowPointer.next;
		}
		//middle will be slowpointer.next!!
		middle=slowPointer.next;
		Node link=reverseRec(middle,null,null);
		//head will be changed so it has to be linked with previous element
		//System.out.println(slowPointer.data);
		slowPointer.next=link;
		//System.out.println(link.data);
		/*while(link!=null)
		{
			System.out.println("reversed:"+link.data);
			link=link.next;
		}*/
		//printList();
		Node i=head;
		Node j=slowPointer.next;
		while(j!=null)
		{
			//System.out.println("i:"+i.data+" j:"+j.data);
			if(i.data==j.data)
			{
				i=i.next;
				j=j.next;
			}
			else 
				return false;
		}
		Node link1=reverseRec(slowPointer.next,null,null);
		slowPointer.next=link1;
		return true;
	}



	void printList()
	{
		Node current=head;
		while(current!=null)
		{
			System.out.print(current.data+"  ");
			current=current.next;
		}
		System.out.println("");

	}



	//set the head of the merged list as the start node and assign to this.head
	//one list with min start will get modified
	void mergeTwoSorted(ArrayLinkedList liM1,ArrayLinkedList liM2)
	{
		Node curr1=liM1.head;
		Node curr2=liM2.head;

		Node tmpHead=null;
		Node modifierNode=null;
		while(curr1!=null && curr2!=null)
		{
			if(curr1.data < curr2.data)
			{
				if(modifierNode==null)
				{
					this.head=curr1;
					modifierNode=curr1;
					curr1=curr1.next;
				}
				else
				{
					modifierNode.next=curr1;
					modifierNode=curr1;
					curr1=curr1.next;
					
				}
			}
			else if(curr2.data < curr1.data)
			{
				if(modifierNode==null)
				{
					this.head=curr2;
					modifierNode=curr2;
					curr2=curr2.next;
				}
				else
				{
					modifierNode.next=curr2;
					modifierNode=curr2;
					curr2=curr2.next;
					
				}
			}
			else //duplicate nodes remove
			{
				if(modifierNode==null)
				{
					this.head=curr1;
					modifierNode=curr1;
					curr1=curr1.next;
					curr2=curr2.next;
				}
				else
				{
					modifierNode.next=curr1;
					modifierNode=curr1;
					curr1=curr1.next;
					curr2=curr2.next;
				}
			}

		}

		//once a list is empty=> just join the other list at the end
		if(curr1==null)
		{
			modifierNode.next=curr2;
		}
		if(curr2==null)
		{
			modifierNode.next=curr1;
		}

	}


	//insert node with data value d in sorted list
	/*
		case 1:head==null
			update head
		case 2: d<head.data
			update head and n.next
		case 3: not at head
		go till you find biggest smaller element then d 
		update its pre and its ownn next
		if inserting at the end,update last.
	*/
	void insertNodeInSortedList(int d)
	{
		Node n=new Node(d);
		//empty list so insert at the front
		if(head==null)
		{
			head=n;
			last=n;
			return;
		}
		//data is less than head so insert at the front.
		if(d<head.data)
		{
			n.next=head;
			head=n;
			return;
		}
		Node current=head;
		Node pre=null;
		//go till you find greater element than d so pre will be biggest smaller!
		while(current!=null && current.data<=d)
		{
			if(current.data==d)
				return;
			pre=current;
			current=current.next;
		}
		Node temp=pre.next;
		pre.next=n;
		n.next=temp;
		//update last
		if(n.next==null)
			last=null;

	}	


	


	//remove duplicates from sorted list.
	/*
		track pre and current if both are same 
		pre.next=current.next;
		current=pre;
	*/
	void removeDuplicatesInSortedList()
	{
		Node pre=null;
		Node current=head;
		if(head==null)
			return;
		while(current.next!=null)
		{
			pre=current;
			current=current.next;
			if(pre.data==current.data)
			{
				pre.next=current.next;
				current=pre;
			}
		}
	}



	//remove duplicates from unsorted list
	/*
		use hashMap, 
		if data value already present in map
			same as current's data,
			pre.next=current.next;
			current=pre.next;
		else
			just simply increment pre and current
	*/
	void removeDuplicatesInUnsortedList()
	{
		HashMap<Integer,Boolean> hm=new HashMap<Integer,Boolean>();
		Node current=head;
		Node pre=null;
		while(current!=null)
		{
			if(hm.containsKey(current.data))
			{
				pre.next=current.next;
				current=pre.next; //dont change pre! only current
			}
			else
			{
				hm.put(current.data,true);
				pre=current;
				current=current.next;
			}
		}
	}


	
	void intersectionOfLists(ArrayLinkedList list1,ArrayLinkedList list2)
	{
		Node n1=list1.head;
		Node n2=list2.head;
		head=intersection(n1,n2);
	}
	Node intersection(Node n1,Node n2)
	{
		if(n1==null || n2==null)
			return null;
		if(n1.data<n2.data)
			return intersection(n1.next,n2);
		if(n2.data<n1.data)
			return intersection(n1,n2.next);
		if(n1.data==n2.data)
		{
			Node n=new Node(n1.data);
			n.next=intersection(n1.next,n2.next);
			return n;
		}
		return null; //no use statement!
		
	}


	
	
	void alternatingSplit(ArrayLinkedList l)
		{ 
			if(head==null)
				return;
			Node head1=head;
			Node last1=head1;
			Node head2=null;
			Node last2=null;
			Node current=head.next;
			int i=0;
			while(current!=null)
			{
				if(i%2==0)
				{
					if(head2==null)
					{
						head2=current;
						last2=head2;
						i++;
						current=current.next;
					}
					else
					{
						last2.next=current;
						last2=last2.next;
						i++;
						current=current.next;
					}
				}
				else
				{
					last1.next=current;
					last1=last1.next;
					i++;
					current=current.next;
				}
			}
			if(i%2==0)
				last2.next=null;
			else
				last1.next=null;
			//l1.head=head1;
			l.head=head2;
		}


		//find whether two given lists are identical or not
		boolean isIdentical(ArrayLinkedList l1,ArrayLinkedList l2)
		{
			Node n1=l1.head;
			Node n2=l2.head;
			boolean result=isIdentical(n1,n2);
			return result;
		}
		boolean isIdentical(Node n1,Node n2)
		{
			if(n1==null && n2==null)
				return true;
			else if(n1!=null && n2!=null)
			{
				if(n1.data==n2.data)
					return isIdentical(n1.next,n2.next);
				else
					return false;
			}
			else 
				return false;
		}

	//Merge Sort link-list
	//we use the same divide and conquer approach
	// to partition we need to write one function as well
	//1. partition
	//2. recursively call both half
	//3. until both have one element => call then mergeList


/****************************************** SORTING ***************************************/



	void mergeSort()
	{
       this.head=doMergeSort(head);
        Node current=head;
       while(current.next!=null && current!=null)
       		current=current.next;
       	last=current;
	}
	Node doMergeSort(Node head)
	{	
		//single node, or no node=> return that
		if(head==null || head.next==null)
			return head;

		Node middle=getMiddleElement(head);

		//divide the list into two parts
		Node front=head;
		Node back=middle.next;
		middle.next=null;

		//recursively call to partition list into two lists
		front=doMergeSort(front);
		back=doMergeSort(back);

		//front and back will contain the list into chunnks at each page
		//merge the list in bottom up approach
		//and set the head of the list and return
		head=mergeLists(front, back);
		return head;
	}

	//1st time call to merge=> only two nodes
	//then, it will always be two sorted list
	//so merge of two sorted list
	Node mergeLists(Node curr1,Node curr2)
	{
		Node head=null;
		Node modifierNode=null;

		//if any one list is empty, simply return the other
		if(curr1==null)
		{
			return curr2;
		}
		else if(curr2==null)
		{
			return curr1;
		}


		//modifier node is the one with correct order
		// curr1 and curr2 are compared and set to next of modifier

		while(curr1!=null && curr2!=null)
		{
			//<= becoz to preserve the order of nodes in original list
			if(curr1.data <= curr2.data)
			{
				if(modifierNode==null)
				{
					head=curr1;
					modifierNode=curr1;
					curr1=curr1.next;
				}
				else
				{
					modifierNode.next=curr1;
					modifierNode=curr1;
					curr1=curr1.next;
					
				}
			}
			else //(curr2.data < curr1.data)
			{
				if(modifierNode==null)
				{
					head=curr2;
					modifierNode=curr2;
					curr2=curr2.next;
				}
				else
				{
					modifierNode.next=curr2;
					modifierNode=curr2;
					curr2=curr2.next;
					
				}
			}
		}

		
		//once a list is empty=> just join the other list at the end
		if(curr1==null)
		{
			modifierNode.next=curr2;
		}
		if(curr2==null)
		{
			modifierNode.next=curr1;
		}

		return head;


	}//end of mergeLists



	//insertion sort of singly linked list**** O(n)
	/*
		1.make current=head.
		2.each time remove head from the actueal list.head=head.next.
		3.and set that current node to the new list which is already sorted.
		4.at the end make head=newHead.
	*/
	void insertionSort()
	{
		//id node hase zero or one element then return.
		if(head==null || head.next==null)
			return;
		//make new head and last node.
		Node newHead=null;
		Node newLast=null;
		//every time make head of actual list as current noode
		//remove it from actual list by making head=head.next 
		//now fit this current node in new list where it fits in sorted order.
		Node current=head;
		while(current!=null)
		{
			//change head to its next
			head=head.next;
			//make current node free from actual list
			//now current node is independent from th actual list
			current.next=null;
			//make first node of actual list as newHead of new list.
			if(newHead==null)
			{
				newHead=current;
				newLast=current;
			}
			//this step is used to complete this in O(n)
			//if actual list is already sorted
			else if(newLast.data<=current.data)
			{
				newLast.next=current;
				newLast=current;
			}
			else
			{
				//traverse the whole new list and insert current node where it fits
				Node traverse=newHead;
				Node pre=null;
				while(traverse.data<current.data && traverse!=null)
				{
					pre=traverse;
					traverse=traverse.next;
				}
				//if pre==null means current is smallest till now, so make it head
				if(pre==null)
				{
					current.next=newHead;
					newHead=current;
				}
				else
				{
					//normal insertion
					current.next=pre.next;
					pre.next=current;
				}
			}
			//again for next iteration make head node as current node
			current=head;
		}
		//main part
		//make new list's head as actual list's head
		head=newHead;
	}

/********************************* REMOVE SECTION **************************/
void removeAlternateNodes()
	{
		int i=0;
		Node pre=null;
		Node current=head;
		while(current!=null)
		{
			if(i%2==0)
			{
				pre=current;
				current=current.next;
				i++;
			}
			else
			{
				pre.next=current.next;
				current=current.next;
				i++;
			}
		}
	}


	//remove alternate nodes from a given list
	/*
		recursive method.
		if current.next.next!=null
			current.next=current.next.next;
		else current.next=null;
	*/
	void removeAlternateNodesRecurcive()
	{
		removeAlternateNodesRecurcive(head);
	}
	void removeAlternateNodesRecurcive(Node current)
	{
		if(current==null || current.next==null )
			return;
		if(current.next.next!=null)
			current.next=current.next.next;
		else
			current.next=null;
		removeAlternateNodesRecurcive(current.next);
	}




	//remove nodes from the list if its RHS immidiate node is bigger than it.
	void removeifrightSideNodeIsGreater()
	{
		removeifrightSideNodeIsGreater(null,head,head.next);
	}
	void removeifrightSideNodeIsGreater(Node pre,Node current,Node after)
	{
		if(current==null || current.next ==null)
			return;
		else if(current.data<after.data)
		{
			if(current==head)
				
			{
				head=after;
				removeifrightSideNodeIsGreater(null,head,head.next);
			}

			else
			{
				pre.next=after;
				removeifrightSideNodeIsGreater(pre,after,after.next);
			}
		}
		else
			removeifrightSideNodeIsGreater(current,after,after.next);
	}



	//remove node from the list if any RHS node is bigger than that node
	/*
		//first reverse the list
		//and assign max node
		//all nodes right side to it smaller than max must be deleted
		//which is same as given question in reverse order
		//then if max is lesser change max
		//in the end again reverse the list.
	*/
	void removeNodeifAnyRHSisBigger()
	{
		
		this.reverseR();
		Node max=head;
		Node current=head.next;
		if(head==null)
			return;
		while(current!=null)
		{
			if(max.data<=current.data)
			{
				max=max.next;
				current=current.next;
			}
			else
			{
				max.next=current.next;
				current=current.next;
			}
		}
		this.reverseR();
	}


/***************************************************************************/

	//for a given list shift even data nodes to the starting of the list 
	//without chnaging the order of even/odd elements inbeetween.	**** O(n)
	/*
		do this for n times!
		track pre and current node
		if current is even 
			simply go to next node
			pre=current;
			current=current.next;
		else
			shift odd data node to end of the list
	*/
	void evenDataFirst()
	{
		//shift all odd elements to the end of the list
		//it will not even change the order of odd and even elements
		Node pre=null;
		Node current=head;
		//Node tempLast=last;
		if(head==null || head.next==null)
			return;
		int count=size(head);
		while(count>0)
		{
			if(current.data%2==0)
			{
				pre=current;
				current=current.next;
				count--;
			}
			else
			{
				if(current==head)
					head=current.next;
				else
					pre.next=current.next;
				last.next=current;
				last=current;
				current=current.next;
				last.next=null;
				count--;
			}
		}
	}



	//for given two lists make another list wich reprents the sum of two lists
	/*
		3->2->6 number=623
		1->2	number=21
		new list: 4->4->6 number=644

		for both same index node sum it and make new node with that data
		if sum>9 carry will be 1 so pass it to next index's sum.
	*/
	void addtwoListNumber(ArrayLinkedList list1,ArrayLinkedList list2)
	{
		addtwoListNumber(list1.head,list2.head,0);
	}
	void addtwoListNumber(Node n1,Node n2,int carry)
	{
		//when both nodes are null then if carry is one-> add new node with data value 1.
		if(n1==null && n2==null)
		{
			if(carry>0)
			{
				Node n=new Node(1);
				last.next=n;
				last=last.next;
			}
			return;
		}
		else
		{
			int value=0;
			if(n1!=null && n2!=null)
				value=n1.data+n2.data+carry;
			else if(n1==null)
				value=n2.data+carry;
			else if(n2==null)
				value=n1.data+carry;
			carry=value/10;
			int m=value%10;
			Node n=new Node(m);
			if(this.head==null)
			{
				head=n;
				last=n;
			}
			else
			{
				last.next=n;
				last=last.next;
			}
			if(n1!=null && n2!=null)
				addtwoListNumber(n1.next,n2.next,carry);
			else if(n1==null)
				addtwoListNumber(null,n2.next,carry);
			else if(n2==null)
				addtwoListNumber(n1.next,null,carry);
		}
	}



	//rotate given list in counter clock wise direction*** O(n)
	/*
		input 12-20-30-40-50-60
		output 50-60-12-20-30-40
		joint first node with last node and change head to next node
		means let k first nodes go to the end of the list iteratively.
	*/
	void rotateList(int k)
	{
		while(k>0)
		{
			last.next=head;
			head=head.next;
			last=last.next;
			last.next=null;
			k--;
		}
	}


	//make new list from given 2 sorted lists for which sum of ele data is max
	/*
		we can only switch at intersaction points
		list1=10-20-30-40-50-60
		list2=15-30-75-200
		output=10-20-75-200
	*/
	ArrayLinkedList maximumSum(ArrayLinkedList l1,ArrayLinkedList l2)
	{
		//make two var to count sum till you find next common point in both lists
		int sum1=0,sum2=0;
		//make 2 pointers which points to current common points
		//or say from where we have to start adding nodes
		Node switch1=l1.head,switch2=l2.head;
		//2 nodes to traverse both th lists
		Node c1=l1.head,c2=l2.head;
		while(c1!=null && c2!=null)
		{
			//when data is not same just add both data value to their respective sum
			//and only increment that node through which you can find next common node
			//means use m,ethod of intersection.
			if(c1.data!=c2.data)
			{
				if(c1.data<c2.data)
				{
					//add that data into sum only when you are going to next 
					sum1=sum1+c1.data;
					c1=c1.next;
				}
				else
				{
					sum2=sum2+c2.data;
					c2=c2.next;
				}
			}
			//when data is same check for max sum and add that list from last common point
			else if(c1.data==c2.data)
			{
				if(sum1>=sum2)
				{
					while(switch1!=c1)
					{
						Node n=new Node(switch1.data);
						if(head==null)
						{
							head=n;
							last=n;
						}
						else
						{
							last.next=n;
							last=n;
						}
						switch1=switch1.next;
					}
					//change both switches to th current common points
					switch1=c1;
					switch2=c2;
					c1=c1.next;
					c2=c2.next;
					//put both sum zero to count max sum again till next common point
					sum1=0;
					sum2=0;
				}
				else if(sum2>sum1)
				{
					while(switch2!=c2)
					{
						Node n=new Node(switch2.data);
						if(head==null)
						{
							head=n;
							last=n;
						}
						else
						{
							last.next=n;
							last=n;
						}
						switch2=switch2.next;
					}
					switch1=c1;
					switch2=c2;
					c1=c1.next;
					c2=c2.next;
					sum1=0;
					sum2=0;
				}
			}
		}
		if(c1!=null || c2!=null)
		{
			if(c1!=null)
			{
				while(c1!=null)
				{
					sum1=sum1+c1.data;
					c1=c1.next;
				}
			}
			else
			{
				while(c2!=null)
				{
					sum2=sum2+c2.data;
					c2=c2.next;
				}
			}
		}
		if(sum1>=sum2)
			{
				while(switch1!=null)
					{
						Node n=new Node(switch1.data);
						if(head==null)
						{
							head=n;
							last=n;
						}
						else
						{
							last.next=n;
							last=n;
						}
						switch1=switch1.next;
					}
			}
			else if(sum2>sum1)
			{
				while(switch2!=null)
					{
						Node n=new Node(switch2.data);
						if(head==null)
						{
							head=n;
							last=n;
						}
						else
						{
							last.next=n;
							last=n;
						}
						switch2=switch2.next;
					}
			}	
		return this;
	}


	//compare two lists strings
	/*
		0:if both strings are same
		1:if string 1st is lexicografically greater than 2nd
		-1:if smaller
	*/
	int strcmp(ArrayLinkedList l1,ArrayLinkedList l2)
	{
		Node c1=l1.head;
		Node c2=l2.head;
		while(c1!=null && c2!=null && c1.data==c2.data)
		{
			c1=c1.next;
			c2=c2.next;
		}
		if(c1!=null && c2!=null)
			return (c1.data>c2.data)?1:-1;
		if(c1==null && c2==null)
			return 0;
		if(c1!=null && c2==null)
			return 1;
		if(c1==null && c2!=null)
			return -1;
		return 0;
	}
}

class ListOperation
{

	//union of two given lists**** O(mlogm+nlogn)
	/*
		start from index zero for both input lists
		compare both elements of the index and add smaller
		one to the new list and increment that lists pointer
		at the end if any one of the list is complete and other still,
		left then add that list to new list as it is.
	*/
	ArrayLinkedList union(ArrayLinkedList l1,ArrayLinkedList l2)
	{
		//sorting is optional no need to do this.
		l1.mergeSort();
		l2.mergeSort();
		//main output list
		ArrayLinkedList list=new ArrayLinkedList();
		int c1=l1.size();
		int c2=l2.size();
		int i=0,j=0;
		while(c1>0 && c2>0)
		{
			if(l1.get(i)<l2.get(j))
			{
				list.add(l1.get(i));
				i++;
				c1--;
			}
			else if(l2.get(j)<l1.get(i))
			{
				list.add(l2.get(j));
				j++;
				c2--;
			}
			//this is for eliminating insertion of multiple same elements.
			else if(l1.get(i)==l2.get(j))
			{
				list.add(l2.get(j));
				i++;
				j++;
				c1--;
				c2--;
			}
		}
		//for leftovers :D
		if(c1==0)
		{
			while(c2>0)
			{
				list.add(l2.get(j));
				j++;
				c2--;
			}
		}
		if(c2==0)
		{
			while(c1>0)
			{
				list.add(l1.get(i));
				i++;
				c1--;
			}
		}
		return list;
	}
                
}


/*
	 Useful for implementation of queue. 
	 Circular lists are useful in applications to repeatedly go around the list.
	 For example, when multiple applications are running on a PC, 
	 it is common for the operating system to put the running applications on a list 
	 and then to cycle through them, giving each of them a slice of time to execute, 
	 and then making them wait while the CPU is given to another application. 
	 It is convenient for the operating system to use a circular list so that 
	 when it reaches the end of the list it can cycle around to the front of the list. 

	 Circular Doubly Linked Lists are used for implementation of advanced data structures
	  like Fibonacci Heap. 
*/
class circularList
{
	class Node
	{
		int data;
		Node next;
		Node(int d)
		{
			data=d;
			next=null;
		}
	}
	Node head=null;
	Node last=null;

	void add(int d)
	{
		Node n=new Node(d);
		if(head==null)
		{
			head=n;
			last=n;
			last.next=head;
		}
		else
		{
			last.next=n;
			last=n;
			n.next=head;
		}
	}
	int size()
	{
		if(head==null)
			return 0;
		Node current=head;
		int c=0;
		do
		{
			c++;
			current=current.next;
		}
		while(current!=head);
		return c;
	}
	void printList()
	{
		if(head==null)
			return;
		Node current=head;
		do
		{
			System.out.print(current.data+" ");
			current=current.next;
		}
		while(current!=head);
		System.out.println("");
	}
}

/*
	Advantages over singly linked list
1) A DLL can be traversed in both forward and backward direction.
2) The delete operation in DLL is more efficient if pointer to the node to be deleted is given.
In singly linked list, to delete a node, pointer to the previous node is needed. 
To get this previous node, sometimes the list is traversed. In DLL, 
we can get the previous node using previous pointer.

Disadvantages over singly linked list
1) Every node of DLL Require extra space for an previous pointer. 
It is possible to implement DLL with single pointer though (See this and this).
2) All operations require an extra pointer previous to be maintained. 
For example, in insertion, we need to modify previous pointers together with next pointers. 
For example in following functions for insertions at different positions, 
we need 1 or 2 extra steps to set previous pointer.
*/
class DoublyList
{
	class Node
	{
		int data;
		Node next;
		Node prev;
		Node(int d)
		{
			data=d;
			next=null;
			prev=null;
		}
	}
	Node head=null;
	Node last=null;


	void add(int d)
	{
		Node n=new Node(d);
		if(head==null)
		{
			head=n;
			last=n;
		}
		else
		{
			last.next=n;
			n.prev=last;
			last=last.next;
		}
	}

	void printList()
	{
		Node current=head;
		while(current!=null)
		{
			System.out.print(current.data+"  ");
			current=current.next;
		}
		System.out.println("");

	}
 	
 	void reverse()
 	{
 		last=head;
 		Node after=null;
 		Node before=null;
 		Node current=head;
 		while(current!=null)
 		{
 			after=current.next;
 			current.next=before;
 			//new for doublyList.
 			current.prev=after;
 			before=current;
 			current=after;
 		}
 		head=before;
 	}


Node getMiddleElement(Node head)
	{
		Node fastPointer=head;
		Node slowPointer=head;
		while(fastPointer.next!=null && fastPointer.next.next!=null)
		{
			fastPointer=fastPointer.next.next;
			slowPointer=slowPointer.next;
		}			
		//System.out.println("middle element is:"+slowPointer.data+"\n");
		return slowPointer;
	}

 	void mergeSort()
	{
       this.head=doMergeSort(head);
       //just to handle last pointer.
       Node current=head;
       while(current.next!=null && current!=null)
       		current=current.next;
       	this.last=current;
	}
	Node doMergeSort(Node head)
	{	
		//single node, or no node=> return that
		if(head==null || head.next==null)
			return head;

		Node middle=getMiddleElement(head);

		//divide the list into two parts
		Node front=head;
		Node back=middle.next;
		middle.next=null;
		//new for DoublyList
		back.prev=null;

		//recursively call to partition list into two lists
		front=doMergeSort(front);
		back=doMergeSort(back);

		//front and back will contain the list into chunnks at each page
		//merge the list in bottom up approach
		//and set the head of the list and return
		head=mergeLists(front, back);
		return head;
	}

	//1st time call to merge=> only two nodes
	//then, it will always be two sorted list
	//so merge of two sorted list
	Node mergeLists(Node curr1,Node curr2)
	{
		Node head=null;
		Node modifierNode=null;

		//if any one list is empty, simply return the other
		if(curr1==null)
		{
			return curr2;
		}
		else if(curr2==null)
		{
			return curr1;
		}


		//modifier node is the one with correct order
		// curr1 and curr2 are compared and set to next of modifier

		while(curr1!=null && curr2!=null)
		{
			//<= becoz to preserve the order of nodes in original list
			if(curr1.data <= curr2.data)
			{
				if(modifierNode==null)
				{
					head=curr1;
					modifierNode=curr1;
					curr1=curr1.next;
				}
				else
				{
					modifierNode.next=curr1;
					//new for DoublyList
					curr1.prev=modifierNode;
					modifierNode=curr1;
					curr1=curr1.next;
					
				}
			}
			else //(curr2.data < curr1.data)
			{
				if(modifierNode==null)
				{
					head=curr2;
					modifierNode=curr2;
					curr2=curr2.next;
				}
				else
				{
					modifierNode.next=curr2;
					//new for DoublyList
					curr2.prev=modifierNode;
					modifierNode=curr2;
					curr2=curr2.next;
					
				}
			}
		}

		
		//once a list is empty=> just join the other list at the end
		if(curr1==null)
		{
			modifierNode.next=curr2;
			//new for DoublyList
			curr2.prev=modifierNode;
		}
		if(curr2==null)
		{
			modifierNode.next=curr1;
			//new for DoublyList
			curr1.prev=modifierNode;
		}

		return head;


	}//end of mergeLists
}



class TestList
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		ArrayLinkedList list=new ArrayLinkedList();
		/*System.out.println("enter number of elements you want to add in the list");
		int n=sc.nextInt();
		sc.nextLine();
		System.out.println("enter list data");
		for(int i=0;i<n;i++)
		{
			list.add(sc.nextInt());
		}*/
		list.add(7);
		list.add(2);
		list.add(3);
		list.add(6);
		list.add(1);
		list.add(9);
		list.add(11);
		//list.add(5);
		list.printList();

		/*list.add(2,135);// add in the middle somewhere
		list.add(0,115);// add at the start
		list.add(list.size(),155);//add at the end
		System.out.println(list.get(0)+","+list.get(2)+","+list.get(list.size()-1));
		list.remove(0);
		list.remove(2);
		list.remove(list.size()-1);
		list.set(2,135);*/
		//list.addFirst(110);
		//list.addLast(160);
		/*for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}*/
		/*list.swap(120,140);
		System.out.println("after swapping");
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}*/
		//list.swapWithoutData(13,11);
		/*System.out.println("after swapping");
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}*/
		/*System.out.println("enter a String:");
		String str=sc.nextLine();
		for(String s:str.split(" "))
		{
			int x=Integer.parseInt(s);
			list.add(x);
		}
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}*/
		/*System.out.println("after reverse");
		list.reverse();
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}*/
		//list.add(3,110);
		//list.addLast(170);
		//System.out.println("after adding");
		/*list.reverseR();
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}*/
		list.printMiddleElement();
		list.printNthNodeFromLast(3);
		//list.reverse();
		//list.reverseR();
		list.printList();
		list.last.next=list.head.next.next;
		boolean a=list.removeLoop();
		if(a)
			System.out.println("loop is present");
		else
			System.out.println("loop is not present");
		list.printList();
		list.printList();
		ArrayLinkedList list1=new ArrayLinkedList();
		list1.add(2);
		list1.add(3);
		list1.add(7);
		ArrayLinkedList list2=new ArrayLinkedList();
		list2.add(1);
		list2.add(3);
		list2.add(7);
		list2.add(9);
		list2.add(11);
		list2.add(12);
		list2.add(17);
		ArrayLinkedList mergeList=new ArrayLinkedList();
		//mergeList.mergeTwoSorted(list1,list2);
		mergeList.printList();
		ArrayLinkedList sortedList=new ArrayLinkedList();
		sortedList.add(2);
		sortedList.add(3);
		sortedList.add(5);
		sortedList.add(7);
		sortedList.add(9);
		sortedList.insertNodeInSortedList(3);
		sortedList.printList();
		ArrayLinkedList palindromList=new ArrayLinkedList();
		palindromList.add(1);
		palindromList.add(2);
		palindromList.add(3);
		palindromList.add(3);
		palindromList.add(2);
		palindromList.add(1);
		boolean p=palindromList.isPalindrom();
		if(p)
			System.out.println("list is palindrom");
		else
			System.out.println("list is not a palindrom");
		palindromList.printList();
		list.printReverse();
		ArrayLinkedList dList=new ArrayLinkedList();
		dList.add(2);
		dList.add(1);
		dList.add(3);
		dList.add(1);
		dList.add(3);
		dList.add(2);
		dList.add(2);
		dList.removeDuplicatesInUnsortedList();
		dList.printList();
		list.printList();
		list.swapPairWise();
		list.printList();
		ArrayLinkedList intersectionList=new ArrayLinkedList();
		intersectionList.intersectionOfLists(list1,list2);
		System.out.println("intersection");
		intersectionList.printList();
		//list2.removeAlternateNodesRecurcive();
		System.out.println("removeAlternateNodes");
		list2.printList();
		ArrayLinkedList l=new ArrayLinkedList();
		//ArrayLinkedList l2=new ArrayLinkedList();
		list2.alternatingSplit(l);
		
		//l2.printList();
		list2.printList();
		l.printList();
		ArrayLinkedList l1=new ArrayLinkedList();
		ArrayLinkedList l2=new ArrayLinkedList();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l2.add(1);
		l2.add(2);
		l2.add(3);
		l2.add(4);
		boolean result=list.isIdentical(l1,l2);
		if(result)
			System.out.println("lists are identical");
		else
			System.out.println("lists are not identical");
		ArrayLinkedList unsortedList=new ArrayLinkedList();
		unsortedList.add(7);
		unsortedList.add(4);
		unsortedList.add(1);
		unsortedList.add(8);
		unsortedList.add(2);
		unsortedList.add(3);
		//unsortedList.add(5);
		System.out.println("before merging");
		unsortedList.printList();
		unsortedList.mergeSort();
		System.out.println("after merging");
		unsortedList.printList();
		unsortedList.reverseByK(2);
		unsortedList.printList();
		//System.out.println(unsortedList.last.data);
		ArrayLinkedList ll=new ArrayLinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(4);
		ll.add(5);
		ll.add(6);
		ll.add(7);
		ll.add(8);
		ll.add(9);
		ll.printList();
		ll.reverseAlternateKNodes(3);
		ll.printList();
		ArrayLinkedList ll1=new ArrayLinkedList();
		ll1.add(12);
		ll1.add(15);
		ll1.add(10);
		ll1.add(11);
		ll1.add(5);
		ll1.add(6);
		ll1.add(2);
		ll1.add(3);
		ll1.printList();
		//ll1.removeifrightSideNodeIsGreater();
		//ll1.removeNodeifAnyRHSisBigger();
		ll1.evenDataFirst();
		ll1.printList();
		ArrayLinkedList ll2=new ArrayLinkedList();
		ll2.add(7);
		ll2.add(5);
		ll2.add(9);
		ll2.add(9);
		ll2.add(9);
		ArrayLinkedList ll3=new ArrayLinkedList();
		ll3.add(8);
		ll3.add(4);
		ArrayLinkedList ll4=new ArrayLinkedList();
		ll4.addtwoListNumber(ll2,ll3);
		ll2.printList();
		ll3.printList();
		ll4.printList();
		ListOperation obj=new ListOperation();
		ArrayLinkedList ans=obj.union(ll2,ll3);
		System.out.println("lists union");
		ans.printList();
		System.out.println("rotate list with k=4");
		ArrayLinkedList r=new ArrayLinkedList();
		r.add(10);
		r.add(20);
		r.add(30);
		r.add(40);
		r.add(50);
		r.add(60);
		r.rotateList(4);
		r.printList();
		ArrayLinkedList s=new ArrayLinkedList();
		s.add(15);
		s.add(30);
		//s.add(75);
		//s.add(200);
		s.add(41);
		s.add(51);
		s.add(61);
		ArrayLinkedList t=new ArrayLinkedList();
		//t=t.maximumSum(r,s);
		//System.out.println("maximum sum list");
		//t.printList();
		System.out.println("insertion sort:");
		r.insertionSort();
		r.printList();
		circularList cl1=new circularList();
		cl1.add(1);
		cl1.add(2);
		cl1.add(3);
		cl1.add(4);
		cl1.add(5);
		System.out.println("size of circularList is:"+cl1.size());
		cl1.printList();

		DoublyList d1=new DoublyList();
		d1.add(1);
		d1.add(2);
		d1.add(3);
		d1.add(4);
		d1.add(5);
		d1.add(6);
		d1.reverse();
		d1.printList();
		d1.mergeSort();
		d1.printList();
	}
}
