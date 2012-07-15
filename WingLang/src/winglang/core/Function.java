package winglang.core;

public class Function {
	String name;
	String lines;
	Interpreter parent;
	
	public Function(String name, String lines, Interpreter parent)
	{
		this.name = name;
		this.lines = lines;
		this.parent = parent;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getLines()
	{
		return lines;
	}
}
