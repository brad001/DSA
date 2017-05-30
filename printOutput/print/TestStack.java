/*
The Stack class represents a last-in-first-out (LIFO) stack of objects. 
It extends class Vector with five operations that allow a vector to be treated as a stack.

A more complete and consistent set of LIFO stack operations is provided by the 
Deque interface and its implementations, which should be used in preference to this class. For example:

   Deque<Integer> stack = new ArrayDeque<Integer>();


How to understand a stack practically?
There are many real life examples of stack. 
Consider the simple example of plates stacked over one another in canteen. 
The plate which is at the top is the first one to be removed, i.e. the plate 
which has been placed at the bottommost position remains in the stack for the longest period of time. 
So, it can be simply seen to follow LIFO/FILO order.

Time Complexities of operations on stack:

push(), pop(), esEmpty() and peek() all take O(1) time. We do not run any loop in any of these operations.

Applications of stack:

   1 Balancing of symbols
   2 Infix to Postfix /Prefix conversion
   3 Redo-undo features at many places like editors, photoshop.
   4 Forward and backward feature in web browsers
   5 Used in many algorithms like Tower of Hanoi, tree traversals, stock span problem, histogram problem.
   6 Other applications can be Backtracking, Knight tour problem, rat in a maze, N queen problem and sudoku solver

*/
import java.util.Stack;
import java.util.ArrayList;
class MyStack<E>
{
	static final int capacity=10;
	int top;
	//char[] arr=new char[capacity];
	E[] arr=(E[]) new Object[capacity];
	E[] minStack=(E[]) new Object[capacity];
	int topMin;
	//constuctor to always assign top as -1.
	MyStack()
	{
		top=-1;
		topMin=-1;
	}

	//method to insert data in stack
	void push(E d)
	{
		//first increment top pointer then insert .
		top++;
		//check for stack(array) capacity first.
		if(top<capacity)
		{
			arr[top]=d;
		}
		else
		{
			pr("stack overflow!");
		}
	}

	//method to delete top element in stack.
	E pop()
	{
		if(isEmpty())
			throw new RuntimeException("stack underflow");
		E d=arr[top];
		top--;
		return d;		
	}

	void pushSp(E data)
{


//allocate the stSp[] and topSp;


//insert in origstack too
push(data);

if(topMin==-1)
{
topMin++;
minStack[topMin]=data;	
}
else//only when elem<stMin[topMin]
{
if( ((Integer)data-(Integer)minStack[topMin])<0)
{
topMin++;
minStack[topMin]=data;
}
}
	
}

	E popSp()
	{
		E d=this.pop();
		if(topMin!=-1 && minStack[topMin]==d)
			topMin--;
		return d;
	}
	/*
		to get the minimum ele of stack in O(1)
		1.we need to make a different push and pop function.
		2.PUSH
			a.push first ele to both stack(stack,minStack).
			b.push() works alike but for next incoming smaller ele than
			  the minStack[top], push it to minStack
		3.PUP
			a.pop() works alike,but if pop ele is topOf minStack,pop from minStack too.
	*/
	E getMin()
	{
		return minStack[topMin];
	}

	E peek()
	{
		if(isEmpty())
			throw new RuntimeException("stack underflow");
		return arr[top];
	}

	boolean isEmpty()
	{
		return top==-1;
	}


	void clear()
	{
		top=-1;
	}

	boolean search(E d)
	{
		for(int i=0;i<=top;i++)
		{
			if(arr[i]==d);
			return true;
		}
		return false;
	}
	//MyStack class object will be converted to the string format you want.
	public String toString() // it has to be public specified coz this method is overwride from the object class
	{
		if(isEmpty())
			return "[ ]";
		StringBuffer out = new StringBuffer("[");
        for(int i = 0; i < top; i++)
        	out.append(arr[i] + ", ");

        out.append(arr[top] + "]");
        return out.toString();
	}

	void pr(String s)
	{
		System.out.println(s);
	}
/*********** Design a stack with operations on middle element **************/

/*
	How to implement a stack which will support following operations in O(1) time complexity?
1) push() which adds an element to the top of stack.
2) pop() which removes an element from top of stack.
3) findMiddle() which will return middle element of the stack.
4) deleteMiddle() which will delete the middle element.
Push and pop are standard stack operations.

The important question is, whether to use a linked list or array for implementation of stack?

Please note that, we need to find and delete middle element. 
Deleting an element from middle is not O(1) for array. Also, 
we may need to move the middle pointer up when we push an element and move down when we pop(). 
In singly linked list, moving middle pointer in both directions is not possible.

The idea is to use Doubly Linked List (DLL). 
We can delete middle element in O(1) time by maintaining mid pointer. 
We can move mid pointer in both directions using previous and next pointers. 
*/
/*********************************************************************************/

}


/***************************END OF MYSTACK CLASS**************************/
class UseStack
{
	/******************* INFIX TO POSTFIX ******************************/

/*
	Infix expression:The expression of the form a op b. 
	When an operator is in-between every pair of operands.

	Postfix expression:The expression of the form a b op. 
	When an operator is followed for every pair of operands.

	Why postfix representation of the expression?

	Infix notation is easy to read for humans, 
	whereas pre-/postfix notation is easier to parse for a machine. 
	The big advantage in pre-/postfix notation is that 
	there never arise any questions like operator precedence

	For example, consider the infix expression 1 # 2 $ 3. 
	Now, we don't know what those operators mean, 
	so there are two possible corresponding postfix expressions: 
	1 2 # 3 $ and 1 2 3 $ #. Without knowing the rules governing the use of these operators, 
	the infix expression is essentially worthless.

	You can evaluate such an expression directly with a very simple algorithm - 
	every time you encounter a number, put it on a stack, 
	every time you encounter an operator, pop the last two elements from the stack, 
	apply the operation and push the result back.

	Postfix is slightly easier to evaluate.
	It reflects the order in which operations are performed.

	Algorithm
1. Scan the infix expression from left to right.
2. If the scanned character is an operand, output it.
3. Else,
…..3.1 If the precedence of the scanned operator is greater than 
	the precedence of the operator in the stack(or the stack is empty), push it.
…..3.2 Else, Pop the operator from the stack until the precedence of 
	the scanned operator is less-equal to the precedence of the operator 
	residing on the top of the stack. Push the scanned operator to the stack.
4. If the scanned character is an ‘(‘, push it to the stack.
5. If the scanned character is an ‘)’, pop and output from the stack until an ‘(‘ is encountered.
6. Repeat steps 2-6 until infix expression is scanned.
7. Pop and output from the stack until it is not empty.

*/
	String infixToPostfix(String infix)
	{
		//convert string to char array or use charAt for string.
		char[] input=infix.toCharArray();
		//take stringBuffer or builder to append characters.
		StringBuffer out=new StringBuffer();
		//use stack for conversion.
		MyStack st=new MyStack();
		for(int i=0;i<input.length;i++)
		{
			char current=input[i];
			//if operand then directly append
			if(isOperand(current))
				out.append(current);
			//push '(' to stack so that when ')' comes you can pop all before '('
			else if(current=='(')
				st.push(current);
			else if(current==')')
			{
				while(st.peek()!='(')
					out.append(st.pop());
				st.pop();
			}	
			else
			{
				//pop all operators in stack with high precedence than current
				//main thing is condition st.isEmpty must comes first so that no error at peek 
				//in second condition
				while(!st.isEmpty() && precedence((char)st.peek())>=precedence(current))
				{
					//manage right to left operators
					if((char)st.peek()=='^' && current=='^')
						break;
					else
						out.append((char)st.pop());
				}
				//else push current.		
				st.push(current);	
			}
		}
		//for leftover operators in stack.
		while(!st.isEmpty())
			out.append((char)st.pop());
		//you are using stringbuffer so you have to convert it to string.
		return out.toString();
	}
	//check whether current char is operand or not
	boolean isOperand(char ch)
	{
		return ((ch>='a' && ch<='z')||(ch>='A' && ch<='Z')||(ch>='0' && ch<='9'));
	}
	//function returns the precedence of the operator.
	int precedence(char ch)
	{
		switch(ch)
		{
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
				return 2;
			case '^':
				return 3;
		}
		return -1;
	}
/************* EVALUATION OF POSTFIX EXPRESSION *************************/

	/*
		Following is algorithm for evaluation postfix expressions.
1) Create a stack to store operands (or values).
2) Scan the given expression and do following for every scanned element.
…..a) If the element is a number, push it into the stack
…..b) If the element is a operator, pop operands for the operator from stack. 
Evaluate the operator and push the result back to the stack
3) When the expression is ended, the number in the stack is the final answer
	*/
	int evaluatePostfix(String infix)
	{
		//first convert infix to postfix
		String postfix=infixToPostfix(infix);
		char[] input=postfix.toCharArray();
		//you can not take your stack class coz char cant store say 10
		//MyStack st=new MyStack();
		Stack<Integer> st=new Stack<Integer>();
		for(int i=0;i<input.length;i++)
		{
			char current=input[i];
			//simply push number into stack
			if(isNumber(current))
				st.push(current-'0');
			else
			{
				int b=st.pop();
				int a=st.pop();
				switch (current)
           		{
           			case '+': st.push(a+b); break;
             		case '-': st.push(a-b); break;
             		case '*': st.push(a*b); break;
             		case '/': st.push(a/b); break;
             		case '^': st.push((int)Math.pow(a,b)); break;
            	}	
			}
		}
		return st.pop();
	}
	boolean isNumber(char ch)
	{
		return (ch>='0' && ch<='9');
	}

/******************* BALANCED PARENTHESES ************************************/


	//balancedparentheses using stack
	boolean balancedparentheses(String str)
	{
		char[] input=str.toCharArray();
		MyStack st=new MyStack();
		for(int i=0;i<input.length;i++)
		{
			char current=input[i];
			//push all open braces into stack
			if(current=='[' ||current=='{' ||current=='(')
				st.push(current);
			//for all closing braces chack for stack top if not same opening
			//then return else continue
			else if(current==']' ||current=='}' ||current==')')
			{
				switch(current)
				{
					case ']':
					{
						if(st.pop()=='[')
							continue;
						else
							return false;
					}
					case '}':
					{
						if(st.pop()=='{')
							continue;
						else
							return false;
					}
					case ')':
					{
						if(st.pop()=='(')
							continue;
						else
							return false;
					}
				}
			}
			//ignore all other characters
			else
				continue;
		}
		if(st.isEmpty())
			return true;
		else
			return false;
		//return true;
	}

/************* NEXT GREATER ELEMENT IN RHS ARRAY *****************************/

	/*
		Given an array, print the Next Greater Element (NGE) for every element. 
		The Next greater Element for an element x is the first greater element on 
		the right side of x in array. Elements for which no greater element exist, 
		consider next greater element as -1.

		1) Push the first element to stack.
		2) Pick rest of the elements one by one and follow following steps in loop.
			….a) Mark the current element as next.
			….b) If stack is not empty, then pop an element 
				from stack and compare it with next.
			….c) If next is greater than the popped element, 
				then next is the next greater element for the popped element.
			….d) Keep popping from the stack while the popped element is smaller than next. 
				next becomes the next greater element for all such popped elements
			….g) If next is smaller than the popped element, then push the popped element back.
		3) After the loop in step 2 is over, pop all the elements from stack and 
			2print -1 as next element for them.
	*/
	void nextGreater(int[] arr)
	{
		Stack<Integer> st=new Stack<Integer>();
		for(int i=0;i<arr.length;i++)
		{
			int current=arr[i];
			//stack empty? simply push
			if(st.isEmpty())
				st.push(current);
			else if(st.peek()>current)
				st.push(current);
			//already on inside stack no need for another right!
			else if(st.peek()==current)
				continue;
			else if(st.peek()<current)
			{
				//pop all elements from stack smaller then current.
				while(!st.isEmpty() && st.peek()<current)
					System.out.println("next greater for "+st.pop()+" is-->"+current);
				st.push(current);
			}
		}
		while(!st.isEmpty())
			System.out.println("next greater for "+st.pop()+" is-->"+ -1);
	}
/*********************** REVERSE STACK WITH RECURSION ********************/

	//reverse stack without using while,for loops
	//only push,pop,isEmpty allowed
	/*
		need two methods
		firstly pop element and recursively call reverse metod 
		ultimatly you are storing top ele in recursion stack
		push element at the end
		to do that call another method
		in that if stack is empty directly push
		else firstly pop all ele and push at bottom then again push 
		all ele again in stack.
	*/
	void reverseStack(MyStack st)
	{
		if(!st.isEmpty())
		{
			//pop top element
			char temp=(char)st.pop();
			//it will recursively pop until stack becomes empty.
			reverseStack(st);
			//it will push poped ele to bottom of the stack
			pushAtBottom(temp,st);
		}
		else
			return;
	}
	void pushAtBottom(char d,MyStack st)
	{
		if(!st.isEmpty())
		{
			char temp=(char)st.pop();
			pushAtBottom(d,st);
			st.push(temp);
		}
		else
			//when stack becomes empty push ele to stack
			st.push(d);
	}
/********************** SORT STACK WITH RECURSION *********************/

	//sort stack using recursion without using while,for loops
	//only ADT functions of stack are allowed.
	//same method as reverse of stack with recusion.
	void sortStack(Stack st)
	{
		if(!st.isEmpty())
		{
			//pop top element
			int temp=(int)st.pop();
			//it will recursively pop until stack becomes empty.
			sortStack(st);
			//it will push poped ele at proper position.
			pushAtProperPosition(temp,st);
		}
		else
			return;
	}
	void pushAtProperPosition(int d,Stack st)
	{
		if(!st.isEmpty() && (int)st.peek()>d)
		{
			int temp=(int)st.pop();
			pushAtProperPosition(d,st);
			st.push(temp);
		}
		else
			st.push(d);	
	}
/************************ STOCK SPAN PROBLEM **********************************/
	/*
		is a financial problem where we have a series of n daily price quotes for 
		a stock and we need to calculate span of stock’s price for all n days.
		The span Si of the stock’s price on a given day i is defined as 
		the maximum number of CONSECUTIVE days just before the given day, 
		for which the price of the stock on the current day is 
		less than or equal to its price on the given day.
		For example, if an array of 7 days prices is given as 
		{100, 80, 60, 70, 60, 75, 85}, then the span values for 
		corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
	*/
	int[] stockSpan(int[] arr)
	{
		Stack<Integer> st=new Stack<Integer>();
		int[] span=new int[arr.length];
		//you have to push first index manually
		st.push(0);
		//for first index price no lesser or equal value is there
		//except itself so s[0]=1
		span[0]=1;
		for(int i=1;i<arr.length;i++)
		{
			//we have to calculate consecutive lesser or equal days
			//soo pop all lesser or equal prices's indexes from stack
			//and find the index where price is greater.
			while(!st.isEmpty() && arr[(int)st.peek()]<=arr[i])
				st.pop();
			//if in process if stack becomes empty means all i elements
			//before it are smaller or equal so span[i]=i+1(+1 for its own);
			if(st.isEmpty())
				span[i]=i+1;
			else
				//if stack is not empty means elements smaller or eequal 
				//are between i and index at top of the stack
				//so span[i]=i-(int)st.peek();
				span[i]=i-(int)st.peek();
			st.push(i);
		}
		return span;
	}
/****************** IS ARRAY A PREORDER OF BINARY TREE ********************/

/*
	1) Create an empty stack.
	2) Initialize root as INT_MIN.
	3) Do following for every element pre[i]
    	 a) If pre[i] is smaller than current root, return false.
    	 b) Keep removing elements from stack while pre[i] is greater
        	then stack top. Make the last removed item as new root (to
        	be compared next).
        	At this point, pre[i] is greater than the removed root
	        (That is why if we see a smaller element in step a), we 
        	return false)
     	c) push pre[i] to stack (All elements in stack are in decreasing
        	order)  
*/
	boolean isArrayPreorder(int[] arr)
	{
		MyStack<Integer> st=new MyStack<Integer>();
		// Initialize current root as minimum possible
    	// value coz you dont know how min values can come
		int cameRightToIt=Integer.MIN_VALUE;
		// Traverse given array
		for(int i=0;i<arr.length;i++)
		{
			// If we find a node who is on right side
        	// and smaller than root, return false
			if(arr[i]<cameRightToIt)
				return false;
			//if  stack is empty no option is left, so push
			else if(st.isEmpty())
				st.push(arr[i]);
			else
			{
				// If pre[i] is in right subtree of stack top,
        		// Keep removing items smaller than pre[i]
        		// and make the last removed item as new
        		// root.
				while(!st.isEmpty() && st.peek()<=arr[i])
					cameRightToIt=st.pop();
				//now push arr[i] into stack
				st.push(arr[i]);
			}
		}
		return true;
	}
/********************** How to create mergable stack? ***************************/
/*
	Design a stack with following operations.

	a) push(Stack s, x): Adds an item x to stack s
	b) pop(Stack s): Removes the top item from stack s
	c) merge(Stack s1, Stack s2): Merge contents of s2 into s1.
	
	Time Complexity of all above operations should be O(1). 
	If we use array implementation of stack, then merge is not possible to do 
	in O(1) time as we have to do following steps.
a) Delete old arrays
b) Create a new array for s1 with size equal to size of old array 
for s1 plus size of s2.
c) Copy old contents of s1 and s2 to new array for s1
The above operations take O(n) time.

We can use a linked list with two pointers, one pointer to first node 
(also used as top when elements are added and removed from beginning). 
The other pointer is needed for last node so that we can quickly link the 
linked list of s2 at the end of s1. Following are all operations.
a) push(): Adds the new item at the beginning of linked list using first pointer.
b) pop(): Removes an item from beginning using first pointer.
c) merge(): Links the first pointer second stack as next of last pointer of first list.

Can we do it if we are not allowed to use extra pointer?
We can do it with circular linked list. The idea is to keep track of 
last node in linked list. The next of last node indicates top of stack.
a) push(): Adds the new item as next of last node.
b) pop(): Removes next of last node.
c) merge(): Links the top (next of last) of second list to the 
top (next of last) of first list. And makes last of second list as last of whole list.
*/

/**************************	DFS USING STACK **************************************/


	void DFS(ArrayList[] arr)
	{
		//in directed graph there might be cycles so 
		//take visited array that only allows non visited nodes to be considered.
		boolean[] visited=new boolean[arr.length];
		//need this for loop coz there might be nodes which are unreachable
		for(int i=0;i<arr.length;i++)
		{
			if(!visited[i])
				doDFS(i,arr,visited);
		}
			System.out.println("");

	}
	void doDFS(int i,ArrayList[] arr,boolean[] visited)
	{
		//use stack for dfs
		MyStack<Integer> st=new MyStack<Integer>();
		//push node from where you want to start traversal
		st.push(i);
		while(!st.isEmpty())
		{
			int s=(int)st.pop();
			if(!visited[s])
			{
				System.out.print(s+" ");
				visited[s]=true;
			}
			//push all unvisited adjecent nodes of node s into stack
			for(int a=0;a<arr[s].size();a++)
			{
				int node=(int)arr[s].get(a);
				if(!visited[node])
					st.push(node);
			}
		}
	}
/*********** MINIMUM NUMBER OF BRACKET REVERSAL ****************************/
/*
	Given an expression with only ‘}’ and ‘{‘. The expression may not be balanced. 
	Find minimum number of bracket reversals to make the expression balanced.
	Input:  exp = "}{"
Output: 2
We need to change '}' to '{' and '{' to
'}' so that the expression becomes balanced, 
the balanced expression is '{}'

Input:  exp = "{{{"
Output: Can't be made balanced using reversals

Input:  exp = "{{{{"
Output: 2 

Input:  exp = "{{{{}}"
Output: 1 

Input:  exp = "}{{}}{{{"
Output: 3

An Efficient Solution can solve this problem in O(n) time. 
The idea is to first remove all balanced part of expression. 
For example, convert “}{{}}{{{” to “}{{{” by removing highlighted part. 
If we take a closer look, we can notice that, after removing balanced part, 
we always end up with an expression of the form }}…}{{…{, 
an expression that contains 0 or more number of closing brackets 
followed by 0 or more numbers of opening brackets.

How many minimum reversals are required for an expression of the form “}}..}{{..{” ?. 
Let m be the total number of closing brackets and n be the number of opening brackets. 
We need ⌈m/2⌉ + ⌈n/2⌉ reversals. For example }}}}{{ requires 2+1 reversals.
*/
	int minimumBracketReversal(String str)
	{
		MyStack<Character> st=new MyStack<Character>();
		//if string length is odd return -1
		if(str.length()%2==1)
			return -1;
		for(int i=0;i<str.length();i++)
		{
			char current=str.charAt(i);
			if(current=='}' )
			{
				if(!st.isEmpty() && (char)st.peek()=='{')
					st.pop();
				else
					st.push(current);
			}
			else
				st.push(current);
		}
		int c1=0,c2=0;
		while(!st.isEmpty())
		{
			if((char)st.pop()=='{')
				c1++;
			else
				c2++;
		}
		return (int)(Math.ceil((double)c1/2)+Math.ceil((double)c2/2));
	}
/************* STACK USING TWO QUEUE *******************************/
/*
	Method 1 (By making push operation costly)
This method makes sure that newly entered element is always at the front of ‘q1’, 
so that pop operation just dequeues from ‘q1’. ‘q2’ is used to put every new element at front of ‘q1’.

push(s, x) // x is the element to be pushed and s is stack
  1) Enqueue x to q2
  2) One by one dequeue everything from q1 and enqueue to q2.
  3) Swap the names of q1 and q2 
// Swapping of names is done to avoid one more movement of all elements 
// from q2 to q1. 

pop(s)
  1) Dequeue an item from q1 and return it.


 Method 2 (By making pop operation costly)
In push operation, the new element is always enqueued to q1. 
In pop() operation, if q2 is empty then all the elements except the last, 
are moved to q2. Finally the last element is dequeued from q1 and returned.

push(s,  x)
  1) Enqueue x to q1 (assuming size of q1 is unlimited).

pop(s)  
  1) One by one dequeue everything except the last element from q1 and enqueue to q2.
  2) Dequeue the last item of q1, the dequeued item is result, store it.
  3) Swap the names of q1 and q2
  4) Return the item stored in step 2.
// Swapping of names is done to avoid one more movement of all elements 
// from q2 to q1.


*/

/**********IMPLEMENT STACK USING SINGLE QUEUE *********************/

/*
	// x is the element to be pushed and s is stack
push(s, x) 
  1) Let size of q be s. 
  1) Enqueue x to q
  2) One by one Dequeue s items from queue and enqueue them.
  
// Removes an item from stack
pop(s)
  1) Dequeue an item from q

*/
/************************ CELEBRITY PROBLEM *********************************/
/*
	n a party of N people, only one person is known to everyone. 
	Such a person may be present in the party, if yes, (s)he doesn’t 
	know anyone in the party. We can only ask questions like “does A know B? “. 
	Find the stranger (celebrity) in minimum number of questions.

We can describe the problem input as an array of numbers/characters 
representing persons in the party. We also have a hypothetical 
function knows(A, B) which returns true if A knows B, false otherwise. 
How can we solve the problem.

We have following observation based on elimination technique 
(Refer Polya’s How to Solve It book).

    If A knows B, then A can’t be celebrity. Discard A, and B may be celebrity.
    If A doesn’t know B, then B can’t be celebrity. Discard B, and A may be celebrity.
    Repeat above two steps till we left with only one person.
    Ensure the remained person is celebrity. (Why do we need this step?)

We can use stack to verity celebrity.

    Push all the celebrities into a stack.
    Pop off top two persons from the stack, discard one person based on 
    	return status of HaveAcquaintance(A, B).
    Push the remained person onto stack.
    Repeat step 2 and 3 until only one person remains in the stack.
    Check the remained person in stack doesn’t have acquaintance with anyone else.

1. Graph (O(n^2))-> to find a node which has N-1 inDegree and 0
outDegree
*/
	boolean clebrityProblem(int n)
	{
		MyStack<Integer> st=new MyStack<Integer>();
		//push all elements to stack
		for(int i=0;i<n;i++)
			st.push(i);
		int count =n;
		//pop two elements and store them on A and B
		//pushing only one so at the end only one will be left
		while(count>1)
		{
			int a=(int)st.pop();
			int b=(int)st.pop();
			if(knows(a,b))
				st.push(b);
			else
				st.push(a);
			count--;
		}
		int celebrity=0;
		// at last execution make one more pop() this might be the celebrity
		if(count==1)
			celebrity=(int)st.pop();

		/*
		finally , celebrity will store the candidate(correct or incorrect)
                                                Correct ,  only when he doesnt know anyone or nobody knows him
                                                Incorrect, because as of now it has only been compared with A
		and B, whereas A and B are comapred with all other
        therefore one more Scan,*/
		for(int j=0;j<n;j++)
		{
			if(celebrity!=j && (knows(celebrity,j)||!knows(j,celebrity)))
				return false;
		}
		return true;
	}
	boolean knows(int a,int b)
	{
		int[][] arr={{0, 0, 1, 1},
                      {0, 0, 1, 0},
                      {0, 0, 0, 0},
                      {0, 0, 0, 0}};
        if(arr[a][b]==1)
        	return true;
        else
        	return false;
	}

/***********************************************************************/

	/*
FIND LENGTH OF LONGEST SUBSTRING VALID PARENTHESIS

1. in a number line 0 1 2 3 .... if i stand at 3 and i started at 1 => i have travelled 3-0=3 dist
2. so base is always one previous to where i start journey i.e '(' => start of valid string may be
   base for each ) is : previous to top[because top is start of (]=> we first pop and then check top
3. for each ( :
I push the index i.e I did not find closing
4. for each ) :
   ///********{
    I expect a index of corresponding ( being pushed earlier
    So I pop() first , and then calculate the currentIndex-top (length of valid string)

    Why -1 is pushed first:
        ()()()...for a strong like this I never update my base because, each ) will add to length
        so I finally get currentIndex=5 and I pop 4: and then do i-top => 5-(-1)=>6

    ))()()... : I expect a index of corresponding ) being pushed earlier
    So I pop()first , but I cant calulate currentIndex-top as stack is empty
    I had -1 as first elment : and I pop() it: so Set my base as currentIndex, similarly for next symbol
    I end up setting my base as 1 : bcoz 2 has a valid start....therefore 2 is pushed
    .......
   } ////*******
   4.1:   pop()
   4.2:   if stack is empty => push the currentIndex as base
   4.3:   if not => i have a corresponding ( index:=> ValidLength=currentIndex-top

 
*/
int findLongestValidSubstring(String s)
{
pr("the string is:");
for(char e:s.toCharArray())
System.out.print(e+" ");
pr("");



//stack of indexes
Stack<Integer>st=new Stack<Integer>();
int len=0;
int maxLen=0;
//base is -1 
st.push(-1);

int i=0;
while(i<s.length())
{
char incoming=s.charAt(i);

//for each ( push the index
if(incoming=='(')
{
st.push(i);
}
else
{
//to find the length of continous we keep track of base(prev to start of valid string)
//so we pop() to go to the base
//calculation: 1. i-top() [top is the base for current ')']
//             2. note if stack is empty we simple push the index

st.pop();
if(st.isEmpty())
{
//set base
st.push(i);
}
else
{

len=i-(int)st.peek();
if(len>maxLen)
{

pr("found currMax between "+((int)st.peek())+" & "+i+" of length="+len);
maxLen=len;
}
}

}
i++;
}//end while

return maxLen;

}
}

class ArrayStack
{
	int[] arr;
	int[] top;
	int[] next;
	int free;

	ArrayStack(int n,int k)
	{
		arr=new int[n];
		top=new int[k];
		for(int i=0;i<k;i++)
			top[i]=-1;
		next=new int[n];
		for(int i=0;i<n-1;i++)
			next[i]=i++;
		next[n-1]=-1;
		free=0;
	}

	boolean isFull()
	{
		if(free==-1)
			return true;
		else 
			return false;
	}

	boolean isEmpty(int stackNo)
	{
		if(top[stackNo]==-1)
			return true;
		else 
			return false;
	}

	void add(int d,int stackNo)
	{
		if(isFull())
			throw new RuntimeException("stack overflow");

		int i=free;
		free=next[i];

		next[i]=top[stackNo];
		top[stackNo]=i;

		arr[i]=d;
	}

	int remove(int stackNo)
	{
		int i=top[stackNo];
		top[stackNo]=next[i];

		next[i]=free;
		free=i;

		return arr[i];
	}

	void printStack(int stackNo)
	{
		if(top[stackNo]==-1)
			return;
		int i=top[stackNo];
		while(i!=-1)
		{
			System.out.print(arr[i]);
			i=next[i];
		}
		System.out.println("");
	}
}

class TestMyStack
{
	public static void main(String[] args)
	{
		MyStack<Character> st1=new MyStack<Character>();
		st1.push('1');
		st1.push('2');
		st1.push('3');
		st1.push('4');
		st1.push('5');
		st1.push('6');
		System.out.println(st1);
		//pr(st1+"");
		UseStack obj=new UseStack();
		//String postfix=obj.infixToPostfix("a-b^c+d^e^f*g/h");
		String postfix=obj.infixToPostfix("a^b^c");
		pr(postfix);
		int evaluated=obj.evaluatePostfix("1+3*(2^3-4)^(0+1*3)-5");
		System.out.println("evaluated number is:"+evaluated);
		boolean result=obj.balancedparentheses("[12(00)]{998}{[()()]()}");
		if(result)
			pr("yes");
		else
			pr("no");
		int[] arr={13,7,6,12};
		obj.nextGreater(arr);
		obj.reverseStack(st1);
		System.out.println("reverse:"+st1);
		Stack<Integer> st=new Stack<Integer>();
		st.push(30);
		st.push(-5);
		st.push(18);
		st.push(14);
		st.push(-3);
		obj.sortStack(st);
		System.out.println("sorted stack:"+st);
		int[] arr1={100,80,60,70,60,75,85};
		int[] span=obj.stockSpan(arr1);
		for(int x=0;x<span.length;x++)
		System.out.print(span[x]+" ");
		System.out.println("");

		MyStack<Integer> special=new MyStack<Integer>();
		special.pushSp(18);
		special.pushSp(19);
		special.pushSp(29);
		special.pushSp(15);
		special.pushSp(16);
		System.out.println("minimum element in stack is:"+special.getMin()); 
		special.popSp();
		special.popSp();
		System.out.println("minimum element in stack is:"+special.getMin());
		int[] arr2={40,30,35,20,80,70,100};
		boolean res=obj.isArrayPreorder(arr2);
		if(res)
			System.out.println("yes");
		else
			System.out.println("no");

		ArrayList<Integer>[] arr3=new ArrayList[5];
		for(int y=0;y<arr3.length;y++)
			arr3[y]=new ArrayList<Integer>();
		//arr3[0].add(1);
		arr3[0].add(2);
		arr3[0].add(4);
		arr3[2].add(3);
		//arr3[2].add(1);
		arr3[3].add(0);
		System.out.println("dfs of given graph using stack");
		obj.DFS(arr3);
		String strr="}}}}}{{{";
		int result1=obj.minimumBracketReversal(strr);
		System.out.println("minimum bracket reversal needed is:"+result1);
		if(obj.clebrityProblem(4))
			System.out.println("celebrity is present");
		else
			System.out.println("celebrity is not present");
		System.out.println("longestValidSubstring is:"+obj.longestValidSubstring("))()()(("));
	}
	static void pr(String s)
	{
		System.out.println(s);
	}

}
