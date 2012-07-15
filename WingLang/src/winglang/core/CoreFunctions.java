package winglang.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoreFunctions {
	

	ArguementHandler arguementHandler;
	Interpreter parent;
	
	public CoreFunctions(Interpreter in)
	{
		this.parent = in;
		this.arguementHandler = parent.arguementHandler;
	}
	public void print()
	{
		String output = arguementHandler.getStringArgument(parent.arguements[0]);
		if(output.equals("String Format Error: 909912342.winglangError"))
		{
			parent.error("String format error");
		}
		else
		{
			System.out.print(output);	
		}
		parent.arguements[0] = null;
	}
	
	public void println()
	{
		String output = arguementHandler.getStringArgument(parent.arguements[0]);
		System.out.println(output);
		parent.arguements[0] = null;
	}
	
	public String readLine()
	{
		InputStreamReader inp = new InputStreamReader(System.in) ;
	    BufferedReader br = new BufferedReader(inp);
	    String str = "";
	    try {
			str = br.readLine();
		} catch (IOException e) {
			parent.error("Read error");
		}
	    return str;
	}
	
	public void clear()
	{
		for(int i = 0; i < 100; i++)
		{
			System.out.println();
		}
	}
}
