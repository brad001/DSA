
package listOfAllFiles;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class getListOfFiles 
{
     	
	 
	static void listAllFiles(File folder,String fileExt)
	{
		
		for(File fileEntry:folder.listFiles())
		{
			if(fileEntry.isDirectory())//get in
			{
				
				listAllFiles(fileEntry,fileExt);
		
			}
			else//its a file
			{
			  if(fileEntry.exists() && (fileEntry.getName().length())>fileExt.length())
			  {
				String fname=fileEntry.getName();
			
				//check the extension
				if( (fname.substring(fname.length()-fileExt.length(),fname.length())).equals(fileExt)   )
				{
					System.out.println(fname);
				}
				
			  }
			}
		}
	}
	public static void main(String[]args)
	  {
		String fileExt="pdf";
	 	File folder = new File("D:/");
	 	System.out.println(folder.getAbsolutePath());
	 
	 	
	 	//   static FileWriter fw=new FileWriter("D:/prog/output.txt");
		  // static BufferedWriter bw=new BufferedWriter(fw); 
	 	listAllFiles(folder,fileExt);
	 }
}
