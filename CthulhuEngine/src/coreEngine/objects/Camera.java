package coreEngine.objects;

import coreEngine.input.Input;
import coreEngine.maths.Matrix4f;
import coreEngine.maths.Vector3f;
import coreEngine.ui.Screen;

public class Camera
{
	Vector3f position;
	Matrix4f vw_matrix;
	float movespeed = 0.1f;
	float[] limits = new float[6];
	
	public Camera()
	{
		this(new Vector3f(0f,0f,0f));
	}
	
	public Camera(Vector3f position)
	{
		this.position = position;
		this.vw_matrix = Matrix4f.translate(position);
		setLimits();
	}

	public void setLimits(float[] lim)
	{
		limits = lim;
	}
	
	public void setLimits()
	{
		limits[0] = Screen.left;
		limits[1] = Screen.right;
		limits[2] = Screen.bottom;
		limits[3] = Screen.top;
		limits[4] = Screen.back;
		limits[5] = Screen.front;
	}
	
	public void update(Vector3f charpos)
	{
		float[] screen = new float[6];
		screen[0] = charpos.x - Screen.centerx;
		screen[1] = charpos.x + Screen.centerx;
		screen[2] = charpos.y - Screen.centery;
		screen[3] = charpos.y + Screen.centery;
//		screen[4] = charpos.z + Screen.back;
//		screen[5] = charpos.z + Screen.front;
//		if(Input.isKeyDown(Input.D))
//		{
//			position.x += movespeed;
//		}
//		else if(Input.isKeyDown(Input.A))
//		{
//			position.x -= movespeed;
//		}
//		if(Input.isKeyDown(Input.W))
//		{
//			position.y += movespeed;
//		}
//		else if(Input.isKeyDown(Input.S))
//		{
//			position.y -= movespeed;
//		}
//		System.out.println("checking limits");
//		System.out.println(toString());
//		System.out.println("Limits: "+limits[0]+":"+limits[1]);
//		System.out.println("Character: "+screen[0]+":"+screen[1]);
		
//		position.x = charpos.x - Screen.centerx;
//		if(position.x < limits[0])
//		{
//			position.x = limits[0];
//		}
//		else if(position.x > limits[1])
//		{
//			position.x = limits[1]-Screen.width;
//		}
		
		if(screen[0] > limits[0] && screen[1]< limits[1] )
		{
			position.x = charpos.x-Screen.centerx;
		}
		else
		{
			
			if(screen[0] > limits[0])
			{
				position.x = limits[0];
//				System.out.println("here");
			}
			if(screen[1] > limits[1])
			{
//				System.out.println("here2: "+(limits[1]/2f));

				position.x = limits[1]-Screen.width;
			}
		}
		if(screen[2] > limits[2] && screen[3] < limits[3] )
		{
			position.y = charpos.y-Screen.centery;
		}
		else
		{
			if(screen[2] < limits[2])
			{
				position.y = limits[2];
			}
			if(screen[3] > limits[3])
			{
				position.y = limits[3]-Screen.height;
			}
		}
//		if(screen[4] > limits[4] && screen[5] < limits[5] )
//		{
//			position.z = charpos.z;
//		}
//		else
//		{
//			if(screen[4] < limits[4])
//			{
//				position.z = limits[4]+(Screen.depth/2f);
//			}
//			if(screen[5] > limits[5])
//			{
//				position.z = limits[5]-(Screen.depth/2f);
//			}
//		}
		vw_matrix = Matrix4f.translate(getNegativeCoords());
//		System.out.println(toString());
//		System.out.println();
	}
	
	public Vector3f getCenterWorld()
	{
		return new Vector3f(limits[1]/2f,limits[3]/2f,0);
	} 
	
	
	public Vector3f getNegativeCoords()
	{
		return new Vector3f(-position.x, -position.y,-position.z);
	}
	
	public Matrix4f getVWMatrix()
	{
		return vw_matrix;
	}
	
	public String toString()
	{
		String s = "";
		s += "Camera: (X:"+position.x+",Y:"+position.y+",Z:"+position.z+")";
		s += "\t("+limits[0]+","+limits[1]+","+limits[2]+","+limits[3]+","+limits[4]+","+limits[5]+")";
		return s;
	}
}
