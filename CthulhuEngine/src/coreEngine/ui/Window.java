package coreEngine.ui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.Dimension;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import coreEngine.input.Input;
import coreEngine.maths.Vector3f;
import coreEngine.maths.Vector4f;

public class Window
{
	private Dimension size;
	private String title;
	private long window;
	
	public Window(Dimension size, String title)
	{
		this.size = size;
		this.title = title;
	}
	
	public void init()
	{
		GLFWErrorCallback.createPrint(System.err).set();
		if(!glfwInit())
		{
			throw new IllegalStateException("GLFW failed to initialize");
		}
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		glfwWindowHint(GLFW_DOUBLEBUFFER, GLFW_TRUE);
		window = glfwCreateWindow(size.width,size.height,title,NULL,NULL);
		if(window == NULL)
		{
			throw new IllegalStateException("Failed to create new window");
		}
		GLFWVidMode mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window,(mode.width()-size.width)/2,(mode.height()-size.height)/2);
		glfwSetKeyCallback(window, new Input());
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glEnable(GL_DEPTH_TEST);
		glActiveTexture(GL_TEXTURE1);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(0f,0f,0f,0f);
		
	}

	public void showWindow()
	{
		glfwShowWindow(window);
	}
	
	public void setClearColor(Vector4f color)
	{
		glClearColor(color.x,color.y,color.z,color.w);
	}
	
	public void stop()
	{
		glfwDestroyWindow(window);
		glfwTerminate();
	}
	
	public void update()
	{
		glfwPollEvents();
	}
	
	public void clearScreen()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);		
	}
	
	public void swapBuffers()
	{
		glfwSwapBuffers(window);
	}
	
	public boolean shouldClose()
	{
		return glfwWindowShouldClose(window);
	}
}