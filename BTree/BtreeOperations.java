import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Stack;
class Node
{
	int data;
	Node left;
	Node right;
	public Node(int data)
	{
		this.data=data;
	}

}
class BinaryTree
{
	private Node root;
	Queue<Node> q;
	public BinaryTree()
	{
		root=null;
		q=new ArrayDeque<Node>();
	}
	public void insert(int data)
	{	
		Node newNode=new Node(data);
		if(root==null)
		{
			root=newNode;
			q.add(root);
		}
		else
		{
			
			Node front=q.element();
			if(front.left==null)
			{
				front.left=newNode;
			}
			else
			{
				front.right=newNode;
				q.remove();
			}
			q.add(newNode);
		}

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
	public void preOrderIter(Node curr)
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
	public void displayPost()
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
	public void postOrderIter(Node curr)
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
	public void postOrderRec(Node curr)
	{
		if(curr==null)
			return;
		postOrderRec(curr.left);
		postOrderRec(curr.right);
		System.out.print(curr.data+" ");
	}
	public void displayIn()
	{
		System.out.println("the iterative in order");
		inOrderIter(root);
		System.out.println();
		System.out.println("the recursive  order");
		inOrderRec(root);
		System.out.println();

	}
	public void inOrderIter(Node curr)
	{
		ArrayDeque<Node>st=new ArrayDeque<Node>();

		if(curr==null)
			return;

		st.push(curr);
		curr=curr.left;
		//go left and push all first
		while(curr!=null)
		{
			st.push(curr);
			curr=curr.left;
		}

		while(!st.isEmpty())
		{
			Node top=st.pop();
			System.out.print(top.data+" ");
			if(top.right!=null)
			{
				Node tempCurr=top.right;
				while(tempCurr!=null)
				{
					st.push(tempCurr);
					tempCurr=tempCurr.left;
				}
			}

		}
	}

	public void inOrderRec(Node curr)
	{
		if(curr==null)
			return ;
		inOrderRec(curr.left);
		System.out.print(curr.data+" ");
		inOrderRec(curr.right);

	}

	public void displayLevel()
	{
		System.out.println("the iterative level order");
		levelOrderIter(root);
		System.out.println();
		System.out.println("the recursive level order");
		levelOrderRec(root);
	}
	// i print for each level the nodes from left to right
	public void levelOrderRec(Node curr)
	{
		for(int i=1;i<=height();i++)
			getLevelNodes(root,i);
	}
	public void getLevelNodes(Node curr,int level)
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
	public void levelOrderIter(Node root)
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
	public int height()
	{
		return getHeight(root);
	}
	//the height is w.r.t no. of edges from each node to longest leaf
	// height of tree= height(root)
	//if only one node=> height(tree)=0
	//therefore, include -1
	//BUT IN PROGRAMMING, WE CODE FOR HEIGHT OF LEAF=1
	public int getHeight(Node curr)
	{
		if(curr==null)
			return 0;

		return 1+Math.max(getHeight(curr.left),getHeight(curr.right));
	}
	public int size()
	{
		return getSize(root);
	}
	public int getSize(Node curr)
	{
		if(curr==null)
			return 0;

		return 1+(getSize(curr.left))+(getSize(curr.right));
	}
	public int max()
	{
		return getMax(root);
	}
	public int getMax(Node curr)
	{
		if(curr==null)
			return Integer.MIN_VALUE;

		return Math.max(curr.data, Math.max(getMax(curr.left),getMax(curr.right)));
	}
	
	/*
	public String max()
    {
   	 return getMax(root);

    }
    public String getMax(Node curr)
    {
    	if(curr==null)
    		return "";
    	else
    	{
    		String max1,max2;
    		if((curr.data.compareTo(max1=findMaximum(curr.left)))>0)
    		{
    			max1=curr.data;
    		}
    		if(max1.compareTo(max2=findMaximum(curr.right))>0)
    		{
    			max1=max1;
    		}
    		else
    			max1=max2;
    		
    		return max1;	
    	}
    }

	*/
	public int min()
	{
		return getMin(root);
	}
	public int getMin(Node curr)
	{
		if(curr==null)
			return Integer.MAX_VALUE;

		return Math.min(curr.data, Math.min(getMax(curr.left),getMax(curr.right)));
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

		ArrayDeque<Node>q1=new ArrayDeque<Node>();
		q1.add(curr);
		Node prevMark,currMark=curr;
		//System.out.print(1+" ");
		int mW=1;
		while(!q1.isEmpty())
		{
			Node front=q1.getFirst();
			prevMark=currMark;
			if(front.left!=null)
				q1.add(front.left);
			if(front.right!=null)
				q1.add(front.right);

			Node temp=q1.remove();
			if(temp==prevMark && !q1.isEmpty())
			{
				currMark=q1.getLast();
				//System.out.print("curr size="+q1.size()+" ");
				int sz=q1.size();
				if(sz>mW)
				  mW=sz;
			}

		}
		return mW;
	}
}

public class binaryTrees
{
	static void pl()
	{
		System.out.println();
	}
	static void pl(String s)
	{
		System.out.println(s);
	}
	public static void main(String[]args)
	{

		BinaryTree bt=new BinaryTree();
		bt.insert(1);
		bt.insert(2);
		bt.insert(3);
		bt.insert(4);
		bt.insert(5);
		bt.insert(6);
		bt.insert(7);
		bt.insert(8);
		bt.insert(9);
		bt.insert(10);
		bt.insert(11);
		bt.insert(12);
		bt.insert(13);
		bt.insert(14);
		bt.insert(15);
		bt.insert(16);
		bt.insert(17);
		//bt.insert(6);


		pl("the preOrder is");
		bt.displayPre();
		pl();
		pl("the postOrder");
		bt.displayPost();
		pl();

		pl("the inOrder");
		bt.displayIn();
		pl();

		pl("the levelOrder");
		bt.displayLevel();
		pl();
		pl();

		pl("the height is");
		int h=bt.height();
		System.out.println(h);
		pl();

		pl("the no. of nodes=size of tree is");
		int tot=bt.size();
		System.out.println(tot);
		pl();

		pl("the max element of tree is");
		int max=bt.max();
		System.out.println(max);
		pl();

		pl("the min element of tree is");
		int min=bt.min();
		System.out.println(min);
		pl();


		pl("the diameter of the tree");
		int diam=bt.diameter();
	    System.out.println(diam);
		pl();		

		pl("the maximum width of the tree");
		int wd1=bt.width1();
	    System.out.println("maximum width="+wd1);
		pl();
		pl("the maximum width of the tree");	
		int wd2=bt.width2();
		System.out.println("maximum width="+wd2);
		pl();

	}
}
/*
the preOrder is
the Iterative preOrder
1 2 4 8 16 17 9 5 10 11 3 6 12 13 7 14 15 
the Recursive preOrder
1 2 4 8 16 17 9 5 10 11 3 6 12 13 7 14 15 

the postOrder
the iterative post order
16 17 8 9 4 10 11 5 2 12 13 6 14 15 7 3 1 
the recursive post order
16 17 8 9 4 10 11 5 2 12 13 6 14 15 7 3 1 

the inOrder
the iterative in order
16 8 17 4 9 2 10 5 11 1 12 6 13 3 14 7 15 
the recursive  order
16 8 17 4 9 2 10 5 11 1 12 6 13 3 14 7 15 

the levelOrder
the iterative level order
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 
the recursive level order
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 

the height is
5

the no. of nodes=size of tree is
17

the max element of tree is
17

the min element of tree is
1

the diameter of the tree
8

the maximum width of the tree
maximum width=8

the maximum width of the tree
level:1 =Nodes=1
level:2 =Nodes=2
level:3 =Nodes=4
level:4 =Nodes=8
level:5 =Nodes=2
maximum width=8
*/
