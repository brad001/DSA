import stack.*;
public class StackApp 
{
  public static void main(String[]args)
  {
	  Stack st=new Stack();
	  st.push(1,22,"chandan");
	  st.push(2, 34, "hari r nair");
	  st.push(3, 21, "falguni");
	  st.push(4, 23, "remiya");
	  st.push(5, 32, "suresh uncle");
	  
	  st.displayStack();
	  st.pop();
	  System.out.println("after one pop");
	  st.displayStack();
			  
  }
}

