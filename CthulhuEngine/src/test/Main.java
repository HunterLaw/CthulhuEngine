package test;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import coreEngine.maths.Vector3f;
import coreEngine.maths.Vector4f;
import coreEngine.objects.Camera;
import coreEngine.objects.Light;
import coreEngine.objects.NonTexturedObject2d;
import coreEngine.ui.FPS;
import coreEngine.ui.HorizontalScrollingBG;
import coreEngine.ui.Renderer2D;
import coreEngine.ui.Screen;
import coreEngine.ui.ScrollingMap;
import coreEngine.ui.Window;

//import graphics.Shader;
//import level.Level2;
//import maths.Matrix4f;

public class Main implements Runnable
{
	private Thread thread;
	private boolean running = false;
	private int width = 1280,height = 720;
	private Window window;
	private long start;
	Random rand = new Random();
	Character chars;
	FPS fps = new FPS(60);
	Screen screen;
	HorizontalScrollingBG hbg;
//	VerticalScrollingBG vbg;
	ScrollingMap scroll;
	BufferedImage bg;
	Camera cam;
	Renderer2D renderer;
	ArrayList<NonTexturedObject2d> objects;
	Block block;
//	Level2 lv;
	public static void main(String[] args)
	{
		new Main().start();
	}
	
	public void start()
	{
		running = true;
		thread = new Thread(this,"Game Loop");
		thread.start();
	}
	
	public void init()
	{
		window = new Window(new Dimension(width,height),"Title");
		window.init();
		window.setClearColor(new Vector4f(0f,0f,0f,0f));
//		glEnable(GL_BLEND);
//		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
//		window.setClearColor(new Vector4f(1.0f,1.0f,1.0f,1.0f));
//		Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
//		Shader.loadAll();
		screen = new Screen(new float[]{0f,20f,0f,20f*9f/16f,-1f,1f});
		System.out.println(screen);
//		Shader.setPrMatrix(Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f));
//		Shader.BG.setUniformMat4f("pr_matrix", pr_matrix);
//		Shader.BG.setUniform1i("tex", 1);
//		lv = new Level2();
		objects = new ArrayList<NonTexturedObject2d>();
		renderer = new Renderer2D();
		try{
			bg= ImageIO.read(new File("Rsc/TestScrolling.png"));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
//		hbg = new HorizontalScrollingBG(bg,bg.getWidth(),bg.getHeight());
//		vbg = new VerticalScrollingBG(bg,bg.getWidth(),bg.getHeight());
//		new Light(new Vector3f(4f,4f,0f),2f,0.15f);
//		new Light();
		scroll = new ScrollingMap(bg,2f,2f);
		scroll.enableLight();
		System.out.println("scrol");
		block = new Block(new Vector3f(2f,2f,0f),1f,1f);
		block.enableLight();
		System.out.println("block");
		chars = new Character();
		chars.enableLight();
		cam = new Camera();
		cam.setLimits(new float[]{Screen.left,Screen.right*2f,Screen.bottom,Screen.top*2f,Screen.back,Screen.front});
//		cam.
//		objects.add(vbg);
		objects.add(block);
		objects.addAll(chars.getObjects(chars));
		objects.add(scroll);
//		objects.add
		renderer.setObjectArray(objects);
	}
	
	public void stop()
	{
		window.stop();
	}
	
	public void update()
	{
//		System.out.println(screen);
		window.update();
		chars.update();
//		scroll.setLightPos(chars.getPosition());
		cam.update(chars.getPosition());
//		vbg.update();
	}
	
	public void render()
	{
		window.clearScreen();
		renderer.render(cam);
		window.swapBuffers();
	}
	
	@Override
	public void run()
	{
//		int ups = 0;
		init();
		fps.startFPSTime();
//		start = System.currentTimeMillis();
		while(running)
		{

			if(fps.secondDone())
			{
//				System.out.println("here");
				fps.startFPSTime();
			}
			if(fps.shouldUpdate())
			{
				update();	
//				ups++;
			}
			render();
			fps.addFPS();
			if(window.shouldClose())
			{
				running = false;
			}
		}
//		long elapsed = System.currentTimeMillis()-start;
//		double sec = (double)elapsed/1000;
//		System.out.println("Time: "+sec);
//		System.out.println("Updates: "+ups);
		stop();
	}
}

