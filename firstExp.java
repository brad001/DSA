import java.util.ArrayList;
import java.io.Serializable;
import java.io.*;


// store all the expense and savings of each student of iiitb
//1. i do not want to store name of colg again n again =>static
//2. i do not want to store their individual colthing,food,grocery=>transient
//, BUT totalExpense => not transient

class Student
{
	String name;
	static String colgName="IITB";
	static int noOfStudents;
	int roll;
	int semester;
	transient int clothingExpense;
	transient int foodExpense;
	transient int groceryExpense;

	int totalSaving;
	int totalExpense;
	public Student(String name,int roll, int semester,int clEx,int foodEx,int groExp)
	{

		this.name=name;
		this.roll=roll;
		this.semester=semester;
		this.clothingExpense=clEx;
		this.foodExpense=foodEx;
		this.groceryExpense=groExp;

		totalSaving=0;
		totalExpense=clothingExpense+foodExpense+groceryExpense;
	}
}

public class firstExp implements Serializable
{
	public static void main(String[]args) throws Exception 
	{
		
		ArrayList<Integer>li=new ArrayList<Integer>();
		ArrayList<Integer>li2=new ArrayList<Integer>();
		li.add(1);
		li.add(2);
		li.add(3);
		li.add(4);
		li.add(5);
		li.add(6);

		li2.add(12);
		li2.add(13);
		li2.add(14);
		li2.add(15);
		li2.add(16);
		li2.add(17);


		FileOutputStream fout=new FileOutputStream("arrayData.txt");
		ObjectOutputStream out=new ObjectOutputStream(fout);
		out.writeObject(li2);
		out.writeObject(li);

		out.flush();


		ObjectInputStream in=new ObjectInputStream(new FileInputStream("arrayData.txt"));
		ArrayList<Integer>li1=(ArrayList<Integer>)in.readObject();
		System.out.println(li1);


		//--------------------------------//
		Student s1=new Student("lokender",75,2,5000,100,203);
		Student s2=new Student("margi",39,2,200,340,2103);

	}
}