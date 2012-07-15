package winglang.variables;

public class bool implements Variable{
	
	String name;
	boolean data;
	
	public bool(String name, boolean data)
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
		this.data = Boolean.valueOf(data);
	}
	
	public String type()
	{
		return "bool";
	}
	
	public string tostring()
	{
		string newstring = new string(name, String.valueOf(data));
		return newstring;
	}
	
	public number tonumber()
	{
		if(this.data == true)
		{
			number no = new number(name, 1);
			return no;
		}
		else
		{
			number no = new number(name, 0);
			return no;
		}
	}
	
	public character tocharacter()
	{
		character newcharacter = new character(name, String.valueOf(data).toCharArray()[0]);;
		return newcharacter;
	}
	
	public bool tobool()
	{
		return this;
	}

}