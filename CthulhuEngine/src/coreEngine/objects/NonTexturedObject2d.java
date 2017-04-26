package coreEngine.objects;

import java.io.File;
import java.net.URISyntaxException;

import coreEngine.graphics.Shader;
import coreEngine.graphics.VertexArray;
import coreEngine.maths.Matrix4f;
import coreEngine.maths.Vector3f;

public abstract class NonTexturedObject2d extends Object2d
{

	protected Vector3f color = new Vector3f(1.0f,0f,0f);
	Shader shader;
	protected File vert,frag;
	private boolean lightEnabled = false;
	Vector3f lightPos = new Vector3f();
	public NonTexturedObject2d(Vector3f pos, float w, float h)
	{
		super(pos, w, h);
		if(!(this instanceof TexturedObject2d))
		{
			try
			{
				vert =	new File(getClass().getClassLoader().getResource("coreEngine/BaseShaders/BasicShader.vert").toURI());
				frag = new File(getClass().getClassLoader().getResource("coreEngine/BaseShaders/BasicShader.frag").toURI());
			}
			catch (URISyntaxException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setShaderFiles(vert,frag);
			changeColor(color);
		}
//		shader = new Shader(vert,frag);
//		shader.setUniformMat4f("pr_matrix", Shader.pr_matrix);
//		shader.setUniform4f("col",color);
	}

	public void setColor(Vector3f col)
	{
		color = col;
		shader().setUniform4f("col", color);
	}
	
	public void changeColor(Vector3f col)
	{
		shader.setUniform4f("col", col);
	}
	
	public void setShaderFiles(String vert,String frag)
	{
		setShader(new Shader(vert,frag));
		shader().setUniformMat4f("pr_matrix", Shader.pr_matrix);
		shader().setUniform4f("col", color);
	}
	
	public void setShaderFiles(File vert,File frag)
	{
		setShader(new Shader(vert,frag));
		shader().setUniformMat4f("pr_matrix", Shader.pr_matrix);

		shader().setUniform4f("col",color);
	}
	
	public void setViewMatrix(Matrix4f vw)
	{
		shader().setUniformMat4f("vw_matrix", vw);
	}
	
	public void setModelMatrix()
	{

		shader().setUniformMat4f("ml_matrix", Matrix4f.translate(position));
	}
	
	public void enableLight()
	{
		lightEnabled = true;
	}
	
	public void disableLight()
	{
		lightEnabled = false;
	}
	
	protected void setLight()
	{
		shader().setUniformBool("enabled", isLightEnabled());
//		shader().setUniform2f("lightPos", lightPos.x, lightPos.y);
	}
	
	public void setLightPos(Vector3f pos)
	{
		lightPos = pos;
//		shader.setUniform2f("lightPos", lightPos.x, lightPos.y);

	}
	
	public boolean isLightEnabled()
	{
		return lightEnabled;
	}

	public Shader shader()
	{
		return shader;
	}

	protected void setShader(Shader shader)
	{
		this.shader = shader;
	}

	public Vector3f getColor()
	{
		return color;
	}
	
	public void defaultRender()
	{
//		shader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		System.out.println("Non text render");
		setLight();
		shader.enable();
//		mesh.
		mesh.render();
		shader.disable();
	}
	
	public void simpleRender(VertexArray mesh2)
	{
		setLight();
		shader.enable();
		mesh2.render();
		shader.disable();
	}
}
