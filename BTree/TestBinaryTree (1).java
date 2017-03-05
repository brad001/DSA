import java.util.*;
class BinaryTree
{
	class Node
	{
		Node left;
		int data;
		Node right;
		Node nextRight;

		Node(int data)
		{
			left=null;
			this.data=data;
			right=null;
			nextRight=null;
		}
		Node(){}
		public String toString()
		{
			return this.data+"";
		}
	}
	Node root=null;
	//int count=0;
	Queue<Node> tree=new LinkedList<Node>();
	
	void add(int d)
	{
		if(tree.isEmpty())
		{
			Node n=new Node(d);
			root=n;
			tree.add(n);
		}
		else if(tree.element().left==null)
		{
			Node parent=tree.element();
			Node n=new Node(d);
			parent.left=n;
			tree.add(n);
		}
		else
		{
			Node parent=tree.remove();
			Node n=new Node(d);
			parent.right=n;
			tree.add(n);
		}
	}
	void display()
	{
		System.out.println("preorder of tree is:");
		preorder(root);
		System.out.println("");
		System.out.println("preorder in a iterative way of tree is:");
		preorderIterative();
		System.out.println("");
		System.out.println("postorder of tree is:");
		postOrder(root);
		System.out.println("");
		System.out.println("postorder in a iterative way of tree is:");
		postorderIterative(root);
		System.out.println("");
		System.out.println("inorder of tree is:");
		inOrder(root);
		System.out.println("");
		System.out.println("inOrderIteration of tree is:");
		inOrderIterative();
		System.out.println("");
		System.out.println("levelOrder of tree is:");
		levelOrder();
		System.out.println("");
		System.out.println("levelOrder in a recursive way of tree is:");
		levelOrderrec();
		System.out.println("");
		
	}
	void preorder(Node n)
	{
		if(n!=null)
		{
		System.out.print(n.data+"  ");
		preorder(n.left);
		preorder(n.right);
		}
		else
		{
			
			return;
		}
	}
	void preorderIterative()
	{
		ArrayDeque<Node> st=new ArrayDeque<Node>();
		st.push(root);
		while(st.size()>0)
		{
			Node n=st.pop();
			System.out.print(n.data+"  ");
			if(n.right!=null)
				st.push(n.right);
			if(n.left!=null)
				st.push(n.left);
		}
	}
	void inOrder(Node n)
	{
		if(n!=null)
		{

			inOrder(n.left);
			System.out.print(n.data+"  ");
			inOrder(n.right);	
		}
		else
		{
			
			return;
		}
	}
	void inOrderIterative()
    {
    	if(root==null)
    		return;
    	Node n=root;
    	ArrayDeque<Node> st=new ArrayDeque<Node>();
    	while(n!=null)
    	{
    		st.push(n);
    		n=n.left;
    	}
    	while(st.size()>0)
    	{
    		Node x=st.pop();
    		System.out.print(x.data+"  ");
    		if(x.right!=null)
    		{
    			x=x.right;
    			while(x!=null)
    			{
    				st.push(x);
    				x=x.left;
    			}
    		}
    	}

    }
	void postOrder(Node n)
	{
		if(n!=null)
		{
			
			postOrder(n.left);
			postOrder(n.right);	
			System.out.print(n.data+"  ");
		}
		else
		{
			
			return;
		}
	}
	//we have three cases for solving it
	//curr=>standing at what pos
	//prev=>from where i have come

	//1. go down the tree, => prev is up and curr is down
	       //left or right(any)[marked by prev.left=curr or prev.right=curr]

	//2. coming up from left => pre
	        //prev is left down and curr is top => curr.left==prev

	//3. coming up from right => prev is right down and curr is top => curr.right==prev
	public void postorderIterative(Node curr)
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
					System.out.print(st.pop().data+"  ");
			}
			else if(curr.left==prev) //2. coming up from left=> go right or if no right print and pop
			{
				if(curr.right!=null)
					st.push(curr.right);
				else
				{
					System.out.print(st.pop().data+"  ");
				}
			}	
			else  // 3. coming up from right => all left and right are over =>only print the curr
			{
				System.out.print(st.pop().data+"  ");
			}


			//last statement
			prev=curr;
		}

	}
	void FindAncestors(int d)
	{
		System.out.print("Ancestors of node with data "+d+" is: ");
		Node curr=root;
		Node prev=curr;

		ArrayDeque<Node>st=new ArrayDeque<Node>();

		if(curr==null)
			return;
		st.push(curr);

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
				{
					Node temp=st.pop();
					if(temp.data==d)
					{
						
							System.out.println(st);
					}
				}

			}
			else if(curr.left==prev) //2. coming up from left=> go right or if no right print and pop
			{
				if(curr.right!=null)
					st.push(curr.right);
				else
				{
					Node temp=st.pop();
					if(temp.data==d)
					{
						
							System.out.println(st);
					}
		
				}
			}	
			else  // 3. coming up from right => all left and right are over =>only print the curr
			{ 
					Node temp=st.pop();
					if(temp.data==d)
					{
						
							System.out.println(st);
					}
						
			}


			//last statement
			prev=curr;
		}

	}
	
	void levelOrder()
	{
		//BFS
		Queue<Node> level=new LinkedList<Node>();
		level.add(root);
		while(!level.isEmpty())
		{
			Node temp=level.element();
			System.out.print(temp.data+"  ");
			if(temp.left!=null)
				level.add(temp.left);
			if(temp.right!=null)
				level.add(temp.right);
			level.remove();
		}
	}
	int height()
	{
		int x=FindHeight(root);
		return x;
	}
	//the height is w.r.t node. height of the node is number of edges from that node to its leaf node 
	//so if there is only one node its height is zero.
	//thats why at the and return -1;

	//or if for leaf node also if you consider height 1,then if n==null return 0.
	int FindHeight(Node n)
	{
		if(n==null)
		{
			return 0;
		}
		return(1+Math.max(FindHeight(n.left),FindHeight(n.right)));
	}
	void levelOrderrec()
	{
		System.out.println("level order:");
		for(int i=1;i<=height();i++)
		{
			levelOrderR(root,i);
		}
	}
	void levelOrderR(Node n,int i)
	{
		if(n==null)
		{
			return ;
		}
	    if(i==1)
		{
			System.out.print(n.data+"  ");
		}
		else if(i>=1)
		{
			levelOrderR(n.left,i-1);
			levelOrderR(n.right,i-1);
		}

	}
	//int count=0;
	void widthOfTree()
	{
		//int count=0;
		int max=0;
		int count=0;
		for(int i=1;i<=height();i++)
		{
			
			count=levelOrderForWidthOfTree(root,i);
			//System.out.println("i:"+i+" count="+count);
			if(max<count)
				max=count;
			count=0;
		}
		System.out.println("width of a tree: "+max);
	}
	int levelOrderForWidthOfTree(Node n,int i)
	{
		
		if(n==null)
		{
			return 0;
		}
	    if(i==1)
		{
			return 1;
		}		
		else if(i>=1)
		{
			int a=levelOrderForWidthOfTree(n.left,i-1);
			int b=levelOrderForWidthOfTree(n.right,i-1);
			//System.out.println(a+b);
			return a+b;
		}
		return 0;

	}

	void diameter()
	{
		int x=FindDiameter(root);
		System.out.println("Diameter:"+x);
	}
	int FindDiameter(Node n)
	{
		if(n==null)
			return 0;
		int lHeight=FindHeight(n.left);
		int rHeight=FindHeight(n.right);

		int lDiameter=FindDiameter(n.left);
		int rDiameter=FindDiameter(n.right);

		return Math.max((1+lHeight+rHeight),Math.max(lDiameter,rDiameter));
	}
	void size()
	{
		
		int x=findSize(root);
		System.out.println("size:"+x);
		
	}
	int findSize(Node n)
	{
		if(n!=null)
		{
			return(1+findSize(n.left)+findSize(n.right));
		
		}
		return 0;
	}
	void maximum()
	{
		int max=root.data;
		int x=findMaximum(root);
		System.out.println("maximum:"+x);


	}
	int findMaximum(Node n)
	{
		
        if(n==null)
            return Integer.MIN_VALUE;

        return Math.max(n.data, Math.max(findMaximum(n.left),findMaximum(n.right)));
    

	}
	void minimum()
	{
		int max=root.data;
		int x=findMinimum(root);
		System.out.println("minimum:"+x);


	}
	int findMinimum(Node n)
	{
		
        if(n==null)
            return Integer.MAX_VALUE;

        return Math.min(n.data, Math.min(findMinimum(n.left),findMinimum(n.right)));
    

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
    
    void printKthLevelNodes(int k)
    {
    	if(k>=1 && k<=height())
    	{
    		levelOrderR(root,k);
    	}
    	else
    		System.out.println("not Posiible");
    }

    public void connectNodesAtLevel()
	{
		getWidth(root);
		Node current=root;
		ArrayList<Node> list=new ArrayList<Node>();
		/*list.add(root);
		while(list.get(list.size()-1).left!=null ||list.get(list.size()-1).right!=null)
		{
			Node temp=list.get(list.size()-1);
			if(temp.left!=null)
				list.add(temp.left);
			else if(temp.right!=null)
				list.add(temp.right);
		}*/

		
		/*for(int i=0;i<list.size();i++)
		{
			Node first=list.get(i);
			while(first!=null)
			{
				System.out.print(first.data+"  ");
				first=first.nextRight;
			}
			System.out.println("");			
		}*/
		System.out.println("connecting nodes at each level:");
		for(int i=1;i<=height();i++)
		{
			Node first=levelOrderForConnectingNodes(root,i);
			while(first!=null)
			{
				System.out.print(first.data+"  ");
				first=first.nextRight;
			}
			System.out.println("");	
		}


	}
	Node levelOrderForConnectingNodes(Node n,int i)
	{
		if(n==null)
		{
			return null;
		}
	    if(i==1)
		{
			return n;
		}
		else if(i>=1)
		{
			if(n.left!=null)
			{
				return levelOrderForConnectingNodes(n.left,i-1);
			}
			else if(n.right!=null)
			{
				 return levelOrderForConnectingNodes(n.right,i-1);
			}
		}
		return null;

	}
	public void getWidth(Node curr)
	{
		if(curr==null)
			return;

		ArrayDeque<Node>q1=new ArrayDeque<Node>();
		q1.add(curr);
		Node prevMark,currMark=curr;
		//System.out.print(1+" ");
		//int maxNodes=1;
		while(!q1.isEmpty())
		{
			Node front=q1.getFirst();
			prevMark=currMark;
			if(front.left!=null)
				q1.add(front.left);
			if(front.right!=null)
				q1.add(front.right);

			Node temp=q1.remove();
			if(temp==prevMark) //end of lvele
			{
				if(!q1.isEmpty())
				currMark=q1.getLast();

				temp.nextRight=null;
			}
			else
			{
				temp.nextRight=q1.getFirst();
			}

		}
	}

}
class TestBinaryTree
{
	public static void main(String[] args)
	{
		BinaryTree obj=new BinaryTree();
		obj.add(1);
		obj.add(2);
		obj.add(3);
		obj.add(4);
		obj.add(5);
		obj.add(6);
		obj.add(7);
		obj.add(8);
		obj.add(9);
		obj.add(10);
		obj.add(11);
		obj.add(12);
		obj.display();
		System.out.println("hight of the tree:"+obj.height());
		obj.size();
		obj.maximum();
		obj.minimum();
		obj.diameter();
		obj.widthOfTree();
		System.out.print("kth level nodes:");
		obj.printKthLevelNodes(3);
		System.out.println("");
		obj.FindAncestors(5);
		obj.connectNodesAtLevel();

	}
}
/*
preorder of tree is:
1  2  4  8  9  5  10  11  3  6  12  7  
preorder in a iterative way of tree is:
1  2  4  8  9  5  10  11  3  6  12  7  
postorder of tree is:
8  9  4  10  11  5  2  12  6  7  3  1  
postorder in a iterative way of tree is:
8  9  4  10  11  5  2  12  6  7  3  1  
inorder of tree is:
8  4  9  2  10  5  11  1  12  6  3  7  
inOrderIteration of tree is:
8  4  9  2  10  5  11  1  12  6  3  7  
levelOrder of tree is:
1  2  3  4  5  6  7  8  9  10  11  12  
levelOrder in a recursive way of tree is:
level order:
1  2  3  4  5  6  7  8  9  10  11  12  
hight of the tree:4
size:12
maximum:12
minimum:1
Diameter:7
width of a tree: 5
kth level nodes:4  5  6  7  
[2, 1]
connecting nodes at each level:
1  
2  3  
4  5  6  7  
8  9  10  11  12 
*/