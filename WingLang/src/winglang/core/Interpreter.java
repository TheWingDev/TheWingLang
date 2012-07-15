package winglang.core;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import winglang.logic.Maths;
import winglang.variables.Variable;
import winglang.variables.bool;
import winglang.variables.character;
import winglang.variables.number;
import winglang.variables.string;

public class Interpreter {
	
	Object output;
	Maths mathHandler = new Maths();
	ArguementHandler arguementHandler = new ArguementHandler();
	public boolean cont = true;
	int lineno = 1;
	ArrayList<ClassHolder> classes = new ArrayList<ClassHolder>();
	Object[] arguements = new Object[15];
	char[] charArray;
	CoreFunctions core = new CoreFunctions(this);
	Class<?> coreClass = core.getClass();
	ArrayList<Variable> variables = new ArrayList<Variable>();
	ArrayList<Function> functions = new ArrayList<Function>();
	//private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	//ScheduledTask schedule = new ScheduledTask(this);
	
	public Interpreter(Object output)
	{
		this.output = output;
		setUpClasses();
		//scheduler.scheduleAtFixedRate(schedule, 8, 8, TimeUnit.SECONDS);
	}
	
	
	public void setUpClasses()
	{
		classes.add(new ClassHolder("string", new string("", "")));
		classes.add(new ClassHolder("number", new number("", 0)));
		classes.add(new ClassHolder("character", new character("", ' ')));
	}
		
	public void setOutput(Object output)
	{
		this.output = output;
	}
	
	public void error(String reason)
	{
		cont = false;
		System.out.println("Stopping. " + reason + " at line " + lineno);
	}
	
	public void readFile(String file)
	{
		String[] fileArray = file.split(";");
		lineno = 1;
		if(!file.endsWith(";"))
		{
			fileArray[fileArray.length -1] = "";
		}
		while(lineno <= fileArray.length && cont == true)
		{
			String line = fileArray[lineno - 1];
			line = line.trim();
			if(line!= "")
			{
				handleLineType(line);
			}
			lineno++;
		}
	}
	
	public String removeAndStoreFunctions(String file)
	{
		
		return null;
	}
	
	public void handleLineType(String line)
	{
			int i = 0;
			char[] charArray = line.toCharArray();
			boolean done = false;
			while(i < line.length() && done == false)
			{
				if(charArray[i] == '=')
				{
					//Is variable declaration / modification
					handleVariable(line);
					done = true;
				}
				else if (charArray[i] == '(')
				{
					//Is function calling
					execute(line);
					done = true;
				}
				i++;
			}
			if(done == false)
			{
				error("Unrecognised code");
			}
	}
	
	public void handleVariable(String line)
	{
		String[] components = line.split("=");
		if(components.length > 2)
		{
			error("Invalid variable declaration / modification");
		}
		else
		{
			String[] variable = components[0].split(" ");
			int i = 0;
			while(i < variable.length)
			{
				variable[i] = variable[i].replace(" ", "");
				i++;
			}
			if(variable[0].equals("string") || variable[0].equals("character") || variable[0].equals("number") || variable[0].equals("bool"))
			{
				declare(components);
			}
			else
			{		
				modify(components);
			}
		}
	}
	
	public void declare(String[] components)
	{
		String[] variable = components[0].split(" ");
		String type = variable[0];
		String name = variable[1];
		String data = components[1];
		type = type.trim();
		name = name.trim();
		data = data.trim();
		boolean exists = false;
		for(Variable var : variables)
		{
			if(var.name().equals(name))
			{
				exists = true;
				error("Variable already exists");
			}
		}
		if(exists == false)
		{
			if(type.equals("string"))
			{
				variables.add(new string(name, arguementHandler.getStringArgument(data)));
			}
			else if(type.equals("character"))
			{
				data = data.replace(" ", "");
			if (data == "")
			{
				data = " ";
			}
			variables.add(new character(name, data.toCharArray()[0]));
			}
			else if(type.equals("number"))
			{
				data.replace(" ", "");
				variables.add(new number(name, mathHandler.calculate(data)));	
			}
			else if(type.equals("bool"))
			{
				data.replace(" ", "");
				if(data.equals("true"))
				{
					variables.add(new bool(name, true));
				}
				else if(data.equals("false"))
				{
					variables.add(new bool(name, false));
				}
				else
				{
					error("Invalid boolean format");
				}
			}
		}
	}
	
	public void modify(String[] components)
	{
		String name = components[0];
		name = name.replaceAll(" ", "");
		name = name.trim();
		String data = components[1];
		data = data.trim();
		if(data.startsWith("\""))
		{
			data = arguementHandler.getStringArgument(data);	
		}
		else if(data.contains("("))
		for(Variable var : variables)
		{
			if(var.name().equals(name))
			{
				if(var.type() == "number")
				{
					var.setData(String.valueOf(mathHandler.calculate(data)));
				}
				else
				{
					var.setData(data);
				}
			}
		}
	}
	
	public void execute(String line)
	{
		java.lang.reflect.Method method = null;
		boolean methodDone = false;
		//Split into command and arguments
		int i = 0;
		boolean done = false;
		charArray = line.toCharArray();
		while(i < line.length() && done == false)
		{
			if(charArray[i] == '(')
			{
				done = true;
			}
			i++;
		}
		int i2 = 0;
		String methodString = "";
		while(i2 < i - 1)
		{
			methodString = methodString + charArray[i2];
			i2++;
		}
		methodString = methodString.replace(" ", "");
		methodString = methodString.trim();
		i2 = i;
		String argString = "";
		while(i2 < line.length() - 1)
		{
			argString = argString + charArray[i2];
			i2++;
		}
		String[] args = argString.split(",");
		if(args.length > 15)
		{
			error("Too many arguements (Max 15)");
		}
		int argCounter = 0;
		while(argCounter < args.length)
		{
			args[argCounter] = args[argCounter].trim();
			arguements[argCounter] = args[argCounter];
			argCounter++;
		}
		argCounter = 0;
		while(argCounter < arguements.length)
		{
			Object arguement = arguements[argCounter];
			for(Variable var : variables)
			{
				if(var.name().equals((String)arguement))
				{
					arguement = var.data();
					arguement = arguement + ".v";
					arguements[argCounter] = arguement;
				}
			}
			argCounter++;
		}
		Object obj = core;
		if(methodString.contains("."))
		{
			String methodString2 = methodString.split("\\.")[1];
			String varName = methodString.split("\\.")[0];
			for(ClassHolder Class : classes)
			{
				if(varName.equals(Class.getName()))
				{
					try {
						method = Class.getMethod(methodString2);
						obj = Class.getObj();
					} catch (SecurityException e) {
						error("Security Exception");
					} catch (NoSuchMethodException e) {
						error("Method not found");
					}
					methodDone = true;
				}
			}
			if(methodDone == false)
			{
				for(Variable var : variables)
				{
					if(varName.equals(var.name()))
					{
						try {
							method = var.getClass().getMethod(methodString2);
							obj = var;
						} catch (SecurityException e) {
							error("Security Exception");
						} catch (NoSuchMethodException e) {
							error("Method not found");
						}
						methodDone = true;
					}
				}
			}
		}
		if(methodDone == false)
		{
			try {
				method = coreClass.getMethod(methodString);
			} catch (SecurityException e) {
				error("Security Exception");
			} catch (NoSuchMethodException e) {
				error("Method not found");
			}
		}
		if(cont == true)
		{
			try {
				Object[] arg = new Object[0];
				method.invoke(obj, arg);
			} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
				error("Illegal Arguments");
				e.printStackTrace();
			}
		}
	}
}