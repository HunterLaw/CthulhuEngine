package coreEngine.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import coreEngine.maths.Vector3f;
import coreEngine.objects.TexturedObject2d;

public class HorizontalScrollingBG extends TexturedObject2d implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2755389867732735169L;
	float movespeed = 0.000005f;
	int xoffset ,yoffset;
	int width, height;
	BufferedImage image,bg;
	
	/*
	 * Constructor constructs an image with the information you do or dont have
	 */
	public HorizontalScrollingBG(BufferedImage images,int widths,int heights)
	{
		this(images,widths,heights,0.01f,0,0);
	}
	
	public HorizontalScrollingBG(BufferedImage images,int widths,int heights,float speed)
	{
		this(images,widths,heights,speed,0,0);
	}
	
	public HorizontalScrollingBG(BufferedImage images,int widths,int heights,float speed,int yoffsets)
	{
		this(images,widths,heights,speed,yoffsets,0);
	}
	public HorizontalScrollingBG(BufferedImage images,int widths,int heights,float speed,int yoffsets,int xoffsets)
	{
		super(new Vector3f(Screen.right,0,Screen.back),Screen.width*2f,Screen.height);
		yoffset = yoffsets;
		xoffset = xoffsets;
		bg = images;
		movespeed = speed;
		width = widths*2;
		height = heights;
		image = new BufferedImage(width,height,bg.getType());
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.drawImage(bg,(width/2)+xoffset,yoffset,widths,heights,null);
		g.drawImage(bg,0,yoffset,widths,heights,null);
		g.dispose();
		setTexture(image);
	}
	
	/*
	 * update()
	 * 
	 * Updates the position of the scrolling background image
	 */
	public void update()
	{
		position.x-=movespeed;
		if(position.x <= Screen.left)
		{
			position.x = Screen.right;
		}
	}

	/*
	 * setX()
	 * 
	 * Sets the X coord
	 */
	public void setX(float xs)
	{
		position.x = xs;
	}
	/*
	 * getX()
	 * 
	 * returns the x coord that the image should be drawn at
	 */
	public float getX()
	{
		return position.x;
	}
	/*
	 * getWidth()
	 * 
	 * returns the width of the VerticalScrollingBG
	 */
	public float getWidth()
	{
		return width;
	}
	/*
	 * getHeight()
	 * 
	 * returns the height of the VerticalScrollingBG
	 */
	public float getHeight()
	{
		return height;
	}
	/*
	 * getImage()
	 * 
	 * returns the image of the VerticalScrollingBG
	 */
	public BufferedImage getImage()
	{
		return image;
	}

	@Override
	public void render()
	{
		defaultRender();
	}
}
