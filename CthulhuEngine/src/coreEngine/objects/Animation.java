package coreEngine.objects;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import coreEngine.graphics.Texture;

public class Animation implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5141362201358443372L;
	ArrayList<Texture> images;
	int place = 0;
	int change = 0;
	int maxchange;
	boolean autoChange = false;
	public Animation()
	{
		autoChange = false;
		init();
	}
	public Animation(int updatestillchange)
	{
		init();
		setUpdates(updatestillchange);
	}
	
	public void init()
	{
		images = new ArrayList<Texture>();
	}
	
	public void addImage(Texture image)
	{	
		images.add(image);
	}
	
	public void restart()
	{
		place = 0;
	}
	
	public Animation nextImage()
	{
		if(place+1 < images.size())
			place++;
		return this;
	}
	
	public Animation prevImage()
	{
		if(place-1 >= 0)
			place--;
		return this;

	}
	
	public Animation selectImage(int p)
	{
		if(p >= 0 && p < images.size())
			place = p; 
		else
			System.err.println("The image index selected was out of bounds!");
		return this;

	}
	
	public void update()
	{
		if(autoChange)
		{
			change++;
			if(change == maxchange)
			{
				place++;
				change = 0;
			}
		}
		if(place+1 != images.size())
		{
			place++;
		}
		else
		{
			place = 0;
		}
	}
	
	public Texture getTexture()
	{
		return images.get(place);
	}
	
	public void setUpdates(int updatestillchange)
	{
		maxchange = updatestillchange;
		autoChange = true;
	}
}
