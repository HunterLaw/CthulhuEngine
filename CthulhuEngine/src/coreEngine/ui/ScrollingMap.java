package coreEngine.ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import coreEngine.maths.Vector3f;
import coreEngine.movement.Direction;
import coreEngine.objects.NonTexturedObject2d;
import coreEngine.objects.Object2d;
import coreEngine.objects.TexturedObject2d;

public class ScrollingMap extends TexturedObject2d{
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -7406384204090085166L;
	BufferedImage image;
	public ScrollingMap(BufferedImage image, float widthScale, float heightScale) {
		super(new Vector3f(0,0,Screen.back+0.001f),Screen.width*widthScale,Screen.height*heightScale);
		this.image = image;
		setTexture(image);
	}
	
	@Override
	public void update()
	{
		
	}
	
	
	@Override
	public void render()
	{
		setModelMatrix();
		defaultRender();
	}
}
