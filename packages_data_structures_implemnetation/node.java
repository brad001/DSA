package node;

public class Node
{
 public int adata;
 public int bdata;
 public String str;
 public Node next;
 
   public Node(int a,int b,String s)
   {
	   adata=a;
	   bdata=b;
	   str=s;
   }
   public Node(int dest,int wt)
   {
	   adata=dest;
	   bdata=wt;
   }
   
 
}
