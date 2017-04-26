package coreEngine.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.net.URISyntaxException;

import coreEngine.graphics.Shader;
import coreEngine.maths.Matrix4f;
import coreEngine.maths.Vector3f;
import coreEngine.objects.TexturedObject2d;

public class VerticalScrollingBG extends TexturedObject2d implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1606902216074874863L;
	float movespeed = 0.1f;
	int xoffset,yoffset;
	int width,height;
	BufferedImage image;
	BufferedImage bg;
	
	/*
	 * Constructor constructs an image with the information you do or dont have
	 */
	public VerticalScrollingBG(BufferedImage images,int widths,int heights)
	{
		this(images,widths,heights,0.01f,0,0);
	}
	
	public VerticalScrollingBG(BufferedImage images,int widths,int heights,float speed)
	{
		this(images,widths,heights,speed,0,0);
	}
	
	public VerticalScrollingBG(BufferedImage images,int widths,int heights,float speed,int yoffsets)
	{
		this(images,widths,heights,speed,yoffsets,0);
	}
	public VerticalScrollingBG(BufferedImage images,int widths,int heights,float speed,int yoffsets,int xoffsets)
	{
		super(new Vector3f(0,Screen.top,Screen.back),Screen.width,Screen.height*2f);
//		try
//		{
//			vert =	new File(getClass().getClassLoader().getResource("coreEngine/BaseShaders/Background.vert").toURI());
//			frag = new File(getClass().getClassLoader().getResource("coreEngine/BaseShaders/Background.frag").toURI());
//		}
//		catch (URISyntaxException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		setShaderFiles(vert,frag);
		yoffset = yoffsets;
		xoffset = xoffsets;
		bg = images;
		movespeed = speed;
		width = widths;
		height = heights*2;
		image = new BufferedImage(width,height,bg.getType());
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.drawImage(bg,xoffsets,(height/2)+yoffset,widths-xoffsets,heights,null);
		g.drawImage(bg,xoffsets,0,widths-xoffset,heights,null);
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
		position.y-=movespeed;
		if(position.y <= Screen.bottom)
		{
			position.y = Screen.top;
		}
	}
	/*
	 * setY()
	 * 
	 * Sets the y coord
	 */
	public void setY(float ys)
	{
		position.y = ys;
	}
	/*
	 * getY()
	 * 
	 * returns the y coord that the image should be drawn at
	 */
	public float getY()
	{
		return position.y;
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
//		shader.setUniformMat4f("vw_matrix", Matrix4f.translate(position));
//		shader.setUniformMat4f("vw_matrix", Matrix4f.identity());
		shader().setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		defaultRender();
	}
}
