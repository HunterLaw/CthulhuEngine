package coreEngine.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import coreEngine.graphics.Shader;
import coreEngine.graphics.Texture;
import coreEngine.maths.Matrix4f;
import coreEngine.maths.Vector3f;

public class HealthBar2D extends TexturedObject2d
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2589704241073492424L;
	private Rectangle healthbar;
	private Rectangle health;
	private Color healthcolor;
	

	private int healthsize = 30;
	private double totalhealth;
	private double healthpercent = 1.0;
	private float yoffset;
	
	private Color high = Color.green;
	private Color med = Color.yellow;
	private Color low = Color.red;
	private BufferedImage image;
	
	/*
	 * Constructor creates the rectangle for the bar and then the actual current health
	 */
	public HealthBar2D(double totalhealths,float yoffsets,float w,float h)
	{
		super(new Vector3f(0f,0f,0.1f), w, h);
		if(w != 0)
			width = w;
		if(h != 0)
			height = h;
		healthbar = new Rectangle((int)(100),(int)(20));
		health = new Rectangle(healthbar.width-4,healthbar.height-8);
		healthcolor = high;
		totalhealth = totalhealths;
		yoffset=yoffsets;
		image = new BufferedImage(healthbar.width,healthbar.height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, healthbar.width, healthbar.height);
		g.setColor(healthcolor);
		g.fillRect(2, 4, health.width, health.height);
		g.dispose();
		setTexture(image);
	}
	/*
	 * update()
	 * 
	 * Updates the position of the HealthBar and the current health then sets the color of the health bar based on how much
	 * health is left
	 */
	public void update(float xs,float ys,double currenthealth)
	{
//		System.out.println(toString());
//		System.out.println(vert.getName());
		position.x = xs;
		position.y = ys+yoffset;
		if(currenthealth > 0.0)
		{
			healthcolor = low;
		}
		if(currenthealth > totalhealth*0.25)
		{
			healthcolor = med;
		}
		if(currenthealth > totalhealth*0.60)
		{
			healthcolor = high;
		}
		healthpercent = currenthealth/totalhealth;
		health.setSize((int)((healthbar.width-4)*healthpercent), health.height);
		image = new BufferedImage(healthbar.width,healthbar.height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, healthbar.width, healthbar.height);
		g.setColor(healthcolor);
		g.fillRect(2, 4, health.width, health.height);
		g.dispose();
		setTexture(image);
//		shader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
	}
	/*
	 * setHealthColors
	 * 
	 * Sets the color of high, med, or low health
	 * If sent a null that status color stays the same
	 */
	public void setHealthColors(Color highs,Color meds,Color lows)
	{
		if(highs != null)
			high = highs;
		if(meds != null)
			med = meds;
		if(lows != null)
			low = lows;
	}
	/*
	 * isDead()
	 * 
	 * returns weather or not the healthpercent is below zero
	 */
	public boolean isDead()
	{
		if(healthpercent <= 0)
			return true;
		else 
			return false;
	}
	/*
	 * getHealthBar()
	 * 
	 * returns the healthbar rectangle
	 */
	public Rectangle getHealthBar()
	{
		return healthbar;
	}
	/*
	 * getHealth()
	 * 
	 * returns the health
	 */
	public Rectangle getHealth()
	{
		return health;
	}
	/*
	 * getHealthColor()
	 * 
	 * returns the current health color
	 */
	public Color getHealthColor()
	{
		return healthcolor;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render()
	{
		setModelMatrix();
		defaultRender();
	}
	
	public String toString()
	{
		String s = "";
		s += "HealthBar: (X:"+position.x+",Y:"+position.y+",Z:"+position.z+")";
		return s;
	}
}
