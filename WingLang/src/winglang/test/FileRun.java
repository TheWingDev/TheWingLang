package winglang.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import winglang.core.Interpreter;

public class FileRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File directory = new File(".");
		String curr = directory.getAbsolutePath();
		curr = curr.substring(0, curr.length() - 1);
		while (true)
		{
			Interpreter in = new Interpreter(null);
			System.out.println("Enter the name of the file you wish to execute");
			InputStreamReader inp = new InputStreamReader(System.in) ;
		    BufferedReader br = new BufferedReader(inp);
		    System.out.flush();
		    String str = "";
		    try {
				str = curr + br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    File f = new File(str);
		    FileReader fr = null;
		    boolean cont = false;
		    try {
				fr = new FileReader(f);
		    	cont = true;
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
		    if(cont == true)
		    {
		    	BufferedReader br2 = new BufferedReader(fr);
		    	StringBuilder sb = new StringBuilder();
		    	String line = null;
		    	try {
					line = br2.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	while(line!= null)
		    	{
		    		sb.append(line);
		    		sb.append("\n");
		    		try {
						line = br2.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    	String file = sb.toString();
		    	try {
					br2.close();
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	in.readFile(file);
		    }
		    
		}
	}

}
