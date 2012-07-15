package winglang.variables;

public class character implements Variable{

	String name;
	char data;
	
	public character(String name, char data)
	{
		this.name = name;
		this.data = data;
	}
	public String name()
	{
		return name;
	}
	
	public Object data()
	{
		return (Object)data;
	}
	
	public void setData(String data)
	{
		this.data = data.toCharArray()[0];
	}
	
	public String type()
	{
		return "character";
	}
	
	public string tostring()
	{
		string newstring = new string(name, String.valueOf(data));
		return newstring;
	}
	
	public number tonumber()
	{
		number newnumber = new number(name, Double.valueOf(data));;
		return newnumber;
	}
	
	public character tocharacter()
	{
		return this;
	}
	
	public bool tobool()
	{
		if(this.data == '1')
		{
			bool newbool = new bool(name, true);
			return newbool;
		}
		else if(this.data == '0')
		{
			bool newbool = new bool (name, false);
			return newbool;
		}
		return null;
		
	}
}