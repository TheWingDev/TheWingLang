package winglang.core;

import winglang.logic.Maths;

public class ArguementHandler {
	
	Maths mathHandler = new Maths();
	
	public String getStringArgument(Object arg)
	{
			String input = (String)arg;
			char[] charArray;
			int i;
			String output = "";
			boolean done = false;
			while(input.contains("\"") == true && done == false)
			{
				charArray = input.toCharArray();
				i = 0;
				while (i < charArray.length && done == false)
					{
					charArray = input.toCharArray();
					if(charArray[i] == '"')
					{
						int i2 = i;
						i2++;
						while(i2 < charArray.length && done == false)
						{
							charArray = input.toCharArray();
							if(charArray[i2] == '"')
							{
								String subString = "";
								int i3 = i;
								i3++;
								while(i3 < i2)
								{
									subString = subString + String.valueOf(charArray[i3]);
									i3++;
								}
								output = subString;
								done = true;
							}
							i2++;
						}
					}
					i++;
					}
			}
			if(output.equals("") && input.endsWith(".v") && !input.endsWith("..v"))
			{
				output = input.replace(".v", "");
			}
			else if(output.equals("") && !input.equals(""))
			{
				output = "String Format Error: 909912342.winglangError";
			}
			return output;
		}
	
	public char getCharArgument(Object arg)
	{
			String input = (String)arg;
			char[] charArray;
			int i;
			String output = "";
			boolean done = false;
			while(input.contains("'") == true && done == false)
			{
				charArray = input.toCharArray();
				i = 0;
				while (i < charArray.length && done == false)
					{
					charArray = input.toCharArray();
					if(charArray[i] == '\'')
					{
						int i2 = i;
						i2++;
						while(i2 < charArray.length && done == false)
						{
							charArray = input.toCharArray();
							if(charArray[i2] == '\'')
							{
								String subString = "";
								int i3 = i;
								i3++;
								while(i3 < i2)
								{
									subString = subString + String.valueOf(charArray[i3]);
									i3++;
								}
								output = subString;
								done = true;
							}
							i2++;
						}
					}
					i++;
					}
			}
			if(output.equals("") && input.endsWith(".v") && !input.endsWith("..v"))
			{
				output = input.replace(".v", "");
			}
			char outputChar = output.toCharArray()[0];
			return outputChar;
	}

	public double getNumberArgument(Object arg)
	{
		return mathHandler.calculate(String.valueOf(arg));
	}
}
