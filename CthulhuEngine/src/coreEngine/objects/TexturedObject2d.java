package coreEngine.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import coreEngine.graphics.Shader;
import coreEngine.graphics.Texture;
import coreEngine.maths.Vector3f;

public abstract class TexturedObject2d extends NonTexturedObject2d
{
	protected Texture texture;
	protected ArrayList<BufferedImage> images;
	public TexturedObject2d(Vector3f pos, float w, float h)
	{
		super(pos, w, h);
		images = new ArrayList<BufferedImage>();
		try
		{
			vert =	new File(getClass().getClassLoader().getResource("coreEngine/BaseShaders/TextureShader.vert").toURI());
			frag = new File(getClass().getClassLoader().getResource("coreEngine/BaseShaders/TextureShader.frag").toURI());
		}
		catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setShaderFiles(vert,frag);
//		shader = new Shader(vert,frag);
//		shader.setUniformMat4f("pr_matrix", Shader.pr_matrix);
//		shader.setUniform1i("tex", 1);
	}
	
	public void setShaderFiles(String vert,String frag)
	{
		setShader(new Shader(vert,frag));
		shader.setUniformMat4f("pr_matrix", Shader.pr_matrix);
		shader.setUniform1i("tex", 1);
	}
	
	public void setShaderFiles(File vert,File frag)
	{
		setShader(new Shader(vert,frag));
		shader.setUniformMat4f("pr_matrix", Shader.pr_matrix);
		shader.setUniform1i("tex",1);
	}
	
	public void setTexture(String file)
	{
		texture = new Texture(file);
	}
	
	public void setTexture(BufferedImage image)
	{
		texture = new Texture(image);
	}
	
	public BufferedImage loadBasicImage(File files)
	{
		try
		{
			return ImageIO.read(files);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * loadTiledImage()
	 * 
	 * Loads the image and then grabs the subimage from the specified area given
	 */
	public void loadTiledImage(int width,int height,File file)
	{
		BufferedImage im = loadBasicImage(file);
		if(im !=null)
		{
			for(int x = 0; x < im.getWidth(); x+=width)
			{
				for (int y = 0; y< im.getHeight();y+=height)
				{
					images.add(im.getSubimage(x,y,width,height));
				}
			}
		}
	}
	
	public void defaultRender()
	{
//		System.out.println("text render");
		setLight();
		shader.enable();
		texture.bind();
		mesh.render();
		shader.disable();
		texture.unbind();
	}
	
	public void defaultRender(Texture tx)
	{
		setLight();
		shader.enable();
		tx.bind();
		mesh.render();
		shader.disable();
		texture.unbind();
	}
}
