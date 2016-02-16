package org.newton.api.util;

public class Timer {

	private long period;
	private long start;
	
	public Timer(long period)	{
		this.period = period;
		this.start = System.currentTimeMillis();
	}

	public long getElapsed()	{
		return System.currentTimeMillis() - this.start;
	}
	
	public long getRemaining()	{
		return this.period - this.getElapsed();
	}
	
	public boolean isRunning()	{
		return this.getElapsed() <= this.period;
	}
	
	public void reset()	{
		this.start = System.currentTimeMillis();
	}
	
	public void stop()	{
		this.period = 0;
	}
	
	public static String format(long milliSeconds)	{
		long secs = milliSeconds / 1000L;
		return String.format("%02d:%02d:%02d", new Object[]	{
				Long.valueOf(secs / 3600L), Long.valueOf(secs % 3600L/ 60L), Long.valueOf(secs % 60L)
		});
	}
}