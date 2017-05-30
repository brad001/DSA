import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;


class Node
{
	int x;
	int y;

	Node(int x1,int y1)
	{
		x=x1;
		y=y1;
	}
}
class Matrix
{
	private int R;
	private int C;
	private int[][] arr;
	Matrix(int r,int c)
	{
		R=r;
		C=c;
		arr=new int[R][C];
	}

	void set(int[][] a)
	{
		for(int i=0;i<R;i++)
		{
			for(int j=0;j<C;j++)
			{
				arr[i][j]=a[i][j];
			}
		}
	}

	void printMatrix()
	{
		for(int i=0;i<R;i++)
		{
			for(int j=0;j<C;j++)
			{
				System.out.print(arr[i][j]+" ");
			}
			System.out.println("");
		}
	}

	boolean isValid(int[][]ar,int i,int j)
	{
		int R=ar.length, C=ar[0].length;
		if(i>=0 && j>=0 && i<R && j<C)
			return true;
		else 
			return false;
	}

	//search key in sorted matrix in raw and column manner
	/*
		1.start from first row rightmost element
		2.if key is same print
		3.if key is small means it will not be on that column downside to currnent
			so skip that column
		4.same if key is bigger then current means it cant be on tht row before current
			so skip tht row.
	*/
	// O(n) time complexity
	boolean search(int key)
	{
		int i=0,j=C-1;
		while(i<R && j>=0)
		{
			if(arr[i][j]==key)
			{
				System.out.println("found at ("+i+","+j+")");
				return true;
			}
			//skip row
			else if(arr[i][j]>key)
				j--;
			//skip column
			else
				i++;
		}
		System.out.println("not found!");
		return false;
	}
	/*
		1 2 3
		4 5 6 
		7 8 9

		o/p: 1 2 3 6 9 8 7 4 5
		 you are printing in circle form so there are 4 lines(2 rows and 2 columns)
		 are going to be printed in one circle
		 so two rows will be strat and end row
		 two columns will be start and end column
	*/
		 /*
			
		Count Negative Numbers in a Column-Wise and Row-Wise Sorted Matrix

		Find the number of negative numbers in a column-wise / row-wise sorted 
		matrix M[][]. Suppose M has n rows and m columns.

		Example:

		Input:  M =  [-3, -2, -1,  1]
		             [-2,  2,  3,  4]
		             [4,   5,  7,  8]
		Output : 4



		
		We have 4 negative numbers in this matrix

		Here’s a more efficient solution:

    We start from the top right corner and find the position of 
    the last negative number in the first row.
    Using this information, we find the position of the 
    last negative number in the second row.
    We keep repeating this process until we either run out of 
    negative numbers or we get to the last row.

	With the given example:
	[-3, -2, -1,  1]
	[-2,  2,  3,  4]
	[4,   5,  7,  8]

	Here's the idea:
	[-3, -2,  ↓,  ←] -> Found 3 negative numbers in this row
	[ ↓,  ←,  ←,  4] -> Found 1 negative number in this row
	[ ←,  5,  7,  8] -> No negative numbers in this row 

		 */
	int countNegativeNumbers(int[][] arr)
	{
		int i=0;
		int j=arr[0].length-1;
		int count=0;

		while(i<arr.length && j>=0)
		{
			if(arr[i][j]>=0)
				j--;
			else
			{
				count=count+(j+1);
				i++;
			}
		}
		return count;
	}

	void printInSpiralForm()
	{
		/*  take start row index sr
			take end row index er
			take start column index sc
			take end column index ec
		*/
		int sr=0;
		int er=R;
		int sc=0;
		int ec=C;
		//(er=R && ec=C so 	always < will come when you want that element er-1 and ec-1 will come!!)
		//as you know always start index must be less then end index so this is base condition
		while(sr<er && sc<ec)
		{
			//print start row til last allowed column element.
			for(int i=sc;i<ec;i++)
				System.out.print(arr[sr][i]+" ");
			//after printing incerement start row.
			sr++;

			//print end column from start allowed row elemnt to end allowed row element
			//here arr[i][ec-1] coz you know why!
			for(int i=sr;i<er;i++)
				System.out.print(arr[i][ec-1]+" ");
			//after printing decrement end collumn.
			ec--;

			//if there is end column still left to print(sr!==er)
			//print from right to left
			//means from end allowed column ele to start allowed column ele
			if(sr<er)
			{
				for(int i=ec-1;i>=sc;i--)
					System.out.print(arr[er-1][i]+" ");
				//decremnt end colum after printing
				er--;
			}


			//if there is any starting colummn left
			//print in same down to up manner
			if(sc<ec)
			{
				for(int i=er-1;i>=sr;i--)
					System.out.print(arr[i][sc]+" ");
				//inceremnt starting columm.
				sc++;
			}
		}
	}



	/*


	A Boolean Matrix Question

	Given a boolean matrix mat[M][N] of size M X N, modify it such that if 
	a matrix cell mat[i][j] is 1 (or true) then make all the cells of ith row and jth column as 1.

	The matrix
	0 0 0
	0 0 1
	should be changed to following
	0 0 1
	1 1 1

	Method 2 (A Space Optimized Version of Method 1)
	This method is a space optimized version of above method 1. 
	This method uses the first row and first column of the input matrix 
	in place of the auxiliary arrays row[] and col[] of method 1. So what we 
	do is: first take care of first row and column and store the info about 
	these two in two flag variables rowFlag and colFlag. Once we have this info, 
	we can use first row and first column as auxiliary arrays and apply method 1 
	for submatrix (matrix excluding first row and first column) of size (M-1)*(N-1).

	1) Scan the first row and set a variable rowFlag to indicate whether 
	we need to set all 1s in first row or not.
	2) Scan the first column and set a variable colFlag to indicate whether 
	we need to set all 1s in first column or not.
	3) Use first row and first column as the auxiliary arrays row[] and col[] 
	respectively, consider the matrix as submatrix starting from second row and 
	second column and apply method 1.
	4) Finally, using rowFlag and colFlag, update first row and first column if needed.

	Time Complexity: O(M*N)
	Auxiliary Space: O(1)
	*/



	/*

		
		Zigzag (or diagonal) traversal of Matrix

		Given a 2D matrix, print all elements of the given matrix in diagonal order. 
		For example, consider the following 5 X 4 input matrix.

		    1     2     3     4
		    5     6     7     8
		    9    10    11    12
		   13    14    15    16
		   17    18    19    20

		Diagonal printing of the above matrix is

		    1
		    5     2
		    9     6     3
		   13    10     7     4
		   17    14    11     8
		   18    15    12
		   19    16
		   20
	*/
	void printZigzag()
	{
		/********** till all rows are diagonally covered ******************/
		int j=0;
		for(int r=0;r<R;r++)
		{
			j=0;
			for(int i=r;i>=0;i--)
			{
				System.out.print(arr[i][j]+" ");
				if(j+1<C)
					j++;
				else 
					break;
			}
			System.out.println("");
		}

		/************** from last row's 2nd(1st column) element do till last element***/
		for(int c=1;c<C;c++)
		{
			j=R-1;
			for(int i=c;i<C;i++)
			{
				System.out.print(arr[j][i]+" ");
				j--;
			}
			System.out.println("");
		}
	}

	int maxRow()
	{
		int firstIndex=0;
		// Initialize first row as row with max 1s
		int maxRowIndex=0;
		int j=0;
		//find the first index of 1 in 0th row
		while(firstIndex<C-1 && arr[0][firstIndex]!=1)
		{
			firstIndex++;
		}
		//now for all  remaining rows
		for(int i=1;i<R;i++)
		{
			if(isValid(arr,i,firstIndex-1) && arr[i][firstIndex-1]==1)
			{
				//check the prev column is 1
				//if not we take the prevSetRow as Maximum rows
				//upadte the maxNumOnesRow =i 
				while(isValid(arr,i,firstIndex-1) && arr[i][firstIndex-1]==1)
				{
				  firstIndex--;
			    }
			     maxRowIndex=i;
			}
		}
		return maxRowIndex;
	}

	/*
	Given an n x n square matrix, find sum of all sub-squares of 
	size k x k where k is smaller than or equal to n.

	Examples

	Input:
	n = 5, k = 3
	arr[][] = { {1, 1, 1, 1, 1},
	            {2, 2, 2, 2, 2},
	            {3, 3, 3, 3, 3},
	            {4, 4, 4, 4, 4},
	            {5, 5, 5, 5, 5},
	         };
	Output:
	       18  18  18
	       27  27  27
	       36  36  36

	*/
	// A O(n^2) function to find sum of all sub-squares of size k x k
	// in a given square matrix of size n x n
	void printSumOfSubmatrix(int k)
	{
		//here R==C
		int n=R;
	   // k must be smaller than or equal to n
	   if (k > n) return;
	 
	   // 1: PREPROCESSING
	   // To store sums of all strips of size k x 1
	   int[][] auxArray=new int[n-k+1][n];
	 
	   // Go column by column
	   for (int j=0; j<n; j++)
	   {
	       // Calculate sum of first k x 1 rectangle in this column of first row
	       int sum = 0;
	       for (int i=0; i<k; i++)
	          sum += arr[i][j];
	       auxArray[0][j] = sum;
	 
	       // Calculate sum of remaining rectangles
	       for (int i=1; i<n-k+1; i++)
	       {
	            sum += (arr[i+k-1][j] - arr[i-1][j]);
	            auxArray[i][j] = sum;
	       }
	   }
	 
	   // 2: CALCULATE SUM of Sub-Squares using stripSum[][]
	   for (int i=0; i<n-k+1; i++)
	   {
	      // Calculate and print sum of first subsquare in this row
	      int sum = 0;
	      for (int j = 0; j<k; j++)
	           sum += auxArray[i][j];
	      System.out.print(sum+" ");
	 
	      // Calculate sum of remaining squares in current row by
	      // removing the leftmost strip of previous sub-square and
	      // adding a new strip
	      for (int j=1; j<n-k+1; j++)
	      {
	         sum += (auxArray[i][j+k-1] - auxArray[i][j-1]);
	         System.out.print(sum+" ");
	      }
	 
	      System.out.println();
	   }
	}

	void printMaximumSumOfSubmatrix(int k)
	{
		//here R==C
		int n=R;
		int max=0;
		int startRow=0;
		int startcol=0;

	   // k must be smaller than or equal to n
	   if (k > n) return;
	 
	   // 1: PREPROCESSING
	   // To store sums of all strips of size k x 1
	   int[][] auxArray=new int[n-k+1][n];
	 
	   // Go column by column
	   for (int j=0; j<n; j++)
	   {
	       // Calculate sum of first k x 1 rectangle in this column of first row
	       int sum = 0;
	       for (int i=0; i<k; i++)
	          sum += arr[i][j];
	       auxArray[0][j] = sum;
	 
	       // Calculate sum of remaining rectangles
	       for (int i=1; i<n-k+1; i++)
	       {
	            sum += (arr[i+k-1][j] - arr[i-1][j]);
	            auxArray[i][j] = sum;
	       }
	   }
	 
	   // 2: CALCULATE SUM of Sub-Squares using stripSum[][]
	   for (int i=0; i<n-k+1; i++)
	   {
	      // Calculate and print sum of first subsquare in this row
	      int sum = 0;
	      for (int j = 0; j<k; j++)
	           sum += auxArray[i][j];
	       //we have to print max sum wala submatrix
	       //so when max gets upadated update submatrix start point.
	      if(max<sum)
	      {
	      	max=sum;
	      	startRow=i;
	      	startcol=0;
	      }
	 
	      // Calculate sum of remaining squares in current row by
	      // removing the leftmost strip of previous sub-square and
	      // adding a new strip
	      for (int j=1; j<n-k+1; j++)
	      {
	         sum += (auxArray[i][j+k-1] - auxArray[i][j-1]);
	         if(max<sum)
	         {
	         	max=sum;
	         	startRow=i;
	         	startcol=j;
	         }
	      }
	 
	      
	   }
	   for(int i=startRow;i<startRow+k;i++)
	   {
		   	for(int j=startcol;j<startcol+k;j++)
		   	{
		   		System.out.print(arr[i][j]+"  ");
		   	}
		   	System.out.println();
		}
	}	

	

}
/************************ My Input ******************************************/
class useMatrix
{

	boolean isValid(int[][] arr,int x,int y)
	{
		if(x>=0 && y>=0 && x<arr.length && y<arr[0].length)
			return true;
		else 
			return false;
	}
	void MatrixMultiplication(int[][] arr1,int[][] arr2)
	{
		//arr1[m][n] * arr2[n][p];
		int m=arr1.length;
		int n=arr1[0].length;
		int p=arr2[0].length;
		int[][] arr=new int[m][p];

		for(int i=0;i<m;i++)
		{
			for(int j=0;j<p;j++)
			{
				arr[i][j]=0;
				for(int k=0;k<n;k++)
				{
					arr[i][j]+=arr1[i][k]*arr2[k][j];
				}
			}
		}

		for(int i=0;i<m;i++)
		{
			for(int j=0;j<p;j++)
			{
				System.out.print(arr[i][j]+" ");
			}

			System.out.println();
		}
	}


	/*
		Write a code which inputs two numbers m and n and creates a matrix of size 
		m x n (m rows and n columns) in which every elements is either X or 0. 
		The Xs and 0s must be filled alternatively, the matrix should have outermost 
		rectangle of Xs, then a rectangle of 0s, then a rectangle of Xs, and so on.

		Examples:

		Input: m = 3, n = 3
		Output: Following matrix 
		X X X
		X 0 X
		X X X

		Input: m = 4, n = 5
		Output: Following matrix
		X X X X X
		X 0 0 0 X
		X 0 0 0 X
		X X X X X

		modification of printing in spiral form.


	*/
	void ractenagleOfXand0(int R,int C)
	{
		int sr=0;
		int er=R;
		int sc=0;
		int ec=C;

		char arr[][]=new char[R][C];
		char c='X';
		while(sr<er && sc<ec)
		{
			for(int i=sc;i<ec;i++)
				arr[sr][i]=c;
			sr++;

			for(int i=sr;i<er;i++)
				arr[i][ec-1]=c;
			ec--;

			if(sr<er)
			{
				for(int i=ec-1;i>=sc;i--)
					arr[er-1][i]=c;
				er--;
			}

			if(sc<ec)
			{
				for(int i=er-1;i>=sr;i--)
					arr[i][sc]=c;
				sc++;
			}

			if(c=='X')
				c='0';
			else
				c='x';
		}

		for(int i=0;i<R;i++)
		{
			for(int j=0;j<C;j++)
			{
				System.out.print(arr[i][j]+" ");
			}

			System.out.println();
		}

	}
	/*
		
		Count number of islands where every island is row-wise and column-wise separated

		Given a rectangular matrix which has only two possible values ‘X’ and ‘O’. 
		The values ‘X’ always appear in form of rectangular islands and these islands 
		are always row-wise and column-wise separated by at least one line of ‘O’s. 
		Note that islands can only be diagonally adjacent. Count the number of islands 
		in the given matrix.

		Examples:

		mat[M][N] =  {{'O', 'O', 'O'},
		              {'X', 'X', 'O'},
		              {'X', 'X', 'O'},
		              {'O', 'O', 'X'},
		              {'O', 'O', 'X'},
		              {'X', 'X', 'O'}
		             };
		Output: Number of islands is 3

		The idea is to count all top-leftmost corners of given matrix. 
		We can check if a ‘X’ is top left or not by checking following conditions.
		1) A ‘X’ is top of rectangle if the cell just above it is a ‘O’
		2) A ‘X’ is leftmost of rectangle if the cell just left of it is a ‘O’
	*/
	int countIsland(char[][] arr)
	{
		int R=arr.length;
		int C=arr[0].length;
		int count=0;
		for(int i=0;i<R;i++)
		{
			for(int j=0;j<C;j++)
			{
				if(arr[i][j]=='X')
				{
					if((i==0|| arr[i-1][j]=='O') && (j==0 || arr[i][j-1]=='O'))
						count++;
				}
			}
		}
		return count;
	}

	/*
		
Flood-fill Algorithm

    Tutorial

	Flood fill algorithm helps in visiting each and every point in a given area. 
	It determines the area connected to a given cell in a multi-dimensional array. 
	Following are some famous implementations of flood fill algorithm:

	Bucket Fill in Paint:
	Clicking in an area with this tool selected fills that area with the selected color.

	Solving a Maze:
	Given a matrix with some starting point, and some destination with some obstacles in 
	between, this algorithm helps to find out the path from source to destination

	Minesweeper:
	When a blank cell is discovered, this algorithm helps in revealing neighboring cells. 
	This step is done recursively till cells having numbers are discovered.

	Flood fill algorithm can be simply modeled as graph traversal problem, representing the 
	given area as a matrix and considering every cell of that matrix as a vertex that is 
	connected to points above it, below it, to right of it, and to left of it and in case of 
	8-connections, to the points at both diagonals also.

	
	n MS-Paint, when we take the brush to a pixel and click, the color of the region of that pixel 
	is replaced with a new selected color. Following is the problem statement to do this task.
	Given a 2D screen, location of a pixel in the screen and a color, replace color of the given 
	pixel and all adjacent same colored pixels with the given color.

	Input:
       screen[M][N] = {{1, 1, 1, 1, 1, 1, 1, 1},
                      {1, 1, 1, 1, 1, 1, 0, 0},
                      {1, 0, 0, 1, 1, 0, 1, 1},
                      {1, 2, 2, 2, 2, 0, 1, 0},
                      {1, 1, 1, 2, 2, 0, 1, 0},
                      {1, 1, 1, 2, 2, 2, 2, 0},
                      {1, 1, 1, 1, 1, 2, 1, 1},
                      {1, 1, 1, 1, 1, 2, 2, 1},
                      };
    x = 4, y = 4, newColor = 3


    Output:
	Screen should be changed to following.
       screen[M][N] = {{1, 1, 1, 1, 1, 1, 1, 1},
                      {1, 1, 1, 1, 1, 1, 0, 0},
                      {1, 0, 0, 1, 1, 0, 1, 1},
                      {1, 3, 3, 3, 3, 0, 1, 0},
                      {1, 1, 1, 3, 3, 0, 1, 0},
                      {1, 1, 1, 3, 3, 3, 3, 0},
                      {1, 1, 1, 1, 1, 3, 1, 1},
                      {1, 1, 1, 1, 1, 3, 3, 1},
                      };

    DFS methode
	*/
	void floodFillAlgorithm(int[][] arr,int x,int y,int newColor)
	{
		if(isValid(arr,x,y))
		{
			int preColor=arr[x][y];
			floodFillAlgorithm(arr,x,y,preColor,newColor);
		}
		else 
			return;
	}
	void floodFillAlgorithm(int[][] arr,int x,int y,int preColor,int newColor)
	{
		if(!isValid(arr,x,y))
			return;
		if(arr[x][y] != preColor)
			return;
		arr[x][y]=newColor;

		floodFillAlgorithm(arr,x-1,y,preColor,newColor);
		floodFillAlgorithm(arr,x+1,y,preColor,newColor);
		floodFillAlgorithm(arr,x,y-1,preColor,newColor);
		floodFillAlgorithm(arr,x,y+1,preColor,newColor);
	}

	

	
	void floodFillAlgorithmBFS(int[][] arr,int x,int y,int newColor)
	{
		if(isValid(arr,x,y))
		{
			Queue<Node> q=new LinkedList<Node>();
			q.add(new Node(x,y));
			int preColor=arr[x][y];
			floodFillAlgorithmBFS(arr,preColor,newColor,q);
		}
		else 
			return;
	}

	void floodFillAlgorithmBFS(int[][] arr,int preColor,int newColor,Queue<Node> q)
	{
		
		while(!q.isEmpty())
		{
			Node n=q.remove();
			int x=n.x;
			int y=n.y;

			if(!isValid(arr,x,y))
				continue;
			if(arr[x][y] != preColor)
				continue;
			arr[x][y]=newColor;
			q.add(new Node(x-1,y));
			q.add(new Node(x+1,y));
			q.add(new Node(x,y-1));
			q.add(new Node(x,y+1));
		}

	}

	boolean isPathAvailavle(int[][] arr,int sx,int sy,int dx,int dy)
	{
		boolean[][] visited=new boolean[arr.length][arr[0].length];
		return isPathAvailavle(arr,sx,sy,dx,dy,visited);
	}
	boolean isPathAvailavle(int[][] arr,int x,int y,int dx,int dy,boolean[][] visited)
	{
		if(x==dx && y==dy)
			return true;
		if(!isValid(arr,x,y))
			return false;
		if(arr[x][y]==0)
			return false;
		if(visited[x][y])
			return false;

		visited[x][y]=true;

		//System.out.println("("+x+","+y+")");
		if(isPathAvailavle(arr,x-1,y,dx,dy,visited)==true)
			return true;

		if(isPathAvailavle(arr,x+1,y,dx,dy,visited)==true)
			return true;

		if(isPathAvailavle(arr,x,y-1,dx,dy,visited)==true)
			return true;

		if(isPathAvailavle(arr,x,y+1,dx,dy,visited)==true)
			return true;
		return false;
	}

	int findShortestPath(int[][] arr,int sx,int sy,int dx,int dy)
	{
		Queue<Node> q=new LinkedList<Node>();
		if(isValid(arr,sx,sy))
		{
			q.add(new Node(sx,sy));
			//adddelimeter after level.
			q.add(new Node(-1,-1));
		}
		boolean[][] visited=new boolean[arr.length][arr[0].length];
		return findShortestPath(arr,dx,dy,visited,q);
	}

	/*
		in bfs when you want to complete the whole level
		add delimiters between levels
		so that when you find delimeter youn can count the level
		here path is nothing but the number of levels.
	*/ 
	int findShortestPath(int[][] arr,int dx,int dy,boolean[][] visited,Queue q)
	{
		int count=0;
		while(!q.isEmpty())
		{
			Node n=(Node)q.remove();
			int x=n.x;
			int y=n.y;
			if(x==dx && y==dy)
				return count;
			if(x==-1 && y==-1)
			{
				count++;
				if(!q.isEmpty())
					q.add(new Node(-1,-1));
				continue;
			}
			if(!isValid(arr,x,y))
				continue;
			if(visited[x][y])
				continue;
			if(arr[x][y]==0)
				continue;

			visited[x][y]=true;

			q.add(new Node(x-1,y));
			q.add(new Node(x+1,y));
			q.add(new Node(x,y-1));
			q.add(new Node(x,y+1));
		}
		return -1;
	}

	/*
		
		Given a matrix of ‘O’ and ‘X’, replace ‘O’ with ‘X’ if surrounded by ‘X’

		Given a matrix where every element is either ‘O’ or ‘X’, replace ‘O’ with 
		‘X’ if surrounded by ‘X’. A ‘O’ (or a set of ‘O’) is considered to be by surrounded 
		by ‘X’ if there are ‘X’ at locations just below, just above, just left and just right of it.

		Examples:

		Input: mat[M][N] =  {{'X', 'O', 'X', 'X', 'X', 'X'},
		                     {'X', 'O', 'X', 'X', 'O', 'X'},
		                     {'X', 'X', 'X', 'O', 'O', 'X'},
		                     {'O', 'X', 'X', 'X', 'X', 'X'},
		                     {'X', 'X', 'X', 'O', 'X', 'O'},
		                     {'O', 'O', 'X', 'O', 'O', 'O'},
		                    };
		Output: mat[M][N] =  {{'X', 'O', 'X', 'X', 'X', 'X'},
		                      {'X', 'O', 'X', 'X', 'X', 'X'},
		                      {'X', 'X', 'X', 'X', 'X', 'X'},
		                      {'O', 'X', 'X', 'X', 'X', 'X'},
		                      {'X', 'X', 'X', 'O', 'X', 'O'},
		                      {'O', 'O', 'X', 'O', 'O', 'O'},
		                    };

		Input: mat[M][N] =  {{'X', 'X', 'X', 'X'}
		                     {'X', 'O', 'X', 'X'}
		                     {'X', 'O', 'O', 'X'}
		                     {'X', 'O', 'X', 'X'}
		                     {'X', 'X', 'O', 'O'}
		                    };

		Input: mat[M][N] =  {{'X', 'X', 'X', 'X'}
		                     {'X', 'X', 'X', 'X'}
		                     {'X', 'X', 'X', 'X'}
		                     {'X', 'X', 'X', 'X'}
		                     {'X', 'X', 'O', 'O'}
		                    };

		We strongly recommend to minimize your browser and try this yourself first.
		 

		This is mainly an application of Flood-Fill algorithm. The main difference here is 
		that a ‘O’ is not replaced by ‘X’ if it lies in region that ends on a boundary. 
		Following are simple steps to do this special flood fill.

		1) Traverse the given matrix and replace all ‘O’ with a special character ‘-‘.

		2) Traverse four edges of given matrix and call floodFill(‘-‘, ‘O’) 
		for every ‘-‘ on edges. The remaining ‘-‘ are the characters that indicate ‘O’s 
		(in the original matrix) to be replaced by ‘X’.

		3) Traverse the matrix and replace all ‘-‘s with ‘X’s.
	*/


	/*
		1) Start from top right corner, i.e., i = 0, j = n-1.  
   Initialize result as -1.

	2) Do following until we find the result or reach outside the matrix.

	......a) If mat[i][j] is 0, then check all elements on left of j in current row. 
	.........If all elements on left of j are also 0, then set result as i. Note 
	.........that i may not be result, but if there is a result, then it must be i 
	.........(Why? we reach mat[i][j] after discarding all rows above it and all 
	.........columns on right of it)

	.........If all left side elements of i'th row are not 0, them this row cannot 
	.........be a solution, increment i.

	......b) If mat[i][j] is 1, then check all elements below i in current column. 
	.........If all elements below i are 1, then set result as j. Note that j may
	......... not be result, but if there is a result, then it must be j 

	.........If all elements of j'th column are not 1, them this column cannot be a
	.........solution decrement j.

	3) If result is -1, return it. 

	4) Else check validity of result by checking all row and column
	          elements of result
	*/
	int findK(int[][] arr)
	{
		//if matrix is not square return -1
		if(arr.length!=arr[0].length)
			return -1;
		int n=arr.length;
		/*
			// Start from top-most rightmost corner
   			// (We could start from other corners also)
		*/
		int i=0;
		int j=n-1;
		// Initialize result
		int result=-1;
		// Find the index (This loop runs at most 2n times, we either
    	// increment row number or decrement column number)
		while(i<n && j>=0)
		{
			// If current element is 0, then this row may be a solution
			if(arr[i][j]==0)
			{
				// Check for all elements in this row
				//if i==j than ignore coz it can be anything(0 or 1)
				while(j>=0 && (arr[i][j]==0 || i==j))
					//if 0 means that column is not valid
					j--;
				// If all values are 0, then store this row as result
				//means this is valid row
				if(j==-1)
				{
					result=i;
					break;
				}
				// We reach here if we found a 1 in current row, so this
            	//  row cannot be a solution, increment row number
				i++;
			}
			
			else
			{
				// Check for all elements in this column
				//if i==j than ignore coz it can be anything(0 or 1)
				while(i<n && (arr[i][j]==1 || i==j))
					//if 1 means that row is not valid
					i++;
				// If all elements are 1
				if(i==n)
				{
					result=j;
					break;
				}
				// We reach here if we found a 0 in current column, so this
            	// column cannot be a solution, increment column number
				j--;
			}
		}
		// If we could not find result in above loop, then result doesn't exist
		if(result==-1)
			return -1;
		// Check if above computed res is valid
		//check for column(for all rows)
		for(i=0;i<n;i++)
		{
			if(i!=result && arr[i][result]!=1)
				return -1;
		}
		//check for row(for all column)
		for(j=0;j<n;j++)
		{
			if(j!=result && arr[result][j]!=0)
				return -1;
		}
		return result;
	}

/*
	
	Rotate Matrix Elements

	Given a matrix, clockwise rotate elements in it.

	Examples:

	Input
	1    2    3
	4    5    6
	7    8    9

	Output:
	4    1    2
	7    5    3
	8    9    6

	For 4*4 matrix
	Input:
	1    2    3    4    
	5    6    7    8
	9    10   11   12
	13   14   15   16

	Output:
	5    1    2    3
	9    10   6    4
	13   11   7    8
	14   15   16   12

	The idea is to use loops similar to the program for printing a matrix in spiral form. 
	One by one rotate all rings of elements, starting from the outermost. To rotate a ring, 
	we need to do following.
*/
	void rotateMatrixElements(int[][] arr)
	{
		int sr=0;
		int er=arr.length;
		int sc=0;
		int ec=arr[0].length;

		while(sr<er && sc<ec)
		{
			//this is main
			//when matrix is odd check this condition
			//else at the end it will go inside last one but it should not
			if(sr+1==er || sc+1==ec)
				break;

			int pre=arr[sr+1][sc];
			int temp=0;
			for(int i=sc;i<ec;i++)
			{
				temp=arr[sr][i];
				arr[sr][i]=pre;
				pre=temp;				
			}
			sr++;
			for(int i=sr;i<er;i++)
			{
				temp=arr[i][ec-1];
				arr[i][ec-1]=pre;
				pre=temp;				
			}
			ec--;
			if(sr<er)
			{
				for(int i=ec-1;i>=sc;i--)
				{
					temp=arr[er-1][i];
					arr[er-1][i]=pre;
					pre=temp;			
				}
				er--;
			}

			if(sc<ec)
			{
				for(int i=er-1;i>=sr;i--)
				{
					temp=arr[i][sc];
					arr[i][sc]=pre;
					pre=temp;					
				}
				sc++;
			}
		}
	}

	void printMatrix(int[][] arr)
	{
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr[0].length;j++)
			{
				System.out.print(arr[i][j]+" ");;
			}
			System.out.println();
		}
	}


	/*
		
		Validity of a given Tic-Tac-Toe board configuration

		A Tic-Tac-Toe board is given after some moves are played. 
		Find out if the given board is valid, i.e., is it possible to 
		reach this board position after some moves or not.

		Note that every arbitrary filled grid of 9 spaces isn’t valid e.g. 
		a grid filled with 3 X and 6 O isn’t valid situation because each player 
		needs to take alternate turns.

		Input: board[] =  {'X', 'X', 'O', 
		                   'O', 'O', 'X',
		                   'X', 'O', 'X'};
		Output: Valid

		Input: board[] =  {'O', 'X', 'X', 
		                   'O', 'X', 'X',
		                   'O', 'O', 'X'};
		Output: Invalid
		(Both X and O cannot win)

		Input: board[] =  {'O', 'X', ' ', 
		                   ' ', ' ', ' ',
		                   ' ', ' ', ' '};
		Output: Valid
		(Valid board with only two moves played)

		Since “X” is always the first move,

		1)  countX == countO or countX == countO + 1
		2)  If O is in win condition then check 
		     a)     If X also wins, not valid
		     b)     If xbox != obox , not valid
		3)  If X is in win condition then check if xCount is
		     one more than oCount or not  
	*/
	boolean isValidConfiguration(char
		[] board)
	{
		//tic tac to board always take as single array not matrix!!!
		//x is the count of xs and o is the couunt of os in board
		int x=0;
		int o=0;
		//first count x and o in board
		for(int i=0;i<9;i++)
		{
			if(board[i]=='x')
				x++;
			else if(board[i]=='o')
				o++;
		}
		//only below 2 posibilities are vallid else false
		//1.x==o or x==o+1
		if(x==o || x==o+1)
		{
			//if o wins then check if x also wins means false
			//if not then main thing is is o wins then x must be equals to o.
			if(isWin(board,'o'))
			{
				if(isWin(board,'x'))
					return false;
				if(x==o)
					return true;
			}
			//if x wins then main thing to check is that x must be one more then o.
			else if(isWin(board,'x'))
			{
				if(x==o+1)
					return true;
				else
					return false;
			}
			//if no body wins and x==o or x==o+1 then true coz board can be not full
			//or
			/*
				{'O', 'X', ' ', 
               ' ', ' ', ' ',
               ' ', ' ', ' '};
			*/ 
			else
				return true;
		}
		return false;
	}

	boolean isWin(char[] board,char c)
	{
		//this win matrix stores all winning possibilities.
		int[][] win={{0, 1, 2}, // Check first row.
	                {3, 4, 5}, // Check second Row
	                {6, 7, 8}, // Check third Row
	                {0, 3, 6}, // Check first column
	                {1, 4, 7}, // Check second Column
	                {2, 5, 8}, // Check third Column
	                {0, 4, 8}, // Check first Diagonal
	                {2, 4, 6}}; // Check second Diagonal

	    //check for all possibilities even one matches then true.
		for(int i=0;i<8;i++)
		{
			if(board[win[i][0]]==c &&  board[win[i][1]]==c  &&  board[win[i][2]]==c)
				return true;
		}
		return false;
	}


	/*
		
		Find sum of all elements in a matrix except the elements in 
		row and/or column of given cell?

		Given a 2D matrix and a set of cell indexes e.g., 
		an array of (i, j) where i indicates row and j column. For every given cell 
		index (i, j), find sums of all matrix elements except the elements present in 
		i’th row and/or j’th column.

		Example:
		mat[][]  = { {1, 1, 2}
		             {3, 4, 6}
		             {5, 3, 2} }
		Array of Cell Indexes: {(0, 0), (1, 1), (0, 1)}
		Output:  15, 10, 16


		An Efficient Solution can compute all sums in O(R x C + n) time. 
		The idea is to precompute total sum, row and column sums before processing 
		the given array of indexes. Below are details
		1. Calculate sum of matrix, call it sum.
		2. Calculate sum of individual rows and columns. (row[] and col[])
		3. For a cell index (i, j), the desired sum will be “sum- row[i] – col[j] + arr[i][j]”

		*/
	void sumOfMatrixExceptRowColumn(int[][] arr,Node[] input)
	{
		int sum=0;
		int[] row=new int[arr.length];
		int[] col=new int[arr[0].length];

		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr[0].length;j++)
			{
				sum=sum+arr[i][j];
				row[i]=row[i]+arr[i][j];
				col[j]=col[j]+arr[i][j];
			}			
		}

		for(int i=0;i<input.length;i++)
		{
			int x=input[i].x;
			int y=input[i].y;
			if(isValid(arr,x,y))
				System.out.println(sum-row[x]-col[y]+arr[x][y]);
		}
	}

	/*
	      Find common elements in all the rows (Rows are sorted, we may have repeated elements)
	 	  Input: mat[4][5] = { {1, 2, 3, 4, 5},
	       	            {2, 4, 5, 8, 10},
	           	        {3, 5, 7, 9, 11},
	               	    {1, 3, 5, 7, 9},
	                 };
	 Output: 5

	     Algo: 
	         1. each row is sorted, so we can compare all the last column values for all the rows {5,10,11,9}
	         2. find the min among them {5}
	         3. reduce column idnex for all those rows who are greater than {5} and count for all values equal to min
	         4. therefore, next find min among {{5}, 8 , 9 , 7}
	         5. continue and we happen to reach a value if common in all rows
	         6. and the min will equal all the rows last column
	    */

	   /* void findCommonElementsInAllSortedRows(int[][]arr)
    {
    int r=arr.length,c=arr[0].length;

    //track the candidate column numbers in a column-array
    int column[]=new int[r];

    //initialize all the row's last column as last column
    for(int i=0;i<r;i++)
    column[i]=c-1;	

    //start from first row=0
    int rowWithLastMinColumn=0;
   

   
    /*
     Note: WE COMAPRE THE CURRENT MIN_ROW AND ITS CORRESPONDING COLUMN_INDEX WITH ALL OTHER
    


    //do this until you decrement any row's column vaule to less than 0
    while(column[rowWithLastMinColumn] >=0)
    {
    //find the minimum value among all the rows's last valid column 
    for( int i=0;i<r;i++)	
    {
    //initially it compares all row's last column
    //compare current min with all valid row's last column
    if(arr[rowWithLastMinColumn][column[rowWithLastMinColumn]] > arr[i][column[i]]) 
    rowWithLastMinColumn=i;
    }	


    //once we have the rowWithLastMinColumn as current minimum
    //we keep on counting how many are greater than this,
    //we decrement the column[] value for that paritcular row {come once column previous}
    //and calculate how many are equal
    //if all are equal => we have reached to that element in each row

    int equalCount=0;

    for(int i=0;i<r;i++)
    {
    //all that are greater than current min -> reduce their column index in column
    if(arr[i][column[i]] > arr[rowWithLastMinColumn][column[rowWithLastMinColumn]] )
    {
    //we reduce the correspoding row's last valid column position
    //but take care of the value once it is 0, we cant reduce it more

    if(column[i]==0)
    return ;
    else
    {
    column[i]=column[i]-1;
    }
    }
    else  //the values are equal to current min Column
    {
    equalCount++;
    }
    }


    //check if all values are equal and reaches to the number of rows=> all rows has this value
    if(equalCount==r)
    {
    pr("we have a common element :"+arr[rowWithLastMinColumn][column[rowWithLastMinColumn]]);
    pr("in each row at column index:");	
    for(int k:column)
    System.out.print(k+" ");
    pr("");
   
    //UPDATE ALL THE COLUMNS
    for(int i=0;i<r;i++)
    {
    if(column[i]==0)
    return;
    else
    {
    column[i]=column[i]-1;
    rowWithLastMinColumn=0;	
    }
   
    }
    pr("current min value set is :"+arr[rowWithLastMinColumn][column[rowWithLastMinColumn]]);
    }
    }//end of main-while
   
    System.out.println("found all the common elements");
    }*/



    /*
      Submatrix-Sum Queries
      find the sum of sub-matrix between (lowSt,lowEnd) (highSt,highEnd)

      Example:
      Input: mat[M][N] = {{1, 2, 3, 4, 6},
                     {5, 3, 8, 1, 2},
                     {4, 6, 7, 5, 5},
                     {2, 4, 8, 9, 4} };

Query1:  (0,0) - (1,1)
Query2:  (2,2) - (3,4)
Query3:  (1,2) - (3,4)

Output:

Query1: 11  // Sum between (0, 0) and (1, 1)
Query2: 38  // Sum between (2, 2) and (3, 4)
Query3: 38  // Sum between (1, 2) and (3, 3)

   Algo:
   	  1. create aux array[][]
   	  2. first sum column wise downward
   	  3. then row wise sum of all columns
   	  4. this implies => each index (i,j) has sum of all elements from (0,0)

   	 So now. any given (ll,lh) (hl,hh)
   	 =>  (hl,hh) has sum from (0,0)
   	 => to remove all previous rows sum
   	    previous row to (ll,lh) => discard the sum from (0,0) to (ll-1,hh) all rows above the (ll,hl)
   	 => to remove all previous columns sum   
   	     previous colum to (ll,lh) => discard the sum from (0,0) to (hl,lh-1)

   	 => two times the previous diagonal element above (ll,lh) is subtracted, so add it*/


   	 /*
		
		Common elements in all rows of a given matrix

		Given an m x n matrix, find all common elements present in all rows 
		in O(mn) time and one traversal of matrix.

		Example:

		Input:
		mat[4][5] = {{1, 2, 1, 4, 8},
		             {3, 7, 8, 5, 1},
		             {8, 7, 7, 3, 1},
		             {8, 1, 2, 7, 9},
		            };

		Output: 
		1 8 or 8 1
		8 and 1 are present in all rows.

		The idea is to use maps. We initially insert all elements of the 
		first row in an map. For every other element in remaining rows, 
		we check if it is present in the map. If it is present in the map and is 
		not duplicated in current row, we increment count of the element in map by 1, 
		else we ignore the element. If the currently traversed row is the last row, 
		we print the element if it has appeared m-1 times before.

   	 */

   	 void commonElementsInAllRowsUnsorted(int[][] arr)
   	 {
   	 	//take hashmap to store elements and its occurence in each row(without dupllicate count)
   	 	HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();

   	 	int R=arr.length;
   	 	int C=arr[0].length;

   	 	//first put all unique elements of first row into hashMap with row number.
   	 	for(int j=0;j<C;j++)
   	 	{
   	 		//this if condition is to avoid duplication
   	 		if(!hm.containsKey(arr[0][j]))
   	 			hm.put(arr[0][j],0);
   	 	}
   	 	//now for all other rows
   	 	//if element present in map and the row number is immidiate upper row
   	 	//then only insert this row number for that element
   	 	//this will take care of both duplication and all previous 
   	 	//row insertion confirmation
   	 	for(int i=1;i<R;i++)
   	 	{
   	 		for(int j=0;j<C;j++)
   	 		{
   	 			if(hm.containsKey(arr[i][j]) && (hm.get(arr[i][j])==i-1))
   	 				hm.put(arr[i][j],i);
   	 		}
   	 	}

   	 	/*Set s=hm.entrySet();
   	 	Iterator i=s.iterator();

   	 	while(i.hasNext())
   	 	{
   	 		Map.Entry me=(Map.Entry)i.next();
   	 		if(me.getValue()==R-1)
   	 			System.out.print(me.getKey()+"  ");
   	 	}
   	 	System.out.println();*/

   	 	for(Map.Entry me:hm.entrySet())
   	 	{
   	 		if(me.getValue()==R-1)
   	 			System.out.print(me.getKey()+"  ");
   	 	}
   	 	System.out.println();


   	 }

    int[][] sumfrom00toij(int[][] arr)
    {
    		//first copy the first row as it is
			//then column wise sum
			//then sum of all rows
    	int R=arr.length;
    	int C=arr[0].length;
    	int[][] auxArray=new int[R][C];

    	//first store the first row as it as
    	for(int j=0;j<C;j++)
    		auxArray[0][j]=arr[0][j];

    	//column-wise , sum in aux 
		//aux[i][j]=mat[i][j]+aux[i-1][j]
    	for(int i=1;i<R;i++)
    	{
    		for(int j=0;j<C;j++)
    		{
    			auxArray[i][j]=arr[i][j]+auxArray[i-1][j];
    		}
    	}

    	//row wise sum now
    	for(int i=0;i<R;i++)
    	{
    		for(int j=1;j<C;j++)
    		{
    			auxArray[i][j]=auxArray[i][j]+auxArray[i][j-1];
    		}
    	}

    	return auxArray;
    }

    void submatrixQuery(int[][] arr,Node topLeft,Node bottomRight)
    {
    	if(!isValid(arr,topLeft.x,topLeft.y) || !isValid(arr,bottomRight.x,bottomRight.y))
    		return;
    	int[][] auxArray=sumfrom00toij(arr);
    	//total sum is the sum of submatrix from (0,0) to bottomRight (x,y)
    	int totalSum=auxArray[bottomRight.x][bottomRight.y];

    	//subtract the upper rows if falls outside range=>determined by lowSt
    	if(topLeft.x>0)
    		totalSum=totalSum - auxArray[topLeft.x-1][bottomRight.y];

    	//subtract the leftMost columns if falls outside range=>determined by lowEnd
    	if(topLeft.y>0)
    		totalSum=totalSum - auxArray[bottomRight.x][topLeft.y-1];


    	//since we subtract the diagonal above the lowSt,lowEnd twice
    	if(topLeft.x>0 && topLeft.y>0)
    		totalSum=totalSum+auxArray[topLeft.x-1][topLeft.y-1];

    	System.out.println(totalSum);
    }

/*	
	Find a specific pair in Matrix

	Given an n x n matrix mat[n][n] of integers, find the maximum value of 
	mat(c, d) – mat(a, b) over all choices of indexes such that both c > a and d > b.

	Example:

	Input:
	mat[N][N] = {{ 1, 2, -1, -4, -20 },
	             { -8, -3, 4, 2, 1 }, 
	             { 3, 8, 6, 1, 3 },
	             { -4, -1, 1, 7, -6 },
	             { 0, -4, 10, -5, 1 }};
	Output: 18
	The maximum value is 18 as mat[4][2] 
	- mat[1][0] = 18 has maximum difference. 
	An efficient solution uses extra space. We pre-process the matrix such that index(i, j) 
	stores max of elements in matrix from (i, j) to (N-1, N-1) and in the process keeps on 
	updating maximum value found so far. We finally return the maximum value.
*/
    int maxDifference(int[][] arr)
    {
    	//because its a square matrix
    	int n=arr.length;

    	//take one auxArray which will store at (i,j), max value of the 
    	//whole submatrix from (i,j) to (n-1,n-1)
    	int[][] maxArray=new int[n][n];

    	//as we know max valu at point(n-1,n-1) is that point value only
    	maxArray[n-1][n-1]=arr[n-1][n-1];

    	//now update last row's all elements with max from (i,j) to (n-1,n-1)
    	//like say last row is:  2 7 1 10 1 3
    	//then in maxArray store:10 10 10 10 3 3
    	int max=maxArray[n-1][n-1];
    	for(int j=n-2;j>=0;j--)
    	{
    		if(max<arr[n-1][j])
    			max=arr[n-1][j];
    		else
    			arr[n-1][j]=max;
    	}

    	//same way update maxArray's last column
    	max=maxArray[n-1][n-1];
    	for(int i=n-2;i>=0;i--)
    	{
    		if(max<arr[i][n-1])
    			max=arr[i][n-1];
    		else
    			arr[i][n-1]=max;
    	}

    	//now for all other ele of matrix as you know you are storing max of 
    	//whole  submatirx at (i,j) so when you are at point (i,j)
    	//then (i,j) is(a,b) and (c,d) will be only in the submatrix  from
    	//(i+1,j+1) to (n-1,n-1).
    	//you just have to check for max value at (i+1,j+1) and get the diffrence

    	//in first take maxDifference as minimum as possible
    	int maxDifference=Integer.MIN_VALUE;
    	for(int i=n-2;i>=0;i--)
    	{
    		for(int j=n-2;j>=0;j--)
    		{
    			//first  get the difference and update maxdifference if needed
    			if(maxDifference<(maxArray[i+1][j+1]-arr[i][j]))
    				maxDifference=maxArray[i+1][j+1]-arr[i][j];
				//now at (i,j) we have to put maximum of whole submatrix
				maxArray[i][j]=(int)Math.max(((int)Math.max(arr[i][j], maxArray[i+1][j] )), maxArray[i][j+1]);
    		}
    	}

    	return maxDifference;
    }

    void rotateMatrix90Left(int[][] arr)
    {
    	//because its a square matrix
    	int n=arr.length;
    	for(int x=0;x<n/2;x++)
    	{
    		for(int y=x;y<n-1-x;y++)
    		{
    			int temp=arr[x][y];
    			arr[x][y]=arr[y][n-1-x];
    			arr[y][n-1-x]=arr[n-1-x][n-1-y];
    			arr[n-1-x][n-1-y]=arr[n-1-y][x];
    			arr[n-1-y][x]=temp;
    		}
    	}
    }

    void rotateMatrix90Right(int[][] arr)
    {
    	int n=arr.length;
    	for(int x=0;x<n/2;x++)
    	{
    		for(int y=x;y<n-1-x;y++)
    		{
    			int temp=arr[x][y];
    			arr[x][y]=arr[n-1-y][x];
    			arr[n-1-y][x]=arr[n-1-x][n-1-y];
    			arr[n-1-x][n-1-y]=arr[y][n-1-x];
    			arr[y][n-1-x]=temp;
    		}
    	}
    }
    //both 180 lfet and right are same 
    void rotateMatrix180(int[][] arr)
    {
    	int n=arr.length;
    	for(int x=0;x<n/2;x++)
    	{
    		for(int y=x;y<n-1-x;y++)
    		{
    			int temp=arr[x][y];
    			arr[x][y]=arr[n-1-x][n-1-y];
    			arr[n-1-x][n-1-y]=temp;
    			temp=arr[y][n-1-x];
    			arr[y][n-1-x]=arr[n-1-y][x];
    			arr[n-1-y][x]=temp;
    		}
    	}
    }

}	
class FastReader
{
	BufferedReader br;
	StringTokenizer st;

	FastReader()
	{
		br=new BufferedReader(new InputStreamReader(System.in));
	}

	String next()
	{
		while(st==null || !st.hasMoreTokens())
		{
			try
			{
				st=new StringTokenizer(br.readLine());
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		return st.nextToken();
	}

	String nextLine()
	{
		String str="";
		try
		{
			str=br.readLine();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return str;
	}

	int nextInt()
	{
		return Integer.parseInt(next());
	}


	float nextFloat()
	{
		return Float.parseFloat(next());
	}


	double nextDouble()
	{
		return Double.parseDouble(next());
	}

	char nextChar()
	{
		return next().charAt(0);
	}
}
/*****************************************************************************************/
class TestMatrix
{
	public static void main(String[] args)
	{
		FastReader sc=new FastReader();
		/*System.out.println("enter number of rows and columns");
		int R=sc.nextInt();
		int C=sc.nextInt();
		System.out.println("enter matrix elements");
		Matrix m=new Matrix(R,C);
		int[][] arr=new int[R][C];
		for(int i=0;i<R;i++)
		{
			for (int j=0;j<C ;j++ ) 
			{
				arr[i][j]=sc.nextInt();
			}
		}
		m.set(arr);
		System.out.println("enter number of rows and columns");
		int R1=sc.nextInt();
		int C1=sc.nextInt();
		System.out.println("enter matrix elements");
		Matrix m1=new Matrix(R1,C1);
		int[][] arr1=new int[R1][C1];
		for(int i=0;i<R1;i++)
		{
			for (int j=0;j<C1 ;j++ ) 
			{
				arr1[i][j]=sc.nextInt();
			}
		}
		m1.set(arr1);
		useMatrix obj=new useMatrix();
		obj.MatrixMultiplication(arr,arr1);
		//m.search(29);
		//m.printMatrix();
		//m.printInSpiralForm();
		//System.out.println("");
		//m.printZigzag();
		useMatrix obj=new useMatrix();
		obj.ractenagleOfXand0(6,7);*/
		/*System.out.println("enter number of rows and columns");
		int R=sc.nextInt();
		int C=sc.nextInt();
		System.out.println("enter matrix elements");
		Matrix m=new Matrix(R,C);
		int[][] arr=new int[R][C];
		for(int i=0;i<R;i++)
		{
			for (int j=0;j<C ;j++ ) 
			{
				arr[i][j]=sc.nextInt();
			}
		}
		m.set(arr);
		//System.out.println("row with maximum 1's is:"+m.maxRow());
		m.printSumOfSubmatrix(3);
		System.out.println();
		m.printMaximumSumOfSubmatrix(3);*/
		useMatrix obj=new useMatrix();
		/*char[][] a={{'O', 'O', 'O'},
	              {'X', 'X', 'O'},
	              {'X', 'X', 'O'},
	              {'O', 'O', 'X'},
	              {'O', 'O', 'X'},
	              {'X', 'X', 'O'}
	             };
		System.out.println("number of islands are:"+obj.countIsland(a));*/

		int[][] a2={{1, 1, 1, 1, 1, 1, 1, 1},
                      {1, 1, 1, 1, 1, 1, 0, 0},
                      {1, 0, 0, 1, 1, 0, 1, 1},
                      {1, 2, 2, 2, 2, 0, 1, 0},
                      {1, 1, 1, 2, 2, 0, 1, 0},
                      {1, 1, 1, 2, 2, 2, 2, 0},
                      {1, 1, 1, 1, 1, 2, 1, 1},
                      {1, 1, 1, 1, 1, 2, 2, 1},};

         obj.floodFillAlgorithm(a2,0,0,7);
         //obj.printMatrix(a2);
         obj.floodFillAlgorithmBFS(a2,4,4,8);
         //obj.printMatrix(a2);

         int[][] a3={{1,1,1,1},
     				 {0,0,0,0},
     				 {1,1,0,1},
     				 {1,1,1,1}};
     	if(obj.isPathAvailavle(a3,0,0,3,1))
     		System.out.println("path is available");
     	else
     		System.out.println("path is not available");
     	System.out.println("shortest path is:"+obj.findShortestPath(a3,0,0,3,1));
     	System.out.println("valid k is:"+obj.findK(a3));
     	char[] board={'o', 'X', 'X', 
                   'o', 'X', 'X',
                   'o', 'o', 'X'};
        if(obj.isValidConfiguration(board))
        	System.out.println("valid");
        else
        	System.out.println("invalid");
        int[][] a4={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        obj.printMatrix(a4);
        obj.rotateMatrixElements(a4);
        obj.printMatrix(a4);
        /*System.out.println("enter number of points");
        int N=sc.nextInt();
        Node[] a5=new Node[N];
        int[][] a6={{1, 1, 2,3}, {3, 4, 6,4}, {5, 4, 3,2}};
        for(int b=0;b<N;b++)
        {
        	a5[b]=new Node(sc.nextInt(),sc.nextInt());
        }
        obj.sumOfMatrixExceptRowColumn(a6,a5);*/

        /*System.out.println("enter two points");
        Node n1=new Node(sc.nextInt(),sc.nextInt());
        Node n2=new Node(sc.nextInt(),sc.nextInt());
        int[][] a7={{1, 2, 3, 4, 6},
                    {5, 3, 8, 1, 2},
                    {4, 6, 7, 5, 5},
                    {2, 4, 8, 9, 4} };
        obj.submatrixQuery(a7,n1,n2);*/
        int[][] a8={{-3,-2,-1,1},{-2,2,3,4},{4,5,7,8}};
        Matrix m1=new Matrix(1,1);
        System.out.println("total negative numbers in matrix is:"+m1.countNegativeNumbers(a8));

        int[][] a9={{1, 2, 1, 4, 8},
             {3, 7, 8, 5, 1},
             {8, 7, 7, 3, 1},
             {8, 1, 2, 7, 9},
            };

        obj.commonElementsInAllRowsUnsorted(a9);
        int[][] a10={
                 { -10,  2, -1, -4, -20 },
                  {-8,  -3,  4,  2,  1 },
                  { 3,  1,  12, 1,  3 },
                  {-4,  -1,  1,  7, -6 },
                  { 0,  -4, 10, -5,  1 }
               };
        System.out.println("maxDifference is:"+obj.maxDifference(a10));
        int[][] a11={
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16}
    	};
    	obj.printMatrix(a11);
    	System.out.println();
    	obj.rotateMatrix90Left(a11);
    	obj.printMatrix(a11);
    	System.out.println();

    	int[][] a12={
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16}
    	};
    	obj.printMatrix(a12);
    	System.out.println();
    	obj.rotateMatrix90Right(a12);
    	obj.printMatrix(a12);
    	System.out.println();


    	int[][] a13={
        {1, 2, 3, 4,-1},
        {5, 6, 7, 8,-2},
        {9, 10, 11, 12,-3},
        {13, 14, 15, 16,-4},
        {17,18,19,20,-5}
    	};
    	obj.printMatrix(a13);
    	System.out.println();
    	obj.rotateMatrix180(a13);
    	obj.printMatrix(a13);
    	System.out.println();
	}

}
