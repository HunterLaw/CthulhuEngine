package coreEngine.objects;

import coreEngine.maths.Vector3f;

public class Wall extends TexturedObject2d{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7456306774113145109L;
	public Wall(Vector3f pos, float width, float height) {
		super(pos, width, height);
	}

	boolean passable = false;

	public void setPassable(boolean pass)
	{
		passable = pass;
	}
	
	public boolean isPassable()
	{
		return passable;
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render()
	{
		
	}

}
