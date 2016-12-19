package files;
import java.io.File;
import java.io.*;

public class readAllFilesFolders 
{
	
   static void listAllFiles(File folder)
   {
	   //display all files if file
	   //else get in folder
	   
	   //1. identify is folder or file
	   try
	   {
	   FileWriter fw=new FileWriter("D:/prog/output.txt");
	   BufferedWriter bw=new BufferedWriter(fw); 
	   
	   
	   for(File fileEntry:folder.listFiles())
	   {
		   if(fileEntry.isDirectory())
		   {
			   //pass this folder name to same fun
			   
			   //listAllFiles(fileEntry);
			   
		   }
		   else
		   {
		
			   if(fileEntry.exists())
			   {
				   
				 String str=fileEntry.getName();
		         String file_ext="txt";
				// File f1=new File(str);
		 
				 if( (str.substring(str.length()-file_ext.length(), str.length()).equals(file_ext)))
				 {
					 
					 System.out.println(str);
					FileReader fr=new FileReader("D:/prog/files/"+str);
					BufferedReader br=new BufferedReader(fr);
			        int i;		
					char[]cbuf=new char[20];
					String s;
					
					bw.newLine();
					bw.append("-----------------------------this is a new file--------------------------------------");
					bw.newLine();
					/*
					 while((i=fr.read(cbuf))!=-1)
					{
						
						bw.append(new String(cbuf));
					}
					*/
					while((i=br.read())!=-1)
					bw.append((char)i);
					 
					 fr.close();
					 br.close();
				 }
				 
				 //System.out.println(fileEntry.getName());  
				 
			   }
		   }
		
		   
	   }//for list of files
	   
	   bw.close();
	   fw.close();
	   }//try end
	   catch(Exception e)
	   {
		   System.out.println("eroor in handling files");
	   }
	   
   }
  public static void main(String[]args)
  {
	
 	File folder = new File("D:/prog/files");
 	System.out.println(folder.getAbsolutePath());
 	
 	listAllFiles(folder);
 }
}
