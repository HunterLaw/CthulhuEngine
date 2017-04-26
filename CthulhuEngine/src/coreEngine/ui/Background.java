package coreEngine.ui;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import coreEngine.maths.Vector3f;
import coreEngine.objects.TexturedObject2d;

public class Background extends TexturedObject2d implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2755389867732735169L;
	BufferedImage bg;
	
	
	public Background(BufferedImage images)
	{
		this(images,Screen.width,Screen.height);
		
	}
	
	public Background(Vector3f pos,BufferedImage images)
	{
		this(pos,images,1f,1f);
	}
	
	
	/*
	 * Constructor constructs an image with the information you do or dont have
	 */
	public Background(BufferedImage images,float widths,float heights)
	{
		this(new Vector3f(0,0,Screen.back), images, widths/images.getWidth(),heights/images.getHeight());
	}
	
	public Background(Vector3f pos, BufferedImage images, float widthScale, float heightScale)
	{
		super(pos,Screen.width*widthScale,Screen.height*heightScale);
		bg = images;
		setTexture(bg);
		
	}
	/*
	 * update()
	 * 
	 * Updates the position of the scrolling background image
	 */
	public void update()
	{
	}
	/*
	 * getImage()
	 * 
	 * returns the image of the VerticalScrollingBG
	 */
	public BufferedImage getImage()
	{
		return bg;
	}

	@Override
	public void render()
	{
		setModelMatrix();
		defaultRender();
	}
}
