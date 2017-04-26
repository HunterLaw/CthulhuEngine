package coreEngine.ui;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL12;

import coreEngine.graphics.Shader;
import coreEngine.maths.Matrix4f;

public class Screen
{
	public static float width,height,depth;
	public static float left,right;
	public static float top,bottom;
	public static float front,back;
	public static float aspectratio;
	public static float centerx,centery;
	public Screen()
	{
		aspectratio = 4f/3f;
		width = 20f;
		height = width/aspectratio;
		depth = 2f;
		left = -width/2f;
		right = width/2f;
		top = height/2f;
		bottom = -height/2f;
		front = depth/2f;
		back = -depth/2f;
		setMatrix();
	}
	public Screen(float[] di)
	{
		left = di[0];
		right = di[1];
		bottom = di[2];
		top = di[3];
		back = di[4];
		front = di[5];
		width = right-left;
		height = top-bottom;
		depth = front - back;
		centerx = width/2f;
		centery = height/2f;
		setMatrix();
	}
	
	public static void setMatrix()
	{
		Shader.setPrMatrix(Matrix4f.orthographic(left, right, bottom, top, back, front));
	}
	
	public static void translateX(float x)
	{
//		glTranslatef(x, 0f, 0f);
//		GL30.
//		glTranslate2f(x,0f,0f);
	}
	
	public static void translateY(float y)
	{
//		top += y;
//		bottom += y;
//		setMatrix();
	}
	
	@Override
	public String toString()
	{
		String result = "Screen: ";
		result += "("+left+","+right+","+bottom+","+top+","+back+","+front+")";
		return result;
	}
}
