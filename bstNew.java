import java.util.ArrayDeque;
import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ArrayList;

class Node
{
	int data;
	Node left;
	Node right;
	public Node()
	{

	}
	public Node(int data)
	{
		this.data=data;
	}

}
class BST
{
	Node root;
	Iterator<Integer> head;

	public BST()
	{
		root=null;
	}
	void insert(int data)
	{
		root=insertTree(root,data);
	}
	
    Node insertTree(Node curr,int data)
	{
		if(curr==null)
		{
			Node newNode=new Node(data);
			return newNode;
		}
		else if(data<curr.data)
			curr.left=insertTree(curr.left,data);
		else
			curr.right=insertTree(curr.right,data);

		return curr;


	}

	//delete is kind of insert only
	//where you do some new assignment
	//1. leaf node=> return to its parent null;
	//2. no left but right child => return right child to parent
	//3. no right but left child => return left child to parent
	//4. when it has both child => return the inorder-succeser
	        //=> copy the contents of inorder succ to this node
            //=> and the recur down the right of curr,
	        //=>  to find node with data=inordersucc.data
	        //=> simply delete this node as its data is copied and it must be deleted

	void delete(int key)
	{
		root=deleteNode(root,key);
	}
	Node deleteNode(Node curr,int key)
	{
		//we reach this=> node with key was not found
		if(curr==null)
		{
			System.out.println("node with data="+key+" was not found in the tree");
			return null;
		}
		//if key lies in the left=> modify the curr.left
		else if(key<curr.data)
			curr.left=deleteNode(curr.left,key);
		else if(key>curr.data)
			curr.right=deleteNode(curr.right,key);
		//if the key is found at this node
		//apply the above cases
		else
		{
			//node has left=null, and rightSubtree exists
			//simply return the rightSubtree
			if(curr.left==null)
				return curr.right;

			//node has right=null, and leftSubtree exists
			//simply return the leftSubtree
			if(curr.right==null)
				return curr.left;

			//if leaf node, handled by 1st one too :p

			//if node has both left and right subtree
			//find and copy data of inordersucc
			//and then delete the inordersucc

			//inOrderSucc(data)
			curr.data=inOrderSucc(curr.data);
			//now delete this copied node
			curr.right=deleteNode(curr.right,curr.data);

		}
		return curr;

	}
	boolean search(int data)
	{
		return getSearch(root,data);
	}
	boolean getSearch(Node curr,int data)
	{
		if(curr==null)
			return false;
		else if(data<curr.data)
			return getSearch(curr.left,data);
		else if(data>curr.data)
			return getSearch(curr.right,data);
		else
			return true;


	}
	public void displayPre()
	{
		System.out.println("the Iterative preOrder");
		preOrderIter(root);
		System.out.println();
		System.out.println("the Recursive preOrder");
		preOrderRec(root);
		System.out.println();
	}
	public void preOrderRec(Node curr)
	{
		if(curr==null)
			return;
		System.out.print(curr.data+" ");
		preOrderRec(curr.left);
		preOrderRec(curr.right);
	}
	void preOrderIter(Node curr)
	{
		if(curr==null)
			return;

		ArrayDeque<Node>st=new ArrayDeque<Node>();
		st.push(curr);

		while(!st.isEmpty())
		{
			Node top=st.pop();
			System.out.print(top.data+" ");
			if(top.right!=null)
				st.push(top.right);
			if(top.left!=null)
				st.push(top.left);
		}

	}
	void displayPost()
	{
		System.out.println("the iterative post order");
		postOrderIter(root);
		System.out.println();
		System.out.println("the recursive post order");
		postOrderRec(root);
		System.out.println();
	}
	//we have three cases for solving it
	//curr=>standing at what pos
	//prev=>from where i have come

	//1. go down the tree, => prev is up and curr is down
	        //left or right(any)[marked by prev.left=curr or prev.right=curr]

	//2. coming up from left => pre
	         //prev is left down and curr is top => curr.left==prev

	//3. coming up from right => prev is right down and curr is top => curr.right==prev
	void postOrderIter(Node curr)
	{
		Node prev=curr;
		ArrayDeque<Node>st=new ArrayDeque<Node>();

		if(curr==null)
			return;
		st.push(curr);

		//1st statement =>curr always peek
		//last statement=> prev always curr

		while(!st.isEmpty())
		{
			curr=st.element();

			// 1. go down
			if(curr==prev || prev.left==curr|| prev.right==curr)
			{
				if(curr.left!=null)
					st.push(curr.left);
				else if(curr.right!=null)
					st.push(curr.right);
				else if(curr.left==null && curr.right==null)
					System.out.print(st.pop().data+" ");
			}
			else if(curr.left==prev) //2. coming up from left=> go right or if no right print and pop
			{
				if(curr.right!=null)
					st.push(curr.right);
				else
				{
					System.out.print(st.pop().data+" ");
				}
			}	
			else  // 3. coming up from right => all left and right are over =>only print the curr
			{
				System.out.print(st.pop().data+" ");
			}


			//last statement
			prev=curr;
		}

	}
	void postOrderRec(Node curr)
	{
		if(curr==null)
			return;
		postOrderRec(curr.left);
		postOrderRec(curr.right);
		System.out.print(curr.data+" ");
	}
	void displayIn()
	{
		System.out.println("the iterative in order");
		inOrderIter(root);
		System.out.println();
		System.out.println("the recursive  order");
		inOrderRec(root);
		System.out.println();

	}
	void inOrderIter(Node curr)
	{
		ArrayDeque<Node>st=new ArrayDeque<Node>();

		if(curr==null)
			return;
		
		boolean flag=true;
		while(flag)
		{
			if(curr!=null)
			{
				st.push(curr);
				curr=curr.left;
			}
			else
			{
				if(st.isEmpty())
				{
					flag=false;
				}
				else
				{
					curr=st.pop();
					System.out.print(curr.data+" ");
					curr=curr.right;
				}
			}
		}
	}

	void inOrderRec(Node curr)
	{
		if(curr==null)
			return ;
		inOrderRec(curr.left);
		System.out.print(curr.data+" ");
		inOrderRec(curr.right);

	}
	void inOrderReverse()
	{
		System.out.println("print the reverse inOrder of the tree");
		inRev(root);
	}
	//right data left
	void inRev(Node curr)
	{
		if(curr==null)
			return;
		inRev(curr.right);
		System.out.print(curr.data+" ");
		inRev(curr.left);
	}


	void displayLevel()
	{
		System.out.println("the iterative level order");
		levelOrderIter(root);
		System.out.println();
		System.out.println("the recursive level order");
		levelOrderRec(root);
	}
	// i print for each level the nodes from left to right
	void levelOrderRec(Node curr)
	{
		for(int i=1;i<=height();i++)
			getLevelNodes(root,i);
	}
	void getLevelNodes(Node curr,int level)
	{
		if(curr==null)
		   return;
		if(level==1)
		{
			System.out.print(curr.data+" ");
			return;
		}
		else
		{
			getLevelNodes(curr.left,level-1);
			getLevelNodes(curr.right,level-1);
		}

	}
	void levelOrderIter(Node root)
	{
		if(root==null)
			return;

		Queue<Node>q1=new ArrayDeque<Node>();
		q1.add(root);

		while(!q1.isEmpty())
		{
			Node front=q1.element();
			System.out.print(front.data+" ");
			if(front.left!=null)
				q1.add(front.left);
			if(front.right!=null)
				q1.add(front.right);
			q1.remove();

		}


	}

	int size()
	{
		return getSize(root);
	}
	int getSize(Node curr)
	{
		if(curr==null)
			return 0;
		return getSize(curr.left)+1+getSize(curr.right);
	}


	int height()
	{
		return getHeight(root);
	}
	int getHeight(Node curr)
	{
		if(curr==null)
			return 0;
		return 1+Math.max(getHeight(curr.left),getHeight(curr.right));
	}
	int max()
	{
		return getMax(root);
		
	}
	//get max of any subtree
	int getMax(Node curr)
	{
		if(curr==null)
		{
			System.out.println("root is empty");
			return Integer.MIN_VALUE;
		}

		while(curr.right!=null)
			curr=curr.right;

		return curr.data;
	}
	int min()
	{
		return getMin(root);
	}
	//return min of any subtree
	int getMin(Node curr)
	{

		if(curr==null)
		{
			System.out.println("root is empty");
			return Integer.MAX_VALUE;
		}

		while(curr.left!=null)
			curr=curr.left;

		return curr.data;
	}

	//diameter = max no. of nodes along any path
	//1. path may go through root =>1+height(leftTree)+height(rightTree)
	//2. path may not go through root=> max(Diam(leftTree),Diam(rightTree))
	public int diameter()
	{
		return getDiameter(root);
	}

	public int getDiameter(Node curr)
	{
		//if no nodes are there
		if(curr==null)
			return 0;

		//going through the path=> lh+rh+ 1(for root)
		int lh=getHeight(curr.left);
		int rh=getHeight(curr.right);

		//leftDiam=> the lh+rh +1 for the left node for curr
		int leftDiam=getDiameter(curr.left);
		//rightDiam=> the lh+rh +1 for the rigt node for curr
		int rightDiam=getDiameter(curr.right);

		return Math.max(lh+rh+1 , Math.max(leftDiam,rightDiam));

	}

	public int width1()
	{
		return getWidth1(root);


	}
	public int getWidth1(Node curr)
	{
		if(curr==null)
			return 0;

		ArrayDeque<Node>q=new ArrayDeque<Node>();
		//arryaDeque doesnt allow null values
		//Node marker=null;
		Node marker=new Node();
		int countNodesLevel=0;
		int maxNodes=0;

		q.add(curr);
		maxNodes=1;
		//1st level is over => place a marker
		q.add(marker);

		while(!q.isEmpty())
		{
			Node front=q.remove();

			//if the current level is complete:
			//        => store the count in max
			//        => update the marker(insert the marker)
			if(front==marker)
			{
				if(countNodesLevel>maxNodes)
					maxNodes=countNodesLevel;

				//before inserting more markers
				//ensure the list is empty
				//if empty=> dont
				if(!q.isEmpty())
				   q.add(marker);
				//for next level count
				countNodesLevel=0;
			}
			else //we have more nodes at level to insert
			{
				if(front.left!=null)
				{
					countNodesLevel++;
					q.add(front.left);
				}
				if(front.right!=null)
				{
					countNodesLevel++;
					q.add(front.right);	
				}
			}
		}
		return maxNodes;

	}

	//to find the maximum sum at any level
	//we follow the width1 approach
	//level by level , AND marker for level end
	void maxSumLevel()
	{
		Node curr=root;
		ArrayDeque<Node>q=new ArrayDeque<Node>();
		//since we cant insert null values in an ArrayDeque
		Node marker=new Node();
		int level=0;
		int maxSumLevel=0;
		int currLevelSum=0;
		int maxLevelSum=0;

		q.add(curr);
		q.add(marker);
		level=1;
		//level 1 finished

		while(!q.isEmpty())
		{
			Node front=q.remove();

			//if its the end of level
			if(front==marker)
			{
				//we find store the max and corresponding level
				if(currLevelSum>maxLevelSum)
				{
					maxLevelSum=currLevelSum;
					maxSumLevel=level;
				}

				//whenver we come inside this=> next level
				level++;
				currLevelSum=0;

				//if currMarker has reached=> we already added next level nodes
				// so insert marker 
				//[but is there were no next level nodes => queue just got empty with removal of front]
				if(!q.isEmpty())
					q.add(marker);
				
			}
			else //not the end of level => add more nodes
			{
				//we add nodes for next level
				//but we have front = currLevelNode
				//      => add each of them
				currLevelSum=currLevelSum+front.data;

				if(front.left!=null)
					q.add(front.left);
				if(front.right!=null)
					q.add(front.right);
			}
		}

		System.out.println("we got the maximum sum at level="+maxSumLevel);
		System.out.println("we got the maximum sum ="+maxLevelSum);
	}
	public int width2()
	{
		//get no. of nodes at each level
		int maxWidth=0,count=0;
		for(int i=1;i<=height();i++)
		{
			count=getWidthLevelOrder(root,i);
			System.out.println("level:"+i+" =Nodes="+count);
			if(count>maxWidth)
				maxWidth=count;
		}
		return maxWidth;
	}
	public int getWidthLevelOrder(Node curr,int level)
	{
		if(curr==null)
			return 0;
		else if(level==1) // once you reach this level
			return 1;
	    else
	    {
	    	int leftOfCurr= getWidthLevelOrder(curr.left,level-1);
	    	int rightOfCurr=getWidthLevelOrder(curr.right,level-1);

	    	return leftOfCurr+rightOfCurr;

	    }
	}

	//print the paths from root to leaves
	// the path may have half children too
	//so the base case= > when both left and right child are null => print the path
	// if any one of the child exists => call that with (string+curr.data)
	void printPathsFromRoot()
	{
		//we pass empty string so that nodes can be apppended and printed at end
		getAllPath(root,"");
	}
	void getAllPath(Node curr,String s)
	{
		//if leaf is reached => add this to the string and then print
		if(curr.left==null && curr.right==null)
		{
			s=s+" "+curr.data;
			System.out.println("path: "+s);
			return;
		}
		if(curr.left!=null)
			getAllPath(curr.left,s+" "+curr.data);
		if(curr.right!=null)
			getAllPath(curr.right,s+" "+curr.data);

	}

	

	static class PathSum
	{
		static int maxSumPath=0;
	}
	void maxSumAllPathFromRoot()
	{
		getMaxSumAllPath(root,0);
		System.out.println("the maximum sum along all paths ="+PathSum.maxSumPath);

		//some other insatnce of the same class might call this function too
		//so set the static data =0;
		//otherwise the data is retained
		PathSum.maxSumPath=0;
	}
	void getMaxSumAllPath(Node curr,int currSum)
	{
		//if leaf is reached => add this to the sum and then compare with max
		if(curr.left==null && curr.right==null)
		{
			currSum=currSum+curr.data;
			System.out.println("path ends with sum: "+currSum);

			if(currSum > PathSum.maxSumPath)
				PathSum.maxSumPath= currSum;

			return;
		}
		if(curr.left!=null)
			getMaxSumAllPath(curr.left,currSum+curr.data);
		if(curr.right!=null)
			getMaxSumAllPath(curr.right,currSum+curr.data);
	}



	void getMirror(BST oldTree)
	{
		//assign the root of mirror a mirrored tree
		// 
		this.root=mirror(oldTree.root);
	}
	//basic idea is => call recursively left and right
	//once we reach any page=> we have nodes left and right link=> swap them
	//once we get back to the called page => automatically it swaps the nodes for that level
	//level by level swaps nodes of left and right
	Node mirror(Node curr)
	{
		if(curr==null)
			return null;

  		mirror(curr.left);
		mirror(curr.right);

		//once we have reached the leftmost node=> it calls it left and right
		//they both are null => swap them
		// curr= leftmostnode
		Node temp=null;
		temp=curr.left;
		curr.left=curr.right;
		curr.right=temp;

		//this is done for leftmost node
		//once you get back to the call of curr.left
		//next it will call right of that curr => setting them
		//curr of prev swaps them both
		return curr;
	}



	void printNodesKdistance(int k)
	{
		if(k>=1 && k<=height())
		  getLevelNodes(root,k);
		else
		  System.out.println("distance not proper");	

	}
    
    int inOrderPred(int data)
    {
    	
    	Node pred=getInOrderPred(root,data);
    	if(pred==null)
    		return Integer.MIN_VALUE;
    	else
    	    return pred.data;
    }
    Node getInOrderPred(Node curr,int data)
    {
    	//element not found
    	if(curr==null)
    	{
    		return null;
    	}
    	
    	if(curr.data==data)
    	{	
    		//if we have reached the node whose inOrderPredecessor
    	    //is to be found , then 
    	    // go find the righMost elem of left subtree of the currNode
    		
    		if(curr.left!=null)
    		{
    			//left subtree, 
    		     curr=curr.left;
    		    //rightmost
    			while(curr.right!=null)
    			{
    				curr=curr.right;
    			}
    			return curr;
    		}
    	    else
    		  return null;

    	}

    	//if the node is not found yet,
    	//then traverse left or right based
    	// on data is smaller or greater

    	if(data<curr.data)
    		return getInOrderPred(curr.left,data);
    	else
    		return getInOrderPred(curr.right,data);

    }
    int inOrderSucc(int data)
    {
    	
    	Node succ=getInOrderSucc(root,data);
    	if(succ==null)
    		return Integer.MIN_VALUE;
    	else
    	    return succ.data;
    }
    Node getInOrderSucc(Node curr,int data)
    {
    	//element not found
    	if(curr==null)
    	{
    		return null;
    	}
    	if(curr.data==data)
    	{	
    		//if we have reached the node whose inOrderSucccessor
    	    //is to be found , then 
    	    // go find the leftMost elem of right subtree of the currNode
    		if(curr.right!=null)
    		{
    			//right subtree
    		     curr=curr.right;
    		    //leftmost
    			while(curr.left!=null)
    			{
    				curr=curr.left;
    			}
    			return curr;
    		}
    	    else
    		  return null;

    	}

    	//if the node is not found yet,
    	//then traverse left or right based
    	// on data is smaller or greater

    	if(data<curr.data)
    		return getInOrderSucc(curr.left,data);
    	else
    		return getInOrderSucc(curr.right,data);

    }
    //to check if a binary tree is bst or not
    //1. check for each node's left and right => left<node<right
       //but       6
     //        2        7
       //    1   9    5   10 

       //above tree satisfies prop 1 for each node , but not a valid bst

    //2. check for each node, max(left)<node<min(right)
    	//but it calls getMax and getMin for each node of subtree 
        // going through nodes many times
    boolean checkBst1()
    {
    	return isBst1(root);
    }   
    boolean isBst1(Node curr)
    {
    	//if the node on top down traversal, has reached this point=> true
    	if(curr==null)
    		return true;

    	//if left subtree has max > curr rootOf subtree
    	if(curr.left!=null && getMax(curr.left)>curr.data)
    		return false;

    	//if right subtree has min < curr rootOf subtree
    	if(curr.right!=null && getMin(curr.right)<curr.data)
    		return false;

    	//if above conditions are false=> possibility of bst, BUT 
    	//check the same for lower subtree
    	//if any subcall, returns false => any one condition true=> return false
    	if(!isBst1(curr.left) || !isBst1(curr.right))
    		return false;

    	//if all abpve conditioned failed
    	return true;

    }


    boolean checkBst2()
    {
    	return isBst2(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    //root will compare with max and min value and is true
    // for rest of the nodes, we update min and max as
    // max=curr.data-1 for left :  min=curr.data+1 for right
    // bcoz bst has all unique values
    boolean isBst2(Node curr,int min,int max)
    {
    	if(curr==null)
    		return true;

    	//intially it is fasle=> if is skipped
    	//if is true when violation
    	if(curr.data<min || curr.data>max)
    		return false;

    	//recursively,    2
    	//              6   10
    	// when call for 2.left(curr.left,min,1(max))
    	//the max value that left tree can have is prev.data-1 => all values<=1
    	//the min value that right subtree can have is prev.data+1 =>all values >=3
    	return (isBst2(curr.left,min,curr.data-1) && isBst2(curr.right,curr.data+1,max));
    }

    //find the deepsest node of the tree
    //go level by level=> last node to be popped out is the deepest one
    int findDeepestNode()
    {
    	Node curr=root;
    	ArrayDeque<Node> q=new ArrayDeque<Node>();

    	q.add(curr);

    	Node front=null;
    	int level=1;
    	while(!q.isEmpty())
    	{
    		front=q.remove();
    		if(front.left!=null)
    			q.add(front.left);
    		if(front.right!=null)
    			q.add(front.right);

    	}

    	//once the queue is empty=> the last loop run had front as the deepest node
    	return front.data;
    }

    //find all the leaves of the tree
    //go level by level = on each iteration, if we have both left and right as null
    //                  => curr node is a leaf

    void findAllLeafFullHalf()
    {
    	Node curr=root;
    	ArrayDeque<Node>q=new ArrayDeque<Node>();
    	int countLeaf=0,countFull=0,countHalf=0;
    	ArrayList<Integer>liLeaf=new ArrayList<Integer>();
    	ArrayList<Integer>liFull=new ArrayList<Integer>();
    	ArrayList<Integer>liHalf=new ArrayList<Integer>();

    	q.add(curr);
    	while(!q.isEmpty())
    	{
    		Node front=q.remove();
    		
    		//when both childern are null=> leaf
    		if(front.left==null && front.right==null)
    		{
    			countLeaf++;
    			liLeaf.add(front.data);
    		}

    		//when both childern are not null => full
    		if(front.left!=null && front.right!=null)
    		{
    			countFull++;
    			liFull.add(front.data);
    		}

    		//when one of right or left is null but not both =>half
    		if( (front.left==null && front.right!=null) || (front.left!=null && front.right==null))
    		{
    			countHalf++;
    			liHalf.add(front.data);
    		}

    		//if both child exits then add both
    		if(front.left!=null)
    			q.add(front.left);
    		if(front.right!=null)
    			q.add(front.right);
    	}

    	//end of it
    	System.out.println("the tree has "+countLeaf+" leaves");
    	System.out.println(liLeaf);

    	System.out.println("the tree has "+countFull+" full nodes");
    	System.out.println(liFull);

    	System.out.println("the tree has "+countHalf+" half nodes");
    	System.out.println(liHalf);

    }
    int lowestCommonAcncestorIter(int a,int b)
    {
    	if(search(a) && search(b))
    	{
    		Node curr=root;
    		while(curr!=null)
    		{	
    			//go left is both lies on left
    			if(a<curr.data && b<curr.data)
    				curr=curr.left;
    			else if(a>curr.data && b>curr.data)
    				curr=curr.right;
    			else //we have reached a point of left,right alignment
    				break;
    		}
    		return curr.data;

    	}
    	return Integer.MIN_VALUE;
    }
    int lowestCommonAcncestorRec(int a,int b)
    {
    	if(search(a) && search(b))
    	{
             System.out.println("found both");
             return getLcaRec(root,a,b);

    	}
         else
         {
         	System.out.println("elemets doesnt exists");
         	return Integer.MIN_VALUE;
         }
    }
    //check is both lies on leftSubtree/rightSubtree
    //if not on any=>left and right 

    int getLcaRec(Node curr,int a ,int b)
    {
    	

    	//when both lies on leftSubtree
    	if(a<curr.data && b<curr.data)
    		return getLcaRec(curr.left,a,b);

    	//when both lies on rightSubtree
    	if(a>curr.data && b>curr.data)
    		return getLcaRec(curr.right,a,b);

    	//the moment it has reached here=> 
    	//one lies on left of curr
    	//and other lies on right of curr
    	return curr.data;
    }

    
    //check is tree has <=k elments
    int kthSmallest(int k)
    {
    	//is there are atmost k elements
    	if(k<=size())
    	{
    		return getKthSmallest(root,k);
    	}
    	else //not sufficient elements
    	  return Integer.MIN_VALUE;
    
    }
    //preOrder of Bst gives me sorted order
    //1st print is smallest and then next....
    //recursion inOrder is difficult
    //use iterative InOrder [O(n) W.C ]
    int getKthSmallest(Node curr,int k)
    {
		ArrayDeque<Node>st=new ArrayDeque<Node>();

		int count=1;
		boolean flag=true;
		while(flag)
		{
			if(curr!=null)
			{
				st.push(curr);
				curr=curr.left;
			}
			else
			{
				if(st.isEmpty())
				{
					flag=false;
				}
				else
				{
					curr=st.pop();
					//print curr.data
					if(count==k)
						return curr.data;
					count++;

					curr=curr.right;
				}
			}
		}
		return Integer.MIN_VALUE;
	}

	//ceil(5) => smallest value greater than or equal to 5
	// if no value exits=> -1
	// for each value(root.data): 
	          // if key>root.data => just call right
	          // if equal return

	          //since we reached a point, where we 
	   //have elements on left or this node.data itself 
	 // therefore, inputKey> all left => this node is the right candidate
	int ceil(int key)
	{
		int val=getCeil(root,key);
		if(val==-1)
		{
			System.out.println("doesnt not exists");
		}
		return val;
	}
	int getCeil(Node curr,int key)
	{
		//we have hit an end
		if(curr==null)
			return -1;
		else if(key > curr.data)
			return getCeil(curr.right,key);
		else if(key==curr.data)
			return curr.data;
		else
		{
			//search for 11
			//       12
			//   8        15
			// 1  10    14  19
			int ceil=getCeil(curr.left,key);
			//here -1 will be the ceil
			if(ceil>=key)
				return ceil;
			else
				return curr.data;
			//therefore returned =12

		}

	}
	int floor(int key)
	{
		int val=getFloor(root,key);
		if(val==-1)
		{
			System.out.println("doesnt not exists");
		}
		return val;
	}
	int getFloor(Node curr,int key)
	{
		//we have hit an end
		if(curr==null)
			return -1;
		else if(key < curr.data)
			return getFloor(curr.left,key);
		else if(key==curr.data)
			return curr.data;
		else
		{
			//search for 11
			//       12
			//   8        15
			// 1  10    14  19
			int floor=getFloor(curr.right,key);
			
			//floor store values to right of curr
			//so iam sure that key has floor as: curr or right
			//so if floor>curr => key has floor on right
			if(floor>=curr.data)
				return floor;
			else
				return curr.data;
			//therefore returned =12

		}

	}
	//1. any traversal in nested loop , O(n2)

	//2. we store the inorder in an array
	//   do scanning from l and h => time O(n) space O(n)

	//3. inOrder and reverseInOrder
	//   same logic as array , if sum>currSum or <
	//   at any instant both will have a val1 < val2 relation
	//   break when val1>=val2 => we are again doing the same check on second half


	//USE ITERATIVE IN ORDER
	boolean isPairPresent(int sum)
	{

		Node curr1=root;
		Node curr2=root;

		ArrayDeque<Node> stack1=new ArrayDeque<Node>();
		ArrayDeque<Node> stack2=new ArrayDeque<Node>();

		int val1=curr1.data;
		int val2=curr2.data;

		//check is true until we find first value in both
		//first value in 1=> minimum
		//first value in 2=>maximum

		boolean checkInOrder=true;
		boolean checkRevInOrder=true;
		//check=false => do not check more but sum them now.

	  while(true)
	  {
		while(checkInOrder==true)
		{
			//this is to push
			if(curr1!=null)
			{
				stack1.push(curr1);
				curr1=curr1.left;

			}//once filled for all left => find first min
			else
			{
				//if stack is empty break;
				if(stack1.isEmpty())
					checkInOrder=false;

				else
				{
				//pop => we check for this in sum
				curr1=stack1.pop();
				//break the loop
				checkInOrder=false;
				//store the value
				val1=curr1.data;
				//if fails=> next push from right of this if subtree
				// inorder=> left data right
				curr1=curr1.right;
			    }

			}
		}
		while(checkRevInOrder==true)
		{
			//this is to push in revOrder
			if(curr2!=null)
			{
				stack2.push(curr2);
				curr2=curr2.right;
			}
			else
			{
				//if stack is empty 
				//return false
				if(stack2.isEmpty())
					checkRevInOrder=false;
				else
				{
				//pop => we check for this in sum
				curr2=stack2.pop();
				//break the loop
				checkRevInOrder=false;
				//store the value
				val2=curr2.data;
				//if fails=> next push from left of this if subtree
				// RevInorder=> right data left
				curr2=curr2.left;
			   }
			}
		}

		//once we have found first element from both
		//we compare and proceed

		//it might happen root =5 and sum=10
		//and both order has curr1,curr2=node(5)
		if((val1+val2) == sum && val1!=val2)
		{
			System.out.println("pair with given sum="+sum+" exists");
			System.out.println("the pairs are:"+val1+" &"+val2);
			return true;
		}
		//sum> val1+val2 => inOrder shoud go ahead for next elemet
		else if( sum > (val1+val2) )
		{
			checkInOrder=true;
		}
		else if(sum < (val1+val2))
		{
			checkRevInOrder=true;
		}

		//once we reach this point where we have something like
		// val1>=val2 => we have not found any pair and now we are
		//crossing mid point

		if(val1>=val2)
		  return false;
	  }//outer while
    }

    // 1. use Inorder in both tree
    // 2. push all the left for both the tree => min=top
    // 3. if both stack not empty
    //          once we comapre the top=> 
    //          min stack will have pop and 
    //    then push right and then again all left (min on top again)
    // 4. else if any stack empty=> print inorder other      
    void mergeOutput(BST bst2)
    {
    	Node curr1=root;
    	Node curr2=bst2.root;

    	//do as inOrder of both but check the top of both
    	//min of them=>. operate under that

    	ArrayDeque<Node> stack1=new ArrayDeque<Node>();
    	ArrayDeque<Node> stack2=new ArrayDeque<Node>();

    	boolean operateFirst=true,operateSecond=true;
    	int val1=0,val2=0;
    	while(true)
    	{
    		//first push onto both stack the left>left>left..
    			while(curr1!=null)
    			{
    				stack1.push(curr1);
    				curr1=curr1.left;
    			}
    			while(curr2!=null)
    			{
    				stack2.push(curr2);
    				curr2=curr2.left;
    			}
    			//System.out.println("top of both="+stack1.peek().data+" "+stack2.peek().data);

    			//IF ANY ONE OF THE STACK IS EMPTY 
    			if(stack1.isEmpty() || stack2.isEmpty())
    			{
    				if(stack1.isEmpty() && stack2.isEmpty())
    					break;
    				//PRINT STACK2 INORDER
    				else if(stack1.isEmpty())  
    				{
    					boolean flag=true;
						while(flag)
						{
						  if(curr2!=null)
						  {
							stack2.push(curr2);
							curr2=curr2.left;
						  }
						  else
						  {
							if(stack2.isEmpty())
							{
								flag=false;
							}
							else
							{			
								curr2=stack2.pop();
								System.out.print(curr2.data+" ");
								curr2=curr2.right;
						    }
					      }

					    } //once flag
					    break;
    				  
    				}
    				//PRINT STACK1 INORDER
    				else if(stack2.isEmpty())
    				{
    				   boolean flag=true;
						while(flag)
						{
						  if(curr1!=null)
						  {
							stack1.push(curr1);
							curr1=curr1.left;
						  }
						  else
						  {
							if(stack1.isEmpty())
							{
								flag=false;
							}
							else
							{			
								curr1=stack1.pop();
								System.out.print(curr1.data+" ");
								curr1=curr1.right;
						    }
					      }

					    } //once flag
					    break;
    				}
    			
    			}
    			//IF NONE IS EMPTY => find MIN and proceed
    			else
    			{
    				if(stack1.peek().data < stack2.peek().data)
    				{
    					curr1=stack1.pop();
    					//print the min
    					System.out.print(curr1.data+" ");
    					curr1=curr1.right;
    				}
    				else
    				{
    					curr2=stack2.pop();
    					//print the min
    					System.out.print(curr2.data+" ");
    					curr2=curr2.right;
    				}
    			}
    				
    		
    	}
    }



    // find the intersection of the two bsts
    //use the code same as merge 
    // but little modification
    // 1. use Inorder in both tree
    // 2. push all the left for both the tree => min=top
    // 3. if both stack not empty
    //          once we comapre the top=> 
    //          min stack will have pop and 
    //    then push right and then again all left (min on top again)
    // 4. else if any stack empty=> break the process
    void findCommonNodes(BST bst2)
    {
    	Node curr1=root;
    	Node curr2=bst2.root;

    	//do as inOrder of both but check the top of both
    	//min of them=>. operate under that

    	ArrayDeque<Node> stack1=new ArrayDeque<Node>();
    	ArrayDeque<Node> stack2=new ArrayDeque<Node>();

    	boolean operateFirst=true,operateSecond=true;
    	int val1=0,val2=0;
    	while(true)
    	{
    		//first push onto both stack the left>left>left..
    			while(curr1!=null)
    			{
    				stack1.push(curr1);
    				curr1=curr1.left;
    			}
    			while(curr2!=null)
    			{
    				stack2.push(curr2);
    				curr2=curr2.left;
    			}
    			//System.out.println("top of both="+stack1.peek().data+" "+stack2.peek().data);

    			//IF ANY ONE OF THE STACK IS EMPTY 
    			if(stack1.isEmpty() || stack2.isEmpty())
    			{
    				//empty=> no more elements
    				break;
    			}
    			//IF NONE IS EMPTY => find MIN and proceed
    			else
    			{
    				if(stack1.peek().data == stack2.peek().data)
    				{
    					//print the nodes
    					//set both the curr1, curr2
    					curr1=stack1.pop();
    					curr1=curr1.right;

    					curr2=stack2.pop();
    					//since both are same=> print one
    					System.out.print(curr2.data+" ");
    					curr2=curr2.right;


    				}
    				else if(stack1.peek().data < stack2.peek().data)
    				{
    					curr1=stack1.pop();
    					curr1=curr1.right;
    				}
    				else
    				{
    					curr2=stack2.pop();
    					curr2=curr2.right;
    				}
    			}
    				
    		
    	}
    	System.out.println(" ");
    }



    //single list to balanced bst
    // 1->2->3->4->5->7->12->14->21->34

    //---------TOP DOWN APPROACH-------------
    // 1. O(n logn) [ sort of binary search, find the middle each time for all elements]
    //middle=root , middle.left= search(left half) , middle.right=search(right half)

    void listToBst1(List list)
    {
    	//pass the list and root
    	//get the root of the tree
    	// call(list,low,high)

    	this.root=getListToBst1(list,0,list.size()-1);

    }
    Node getListToBst1(List list,int low,int high)
    {
    	//create and recur until low<=high
    	//once low>high=> return null as left or right of a node
    	if(low>high)
    		return null;

    	//create a node , data=middle of list
    	//asiign left and right
    	int mid=(low+high)/2;

    	//list contains integer
    	Node root=new Node((int)list.get(mid));

    	//get the left child from left side of list
    	root.left=getListToBst1(list,low,mid-1);

    	root.right=getListToBst1(list,mid+1,high);

    	return root;
    }


    //---------USER-DEFINED LINKED-LIST TO BST IN-PLACE (no new node)------------
    //--- prev-data-next  prev-data-next prev-data-next -----
    /*
        same code as below  : Only one change
        if(n<=0)
    		return null;
    	Node prev=getListToBst2(n/2);
    	Node root=new Node(head.data);
    	head=head.next();
    	root.prev=prev;
    	root.next=getListToBst2(n-n/2-1);
        return root;
	*/
    //----------------------------------------



    //single list to balanced bst
    // 1->2->3->4->5->7->12->14->21->34

    //---------------BOTTOM UP APPROACH----------
    // 1. O(n)  take N/2 nodes=> Construct left-subtree
    //                           CREATE_ROOT
    //      link leftSubtree     ROOT.left=left-subtree

    //   take remaining node => Construct right-subtree
    //     link righSubtree     ROOT.right=right-subtree
   
    void listToBst2(List list)
    {
    	//pass the list and root
    	//get the root of the tree
    	// call(list,sizeOfList)

    	head=list.iterator();
    	this.root=getListToBst2(list.size());


    }
    Node getListToBst2(int n)
    {
    	if(n<=0)
    		return null;

    	//construct first node when you hit left-most node
    	Node left=getListToBst2(n/2);

    	//initially above call is made till last level
    	//and null is returned

    	//NOW create first leaf node
    	int x=(int)head.next();
    	
    	Node root=new Node(x);

    	//if this was a list
    	//head=head.next();

    	root.left=left;

    	//right side=> remaining nodes
    	// total(n)-left (n/2) + root(1)
    	root.right=getListToBst2(n-n/2-1);

      return root;
    }

    //all nodes k1<=.keys.>=k2
    //each time, recurse:
    // if k1<curr.data => go left
    // once it breaks: => check for k1<=key>=k2 => print
    // if k2>curr.data => go right
    void getRangeKeys(int k1,int k2)
    {
    	System.out.println("printing keys in between");
    	getKeysInRange(root,k1,k2);
    	System.out.println();
    }
    void getKeysInRange(Node curr,int k1,int k2)
    {
    	if(curr==null)
    		return;
    	//we have possible keys on left
    	if(k1<curr.data)
    		getKeysInRange(curr.left,k1,k2);

    	// curr.data lies in between
    	if(k1<=curr.data && curr.data<=k2)
    		 System.out.print(curr.data+" ");

    	//we have possible keys on right too	
    	if(k2>curr.data)
    		getKeysInRange(curr.right,k1,k2);
    } 

    //remove keys not in range [k1,k2]
    //1. modify each node's left and right link
    //2. root.left=call();
    //3. root.right=call();
    //4. if root.data < k1 => all nodes to the left of root are to be deleted 
                             // => we simply [return the root.right] to prev call
    //5. if root.data > k2 => all nodes to the right of root are to be deleted
                             // => we simply [ return the root.left ] to the prev call
    // otherwise  if root.data satisfies both condition => we simply return the root

    void removeKeysNotInRange(int k1,int k2)
    {
    	root=getModifiedRootAfterRemoving(root,k1,k2);
    }
    Node getModifiedRootAfterRemoving(Node curr,int k1,int k2)
    {
    	if(curr==null)
    		return null;

    	curr.left=getModifiedRootAfterRemoving(curr.left,k1,k2);
    	curr.right=getModifiedRootAfterRemoving(curr.right,k1,k2);

    	//remove all left subtree nodes=. simple return curr.right
    	if(curr.data < k1)
    		return curr.right;

    	if(curr.data > k2)
    		return curr.left;

    	return curr;
    }

    //this generates a btree from bst after the addition of each node to greater nodes
    //root=root+root.right
    //root.left=root+root.left
    void addGreaterNodes()
    {
    	sumNodesRevInorder(root);
    	//so that other function can as well use this static class
    	storeSum.totsum=0;
    }
    static class storeSum
    {
    	static int totsum=0;
    	public storeSum()
    	{
    		System.out.println("constructor is static");
    	}
    }
    void sumNodesRevInorder(Node curr)
    {
    	//storeSum.totsum;
    	if(curr==null)
    		return;

    	sumNodesRevInorder(curr.right);

    	curr.data=curr.data+storeSum.totsum;
    	storeSum.totsum=curr.data;

    	sumNodesRevInorder(curr.left);
    }


    //this generates a binary tree
    //above approach only with one diff
    //greatest node will have a value 0 (bcoz no nodes> this largest)
    void addOnlyGreaterNodes()
    {
    	System.out.println("static data contains="+storeSum.totsum);
    	sumNodesRevInorderWithoutCurrNode(root);

    	//so that other function can as well use this static class
    	storeSum.totsum=0;
    }
    void sumNodesRevInorderWithoutCurrNode(Node curr)
    {
    	if(curr==null)
    		return;

    	sumNodesRevInorderWithoutCurrNode(curr.right);

    	int tempCurrNode=curr.data;
    	curr.data=storeSum.totsum;
    	storeSum.totsum=storeSum.totsum+tempCurrNode;

    	sumNodesRevInorderWithoutCurrNode(curr.left);
    }

    void secondLargestNode()
    {
    	getSecondLargestNode(root);
    	
    }
    //to keep a count of nodes
    //make a static class

    static class CountNodes
    {
    	static int count=0;
    }
    void getSecondLargestNode(Node curr)
    {
    	if(curr==null)
    		return;
    	getSecondLargestNode(curr.right);
    	CountNodes.count++;
    	if(CountNodes.count==2)
    	{
    		System.out.println("the second largest is"+curr.data);
    		return;
    	}
    	else
    	getSecondLargestNode(curr.left);

    }

    //bst from preOrder
    void constructBstFromPre(int[]preOrder)
    {
    	//min will restrict the min for a node
    	//max will restrict the max for a node
    	//key will be the curr data to be processed from preOrder array
    	//if key falls between both => construct node and call its left and right

    	//this.root=getBstFromPre(0,preOrder,Integer.MIN_VALUE,Integer.MAX_VALUE);
    	this.root=getBstFromPre(preOrder,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    //we cant pass preIndex as local variable, because 
    // curr.left will be called till whole of left subtree is constructed
    //but we need the right subtree call to have index greater than the processed index so far
    //therefore, commented code replaces with new static variable

    static class Index
    {
    	static int preIndex=0;
    }
    //Node getBstFromPre(int preIndex, int[]preOrder,int min,int max)
    Node getBstFromPre(int[]preOrder,int min,int max)
    {
    	if(Index.preIndex>=preOrder.length)
    		return null;

    	int key=preOrder[Index.preIndex];
    	//we dont allocate node yet, bcoz the key may not lie in range
    	//in that case we simply return null
    	//this case arises when we have hit a left most node's left and right
    	Node curr=null;

    	//preIndex is inc only when key satisfies this
    	//and if preIndex goes to size the base condition returnds false
    	//so it never checks array bound exception
    	if(key>min && key<max)
    	{
    		curr=new Node(key);

    		//automatic the left and right are set to null
    		// so if it is the last node=> we dont need to call or set its left and right
    		Index.preIndex++;
    			//curr.left=getBstFromPre(preIndex,preOrder,min,key);
    			curr.left=getBstFromPre(preOrder,min,key);
    			//curr.right=getBstFromPre(preIndex,preOrder,key,max);
    			curr.right=getBstFromPre(preOrder,key,max);
    	}
    	return curr;
    }




}

public class binarySearchTree
{
	static void pl()
	{
		System.out.println();
	}
	static void pl(Object s)
	{
		System.out.println(s);
	}
	//level order is passed , PRINT the inOrder
	static void printSortedOrder(int arr[],int low,int high)
	{
		if(low>high)
			return;
		printSortedOrder(arr, 2*low+1 ,high);
		System.out.print(arr[low]+" ");
		printSortedOrder(arr, 2*low+2 , high);
	}
	public static void main(String[]args)
	{

		BST bst=new BST();
		bst.insert(5);
		bst.insert(2);
		bst.insert(3);
		bst.insert(4);
		bst.insert(7);
		bst.insert(6);
		bst.insert(12);


		pl("the preOrder is");
		bst.displayPre();
		pl();
		pl("the postOrder");
		bst.displayPost();
		pl();

		pl("the inOrder");
		bst.displayIn();
		pl();

		pl("the levelOrder");
		bst.displayLevel();
		pl();
		pl();
		
		pl("the height is");
		int h=bst.height();
		System.out.println(h);
		pl();

		pl("the max element of tree is");
		int max=bst.max();
		System.out.println(max);
		pl();

		pl("the min element of tree is");
		int min=bst.min();
		System.out.println(min);
		pl();

		pl("the diameter of the tree");
		int diam=bst.diameter();
	    System.out.println(diam);
		pl();		

		pl("the maximum width of the tree");
		int wd1=bst.width1();
	    System.out.println("maximum width="+wd1);
		pl();
		pl("the maximum width of the tree");	
		int wd2=bst.width2();
		System.out.println("maximum width="+wd2);
		pl();

		pl("the maximum sum out of each level");
		bst.maxSumLevel();
		pl();

		pl("print all the paths from root to leaf");
		bst.printPathsFromRoot();
		pl();

		pl("print the sum of each path and also the maximum sum along all");
		bst.maxSumAllPathFromRoot();
		pl();

		pl("create a tree = mirror of the given bst");
		pl("level order of old tree");
		bst.displayLevel();
		BST mirror1=new BST();
		mirror1.getMirror(bst);
		pl("mirror is created");
		pl("the level order for new mirror would look like");
		mirror1.displayLevel();
		pl();

		pl();
		int k=2;
		pl("nodes at k="+k+" distance from root");
		bst.printNodesKdistance(k);
		pl();


		int val1=5;
		pl();
		pl("the inOrder-predecessor of the node="+val1);
		int val1Res=bst.inOrderPred(val1);
		if(val1Res==Integer.MIN_VALUE)
			pl("does not exists");
		else
			pl("pred is="+val1Res);

		pl();

		pl();
		pl("the inOrder-succcessor of the node="+val1);
		int val2Res=bst.inOrderSucc(val1);
		if(val2Res==Integer.MIN_VALUE)
			pl("does not exists");
		else
			pl("pred is="+val2Res);

		pl();
		pl("check if the tree is bst or not");
		boolean bstOrNot=bst.checkBst1();
		pl("the tree passed is bst="+bstOrNot);
		pl();

		pl();
		pl("Find the DEEPEST node in the tree");
		int deepNode=bst.findDeepestNode();
		pl("the deepest node has val="+deepNode);
		pl();

		pl("Find the size of the tree");
		int sz=bst.size();
		System.out.println("the no. of nodes in the tree is="+sz);
		pl();


		pl("Find the leaves of the tree");
		bst.findAllLeafFullHalf();
		pl();


		//-------------CREATE a binary tree------//
		BST bstTemp=new BST();
		bstTemp.root=new Node(6);
		bstTemp.root.left=new Node(2);
		bstTemp.root.right=new Node(7);
		bstTemp.root.left.left=new Node(1);
		bstTemp.root.left.right=new Node(5);
		bstTemp.root.right.right=new Node(10);


		pl("-----------------NEW BST--------- ");
		pl("the levelOrder of new binary tree");
		bstTemp.displayLevel();
		pl();


		// time complexity more
		pl("check if the tree is bst or not");
		boolean bstTempBstOrNot1=bstTemp.checkBst1();
		pl("the tree passed is bst="+bstTempBstOrNot1);
		pl();
		//less time complexity
		pl("check if the tree is bst or not");
		boolean bstTempBstOrNot2=bstTemp.checkBst2();
		pl("the tree passed is bst="+bstTempBstOrNot2);
		pl("------------------------");

		//inorder traversal and check is array is sorted
		//atleast one inversion=> not a bst


		//---------------------------//
		pl("seach the tree for nodes");
		pl(bst.search(3)+"");
		pl(bst.search(6)+"");

		pl();
		int a=5;
		int b=6;
		pl("lowest common ancesotr of the nodes");
		int lca=bst.lowestCommonAcncestorRec(a,b);
		int lca1=bst.lowestCommonAcncestorIter(a,b);
		pl("given a="+a+" and b="+b+" is Rec="+lca+" & Iter="+lca1);
		pl();

		pl("finding kth-smallest elemnt in the tree");
		int kVal=12;
		int kSmall=bst.kthSmallest(kVal);
		if(kSmall==Integer.MIN_VALUE)
			pl("wrong data");
		else
			pl("result of k="+kVal+"th-smallest="+kSmall);
		pl();


		pl("------DELETING node from the tree--------");
		int delNodeData=5;
		bst.delete(delNodeData);
		pl("after deleting node="+delNodeData+" the level order looks like");
		pl("the levelOrder");
		bst.displayLevel();
		pl();
		pl();

		//return the next larger number whatsoever for this number if exists
		pl("ceil of a value");
		int getCeilData=1;
		int retCeilData=bst.ceil(getCeilData);
		pl("returned ceil for "+getCeilData+"="+ retCeilData);
		pl();

		//return the previous number whatsoever for this number if exists
		pl("floor of a value");
		int getFloorData=5;
		int retFloorData=bst.floor(getFloorData);
		pl("returned ceil for "+getFloorData+"="+ retFloorData);
		pl();		

		//print the reverseInOrder
		pl();
		bst.inOrderReverse();
		pl();
		pl();

		//sum of two elements equals target
		int targetSum=4;
		pl("to check if any two pairs has sum="+targetSum);
		boolean isPresent=bst.isPairPresent(targetSum);
		if(isPresent)
			pl("we do have a pair");
		else
			pl("we do not have any pair");
		pl();


		pl("------Create Two BST and MERGE the output as sorted-------");
		BST bstM1=new BST();
		bstM1.insert(5);
		bstM1.insert(3);
		bstM1.insert(7);

		BST bstM2=new BST();
		bstM2.insert(4);
		bstM2.insert(1);
		bstM2.insert(9);
		bstM2.insert(6);
		bstM2.insert(11);

		pl("--the level order traversal of 1st bst");
		bstM1.displayLevel();
		pl();

		pl("the leaves of the 1st tree");
		bstM1.findAllLeafFullHalf();
		pl();

		pl("the maximum sum out of each level of 1st bst");
		bstM1.maxSumLevel();
		pl();

		pl("print all the paths from root to leaf of 1st bst");
		bstM1.printPathsFromRoot();
		pl();

		pl("print the sum of each path and also the maximum sum along all of 1st bst");
		bstM1.maxSumAllPathFromRoot();
		pl();
		pl("-------------------------------");


		pl();
		pl("-----------CREATEd TWO NEW BST will merge but see the order--------");
		pl("Inorder 1 : ");
		bstM1.displayIn();
		pl("Inorder 2 : ");
		bstM2.displayIn();

		pl("--------MERGE OF THE TWO BSTs------");
		//1st approach => insert elements of one on other
		//2nd approach => inorder of both to array. Sort. Merge. Construct array to BST
		//[optimized one ] 3rd approach => merge inplace of two circular list ???????????????

		pl();
		//2nd approach
		bstM1.mergeOutput(bstM2);
		pl("------------------");
		pl();


		pl("-----COMMON NODES OF TWO BST's-----------");
		BST bstc1=new BST();
		bstc1.insert(5);bstc1.insert(1);bstc1.insert(10);bstc1.insert(0);
		bstc1.insert(4);bstc1.insert(7);bstc1.insert(9);

		BST bstc2=new BST();
		bstc2.insert(10);bstc2.insert(7);bstc2.insert(20);
		bstc2.insert(4);bstc2.insert(9);bstc2.insert(1);
		bstc2.insert(5);

		pl("two bsts have nodes as :inorder");
		bstc1.displayIn();
		pl();
		bstc2.displayIn();
		pl();
		pl("THE COMMON nodes of BOTH the bsts are ");
		bstc1.findCommonNodes(bstc2);
		pl();
		pl();



		pl("-----------CREATE A BST FROM SINGLE LINKED LIST 1St APPROACH--------");
		//first create alist (SORTED ORDER)
		LinkedList<Integer> li=new LinkedList<Integer>();
		li.add(1);li.add(4);li.add(5);
		li.add(6);li.add(7);li.add(9);
		li.add(10);li.add(12);li.add(14);
		li.add(16);li.add(21);li.add(22);
		li.add(26);li.add(28);li.add(30);
		//creare a bst
		BST bstFromList=new BST();
		bstFromList.listToBst1(li);
		pl();
		pl("the list is");
		pl(li);
		pl();
		pl("once bst is created from list, do all operation");
		pl("1. level order");
		bstFromList.displayLevel();
		pl();

		pl("the height is");
		int hl=bstFromList.height();
		System.out.println(hl);
		pl();

		pl("the max element of tree is");
		int maxl=bstFromList.max();
		System.out.println(maxl);
		pl();

		pl("the min element of tree is");
		int minl=bstFromList.min();
		System.out.println(minl);
		pl();

		pl("the diameter of the tree");
		int diaml=bstFromList.diameter();
	    System.out.println(diaml);
		pl();		

		pl("the maximum width of the tree");
		int wd1l=bstFromList.width1();
	    System.out.println("maximum width="+wd1l);
		pl();

		pl("the leaves of the bst are");
		bstFromList.findAllLeafFullHalf();
		pl();

		pl("the maximum sum out of each level");
		bstFromList.maxSumLevel();
		pl();

		pl("print all the paths from root to leaf");
		bstFromList.printPathsFromRoot();
		pl();
		
		pl("print the sum of each path and also the maximum sum along all");
		bstFromList.maxSumAllPathFromRoot();
		pl();


		pl("create a tree = mirror of the given bst");
		pl();
		pl("level order of old tree");
		bstFromList.displayLevel();
		BST mirror2=new BST();
		mirror2.getMirror(bstFromList);
		pl();
		pl();
		pl("mirror is created");
		pl("the level order for new mirror would look like");
		mirror2.displayLevel();
		pl();



		pl();
		pl("-----CREATE A BST FROM SINGLE LINKED LIST 2nd approach------");
		LinkedList<Integer> li1=new LinkedList<Integer>();
		li1.add(12);li1.add(14);li1.add(15);
		li1.add(16);li1.add(17);li1.add(19);
		li1.add(20);li1.add(22);li1.add(24);
		li1.add(26);li1.add(31);li1.add(32);
		li1.add(36);li1.add(38);li1.add(40);
		//creare a bst
		BST bstFromList2=new BST();
		bstFromList2.listToBst2(li1);	
		pl("the list is");
		pl(li1);
		pl();
		pl("once bst is created from list, do all operation");
		pl("1. level order");
		bstFromList2.displayLevel();
		pl();
		pl("the height is");
		int hl2=bstFromList2.height();
		System.out.println(hl2);
		pl();

		pl("the max element of tree is");
		int maxl2=bstFromList2.max();
		System.out.println(maxl2);
		pl();

		pl("the min element of tree is");
		int minl2=bstFromList2.min();
		System.out.println(minl2);
		pl();

		pl("the diameter of the tree");
		int diaml2=bstFromList2.diameter();
	    System.out.println(diaml2);
		pl();		

		pl("the maximum width of the tree");
		int wd1l2=bstFromList2.width1();
	    System.out.println("maximum width="+wd1l2);
		pl();
		pl("--------------------------------------------");

		pl();
		pl("-----CREATE A BST FROM sorted array approach------");
		/*
			if(low>high)
			return null

			//first get the middle index
			middle=low+high/2;
			New newNode(middle)
			newNode.left(low,mid-1)
			newNode.right(mid+1,high)
			return newNode
		*/
		pl("");

		pl("-------CREATE A BST FROM BINARY TREE----------");
		pl("");
		/*
		//1. store the tree's data in array
	    //2. sort the array
	    //3. create a bst from sorted array
	    void convertToBst()
	    {
		 ArrayList<Integer>li;

		 //store in arary
		 li=storedToArray(root);

		 //sort the array
		 Collections.sort(li);

		 //modify the tree => bst
		 root=getConvertedBst(li,0,li.size()-1);
		 System.out.println("successfully converted : now check for all bst output");
	   }
	   Node getConvertedBst(ArrayList<Integer>li,int low,int high)
	   {
		 if(low>high)
			return null;
		 int mid=(low+high)/2;

		 Node newNode=new Node(li.get(mid));

		 newNode.left=getConvertedBst(li,low,mid-1);
		 newNode.right=getConvertedBst(li,mid+1,high);
		 return newNode;
	   }
	   ArrayList<Integer> storedToArray(Node curr)
	   {   
		 ArrayList<Integer>li=new ArrayList<Integer>();
		 ArrayDeque<Node>st=new ArrayDeque<Node>();

		 //inorder storage of Binary tree
		 boolean flag=true;
		 while(flag)
		 {
			if(curr!=null)
			{
				st.push(curr);
				curr=curr.left;
			}
			else
			{
				if(st.isEmpty())
				{
					flag=false;
				}
				else
				{
					curr=st.pop();
					//System.out.print(curr.data+" ");
					li.add(curr.data);
					curr=curr.right;
				}
			}
		 }

		return li;
	   }
		*/
	   pl("");

	   pl("-----level order of bst stored in array- Print the array in sortedOrder-----");
	   int levelOrderArray[]=new int[]{10,6,14,3,8,11,16,1,5,7,9};
	   printSortedOrder(levelOrderArray,0,levelOrderArray.length-1);
	   pl("");
	   pl("");

	   pl("------coming back to the original bst-----------");
	   bst.insert(1);
	   bst.insert(9);
	   bst.insert(10);
	   bst.insert(8);
	   pl("----LEVEL ORDER of BST------");
	   bst.displayLevel();
	   pl("");
	   pl("-----all nodes between range[k1,k2]-------");
	   int key1=3;
	   int key2=10;
	   pl("all nodes between key1="+key1+" & key2="+key2);
	   pl();
	   bst.getRangeKeys(key1,key2);
	   pl("");
	   pl("-----remove all nodes except range[k1,k2]-------");
	   int k1=4;
	   int k2=9;
	   pl("remove all nodes except the range of k1= "+k1+" & k2="+k2);
	   bst.removeKeysNotInRange(k1,k2);
	   pl("after removing all not in the range-Level order--");
	   bst.displayLevel();
	   pl();

	   pl();
	   pl("-------construct a new bst--------");
	   BST bst11=new BST();
	   bst11.insert(10);bst11.insert(7);bst11.insert(15);
	   bst11.insert(3);bst11.insert(12);bst11.insert(18);
	   bst11.insert(6);bst11.insert(16);
	   pl("before adding: the LEVEL order of bst is");
	   pl("the level order is");
	   bst11.displayLevel();
	   pl("the in order is");
	   bst11.displayIn();
	   pl();

	   pl("ADD ALL GREATER VALUES TO EACH NODE'S KEY");
	   bst11.addGreaterNodes();
	   pl("after adding: the LEVEL order of binary tree is");
	   bst11.displayLevel();
	   pl();
	   pl("-----------------");
	   pl();

	   pl("-------construct a new bst--------");
	   BST bst12=new BST();
	   bst12.insert(11);bst12.insert(2);bst12.insert(29);
	   bst12.insert(1);bst12.insert(7);bst12.insert(15);
	   bst12.insert(40);bst12.insert(35);
	   pl("before adding :the level order of bst is");
	   pl("the level order is");
	   bst12.displayLevel();
	   pl("the in order is");
	   bst12.displayIn();
	   pl();

	   pl("ADD ALL GREATER NODES AND STORE IN NODE (NOT THE CURR NODE'S DATA)");
	   bst12.addOnlyGreaterNodes();
	   pl("after adding: the LEVEL order of binary tree is");
	   bst12.displayLevel();
	   pl();
	   pl("-----------------");
	   pl();




	   pl("Given Preorder Of A BST=> CONSTRUCT THE BST of the tree");
	   int []preOrder=new int[]{12,6,4,1,5,9,7,10,22,16,14,21,28,26,30,31};

	   //approach1 => O(n2) , saerching for such a index in array in each call=> O(n)
	   //          => 1. 0th index=root
	   //		   => 2. left=> first index , val[index]>root =>4 root.left=call(root.left,int low+1,high=index-1)
	   //		   => 3. right=>                                  root.right=call(root.right,index,high) 

	   //approach2 => O(n) , restrict the range for each node=> min, max
	   BST bstFromPre=new BST();
	   bstFromPre.constructBstFromPre(preOrder);
	   pl("tree constructed successfully-do operations on tree");
	   pl();
	   bstFromPre.displayLevel();
	   pl();
	   bstFromPre.displayIn();
	   pl();
	   pl("--------------");

	   pl("---THE new tree-----");
	   BST bstT1=new BST();
	   bstT1.insert(5);bstT1.insert(10);
	   bstT1.insert(3);bstT1.insert(13);
	   bstT1.insert(15);
	   bstT1.displayIn();
	   pl();
	   pl("the second largest node of the tree is");
	   bstT1.secondLargestNode();
	   pl("---------------------------------");
	   pl();


	}
}

/*
Note: /home/lokender/workspace1/GeeksForGeeks/binarySearchTree.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
the preOrder is
the Iterative preOrder
5 2 3 4 7 6 12 
the Recursive preOrder
5 2 3 4 7 6 12 

the postOrder
the iterative post order
4 3 2 6 12 7 5 
the recursive post order
4 3 2 6 12 7 5 

the inOrder
the iterative in order
2 3 4 5 6 7 12 
the recursive  order
2 3 4 5 6 7 12 

the levelOrder
the iterative level order
5 2 7 3 6 12 4 
the recursive level order
5 2 7 3 6 12 4 

the height is
4

the max element of tree is
12

the min element of tree is
2

the diameter of the tree
6

the maximum width of the tree
maximum width=3

the maximum width of the tree
level:1 =Nodes=1
level:2 =Nodes=2
level:3 =Nodes=3
level:4 =Nodes=1
maximum width=3

the maximum sum out of each level
we got the maximum sum at level=3
we got the maximum sum =21

print all the paths from root to leaf
path:  5 2 3 4
path:  5 7 6
path:  5 7 12

print the sum of each path and also the maximum sum along all
path ends with sum: 14
path ends with sum: 18
path ends with sum: 24
the maximum sum along all paths =24

create a tree = mirror of the given bst
level order of old tree
the iterative level order
5 2 7 3 6 12 4 
the recursive level order
5 2 7 3 6 12 4 mirror is created
the level order for new mirror would look like
the iterative level order
5 7 2 12 6 3 4 
the recursive level order
5 7 2 12 6 3 4 

nodes at k=2 distance from root
7 2 

the inOrder-predecessor of the node=5
pred is=6


the inOrder-succcessor of the node=5
pred is=4

check if the tree is bst or not
the tree passed is bst=false


Find the DEEPEST node in the tree
the deepest node has val=4

Find the size of the tree
the no. of nodes in the tree is=7

Find the leaves of the tree
the tree has 3 leaves
[12, 6, 4]
the tree has 2 full nodes
[5, 7]
the tree has 2 half nodes
[2, 3]

-----------------NEW BST--------- 
the levelOrder of new binary tree
the iterative level order
6 2 7 1 5 10 
the recursive level order
6 2 7 1 5 10 
check if the tree is bst or not
the tree passed is bst=true

check if the tree is bst or not
the tree passed is bst=true
------------------------
seach the tree for nodes
false
false

lowest common ancesotr of the nodes
elemets doesnt exists
given a=5 and b=6 is Rec=-2147483648 & Iter=-2147483648

finding kth-smallest elemnt in the tree
wrong data

------DELETING node from the tree--------
node with data=4 was not found in the tree
after deleting node=5 the level order looks like
the levelOrder
the iterative level order
4 7 2 12 6 3 4 
the recursive level order
4 7 2 12 6 3 4 

ceil of a value
returned ceil for 1=12

floor of a value
returned ceil for 5=4


print the reverse inOrder of the tree
2 3 4 4 6 7 12 

to check if any two pairs has sum=4
we do not have any pair

------Create Two BST and MERGE the output as sorted-------
--the level order traversal of 1st bst
the iterative level order
5 3 7 
the recursive level order
5 3 7 
the leaves of the 1st tree
the tree has 2 leaves
[3, 7]
the tree has 1 full nodes
[5]
the tree has 0 half nodes
[]

the maximum sum out of each level of 1st bst
we got the maximum sum at level=2
we got the maximum sum =10

print all the paths from root to leaf of 1st bst
path:  5 3
path:  5 7

print the sum of each path and also the maximum sum along all of 1st bst
path ends with sum: 8
path ends with sum: 12
the maximum sum along all paths =12

-------------------------------

-----------CREATEd TWO NEW BST will merge but see the order--------
Inorder 1 : 
the iterative in order
3 5 7 
the recursive  order
3 5 7 
Inorder 2 : 
the iterative in order
1 4 6 9 11 
the recursive  order
1 4 6 9 11 
--------MERGE OF THE TWO BSTs------

1 3 4 5 6 7 9 11 ------------------

-----COMMON NODES OF TWO BST's-----------
two bsts have nodes as :inorder
the iterative in order
0 1 4 5 7 9 10 
the recursive  order
0 1 4 5 7 9 10 

the iterative in order
1 4 5 7 9 10 20 
the recursive  order
1 4 5 7 9 10 20 

THE COMMON nodes of BOTH the bsts are 
1 4 5 7 9 10  


-----------CREATE A BST FROM SINGLE LINKED LIST 1St APPROACH--------

the list is
[1, 4, 5, 6, 7, 9, 10, 12, 14, 16, 21, 22, 26, 28, 30]

once bst is created from list, do all operation
1. level order
the iterative level order
12 6 22 4 9 16 28 1 5 7 10 14 21 26 30 
the recursive level order
12 6 22 4 9 16 28 1 5 7 10 14 21 26 30 
the height is
4

the max element of tree is
30

the min element of tree is
1

the diameter of the tree
7

the maximum width of the tree
maximum width=8

the leaves of the bst are
the tree has 8 leaves
[1, 5, 7, 10, 14, 21, 26, 30]
the tree has 7 full nodes
[12, 6, 22, 4, 9, 16, 28]
the tree has 0 half nodes
[]

the maximum sum out of each level
we got the maximum sum at level=4
we got the maximum sum =114

print all the paths from root to leaf
path:  12 6 4 1
path:  12 6 4 5
path:  12 6 9 7
path:  12 6 9 10
path:  12 22 16 14
path:  12 22 16 21
path:  12 22 28 26
path:  12 22 28 30

print the sum of each path and also the maximum sum along all
path ends with sum: 23
path ends with sum: 27
path ends with sum: 34
path ends with sum: 37
path ends with sum: 64
path ends with sum: 71
path ends with sum: 88
path ends with sum: 92
the maximum sum along all paths =92

create a tree = mirror of the given bst

level order of old tree
the iterative level order
12 6 22 4 9 16 28 1 5 7 10 14 21 26 30 
the recursive level order
12 6 22 4 9 16 28 1 5 7 10 14 21 26 30 

mirror is created
the level order for new mirror would look like
the iterative level order
12 22 6 28 16 9 4 30 26 21 14 10 7 5 1 
the recursive level order
12 22 6 28 16 9 4 30 26 21 14 10 7 5 1 

-----CREATE A BST FROM SINGLE LINKED LIST 2nd approach------
the list is
[12, 14, 15, 16, 17, 19, 20, 22, 24, 26, 31, 32, 36, 38, 40]

once bst is created from list, do all operation
1. level order
the iterative level order
22 16 32 14 19 26 38 12 15 17 20 24 31 36 40 
the recursive level order
22 16 32 14 19 26 38 12 15 17 20 24 31 36 40 
the height is
4

the max element of tree is
40

the min element of tree is
12

the diameter of the tree
7

the maximum width of the tree
maximum width=8

--------------------------------------------

-----CREATE A BST FROM sorted array approach------

-------CREATE A BST FROM BINARY TREE----------


-----level order of bst stored in array- Print the array in sortedOrder-----
1 3 5 6 7 8 9 10 11 14 16 

------coming back to the original bst-----------
----LEVEL ORDER of BST------
the iterative level order
4 7 2 12 6 3 9 1 4 8 10 
the recursive level order
4 7 2 12 6 3 9 1 4 8 10 
-----all nodes between range[k1,k2]-------
all nodes between key1=3 & key2=10

printing keys in between
7 6 4 8 9 10 

-----remove all nodes except range[k1,k2]-------
remove all nodes except the range of k1= 4 & k2=9
after removing all not in the range-Level order--
the iterative level order
4 7 9 6 8 
the recursive level order
4 7 9 6 8 

-------construct a new bst--------
before adding: the LEVEL order of bst is
the level order is
the iterative level order
10 7 15 3 12 18 6 16 
the recursive level order
10 7 15 3 12 18 6 16 the in order is
the iterative in order
3 6 7 10 12 15 16 18 
the recursive  order
3 6 7 10 12 15 16 18 

ADD ALL GREATER VALUES TO EACH NODE'S KEY
after adding: the LEVEL order of binary tree is
the iterative level order
71 78 49 87 61 18 84 34 
the recursive level order
71 78 49 87 61 18 84 34 
-----------------

-------construct a new bst--------
before adding :the level order of bst is
the level order is
the iterative level order
11 2 29 1 7 15 40 35 
the recursive level order
11 2 29 1 7 15 40 35 the in order is
the iterative in order
1 2 7 11 15 29 35 40 
the recursive  order
1 2 7 11 15 29 35 40 

ADD ALL GREATER NODES AND STORE IN NODE (NOT THE CURR NODE'S DATA)
static data contains=0
after adding: the LEVEL order of binary tree is
the iterative level order
119 137 75 139 130 104 0 40 
the recursive level order
119 137 75 139 130 104 0 40 
-----------------

Given Preorder Of A BST=> CONSTRUCT THE BST of the tree
tree constructed successfully-do operations on tree

the iterative level order
12 6 22 4 9 16 28 1 5 7 10 14 21 26 30 31 
the recursive level order
12 6 22 4 9 16 28 1 5 7 10 14 21 26 30 31 
the iterative in order
1 4 5 6 7 9 10 12 14 16 21 22 26 28 30 31 
the recursive  order
1 4 5 6 7 9 10 12 14 16 21 22 26 28 30 31 

--------------
---THE new tree-----
the iterative in order
3 5 10 13 15 
the recursive  order
3 5 10 13 15 

the second largest node of the tree is
the second largest is13
---------------------------------

[Finished in 5.6s]

*/

/*  DELETE OPERATION
level order of the orig tree
5 2 7 3 6 12 4 

delete=5 (root)
level order => 6 2 7 3 12 4

delete=2 (no right subtree)
level order => 5 3 7 4 6 12 

delete=7 (not root but has left and right subtree)
level order => 5 2 12 3 6 4 

delete =4 (leaf node)
level order => 5 2 7 3 6 12 

*/

/*

LEVEL ORDER of tree
5 2 7 3 6 12 4 

ceil of a value
doesnt not exists
returned ceil for 16=-1

ceil of a value
returned ceil for 11=12

ceil of a value
returned ceil for 1=2

*/
