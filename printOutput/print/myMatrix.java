import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Matrix
{
	private int R;
	private int C;
	int[][]arr;
	public Matrix(int r,int c)
	{
		R=r;
		C=c;

		arr=new int[R][C];
	}

	int getRow()
	{
		return R;
	}
	int getColumn()
	{
		return C;
	}
	void setMat(int[][]m)
	{
		for(int i=0;i<R;i++)
		{
			for(int j=0;j<C;j++)
				arr[i][j]=m[i][j];
		}
	}
	void pr(String s)
	{
		System.out.println(s);
	}
	//checks the indexes of the matrix for valid position
	boolean isValid(int[][]mat, int i,int j)
 	{
 		int R=mat.length;
 		int C=mat[0].length;
 		if(i>=0 && i<=R-1 && j>=0 && j<=C-1)
 			return true;
 		else
 			return false; 		
 	}

	void displayMat()
	{
		System.out.println("the matrix is:");

		for(int i=0;i<R;i++)
		{
			for(int j=0;j<C;j++)
			{
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	void printMatrix(int[][]matrix)
	{
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix[0].length;j++)
				System.out.print(matrix[i][j]+" ");
			System.out.println();
		}
	}
	/*
	  input : row-sorted and column-sorted matrix , key
	  algo  :  O(m+n) in W.C when key is bottom left

	  		1. we start from top-right corner [i=0, j=C-1]
	  		2. if key > top-right => move to next row(bcoz left of it are all small)
	  		3. if key < top-right => move left column(bcoz down of it are all greater)
	  		4. loop this while( i<=R-1 and j>=0) => [i reaches last row and j reaches first column]

	*/

	boolean search(int key)
	{
		int i=0;
		int j=C-1;
		int numOfChecks=0;

		while(i<=R-1 && j>=0)
		{
			numOfChecks++;

			if(key==arr[i][j])
			{
				System.out.println("numOfChecks="+numOfChecks);
				return true;
			}

			if(key>arr[i][j])
				i++;
			else
				j--;
		}

		System.out.println("numOfChecks="+numOfChecks);
		return false;
	}

	/*
		Count Negative Numbers in a Column-Wise and Row-Wise Sorted Matrix

		Example:
		Input:  M =  [-3, -2, -1,  1]
                     [-2,  2,  3,  4]
                     [4,   5,  7,  8]
        Output : 4 (We have 4 negative numbers in this matrix)

	*/
    int countNumberOfNegativeInSortedRowColumn(int[][]arr)
    {
    	int r=arr.length;
    	int c=arr[0].length;

    	//start from top right
    	//above logic as same, if curr num is not-negative => column shift previous (count=Colindex+1)
    	//as below it cant be any value that is negative
    	//as soon we find -ve => we can go down


    	int i=0;
    	int j=c-1;
    	int countNegative=0;

    	//until we reach go beyond j<0 and also if i>r
    	while(i<r && j>=0)
    	{
    		//if negative number => move to next row same column
    		//we count all elemts based on j+1
    		if(arr[i][j]<0)
    		{
    			countNegative=countNegative+(j+1);
    			i++;
    		}
    		else
    		{
    			//positive number means move back to prev column
    			j--;
    		}
    	}
     return countNegative;
    }    

    /*
    Find a specific pair in Matrix
		Given an n x n matrix mat[n][n] of integers, find the maximum value of mat(c, d) – mat(a, b) over all choices of indexes such that both c > a and d > b.

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



		maximum difference mat(c,d)-mat(a,b) | c>a && d>b
		meaning, mat[c][d] must not lie in <= row or column but lie in higher section of mat[a][b]

	Algo:
		1. create an aux array
		2. store the values of last row and last column in such a way that
		    from right-> left we have maximum value flown
		    from bottom -> top we have maximum value flown
		3. then we traverse the mat[][]array in [row-1][col--]  way till 1st column 
		   can calculate: auxArray[i+1][j+1]-mat[i][j] 
		                  (as auxArray[i+1][j+1] stores the maximum value till that row and column in bottom up manner)
		   Also, from that index on-> we store the current max to be among its right and down and arr[i][j] 
		           (so that max from all directin to that point travels through)             
	*/

    int max(int a,int b)
    {
    	return a>b?a:b;
    }
	int findMaxDifferenceWithConstraint(int[][]mat)	
	{
		int r=mat.length,c=mat[0].length,i=0,j=0;
		int[][]aux=new int[r][c];

		//the current maximum is bottom right
		int maxValue=mat[r-1][c-1];

		//store maximum flown through last row in right-left approach is
		i=r-1;
		for(j=c-2;j>=0;j--)
		{
			if(mat[i][j]>maxValue)
				maxValue=mat[i][j];
			aux[i][j]=maxValue;
		}

		//store maximum flown through last column in bottom-up manner
		maxValue=mat[r-1][c-1];
		j=c-1;
		for(i=r-2;i>=0;i--)
		{
			if(mat[i][j]>maxValue)
				maxValue=mat[i][j];
			aux[i][j]=maxValue;
		}


		//once last row of aux are stored in correct order, so that each position sotes the maximum
		//value till that point for the above row if compared from mat[i][j] to diagonal for subtraction { aux[i+1][j+1]-mat[i][j]}

		//similarly, last column of aux stored in correct order, so that each position stores the maximum
		//value till that point for previous column and can be compared the mat[i][j] to diagonal for subtraction { aux[i+1][j+1]-mat[i][j]}

		//traverse now, in row-- manner for all column till 1st column to check maxDiff

		int maxDiff=Integer.MIN_VALUE;

		//for all positions(r, c-2) to (r,0 )
		for(i=r-2;i>=0;i--)
		{
			//traverse till 1st column reached for each row
			for(j=c-2;j>=0;j--)
			{
				//diff has to be maximize
				//mat[i][j] is not yet processed for maximum value, 
				//after this, we consider it for maximum for all elements lying before this column and row on left upper

				if(aux[i+1][j+1]-mat[i][j]>maxDiff)
					maxDiff=aux[i+1][j+1]-mat[i][j];

				//store at this index the maximum flown through for all rows above and colum larger than this
				aux[i][j]=max(mat[i][j],max(aux[i+1][j],aux[i][j+1]));
			}
		}
		return maxDiff;

	}

	/*
		1. make lower and upper bound of rows and columns covered

	->set the appropriate lower and upper bound
		2. when 1st row is covered, increment lr++;
		3. when last column is covered , decrement hc--;
		4. when last row is covered , decrement hr--;
		5. when first column is covered, increment lc++;

	->if else if necessary , otherwise you end up printing some elements twice
	    6. update the direction at the end of each condition

	*/
	void printSpiral()
	{
		int lr=0,hr=R-1;
		int lc=0,hc=C-1;
		char dir='r';

		//lr, lc , hr, hc at each time maintains the correct config
		while(lr<=hr && lc<=hc)
		{

			if(dir=='r')
			{
				int i=lr,j=lc;
				//go upto highest column

				//cover the lowest row
				while(j<=hc)
				{
					System.out.print(arr[i][j]+" ");
					j++;
				}
				dir='d';
				//once covered row, increment lowest row
				lr++;
			}
		    else if(dir=='d')
			{
				int i=lr,j=hc;
				//go upto highest row

				//cover the highest column
				while(i<=hr)
				{
					System.out.print(arr[i][j]+" ");
					i++;
				}
				dir='l';
				//once covered column, decrement highest column
				hc--;	
			}
			else if(dir=='l')
			{
				int i=hr,j=hc;
				//go upto lowest column

				//cover the highest row
				while(j>=lc)
				{
					System.out.print(arr[i][j]+" ");
					j--;
				}
				dir='u';
				//once highest row covered, decrement highest row
				hr--;		
			}
		    else if(dir=='u')
			{
				int i=hr,j=lc;
				//go upto lowest row

				//cover the lowest column
				while(i>=lr)
				{
					System.out.print(arr[i][j]+" ");
					i--;
				}
				dir='r';
				//once covered column, increment lowest column
				lc++;		
			}
		}
	}

	/*
	rotate matrix elements by one
	*/

	void rotateMatrixElements(int[][]mat)
	{

		int lr=0,hr=mat.length-1;
		int lc=0,hc=mat[0].length-1;
		char dir='r';

		if(mat.length!=mat[0].length)
			return;
		else if(mat.length==1)
			return;
		//lr, lc , hr, hc at each time maintains the correct config
		//4 times I do one modification and then the other(alternatively)
		char write='X';
		//turn = true=> write X
		//turn =false => write O
		boolean turn=true;
		int count=0;
		int rings=0;

		//start from diagonal always
		int temp=mat[rings][rings];
		int prev=temp;
		int next=0;

		while(lr<=hr && lc<=hc)
		{

			if(dir=='r')
			{
				int i=lr,j=lc;
				//go upto highest column

				//cover the lowest row
				while(j<=hc)
				{

					next=mat[i][j];
					//mat[i][j]=write;
					mat[i][j]=prev;
					prev=next;
					j++;
				}
				dir='d';
				//once covered row, increment lowest row
				lr++;
				//count to take all direction turn by turn
				count++;
			}
		    else if(dir=='d')
			{
				int i=lr,j=hc;
				//go upto highest row

				//cover the highest column
				while(i<=hr)
				{
					next=mat[i][j];
					//mat[i][j]=write;
					mat[i][j]=prev;
					prev=next;
					i++;
				}
				dir='l';
				//once covered column, decrement highest column
				hc--;	
				//count to take all direction turn by turn
				count++;
			}
			else if(dir=='l')
			{
				int i=hr,j=hc;
				//go upto lowest column

				//cover the highest row
				while(j>=lc)
				{
					next=mat[i][j];
					//mat[i][j]=write;
					mat[i][j]=prev;
					prev=next;
					j--;
				}
				dir='u';
				//once highest row covered, decrement highest row
				hr--;		
				//count to take all direction turn by turn
				count++;
			}
		    else if(dir=='u')
			{
				int i=hr,j=lc;
				//go upto lowest row

				//cover the lowest column
				while(i>=lr)
				{
					next=mat[i][j];
					//mat[i][j]=write;
					mat[i][j]=prev;
					prev=next;
					i--;
				}
				dir='r';
				//once covered column, increment lowest column
				lc++;		
				//count to take all direction turn by turn
				count++;
			}

			//once I have done 4 times direction change
			//make count=0 & turn = false
			//note here that, for even matrix, though the direction will not all be traversed
			// but I might visit all the circular-path
			//so i will have my lr or lc cross the highest value
			//it marks the end of one circle as well
			//count==4 also marks the end for outer circles until you rech
			//for odd=> count==4 will suffice
			if(count==4 || lr>hr || lc>hc)
			{
				mat[rings][rings]=next;
				rings++;

			  temp=mat[rings][rings];
			  prev=temp;
			  next=0;

				
				count=0;
			}
			
		}//end of while
	}

	/*
		rotate matrix 90 degree-left : each copying => [i][j]= [j] [n-1-i] , same on inside,but subtract left of lhs from n-1
		rotate matrix 90 degree-right: each copying => [i][j]= [n-1-j] [i] , same on outside,but subtract right of lhs from n-1
		Algo:
			1. in rings pattern, copy elements in fashion
			2. top <- right , right <- bottom , bottom <- left , left <- top
			3. the number of rings in total are :
			 					For n=even -> ceil(n/2) [we have all complete rings]
			 					For n=odd  -> ceil(n/2) [we have all complete but one single element]
			 					          * but to caclulate from 0, we go one less = floor(n/2)

	*/
	void rotate90Left(int[][]mat)	
	{
		pr("before 90 degree left rotation");
		printMatrix(mat);


		int r=mat.length,c=mat[0].length;

		//(number of elements in each row/column)
		int n=c;  

		for(int i=0;i<n/2;i++)
		{
			//for each ring ,Note: in each side of ring, we update n-1 fields 
			//each ring starts at updated i and moves  i elements less in each side of the ring

			//suppose 5*5 matrix : (3 layers) : n=5 ,i=0, j=0 to 5-0-1
			//   1st iteration: move 00,01,02,03 elements and copy on them from right side, then bottom,then left....
			//   2nd iteration: move 11,12
			//   3rd iteration: move 22

			for(int j=i;j<n-i-1;j++)
			{

				int temp=mat[i][j];
				//left rotate=> right same => middle of both same
				// 			  => different in left => outer(n-1-(leftPuter))
				mat[i][j]=mat[j][n-1-i];
				mat[j][n-1-i]=mat[n-1-i][n-1-j];
				mat[n-1-i][n-1-j]=mat[n-1-j][i];
				mat[n-1-j][i]=temp;
			}
		}

		pr("after 90 degree left rotation");
		printMatrix(mat);
	}

	/*
		rotate matrix 90 degree-right
		rotate matrix 90 degree-right: each copying => [i][j]= [n-1-j] [i] , same on outside,but subtract right of lhs from n-1
	*/	
	void rotate90Right(int[][]mat)	
	{
		pr("before 90 degree right rotation");
		printMatrix(mat);


		int r=mat.length,c=mat[0].length;

		//(number of elements in each row/column)
		int n=c;  

		for(int i=0;i<n/2;i++)
		{
			//for each ring ,Note: in each side of ring, we update n-1 fields 
			//each ring starts at updated i and moves  i elements less in each side of the ring

			//suppose 5*5 matrix : (3 layers) : n=5 ,i=0, j=0 to 5-0-1
			//   1st iteration: move 00,01,02,03 elements and copy on them from right side, then bottom,then left....
			//   2nd iteration: move 11,12
			//   3rd iteration: move 22

			for(int j=i;j<n-i-1;j++)
			{

				int temp=mat[i][j];
				//rotate right=> same on left => outer
				// 			  => different in right => inner(n-1-(left))
				mat[i][j]=mat[n-1-j][i];
				mat[n-1-j][i]=mat[n-1-i][n-1-j];
				mat[n-1-i][n-1-j]=mat[j][n-1-i];
				mat[j][n-1-i]=temp;
			}
		}

		pr("after 90 degree left rotation");
		printMatrix(mat);
	}	

	void rotate270Left(int[][]mat)
	{
		pr("before 270 left");
		printMatrix(mat);

		rotate90Right(mat);

		pr("after 270 left");
		printMatrix(mat);		

	}
	void rotate270Right(int[][]mat)
	{
		pr("before 270 right");
		printMatrix(mat);

		rotate90Left(mat);

		pr("after 270 right");
		printMatrix(mat);				
	}


	/*
	Rotate matrix 180 degree-left/right
	 1 2 3		9 8 7
	 4 5 6  =>  6 5 4 
	 7 8 9		3 2 1

	  Algo:
	  	 1. rows are swapped first -last + in reverse order
		 2. for(i=0;i<=rows/2;i++) equal sign handles the odd order (reversing the middle row)
		 3.  for(j=0;j<c;j++) 
		 4.     swap [i][j] from bottom [lastrow-i][lastcol-j]

		 Example 1st row and last row
		          // 00 <-> 22
		          // 01 <-> 21
		          // 02 <-> 20
	*/

	void rotate180(int[][]mat)
	{
		pr("before rotating 180 degree:");
		printMatrix(mat);

		int r=mat.length,c=mat[0].length;

		//do for all the rows in pairs, top and bottom
		for(int i=0;i<r/2;i++)
		{
			
			//it considers all rows, but for middle'th row
			//it goes from front to last=> swaps back elements	
			for(int j=0;j<c;j++)
			{
				//swap frontOfFirst-Row and backOfLast-Row

				int temp=mat[i][j];
				mat[i][j]=mat[r-1-i][c-1-j];
				mat[r-1-i][c-1-j]=temp;
			}
		}

		//if rows are odd => middle rows has to be swapped, go to half
		if(r%2==1)
		{
			int i=r/2;
			for(int j=0;j<c/2;j++)	
			{
				int temp=mat[i][j];
				mat[i][j]=mat[i][c-1-j];
				mat[i][c-1-j]=temp;
			}
		}
		

		pr("after rotating 180 degree:");
		printMatrix(mat);

	}




	void makeCicularRingsAlt(char[][]mat)
	{
		int lr=0,hr=mat.length-1;
		int lc=0,hc=mat[0].length-1;
		char dir='r';

	
		//lr, lc , hr, hc at each time maintains the correct config
		//4 times I do one modification and then the other(alternatively)
		char write='X';
		//turn = true=> write X
		//turn =false => write O
		boolean turn=true;
		int count=0;
		while(lr<=hr && lc<=hc)
		{

			if(dir=='r')
			{
				int i=lr,j=lc;
				//go upto highest column

				//cover the lowest row
				while(j<=hc)
				{
					mat[i][j]=write;
					j++;
				}
				dir='d';
				//once covered row, increment lowest row
				lr++;
				//count to take all direction turn by turn
				count++;
			}
		    else if(dir=='d')
			{
				int i=lr,j=hc;
				//go upto highest row

				//cover the highest column
				while(i<=hr)
				{
					mat[i][j]=write;
					i++;
				}
				dir='l';
				//once covered column, decrement highest column
				hc--;	
				//count to take all direction turn by turn
				count++;
			}
			else if(dir=='l')
			{
				int i=hr,j=hc;
				//go upto lowest column

				//cover the highest row
				while(j>=lc)
				{
					mat[i][j]=write;
					j--;
				}
				dir='u';
				//once highest row covered, decrement highest row
				hr--;		
				//count to take all direction turn by turn
				count++;
			}
		    else if(dir=='u')
			{
				int i=hr,j=lc;
				//go upto lowest row

				//cover the lowest column
				while(i>=lr)
				{
					mat[i][j]=write;
					i--;
				}
				dir='r';
				//once covered column, increment lowest column
				lc++;		
				//count to take all direction turn by turn
				count++;
			}

			//once I have done 4 times direction change
			//make count=0 & turn = false
			if(count==4)
			{
				if(turn==true)
				{
					turn =false;
					write='O';
				}
				else
				{
					turn =true;	
					write='X';
				}
				count=0;
			}
			
		}

		System.out.println("the matrix in alternate X and O is:");

		for(int i=0;i<mat.length;i++)
		{
			for(int j=0;j<mat[0].length;j++)
			{
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
	}

	/*
	 --> the matrix can be 1*3, 3*1 , 2*4 , 3*3
	 --> 1st we traverse the 1st column and based on that all the elements that comes diagonally
	 --> at last we reach the last row's 2nd , 3rd,4th...column elemnts whose diagonal might be left

	 1. one for loop [ for Rows=changing , Column=0] 
	 	1.1 while loop will go till all diagonals traversed
	 2.	one for loop [ for Rows=last  , Column=changing]    ]
	 	2.1 while loop will go till all diagonals traversed

	*/

	void printDiagonal()
	{
		int r=R-1;
		int c=C-1;
		int i=0,j=0;

		//untill all rows are covered
		for(i=0;i<=r;i++)
		{
			//00,10,20,...
			j=0;
			int ii=i;
			int jj=j;
			while(isValid(arr,ii,jj))
			{
				System.out.print(arr[ii][jj]+" ");
				ii--;
				jj++;
			}
			System.out.println();
		}

		//all the 1st columns are covered
		//from last row's 2nd column..... we need to traverse for each elements's diagonal


		//for the last row's remaining elemnts
		i=r;
		for(j=1;j<=c;j++)
		{
			int ii=i;
			int jj=j;
			while(isValid(arr,ii,jj))
			{
				System.out.print(arr[ii][jj]+" ");
				ii--;
				jj++;
			}
			System.out.println();
			//j++;
		}
	}

	/*
	MATRIX - MULTIPLICATION :
	1.  void multiply(int A[][N], int B[][N], int C[][N])
		{
		    for (int i = 0; i < N; i++)
		    {
		        for (int j = 0; j < N; j++)
		        {
		            C[i][j] = 0;
		            for (int k = 0; k < N; k++)
		            {
		                C[i][j] = C[i][j]+ ( A[i][k]*B[k][j] );
		            }
		        }
		    }
		}

	2. 	Divide and Conquer : 
			-) Divide matrices A and B in 4 sub-matrices of size [ N/2 x N/2 ] as shown in the below diagram.
		    -) Calculate following values recursively. ae + bg, af + bh, ce + dg and cf + dh.

			 -) T(N) = 8T(N/2) + O(N^2)   (8 multiplication , and addition will be N^2 )
			    From Master's Theorem, time complexity of above method is O(N3)
			    which is unfortunately same as the above naive method.	


	3. Strassen's Mult:

			T(N) = 7T(N/2) +  O(N2)  ( O(NLog7) which is approximately O(N^2.8074 ))
			It is faster than the standard matrix multiplication algorithm and 
			   It is useful in practice for large matrices, 	
			   but would be SLOWER than the fastest known algorithms for extremely large matrices
	*/

	void multiply(int[][]A,int[][]B)
	{
		/*
			1.for multiplication, [each row of a * each column of b]
			  so, we must have    [ Col of A = Row of B ]
			2. C has (R1*C2)  

			  C[i][j]=C[i][j]+(all multiplications of ith row elemnts of A * all col elements of B )
			         = C[i][j] + ( (ith fixed-col varies) * (row varies-col fixed j))
			         = C[i][j] + (  all columns of ith row * each row of jth column )
			         = C[i][j] + ( so we need A[i][k]* B[k][j] ) // { k times loop for one elemnt in C}
		*/

		//rows=> A.length
		//cols=> A[0].length
		int r1=A.length,c1=A[0].length,r2=B.length,c2=B[0].length;

		if(c1==r2)
		{

			int[][]C=new int[r1][c2];
			int i=0,j=0;

			//for each element from row of A, get each element from col of B
			//so A[i][k] and B[k][j]
			for(i=0;i<r1;i++)
			{
				for(j=0;j<c2;j++)
				{
					C[i][j]=0;
					//k varies to c1 or r2 both are equal, we need to multiply that many elemets
					for(int k=0;k<c1;k++)
						C[i][j]=C[i][j]+(A[i][k]*B[k][j]);
				}
			}
			
			System.out.println("A matrix");
			printMatrix(A);
			System.out.println("B matrix");
			printMatrix(B);			
			System.out.println("the mult matrix C is:");
			printMatrix(C);
		}
		else
		{
			System.out.println("cant multiply");
		}
	}

	/*
	 Find the row with maximum number of 1s
	 Given a boolean 2D array, where each row is sorted. Find the row with the maximum number of 1s.

		Example
		Input matrix
		0 1 1 1
		0 0 1 1
		1 1 1 1  // this row has maximum 1s
		0 0 0 0

		Output: 2
	*/

	void maxNumOnes(int[][]mat)
	{
		System.out.println("the matrix is:");
		printMatrix(mat);

		int r=mat.length-1,c=mat[0].length-1;
		int i=0,j=0;
		//find first index of 1 or else last column if not any 1
		int maxNumOnesRow=0;

		//firstIndex will point to that location if there is a 1
		//or else it wil point to last location(column)
		int firstIndex=0;
		i=0;


		for(j=0;j<=c;j++)
		{
			if(mat[i][j]==1)
			{
				firstIndex=j;
				break;
			}
		}

		//if not found any 1
		if(j==c+1)
			firstIndex=c;



		System.out.println("first index:"+firstIndex);

		//for each row , skip if col below has 0 otherwise find its prev column

		for(i=1;i<=r;i++)
		{
			if(isValid(mat,i,firstIndex-1) && mat[i][firstIndex-1]==1)
			{
				//check the prev column is 1
				//if not we take the prevSetRow as Maximum rows
				//upadte the maxNumOnesRow =i 
				while(isValid(mat,i,firstIndex-1) && mat[i][firstIndex-1]==1)
				{
					firstIndex--;
				}
			  maxNumOnesRow=i;
			}
		}

		//the maxNumOnesRow is the row number
		System.out.println("the maximum number of 1's present in Row No.="+maxNumOnesRow);
	}

	/*
	Algo: 
	  1. k*1 column wise sum storage
	  2. 1*k row wise sum storage
	  3. finally each value => k*k  possible sub-matrices sum


	  For each column: 
	  		1. for loop : we store k elements sum in 1st place
	  		2. for(row1 to nextOfk )
	  			add i+k-1(row-th value) , subtract i-1(row-th value)
		< WE HAVE ALL POSSIBLE K*1 SUB-MATRIX SUM IN THE MATRIX first (n-k+1 rows and n columns)>	  			

	  For each row:
	       1. for loop :We store k elements sum in 1st palce
	       2. for(col1 to nextOfk)
	       		add j+k-1(col-th value), subtract(j-1 colth value)
	       <FINALLY WE HAVE K*K SUM MATRIX>		

	*/

	void findSumAllSubMatrix(int[][]mat,int k)
	{
		int r=mat.length,c=mat[0].length;
		pr("the original matrix is:");
		printMatrix(mat);

		int[][]sumMatrix=new int[r][c];
		int i=0,j=0;

		//for each column starting at 1st element : go down column wise store
		for(j=0;j<c;j++)
		{
			int sum=0;

			//first find the sum of first k*1 block in the column
			for(i=0;i<k;i++)
				sum=sum+mat[i][j];

			//store the sum of each first k elements in 1st place in each column
			sumMatrix[0][j]=sum;

			//for rest of the k*1 sub-matrix col-wise store in the next place
			for(i=1;i<r-k+1;i++)
			{
				//add the next kth element from the ith index: [i+k-1]
				//and subtract previous fromt ith element : [i-1]
				sum=sum+(mat[i+k-1][j]-mat[i-1][j]);
				//store the sum in the index
				sumMatrix[i][j]=sum;
			}
		} //end-for column wise

		pr("submatrix");
		printMatrix(sumMatrix);

		//for each row starting at 1st element : go right row-wise
		//each element of subMatrix now store sum of 3 column of originalMatrix
		//therefore sum of next 3 elements=> k*k sumSubMatrix
		for(i=0;i<r-k+1;i++)
		{
			int sum=0;

			//first find the sum of first 1*k block in the row
			for(j=0;j<k;j++)
				sum=sum+sumMatrix[i][j];

			//print the sum
			System.out.print(sum+" ");

			//for rest of the 1*k sub-matrix row-wise store in the next place
			for(j=1;j<c-k+1;j++)
			{
				//add the next kth element from the jth index: [j+k-1]
				//and subtract previous fromt jth element : [j-1]
				sum=sum+(sumMatrix[i][j+k-1]-sumMatrix[i][j-1]);
				//store the sum in the index
				//sumMatrix[i][j]=sum;
				System.out.print(sum+" ");
			}
			System.out.println("");
		}

	}
	/*
	find maximum sum sub-matrix of size k
	*/
    void findSumMaxSubMatrix(int[][]mat,int k)
	{
		int r=mat.length,c=mat[0].length;
		pr("the original matrix is:");
		printMatrix(mat);

		int[][]sumMatrix=new int[r][c];
		int i=0,j=0;

		//for each column starting at 1st element : go down column wise store
		for(j=0;j<c;j++)
		{
			int sum=0;

			//first find the sum of first k*1 block in the column
			for(i=0;i<k;i++)
				sum=sum+mat[i][j];

			//store the sum of each first k elements in 1st place in each column
			sumMatrix[0][j]=sum;

			//for rest of the k*1 sub-matrix col-wise store in the next place
			for(i=1;i<r-k+1;i++)
			{
				//add the next kth element from the ith index: [i+k-1]
				//and subtract previous fromt ith element : [i-1]
				sum=sum+(mat[i+k-1][j]-mat[i-1][j]);
				//store the sum in the index
				sumMatrix[i][j]=sum;
			}
		} //end-for column wise

		pr("submatrix");
		printMatrix(sumMatrix);

		//for each row starting at 1st element : go right row-wise
		//each element of subMatrix now store sum of 3 column of originalMatrix
		//therefore sum of next 3 elements=> k*k sumSubMatrix
		int maxSum=0;
		//rowIndex and colIndex will track which k*k starting index gives me maximum
		int rowIndex=0;
		int colIndex=0;
		for(i=0;i<r-k+1;i++)
		{
			int sum=0;

			//first find the sum of first 1*k block in the row
			for(j=0;j<k;j++)
				sum=sum+sumMatrix[i][j];

			//print the sum
			//System.out.print(sum+" ");
			if(sum>maxSum)
			{
				rowIndex=i;
				colIndex=0;
				maxSum=sum;
			}
			


			//for rest of the 1*k sub-matrix row-wise store in the next place
			for(j=1;j<c-k+1;j++)
			{
				//add the next kth element from the jth index: [j+k-1]
				//and subtract previous fromt jth element : [j-1]
				sum=sum+(sumMatrix[i][j+k-1]-sumMatrix[i][j-1]);

				if(sum>maxSum)
				{
					rowIndex=i;
				    colIndex=j;
				    maxSum=sum;
				}
				
				//store the sum in the index
				//sumMatrix[i][j]=sum;
				//System.out.print(sum+" ");
			}
			
		}
		System.out.println("maximum sum="+maxSum);
		System.out.println("starting at index"+rowIndex+" "+colIndex);
		for(i=rowIndex;i<rowIndex+k;i++)
		{
			for(j=colIndex;j<colIndex+k;j++)
			{
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}

	}




	/*
	  1.count the number of islands:
	  2.each islands is separated by O's

	  Algo:
	     1. the first X of each island will have a property
	      	-top is O and left is O
	      	- but if X is left most or top most => row 0 or col 0

	*/
	int countNumIsland(char[][]mat)
	{
		int cnt=0;

		for(int i=0;i<mat.length;i++)
		{
			for(int j=0;j<mat[0].length;j++)
			{
				if(mat[i][j]=='X')
				{
					//check for top and left of i,j :both should be 'O'
					//if( mat[i-1][j]=='O' &&  mat[i][j-1]=='O')  => must be valid index 
					
					//so for row check if i==0 dont check mat[i-1][j]
					//for column check if j==0 dont check mat[i][j-1]

					if( (i==0 || mat[i-1][j]=='O') && (j==0 || mat[i][j-1]=='O'))
						cnt++;
				}
			}
		}
		return cnt;
	}


	/*
	Given a matrix of ‘O’ and ‘X’, replace ‘O’ with ‘X’ if surrounded by ‘X’
	Given a matrix where every element is either ‘O’ or ‘X’, replace ‘O’ with ‘X’ 
	if surrounded by ‘X’. A ‘O’ (or a set of ‘O’) is considered to be by surrounded by ‘X’ 
	if there are ‘X’ at locations just below, just above, just left and just right of it.

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

		2) Traverse four edges of given matrix and call floodFill(‘-‘, ‘O’) for every ‘-‘ on edges. 
		The remaining ‘-‘ are the characters that indicate ‘O’s (in the original matrix) to be replaced by ‘X’.

		3) Traverse the matrix and replace all ‘-‘s with ‘X’s.
	*/

	/*
	flood fill algorithm
	dfs manner in each of the top bottom left right cell


	*/
	//we want to color all the connected cells to next color
	//so first find the color of the current cell
	//and set it to prevColor
	//color all adjacent to newColor if matches to prevColor
	void floodFillDfs(int[][]arr5,int x,int y,int newColor)
	{
		int prevColor=arr5[x][y];
		floodFillMatrixDfs(arr5,x,y,prevColor,newColor);
	}

	void floodFillMatrixDfs(int[][]arr5,int x,int y,int prevColor,int newColor)
	{
		if(!isValid(arr5,x,y))
			return;
		//if the color of this index is same as prev, only then color it new otherwise return
		if(arr5[x][y]!=prevColor)
			return;

		//if valid index and color of this index is same as prevColor
		//color it wihth new and continue 
		arr5[x][y]=newColor;

		floodFillMatrixDfs(arr5,x-1,y,prevColor,newColor);
		floodFillMatrixDfs(arr5,x,y+1,prevColor,newColor);
		floodFillMatrixDfs(arr5,x+1,y,prevColor,newColor);
		floodFillMatrixDfs(arr5,x,y-1,prevColor,newColor);
	}

	/*
	flood fill algorithm
	bfs manner in each of the top bottom left right cell
	*/
	class MatNode
	{
		int x;
		int y;
		public MatNode(int x,int y)
		{
			this.x=x;
			this.y=y;
		}

	}
	void floodFillBfs(int[][]arr6,int x,int y,int newColor)
	{
		int prevColor=arr6[x][y];
		MatNode newNode=new MatNode(x,y);

		Queue<MatNode>q=new ArrayDeque<MatNode>();
		q.add(newNode);
		floodFillMatrixBfs(arr6,q,prevColor,newColor);
	}
	void floodFillMatrixBfs(int[][]arr6,Queue<MatNode>q,int prevColor,int newColor)
	{

		while(!q.isEmpty())
		{
			MatNode front=q.remove();
			int x=front.x;
			int y=front.y;

			//check if x and y are valid , only then continue for rest nodes
			//otherwise simply discard the node, remove
			if(!isValid(arr6,x,y))
				continue;
			if(arr6[x][y]!=prevColor)
				continue;
			//color the node
			arr6[x][y]=newColor;
			q.add(new MatNode(x-1,y));
			q.add(new MatNode(x,y+1));
			q.add(new MatNode(x,y-1));
			q.add(new MatNode(x+1,y));
		}
	}

	/*
	 path exists from a source to a destination
	*/
	 boolean floodFillFindPath(int[][]arr6,int x,int y,int s,int d)
	 {
	 	//to mark my path, so that i dont come to the index again and go on loop

	 	boolean visited[][]=new boolean[arr6.length][arr6[0].length];
	 	return floodFillFindPathDfs(arr6,visited,x,y,s,d);	
	 }
	 boolean floodFillFindPathDfs(int[][]arr6,boolean[][]visited,int x,int y,int s,int d)
	 {
	 	//reached destination
	 	if(x==s && y==d)
	 		return true;

	 	if(!isValid(arr6,x,y))
	 		return false;

	 	//valid index only then check all other
	 	//if x,y has 0, return
	 	if(arr6[x][y]==0)
	 		return false;

	 	if(visited[x][y]==true)
	 		return false;

	 	visited[x][y]=true;

	 	


	 	System.out.println("going for x="+x+" y="+y);
	 	if(floodFillFindPathDfs(arr6,visited,x-1,y,s,d)==true)
	 		return true;
	 	if(floodFillFindPathDfs(arr6,visited,x,y+1,s,d)==true)
	 		return true;
	 	if(floodFillFindPathDfs(arr6,visited,x,y-1,s,d)==true)
	 		return true;
	 	if(floodFillFindPathDfs(arr6,visited,x+1,y,s,d)==true)
	 		return true;

	 	return false;
	 }


	int floodFillFindPathLength(int[][]arr,int x,int y,int source,int dest)
	{
		boolean[][]visited=new boolean[arr.length][arr[0].length];
		Queue<MatNode>q=new LinkedList<MatNode>();
		q.add(new MatNode(x,y));
		return floodFillFindPathLengthBfs(q,arr,visited,source,dest);	
	}
	int floodFillFindPathLengthBfs(Queue<MatNode>q,int[][]arr,boolean[][]visited,int s,int d)
	{
		int count=0;
		MatNode marker=null;

		q.add(marker);

		while(!q.isEmpty())
		{
			MatNode front=q.remove();

			if(front==null)
			{
				//if quueu is empty=> dont push marker again
				//if not, more levels to go mark the end by adding marker
				if(!q.isEmpty())
				{
					q.add(marker);
					count++;
				}
			}
			else
			{
				int x=front.x;
				int y=front.y;

				if(x==s && y==d)
					return count;
				if(!isValid(arr,x,y))
					continue;
				if(visited[x][y]==true)
					continue;

				visited[x][y]=true;

				q.add(new MatNode(x-1,y));
				q.add(new MatNode(x,y-1));
				q.add(new MatNode(x,y+1));
				q.add(new MatNode(x+1,y));
			}
			
		}

		return -1;
	}



	/*
	 find kth row which has all 0's and kth column has all 1's

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
    int findLargestRectangleWithColumnSwap(int[][] arr)
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
    -the board is 1-d char array with 'X','O' ,' '
	- we need to store all the winning configuration of the board
	- check for all these if the character is same

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
    */

	boolean isWin(char[]board,char c)
	{
		//check all 8 possible configuration of winning
		//this stores the char index of board

		int win[][]=new int[][]{{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

		// win[i][0] win[i][1] win[i][2] gets the index if boardand check all three charactrers same

		for(int i=0;i<8;i++)
			if(board[win[i][0]]==c && board[win[i][1]]==c && board[win[i][2]]==c)
				return true;

		return false;	
	}

	//the board of tic-tac-toe will always be stored in character 1-d array
	//so that we can map it accordingly with all the winning config
	//Note: X starts the move in a tic-tac-toe game

    boolean isValidTicToeConfig(char[]board)
    {
    	//count the number of X's and O's in the board
    	int countX=0,countO=0;
    	for(int i=0;i<board.length;i++)
    	{
    		if(board[i]=='X')
    			countX++;
    		else if(board[i]=='O')
    			countO++;
    	}

    	//the pre-requisite for a valid board is when X's=O's or X's=O's+1
    	//otherwise it is never a valid board configuration
    	if(countX==countO || countX==countO+1)
    	{	
    		//either one wins
    		//or none wins

    		//I still need to check the possibility of only one among them exists if one wins
    		//if O wins => it must have any one combination of winning config 
    		if(isWin(board,'O')==true)
    		{
    			//X cant win alongwith O 
    			if(isWin(board,'X'))
    			{
    				return false;
    			}
    			else
    			{
    				//O wins alone, but not X => I still need to verify the condition
    				//O wins means=> count of X and O must be same
    				if(countX==countO)
    					return true;
    				else
    					return false;
    			}
    		}
    		else if(isWin(board,'X'))
    		{
    			//if X wins the board but not O , then count must satisfy
    			
    				if(countX==countO+1)
    					return true;
    				else
    					return false;
    		}
    		//if nobody wins => i must have a valid move because of the condition
    		//countX==countO || countX==countX==countO+1
    		else
    			return true;

    	}
    	else
    	{
    		return false;
    	}
    }

    /*
     Find sum of all elements in a matrix except the elements in row and/or column of given cell?
     mat[][]  = { {1, 1, 2}
             	  {3, 4, 6}
             	  {5, 3, 2} }
		Array of Cell Indexes: {(0, 0), (1, 1), (0, 1)}
		Output:  15, 10, 16

	Algo:
		1. make two arrays: row and col -> storing the individual col and row sums
		2. calc totSum of the matrix
		3. for any i,j => we dont need ith row and jth colum
		4. therefore, subtract the row[i] and col[j] from total sum
		5. but two times the intersection elemnt gets subtracted, therofre add that elemnt to compensate
    */

    int[] findSumOfAllExceptRowAndCol(int[][]mat,ArrayList<Pair1>p)
    {
    	int []output=new int[p.size()];

    	int totSum=0;
    	int[]row=new int[mat.length];
    	int[]col=new int[mat[0].length];

    	for(int i=0;i<mat.length;i++)
    	{
    		for(int j=0;j<mat[0].length;j++)
    		{
    			totSum=totSum+mat[i][j];
    			row[i]=row[i]+mat[i][j];
    			col[j]=col[j]+mat[i][j];
    		}
    	}

    	for(int p1=0;p1<p.size();p1++)
    	{
    		output[p1]=totSum-row[p.get(p1).i]-col[p.get(p1).j]+mat[p.get(p1).i][p.get(p1).j];
    	}
    	return output;
    }

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

	   	  So now. any given (lowSt,lowEnd) (highSt,highEnd) 	
	   	  =>  (lowSt,lowEnd) has sum from (0,0)
	   	  => to remove all previous rows sum
	   	     previous row to (lowSt,lowEnd) => discard the sum from (0,0) to (lowSt-1,highEnd) all rows above the (lowSt,highSt)
	   	  => to remove all previous columns sum   
	   	      previous colum to (lowSt,lowEnd) => discard the sum from (0,0) to (highSt,lowEnd-1)

	   	  => two times the previous diagonal element above (lowSt,lowEnd) is subtracted, so add it

    */

	int[][] fillAuxArray(int[][]mat)
	{
		//first copy the first row as it is
		//then column wise sum
		//then sum of all rows
		int r=mat.length;
		int c=mat[0].length;
		int[][]aux=new int[r][c];
		int i=0,j=0;

		//first store the first row as it as
		for(j=0;j<c;j++)
			aux[0][j]=mat[0][j];

		//column-wise , sum in aux 
		//aux[i][j]=mat[i][j]+aux[i-1][j]
		for(j=0;j<c;j++)
			for(i=1;i<r;i++)
				aux[i][j]=aux[i-1][j]+mat[i][j];

		pr("the aux matrix after column wise sum:");
		printMatrix(aux);


		//row wise sum now
		for(i=0;i<r;i++)	
		{
			for(j=1;j<c;j++)
			{
				aux[i][j]=aux[i][j]+aux[i][j-1];
			}
		}

		pr("the aux matrix after row wise sum:");
		printMatrix(aux);

		return aux;



	}
    int findSumOfSubMatrixWithRange(int[][]mat,int lowSt,int lowEnd,int highSt,int highEnd)
    {
    	if(!isValid(mat,lowSt,lowEnd) && !isValid(mat,highSt,highEnd))
    		return -1;
    	pr("helllo");
    	int[][]aux=fillAuxArray(mat);

    	//now for the range of query, which dont fall in the range
    	//we know how to discard the upper rows and leftmost columns
    	
    	//this stores the sum from (0,0)
    	int result=aux[highSt][highEnd];
    	pr("max sum="+result);

    	//subtract the upper rows if falls outside range=>determined by lowSt
    	if(isValid(aux,lowSt-1,highEnd))
    	result=result-(aux[lowSt-1][highEnd]);

    	//subtract the leftMost columns if falls outside range=>determined by lowEnd
    	if(isValid(aux,highSt,lowEnd-1))
    	result=result-(aux[highSt][lowEnd-1]);

    	//since we subtract the diagonal above the lowSt,lowEnd twice
    	if(isValid(aux,lowSt-1,lowEnd-1))
    	result=result+aux[lowSt-1][lowEnd-1];

    	return result;

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

    void findCommonElementsInAllSortedRows(int[][]arr)
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
    	*/


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
    }

    /*
		Given a Binary Tree where all values are from 0 to n-1. 
		Construct an ancestor matrix mat[n][n]. Ancestor matrix is defined as below.

		mat[i][j] = 1 if i is ancestor of j
		mat[i][j] = 0, otherwise
		Examples:

		Input: Root of below Binary Tree.
		          0
		        /   \
		       1     2
		Output: 0 1 1
		        0 0 0 
		        0 0 0 

		Input: Root of below Binary Tree.
		           5
		        /    \
		       1      2
		      /  \    /
		     0    4  3
		Output: 0 0 0 0 0 0 
		        1 0 0 0 1 0 
		        0 0 0 1 0 0 
		        0 0 0 0 0 0 
		        0 0 0 0 0 0 
		        1 1 1 1 1 0    	
    */
	
	int[][]ancestorMatrixFromBinaryTree(Node root,int numNodes)
	{
		int[][]ancestorMatrix=new int[numNodes][numNodes];
		traverseRecAndStore(root,ancestorMatrix);
		return ancestorMatrix;
	}
	void traverseRecAndStore(Node curr,int[][]arr)
	{
		if(curr.left!=null)
		{
			traverseRecAndStore(curr.left,arr);
			//once back from this recursion
			//curr points to last but one up level, and curr.left points to leaf
			arr[curr.data][curr.left.data]=1;

			//OR of all its child's row array to its parent
			for(int i=0;i<arr.length;i++)
				arr[curr.data][i]=arr[curr.data][i] | arr[curr.left.data][i];

		}
		if(curr.right!=null)
		{
			traverseRecAndStore(curr.right,arr);
			//once back from this recursion, do the same
			arr[curr.data][curr.right.data]=1;

			//OR of all its child's row array to its parent
			for(int i=0;i<arr.length;i++)
				arr[curr.data][i]=arr[curr.data][i] | arr[curr.right.data][i];

		}
	}	

	/*
	common elements
	*/	        
	void findCommonElementsInMatrixNoSortedOrder(int[][]arr)
	{
		Map<Integer,Integer>m=new HashMap<Integer,Integer>();
		//1st row
		int i=0,j=0;
		int r=arr.length,c=arr[0].length;

		for(j=0;j<c;j++)
		{
			if(!m.containsKey(arr[i][j]))
				m.put(arr[i][j],i+1);
		}

		//for other rows
		for(i=1;i<r;i++)
		{
			for(j=0;j<c;j++)
			{
				if(m.containsKey(arr[i][j]))
				{
				   if((int)m.get(arr[i][j])==i-1)
				   	m.put(arr[i][j],i+1);
				}
			}
		}

		for(Map.Entry e:m.entrySet())
		{
			if((int)e.getValue()==(r-1))//last row index must match the value of the element in the hashmap
			System.out.println("element="+e.getKey());
		}
	}

    



}

class Node
{
	Node left;
	Node right;
	int data;
	public Node(int data)
	{
		this.data=data;
	}
}
class tree
{
  Node root;
  public tree()
  {
  	root=null;
  }
}

class Pair1
{
	int i;
	int j;
	public Pair1(int i,int j)
	{
		this.i=i;
		this.j=j;
	}
}
class FastReader
{
	private BufferedReader br;
	private StringTokenizer st;

	public FastReader()
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
			catch(IOException e)
			{
				System.out.println("error in Io");
			}
		}
		return st.nextToken();
	}

	int nextInt()
	{
		return Integer.parseInt(next());
	}
	String nextLine()
	{
		String s="";
		try
		{
			s=br.readLine();
		}
		catch(IOException e)
		{
		  System.out.println("error IO");
		}
		return s;
	}
	char nextChar()
	{
		return next().charAt(0);
	}
}


public class Matrices
{
	static void pr(String s)
	{
		System.out.println(s);
	}

	public static void main(String[]args)
	{	
		FastReader sc=new FastReader();

		/*
		pr("********************************");
		
		pr("enter the row and column");
		int r=sc.nextInt();int c=sc.nextInt();
		Matrix mt1=new Matrix(r,c);
		int [][]mat=new int[r][c];
		pr("enter the elments :");
		for(int i=0;i<r;i++)
		  for(int j=0;j<c;j++)
		  	mat[i][j]=sc.nextInt();
		mt1.setMat(mat);


		pr("********************************");
		
		pr("search key in sorted matrix col-wise and row-wise".toUpperCase());
		pr("enter the row and column");
		int i=0,j=0;
		r=sc.nextInt();c=sc.nextInt();
		Matrix mt2=new Matrix(r,c);
		int [][]mat2=new int[r][c];
		pr("enter the sorted elments :");
		for(i=0;i<r;i++)
		  for(j=0;j<c;j++)
		  	mat2[i][j]=sc.nextInt();
		mt2.setMat(mat2);  
		pr("enter the key to be searched:");
		int key=sc.nextInt();
		if(mt2.search(key))
			pr("key found");
		else
			pr("key not found");


		pr("*********************************");

		pr("spiral print of a matrix".toUpperCase());
		pr("given matrix is :");
		mt2.displayMat();
		pr("spiral form is:");
		mt2.printSpiral();

		pr("*********************************");

		pr("print diagonally the elements of the matrix");
		pr("enter the order");
		r=sc.nextInt();c=sc.nextInt();
		Matrix mt3=new Matrix(r,c);
		int [][]mat3=new int[r][c];
		pr("enter the sorted elments :");
		for(i=0;i<r;i++)
		  for(j=0;j<c;j++)
		  	mat3[i][j]=sc.nextInt();
		mt3.setMat(mat3);  
		mt3.displayMat();
		pr("diagonal form is:");
		mt3.printDiagonal();

		pr("***********************************");

		pr("multiply two matrices");
		int[][]A=new int[][]{{1,2,1,3},{1,2,3,4},{4,3,2,1}};
		int[][]B=new int[][]{{3,2},{2,1},{1,1},{5,1}};
		mt3.multiply(A,B);


		pr("***********************************");

		pr("MAKE CIRCULAR RINGS IN MATRIX ALTERNATE X's AND O's");
		pr("enter row and column");
		r=sc.nextInt();c=sc.nextInt();
		char[][]ch=new char[r][c];
		mt3.makeCicularRingsAlt(ch);

		pr("*************************************");

		pr("FIND ROW WITH MAXIMUM NUMBER OF 1's (array is sorted and contains 0's and 1's)");
		pr("enter row and column");
		r=sc.nextInt();c=sc.nextInt();
		int[][]arr3=new int[r][c];
		for(i=0;i<r;i++)
			for(j=0;j<c;j++)
				arr3[i][j]=sc.nextInt();
		mt3.maxNumOnes(arr3);

		
		
		pr("***********************************");		
		Matrix mt3=new Matrix(1,1);
		pr("FIND SUM OF ALL SUB-MATRIX OF SIZE K*K");
		pr("enter row and column");
		int r=sc.nextInt(),c=sc.nextInt();
		pr("enter the elments :");
		int[][]arr4=new int[r][c];
		for(int i=0;i<r;i++)
			for(int j=0;j<c;j++)
				arr4[i][j]=sc.nextInt();
		pr("enter the size of all sub-matrix to find sum");	
		int k=sc.nextInt();
		mt3.findSumAllSubMatrix(arr4,k);
		mt3.findSumMaxSubMatrix(arr4,k);
		*/
		
		/*
		pr("**************************************");			

		pr("COUNT THE NUMBER OF ISLANDS in a matrix of X's and O's(each X's separated by O's)");
		pr("enter row and column");
		r=sc.nextInt();c=sc.nextInt();
		ch=new char[r][c];
		for(i=0;i<r;i++)
			for(j=0;j<c;j++)
				ch[i][j]=sc.nextChar();		
		int countIsland=mt3.countNumIsland(ch);
		pr("the number of islands are:"+countIsland);

		pr("*************************************");

		pr("FLOOD FILL ALGORITHM Dfs (fill the region with next color)");
		pr("enter the row and column");
		r=sc.nextInt();c=sc.nextInt();
		int [][]arr5=new int[r][c];
		pr("enter the MS-Paint matrix with 1,2,3");
		for(i=0;i<r;i++)
			for(j=0;j<c;j++)
				arr5[i][j]=sc.nextInt();
		int newColor=4;	
		pr("continue? 'y':'n' ");
		while(sc.nextChar()!='n')
		{
			pr("enter the cordinate you want to put color to: x&y?");	
			int x=sc.nextInt();int y=sc.nextInt();
			mt3.floodFillDfs(arr5,x,y,newColor);
			newColor++;
			pr("after the operation: the colors of matrix is :");
			for(i=0;i<r;i++)
			{
				for(j=0;j<c;j++)
				{
					System.out.print(arr5[i][j]+" ");
				}
				System.out.println();
			}
		}
		
		pr("************************************");

		pr("FLOOD FILL ALGORITHM Bfs (fill the region with next color)");
		pr("enter the row and column");
		r=sc.nextInt();c=sc.nextInt();
		int [][]arr6=new int[r][c];
		pr("enter the MS-Paint matrix with 1,2,3");
		for(i=0;i<r;i++)
			for(j=0;j<c;j++)
				arr6[i][j]=sc.nextInt();
		newColor=4;	
		pr("continue? 'y':'n' ");
		while(sc.nextChar()!='n')
		{
			pr("enter the cordinate you want to put color to: x&y?");	
			int x=sc.nextInt();int y=sc.nextInt();
			mt3.floodFillBfs(arr6,x,y,newColor);
			newColor++;
			pr("after the operation: the colors of matrix is :");
			for(i=0;i<r;i++)
			{
				for(j=0;j<c;j++)
				{
					System.out.print(arr6[i][j]+" ");
				}
				System.out.println();
			}
		}

		pr("*****************************************");

		pr("find if there is any path exists between source and destination");
		pr("enter the row and column");
		r=sc.nextInt();c=sc.nextInt();
		int [][]arr7=new int[r][c];
		pr("enter the matrix with 0,1 [0-not move :  1-move postion]");
		for(i=0;i<r;i++)
			for(j=0;j<c;j++)
				arr7[i][j]=sc.nextInt();
		pr("enter the source x and y");	
		int x=sc.nextInt();int y=sc.nextInt();
		pr("enter the destination x and y");	
		int source=sc.nextInt();int dest=sc.nextInt();
		if(mt3.floodFillFindPath(arr7,x,y,source,dest))
			pr("path do exists");
		else
			pr("path do not exists");

		
			int r=0,c=0,x=0,y=0,source=0,dest=0,i,j;
			int[][]arr;
			Matrices mt=new Matrices();
		pr("*****************************************");

		pr("if any path exists between source and destination: Return path length , -1 otherwise");
		pr("enter the row and column");
		r=sc.nextInt();c=sc.nextInt();
		arr=new int[r][c];
		pr("enter the matrix with 0,1 [0-not move :  1-move postion]");
		for(i=0;i<r;i++)
			for(j=0;j<c;j++)
				arr[i][j]=sc.nextInt();
		pr("enter the source x and y");	
		x=sc.nextInt();y=sc.nextInt();
		pr("enter the destination x and y");	
		source=sc.nextInt();dest=sc.nextInt();
		pr("shortest length="+mt.floodFillFindPathLength(arr,x,y,source,dest));

		*/
		/*
		pr("the tic-tac-toe problem");
		pr("enter the 9 characters (X,O,_)");
		char []board=new char[9];
		for(int i=0;i<9;i++)
			board[i]=sc.nextChar();

		Matrix mt=new Matrix(1,1);
		if(mt.isValidTicToeConfig(board))
			System.out.println("valid");
		else
			System.out.println("in-valid");
			*/

			/*
		pr("enter the row and column");
		int r=sc.nextInt();
		int c=sc.nextInt();
		int[][]arr=new int[r][c];
		pr("enter the elements");
		for(int i=0;i<r;i++)
			for(int j=0;j<c;j++)
			arr[i][j]=sc.nextInt();

		Matrix mt=new Matrix(1,1);	
		mt.rotateMatrixElements(arr);
		pr("after rotating elements");
		mt.printMatrix(arr);
		*/

		/*
		pr("enter the row and column for sqaure matrix");
		int r=sc.nextInt();int c=sc.nextInt();
		int[][]arr=new int[r][c];
		for(int i=0;i<r;i++)
			for(int j=0;j<c;j++)
				arr[i][j]=sc.nextInt();
		pr("enter how many set of pairs ");	
		int len=sc.nextInt();
		ArrayList<Pair1>p=new ArrayList<Pair1>();
		int cnt=1;
		while(cnt<=len)
		{
			    p.add(new Pair1(sc.nextInt(),sc.nextInt()));
				cnt++;
		}
		Matrix mt=new Matrix(1,1);
		int []output=mt.findSumOfAllExceptRowAndCol(arr,p);
		for(int e:output )
			System.out.print(e+" ");

		*/
		
		
		/*
		int mat[][] = { {1, 2, 3, 4, 5},
                    	  {2, 4, 5, 8, 10},
                    	  {2, 5, 7, 9, 11},
                          {1,2, 3, 5, 9}};
                        
        Matrix mt=new Matrix(1,1);
        mt.findCommonElementsInAllSortedRows(mat);
        pr("*************");

        */

        /*
        int mat[][] = {{1, 2, 3, 4, 6},
                         {5, 3, 8, 1, 2},
                         {4, 6, 7, 5, 5},
                         {2, 4, 8, 9, 4} };

        Matrix mt=new Matrix(1,1);
        pr("returned sum="+mt.findSumOfSubMatrixWithRange(mat,2,2,3,4));
        pr("returned sum="+mt.findSumOfSubMatrixWithRange(mat,0,0,1,1));
        pr("returned sum="+mt.findSumOfSubMatrixWithRange(mat,1,2,3,3));

        */
        /*
        int arr[][]={{-4, -3, -2,  -1},
      				 {-3,  2,  3,  4},
      				 {-1,   5,  7,  8}};
        Matrix mt=new Matrix(1,1);
        pr("the nuber of negative numbers are :"+mt.countNumberOfNegativeInSortedRowColumn(arr));
        */

        /*

        tree t2=new tree();
        t2.root=new Node(0);
        t2.root.left=new Node(1);
        t2.root.right=new Node(2);
        Matrix mt=new Matrix(1,1);
        int[][]ancestorMatrix=mt.ancestorMatrixFromBinaryTree(t2.root,3);
        pr("the ancestor matrix:");
        for(int i=0;i<ancestorMatrix.length;i++)
        {
        	for(int j=0;j<ancestorMatrix[0].length;j++)
        		System.out.print(ancestorMatrix[i][j]+" ");
        	System.out.println();
        }


        tree t1=new tree();
        t1.root=new Node(5);
        t1.root.left=new Node(1);
        t1.root.right=new Node(2);
        t1.root.left.left=new Node(0);
        t1.root.left.right=new Node(4);
        t1.root.right.left=new Node(3);


        mt=new Matrix(1,1);
        ancestorMatrix=mt.ancestorMatrixFromBinaryTree(t1.root,6);
        pr("the ancestor matrix:");
        for(int i=0;i<ancestorMatrix.length;i++)
        {
        	for(int j=0;j<ancestorMatrix[0].length;j++)
        		System.out.print(ancestorMatrix[i][j]+" ");
        	System.out.println();
        }
		*/
        /*
		int[][]mat={{1, 8, 1, 4, 7},
                    {3, 7, 8, 5, 1},
                    {8, 7,5 , 3, 1},
                    {8, 1, 1, 7, 9},
                    };
		Matrix m=new Matrix(1,1);            
		m.findCommonElementsInMatrixNoSortedOrder(mat);
		*/
		/*
		int [][]mat={
                 { -10,  2, -1, -4, -20 },
                  {-8,  -3,  4,  2,  1 },
                  { 3,  15,  12, 1,  3 },
                  {-4,  -1,  1,  7, -6 },
                  { 0,  -4, 10, -5,  1 }
               };
		Matrix mt=new Matrix(1,1);
		pr("the maximum difference="+mt.findMaxDifferenceWithConstraint(mat));
		*/
		int[][]mat1={{1,  2,  3,  4 },
 					{5,  6,  7,  8 },
 					{9, 10, 11, 12 },
					{13, 14, 15, 16 },
					//{17, 18, 19, 20}
				};

		Matrix mt=new Matrix(1,1);
		pr("matrix rotation 90 degree left");
		mt.rotate90Left(mat1);

		int[][]mat2={{1,  2,  3,  4 ,-1},
 					{5,  6,  7,  8 ,-2},
 					{9, 10, 11, 12 ,-3},
					{13, 14, 15, 16 ,-4},
					{17, 18, 19, 20,-5}};
		pr("matrix rotation 90 degree left");
		mt.rotate90Right(mat1);

		int[][]mat3={{1,  2,  3,  4 ,-1},
 					{5,  6,  7,  8 ,-2},
 					{9, 10, 11, 12 ,-3},
					{13, 14, 15, 16 ,-4},
					{17, 18, 19, 20,-5}};

		pr("matrix rotation 180 degree left");
		mt.rotate180(mat1);
		mt.rotate180(mat1);

		
		pr("matrix rotation 270 degree right");
		mt.rotate270Right(mat1);

		pr("matrix rotation 270 degree left");
		mt.rotate270Left(mat1);


	}
}
