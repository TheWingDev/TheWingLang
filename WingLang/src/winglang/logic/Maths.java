package winglang.logic;

public class Maths {

	public double calculate(String sum)
	{
		sum = sum.replaceAll(" ", "");
		return Double.valueOf(BODMAS(sum));
	}
	
	public String BODMAS(String sum)
	{
		//Other
		sum = Other(sum);
		//Brackets
		while (sum.contains(")"))
		{
			sum = Brackets(sum);
		}
		//Division
		while (sum.contains("/"))
		{
			sum = Division(sum);
		}
		//Multiplication
		while (sum.contains("*"))
		{
			sum = Multiplication(sum);
		}
		//Addition
		while (sum.contains("+"))
		{	
			sum = Addition(sum);
		}
		//Subtraction
		if(sum.contains("-"))
		{
			sum = Subtraction(sum);
		}
		return sum;
	}
	
	public String Brackets(String sum)
	{
		char[] charArray;
		int i;
		while(sum.contains(")") == true)
		{
			charArray = sum.toCharArray();
			i = 0;
			while (i < charArray.length)
				{
				charArray = sum.toCharArray();
				if(charArray[i] == ')')
				{
					int i2 = i;
					i2--;
					boolean done = false;
					while(i2 > -1 && done == false)
					{
						charArray = sum.toCharArray();
						if(charArray[i2] == ')')
						{
							i = i2;
						}
						if(charArray[i2] == '(')
						{
							String subSum = "";
							int i3 = i2;
							i3++;
							while(i3 < i)
							{
								subSum = subSum + String.valueOf(charArray[i3]);
								i3++;
							}
							String subSum2 = subSum;
							subSum = "(" + subSum + ")";
							sum = sum.replace(subSum, BODMAS(subSum2));
							done = true;
							i = 0;
						}
						i2--;
					}
				}
				i++;
				}
		}
		sum = sum.replace(")", "");
		sum = sum.replace("(", "");
		return sum;
	}
	
	public String Other(String sum)
	{
		while(sum.contains("sin("))
		{
			String subSum = sum.substring(sum.indexOf("sin("));
			boolean done = false;
			int open = 1;
			int close = 0;
			subSum = subSum.replaceFirst("sin\\(", "");
			String subSum2 = subSum;
			int firstClose = subSum2.indexOf(")");
			while(done == false)
			{
				int openIndex = subSum2.indexOf("(");
				int closeIndex = subSum2.indexOf(")");
				if(openIndex == -1)
				{
					openIndex = subSum2.length() - 1;
				}

				if(closeIndex == -1)
				{
					closeIndex = subSum2.length() - 1;
				}
				if(openIndex >= closeIndex || (subSum2.contains(")") && !subSum.contains("(")))
				{
					close++;
					if(subSum2.contains(")"))
					{
						subSum2 = subSum2.replaceFirst("\\)", " ");
					}
					firstClose = closeIndex;
				}
				else if(closeIndex > openIndex || (subSum2.contains("(") && !subSum.contains(")")))
				{
					open++;
					if(subSum2.contains("("))
					{
						subSum2 = subSum2.replaceFirst("\\(", " ");
					}
					firstClose = closeIndex;
					
				}
				if(open == close || !subSum2.contains(")"))
				{
					done = true;
				}
			}
			subSum = subSum.replace(subSum.substring(firstClose), "");
			char[] charArray = subSum.toCharArray();
			int openbr = 0;
			int closebr = 0;
			for(char ch : charArray)
			{
				if(ch == '(')
				{
					openbr++;
				}
				if(ch == ')')
				{
					closebr++;
				}
			}
			if(openbr > closebr)
			{
				int i = 0;
				int diff = openbr - closebr;
				while(i < diff)
				{
					i++;
					subSum = subSum + ")";
				}
			}
			sum = sum.replace("sin(" + subSum + ")", String.valueOf(Math.sin(Double.valueOf(BODMAS(subSum)))));
		}
		
		if(sum.contains("pow("))
		{
			String subSum = sum.substring(sum.indexOf("pow("));
			subSum = subSum.replace(subSum.substring(subSum.indexOf(")")), "");
			subSum = subSum.replace("sin(", "");
			sum = sum.replace("sin(" + subSum + ")", String.valueOf(Math.sin(Double.valueOf(BODMAS(subSum)))));
		}
		return sum;
	}
	
	public String Division(String sum)
	{
		char[] charArray;
		int i;
		i = 0;
		charArray = sum.toCharArray();
		boolean done = true;
		while (i < charArray.length - 1 && done == true)
		{
			charArray = sum.toCharArray();
			if(charArray[i] == '/')
			{
				String storestring = "";
				int num1count = 0;
				int num2count = 0;
				int i2 = i;
				double num1 = 0;
				double num2 = 0;
				int place1 = 0;
				int place2 = 0;
				i2--;
				while(i2 > -1 && num1count ==0)
				{
					int holdno = i2;
					if(!isNumber(charArray[i2]) || charArray[i2] == '-' || i2 == 0)
					{
						if(i2 == 0)
						{
							i2--;
						}
						String string1 = "";
						i2++;
						place1 = i2;
						while(i2 < i)
						{
							if(isNumber(charArray[i2]))
							{
								string1 = string1 + String.valueOf(charArray[i2]);
							}
							i2++;
						}
						num1 = Double.valueOf(string1);
						num1count++;
					}
					i2 = holdno;
					i2--;
				}
				i2 = i;
				i2++;
				while(i2 < charArray.length && num2count == 0)
				{
					int holdno = i2;
					if(!isNumber(charArray[i2]) || i2 == charArray.length - 1)
					{
						String string2 = "";
						int i3 = i;
						place2 = i2;
						i2++;
						while(i3 < i2)
						{
							if(isNumber(charArray[i3]))
							{
								string2 = string2 + String.valueOf(charArray[i3]);
							}
							i3++;
						}
						storestring = string2;
						num2 = Double.valueOf(string2);
						num2count++;
					}
					i2 = holdno;
					i2++;
				}
				double num = num1 / num2;
				String subSum = "";
				if(sum.endsWith(String.valueOf(storestring)))
				{
					place2++;
				}
				while(place1 < place2)
				{
					subSum = subSum + String.valueOf(charArray[place1]);
					place1++;
				}
				sum = sum.replace(subSum, String.valueOf(num));
				charArray = sum.toCharArray();
			}
			i++;
		}
		return sum;
	}
	
	public String Multiplication(String sum)
	{
		char[] charArray;
		int i;
		i = 0;
		charArray = sum.toCharArray();
		boolean done = true;
		while (i < charArray.length - 1 && done == true)
		{
			charArray = sum.toCharArray();
			if(charArray[i] == '*')
			{
				String storestring = "";
				int num1count = 0;
				int num2count = 0;
				int i2 = i;
				double num1 = 0;
				double num2 = 0;
				int place1 = 0;
				int place2 = 0;
				i2--;
				while(i2 > -1 && num1count ==0)
				{
					int holdno = i2;
					if(!isNumber(charArray[i2]) || i2 == 0)
					{
						if(i2 == 0)
						{
							i2--;
						}
						String string1 = "";
						i2++;
						place1 = i2;
						while(i2 < i)
						{
							if(isNumber(charArray[i2]))
							{
								string1 = string1 + String.valueOf(charArray[i2]);
							}
							i2++;
						}
						num1 = Double.valueOf(string1);
						num1count++;
					}
					i2 = holdno;
					i2--;
				}
				i2 = i;
				i2++;
				while(i2 < charArray.length && num2count == 0)
				{
					int holdno = i2;
					if(!isNumber(charArray[i2]) || i2 == charArray.length - 1)
					{
						String string2 = "";
						int i3 = i;
						place2 = i2;
						i2++;
						while(i3 < i2)
						{
							if(isNumber(charArray[i3]))
							{
								string2 = string2 + String.valueOf(charArray[i3]);
							}
							i3++;
						}
						storestring = string2;
						num2 = Double.valueOf(string2);
						num2count++;
					}
					i2 = holdno;
					i2++;
				}
				double num = num1 * num2;
				String subSum = "";
				if(sum.endsWith(String.valueOf(storestring)))
				{
					place2++;
				}
				while(place1 < place2)
				{
					subSum = subSum + String.valueOf(charArray[place1]);
					place1++;
				}
				sum = sum.replace(subSum, String.valueOf(num));
				charArray = sum.toCharArray();
			}
			i++;
		}
		return sum;
	}
	

	public String Addition(String sum)
	{
		char[] charArray;
		int i;
		i = 0;
		charArray = sum.toCharArray();
		boolean done = true;
		while (i < charArray.length - 1 && done == true)
		{
			charArray = sum.toCharArray();
			if(charArray[i] == '+')
			{
				String storestring = "";
				int num1count = 0;
				int num2count = 0;
				int i2 = i;
				double num1 = 0;
				double num2 = 0;
				int place1 = 0;
				int place2 = 0;
				i2--;
				while(i2 > -1 && num1count ==0)
				{
					int holdno = i2;
					if(!isNumber(charArray[i2]) || charArray[i2] == '-' || i2 == 0)
					{
						if(i2 == 0)
						{
							i2--;
						}
						String string1 = "";
						i2++;
						place1 = i2;
						while(i2 < i)
						{
							if(isNumber(charArray[i2]))
							{
								string1 = string1 + String.valueOf(charArray[i2]);
							}
							i2++;
						}
						num1 = Double.valueOf(string1);
						num1count++;
					}
					i2 = holdno;
					i2--;
				}
				i2 = i;
				i2++;
				while(i2 < charArray.length && num2count == 0)
				{
					int holdno = i2;
					if(!isNumber(charArray[i2]) || i2 == charArray.length - 1)
					{
						String string2 = "";
						int i3 = i;
						place2 = i2;
						i2++;
						while(i3 < i2)
						{
							if(isNumber(charArray[i3]))
							{
								string2 = string2 + String.valueOf(charArray[i3]);
							}
							i3++;
						}
						storestring = string2;
						num2 = Double.valueOf(string2);
						num2count++;
					}
					i2 = holdno;
					i2++;
				}
				double num = num1 + num2;
				String subSum = "";
				if(sum.endsWith(String.valueOf(storestring)))
				{
					place2++;
				}
				while(place1 < place2)
				{
					subSum = subSum + String.valueOf(charArray[place1]);
					place1++;
				}
				sum = sum.replace(subSum, String.valueOf(num));
				charArray = sum.toCharArray();
			}
			i++;
		}
		return sum;
	}

	public String Subtraction(String sum)
	{
		char[] charArray;
		int i;
		i = 0;
		charArray = sum.toCharArray();
		boolean done = true;
		while (i < charArray.length - 1 && done == true)
		{
			charArray = sum.toCharArray();
			if(charArray[i] == '-')
			{
				String storestring = "";
				int num1count = 0;
				int num2count = 0;
				int i2 = i;
				double num1 = 0;
				double num2 = 0;
				int place1 = 0;
				int place2 = 0;
				i2--;
				while(i2 > -1 && num1count ==0)
				{
					int holdno = i2;
					if(!isNumberSubtraction(charArray[i2]) || i2 == 0)
					{
						if(i2 == 0)
						{
							i2--;
						}
						String string1 = "";
						i2++;
						place1 = i2;
						while(i2 < i)
						{
							if(isNumber(charArray[i2]))
							{
								string1 = string1 + String.valueOf(charArray[i2]);
							}
							i2++;
						}
						num1 = Double.valueOf(string1);
						num1count++;
					}
					i2 = holdno;
					i2--;
				}
				i2 = i;
				i2++;
				while(i2 < charArray.length && num2count == 0)
				{
					int holdno = i2;
					if(!isNumber(charArray[i2]) || i2 >= charArray.length - 1)
					{
						String string2 = "";
						int i3 = i;
						i3++;
						place2 = i2;
						i2++;
						while(i3 < i2)
						{
							if(isNumber(charArray[i3]))
							{
								string2 = string2 + String.valueOf(charArray[i3]);
							}
							i3++;
						}
						num2 = Double.valueOf(string2);
						num2count++;
						storestring = string2;
					}
					i2 = holdno;
					i2++;
				}
				double num = num1 - num2;
				String subSum = "";
				if(sum.endsWith(String.valueOf(storestring)))
				{
					place2++;
				}
				while(place1 < place2)
				{
					subSum = subSum + String.valueOf(charArray[place1]);
					place1++;
				}
				sum = sum.replace(subSum, String.valueOf(num));
				charArray = sum.toCharArray();
			}
			i++;
		}
		return sum;
	}
	
	public boolean isNumber(char ch)
	{
		if(ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9' || ch == ' ' || ch == '.' || ch == '-')
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isNumberSubtraction(char ch)
	{
		if(ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9' || ch == ' ' || ch == '.')
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
