/* 
java implementaion :
Deque<String> st=new ArrayDeque<String>();
Deque<String>  st= new LinkedList<String>();
*/

/*
push(), pop(), isEmpty() , peek() => O(1)

Applications:
1. Balancing of symbols
2. Infix to Postfix /Prefix conversion
3. Redo-undo features at many places like editors, photoshop.
4. Forward and backward feature in web browsers
5. Used in many algorithms like Tower of Hanoi, tree traversals, stock span problem, histogram problem.
6. Backtracking, Knight tour problem, rat in a maze, N queen problem and sudoku solver
*/

//by default lang gets imported
//import java.lang.RuntimeException;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;



class Stack<T>
{
	private final int MAX=100;
	private T st[];
	private int top;
	//for implementing GetMin in O(1)
	private T stMin[];
	private int topMin=-1;

	public Stack()
	{	
		st=(T[])new Object[MAX];
		top=-1;
	}

	boolean overflow()
	{
		if(top==MAX-1)
			return true;
		else
			return false;

	}

	void push(T data)
	{
		if(overflow())
		{	
			pr("overflow occurred: cant push "+data);
			return;
		}
		else
		{
			top++;
			st[top]=data;
		}
	}
	//this function if implemented for keeping track of min element
	// we use another MinStack which store all the possible minimums of array
	//we can get Min in O(1)
	void pushSp(T data)
	{
		if(overflow())
		{	
			pr("overflow occurred: cant push "+data);
			return;
		}
		else
		{
			//allocate the stSp[] and topSp;
			if(topMin==-1)
			{
				stMin=(T[])new Object[MAX];
				topMin=-1;	

			}
			

			//insert in origstack too
			push(data);

			if(topMin==-1)
			{
				topMin++;
				stMin[topMin]=data;	
			}
			else//only when elem<stMin[topMin]
			{
				if( ((int)(Integer)data-(int)(Integer)stMin[topMin])<0)
				{
					topMin++;
					stMin[topMin]=data;
				}
			}
			
		}	
	}
	T popSp()
	{
		if(topMin==-1)
		{
			throw new RuntimeException("no elements in MinStack");
		}
		else
		{
			T data=pop();
			if(data==stMin[topMin])
			{
				topMin--;
			}
			return data;
		}
	}
	/*
	to get the minimum off a stack in O(1)
	1. we need to make a different Push() and Pop() function
	2. push 1st element to both Stack (stack, minStack)
	3. push() works alike but for next incoming smaller element than the minStack[top], push it to MinStack
	4. pop() works alike, but if popped element is same as topOfMinStack,pop() on MinStack
	*/

	T getMinSp()
	{
		if(topMin==-1)
		{
			throw new RuntimeException("emoty Min Stack");
		}
		else
		{
			return stMin[topMin];
		}
	}

	//underflow==isEmpty
	boolean isEmpty()
	{
		if(top==-1)
			return true;
		else
			return false;

	}

	T pop()
	{
		if(isEmpty())
		{
			pr("underflow occured: cant pop");
			//throw an exception instead
			throw new RuntimeException("stack empty");
			//return Integer.MIN_VALUE;
		}
		else
		{
			T elem=st[top];
			top--;
			return elem;
		}
	}


	

	T peek()
	{
		if(isEmpty())
		{
			pr("underflow occurred: no elements");
			throw new RuntimeException("stack empty");
			//return Integer.MIN_VALUE;
		}
		else
		{
			return st[top];
		}
	}

	int size()
	{
		if(isEmpty())
		{
			throw new RuntimeException("empty stack");
		}
		else
		{
			int sz=1;
			while(sz<=top)
				sz++;

			return sz;
		}
	}
	boolean search(T data)
	{
		if(isEmpty())
			return false;
		else
		{
			int i=0;
			while(i<=top)
			{
				if(st[i]==data)
					return true;
				i++;
			}
			return false;
		}
	}
	void display()
	{
		if(isEmpty())
		{
			throw new RuntimeException("empty stack");
		}
		else
		{
			//top holds the current location
			int i=0;
			while(i<=top)
			{
				System.out.print(st[i]+" ");
				i++;
			}
			pr("");
		}
	}
	void pr(String s)
	{
		System.out.println(s);
	}

	
}



class FunctionsUsingStack
{
	void pr(String s)
	{
		System.out.println(s);
	}
	//infix to postfix
	//infix is human readable , while postfix has advantages
	//1. easily parsable in one-go by the computer
	//2. no overhead of parenthesis
	//3. it reflects which operation is performed when
	//4. evaluate a postfix expression,single scan from L->R using Stack
	//5. no precedence rules

	//returns -1 => none
	//pass a operator and it will return the precendence value
	int getPrecedence(char in)
	{
		switch (in)
		{
			case '^':
			{
				return 3;
			}
			case '/':
			case '*':
			{
				return 2;
			}
			case '+':
			case '-':
			{
				return 1;
			}
		}

		return -1;
	}

	//distinguish between operand and operator
	boolean isOperand(char in)
	{
		if((in>='a'&& in<='z') || (in>='A' && in<='Z') || 
			(in>='0' && in<='9'))
			return true;
		else
			return false;
	}

	/*
	1. scan the infix
	2. if operands -> append to postfixString
	3. if operator -> emptyStack -> push
	3. if operators -> precdence[stack] >= precedence[incoming] => pop until condition breaks and then push
	4. else operator -> precedence[stack] < precedence[incoming] => push
	5. if '(' -> push
	6. if ')' -> pop until '('
	*/
	String infixToPostfix(String infix)
	{
		String postfix="";
		int i=0;
		Stack opStk=new Stack();

		while(i<infix.length())
		{
			char incoming=infix.charAt(i);

			//if operand
			if(isOperand(infix.charAt(i)))
			{	
				postfix=postfix+String.valueOf(incoming);
			}
			//operator
			else
			{

				if(opStk.isEmpty())
				{
					opStk.push(incoming);
				}
				//these needs to be defined before any other operator
				else if(incoming=='(')
				{
					opStk.push(incoming);
				}
				else if(incoming==')')
				{
					//pop all opertors and append to postFix
					// BUT (
					while((char)opStk.peek()!='(')
					{
						char ch=(char)opStk.pop();
						postfix=postfix+String.valueOf(ch);
					}
					//simply pop the ( operator
					opStk.pop();
				}
				else
				{
					//operator incoming compared to peek
					//but check first empty also , bcoz you might pop all and still check precedence

					//pop all greater and equal operators
					while(!opStk.isEmpty() && getPrecedence((char)opStk.peek())>=getPrecedence(incoming))
					{
						//NOTE: 2^3^4 => first 3^4 evaluates and then 2^res
						//special handle for '^' dont pop and append
						//but skip and push '^' back to back for subsequent '^'
						if(((char)opStk.peek()=='^' && incoming=='^'))
						{
						  break;
						}
						else
						{
						  char ch=(char)opStk.pop();
						  postfix=postfix+String.valueOf(ch);
						}
					}
					//finally push the incoming
					opStk.push(incoming);
				}
			}
			
			i++;
		}//while infix scaned

		//we might have other operators in the stack now
		while(!opStk.isEmpty())
		{
			char ch=(char)opStk.pop();
			postfix=postfix+String.valueOf(ch);
		}

		System.out.println("scanned and converted");
		System.out.println(postfix);
		return postfix;
		
	}



	/*
	evaluate postfix:
	1. Scan L-> R
	2. push operands (unlike previous , where operators pushed)
	3. for each operator => pop two operands evaluate [secondTop operator top]
	4. push res back to stack
	5. when sacnned finish, result=> one value in stack
	*/

	int calRes(int a,int b,char c)
	{
		switch(c)
		{
			case '^':
				return (int)Math.pow(a,b);
			case '*':
				return a*b;
			case '/':
				return a/b;
			case '+':
				return a+b;
			case '-':
				return a-b;
			case '%':
			    return a%b;
		}
		return -1;
	}
	int evaluatePostfix(String postfix)
	{
		//Stack opdStk=new Stack();
		Deque<Integer>opdStk=new ArrayDeque<Integer>();
		int i=0;
		while(i<postfix.length())
		{
			char ch=postfix.charAt(i);

			if(isOperand(ch))
			{
				int in=ch-'0';
				opdStk.push(in);
			}
			else
			{
				//operator
				int b=opdStk.pop();
				int a=opdStk.pop();

				int res=calRes(a,b,ch);
				//pr("a="+a+" b="+b+" res="+res);
				opdStk.push(res);
			}
			i++;
		}

		//
		pr("exp evaluated");
		int result=opdStk.pop();
		pr(result+"");
		return result;
	}


	/*
	infix-prefix
	*/

	String infixToPrefix(String infixT)
	{
		StringBuilder infix=new StringBuilder(infixT);
		infix.reverse();
		pr(""+infixT+" \n"+infix);



		StringBuilder prefix=new StringBuilder("");
		int i=0;
		Stack opStk=new Stack();

		while(i<infix.length())
		{
			char incoming=infix.charAt(i);

			//if operand
			if(isOperand(infix.charAt(i)))
			{	
				prefix=prefix.append(incoming);
			}
			//operator
			else
			{

				if(opStk.isEmpty())
				{
					opStk.push(incoming);
				}
				//these needs to be defined before any other operator
				else if(incoming==')')
				{
					opStk.push(incoming);
				}
				else if(incoming=='(')
				{
					//pop all opertors and append to postFix
					// BUT (
					while((char)opStk.peek()!=')')
					{
						char ch=(char)opStk.pop();
						prefix=prefix.append(incoming);
					}
					//simply pop the ( operator
					opStk.pop();
				}
				else
				{
					//operator incoming compared to peek
					//but check first empty also , bcoz you might pop all and still check precedence

					//pop all greater and equal operators
					while(!opStk.isEmpty() && getPrecedence((char)opStk.peek())>getPrecedence(incoming))
					{
						//NOTE: 2^3^4 => first 3^4 evaluates and then 2^res
						//special handle for '^' dont pop and append
						//but skip and push '^' back to back for subsequent '^'
						if(((char)opStk.peek()=='^' && incoming=='^'))
						{
						  break;
						}
						else
						{
						  char ch=(char)opStk.pop();
						  prefix=prefix.append(incoming);
						}
					}
					//finally push the incoming (for peek()<=incoming)
					opStk.push(incoming);
				}
			}
			
			i++;
		}//while infix scaned

		//we might have other operators in the stack now
		while(!opStk.isEmpty())
		{
			char ch=(char)opStk.pop();
			prefix=prefix.append(ch);
		}

		System.out.println("scanned and converted");
		System.out.println(prefix);
		pr("reversing the final prefix again");
		prefix.reverse();
		System.out.println(prefix);
		return prefix.toString();

	}

	int evaluatePrefix(String prefix)
	{
		//Stack opdStk=new Stack();
		Deque<Integer>opdStk=new ArrayDeque<Integer>();
		int i=0;
		while(i<prefix.length())
		{
			char ch=postfix.charAt(i);

			if(isOperand(ch))
			{
				int in=ch-'0';
				opdStk.push(in);
			}
			else
			{
				//operator
				int b=opdStk.pop();
				int a=opdStk.pop();

				int res=calRes(a,b,ch);
				//pr("a="+a+" b="+b+" res="+res);
				opdStk.push(res);
			}
			i++;
		}

		//
		pr("exp evaluated");
		int result=opdStk.pop();
		pr(result+"");
		return result;
	}

	boolean validParenthesis(String s)
	{
		Stack st=new Stack();
		int i=0;
		while(i<s.length())
		{
			char ch=s.charAt(i);

			if(ch=='(' || ch=='{' || ch=='[')
				st.push(ch);
			else
			{
				if(ch==')')
				{
					if((char)st.peek()=='(')
						st.pop();
					else
						return false;
				}
				else if(ch=='}')
				{
					if((char)st.peek()=='{')
						st.pop();
					else
						return false;
				}
				else 
				{
					if((char)st.peek()=='[')
						st.pop();
					else
						return false;
				}
			}
			i++;
		}

		if(!st.isEmpty())
		{
			pr("reached non-empty");
			return false;
		}
		else
			return true;
	}

 	/*
 	1. push first element
 	2. for each next element: first < next => print pair
 	3. if first has found its pair=> pop() and push next
 	4. now, if first > next => first has not found its pair=> dont pop()  but push next too
 	5. step3.modify => stack has[12,4,1] next is 6 => 1 has found its pair, also 4 has found but not 12
 	6. thereofer while(peek()<next) pop pair with next until condition fails
 	7. last push the next into stack
 	*/
	void findNextGreater(int []arr)
	{
		//Stack st=new Stack();
		Deque<Integer>st=new ArrayDeque<Integer>();
		int i=1;
		//push first
		st.push(arr[0]);
		while(i<arr.length)
		{
			//we might come across empty stack , so just push
			while(!st.isEmpty() && st.peek()<arr[i])
			{
				System.out.println("pair:"+st.peek()+" & "+arr[i]);
				st.pop();
			}
			st.push(arr[i]);
			i++;
		}
	}

	//reverse using stack
	/*
	1. emptyStack
	2. insertBottom then(recursively by pooping and pushing elem passed)
	*/
	void reverse(Deque st)
	{
		if(!st.isEmpty())
		{
			//empty stack first, by recursively
			//calling reverse()

			int elem=(int)st.pop();
			reverse(st);
			//when it reaches emoty stack, starts esecuting this for each call
			insertBottom(st,elem);
		}
		
	}
	//this will insert elem at the bottom 
	//1. empties the stack recursively
	//2. then pushes the elem first when stack is empty, and then pushes each ekem popped
	void insertBottom(Deque st,int elem)
	{
		if(st.isEmpty())
		{
			st.push(elem);
		}
		else
		{
			int popElem=(int)st.pop();
			insertBottom(st,elem);
			//once stack is empty and pushes elem to it then execute this for each call
			st.push(popElem);
		}
	}

	/*
	stack after sort bottom -top [5,4,3,2,1(top)]

	1. sort works on similar lines as above
	2. just change in insertBottom => if(peek()<elem] => simply push here
	3. otherwise pop and call recursively until condition2 and then push back all popped
	*/
	void insertBottomSort(Deque st,int elem)
	{
		if(st.isEmpty())
		{
			st.push(elem);
		}
		//equal makes stable sort
		else if(((int)st.peek())<=elem)
		{
			st.push(elem);
		}
		else
		{
			int popElem=(int)st.pop();
			insertBottomSort(st,elem);
			//once stack is empty and pushes elem to it then execute this for each call
			st.push(popElem);
		}
	}
	/*
	Input      top-6 3 9 1 5 4-bottom
	Output sort => 9 6 5 4 3 1
	*/
	void sort(Deque st)
	{
		if(!st.isEmpty())
		{
			//empty stack first, by recursively
			//calling reverse()

			int elem=(int)st.pop();
			sort(st);
			//when it reaches emoty stack, starts esecuting this for each call
			insertBottomSort(st,elem);
		}

	}

	/*
	stockSpan problem=> how many consecutive days before current day, where price<prevDay><=price<currDay>
	      logic: for how many days in a row, this value is max
	1. use a index stack
	2. push first index
	3. for each next index, check(price<top><=price<curr>)
	4. if yes => pop that index and continue to compare and pop, until match false
	5. if(stack empty in the process=>) this is the max stock price=> its index+1 days are there
	6. if not empty=> and the condition breaks=> currPrice.index - top index are the number of days where the price is largest
	7. push each next elements index, so that next to this element can compare to this

	*/
	void findStockSpan(int []stockPrice)
	{
		//stack to store the index of each element
		Deque<Integer>stack=new ArrayDeque<Integer>();
		int []span=new int[stockPrice.length];
		span[0]=1;
		//first push the start index
		stack.push(0);
		int i=1;
		while(i<stockPrice.length)
		{
			//pop all index whose price is smaller than current
			while(!stack.isEmpty() && stockPrice[(int)stack.peek()]<=stockPrice[i])
			{
				stack.pop();
			}

			//if stack is empty=> the current price is max=> number of days=> i+1
			if(stack.isEmpty())
			{
				span[i]=i+1;
			}
			//if not=> break at the location where top index of stack has price greater
			//=> in between that topIndex and current index=> number of days
			//also , i is always greater than top
			else
			{
				span[i]=i-(int)stack.peek();
			}


			stack.push(i);
			i++;
		}

		pr("the span of each element is :");
		for(int sp:span)
			System.out.print(sp+" ");
		pr("");
	}

	//valid pre-order
	/*
	1. set root based on when incoming value if (arr[i] >top)
	2. but root will be the last popped when condition fails or empty
	3. 

	*/
	boolean validPreorder(int[]preOrder)
	{
		Stack<Integer>st=new Stack<Integer>();
		int root=Integer.MIN_VALUE;
		int i=0;
		while(i<preOrder.length)
		{
			//iniitially all elements fails this
			if(preOrder[i]<root)
				return false;

			//if new coming element is > st.top => we need to set root 
			//but until we find an element such that pek()>element
			//set that as root
			//next time any preOrder[i]<root => return false
			while(!st.isEmpty() && preOrder[i]>st.peek())
			{
				root=st.pop();
			}

			st.push(preOrder[i]);
			i++;
		}
		return true;
	}

	/**************************************************************
	HOW TO CREATE MERGEBALE STACK
     merge(Stack s1, Stack s2): Merge contents of s2 into s1. O(1)

     1.If we use array implementation of stack, then merge is not possible to do in O(1) time
     2.Arrays: a) Delete old arrays
       b) Create a new array for s1 with size equal to size of old array for s1 plus size of s2.
       c) Copy old contents of s1 and s2 to new array for s1
     3. Single Linked List : We can use a linked list with two pointers
     4. Circular LiknkedLIst :We can do it with circular linked list. The idea is to keep track of last node in linked list.
	*/


     /*************************************************************
     STACK USING TWO QUEUS:
    push(s,  x)
  	1) Enqueue x to q1 (assuming size of q1 is unlimited).

	pop(s)  
  	1) One by one dequeue everything except the last element from q1 and enqueue to q2.
  	2) Dequeue the last item of q1, the dequeued item is result, store it.
  	3) Swap the names of q1 and q2
  	4) Return the item stored in step 2.
		// Swapping of names is done to avoid one more movement of all elements 
		// from q2 to q1.

	 STACK USING ONE QUEUE:
	push(s, x) 
  	1) Let size of q be s. 
  	1) Enqueue x to q
  	2) One by one Dequeue SIZE()-1 items from queue and enqueue them.
  
	// Removes an item from stack
	pop(s)
  	1) Dequeue an item from q


     */



     /*
     1. push { 
     2. push } only when top!={ otherwise, pop {
     3. finally the stack will contain }}}}}....{{{ if invalid combination
     4. so min to reverse => half of first half and half of seconf half and make them {}{}{}{}...{}{}{}
     */
	int countMinNumBracketRev(String s)
	{
		//if odd length return -1
		if(s.length()%2==1)
			return -1;
		Stack<Character>st=new Stack<Character>();

		int i=0;
		while(i<s.length())
		{
			char incoming=s.charAt(i);

			if(st.isEmpty())
			{
				st.push(incoming);
			}
			else
			{
				if(incoming=='}' && st.peek()=='{')
				{
					st.pop();
				}
				else
					st.push(incoming);
			}
			i++;
		}

		//once all matched are popped out
		//stack  reamins with invalid combination }}}}}{{{{ & top with '{{''
		//or                                      }}}}}}} or {{{{{
		int cntOpen=0;
		int stSize=st.size();

		//stack pop until you hit empty or found }
		while(!st.isEmpty() && st.peek()=='{')
		{
          st.pop();
          cntOpen++;
		}
		int cntClose=stSize-cntOpen;

		pr("close:"+cntClose);
		pr("open:"+cntOpen);

		//we need to change only half of them
		//but each may be odd odd or even even or odd even or even odd
		//so take upper bound
		pr(""+Math.floor((double)cntOpen/2));
		int tot=(int)(Math.ceil((double)cntClose/2)+Math.ceil((double)cntOpen/2));
		return tot;
	}

	/*
	CELEBRITY PROBLEM
	      in a party, how do you identify a celebrity : given you can ask one one thing: doesAknowsB() -> which return True or false

	1. Graph (O(n^2))-> to find a node which has N-1 inDegree and 0 outDegree	      
	2. Stack ->
			1. push all elements to stack
			2. pop two elements and store them on A and B
			3. while(Stack size>=2)
					 knows(A,B)
					   True:
					   		A cant be a celebrity 
					   		So, A=pop() one different person
					   False:
					   		B cant be a celebrity
					   		Ao, B=pop() one different person
			4. step 3 at last execution make one more pop()					   		
			5. now i have One person in stack and one other in A or B 
			6. So , C=pop() and  comapre knows(C,B) and knows(C,A)
			7. finally , C will store the candidate(correct or incorrect)
						Correct ,  only when he doesnt know anyone or nobody knows him
						Incorrect, because as of now it has only been compared with A and B, whereas A and B are comapred with all other
			8. therefore one more Scan,
						for(int i=0;i<n;i++)						
						    if(i!C && (knows(i,C) || !knows(C,i)) 
						    	 return false => C is not => no Celebrity at party

					return True => C is a celebrity 
			

	*/

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
class Node
{
	int data;
	public Node(int data)
	{
		this.data=data;
	}
}
class Graph
{
	private LinkedList[]li;
	private int V;
	public Graph(int v)
	{
		V=v;
		li=new LinkedList[V];
		//each li[i] is a link list of nodes, so initialise them
		for(int i=0;i<V;i++)
		{
			li[i]=new LinkedList();
		}
	}
	public void addEdge(int src,int dest)
	{
		
		li[src].add(new Node(dest));
	}
	void dfs()
	{
		boolean []visited=new boolean[V];
		//visited[0]=true;
		for(int i=0;i<li.length;i++)
		{
			if(!visited[i])
				dfs(i,visited);
		}
	}
	public void dfs(int i,boolean[]visited)
	{ 
	  Stack<Integer> st=new Stack<Integer>();
	  //visited[i]=true;
	  st.push(i);
	  while(!st.isEmpty())
	  {
		  //int top=(int)st.peek();
		  //System.out.println("visit="+top);
		  int s=(int)st.pop();
		  if(!visited[s])
		  {
		  	System.out.println(s+" ");
		  	visited[s]=true;
		  }
		  
		  Iterator itr=li[s].listIterator();
		  while(itr.hasNext())
		  {
		  	Node temp=(Node)itr.next();
			  //if not visited
			  if(!visited[temp.data])
			  {
				  st.push(temp.data);
			  }

		  }//until list empty=> adjacent
	  }
	  
	} //dfs end


}
public class StackGeeks
{
	static void pr(String s)
	{
		System.out.println(s);
	}
	public static void main(String[]args)
	{
		Stack<Character> st1=new Stack<Character>();
		st1.push((char)(1+'0'));st1.push((char)(2+'0'));st1.push((char)(3+'0'));st1.push((char)(4+'0'));
		st1.push((char)(5+'0'));st1.push((char)(6+'0'));st1.push((char)(7+'0'));st1.push((char)(8+'0'));
		st1.push((char)(9+'0'));st1.push((char)(10+'0'));st1.push((char)(11+'0'));
		st1.display();
		pr("pop one");
		char temp=(char)st1.pop();
		pr("popped= "+temp);
		pr("now stack:");
		st1.display();
		pr("size of stack is:"+st1.size());
		int i=st1.size();
		st1.display();
		pr("----------------------------");
		pr("working for infix to postfix".toUpperCase());

		FunctionsUsingStack obj=new FunctionsUsingStack();
		String infix="a+b*(c^d^p-e)^(f+g*h)-i+(j^k^l)";
		pr("the infix is: "+infix);
		String postfix=obj.infixToPostfix(infix);
		pr("the postfix is :"+postfix);
		pr("---------------------------");
		pr("postfix evaluation".toUpperCase());
		FunctionsUsingStack obj1=new FunctionsUsingStack();
		String infix1="1+3*(2^3-4)^(0+1*3)-5";
		pr("the infix is: "+infix1);
		String postfix1=obj1.infixToPostfix(infix1);
		pr("evaluation of postfix");
		int result=obj1.evaluatePostfix(postfix1);
		pr("final value of postfix="+result);

		pr("------------------------------------");
		pr("working for infix to prefix".toUpperCase());
		FunctionsUsingStack obj3=new FunctionsUsingStack();
		String infix3="1+3*(2^3-4)^(0+1*3)-5";
		obj3.infixToPrefix(infix3);
		pr("------------------------------------");
		pr("check parethesis".toUpperCase());
		String sparen="[]{(([]))}[]({(){}[()]})(";
		FunctionsUsingStack obj4=new FunctionsUsingStack();
		pr("expr:"+sparen);
		boolean valid=obj4.validParenthesis(sparen);
		if(valid)
			pr("valid parenthesis");
		else
			pr("invalid");
		pr("------------------------------");
		pr("FOR EACH ELEMENT- FIND NEXT GREATER ELEMENT");
		FunctionsUsingStack obj5=new FunctionsUsingStack();
		int input[]=new int[10];
		input[0]=3;input[0]=5;input[1]=1;input[2]=10;input[3]=4;
		input[4]=9;input[5]=5;input[6]=34;input[7]=6;input[8]=9;
		input[9]=12;
		pr("the array:");
		for(int e:input)
			System.out.print(e+" ");
		obj5.findNextGreater(input);
		pr("---------------------");
		pr("REVERSE USING STACK");
		Deque<Integer>st2=new ArrayDeque<Integer>();
		st2.push(2);st2.push(7);st2.push(1);st2.push(2);
		st2.push(5);st2.push(3);
		pr("stack top to bottom is:");
		pr(""+st2);
		pr("reversing:");
		obj5.reverse(st2);
		pr("stack after is :");
		pr(""+st2);
		pr("---------------------");
		pr("SORT THE STACK USING STACK");
		Deque<Integer>st3=new ArrayDeque<Integer>();
		st3.push(4);st3.push(5);st3.push(1);st3.push(9);
		st3.push(3);st3.push(6);
		pr("stack top to bottom is :");
		pr(""+st3);
		obj5.sort(st3);
		pr("after sorting top to bottom:");
		pr(""+st3);
		pr("---------------------------");
		pr("stock span problem:".toUpperCase());
		//int[]stockPrice=new int[]{100,10,20,30,80,70,300};
		int[]stockPrice=new int[]{10, 4, 5, 90, 120, 80};
		pr("top to bottom:stock prices:");
		for(int e:stockPrice)
			System.out.print(e+" ");
		pr("");
		obj5.findStockSpan(stockPrice);
		pr("-----------------------------------");
		pr("FIND MINIMUM FROM A STACK IN O(1)");
		Stack<Integer>spclStk=new Stack<Integer>();
		spclStk.pushSp(15);spclStk.pushSp(14);
		spclStk.pushSp(17);spclStk.pushSp(19);
		spclStk.pushSp(21);spclStk.pushSp(10);
		pr("the stack from bottom to top:");
		spclStk.display();
		pr("the min is:"+spclStk.getMinSp());
		pr("pop one:");
		spclStk.popSp();
		pr("the stack is :");
		spclStk.display();
		pr("the min is:"+spclStk.getMinSp());
		pr("pop one:");
		spclStk.popSp();
		pr("the stack is :");
		spclStk.display();
		pr("the min is:"+spclStk.getMinSp());
		pr("pop one:");
		spclStk.popSp();
		pr("the stack is :");
		spclStk.display();
		pr("the min is:"+spclStk.getMinSp());
		pr("pop one:");
		spclStk.popSp();
		pr("the stack is :");
		spclStk.display();
		pr("the min is:"+spclStk.getMinSp());
		pr("pop one:");
		spclStk.popSp();
		pr("the stack is :");
		spclStk.display();
		pr("the min is:"+spclStk.getMinSp());
		pr("----------------------------------");
		pr("VALID PREORDER");
		int[]arr1=new int[]{40, 30, 35, 80, 100};
		System.out.println(obj5.validPreorder(arr1));
		int[]arr2=new int[]{40, 30, 35, 20, 80, 100};
		System.out.println(obj5.validPreorder(arr2));
		pr("------------------------------------");
		pr("GRAPH DFS USING STACK");
		int nodes=7;
		Graph g=new Graph(8);
		g.addEdge(0,1);g.addEdge(0,2);g.addEdge(1,2);
		g.addEdge(3,2);g.addEdge(3,1);g.addEdge(3,5);
		g.addEdge(5,6);g.addEdge(5,7);g.addEdge(6,7);
		g.dfs();
		pr("-------------------------------------");
		pr("COUNT MINIMUM NUMBER OF BRACKETS REVERSAL TO MAKE VALID PARENTHESES:(string only of '{' & '}' :");	
		//String s="{}{{}{{}}{";
		String s="}{{}}}}{{{{{";
		int cntMin=obj5.countMinNumBracketRev(s);
		pr(""+cntMin);
		pr("----------------------------------");
		pr("FIND THE MAXIMUM CONTINOUS LENGTH VAILD SUBSTRING PARENTHESES");
		String sp="()(()()())))()))";
		int maxLength=obj5.findLongestValidSubstring(sp);
		pr("the valid max length: "+maxLength);
		
	}
}
Note: /home/lokender/workspace1/GeeksForGeeks/StackGeeks.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
1 2 3 4 5 6 7 8 9 : ; 
pop one
popped= ;
now stack:
1 2 3 4 5 6 7 8 9 : 
size of stack is:10
1 2 3 4 5 6 7 8 9 : 
----------------------------
WORKING FOR INFIX TO POSTFIX
the infix is: a+b*(c^d^p-e)^(f+g*h)-i+(j^k^l)
scanned and converted
abcdp^^e-fgh*+^*+i-jkl^^+
the postfix is :abcdp^^e-fgh*+^*+i-jkl^^+
---------------------------
POSTFIX EVALUATION
the infix is: 1+3*(2^3-4)^(0+1*3)-5
scanned and converted
1323^4-013*+^*+5-
evaluation of postfix
exp evaluated
188
final value of postfix=188
------------------------------------
WORKING FOR INFIX TO PREFIX
1+3*(2^3-4)^(0+1*3)-5 
5-)3*1+0(^)4-3^2(*3+1
scanned and converted
531+0(432((*3+1+-
reversing the final prefix again
-+1+3*((234(0+135
------------------------------------
CHECK PARETHESIS
expr:[]{(([]))}[]({(){}[()]})(
reached non-empty
invalid
------------------------------
FOR EACH ELEMENT- FIND NEXT GREATER ELEMENT
the array:
5 1 10 4 9 5 34 6 9 12 pair:1 & 10
pair:5 & 10
pair:4 & 9
pair:5 & 34
pair:9 & 34
pair:10 & 34
pair:6 & 9
pair:9 & 12
---------------------
REVERSE USING STACK
stack top to bottom is:
[3, 5, 2, 1, 7, 2]
reversing:
stack after is :
[2, 7, 1, 2, 5, 3]
---------------------
SORT THE STACK USING STACK
stack top to bottom is :
[6, 3, 9, 1, 5, 4]
after sorting top to bottom:
[9, 6, 5, 4, 3, 1]
---------------------------
STOCK SPAN PROBLEM:
top to bottom:stock prices:
10 4 5 90 120 80 
the span of each element is :
1 1 2 4 5 1 
-----------------------------------
FIND MINIMUM FROM A STACK IN O(1)
the stack from bottom to top:
15 14 17 19 21 10 
the min is:10
pop one:
the stack is :
15 14 17 19 21 
the min is:14
pop one:
the stack is :
15 14 17 19 
the min is:14
pop one:
the stack is :
15 14 17 
the min is:14
pop one:
the stack is :
15 14 
the min is:14
pop one:
the stack is :
15 
the min is:15
----------------------------------
VALID PREORDER
true
false
------------------------------------
GRAPH DFS USING STACK
0 
2 
1 
3 
5 
7 
6 
4 
-------------------------------------
COUNT MINIMUM NUMBER OF BRACKETS REVERSAL TO MAKE VALID PARENTHESES:(string only of '{' & '}' :
close:3
open:5
2.0
5
----------------------------------
FIND THE MAXIMUM CONTINOUS LENGTH VAILD SUBSTRING PARENTHESES
the string is:
( ) ( ( ) ( ) ( ) ) ) ) ( ) ) ) 
found currMax between -1 & 1 of length=2
found currMax between 2 & 6 of length=4
found currMax between 2 & 8 of length=6
found currMax between -1 & 9 of length=10
the valid max length: 10
[Finished in 2.3s]






