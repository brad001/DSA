import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.lang.Integer;

class Pair
{
	private int n;
	private String s;
	Pair(int n,String s)
	{
		this.n=n;
		this.s=s;
	}
    public String toString()
    {
    	return this.n+" "+this.s;
    }
    public int getInt()
    {
    	return this.n;
    }
    public String getStr()
    {
    	return this.s;
    }
}
public class arrayList
{
	public static void main(String[]args)
	{
		ArrayList<Pair> li=new ArrayList<Pair>();
		li.add(new Pair(6,"hari"));
		li.add(new Pair(1,"lokender"));
		li.add(new Pair(1,"laxmi"));
		li.add(new Pair(2,"hera"));
		li.add(new Pair(5,"pankaj"));
		li.add(new Pair(10,"abhik"));

		System.out.println(li);

		//this will not work as it has user defined elemets
		//Collections.sort(li);

		//so write your own comparator

		Collections.sort(li,new Comparator<Pair>()
			{
				public int compare(Pair p1,Pair p2)
				{
					//based on ascending of int
					//return (p1.getInt()-p2.getInt());

					//but if you want on 2nd argumnet ascending too then
					//on being equal, compare second arg

					//else first

					/*if( p1.getInt()==p2.getInt())
					{
						//ascending string
						//return p1.getStr().compareTo(p2.getStr());

						//descending string
						return p2.getStr().compareTo(p1.getStr());

					}
					else
					{
						return (p1.getInt()-p2.getInt());

					}*/


					//based on name and then id
					if( (p1.getStr().compareTo(p2.getStr()))==0)
					{
						//id inc
						
						return p1.getInt()-p2.getInt();

						//id dec
						//return p2.getInt()-p1.getInt();
					}
					else
					{
						//ascending
						
						return p1.getStr().compareTo(p2.getStr());
						

						//descending
						//return p2.getStr().compareTo(p1.getStr());
					}


				}
			});
		System.out.println(li);



		System.out.println("-------------new area---------------------");

		ArrayList<Integer>li1=new ArrayList<Integer>();
		for(int i=0;i<10;i++)
			li1.add(i);

		System.out.println(li1);

		System.out.println("Reverse");
		ArrayList<Integer>li2=new ArrayList<Integer>(li1);
		System.out.println(li2);
		Collections.reverse(li2);
		System.out.println(li2);

       
		Collections.sort(li1,new Comparator<Integer>()
			{
				public int compare(Integer p1,Integer p2)
				{
					//descending
					return p2-p1;
				}
			});
		System.out.println(li1);
		Collections.sort(li1);
		System.out.println(li1);
		//System.out.println(rev);
	}
}

/*
[6 hari, 1 lokender, 1 laxmi, 2 hera, 5 pankaj, 10 abhik]
[10 abhik, 6 hari, 2 hera, 1 laxmi, 1 lokender, 5 pankaj]
-------------new area---------------------
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
Reverse
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]*/
