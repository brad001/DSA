/*

public Object clone()throws CloneNotSupportedException
    {
      return super.clone();  
    }


*/


//we want to clone the objects of this method=> we have to implement Cloneable interface
//and define the clone()method
class Student18 implements Cloneable
{  
	int rollno;  
	String name;  
	static int count=5;
  
	Student18(int rollno,String name)
	{  
	 this.rollno=rollno;  
	 this.name=name;  
	}  
  
    public Object clone()throws CloneNotSupportedException
    {
      return super.clone();  
    }

    public int getRoll()  
    {
    	return this.rollno;
    }
    public String getName()
    {
    	return this.name;
    }
    public void setRoll(int roll)  
    {
    	this.rollno=roll;
    }
    public void setName(String name)
    {
    	this.name=name;
    }
    public static void change(int b)
    {
    	count=b;
    }
  
    
}  

public class objectCloning
{
	public static void main(String args[])
   {  
		try
		{  
		  Student18 s1=new Student18(101,"amit");  
  
		  Student18 s2=(Student18)s1.clone();  
		
		 Student18 s3=s1;
		 if(s1==s3)
		 	System.out.println("ref equal s1,s3");
		 else
		 	System.out.println("ref unequal s1,s3");

		 if(s1==s2)
		 	System.out.println("ref equal s1,s2");
		 else
		 	System.out.println("ref unequal s1,s2");


		 if((s1.equals(s3)))
		 	System.out.println("content equal s1,s3");
		 else
		 	System.out.println("contents unequal s1,s3");


		 s1.change(15);

		 System.out.println(s1.rollno+" "+s1.name+" "+s1.count);  
		 System.out.println(s2.rollno+" "+s2.name+" "+s2.count); 
		 s2.setName("lokender");
		 System.out.println(s1.rollno+" "+s1.name+" ");  
		 System.out.println(s2.rollno+" "+s2.name); 


		 if(s1==s3)
		 	System.out.println("ref equal s1,s3");
		 else
		 	System.out.println("ref unequal s1,s3");

		 if((s1.equals(s3)))
		 	System.out.println("content equal s1,s3");
		 else
		 	System.out.println("contents unequal s1,s3");
  
        }
        catch(CloneNotSupportedException c)
		{

		}  
  
	}  
}
/*
ref equal s1,s3
ref unequal s1,s2
content equal s1,s3
101 amit 15
101 amit 15
101 amit 
101 lokender
ref equal s1,s3
content equal s1,s3
[Finished in 1.3s]
*/