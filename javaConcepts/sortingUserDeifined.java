package sorting;

import java.util.ArrayList;
import java.util.*;





class Student implements Comparable<Student>
{
	 private int roll;
	 private String name;
	 private int age;
	 private static int inv;
	Student()
	{
		
	}
	public Student(int roll,String name,int age)
	{
		this.roll=roll;
		this.name=name;
		this.age=age;
	}
	public int getInv()
	{
		return inv;
	}
	//to print the obejct
	public String toString()
	{
		return this.roll+" "+this.name+" "+this.age;
	}
	public String getName()
	{
		return this.name;
	}
	//natural ordering always on this function
		public int compareTo(Student s)
		{
			System.out.println("called me compareTo()");
			//decreasing order of age
			//return s.name.compareTo(this.name);
			//increasing order
			 return this.name.compareTo(s.name);
		}
	
	
	public static Comparator<Student> sortByRoll=new Comparator<Student>()
	{
		
		public int compare(Student s1,Student s2)
		{
			//ascending order by Roll
		    if( (s1.roll-s2.roll)>0)
		    	inv++;
		    System.out.println("inside "+inv);
			return (s1.roll-s2.roll);
			
			//descending order
			//return (s2.roll-s1.roll);
		}
	};
	public static Comparator<Student> sortByName=new Comparator<Student>()
    {
		 public int compare(Student s1,Student s2)
		 {
			 //ascending order
			 System.out.println("inside "+inv);
			 return (s1.name.compareTo(s2.name));
			 
			 //descending order
			 //return (s2.name.compareTo(s1.name));
		 }
	  	
	};
	public static Comparator<Student> sortByAge=new Comparator<Student>()
    {
		 public int compare(Student s1,Student s2)
		 {
			 //ascending order
			 System.out.println("inside "+inv);
			 return (s1.age-s2.age);
			 
			 //descending order
			 //return (s2.age-s1.age);
		 }
	  	
	};
	public static Comparator<Student> sortByAgeName=new Comparator<Student>()
	{
		 public int compare(Student s1,Student s2)
		 {
			 //first on roll
			 //if equal only then name basis
			 int val=(s1.age-s2.age);
			 
			 if(val==0)
			 {
				 //same age=>smaller name first , so greater name last
				 val=s1.name.compareTo(s2.name);
				 //returns 1 if s1>s2
			 }
			 return val;
		 }
	};
	
	
	
}




public class sortingUserDefined 
{
	static void prL()
	{
		System.out.println();
	}
	static void prL(String s)
	{
		System.out.println(s);
	}
	static void prL(Object obj)
	{
		prL("prL(obj)");
		System.out.println(obj);
	}
	
	
	public static void main(String[]args)
	{
	   
		  ArrayList<Student>aS=new ArrayList<Student>();
		  aS.add(new Student(98,"lokender",25));
		  aS.add(new Student(4,"margi",21));
		  aS.add(new Student(9,"mayank",22));
		  aS.add(new Student(10,"aakansha",20));
		  aS.add(new Student(3,"hello",21));
		  aS.add(new Student(12,"subham",23));
		  aS.add(new Student(33,"abhilash",24));
		  aS.add(new Student(1,"hari",29));
		  //aS.add(new Student(33,"charu",24));
		  
		  prL("contents original list are");
		  prL(aS);
		  
		  Collections.reverse(aS);
		  System.out.println("after reversing");
		  prL(aS);
		  prL("after sorting on roll");
		  Collections.sort(aS,Student.sortByRoll);
		  //prL(aS);
		  Iterator iter=aS.iterator();
		  while(iter.hasNext())
		  {
			  Object obj=iter.next();
			  System.out.println(obj);
		  }
		  
		  //natural ordering
		  //using comparable
		  System.out.println("maximum roll object ="+Collections.max(aS));
		  System.out.println("minimum roll object ="+Collections.min(aS));
		  Collections.sort(aS);
		  prL("after sorting natural ordering name");
		  prL(aS);
		  //binary search (we can only pass the key of type which compareTo has used in  comparable)
		  System.out.println("index of the key passed="+Collections.binarySearch(aS,new Student(1,"buffalo",56)));
		  //we cannot directly set at the inndex, bcoz if value is not present , then we would get -ve of that value index
		  //aS.set(Collections.binarySearch(aS, new Student(1,"bufaalo",56)),new Student(1,"buffalo",56));
		 
		  int index=Collections.binarySearch(aS, new Student(1,"bufaalo",56));
		 if(index>=0)
		 {
			 System.out.println("already present");
		 }
		 else
		 {
			 //if not present returns -ve number one extra;
			 index=(-1)*index-1;
			 //set this new item to the index position
			 //aS.set(index,new Student(1,"buffalo",56));
			 
			 //or else add this to the list if not present
			 //aS.add(e)
		 }
		 
		 System.out.println("after insertion if any list");
		 prL();
		 
		 
		  iter=aS.iterator();
		  while(iter.hasNext())
		  {
			  Object obj=iter.next();
			  System.out.println(obj);
		  }
		  
		  
		  System.out.println("maximum roll object ="+Collections.max(aS, Student.sortByRoll));
		  System.out.println("minimum roll object ="+Collections.min(aS, Student.sortByRoll));
		  
		  System.out.println("new max name is "+Collections.max(aS, new Comparator<Student>(){public int compare(Student s1,Student s2)
			  {
			    //name sorting
			    return s1.getName().compareTo(s2.getName());
			  }}));
		  prL("the no. of inversions are "+(new Student().getInv()));
		  
		  
		  prL("after sorting on age");
		  Collections.sort(aS,Student.sortByAge);
		  prL(aS);
		  System.out.println("index returned by bsearch on age="+Collections.binarySearch(aS,new Student(23,"lokender",21),Student.sortByAge));
		  iter=aS.listIterator();
		  while(iter.hasNext())
		  {
			  Object obj=iter.next();
			  System.out.println(obj);
		  }
		  
		  
		  
		  prL("after sorting on name");
		  Collections.sort(aS,Student.sortByName);
		  prL(aS);
		  iter=aS.listIterator();
		  while(iter.hasNext())
		  {
			  Object obj=iter.next();
			  System.out.println(obj);
		  }
		  
		  prL("first sort on age and then name");
		  Collections.sort(aS,Student.sortByAgeName);
		  prL(aS);
		  
		  
		  prL(aS);
		  
		  
		 // Iterator iter=aS.iterator();
		  
		  
		  
		  ArrayList<Integer> ai=new ArrayList<Integer>();
		  ai.add(5);
		  ai.add(45);
		  ai.add(1);
		  ai.add(54);
		  ai.add(65);
		  ai.add(12);
		  
		  prL("ai list has");
		  prL(ai);
		  Collections.sort(ai);
		  
		  prL("after sorting");
		  prL(ai);
		  prL("after reversing");
		  Collections.reverse(ai);
		  prL(ai);
		  
		  
		  
	}
}
