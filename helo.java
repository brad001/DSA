import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.Comparator;
//import java.io.RuntimeException;
class TestHeap
{
	public static void main(String[] args)
	{
		// PriorityQueue<Integer> pq=new PriorityQueue<Integer>(10,new Comparator<Integer>()
		// 	{
		// 		public int compare(Integer i1,Integer i2)
		// 		{
		// 			return i2.compareTo(i1);
		// 		}
		// 	});
		// pq.add(7);
		// pq.add(2);
		// pq.add(4);
		// pq.add(9);
		// pq.add(1);
		// pq.add(5);

		// Iterator i=pq.iterator();
		// while(i.hasNext())
		// {
		// 	System.out.print(i.next()+" ");
		// }
		// System.out.println();

		// PriorityQueue<Integer> pq1=new PriorityQueue<Integer>();
		// pq1.add(1);
		// pq1.add(4);
		// pq1.add(2);
		// pq1.add(7);
		// pq1.add(6);
		// pq1.add(5);
		// pq1.add(100);
		// pq1.add(0);


		// Iterator i1=pq1.iterator();
		// while(i1.hasNext())
		// {
		// 	System.out.print(i1.next()+" ");
		// }
		// System.out.println();

		// PriorityQueue<Node> pq2=new PriorityQueue<Node>(20,new Comparator<Node>()
		// 	{
		// 		public int compare(Node n1,Node n2)
		// 		{
		// 			return ((Character)(n1.c)).compareTo((Character)(n2.c));
		// 		}
		// 	});
		// pq2.add(new Node(9,"margi",'B'));
		// pq2.add(new Node(5,"loki",'A'));
		// pq2.add(new Node(8,"kushagra",'C'));
		// pq2.add(new Node(2,"jumbo",'B'));
		// pq2.add(new Node(1,"marg",'D'));
		// pq2.add(new Node(3,"pagal",'p'));
		// pq2.add(new Node(7,"singh",'Z'));

		// char[]chArr=new char[pq2.size()];


		// Iterator i2=pq2.iterator();
		// int ii=0;
		// while(i2.hasNext())
		// {
		// 	Node temp=(Node)i2.next();
		// 	//System.out.print((Node)i2.next()+" ");
		// 	chArr[ii++]=(char)temp.c;
		// 	System.out.println();
			
		// }
		// System.out.println(chArr);
		// System.out.println();

		int[] a1=new int[10];
		a1[0]=40;
		a1[1]=10;
		a1[2]=5;
		a1[3]=7;
		a1[4]=6;
		a1[5]=19;
		Heap heap=new Heap();
		heap.buildMinHeap(a1,6);
		heap.printHeap(a1,6);
		heap.addInMinHeap(a1,8,6);
		heap.printHeap(a1,7);

		heap.updateKeyInMinHeap(a1,6,1,7);
		heap.printHeap(a1,7);

		int[] a2={7,1,2,9,4,0,0,0};
		heap.heapSortIncresing(a2,5);

		int[] a3={1,6,2,3,4,5,5,6,7,8,9};
		heap.heapSortDecresing(a3,a3.length);

		int[] a4={7,1,2,9,4,3,5,10};
		heap.findKSamllest(a4,3);
		heap.findKLargest(a4,3);

		int[][] a5={ {10, 20, 30, 40},
                     {15, 25, 35, 45},
                     {27, 29, 37, 48},
                     {32, 33, 39, 50},
                   };

        heap.printMatrixInSortedOrder(a5);
	}
}

class Node
{
	int i;
	String str;
	char c;

	Node(int i,String str,char c)
	{
		this.i=i;
		this.str=str;
		this.c=c;
	}

	public String toString()
	{
		return "["+i+","+str+","+c+"]";
	}
}

class Heap
{
	//here size=current heap size in array not array size
	void topDownMinHeapify(int[] arr,int index,int heapSize)	
	{
		//loop executes only for nonleaf nodes
		//if total nodes are n then nonleaf node=(n/2)-1;
		while(index<=(heapSize/2)-1)
		{
			int left=2*index+1;
			int right=2*index+2;

			int min;
			//check minimum between sibling first and then with parent
			if(right<heapSize && arr[right]<arr[left])
				min=right;
			else
				min=left;

			//check with parent
			if(arr[min]<arr[index])
			{
				swap(arr,min,index);
				index=min;
			}
			//if parent(index) is at its correct posision
			//then no heap property is satisfied 
			//no need to do futher checking.
			else 
				break;
		}
	}

	void topDownMaxHeapify(int[] arr,int index,int heapSize)
	{
		//loop executes only for nonleaf nodes
		//if total nodes are n then nonleaf node=(n/2)-1;
		while(index<=(heapSize/2)-1)
		{
			int left=2*index+1;
			int right=2*index+2;

			int max;
			//check minimum between sibling first and then with parent
			if(right<heapSize && arr[right]>arr[left])
				max=right;
			else
				max=left;

			//check with parent
			if(arr[max]>arr[index])
			{
				swap(arr,max,index);
				index=max;
			}
			//if parent(index) is at its correct posision
			//then no heap property is satisfied 
			//no need to do futher checking.
			else 
				break;
		}
	}

	void bottomUpMinHeapify(int[] arr,int index,int heapSize)
	{
		//when index of current node is i then its parent
		//node index--> parent=(i-1)/2
		//coz i=2*parent+(1 or 2);

		while((index-1)/2 >= 0)
		{
			int parent=(index-1)/2;
			if(arr[index]<arr[parent])
			{
				swap(arr,index,parent);
				index=parent;
			}
			//already heapified so break
			else 
				break;
		}
	}

	void bottomUpMaxHeapify(int[] arr,int index,int heapSize)
	{
		//when index of current node is i then its parent
		//node index--> parent=(i-1)/2
		//coz i=2*parent+(1 or 2);

		while((index-1)/2 >= 0)
		{
			int parent=(index-1)/2;
			if(arr[index]>arr[parent])
			{
				swap(arr,index,parent);
				index=parent;
			}
			//already heapified so break
			else 
				break;
		}
	}

	void buildMinHeap(int[] arr,int heapSize)
	{
		//use topdown to buildHeap
		//coz if you use bottomUpHeapify you have to start from leaf node
		//so complexity will be more than topDownHeapify
		//for loop must be from rightmost nonleaf to 0th index
		//it will be wrong if you do it in reverse way

		for(int i=(heapSize/2)-1;i>=0;i--)
		{
			topDownMinHeapify(arr,i,heapSize);
		}
	}


	void buildMaxHeap(int[] arr,int heapSize)
	{
		//use topdown to buildHeap
		//coz if you use bottomUpHeapify you have to start from leaf node
		//so complexity will be more than topDownHeapify
		//for loop must be from rightmost nonleaf to 0th index
		//it will be wrong if you do it in reverse way

		for(int i=(heapSize/2)-1;i>=0;i--)
		{
			topDownMaxHeapify(arr,i,heapSize);
		}
	}  


	void addInMinHeap(int[] arr,int data,int heapSize)
	{
		if(heapSize==arr.length)
			throw new RuntimeException("heap is full");
		else
		{
			int index=heapSize;
			arr[index]=data;
			heapSize++;
			bottomUpMinHeapify(arr,index,heapSize);
		}
	}

	void addInMaxHeap(int[] arr,int data,int heapSize)
	{
		if(heapSize==arr.length)
			throw new RuntimeException("heap is full");
		else
		{
			int index=heapSize;
			arr[index]=data;
			heapSize++;
			bottomUpMaxHeapify(arr,index,heapSize);
		}
	}

	int peekMin(int[] arr,int heapSize)
	{
		return arr[0];
	}

	int peekMax(int[] arr,int heapsize)
	{
		return arr[0];
	}

	void updateKeyInMinHeap(int[] arr,int index,int newKey,int heapSize)
	{
		if(index<heapSize)
		{
			if(newKey<arr[index])
			{
				arr[index]=newKey;
				bottomUpMinHeapify(arr,index,heapSize);
			}
			else
			{
				arr[index]=newKey;
				topDownMinHeapify(arr,index,heapSize);
			}
		}
	}

	void updateKeyInMaxHeap(int[] arr,int index,int newKey,int heapSize)
	{
		if(index<heapSize)
		{
			if(newKey<arr[index])
			{
				arr[index]=newKey;
				topDownMaxHeapify(arr,index,heapSize);
			}
			else
			{
				arr[index]=newKey;
				bottomUpMaxHeapify(arr,index,heapSize);
			}
		}
	}

	int deleteMin(int[] arr,int heapSize)
	{
		//swap with last element of heap and then decrese heapsize
		//so when you decrese the heapsize that min element still there in arr
		//but its no longer part your heap that means its deleted from your heap
		//but now as you have swapped it with last you have to heapify again
		//root is changed so better option to heapify is topDownHeapify
		int min=arr[0];
		swap(arr,0,heapSize-1);
		heapSize--;
		topDownMinHeapify(arr,0,heapSize);
		return min;
	}

	int deleteMax(int[] arr,int heapSize)
	{
		//swap with last element of heap and then decrese heapsize
		//so when you decrese the heapsize that min element still there in arr
		//but its no longer part your heap that means its deleted from your heap
		//but now as you have swapped it with last you have to heapify again
		//root is changed so better option to heapify is topDownHeapify
		int max=arr[0];
		swap(arr,0,heapSize-1);
		heapSize--;
		topDownMaxHeapify(arr,0,heapSize);
		return max;
	}

	void heapSortIncresing(int[] arr,int heapSize)
	{
		//incresing order
		//for incresing order make array as maxheap first
		//so always arr[0] will be max in the current heap
		//now delete max element of heap and put it in last element of heap
		//now your heapsize would be decresed
		//when you delete you nned to again heapify
		int n=heapSize;
		buildMaxHeap(arr,heapSize);
		while(n>0)
		{
			deleteMax(arr,n);
			n--;
		}

		printHeap(arr,heapSize);
	}

	void heapSortDecresing(int[] arr,int heapSize)
	{
		//decresing order
		//for incresing order make array as maxheap first
		//so always arr[0] will be max in the current heap
		//now delete max element of heap and put it in last element of heap
		//now your heapsize would be decresed
		//when you delete you nned to again heapify
		int n=heapSize;
		buildMinHeap(arr,heapSize);
		while(n>0)
		{
			deleteMin(arr,n);
			n--;
		}

		printHeap(arr,heapSize);
	}

	void findKSamllest(int[] arr,int k)
	{
		//first build one Maxheap(YES MAX!)of first k ele of arr
		//now form k+1 to n ele compare each of them with max of heap
		//if smaller then max of heap then swap and heapify
		int arrSize=arr.length;
		buildMaxHeap(arr,k);
		for(int i=k;i<arrSize;i++)
		{
			//here arr[0] will be the root of the heap
			//so it will be the max in the heap
			//so compare all with arr[0]
			if(arr[i]<arr[0])
			{
				swap(arr,0,i);
				topDownMaxHeapify(arr,0,k);
			}

		}

		//print first k ele of arr coz they are smallest
		//but they will be in random order so sort them using heapsort
		heapSortIncresing(arr,k);
	}

	void findKLargest(int[] arr,int k)
	{
		//first build one Minheap(YES MIN!)of first k ele of arr
		//now form k+1 to n ele compare each of them with min of heap
		//if larger then min of heap then swap and heapify
		int arrSize=arr.length;
		buildMinHeap(arr,k);
		for(int i=k;i<arrSize;i++)
		{
			//here arr[0] will be the root of the heap
			//so it will be the min in the heap
			//so compare all with arr[0]
			if(arr[i]>arr[0])
			{
				swap(arr,0,i);
				topDownMinHeapify(arr,0,k);
			}

		}

		//print first k ele of arr coz they are smallest
		//but they will be in random order so sort them using heapsort
		heapSortIncresing(arr,k);
	}

	class Element implements Comparable<Element>
	{
		int row;
		int col;
		int data;

		Element(int r,int c,int d)
		{
			row=r;
			col=c;
			data=d;
		}

		public int compareTo(Element e1)
		{
			return this.data-e1.data;
		}
	}

	void printMatrixInSortedOrder(int[][] arr)
	{
		PriorityQueue<Element> pq=new PriorityQueue<Element>();
		//make one heap of 0th column'selements
		for(int i=0;i<arr.length;i++)
		{
			pq.add(new Element(i,0,arr[i][0]));
		}
		while(!pq.isEmpty())
		{
			System.out.print(pq.peek().data+"  ");
			Element e=pq.poll();
			if(e.col+1<arr[0].length)
				pq.add(new Element(e.row,e.col+1,arr[e.row][e.col+1]));
			//n--;
		}
		System.out.println();
	}

	void printHeap(int[] arr,int heapSize)
	{
		for(int i=0;i<heapSize;i++)
		{
			System.out.print((int)arr[i]+" ");
		}
		System.out.println();
	}

	void swap(int[] arr,int i,int j)
	{
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}


}
/*
HEAP
A Binary Heap is a Complete Binary Tree. A binary heap is typically represented as array. The representation is done as:
The root element will be at Arr[0]. 
Below table shows indexes of other nodes for the ith node, i.e., Arr[i]:
Arr[i/2]
Returns the parent node
Arr[(2*i)+1]
Returns the left child node
Arr[(2*i)+2]
Returns the right child node
Applications of Heaps:
1) Heap Sort: Heap Sort uses Binary Heap to sort an array in O(nLogn) time.
2) Priority Queue: Priority queues can be efficiently implemented using Binary Heap because it supports insert(), delete() and extractmax(), decreaseKey() operations in O(logn) time. Binomoial Heap and Fibonacci Heap are variations of Binary Heap. These variations perform union also efficiently.
3) Graph Algorithms: The priority queues are especially used in Graph Algorithms like Dijkstra’s Shortest Path and Prim’s Minimum Spanning Tree.
4) Many problems can be efficiently solved using Heaps. See following for example.
a) K’th Largest Element in an array.
b) Sort an almost sorted array/
c) Merge K Sorted Arrays.
d) K largest and K smallest elements of given aray.

1 Why is Binary Heap Preferred over BST for Priority Queue?
A typical Priority Queue requires following operations to be efficient. 
1. Get Top Priority Element (Get minimum or maximum) 
2. Insert an element 
3. Remove top priority element 
4. Decrease Key 
A Binary Heap supports above operations with following time complexities: 
1. O(1) 
2. O(Logn) 
3. O(Logn) 
4. O(Logn) 

A Self Balancing Binary Search Tree like AVL Tree, Red-Black Tree, etc can also support above operations with same time complexities.
1. Finding minimum and maximum are not naturally O(1), but can be easily implemented in O(1) by keeping an extra pointer to minimum or maximum and updating the pointer with insertion and deletion if required. With deletion we can update by finding inorder predecessor or successor. 
2. Inserting an element is naturally O(Logn) 
3. Removing maximum or minimum are also O(Logn) 
4. Decrease key can be done in O(Logn) by doing a deletion followed by insertion. See this for details. 
So why is Binary Heap Preferred for Priority Queue? 
Since Binary Heap is implemented using arrays, there is always better locality of reference and operations are more cache friendly. 
Although operations are of same time complexity, constants in Binary Search Tree are higher. 
We can build a Binary Heap in O(n) time. Self Balancing BSTs require O(nLogn) time to construct. 
Binary Heap doesn’t require extra space for pointers. 
Binary Heap is easier to implement. 
There are variations of Binary Heap like Fibonacci Heap that can support insert and decrease-key in Θ(1) time 
Is Binary Heap always better?
Although Binary Heap is for Priority Queue, BSTs have their own advantages and the list of advantages is in-fact bigger compared to binary heap.
Searching an element in self-balancing BST is O(Logn) which is O(n) in Binary Heap. 
We can print all elements of BST in sorted order in O(n) time, but Binary Heap requires O(nLogn) time. 
Floor and ceil can be found in O(Logn) time. 
K’th largest/smallest element be found in O(Logn) time by augmenting tree with an additional field. 

2 Sort a nearly sorted (or K sorted) array
Given an array of n elements, where each element is at most k away from its target position, devise an algorithm that sorts in O(n log k) time. 
For example, let us consider k is 2, an element at index 7 in the sorted array, can be at indexes 5, 6, 7, 8, 9 in the given array.

We can sort such arrays more efficiently with the help of Heap data structure. Following is the detailed process that uses Heap.
1) Create a Min Heap of size k+1 with first k+1 elements. This will take O(k) time (See this GFact)
2) One by one remove min element from heap, put it in result array, and add a new element to heap from remaining elements.
Removing an element and adding a new element to min heap will take Logk time. So overall complexity will be O(k) + O((n-k)*logK)

// Given an array of size n, where every element is k away from its target
// position, sorts the array in O(nLogk) time.
int sortK(int arr[], int n, int k)
{
    // Create a Min Heap of first (k+1) elements from
    // input array
    int *harr = new int[k+1];
    for (int i = 0; i<=k && i<n; i++) // i < n condition is needed when k > n
        harr[i] = arr[i];
    MinHeap hp(harr, k+1);
 
    // i is index for remaining elements in arr[] and ti
    // is target index of for cuurent minimum element in
    // Min Heapm 'hp'.
    for(int i = k+1, ti = 0; ti < n; i++, ti++)
    {
        // If there are remaining elements, then place
        // root of heap at target index and add arr[i]
        // to Min Heap
        if (i < n)
            arr[ti] = hp.replaceMin(arr[i]);
 
        // Otherwise place root at its target index and
        // reduce heap size
        else
            arr[ti] = hp.extractMin();
    }
}


3 Check if a given Binary Tree is Heap
Given a binary tree we need to check it has heap property or not, Binary tree need to fulfill following two conditions for being a heap –
1. It should be a complete tree (i.e. all levels except last should be full). 
2. Every node’s value should be greater than or equal to its child node (considering max-heap). 
We check each of the above condition separately, for checking completeness isComplete and for checking heap isHeapUtil function are written.
Detail about isComplete function can be found here.
isHeapUtil function is written considering following things –
1. Every Node can have 2 children, 0 child (last level nodes) or 1 child (there can be at most one such node). 
2. If Node has No child then it’s a leaf node and return true (Base case) 
3. If Node has one child (it must be left child because it is a complete tree) then we need to compare this node with its single child only. 
4. If Node has both child then check heap property at Node at recur for both subtrees.
Complete code. 

/* This function counts the number of nodes in a binary tree 
	int countNodes(Node root)
	{
		if(root==null)
			return 0;
		return(1 + countNodes(root.left) + countNodes(root.right));
	}
	
	/* This function checks if the binary tree is complete or not 
	boolean isCompleteUtil(Node root, int index, int number_nodes)
	{
		// An empty tree is complete
		if(root == null)
			return true;
		
		// If index assigned to current node is more than
		// number of nodes in tree, then tree is not complete
		if(index >= number_nodes)
			return false;
		
		// Recur for left and right subtrees
		return isCompleteUtil(root.left, 2*index+1, number_nodes) && 
			isCompleteUtil(root.right, 2*index+2, number_nodes);
		
	}
	
	// This Function checks the heap property in the tree.
	boolean isHeapUtil(Node root)
	{
		// Base case : single node satisfies property
		if(root.left == null && root.right==null)
			return true;
		
		// node will be in second last level
		if(root.right == null)
		{
			// check heap property at Node
			// No recursive call , because no need to check last level
			return root.key >= root.left.key;
		}
		else
		{
			// Check heap property at Node and
			// Recursive check heap property at left and right subtree
			if(root.key >= root.left.key && root.key >= root.right.key)
				return isHeapUtil(root.left) && isHeapUtil(root.right);
			else
				return false;
		}

4 How to check if a given array represents a Binary Heap?
An Efficient Solution is to compare root only with its children (not all descendants), if root is greater than its children and same is true for for all nodes, then tree is max-heap (This conclusion is based on transitive property of > operator, i.e., if x > y and y > z, then x > z).
The last internal node is present at index (2n-2)/2 assuming that indexing begins with 0.

bool isHeap(int arr[], int i, int n)
{
   // we know that ,non-leaf node starts at (n-2)/2
   // so we check recursively for all nodes<=(n-2)/2
   //but return true for nodes > n-2/2
   if (i > (n - 2)/2)
       return true;
 
   // If an internal node and is greater than its children, and
   // same is recursively true for the children
   if (arr[i] >= arr[2*i + 1]  &&  arr[i] >= arr[2*i + 2] &&
       isHeap(arr, 2*i + 1, n) && isHeap(arr, 2*i + 2, n))
       return true;
 
   return false;
}


5 Print all elements in sorted order from row and column wise sorted matrix
Given an n x n matrix, where every row and column is sorted in non-decreasing order. Print all elements of matrix in sorted order.
Example:
Input: mat[][]  =  { {10, 20, 30, 40},
                     {15, 25, 35, 45},
                     {27, 29, 37, 48},
                     {32, 33, 39, 50},
                   };

Output:
Elements of matrix in sorted order
10 15 20 25 27 29 30 32 33 35 37 39 40 45 48 50
A better solution is to use the approach used for merging k sorted arrays. The idea is to use a Min Heap of size N which stores elements of first column. The do extract minimum. In extract minimum, replace the minimum element with the next element of the row from which the element is extracted. Time complexity of this solution is O(N2LogN). 


class Element implements Comparable<Element> 
	{ 
		int row; 
		int col; 
		int data; 

		Element(int r,int c,int d) 
		{ 
			row=r; 
			col=c; 
			data=d; 
		} 

		public int compareTo(Element e1) 
		{ 
			return this.data-e1.data; 
		} 
	} 

	void printMatrixInSortedOrder(int[][] arr) 
	{ 
		PriorityQueue<Element> pq=new PriorityQueue<Element>(); 
		//make one heap of 0th column'selements 
		for(int i=0;i<arr.length;i++) 
		{ 
			pq.add(new Element(i,0,arr[i][0])); 
		} 
		while(!pq.isEmpty()) 
		{ 
			System.out.print(pq.peek().data+"  "); 
			Element e=pq.poll(); 
			if(e.col+1<arr[0].length) 
				pq.add(new Element(e.row,e.col+1,arr[e.row][e.col+1])); 
			//n--; 
		} 
		System.out.println(); 
	}


6 Connect n ropes with minimum cost
There are given n ropes of different lengths, we need to connect these ropes into one rope. The cost to connect two ropes is equal to sum of their lengths. We need to connect the ropes with minimum cost.
For example if we are given 4 ropes of lengths 4, 3, 2 and 6. We can connect the ropes in following ways.
1) First connect ropes of lengths 2 and 3. Now we have three ropes of lengths 4, 6 and 5.
2) Now connect ropes of lengths 4 and 5. Now we have two ropes of lengths 6 and 9.
3) Finally connect the two ropes and all ropes have connected.
Total cost for connecting all ropes is 5 + 9 + 15 = 29. This is the optimized cost for connecting ropes. Other ways of connecting ropes would always have same or more cost. For example, if we connect 4 and 6 first (we get three strings of 3, 2 and 10), then connect 10 and 3 (we get two strings of 13 and 2). Finally we connect 13 and 2. Total cost in this way is 10 + 13 + 15 = 38.
f we observe the above problem closely, we can notice that the lengths of the ropes which are picked first are included more than once in total cost. Therefore, the idea is to connect smallest two ropes first and recur for remaining ropes. This approach is similar to Huffman Coding. We put smallest ropes down the tree so that they can be repeated multiple times rather than the longer ropes.
Following is complete algorithm for finding the minimum cost for connecting n ropes.
Let there be n ropes of lengths stored in an array len[0..n-1]
1) Create a min heap and insert all lengths into the min heap.
2) Do following while number of elements in min heap is not one.
……a) Extract the minimum and second minimum from min heap
……b) Add the above two extracted values and insert the added value to the min-heap.
3) Return the value of only left item in min heap.

7 Merge k sorted arrays
Given k sorted arrays of size n each, merge them and print the sorted output.
Example:
Input:
k = 3, n =  4
arr[][] = { {1, 3, 5, 7},
            {2, 4, 6, 8},
            {0, 9, 10, 11}} ;

Output: 0 1 2 3 4 5 6 7 8 9 10 11 
A simple solution is to create an output array of size n*k and one by one copy all arrays to it. Finally, sort the output array using any O(nLogn) sorting algorithm. This approach takes O(nkLognk) time. 
We can merge arrays in O(nk*Logk) time using Min Heap. Following is detailed algorithm.
1. Create an output array of size n*k.
2. Create a min heap of size k and insert 1st element in all the arrays into a the heap
3. Repeat following steps n*k times.
     a) Get minimum element from heap (minimum is always at root) and store it in output array.
     b) Replace heap root with next element from the array from which the element is extracted. If the array doesn’t have any more elements, then replace root with infinite. After replacing the root, heapify the tree.




8 Sort numbers stored on different machines
Given N machines. Each machine contains some numbers in sorted form. But the amount of numbers, each machine has is not fixed. Output the numbers from all the machine in sorted non-decreasing form.
Example:
       Machine M1 contains 3 numbers: {30, 40, 50}
       Machine M2 contains 2 numbers: {35, 45} 
       Machine M3 contains 5 numbers: {10, 60, 70, 80, 100}
       
       Output: {10, 30, 35, 40, 45, 50, 60, 70, 80, 100}
Representation of stream of numbers on each machine is considered as linked list. A Min Heap can be used to print all numbers in sorted order. 
Following is the detailed process 
1. Store the head pointers of the linked lists in a minHeap of size N where N is number of machines.
2. Extract the minimum item from the minHeap. Update the minHeap by replacing the head of the minHeap with the next number from the linked list or by replacing the head of the minHeap with the last number in the minHeap followed by decreasing the size of heap by 1.
3. Repeat the above step 2 until heap is not empty.

*/
