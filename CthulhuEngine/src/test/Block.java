package test;

import coreEngine.maths.Matrix4f;
import coreEngine.maths.Vector3f;
import coreEngine.objects.TexturedObject2d;

public class Block extends TexturedObject2d
{

	public Block(Vector3f pos, float w, float h)
	{
		super(pos, w, h);
//		setColor(new Vector3f(1.0f,0f,0f));
		// TODO Auto-generated constructor stub
		setTexture("Rsc/Test.jpg");
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render()
	{
		System.out.println("Rendering block");
		shader().setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		defaultRender();
	}

}
