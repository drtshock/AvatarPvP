package me.shock.avatarpvp;

import java.util.HashMap;

public class EarthTask implements Runnable
{
	
	private Main instance;
	
	EarthListener earthL = new EarthListener(instance);
	HashMap<String, Long> noDamage = earthL.noDamage;

	
	public void RunCheck()
	{
		// Something done here.
	}
	
	
	public void run() 
	{
		RunCheck();
	}

}
