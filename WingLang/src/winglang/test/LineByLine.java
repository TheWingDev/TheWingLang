package winglang.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import winglang.core.Interpreter;

public class LineByLine {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args){
		main1();
	}
	
	public static void main1()
	{
	    Interpreter in = new Interpreter(null);
		while(in.cont == true)
		{
			InputStreamReader inp = new InputStreamReader(System.in) ;
		    BufferedReader br = new BufferedReader(inp);
		    System.out.flush();

		    System.out.println("Enter your command");
		    String str = "";
		    try {
				str = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    in.readFile(str);
		}
			System.out.println("Press enter to restart");
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			main1();
	}

}
