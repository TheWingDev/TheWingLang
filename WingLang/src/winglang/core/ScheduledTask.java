package winglang.core;

public class ScheduledTask implements Runnable{

	Interpreter in;
	public ScheduledTask(Interpreter in)
	{
		this.in = in;
	}
	@Override
	public void run() {
	}

}
