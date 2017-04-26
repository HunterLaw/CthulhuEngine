package coreEngine.graphics;

import static org.lwjgl.opengl.GL20.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL20;

import coreEngine.maths.Matrix4f;
import coreEngine.maths.Vector3f;
import coreEngine.maths.Vector4f;
import coreEngine.utils.ShaderUtils;

public class Shader {
	
	public static final int VERTEX_ATTRIB = 0;
	public static final int TCOORD_ATTRIB = 1;
	
	public static Shader BG, BIRD, PIPE, FADE;
	
	private boolean enabled = false;
	
	private final int ID;
	private Map<String, Integer> locationCache = new HashMap<String, Integer>();
	
	public static Matrix4f pr_matrix;
	
	public Shader(String vertex, String fragment) {
		ID = ShaderUtils.load(vertex, fragment);
	}
	
	public Shader(File vertex, File fragment)
	{
		ID = ShaderUtils.load(vertex, fragment);
	}
	
	public static void setPrMatrix(Matrix4f pr)
	{
		pr_matrix = pr;
	}
	
	public static void loadAll() {
		BG = new Shader("shaders/shader.vert", "shaders/shader.frag");
		
//		BIRD = new Shader("shaders/bird.vert", "shaders/bird.frag");
//		PIPE = new Shader("shaders/pipe.vert", "shaders/pipe.frag");
//		FADE = new Shader("shaders/fade.vert", "shaders/fade.frag");
//		System.out.println("BG:"+BG.ID);
//		System.out.println("Bird:"+BIRD.ID);
//		System.out.println("Pipe:"+PIPE.ID);
//		System.out.println("Fade:"+FADE.ID);

	}
	
	public int getUniform(String name) {
		if (locationCache.containsKey(name))
			return locationCache.get(name);
		
		int result = glGetUniformLocation(ID, name);
		if (result == -1) 
		{
			
			System.err.println("Could not find uniform variable '" + name +" : "+ID+ "'!");
			System.exit(0);
		}
		else
		{
			locationCache.put(name, result);
		}
		return result;
	}
	
	public void setUniform1i(String name, int value) {
//		System.out.println("UNI1i");
		if (!enabled) enable();
		glUniform1i(getUniform(name), value);
	}
	
	public void setUniform1f(String name, float value) {
//		System.out.println("UNI1f "+name);
		if (!enabled) enable();
		glUniform1f(getUniform(name), value);
	}
	
	public void setUniform2f(String name, float x, float y) {
//		System.out.println("UNI2f "+name);
		if (!enabled) enable();
		glUniform2f(getUniform(name), x, y);
	}
	
	public void setUniform2f(String name, Vector3f vec)
	{
		setUniform2f(name,vec.x,vec.y);
	}
	
	public void setUniform3f(String name, Vector3f vector) {
//		System.out.println("UNI3f "+name);
		if (!enabled) enable();
		glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
	}
	
	public void setUniform4f(String name, Vector4f vec)
	{
		if(!enabled)enable();
		glUniform4f(getUniform(name),vec.x,vec.y,vec.z,vec.w);
	}
	
	public void setUniform4f(String name, Vector3f vec, float alpha)
	{
		setUniform4f(name,new Vector4f(vec.x,vec.y,vec.z,alpha));
	}
	
	public void setUniform4f(String name, Vector3f vec)
	{
		setUniform4f(name,vec,1f);
	}
	
	public void setUniformMat4f(String name, Matrix4f matrix) {
//		System.out.println("UNIMat4f " + name);
		if (!enabled) enable();
		glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
	}
	
	public void setUniformBool(String name, boolean bool)
	{
		if(bool)
			setUniform1i(name,1);
		else
			setUniform1i(name,0);
	}
	
	public void enable() {
		glUseProgram(ID);
		enabled = true;
	}
	
	public void disable() {
		glUseProgram(0);
		enabled = false;
	}

}
