package winglang.core;

import java.lang.reflect.Method;

public class ClassHolder {

	String name;
	Class<?> Class;
	Object obj;
	public ClassHolder(String name, Object obj)
	{
		this.name = name;
		this.obj = obj;
		this.Class = obj.getClass();
	}
	
	public String getName()
	{
		return name;
	}
	
	public Object getObj()
	{
		return obj;
	}
	
	public Method getMethod(String method) throws NoSuchMethodException, SecurityException
	{
		return Class.getMethod(method);
	}

}