package coreEngine.movement;

import java.io.Serializable;

public class Jump implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4451031923577565657L;
	boolean enabled = false;
	boolean completed = true;
	float initjumpspeed;
	float jumpingspeed;
	float decrement;
	
	public Jump(float jumping, float decrem)
	{
		initjumpspeed = jumping;
		decrement = decrem/60f;
//		System.out.println(decrement);
	}
	
	public void setJump(float jumping, float decrem)
	{
		initjumpspeed = jumping;
		decrement = decrem/60f;
	}
	
	public void setJump(float jumping)
	{
		initjumpspeed = jumping;
	}
	
	public float update(float ys)
	{
//		System.out.println("Before: "+ys);
		if(enabled && !completed)
		{
//			System.out.println(jumpingspeed);
			if(jumpingspeed >= 0)
			{
				jumpingspeed -= decrement;
			}
			else
			{
				completed = true;
//				System.out.println("update dis");
			}
//			System.out.println("After: "+(ys+jumpingspeed));
			return (ys+jumpingspeed);
		}
//		System.out.println("After: "+ys);
		return ys;
	}
	
	public void enable()
	{
		if(!enabled)
		{
			enabled = true;
			completed = false;
			jumpingspeed = initjumpspeed;
		}
//		System.out.println("Enabled");
	}
	
	public void disable()
	{
		if(enabled)
		{
			enabled = false;
			jumpingspeed = 0f;
			completed = true;
		}
//		System.out.println("Disable");
	}
	
	public boolean isCompleted()
	{
		return enabled && completed;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
}
