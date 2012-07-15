package winglang.variables;

public interface Variable {
	public String name();
	public Object data();
	public void setData(String data);
	public String type();
	public string tostring();
	public number tonumber();
	public character tocharacter();
	public bool tobool();
}
