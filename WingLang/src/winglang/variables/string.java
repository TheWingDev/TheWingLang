package winglang.variables;

public class string implements Variable{

	String name;
	String data;
	
	public string(String name, String data)
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
		this.data = data;
	}
	
	public String type()
	{
		return "string";
	}
	
	public string tostring()
	{
		return this;
	}
	
	public number tonumber()
	{
		number newnumber = new number(name, Double.valueOf(data));;
		return newnumber;
	}
	
	public character tocharacter()
	{
		character newcharacter = new character(name, String.valueOf(data).toCharArray()[0]);;
		return newcharacter;
	}
	
	public bool tobool()
	{
		bool newbool = new bool(name, Boolean.valueOf(data));
		return newbool;
	}
}
