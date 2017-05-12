import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class FastReader
{
	BufferedReader br;
	public FastReader()
	{
		br=new BufferedReader(new InputStreamReader(System.in));
	}
	String next()
	{
		StringTokenizer st;
		while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
	}
	int nextInt()
	{
		return Integer.parseInt(next());
	}
	int nextLine()
	{
		String s="";
		try
		{
			str=br.readLine();
		}
		catch(IOExceptio e)
		{
			e.printStackTrace();
		}
		return s;
	}
}
public class ScannerInput
{
	public static void main(String[]args)
	{

		//read from console => System.in object

		//Scanner sc1=new Scanner(System.in);

		//read from console fast
		FastReader sc1=new FastReader();
		

		int i=sc1.nextInt();
		System.out.println("you entered int="+i);
		String word=sc1.next();
		System.out.println("you entered word="+word);
		char a=sc1.next().charAt(0);
		System.out.println("you entered character="+a);
		char b=sc1.next().charAt(0);
		System.out.println("you entered character="+b);
		char c=sc1.next().charAt(0);
		System.out.println("you entered character="+c);
		//it will be skipped as last character will be a  new line
		sc1.nextLine();
		String line=sc1.nextLine();
		System.out.println("you entered line="+line);


		//read from file => fileReader object
		System.out.println("---------------------------");
		
	}
}
