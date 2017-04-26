package coreEngine.ui;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import coreEngine.maths.Matrix4f;
import coreEngine.objects.Camera;
import coreEngine.objects.Light;
import coreEngine.objects.NonTexturedObject2d;

public class Renderer2D implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1680824798029018860L;
	BufferedImage image;
	ArrayList<NonTexturedObject2d> objects;
	Minimap mini;
	Object bg;
	int bgwidth;
	int bgheight;
	int width;
	int height;
	int imageType;
	
	/*
	 * Constructor sets the properties of the BufferedImage and initializes its object array
	 */
	public Renderer2D(int widths, int heights,int imageTypes)
	{
		this();
		width = widths;
		height = heights;
		imageType = imageTypes;
	}
	
	public Renderer2D()
	{
		objects = new ArrayList<NonTexturedObject2d>();
	}
	/*
	 * setBgObject()
	 * 
	 * Use this if you want a static background image and you dont want to scale the image
	 */
	public void setBgObject(Object object)
	{
		bg = object;
	}
	
	public void setMinimap(Minimap map)
	{
		mini = map;
	}
	
	public void setObjectArray(ArrayList<NonTexturedObject2d> objectss)
	{
		objects = objectss;
	}
	/*
	 * setBgObject()
	 * 
	 * Use this if you want to scale a static background image or you want to use a VerticalScrollingBG
	 */
	public void setBgObject(Object object,int widths,int heights)
	{
		if(object instanceof BufferedImage)
		{
			bg = object;
			bgwidth = widths;
			bgheight = heights;
		}
		else if(object instanceof VerticalScrollingBG)
		{
			bg = object;
			bgwidth = widths;
			bgheight = heights;
		}
		else if(object instanceof HorizontalScrollingBG)
		{
			bg = object;
			bgwidth = widths;
			bgheight = heights;
		}
		else if(object instanceof ScrollingMap)
		{
			bg = object;
			bgwidth = widths;
			bgheight = heights;
		}
	}
	/*
	 * resize()
	 * 
	 * Sets the new width and height of the BufferedImage
	 * 
	 * WARNING: Will be used to scale games but has not been tested
	 */
	public void resize(int widths,int heights)
	{
		width =widths;
		height = heights;
	}
	/*
	 * render()
	 * 
	 * Renders the BufferedImage and then returns that image
	 */
	public void render(Camera cam)
	{
//		System.out.println("\nRender");
		Matrix4f vw = cam.getVWMatrix();
		if(objects != null || objects.size() != 0)
		{
			for(NonTexturedObject2d object: objects)
			{
//				System.out.println(object.toString());
//				object.setModelMatrix();
				object.setViewMatrix(vw);
				if(object.isLightEnabled())
				{
					Light.setLightsToShader(object.shader());
				}
				object.render();
			}
		}
	}
	
	
	public ArrayList<NonTexturedObject2d> getObjects()
	{
		return objects;
	}
}
