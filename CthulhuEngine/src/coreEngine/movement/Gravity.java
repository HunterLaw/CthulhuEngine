package coreEngine.movement;

import java.io.Serializable;

public class Gravity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4026542045537530763L;
	boolean enabled = false;
	float maxfallingspeed;
	float fallingspeed;
	float increment;
	public Gravity(float falling, float increm)
	{
		maxfallingspeed = falling;
		increment = increm/60f;
	}
	
	public void setGravity(float falling, float increm)
	{
		maxfallingspeed = falling;
		increment = increm/60f;
	}
	
	public void setGravity(float falling)
	{
		maxfallingspeed = falling;
	}
	
	public float update(float ys)
	{
//		System.out.println(ys);
		if(enabled)
		{
			if(fallingspeed < maxfallingspeed)
			{
				fallingspeed += increment;
			}
			else
			{
				fallingspeed = maxfallingspeed;
			}
//			System.out.println(ys+fallingspeed);
			return ys -= fallingspeed;
		}
		return ys;
	}
	
	public void enable()
	{
		enabled = true;
	}
	
	public void disable()
	{
		enabled = false;
		fallingspeed = 0f;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
}
