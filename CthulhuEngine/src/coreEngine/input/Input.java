package coreEngine.input;


import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {
	
	public static int[] keys = new int[65536];
	public static int Left = GLFW_KEY_LEFT;
	public static int Right = GLFW_KEY_RIGHT;
	public static int Up = GLFW_KEY_UP;
	public static int Down = GLFW_KEY_DOWN;
	public static int Space = GLFW_KEY_SPACE;
	public static int ShiftL = GLFW_KEY_LEFT_SHIFT;
	public static int ShiftR = GLFW_KEY_RIGHT_SHIFT;
	public static int W = GLFW_KEY_W;
	public static int S = GLFW_KEY_S;
	public static int A = GLFW_KEY_A;
	public static int D = GLFW_KEY_D;

	public void invoke(long window, int key, int scancode, int action, int mods) {
		switch(action)
		{
			case GLFW_PRESS:
				keys[key] = 1;
				break;
			case GLFW_RELEASE:
				keys[key] = 2;
				break;
				
		}
	}
	
	public static boolean isKeyDown(int keycode) {
		return (keys[keycode] == 1);
	}
	
	public static boolean hasKeyBeenPressed(int keycode)
	{
		if(keys[keycode] == 2)
		{
			keys[keycode] = 0;
			return true;
		}
		return false;
	}
	
}
