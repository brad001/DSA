/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Node
{
 int vertex;
 int weight;   
}
class Anode
{
int vertex;
int priority;
}
class Graph
{
  private int V; 
  private LinkedList<Node>adj[];
  Graph(int v)      
  {
      V=v;
      adj=new LinkedList[V];
      for(int i=0;i<V;i++)
      adj[i]=new LinkedList();
  }
  void addEdge(int v,int w,int wt)
  {
      Node n=new Node();
      Node n1=new Node();
      n.vertex=w;
      n.weight=wt;
      
    adj[v].add(n);
      n1.vertex=v;
      n1.weight=wt;
    adj[w].add(n1);
    
    //undirected graph
     //adj[w].add(v);
  } 
    void top_down_min_heapify(ArrayList<Anode>arr,int i)
	{ 
		int n=arr.size();
		while(2*i+1<n)
		{
			int left=2*i+1;
			int right=2*i+2;
			int min;
			
			if(right<n && arr.get(right).priority<arr.get(left).priority)
			   min=right;
			else
			   min=left;
			   
			   //System.out.println("index= "+arr.get(min));
			if(arr.get(i).priority > arr.get(min).priority)
			 {
			   //int temp=arr.get(i).;
			   //arr.set(i,arr.get(min));
			   //arr.set(min,temp);
			   //i=min;
                Collections.swap(arr,i,min);
			 }
			 else
		       break;
		}
		
	}
    void build_min_heap(ArrayList<Anode>arr)
	{
		//System.out.println(arr.size()/2);
		for(int i=arr.size()/2-1;i>=0;i--)
		{
			//System.out.println("helklo");
			top_down_min_heapify(arr,i);
		}
		
	}
	void bottom_up_min_heapify(ArrayList<Anode>arr,int i)
	{
		while((i-1)/2 >=0)
		{
			int parent=(i-1)/2;
		
		//swap if inserted is less than parent
			if(arr.get(i).priority<arr.get(parent).priority)
			{
				/*int temp_p=arr.get(i).priority;
                int temp_v=arr.get(i).vertex;
                arr.get(i).priority=arr.get(parent).priority;
                arr.get(i).vertex=arr.get(prent).verterx;
                arr.get(parent).priority=temp_p;
                arr.get(parent).vertex=temp_v;
				*/
                Collections.swap(arr,i,parent);
				
				i=parent;
			}
			else //correct position of inserted element
			  break;
			
		}
        
		
	}
    void add_min_heap(ArrayList<Anode>arr,int val,int []dist)
    {
        Anode temp=new Anode();
        temp.priority=dist[val];
        temp.vertex=val;
    	
        arr.add(temp);
    	//go up to parent and till the root in worst case
    	// the index =arr.size()-1
        bottom_up_min_heapify(arr,arr.size()-1);
      	
    }
    /*void extract_min(ArrayList<Integer>arr)
    {
    	if(arr.size()>0)
    	System.out.println(arr.get(0));
    	else
    	System.out.println("no elements present");
    }
    */
    int delete_min(ArrayList<Anode>arr)
    {
        int val=arr.get(0).vertex;
        arr.remove(0);
        return val;
    }
    void update_key(ArrayList<Anode>arr,int index,int val)
    {
    	//set this location a new value
    	//based on prev value of this loc
    	//if(prev<new) =>new  will come down => top_down
    	//if(prev>new) =>new will move up =>bottom_up
    	
    	int prev=arr.get(index).priority;
    	arr.get(index).priority=val;
    	//System.out.println("prev value= "+prev);
    //	System.out.println("after updated min heap= "+arr);
    	if(prev<val)
    	{
    	    	top_down_min_heapify(arr,index);
    	}
    	else
    	{
    		bottom_up_min_heapify(arr,index);
    	}
    }	
    int find(ArrayList<Anode>arr,int v)
    {
       for(int i=0;i<arr.size();i++)    
       {
         if(arr.get(i).vertex == v)
             return i;
       }
        return -1;
    }
    void shortest(int s)
    {
        boolean []visited=new boolean[V];
        int []parent=new int[V];
        int []dist=new int[V];
        
        //start
        dist[s]=0;
        parent[s]=-1;
        ArrayList<Anode>arr=new ArrayList<Anode>();
        
        build_min_heap(arr);
        dist[s]=0;
        add_min_heap(arr,s,dist);
        visited[s]=true;
        
        
        while(!arr.isEmpty())
        {
          int u=delete_min(arr);  
            //System.out.println("min from the heap is "+u);
          //Iterator<Node>it=adj[u].listIterator();
          for(int i=0;i<adj[u].size();i++)
          {
              int v=adj[u].get(i).vertex;
              //System.out.println("for "+u+" adjacent vertex "+v);
            if(!visited[v])    
            {
                //System.out.println(v+" not visited");
                dist[v]=adj[u].get(i).weight;
                //System.out.println("distance calc from "+u+" to "+v+" = "+dist[v]);
                
               add_min_heap(arr,v,dist);  
                parent[v]=u;
                visited[v]=true;
            }
            else
            {
                //System.out.println("entereing for "+adj[u].get(i).vertex);
               if(adj[u].get(i).weight< dist[v])
               {
                   //System.out.println(v+" is visted");
                   int index=find(arr,v);
                   if(index!=-1)
                   {
                       int new_priority=adj[u].get(i).weight;  
                       dist[v]=new_priority;  
                       //System.out.println("new distance "+dist[v]+" from "+u);
                      update_key(arr,index,new_priority);   
                      parent[v]=u;    
                   }
               }
            }
          }
          
            
        }
        
        int sum=0;
        System.out.println("shortest distances array ");
        for(int i=0;i<V;i++)
            {
            System.out.print(dist[i]+" ");
            sum+=dist[i];
             }
        
        System.out.println("\nedges includes");
        //0 doesnt have parent
        
        for(int i=1;i<V;i++)
            {
             System.out.println("parent of "+i+ " is "+parent[i] + " : edge("+i+","+parent[i]+")");
            
            
            }
        System.out.println("\n sum of mst is: "+sum );
        
    }
    	
}
class Solution
{
	
    
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc=new Scanner(System.in);
        
        Graph g=new Graph(9);
       /*
        g.addEdge(0,1,2);
        g.addEdge(0,2,4);
        g.addEdge(1,2,1);
        g.addEdge(1,3,1);
        g.addEdge(2,3,2);
        g.addEdge(2,4,1);
        g.addEdge(3,5,3);
        g.addEdge(3,4,3);
        g.addEdge(4,5,3);
        */
       
       /*
            g.addEdge(0,4,4);
            g.addEdge(0,2,6);
            g.addEdge(2,3,2);
            g.addEdge(3,4,4);
            g.addEdge(1,2,7);
            g.addEdge(1,5,9);
            g.addEdge(2,5,3);
        */
    g.addEdge(0, 1, 4);
    g.addEdge(0, 7, 8);
    g.addEdge(1, 2, 8);
    g.addEdge(1, 7, 11);
    g.addEdge(2, 3, 7);
    g.addEdge(2, 8, 2);
    g.addEdge(2, 5, 4);
    g.addEdge(3, 4, 9);
    g.addEdge(3, 5, 14);
    g.addEdge(4, 5, 10);
    g.addEdge(5, 6, 2);
    g.addEdge(6, 7, 1);
    g.addEdge(6, 8, 6);
    g.addEdge(7, 8, 7);
        
        
        
   /* g.addEdge(0, 1, 5);
    g.addEdge(0, 2, 3);
    g.addEdge(1, 3, 6);
    g.addEdge(1, 2, 2);
    g.addEdge(2, 4, 4);
    g.addEdge(2, 5, 2);
    g.addEdge(2, 3, 7);
    g.addEdge(3, 4, -1);
    g.addEdge(4, 5, -2);
     */   
                
 g.shortest(0);
        
        
        
        
        
        
        
        
        /*
	    int n=sc.nextInt();
	    ArrayList<Integer>arr=new ArrayList<Integer>();
	    for(int i=0;i<n;i++)
	     arr.add(sc.nextInt());
	     
	     System.out.println(arr);
	     
	     Ideone obj=new Ideone();
	     obj.build_min_heap(arr);
	     
	     System.out.println("min heap="+arr);
	     
	     //obj.add_min_heap(arr,6);
	     obj.add_min_heap(arr,0);
	     System.out.println("min heap="+arr);
	     obj.add_min_heap(arr,5);
	     System.out.println("min heap="+arr);
	     
	     obj.extract_min(arr);
	     //pass index and updated value to update_key
	    
	     
	     //update the value of a location (loc, value)
	     obj.update_key(arr,2,12);
	     System.out.println("min heap="+arr);
	     obj.update_key(arr,0,1);
	     System.out.println("min heap="+arr);
         
         */
	}
}
