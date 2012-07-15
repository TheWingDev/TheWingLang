package winglang.variables;

public class number implements Variable{
	
	String name;
	double data;
	
	public number(String name, double data)
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
		this.data = Double.valueOf(data);
	}
	
	public String type()
	{
		return "number";
	}
	
	public string tostring()
	{
		string newstring = new string(name, String.valueOf(data));
		return newstring;
	}
	
	public number tonumber()
	{
		return this;
	}
	
	public character tocharacter()
	{
		character newcharacter = new character(name, String.valueOf(data).toCharArray()[0]);;
		return newcharacter;
	}
	
	public bool tobool() {
		if(this.data == 1)
		{
			bool newbool = new bool(name, true);
			return newbool;
		}
		else
		{
			bool newbool = new bool(name, false);
			return newbool;
		}
	}

}
