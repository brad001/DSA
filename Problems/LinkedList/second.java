class Node
{
 	int data;
 	Node next;
 	Node random;
 	public Node()
 	{

 	}
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
	void addLast(int a)
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
	

	//reverse total
	void reverse()
	{
		Node prev=null;
		Node curr=head;
		Node next=null;

		while(curr!=null)
		{
			next=curr.next;
			curr.next=prev;
			prev=curr;
			curr=next;
		}

		head=prev;
	}
	Node reverse(Node curr,Node prev,Node next)
	{
		//last node => make it head
		if(curr.next==null)
		{
			curr.next=prev;
			return curr;
		}

		next=curr.next;
		curr.next=prev;
		prev=curr;
		curr=next;
		return reverse(curr,prev,next);
	}

	//pH =prevHead
	//cH= currHead
	//nH=nextHead
	void reverseInside(int k)
	{
		Node cH=head;
		Node pH=null;
		Node nH=null;
		Node curr=head;
		int count=1;


		if(k>size())
		{
			reverse();
			return;
		}
		//at each slot
		//cH points to actual first element
		//nH keeps track of nextSlot
		//pH should link to cH after reversal of slot


		while(curr!=null)
		{
			count++;
			curr=curr.next;

			if(count==k && curr!=null)
			{
				//curr points to kth node
				nH=curr.next;
				curr.next=null;
				//link and then update pH,cH,nH
				//1. reverse the slot
				Node revHead=reverse(cH,null,null);
				if(head==cH)
					head=revHead;
				else
					pH.next=revHead;
				//update
				//2.prevHead=currHead
				pH=cH;
				cH=nH;
				curr=nH;
				count=1;
				
			}
		} //end while


		//suppose last slot was not multiple of k => 
		if(count>=2 && curr==null)
		{
			Node revHead=reverse(cH,null,null);
			pH.next=revHead;

		}

	}


	void reverseInsideAlternate(int k)
	{
		Node cH=head;
		Node pH=null;
		Node nH=null;
		Node curr=head;
		int count=1;


		if(size()<k)
		{
			reverse();
			return;
		}
		//at each slot
		//cH points to actual first element
		//nH keeps track of nextSlot
		//pH should link to cH after reversal of slot
		boolean turn=true;

		while(curr!=null)
		{
			count++;
			curr=curr.next;

			if(count==k && curr!=null && turn==true)
			{
				//curr points to kth node
				nH=curr.next;
				curr.next=null;
				//link and then update pH,cH,nH
				//1. reverse the slot
				Node revHead=reverse(cH,null,null);
				if(head==cH)
					head=revHead;
				else
					pH.next=revHead;
				//update
				//2.prevHead=currHead
				pH=cH;
				cH=nH;
				curr=nH;
				count=1;
				turn=false;
				
			}
			else if(count==k && curr!=null && turn==false)
			{

				nH=curr.next;

				pH.next=cH;

				pH=curr;
				cH=nH;
				curr=nH;
				turn=true;
				count=1;
			}
			
		} //end while


		//suppose last slot was true case(. bcoz we dont handle for false case)
		if(count>=2 && curr==null && turn==true)
		{
			Node revHead=reverse(cH,null,null);
			pH.next=revHead;
		}
		else //turn==fasle
		{
			pH.next=cH;
		}

	}

	void maxSumCommonNodes(LinkList l1,LinkList l2)
	{
		int s1=0,s2=0;
		Node curr1=l1.head,curr2=l2.head;
		Node result=this.head;
		Node prev1=null,prev2=null;

		//process till both list complete independently
		while(curr1!=null || curr2!=null)
		{
			while(curr1!=null && curr2!=null)
			{
				if(curr1.data<curr2.data)
				{
					s1=s1+curr1.data;
					curr1=curr1.next;
				}
				else if(curr2.data<curr1.data)
				{
					s2=s2+curr2.data;
					curr2=curr2.next;
				}
				else //equal break at this point
				{
					break;
				}	
			}

			//if they are equal
			//if anyone has reached end

			if(curr1==null)
			{
				while(curr2!=null)
				{
					s2=s2+curr2.data;
					curr2=curr2.next;
				}
			}
			
			if(curr2==null)
			{
				while(curr1!=null)
				{
					s1=s1+curr1.data;
					curr1=curr1.next;
				}
			}

			//after calcluating the sum till end or to the point where they hava common nodes

			if(result==null)
			{
				if(s1>=s2)
					result=l1.head;
				else
					result=l2.head;
			}
			else //already set result , now adjust links of common nodes
			//prev also set so adjust them
			{
				//make them point to that node which has higer sum ahead
				if(s1>=s2)
				{
					prev1.next=prev1.next;
					prev2.next=prev1.next;
				}
				else
				{
					prev2.next=prev2.next;
					prev1.next=prev2.next;
				}
			}

			//once they set based on s1 and s2
			s1=0;
			s2=0;

			//prev1 and prev2 point to common nodes
			prev1=curr1;
			prev2=curr2;

			//now move both curr ahead
			if(curr1!=null)
				curr1=curr1.next;
			if(curr2!=null)
				curr2=curr2.next;

			
		}//while

		//result now points to the corresponding head and al links set
			
			this.head=result;
	}//function maxSum


	void addLink(int src,int dest)
	{
		Node srcNode=refNode(src);
		Node destNode=refNode(dest);
		if(srcNode==null || destNode==null)
			return;
		srcNode.random=destNode;
	}
	void displayRandomOrder()
	{
		Node curr=head;
		while(curr!=null)
		{
			//print the random node curr points to
			if(curr.random==null)
				System.out.print("null"+ " ");
			else
			 	System.out.print(curr.random.data+" ");
			curr=curr.next;
		}

		System.out.println();
	}
	//we have three steps involved majorly
	//1. setup the dulicates nodes in between
	//2. set up the random nodes based on origList random
	//3. detach the two list on their own
	void cloneList(LinkList li1)
	{
		Node origHead=li1.head;
		Node duplHead=null;
		Node curr1=li1.head;
		Node curr2=null;
		Node next=null;

		//1st step (add dupl nodes)
		while(curr1!=null)
		{
			Node newNode=new Node(curr1.data);
			next=curr1.next;
			curr1.next=newNode;
			newNode.next=next;
			
			if(curr1==li1.head)
			{
				duplHead=curr1.next;
				curr2=duplHead;
			}
			
			curr1=next;
		}

		//2. set the random nodes
		//curr2 no points to duplHead
		curr1=li1.head;
		curr2=duplHead;

		while(curr2!=null)
		{
			//since curr1.random points to his own node, 
			//therfore .next points to list2 cloned node
			curr2.random=curr1.random.next;

			//when last node's random is set => curr2.next=null
			if(curr2.next==null)
				break;

			curr2=curr2.next.next;
			curr1=curr1.next.next;

		}

		//3. detach the two lists
		curr1=li1.head;
		curr2=duplHead;

		while(curr1!=null)
		{
			curr1.next=curr1.next.next;
			//each time we update the curr1.next , we can check only last node 
			//of curr1 has reached by curr1.next==null

			//once the curr1.next==null => list 1 reached end
			if(curr1.next==null)
				curr2.next=null;
			else
				curr2.next=curr2.next.next;

			curr1=curr1.next;
			curr2=curr2.next;
		}

		System.out.println("detach done");
		//set the head
		this.head=duplHead;

		//print the node address of next and random
		System.out.println("head.data of list1="+li1.head.data);
		System.out.println("head.data of list2="+this.head.data);
		System.out.println("head.random.data of list1="+li1.head.random.data);
		System.out.println("head.random.data of list2="+this.head.random.data);
		System.out.println("address of head and random node list1="+li1.head+" "+li1.head.random);
		System.out.println("address of head and random node list2="+this.head+" "+this.head.random);

	}

	//consider the acse for 2 and 3 nodes
	//write the condition for while loop acoordingly
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

	//1. store each node's next in random Node
	//2. sort the list
	//3. swap the random and next in sorted list 
	//   to get back the original list(with each node random pointing to next higher value)
	void pointToNextHigherNode()
	{
		//1. store each random
		Node curr=head;

		while(curr!=null)
		{
			curr.random=curr.next;
			curr=curr.next;
		}

		//2. merge sort to sort the list
		Node tempHead=mergeSort(head);
		//tempHead is min value
		Node currt=tempHead;
		System.out.println("the sorted list");
		while(currt!=null)
		{
			System.out.print(currt.data+" ");
			currt=currt.next;
		}
		System.out.println();

		//3. swap back rendom and next
		//we need to start from the orignial head otherwise
		//we may reach a node which was at the end in original list
		// and sorting brings it to and it had a next =null

		curr=head;
		while(curr!=null)
		{
			Node temp=curr.random;
			curr.random=curr.next;
			curr.next=temp;

			curr=curr.next;
		}

		//set the head to old head

	}


	//aleteranate first last nodes in-place
	//use the approach suggested in palindrome
	//1. find the middle
	//2. split from midlle.next
	//3. reverse the second half
	//4. one one from each list merge
	void firstLastAlternate()
	{	
		//1. find the middle
		Node middle=getMiddleNode(head);

		//split from there

		Node head2=middle.next;
		middle.next=null;

		head2=reverse(head2,null,null);

		//both list are half half
		Node curr1=this.head;
		Node curr2=head2;

		//odd elements => list1 gets 1 more list 2 gets half
		//even elemnts => both list gets half half
		Node next1=null;
		Node next2=null;

		//even nodes => both curr1 and curr2 together becomes null
		//odd nodes => curr2 becomes null and curr1 not
		//    => but last node of curr1 is pointed to by last node of curr1
		while(curr1!=null && curr2!=null)
		{
			next1=curr1.next;
			curr1.next=curr2;
			next2=curr2.next;
			curr2.next=next1;

			//update curr1 and curr2
			curr1=next1;
			curr2=next2;
		}

	}
}



public class reverseVariation
{
	static void pr(String s)
	{
		System.out.println(s);
	}
	public static void main(String[]args)
	{
		LinkList li1=new LinkList();
		li1.addLast(1);li1.addLast(2);li1.addLast(3);
		li1.addLast(4);li1.addLast(5);li1.addLast(6);
		li1.addLast(7);li1.addLast(8);li1.addLast(9);
		li1.addLast(11);li1.addLast(12);li1.addLast(13);
		li1.addLast(14);li1.addLast(15);

		pr("the list1 :");
		li1.displayList();

		pr("--------------------------");
		pr("call reverse() and list:");
		li1.reverse();
		li1.displayList();		
		li1.reverse();
		li1.displayList();		
		pr("--------------------------");
		pr("call reverse inside box(k)");
		int k1=3;
		li1.reverseInside(k1);
		li1.displayList();	
		li1.reverseInside(k1);
		li1.displayList();	
		pr("--------------------------");	
		pr("call reverse inside alternate box(k)");
		int k2=3;
		li1.reverseInsideAlternate(k2);
		li1.displayList();	
		li1.reverseInsideAlternate(k2);
		li1.displayList();	
		pr("--------------------------");	
		pr("maximum sum of two list-merge at common nodes");
		LinkList t1=new LinkList();
		t1.addLast(1);t1.addLast(2);t1.addLast(3);		
		t1.addLast(5);t1.addLast(6);t1.addLast(9);
		t1.addLast(10);t1.addLast(12);t1.addLast(15);t1.addLast(21);
		t1.addLast(30);t1.addLast(32);
		pr("list 1:");
		t1.displayList();
		LinkList t2=new LinkList();
		t2.addLast(0);t2.addLast(1);		
		t2.addLast(3);t2.addLast(6);t2.addLast(7);t2.addLast(13);
		t2.addLast(14);t2.addLast(15);t2.addLast(20);t2.addLast(22);
		t2.addLast(32);
		pr("list 2:");
		t2.displayList();
		LinkList sum=new LinkList();
		sum.maxSumCommonNodes(t1,t2);
		sum.displayList();
		pr("-------------------------");
		pr("cloning a list:with next and random pointer");
		LinkList lr1=new LinkList();
		lr1.addLast(1);lr1.addLast(2);lr1.addLast(3);
		lr1.addLast(4);lr1.addLast(5);
		lr1.displayList();

		//adding links, specify src and dest nodes position
		lr1.addLink(1,3);
		lr1.addLink(2,1);
		lr1.addLink(3,5);
		lr1.addLink(4,3);
		lr1.addLink(5,2);

		//print each nodes random
		pr("the random nodes for each node in sequence is:");
		lr1.displayRandomOrder();
		pr("Cloning the list now:...");
		LinkList cloneLi=new LinkList();
		cloneLi.cloneList(lr1);
		pr("original list:");
		lr1.displayList();
		pr("duplicate list:");
		cloneLi.displayList();

		pr("--------------------------");
		pr("each node must point to the next higher value node in the list: it can be anywhere:");
		LinkList lip1=new LinkList();
		lip1.addLast(5);lip1.addLast(10);
		lip1.addLast(2);lip1.addLast(6);lip1.addLast(3);
		// 5 -> 10 -> 2 -> 6 -> 3
		//we can make an extra arbitrary pointer stores the next node's address
		//and sort the list preserving the head
		// then swap each node's arbitrary and next node from the sorted
		lip1.displayList();
		lip1.pointToNextHigherNode();
		pr("after the operation of random nodes s");
		lip1.displayList();
		pr("the random nodes data for each node from start is:");
		lip1.displayRandomOrder();
		pr("---------------------------");
		pr("first last combination alternate nodes of list-in-place operation");
		LinkList li2=new LinkList();
		li2.addLast(1);li2.addLast(2);li2.addLast(3);
		li2.addLast(4);li2.addLast(5);li2.addLast(6);
		li2.addLast(7);li2.addLast(8);li2.addLast(9);

		pr("the list is :");
		li2.displayList();
		li2.firstLastAlternate();
		pr("after operation list :");
		li2.displayList();
		pr("--------------------------");
		
	}
}

/*
the list1 :
1 2 3 4 5 6 7 8 9 11 12 13 14 15 
--------------------------
call reverse() and list:
15 14 13 12 11 9 8 7 6 5 4 3 2 1 
1 2 3 4 5 6 7 8 9 11 12 13 14 15 
--------------------------
call reverse inside box(k)
3 2 1 6 5 4 9 8 7 13 12 11 15 14 
1 2 3 4 5 6 7 8 9 11 12 13 14 15 
--------------------------
call reverse inside alternate box(k)
3 2 1 4 5 6 9 8 7 11 12 13 15 14 
1 2 3 4 5 6 7 8 9 11 12 13 14 15 
--------------------------
maximum sum of two list-merge at common nodes
list 1:
1 2 3 5 6 9 10 12 15 21 30 32 
list 2:
0 1 3 6 7 13 14 15 20 22 32 
1 2 3 5 6 7 13 14 15 21 30 32 
-------------------------
cloning a list:with next and random pointer
1 2 3 4 5 
the random nodes for each node in sequence is:
3 1 5 3 2 
Cloning the list now:...
detach done
head.data of list1=1
head.data of list2=1
head.random.data of list1=3
head.random.data of list2=3
address of head and random node list1=Node@6bc7c054 Node@232204a1
address of head and random node list2=Node@4aa298b7 Node@7d4991ad
original list:
1 2 3 4 5 
duplicate list:
1 2 3 4 5 
--------------------------
each node must point to the next higher value node in the list: it can be anywhere:
5 10 2 6 3 
the sorted list
2 3 5 6 10 
after the operation of random nodes s
5 10 2 6 3 
the random nodes data for each node from start is:
6 null 3 10 5 
---------------------------
first last combination alternate nodes of list-in-place operation
the list is :
1 2 3 4 5 6 7 8 9 
after operation list :
1 9 2 8 3 7 4 6 5 
--------------------------
*/
