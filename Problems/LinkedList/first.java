import java.util.HashSet;
class Node
{
 	int data;
 	Node next;
 	
 	public Node(int data)
 	{
 		this.data=data;
 	}
}

class LinkList
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
		return head;
	}
	public void addFirst(int a)
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
	public void addLast(int a)
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
	public void add(int index,int data)
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
	public void removeFirst()
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
			head=head.next;
			count--;
		}
	}
	public void removeLast()
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
	void remove(int index)
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

	//we have to consider the key could be present at head or some other
	//
	void removeData(int key)
	{
		Node prev=null;
		Node curr=head;

		//if the key is found at head
		if(head.data==key)
		{
			head=head.next;
			return;
		}


		//if not at head, find all till end
		//keep prev and curr for linking
		while(curr!=null && curr.data!=key)
		{
			prev=curr;
			curr=curr.next;
		}

		//once it is found, link prev.next=curr.next
		//but only if found
		//curr reaches null if not found
		//track tail as well

		if(curr!=null)
			prev.next=curr.next;

		//if last node was removed, update tail
		if(prev.next==null)
			tail=prev;
	}

	int size()
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

	void displayList()
	{
		doDisplayList(head);
	}

	void displayListRev()
	{
		doDisplayListRev(head);
	}
	void doDisplayListRev(Node curr)
	{
		if(curr==null)
			return;
		doDisplayListRev(curr.next);
		System.out.print(curr.data+" ");
	}
	void doDisplayList(Node head)
	{
		Node temp=head;
		while(temp!=null)
		{
			System.out.print(temp.data+" ");
			temp=temp.next;
		}
		System.out.println();
	}
	public Node refNode(int data)
	{
		Node curr=head;
		while(curr!=null)
		{
			if(curr.data==data)
				return curr;
			curr=curr.next;
		}
		return null;

	}
	public void swapNodeWithoutData(int data1,int data2)
	{
		Node r1=refNode(data1);
		Node r2=refNode(data2);

		if(r1==null || r2==null)
		{
			System.out.println("data not present in the list");
			return;
		}

		//if both found
		Node p1=null;
		Node c1=head;
		while(c1!=r1)
		{
			p1=c1;
			c1=c1.next;
		}
		Node p2=null;
		Node c2=head;
		while(c2!=r2)
		{
			p2=c2;
			c2=c2.next;
		}

		if(p1==null) //r1 is head=>p1=null=>c1=head
		{
			head=r2;
			p2.next=r1;
			//swap next of r1.next and r2.next
			Node temp=r2.next;
			r2.next=r1.next;
			r1.next=temp;
		}
		

		else if(p2==null) //r2 is head=>p2=null=>c2=head
		{
			head=r1;
			p1.next=r2;
			//swap next of r1.next and r2.next
			Node temp=r2.next;
			r2.next=r1.next;
			r1.next=temp;
		}
		else
		{
			p1.next=r2;
			p2.next=r1;
			//swap next of r1.next and r2.next
			Node temp=r2.next;
			r2.next=r1.next;
			r1.next=temp;

		}
	}

	//static class
	static class NodeHead
	{
		static Node currHead;
	}
	void reverseIter()
	{
		NodeHead.currHead=head;
		doReverseIter(head);
		head=NodeHead.currHead;
	}
	void doReverseIter(Node head)
	{
		Node curr=head,next=null,prev=null;
		tail=curr;
		while(curr!=null)
		{
			next=curr.next;
			curr.next=prev;
			prev=curr;
			curr=next;
		}
		NodeHead.currHead=prev;
		System.out.println("after reversing:head="+head.data+" tail="+tail.data);
	}

	void reverseRec()
	{
		//head,prev,after
		head=doReverseRec(head,null,null);
	}
	Node doReverseRec(Node curr,Node prev,Node next)
	{
		//only to set the main LinkList head=>tail
		if(curr==head)
		{
			tail=curr;
		}
		//last node reached, this will be head now
		if(curr.next==null)
		{
			Node temp=curr;
			curr.next=prev;
			return temp;
		}

		next=curr.next;
		curr.next=prev;
		prev=curr;
		curr=next;

		return doReverseRec(curr,prev,next);
	}

	void findLength()
	{
		int lenI=findLengthIter(head);
		System.out.println("iterative approach=>length="+lenI);
		System.out.println();

		int lenR=findLengthRec(head);
		System.out.println("recursive approach=>length="+lenR);
		System.out.println();

	}

	int findLengthIter(Node curr)
	{
		int count=0;

		while(curr!=null)
		{
			curr=curr.next;
			count++;
		}
		return count;
	}

	int findLengthRec(Node curr)
	{
		if(curr==null)
			return 0;

		return 1+findLengthRec(curr.next);
	}


	void searchElement(int key)
	{
		boolean presentI=searchElementIter(head,key);
		System.out.println("iterative approach: element present="+presentI);
		System.out.println();

		boolean presentR=searchElementRec(head,key);
		System.out.println("recursive approach: element present="+presentR);
		System.out.println();	
	}

	boolean searchElementIter(Node curr,int key)
	{
		while(curr!=null)
		{
			if(curr.data==key)
				return true;
			curr=curr.next;
		}
		return false;
	}

	boolean searchElementRec(Node curr,int key)
	{
		if(curr==null)
			return false;

		if(curr.data==key)
			return true;

		return searchElementRec(curr.next,key);
	}


	int getNthNode(int pos)
	{
		Node curr=head;
		int count=1;
		while(curr!=null && pos!=count)
		{
			curr=curr.next;
			count++;
		}
		if(curr==null)
		{
			System.out.println("data is not present");
			return Integer.MIN_VALUE;
		}

		return curr.data;


	}

	//we can use recursion and have a count start from end
	void getNthNodeEnd(int pos)
	{
		getFromEndRec(head,pos);
		getFromEndIter(head,pos);
	}
	static class CountFromEnd
	{
		static int count;
	}
	void getFromEndRec(Node curr,int pos)
	{
		if(curr==null)
			return;
		getFromEndRec(curr.next,pos);
		CountFromEnd.count++;
		if(CountFromEnd.count==pos)
		{
			System.out.println("the "+pos+"th node from the end="+curr.data);
			return;
		}
	}

	//we take two pointers, one to start, one to nth loc+1 => difference is=pos
	//now carrry both one-one => one ptr last=> other will definately point to exact loc
	// bcoz the difference initially was of pos=> it remains
	void getFromEndIter(Node curr,int pos)
	{
		Node first=curr;
		Node last=curr;

		//count=1=>already  both points to head
		//so make last point to the next of the 3rd node if(pos==3)
		int count=1;
		while(count<=pos)
		{
			last=last.next;
			count++;
		}
		//now last points to node after the 3rd node
		//first points to 1st node
		//so move them together

		while(last!=null)
		{
			first=first.next;
			last=last.next;
		}

		System.out.println("the "+pos+"th node from the end="+first.data);
	}

	//we keep two pointers, slow and fast=head
	//fast will increment to next.next
	          //=> check if fast has a next => fast can point to next.next=> null
	          //=> coming back to the loop => fast is null
	         //=> condition for check is fast!=null && fast.next!=null

	/*
	while(fast.next!=null && fast.next.next!=null)
	=> 1 2 3 4 => middle =2

	while(fast!=null && fast.next!=null)
	=> 1 2 3 4 => middle =3
	*/
	Node printMiddle()
	{
		Node fast=head,slow=head;

		if(!isEmpty())
		{
			//even if one node is present=>fast.next=null => slow and fast still point to same
			//if two nodes=> fast.next!=null => fast=fast.next.next=> fast=null and slow=2nd node
			//if 3 nodes=> fast.next!=null => 1st iter=> fast point to 3rd node and slow point to 2nd node
			            //=> 2nd iteration, fast!=null  but fast.next==null=> break loop=> print middle as slow
			while(fast.next!=null && fast.next.next!=null)
			{
				fast=fast.next.next;
				slow=slow.next;
			}
			System.out.println("the middle is ="+slow.data);
			return slow;
		}
		else
		{
			System.out.println("list is empty");
			return null;
		}

	}

	//best way is to use slow and fast
	//each time slow=slow.next => fast=fast.next.next
	//if there is a loop present, then at some point of time in the loopp, they both will match

	//set conditions properly;
	//if only one node => fast=slow=head , and fast.next==null check => no cycle
	//if two node =>  fast=slow=head  and slow.next=2nd node & fast.next.next=null => next iteration=>check if fast=null too
	void detectLoop()
	{
		Node slow=head,fast=head;

		if(head==null || head.next==null)
		{
			System.out.println("no loop");
			return;
		}

		//&& is very imp => if 1st condition false, wont check the second (exception)
		while(fast!=null && fast.next!=null)
		{
			slow=slow.next;
			fast=fast.next.next;

			//if at point they both equals
			if(slow==fast)
			{
				System.out.println("we detect a loop");
				return;
			}
		}

		System.out.println("no loop");
	}

	Node getRefNode(int data)
	{
		Node curr=head;
		while(curr!=null && curr.data!=data)
			curr=curr.next;

		if(curr==null)
			System.out.println("node not present with data="+data);

		return curr;
	}


	//make src.next=dest
	void pointerChange(int src,int dest)
	{
		Node refSrc=getRefNode(src);
		Node refDest=getRefNode(dest);

		refSrc.next=refDest;
	}

	//set the head of the merged list as the start node and assign to this.head
	//one list with min start will get modified
	void mergeTwoSorted(LinkList liM1,LinkList liM2)
	{
		Node curr1=liM1.head;
		Node curr2=liM2.head;

		Node modifierNode=null;

		//modifier node is the one with correct order
		// curr1 and curr2 are compared and set to next of modifier

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

	//palindrome=> even length and odd length handle differently

	void isPalindrome()
	{
		
		Node middle=null;
		Node fast=head,slow=head;

		while(fast.next!=null && fast.next.next!=null)
		{
			fast=fast.next.next;
			slow=slow.next;
		}
		//here slow=>middle
		middle=slow;

		//next half of the list starts at slow.next;
		
		Node head1=head;
		Node head2=middle.next;

		//reverse of second half
		//print second half as it is
		System.out.println("second half");
		doDisplayList(head2);

		//reverse
		head2=doReverseRec(head2,null,null);

		//print the second half in reverse
		System.out.println("second half reversed");
		doDisplayList(head2);


		//compare head1 and head2 till end


		//we have to detach the list in the middle so as to compare both till null
		//curr will keep going till last if we dont detach it

		boolean equalHalf=compareList(head1,head2);
		if(equalHalf==true)
		{
			System.out.println("check plaindrome:yes");
		}
		else
		{
			System.out.println("check palindrome:no");
		}
        
        System.out.println();

	

		//we need to join the two half again

		//1. reverse the second half
		//2. attach the middle.next=head2
		head2=doReverseRec(head2,null,null);
		System.out.println("second half reversed again");
		doDisplayList(head2);
		//attach first and second half
		middle.next=head2;
		doDisplayList(head1);


		

	}

	//i dont need to compare for each,head1=> head2
	//but for odd length=> since head2 starts after the middle=> 
	//   only compare till head2!=null and equals for every other position
	boolean compareList(Node head1, Node head2)
	{
        Node curr1 = head1;
        Node curr2 = head2;
 
 		//only compare each till head2!=null (needed for odd length palindrome)
        while (curr2!=null) 
        {
        	if(curr1.data==curr2.data)
        	{
        	  curr1=curr1.next;
        	  curr2=curr2.next;
        	}
        	else
        	  return false;
        }
        return true;

	}

	//find the node, from where the two lists start to have all same nodes

	Node intersectFromNode(LinkList li2)
	{
		Node curr1=this.head;
		Node curr2=li2.head;


		//EXAMPLE => 3 6 7 9 12 15 18 30
		//                 1  2  5 18 30

		//get the counts of the list nodes
		//they should be same at the end=>
		// so find the difference, => skip through max list by difference nodes
		// start to compare now => is not equal both ahead
		// otherwise at some point they match=> return


		int count1=size(curr1);
		int count2=size(curr2);
		int diff=0;

		if(count1>count2)
		{
			diff=count1-count2;
			//move the 1st list ahead by diff 
			int pos=1;
			while(pos<=diff)
			{
				curr1=curr1.next;
				pos++;
			}
			//pointing to right location
		}
		else
		{
			diff=count2-count1;
			//move the 2nd list ahead by diff 
			int pos=1;
			while(pos<=diff)
			{
				curr2=curr2.next;
				pos++;
			}
			//pointing to right location
		}

		//both will match at some locations and from there they keep on(must according to question)
		//start matching both curr1 and curr2 for equality, otherwise both ahead and reset the RefNode
		Node refNode=null;
		boolean match=false;

		//any one list empty => both ends together
		while(curr1!=null)
		{
			if(curr1.data==curr2.data)
			{
				//first match
				if(match==false)
				{
					refNode=curr1;
					match=true;
				}
			}
			else //if they dont match now
			{
				refNode=null;
				match=false;
			}
			//anyway both next to one pos
			curr1=curr1.next;
			curr2=curr2.next;
		}

		return refNode;	
	}


	//merge both
	void intersectOfLists(LinkList li1,LinkList li2)
	{
		Node curr1=li1.head;
		Node curr2=li2.head;

		this.head=doRecurIntersect(curr1,curr2);
		System.out.println("intersetion done");
	}

	Node doRecurIntersect(Node curr1,Node curr2)
	{
		//any list empty=> simply end the process
		if(curr1==null || curr2==null)
			return null;

		if(curr1.data < curr2.data)
			return doRecurIntersect(curr1.next,curr2);

		else if( curr2.data < curr1.data)
			return doRecurIntersect(curr1,curr2.next);

		else //when both the nodes are same
		{
			Node newNode=new Node(curr1.data);
			curr1=curr1.next;
			curr2=curr2.next;
			newNode.next=doRecurIntersect(curr1,curr2);

			return newNode;
		}
	}
	//to remove duplicate we do curr and next Node
	void removeDuplicate()
	{
		if(head==null)
			return;

		Node Curr=head;
		Node Next=Curr.next;

		while(Curr.next!=null)
		{
			if(Curr.data==Next.data)
			{
				Curr.next=Next.next;
				Next=Next.next;
			}
			else
			{
				Curr=Curr.next;
				Next=Next.next;
			}
		}
	}

	void removeDuplicateUnsorted()
	{
		if(head==null)
			return ;

		Node curr=head;
		Node prev=null;
		HashSet<Integer> set=new HashSet<Integer>();

		while(curr.next!=null)
		{
			if(set.add(curr.data)==true)
			{
				//System.out.println("true");
				prev=curr;
				curr=curr.next;
			}
			else
			{
				prev.next=curr.next;
				curr=curr.next;
				//System.out.println("false");
			}
		}
	}


	//pair wise sawpping nodes
	//1. consider for the head and last node
	// 1 2 3 4 5 6
	// => 2->1  -> 4->3  ->6->5
	void swapPairWise()
	{
		Node curr=head;

		//if only one node=> not a problem
		Node nextSlot=null;

		Node first=head,second=head.next;
		if(second!=null)
			head=second;

		//first set nextSlot
		//make second point to first

		//then update both first and second to nextSlot's first and second
		while(second!=null)
		{
			//adjust nextSlot to next one
			nextSlot=second.next;
			
			
			//in the current slot

			//if no nodes are left on nextSlot
			if(nextSlot==null)
			{

				first.next=null;
				second.next=first;

				//update first and second
				first=null;
				second=null;


			}
			//if only one node left on nextSlot
			//point the first.next=nextSlot
			//and update the first to nextSlot.next
			else if(nextSlot.next==null)
			{
				first.next=nextSlot;
				second.next=first;
				//update first
				first=nextSlot;
				second=nextSlot.next;
				
			}
			//if both pairs present
			//first.next=nextSlot
			else
			{
			   first.next=nextSlot.next;
			   second.next=first;
			   //update first and second
			   first=nextSlot;
			   second=nextSlot.next;
			   
			}

		}

	}

	//delete alternate nodes
	// here, we have to keep track of the previous node and pointed to node

	void deleteAltNode()
	{
		Node prev=head;
		Node curr=head;;

		//if no Nodes ,curr==null => while loop exits
		//if one Node , curr.next==null => while exits
		//for others,while(true)

		while(curr!=null && curr.next!=null)
		{
			prev=curr;
			curr=curr.next.next;
			prev.next=curr;
		}
	}	

	void deleteAltSlot(int m,int n)
	{
		Node curr=this.head;
		//to count of m and n nodes
		int countm=1,countn=1;
		Node prevHead=null;
		boolean turn=true; //whose turn it is to delte or retain nodes

		while(curr!=null)
		{
			while(curr!=null && countm<=m && turn==true)
			{
				prevHead=curr;
				curr=curr.next;
				countm++;
				//if count > m => we reached the next slots node
				if(countm>m)
				{
					turn=false;
					countm=1;
				}
			}
			while(curr!=null && countn<=n && turn==false)
			{
				curr=curr.next;
				countn++;
				//if countn>n => we reached next slot node, break but join the next slot to prev slot
				if(countn>n)
				{
					prevHead.next=curr;
					countn=1;
					turn=true;
				}
			}
		}

		//if m has last slot=> do nothing , everything taken care of
		   // just update tail => prevHead is the only valid tail position


		//if n has last slot and slot was not complete
                  //=> countn>=2 and curr==null (both condtions are must)
				  //=> prevHead is the valid tail position

		//by default it was set to 1 for no nodes processed so far
		if(curr==null && countn>=2)
		{
			prevHead.next=null;

		}
		tail=prevHead;

		//check tail
		System.out.println("the updated tail of the deleted list="+tail.data);

	}


	//1.alternate split=> loop till curr!=null
	//2.and then, boolean flag for  each turn, 
	//3.keep the last node ref to link
	void alternateSplit()
	{
		//keep two heads for the two lists
		Node head1=null;
		Node head2=null;
		//keep two tails for the two lists, to simple add fast at end
		Node tail1=null;
		Node tail2=null;

		//to traverse the original list
		Node curr=head;
		//turn switches between 1 and 2
		boolean turn=true;


		while(curr!=null)
		{
			//make the new node
			Node newNode=new Node(curr.data);


			if(turn==true)
			{
				//first node
				if(head1==null)
				{
					head1=newNode;
					tail1=newNode;
				}
				else
				{
					tail1.next=newNode;
					tail1=newNode;
				}

				turn=false;
			}
			else
			{
				//first node
				if(head2==null)
				{
					head2=newNode;
					tail2=newNode;
				}
				else
				{
					tail2.next=newNode;
					tail2=newNode;
				}

				turn=true;
			}
			curr=curr.next;
		}//while loop end


		//once the list is ended and spliited
		//print the two lists
		System.out.println("the original list:");
		doDisplayList(this.head);
		System.out.println("the sub-first list:");
		doDisplayList(head1);
		System.out.println("the sub-second list:");
		doDisplayList(head2);
		System.out.println();
	}


	//merge the second into first at every alternate position
	// must list1<=list2 otherwise,dont do
	void mergeTwoIntoOneAlternate(LinkList li2)
	{
		Node curr1=this.head;
		Node curr2=li2.head;

		if(size(curr2)< size(curr1))
		{
			System.out.println("not possible to do this as size of list one is less");
			return;
		}

		//go until the first list empties
		Node prev1=null,prev2=null;
		while(curr1!=null)
		{
			prev1=curr1;
			curr1=curr1.next;

			prev2=curr2;
			curr2=curr2.next;

			prev1.next=prev2;
			prev2.next=curr1;
		}

		this.tail=prev2;
		//firstlist should contain all relevant nodes
		//second list empty

		System.out.println("first list head="+this.head.data+" tail="+this.tail.data);
		if(curr2!=null)
		{
			li2.head=curr2;
			System.out.println("first list head="+li2.head.data+" tail="+li2.tail.data);
		}
		else
		{
			li2.head=null;
		}

	}

	/*
	while(fast.next!=null && fast.next.next!=null)
	=> 1 2 3 4 => middle =2

	while(fast!=null && fast.next!=null)
	=> 1 2 3 4 => middle =3
	*/
	Node getMiddleNode(Node head)
	{
		Node slow=head,fast=head;

		//0 or 1 node
		if(fast==null || fast.next==null)
			return fast;

		while(fast.next!=null && fast.next.next!=null)
		{
			slow=slow.next;
			fast=fast.next.next;
		}
		return slow;
	}

	//Merge Sort link-list
	//we use the same divide and conquer approach
	// to partition we need to write one function as well
	//1. partition
	//2. recursively call both half
	//3. until both have one element => call then mergeList

	void mergeSort()
	{
       this.head=mergeSort(head);
	}
	Node mergeSort(Node head)
	{	
		//single node, or no node=> return that
		if(head==null || head.next==null)
			return head;

		Node middle=getMiddleNode(head);

		//divide the list into two parts
		Node front=head;
		Node back=middle.next;
		middle.next=null;

		//recursively call to partition list into two lists
		front=mergeSort(front);
		back=mergeSort(back);

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


	void mergeSortRev()
	{
       this.head=mergeSortRev(head);
	}
	Node mergeSortRev(Node head)
	{	
		//single node, or no node=> return that
		if(head==null || head.next==null)
			return head;

		Node middle=getMiddleNode(head);

		//divide the list into two parts
		Node front=head;
		Node back=middle.next;
		middle.next=null;

		//recursively call to partition list into two lists
		front=mergeSortRev(front);
		back=mergeSortRev(back);

		//front and back will contain the list into chunnks at each page
		//merge the list in bottom up approach
		//and set the head of the list and return
		head=mergeListsRev(front, back);
		return head;
	}

	//1st time call to merge=> only two nodes
	//then, it will always be two sorted list
	//so merge of two sorted list
	Node mergeListsRev(Node curr1,Node curr2)
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
			if(curr1.data >= curr2.data)
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




	void reverseInSize(int k)
	{
		int count=1;
		if(head==null || head.next==null)
			return;

	  	//prevHead and next point to the respective slot's prev and next
		Node curr=head;
		Node head=this.head;
		Node prevHead=this.head;
		Node next=null;

		while(curr!=null)
		{
			count++;
			curr=curr.next;
			//on third node pointing
			//it might have pointed to the 3rd node and there is no third node( therefore curr!=null)
			if(count==k && curr!=null)
			{
				next=curr.next;
				curr.next=null;
				if(head==this.head)
				{
					this.head=doReverseRec(head,null,null);
					count=1;
				}
				else
				{
					Node tempHead=head;
					head=doReverseRec(head,null,null);
					prevHead.next=head;
					prevHead=tempHead;
					count=1;
				}

			   curr=next;
			   head=next;
			}
	    }
	     
	     	//System.out.println("last slot");
	     	//set tail at the end
			if(curr==null && count>1)
			{	
				Node tempHead=head;
				head=doReverseRec(head,null,null);
				prevHead.next=head;
				tail=tempHead;

			}
			else
			{
				tail=prevHead;
			}


	}//end of reverseInSize

	void reverseInSizeAlternate(int k)
	{
		int count=1;
		if(head==null || head.next==null)
			return;

	  	//prevHead and next point to the respective slot's prev and next
		Node curr=head;
		Node head=this.head;
		Node prevHead=this.head;
		Node next=null;

		boolean turn=false;

		while(curr!=null)
		{
			count++;
			curr=curr.next;
			//on third node pointing
			//it might have pointed to the 3rd node and there is no third node( therefore curr!=null)
			if(count==3 && curr!=null)
			{
				next=curr.next;
				curr.next=null;
				if(head==this.head)
				{
					this.head=doReverseRec(head,null,null);
					count=1;
					turn=true;
				}
				else if(turn==false)
				{
					Node tempHead=head;
					head=doReverseRec(head,null,null);
					prevHead.next=head;
					prevHead=tempHead;
					count=1;
					turn=true;
				}
				else
				{
					prevHead.next=head;
					curr.next=next;
					prevHead=curr;
					turn=false;
					count=1;
				}

			   curr=next;
			   head=next;
			}
	    }
	     
	     	System.out.println("last slot"+turn);
	     	//set tail at the end

	    	//turn=false
	    	//=>do reverse else dont

			if(curr==null && count>1 && turn==false)
			{	
				Node tempHead=head;
				head=doReverseRec(head,null,null);
				prevHead.next=head;
				tail=tempHead;

			}
			else if(curr==null && count>1 && turn == true) //no reverse
			{
				    prevHead.next=head;
			}
			else
			{
				tail=prevHead;
			}

	}//end of function


	//if any element has greater element to its right=> delete that element
	// 5 1 2 3 6 8 2 3
	// 5 , 1, 2 , 3 , 6, should be delted,  and 8 and 3 only remains
	void deleteGreaterRHS()
	{	
		//1. reverse the list
		//2. set max to head, if node to its right smaller=> delete that node
		//3. if greater=> set that to nextMax	

		// 5 1 2 3 6 8 2 3
		
		if(head==null || head.next==null)
			return;

		head=doReverseRec(head,null,null);

		// 3 2 8 6 3 2 1 5

		Node curr=head.next;
		Node max=head;

		//curr = 2
		//max = 3

		while(curr!=null)
		{
			if(max.data > curr.data ) //intitally curr points to 2nd node
			{
				max.next=curr.next; //set maxNode.next= curr.next;
				curr=curr.next;
			}
			else //new max as curr.data
			{
				max=curr;
				curr=curr.next;
			}
		}

		//reverse the list
		head=doReverseRec(head,null,null);

		
	}

	void seggregateEvenOdd()
	{
		
		if(head==null || head.next==null)
			return;
		//i should go upto the original tail only, rest all will be adjusted 
		Node pointTail=tail;

		Node curr=head;
		Node prev=null;
		Node tempCurr=null;

		while(curr!=pointTail)
		{
			//for odd only head movement next
			if(curr.data%2==1)
			{
				if(curr==head)
					head=head.next;
				else
					prev.next=curr.next;

				//add any odd node to last 
				//and update tail accordinlgy
					tail.next=curr;
					tail=curr;
					curr=curr.next;
					tail.next=null;
			}
			else
			{
				prev=curr;
				curr=curr.next;
			}
			
		}

	}

	//tail will point to the refNode of data
	void setLoop(int data)
	{
		Node refNode=getRefNode(data);
		tail.next=refNode;

		System.out.println("head:"+head.data);
		System.out.println("tail:"+tail.data);
		System.out.println("tail pointing to node with data:"+tail.next.data);
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
	void detectAndRemoveLoop()
	{
		Node slow=head, fast=head;
	    boolean loop=false;

		//if there is no loop exit
		while(fast.next!=null && fast.next.next!=null)
		{
			slow=slow.next;
			fast=fast.next.next;

			//detect loop
			if(fast==slow)
			{	
				loop=true;
				System.out.println("we have a loop");
				break;
			}
		}

		if(loop==false)
		{
			System.out.println("we do not have a loop");
			return;
		}

		//if we have a loop, reached to this point

		//fast and slow are same=> set slow to head and take prev too 
		//             for pointing the last node to null when they match
		slow=head;
		Node prev=null;

		while(slow!=fast)
		{
			prev=fast;
			slow=slow.next;
			fast=fast.next;
		}

		//once iam out of while => both fast and slow matches and prev=> last node
		System.out.println("fast points to:"+fast.data);
		System.out.println("slow points to:"+slow.data);
		System.out.println("prev points to:"+prev.data);
		prev.next=null;

	}


	//sum of both lists uses extra space
	void addListNode(LinkList l1,LinkList l2,int carry)
	{
		int sum=0,rem=0;
		Node tempNode=null;
		Node curr1=l1.head,curr2=l2.head;

		while(curr1!=null && curr2!=null)
		{
			
			
			sum=curr1.data+curr2.data+carry;
			rem=sum%10;
			carry=sum/10;
			Node newNode=new Node(rem);
			if(head==null)
			{
				head=newNode;
				tempNode=head;
			}
			else
			{
				tempNode.next=newNode;
				tempNode=newNode;
			}
			curr1=curr1.next;
			curr2=curr2.next;
		}

		//above process breaks if either one is empty
		if(curr1==null && curr2!=null)
		{
			while(curr2!=null)
			{
				sum=curr2.data+carry;
				rem=sum%10;
				carry=sum/10;
				Node newNode=new Node(rem);
				tempNode.next=newNode;
				tempNode=newNode;
				curr2=curr2.next;

			}
		}

		if(curr2==null && curr1!=null)
		{
			while(curr1!=null)
			{

				sum=curr1.data+carry;
				rem=sum%10;
				carry=sum/10;
				Node newNode=new Node(rem);
				tempNode.next=newNode;
				tempNode=newNode;
				curr1=curr1.next;
			}
		}

		//which ever node is created last, tail set to that

		//finally if there is any carry
		if(carry!=0)
		{
			Node newNode=new Node(carry);
			tempNode.next=newNode;
			tempNode=newNode;
			tail=tempNode;
		}
		else
		{
			tail=tempNode;	
		}

		
		

		System.out.println("tail="+tail.data);

	}

	//addTwoList without extra space and without modifying the list
	void addListNode2(LinkList l1,LinkList l2,int carry)
	{
		addListNode2Rec(l1.head,l2.head,carry);
		System.out.println();
	}
	void addListNode2Rec(Node curr1,Node curr2,int carry)
	{
		//if both list empty without a carry, return
		if(curr1==null && curr2==null && carry==0)
			return;
		else if(curr1==null && curr2==null && carry!=0) //with carry
		{
			System.out.print(carry);
			return;
		}

		int sum=0,res=0;
		if(curr1!=null && curr2!=null)
		{
			sum=curr1.data+curr2.data+carry;
			res=sum%10;
			carry=sum/10;
			//output when return
			addListNode2Rec(curr1.next,curr2.next,carry);
		}
		else if(curr1==null)
		{
			//only add carry to curr2 and recur
			sum=curr2.data+carry;
			res=sum%10;
			carry=sum/10;
			addListNode2Rec(curr1,curr2.next,carry);
		}
		else
		{
			//only curr1 nodes left
			sum=curr1.data+carry;
			res=sum%10;
			carry=sum/10;
			addListNode2Rec(curr1.next,curr2,carry);	
		}

		//this is the result we get when we sum the two numbers
		//we print them when we return from recursion
		System.out.print(res);
	}


	//print triplets=> map it to the array problem
	//find two elements sum=given sum [calcSum<sum low++ ,else high--]
	//here we have 3 lists=> first list pick one by one
	// two new list => one single array sorted => sort 2nd list ascending, sort 3rd list descending
	//now compare the sum first-3-of-all-3 => if calSum<sum list2++ else if calcSum>sum list3++ else eqaul=>both ++

	void findTriplets(LinkList l2,LinkList l3,int sum)
	{
		//traverse using node
		Node n1=this.head;
		Node n2=l2.head;
		Node n3=l3.head;

		//to sort l2 in asce
		//mergeSort(head) => return head of the sorted list
		n2=mergeSort(n2);

		//to sort l3 in desc order, change mergeSortRev
		n3=mergeSortRev(n3);
		System.out.println("l1 as it is ");
		doDisplayList(n1);
		System.out.println("l2 in ascending order");
		doDisplayList(n2);
		System.out.println("l3 in desc order");
		doDisplayList(n3);

		//for each of l1, l2 and l3 movement

		while(n1!=null)
		{
			//start both the list from begining for each element of n1
			Node b=n2;
			Node c=n3;
			//if any one reaches end=> take next elem from n1
			while(b!=null && c!=null)
			{
				int calcSum=n1.data+b.data+c.data;

				if(calcSum < sum) //we can inc l2 to get some sum
					b=b.next;
				else if(calcSum > sum)  //we can dec array=> inc l3
				 	c=c.next;
				else //sum found inc both
				{
 					System.out.println("found sum of: "+calcSum);
 					System.out.println("triplets:"+n1.data+" "+b.data+" "+c.data);

 					//update a and b
 					b=b.next;
 					c=c.next;
				}

			}	
			n1=n1.next;
		}
		System.out.println("return from triplets");

		//set the list properly

		l2.head=n2;
		l3.head=n3;
	} //end of function triplets


	//if left>=the size of the list=> invalid operation
	void rotateListLeft(int left)
	{
		Node curr=this.head;
		if(left>=size(curr))
		{	
			System.out.println("invalid operation");
			return;
		}

		//go to the kth node, which will be new head
		int count=1;
		//this will break for the kth node
		while(count<left)
		{
			count++;
			curr=curr.next;
		}	

		Node newLast=curr;
		Node newHead=curr.next;
		Node oldHead=this.head;
		Node oldTail=tail;

		//now change pointers
		this.head=newHead;
		this.tail=newLast;
		this.tail.next=null;
		oldTail.next=oldHead;

 		System.out.println("new head="+this.head.data+" new tail="+this.tail.data);
	}//end of rotate left function


	//maximum sum list intersect at common nodes allowed
	//create a new list that has maximum possible sum of nodes
	void maxSumListIntersectCommonNodes(LinkList li1,LinkList li2)
	{
		this.head=maxSumList(li1.head,li2.head);
	}
	Node maxSumList(Node curr1,Node curr2)
	{
		return null;
		
	}
	
	//a list where node has two pointers=> next and random
	//how to clone in O(n) with constant time space
	void cloneLinkedList()
	{	

		//1. create a copy of node1 and insert it between node1 and node2
		//create a copy of node2 and insert it between node2 and node3
		//.....continue in this fashion till nth node

		//2. originalList->next->arbitrary = originalList->arbitrary->next

		//restore the original list
		//3. original->next = original->next->next;
        //copy->next = copy->next->next;

	}
	


}//end of class LinkList

public class SingleLinkedListOperations
{
	static void pl(String s)
	{
		System.out.println(s);
	}
	static void pl()
	{
		System.out.println();
	}
	public static void main(String[]args)
	{

		LinkList li=new LinkList();
		
		li.addFirst(10);li.addLast(20);li.addFirst(9);li.add(2,18);
		li.add(2,15);li.add(2,14);
		pl();
		pl("after inserting the values:the list is");
		li.displayList();
		pl();
						
		pl("removing from the front");
		li.removeFirst();
		pl("after removing the first list is:");
		li.displayList();
		pl();
		pl();
		pl("removing from the last");					
		li.removeLast();
		pl("after removing the last list is:");
		li.displayList();
		pl();
		pl();
					
		pl("removing from a particular index");
		int rmIndex=3;
		li.remove(rmIndex);
		pl("after removing from index="+rmIndex+" the list is:");
		li.displayList();
		pl();
		pl();

		pl("remove the element");
		int rmData=15;
		li.removeData(rmData);
		pl("after removing data="+rmData+" list is:");
		li.displayList();
		pl();
		pl();
			
		pl("again inserting:list becomes");	
		li.addFirst(9);	li.addLast(18);li.addLast(20);		
		li.displayList();
		pl();
		pl();
		
					
		pl("reversing list: Iterative");
		li.reverseIter();
		pl("after reverse");
		li.displayList();
		pl();
		pl();


		pl("reversing list: Recursive");
		li.reverseRec();
		pl("after reverse");
		li.displayList();
		pl();
		pl();

		pl("find the length recursive/iterative");
		li.findLength();
		pl();
		pl();

		pl("search an element recursice/iterative");
		int elem=20;
		li.searchElement(elem);
		pl();
		pl();

		
		int nth=3;
		pl("get the nth node:"+nth);
		li.displayList();
		int nthData=li.getNthNode(nth);
		pl("the data in the "+nth+"th node="+nthData);
		pl();
		pl();

		//here we are considering on even division=> next higher number
		// 1,2 => middle =2
		pl("print the middle of the list");
		li.displayList();
		li.printMiddle();
		pl();
		li.addFirst(7);
		pl("print the middle of the list");
		li.displayList();
		li.printMiddle();
		pl();


		int endPos=3;
		li.displayList();
		pl("get the Nth node from the end, n="+endPos);
		li.getNthNodeEnd(endPos);
		pl();
		pl();


		pl("check if loop present or not");
		li.displayList();
		li.detectLoop();
		pl();
		pl();

		pl("make 18 point to 9");
		li.pointerChange(18,9);
		//since the list has loop so dont print
		//li.displayList();
		li.detectLoop();
		pl();
		pl();


		pl("-----MERGE TWO SORTED LIST-----");
		LinkList liM1=new LinkList();
		liM1.addLast(1);liM1.addLast(3);
		liM1.addLast(9);liM1.addLast(11);
		liM1.addLast(12);liM1.addLast(17);

		LinkList liM2=new LinkList();
		liM2.addLast(1);liM2.addLast(4);
		liM2.addLast(9);liM2.addLast(13);
		liM2.addLast(22);liM2.addLast(25);
		liM2.addLast(32);

		pl("first list:");
		liM1.displayList();
		pl("second list:");
		liM2.displayList();
		pl("MERGING....");
		pl();
		LinkList newli=new LinkList();
		newli.mergeTwoSorted(liM1,liM2);
		pl("then new list: after merging:");
		newli.displayList();
		liM1.displayList();
		liM2.displayList();
		pl();
		pl();

		pl("---REVERSE printing of list ----");
		pl("forward:");
		newli.displayList();
		pl("backward:");
		newli.displayListRev();
		pl();
		pl();


		pl("----PALINDROME----");
		LinkList liP=new LinkList();
		liP.addLast(1);liP.addLast(2);liP.addLast(3);
		liP.addLast(4);
		liP.addLast(3);liP.addLast(2);liP.addLast(1);
		pl("the given list is:");
		liP.displayList();
		liP.isPalindrome();
		pl("the final list again is:");
		liP.displayList();
		pl();
		pl();


		pl("----WHERE DO THE TWO LISTS starts to MERGE (NODE)--------");
		LinkList li1=new LinkList();
		li1.addLast(3);li1.addLast(6);li1.addLast(9);
		li1.addLast(15);li1.addLast(18);li1.addLast(30);

		LinkList li2=new LinkList();
		li2.addLast(10);li2.addLast(12);li2.addLast(14);
		li2.addLast(15);li2.addLast(18);li2.addLast(30);		

		pl("the link list 1:");
		li1.displayList();
		pl("the link list 2:");
		li2.displayList();
		pl();
		pl("find the node where they start to intersect");
		Node intersectNode=li1.intersectFromNode(li2);

		if(intersectNode==null)
			pl("no nodes are present where they merge");
		else
			pl("node with value="+intersectNode.data+" , starts the merging");

		pl();
		pl();

		pl("-----CREATE A THIRD LIST FROM INTERSECTION OF THE TWO SORTED LIST--------");
		LinkList s1=new LinkList();
		s1.addLast(1);s1.addLast(5);s1.addLast(8);s1.addLast(10);
		s1.addLast(11);s1.addLast(17);s1.addLast(21);

		LinkList s2=new LinkList();
		s2.addLast(0);s2.addLast(1);s2.addLast(3);s2.addLast(7);
		s2.addLast(10);s2.addLast(12);s2.addLast(16);

		pl("the list1:");
		s1.displayList();
		pl("the list2:");
		s2.displayList();
		pl();
		pl("after creating a new list with intersection of the two:");
		LinkList s3=new LinkList();
		s3.intersectOfLists(s1,s2);
		s3.displayList();
		pl();
		pl();


		pl("----REMOVE DUPLICATES FROM THE SORTED LIST-----");
		LinkList liSorted=new LinkList();
		liSorted.addLast(1);liSorted.addLast(1);
		liSorted.addLast(2);liSorted.addLast(2);
		liSorted.addLast(3);liSorted.addLast(4);
		liSorted.addLast(5);liSorted.addLast(5);
		liSorted.addLast(5);liSorted.addLast(5);
		liSorted.addLast(6);liSorted.addLast(7);
		pl("the list is:");
		liSorted.displayList();
		liSorted.removeDuplicate();
		pl("after removing duplicate:list is:");
		liSorted.displayList();
		pl();
		pl();


        pl("----REMOVE DUPLICATES FROM THE UNSORTED LIST-----");
		LinkList liUnsorted=new LinkList();
		liUnsorted.addLast(7);liUnsorted.addLast(1);
		liUnsorted.addLast(2);liUnsorted.addLast(4);
		liUnsorted.addLast(6);liUnsorted.addLast(5);
		liUnsorted.addLast(3);liUnsorted.addLast(5);
		liUnsorted.addLast(5);liUnsorted.addLast(1);
		liUnsorted.addLast(3);liUnsorted.addLast(7);
		pl("the list is:");
		liUnsorted.displayList();
		liUnsorted.removeDuplicateUnsorted();
		pl("after removing duplicate:list is:");
		liUnsorted.displayList();
		pl();
		pl();

		pl("-----PAIR WISE SWAP NODES----");
		LinkList liPair=new LinkList();
		liPair.addLast(2);liPair.addLast(3);
		liPair.addLast(5);liPair.addLast(6);
		liPair.addLast(8);liPair.addLast(9);
		liPair.addLast(11);liPair.addLast(12);

		pl("the list is:");
		liPair.displayList();
		pl("pairWise swapping....");
		liPair.swapPairWise();
		liPair.displayList();
		pl();
		pl();

		pl("--------DELETE ALTERNATE NODES OF A LINKED LIST-----");
		LinkList altNodes=new LinkList();
		altNodes.addLast(4);altNodes.addLast(8);
		altNodes.addLast(3);altNodes.addLast(6);
		altNodes.addLast(2);altNodes.addLast(7);
		altNodes.addLast(12);
		pl("the list:");
		altNodes.displayList();
		altNodes.deleteAltNode();
		pl("after deleting alternate nodes: list:");
		altNodes.displayList();
		pl();
		pl();

		int malt=3,nalt=2;
		pl("-------ALTERNATE RETAIN M NODES AND DELETE N NODES-----:m="+malt+" n="+nalt);
		LinkList altMandNnodes=new LinkList();
		altMandNnodes.addLast(5);altMandNnodes.addLast(2);altMandNnodes.addLast(1);
		altMandNnodes.addLast(3);altMandNnodes.addLast(6);altMandNnodes.addLast(7);
		altMandNnodes.addLast(8);altMandNnodes.addLast(9);altMandNnodes.addLast(2);
		altMandNnodes.addLast(1);altMandNnodes.addLast(2);altMandNnodes.addLast(3);
		pl("list is:");
		altMandNnodes.displayList();
		//function call
		altMandNnodes.deleteAltSlot(malt,nalt);
		pl("after retain m nodes and delete n nodes alternatively:");
		altMandNnodes.displayList();

		pl("------CREATE TWO LISTS FROM ALTERNATE NODES ---------");
		LinkList origList=new LinkList();
		origList.addLast(2);origList.addLast(3);
		origList.addLast(4);origList.addLast(5);
		origList.addLast(6);origList.addLast(7);
		origList.addLast(8);origList.addLast(9);

		pl("the orig list:");
		origList.displayList();
		pl("after splitting the list alternatively:");
		origList.alternateSplit();
		pl();
		pl();

		pl("-------MERGE TWO LISTS INTO FIRST ONE AT EVERY ALTERNATE POSITION-----");
		pl("second list cant be less than first, but list2>=list1");
		LinkList one=new LinkList();
		one.addLast(1);one.addLast(3);one.addLast(5);one.addLast(7);
		LinkList two=new LinkList();
		two.addLast(2);two.addLast(4);two.addLast(6);two.addLast(8);two.addLast(10);two.addLast(12);
		pl("list1:");
		one.displayList();
		pl("list2:");
		two.displayList();
		pl("starting with list1, alternate lists nodes into list1 and list2=remaining");
		one.mergeTwoIntoOneAlternate(two);
		pl("after merging into first:");
		pl("first list:");
		one.displayList();
		pl("second list:");
		two.displayList();
		pl();
		pl();

		pl("-----MERGE SORT OF LINKED LIST----");
		LinkList unsortedList=new LinkList();
		unsortedList.addLast(5);unsortedList.addLast(2);
		unsortedList.addLast(8);
		unsortedList.addLast(3);unsortedList.addLast(12);
		unsortedList.addLast(1);unsortedList.addLast(8);
		unsortedList.addLast(10);unsortedList.addLast(4);
		unsortedList.addLast(6);
		pl("before sorting the list is:");
		unsortedList.displayList();
		//call merge sort linklist
		unsortedList.mergeSort();
		pl("after sorting the list is:");
		unsortedList.displayList();
		pl();
		pl();

		pl("------REVERSE LINKED LIST IN CHUNKS OF K----");
		int k=3;
		LinkList newList=new LinkList();
		newList.addLast(1);newList.addLast(2);
		newList.addLast(3);newList.addLast(4);
		newList.addLast(5);newList.addLast(6);
		newList.addLast(7);newList.addLast(8);
		newList.addLast(10);

		pl("the list:");
		newList.displayList();
		pl("after reversing in chunks of:"+k);
		newList.reverseInSize(k);
		newList.displayList();
		pl();
		pl("after adding more elemts to the above list:");
		newList.addLast(100);
		newList.addLast(101);
		newList.addLast(102);
		newList.addLast(103);
		newList.displayList();
		pl("after reversing in chunks of:"+k);
		newList.reverseInSize(k);
		newList.displayList();
		pl();


		pl("-------REVERSE ALTERNATE GROUPS OF SIZE K-----");
		LinkList newList1=new LinkList();
		newList1.addLast(1);newList1.addLast(2);
		newList1.addLast(3);newList1.addLast(4);
		newList1.addLast(5);newList1.addLast(6);
		newList1.addLast(7);newList1.addLast(8);
		newList1.addLast(10);newList1.addLast(100);
		newList1.addLast(101);newList1.addLast(102);
		newList1.addLast(103);newList1.addLast(104);
		newList1.addLast(105);newList1.addLast(106);
		newList1.addLast(107);newList1.addLast(108);
		newList1.addLast(109);newList1.addLast(110);
		pl("the list:");
		newList1.displayList();
		pl("after reversing in chunks of:"+k);
		newList1.reverseInSizeAlternate(k);
		newList1.displayList();
		pl();
		pl();

		pl("-----DELETE NODES WHO HAS GREATER RHS------");
		LinkList greatDel=new LinkList();
		/*greatDel.addLast(12);greatDel.addLast(15);
		greatDel.addLast(10);greatDel.addLast(11);
		greatDel.addLast(5);greatDel.addLast(6);
		greatDel.addLast(2);greatDel.addLast(3);
		*/
		greatDel.addLast(5);greatDel.addLast(1);
		greatDel.addLast(2);greatDel.addLast(3);
		greatDel.addLast(6);greatDel.addLast(8);
		greatDel.addLast(2);greatDel.addLast(3);
		pl("the list:");
		greatDel.displayList();
		greatDel.deleteGreaterRHS();
		pl("after operation list is:");
		greatDel.displayList();
		pl();
		pl();

		pl("-----Seggregate EVEN AND ODD (EVEN followed by ODD)--------");
		LinkList eo=new LinkList();
		eo.addLast(1);eo.addLast(3);eo.addLast(2);
		eo.addLast(4);eo.addLast(5);eo.addLast(6);
		eo.addLast(7);eo.addLast(9);eo.addLast(12);
		eo.addLast(13);eo.addLast(15);eo.addLast(20);
		eo.addLast(21);
		pl("the list:");
		eo.displayList();
		eo.seggregateEvenOdd();
		pl("after seggregating even followed by odd:");
		eo.displayList();
		pl();
		pl();

		pl("-----DETECT AND REMOVE LOOP-----");
		LinkList loopLi=new LinkList();
		loopLi.addLast(1);loopLi.addLast(3);
		loopLi.addLast(5);loopLi.addLast(4);
		loopLi.addLast(7);loopLi.addLast(10);
		loopLi.addLast(12);
		//tail to node passed
		pl("before loop setting: li");
		loopLi.displayList();
		loopLi.setLoop(5);
		loopLi.detectAndRemoveLoop();
		pl("loop removed: list");
		loopLi.displayList();
		pl();
		pl();

		pl("------SUM OF TWO LISTS WITH EXTRA LIST-----");
		pl("we assume the numbers are known and we store them in reverse order");
		LinkList num1=new LinkList();
		num1.addLast(9);num1.addLast(9);
		//num1.addLast(9);num1.addLast(9);num1.addLast(9);
		pl("the list1:");
		num1.displayList();
		LinkList num2=new LinkList();
		num2.addLast(8);num2.addLast(8);num2.addLast(9);
		num2.addLast(9);
		num2.addLast(9);
		pl("the list2:");
		num2.displayList();
		pl("sum them on a third list:");
		LinkList sum=new LinkList();
		sum.addListNode(num1,num2,0);
		pl("the sum list is:");
		sum.displayList();
		pl("we need to reverse the list to get the actual sum");
		sum.reverseRec();
		sum.displayList();
		pl();
		pl();

		pl("-------SUM OF THE LISTS WITHOUT EXTRA SPACE------");
		pl("we assume the numbers are known and we store them in reverse order");
		pl("list1:");
		num1.displayList();
		pl("list2:");
		num2.displayList();
		pl();
		pl("the sum of elements in right order is:");
		LinkList sumWithoutStorage=new LinkList();
		sumWithoutStorage.addListNode2(num1,num2,0);
		pl();
		pl();


		int triSum=14;
		pl("-----FIND triplet in 3-LinkedList with sum ="+triSum);
		LinkList tr1=new LinkList();
		LinkList tr2=new LinkList();
		LinkList tr3=new LinkList();
		tr1.addLast(14);tr1.addLast(3);tr1.addLast(22);tr1.addLast(2);tr1.addLast(1);
		tr2.addLast(6);tr2.addLast(16);tr2.addLast(5);tr2.addLast(7);tr2.addLast(11);
		tr3.addLast(5);tr3.addLast(2);tr3.addLast(20);tr3.addLast(6);
		tr1.findTriplets(tr2,tr3,triSum);
		pl();
		pl("after returning list1 ,list2,list3");
		tr1.displayList();
		tr2.displayList();
		tr3.displayList();
		pl();
		pl();

		int left=4;
		pl("-------ROTATE linked list to k nodes to left------"+4);
		LinkList rotLi=new LinkList();
		rotLi.addLast(1);rotLi.addLast(2);rotLi.addLast(3);
		rotLi.addLast(4);rotLi.addLast(5);rotLi.addLast(6);
		//rotate by 1 => head=2
		//roatate by 5 => head=6
		//roatate by 6 => invalid
		pl("list is:");
		rotLi.displayList();
		rotLi.rotateListLeft(left);
		pl("after rotating left by:"+left+" the list is:");
		rotLi.displayList();
		pl();
		pl();

		pl("-------MAX SUM LINKED LIST- PATH CHANGE ALLOWED ONLY AT COMMON NODES----");
		LinkList sort1=new LinkList();
		sort1.addLast(1);sort1.addLast(5);sort1.addLast(7);sort1.addLast(12);
		sort1.addLast(13);sort1.addLast(18);
		LinkList sort2=new LinkList();
		sort2.addLast(2);sort2.addLast(5);sort2.addLast(8);sort2.addLast(9);
		sort2.addLast(12);sort2.addLast(16);
		pl("list1:");
		sort1.displayList();
		pl("list2:");
		sort2.displayList();
		pl();
		LinkList sortMax=new LinkList();
		sortMax.maxSumListIntersectCommonNodes(sort1,sort2);
		pl("after the operation: the max sum list:");
		sortMax.displayList();
		pl();
		pl();
    
	}//end main
}
