package coreEngine.objects;

import java.util.ArrayList;

import coreEngine.graphics.Shader;
import coreEngine.maths.Vector3f;
/*
 * TODO: Fix Lighting. Objects not being affected by light correctly!
 */
public class Light
{
	static ArrayList<Light> lights;
	Vector3f position = new Vector3f();
	float size;
	float lowLightValue = 0f;
	public Light(Vector3f pos, float size, float lowLightValue)
	{
		this();
		this.position = pos;
		this.size = size;
		this.lowLightValue = lowLightValue;
		addLight(this);
	}
	
	public Light()
	{
		if(lights == null) lights = new ArrayList<Light>();
	}
	
	public Light(Vector3f pos, float size)
	{
		this(pos,size,0.001f);
	}
	
	public Light(Vector3f pos)
	{
		this(pos,2f,0.001f);
	}
	
	public void setPosition(Vector3f pos)
	{
		position = pos;
	}
	
	public static void addLight(Light l)
	{
		lights.add(l);
	}
	
	public void removeMe()
	{
		lights.remove(this);
	}
	
	public static void removeLight(Light l)
	{
		lights.remove(l);
	}

	public static int getLightSize()
	{
		return lights.size();
	}
	
	public static void setLightsToShader(Shader shader)
	{
		String name, pos = ".pos",size = ".size", llv = ".lowLightValue";
		Light l;
		for(int i = 0;i<lights.size();i++)
		{
//			System.out.println("Light "+i);
			name = "lights["+i+"]";
//			System.out.println(name);
			l = lights.get(i);
			shader.setUniform2f(name+pos,l.position);
			shader.setUniform1f(name+size,l.size);
			shader.setUniform1f(name+llv, l.lowLightValue);
		}
	}
	
}
