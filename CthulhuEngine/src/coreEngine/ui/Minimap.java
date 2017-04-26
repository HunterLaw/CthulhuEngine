package coreEngine.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import coreEngine.maths.Vector3f;
import coreEngine.objects.TexturedObject2d;

public class Minimap extends TexturedObject2d{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4959764315372145333L;
	BufferedImage temp;
	private Color bordercolor;
	private int borderwidth, borderheight;
	
	public Minimap(int xs, int ys, int widths, int heights) {
		super(new Vector3f(0f,0f,0f),0f,0f);
//		super(xs, ys, widths, heights, false);
	}
	public void setBorder(int width, int height, Color color)
	{
		borderwidth = width;
		borderheight = height;
		bordercolor = color;
	}
	
	public void update(BufferedImage map)
	{
//		temp = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
//		Graphics2D g = (Graphics2D) temp.getGraphics();
//		g.setColor(bordercolor);
//		g.fillRect(0,0,width,height);
//		g.drawImage(map.getScaledInstance((width-(borderwidth*2)), (height-(borderheight*2)), BufferedImage.SCALE_FAST), borderwidth, borderheight, null);
//		g.dispose();
//		texture = temp;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render()
	{
		// TODO Auto-generated method stub
		
	}
}
