package test;

import java.util.ArrayList;
import java.util.Random;

import coreEngine.input.Input;
import coreEngine.maths.Matrix4f;
import coreEngine.maths.Vector3f;
import coreEngine.objects.Light;
import coreEngine.objects.NonTexturedObject2d;
import coreEngine.objects.TexturedObject2d;

public class Character extends TexturedObject2d
{
//	Texture t2;
	Random rand = new Random();
	boolean t1 = true;
//	public HealthBar2D hbar;
	int health = 100;
	Light light;
	public Character()
	{
		super(new Vector3f(3f,2f,0.0f),1f,1f);
		System.out.println("here4");
		light =new Light(position,2f,0.25f);

//		new Light()
//		hbar = new HealthBar2D(100,0.2f+(height/2f),1f,0.1f);
		setMovespeed(0.1f);
		
		
		setTexture("Rsc/Test.jpg");
		
//		setTexture("Rsc/Saw.png");
//		t2 = new Texture("Rsc/galagabg.jpg");
//		BufferedImage image = new BufferedImage(100,50,BufferedImage.TYPE_INT_RGB);
//		Graphics g = image.getGraphics();
//		g.setColor(Color.red);
//		g.fillRect(0, 0, 50,50);
//		g.setColor(Color.blue);
//		g.fillRect(50,0,100,50);
//		g.dispose();
//		try
//		{
//			ImageIO.write(image, "jpg", new File("Rsc/Test.jpg"));
//		}
//		catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
//	public void setViewMatrix(Matrix4f vw)
//	{
//		super.setViewMatrix(vw);
////		hbar.setViewMatrix(vw);
//		
//	}
//	
//	public void setModelMatrix()
//	{
//		super.setModelMatrix();
////		hbar.setModelMatrix();
//		
//	}
	
	public void update()
	{
//		System.out.println(width);
		if(rand.nextInt(20) == 10)
		{
			t1 = !t1;
			health-=1;
		}
		if(Input.isKeyDown(Input.Up))
		{
			position.y += movespeed;
		}
		else if(Input.isKeyDown(Input.Down))
		{
			position.y -= movespeed;
		}
		
		if(Input.isKeyDown(Input.Left))
		{
			position.x -= movespeed;
		}
		else if(Input.isKeyDown(Input.Right))
		{
			position.x += movespeed;
		}
		if(Input.isKeyDown(Input.Space))
		{
			position.z += movespeed;
		}
		if(Input.isKeyDown(Input.ShiftL))
		{
			position.z -= movespeed;
		}
		if(Input.isKeyDown(Input.ShiftR))
		{
			position.z = 0;
		}
		light.setPosition(getCenterPosition());
//		System.out.println("X: "+position.x+" Y: "+position.y);
//		System.out.println(toString());
//		System.out.println(vert.getName());
//		hbar.update(position.x, position.y, health);
//		shader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
	}
	@Override
	public void render()
	{
//		hbar.render();
		System.out.println("Rendering Char");
		shader().setUniformMat4f("ml_matrix", Matrix4f.translate(position));
//		setModelMatrix();
		defaultRender();
	}
	
	public ArrayList<NonTexturedObject2d> getObjects(Character chars)
	{
		ArrayList<NonTexturedObject2d> objects = new ArrayList<NonTexturedObject2d>();
//		objects.add(hbar);
		objects.add(chars);
		return objects;
	}
	
	public String toString()
	{
		String s = "";
		s += "Character: (X:"+position.x+",Y:"+position.y+",Z:"+position.z+") Width: "+width+"  Height: "+height;
		return s;
	}
}
