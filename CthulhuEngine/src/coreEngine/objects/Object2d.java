package coreEngine.objects;

import coreEngine.graphics.VertexArray;
import coreEngine.maths.Vector3f;
import coreEngine.movement.Direction;

public abstract class Object2d
{
	protected VertexArray mesh;
	protected Vector3f position = new Vector3f();
	protected float movespeed = 0.1f;
	protected float width,height;
	protected boolean enabled = true;
	protected Direction ud = Direction.none,lr = Direction.none;
	protected float[] vertices,tcs;
	protected byte[] indices;
	public Object2d(Vector3f pos, float w, float h)
	{
		position = pos;
		width = w;
		height = h;
		vertices = new float[]{
			0,0,0.0f,
			0,h,0.0f,
			w,h,0.0f,
			w,0,0.0f
		};
		indices = new byte[]{
			0,1,2,
			2,3,0
		};
		tcs = new float[] {
			0,1,
			0,0,
			1,0,
			1,1
		};
		mesh = new VertexArray(vertices,indices,tcs);
		
	}
	
	public float getRight()
	{
		return position.x+width;
	}
	
	public float getLeft()
	{
		return position.x;
	}
	
	public float getTop()
	{
		return position.y+height;
	}
	
	public float getBottom()
	{
		return position.y;
	}
	
	public void enable()
	{
		enabled = true;
	}

	public void disable()
	{
		enabled = false;
	}
	
	public void setX(float x)
	{
		position.x = x;
	}
	
	public void setY(float y)
	{
		position.y = y;
	}
	
	public void setPosition(Vector3f pos)
	{
		position = pos;
	}
	
	public void setMovespeed(float move)
	{
		movespeed = move;
	}
	
	public Vector3f simulateMove(Direction lr, Direction ud)
	{
		Vector3f rc = position;
		switch(lr)
		{
			case left:
				rc.x -= movespeed;
				break;
			case right:
				rc.x += movespeed;
				break;
			default:
				break;
		}
		switch(ud)
		{
			case up:
				rc.y += movespeed;
				break;
			case down:
				rc.y -= movespeed;
				break;
			default:
				break;
		}
		return rc;
		
	}
	
	public Direction[] positionRelativeTo(Object2d o)
	{
		Direction[] relative = {Direction.none,Direction.none};
		if(position.x < o.position.x)
		{
			relative[0] = Direction.left;
		}
		else if(position.x > o.position.x)
		{
			relative[0] = Direction.right;
		}
		if(position.y > o.position.y)
		{
			relative[1] = Direction.up;
		}
		else if(position.y < o.position.y)
		{
			relative[1] = Direction.down;
		}
		return relative;
	}
	
	public Collision collidesWithObject(Object2d o)
	{
		Collision rc = new Collision();
		boolean leftColl = getLeftEdge() <= o.getRightEdge() && getLeftEdge() >= o.getLeftEdge();
		boolean rightColl = getRightEdge() <= o.getRightEdge() && getRightEdge() >= o.getLeftEdge();
		boolean topColl = getTopEdge() >= o.getBottomEdge() && getTopEdge() <= o.getTopEdge();
		boolean botColl = getBottomEdge() >= o.getBottomEdge() && getBottomEdge() <= o.getTopEdge();
		boolean leftDirColl = leftColl && lr == Direction.left;
		boolean rightDirColl = rightColl && lr == Direction.right;
		boolean topDirColl = topColl && ud == Direction.up;
		boolean botDirColl = botColl && ud == Direction.down;
		if((leftColl || rightColl) && (topColl || botColl))
		{
			rc.collision = true;
		}
		
		if(rc.collision)
		{
			if(leftDirColl)
			{
				rc.directionalCollision[0] = true;
			}
			else if(rightDirColl)
			{
				rc.directionalCollision[0] = true;
			}
			if(topDirColl)
			{
				rc.directionalCollision[1] = true;
			}
			else if(botDirColl)
			{
				rc.directionalCollision[1] = true;
			}
		}
		
		if(position.x < o.position.x)
			rc.centerRelative[0] = Direction.left;
		else if(position.x > o.position.x)
			rc.centerRelative[0] = Direction.right;
		if(position.y < o.position.y)
			rc.centerRelative[1] = Direction.down;
		else if(position.y > o.position.y)
			rc.centerRelative[1] = Direction.up;
		
		return rc;
	}
	
	public void move(Direction lr, Direction ud)
	{
		switch(lr)
		{
			case left:
				position.x -= movespeed;
				break;
			case right:
				position.x += movespeed;
				break;
			default:
				break;
		}
		switch(ud)
		{
			case up:
				position.y += movespeed;
				break;
			case down:
				position.y -= movespeed;
				break;
			default:
				break;
		}
	}
	
	public float simulateX(Direction lr)
	{
		float nx = position.x;
		if(lr == Direction.right)
			nx += movespeed;
		else if(lr == Direction.left)
			nx -= movespeed;
		return nx;
	}
	
	public float simulateY(Direction ud)
	{
		float ny = position.y;
		if(ud == Direction.up)
			ny += movespeed;
		else if(ud == Direction.down)
			ny -= movespeed;
		return ny;
	}

	public boolean isEnabled()
	{
		return enabled;
	}
	
	public float getLeftEdge()
	{
		return position.x - (width/2f);
	}
	
	public float getRightEdge()
	{
		return position.x + (width/2f);
	}
	
	public float getTopEdge()
	{
		return position.y + (height/2f);
	}
	
	public float getBottomEdge()
	{
		return position.y - (height/2f);
	}
	
	public float getSimLeftEdge(Vector3f position)
	{
		return position.x - (width/2f);
	}
	
	public float getSimRightEdge(Vector3f position)
	{
		return position.x + (width/2f);
	}
	
	public float getSimTopEdge(Vector3f position)
	{
		return position.y + (height/2f);
	}
	
	public float getSimBottomEdge(Vector3f position)
	{
		return position.y - (height/2f);
	}
	
	public Vector3f getPosition()
	{
		return position;
	}
	
	public Vector3f getCenterPosition()
	{
		Vector3f npos = new Vector3f(position.x,position.y,position.z);
		npos.x += (width/2f);
		npos.y += (height/2f);
		return npos;
	}
	
	public float getX()
	{
		return position.x;
	}
	
	public float getY()
	{
		return position.y;
	}
	
	public float getZ()
	{
		return position.z;
	}

	public float getWidth()
	{
		return width;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public float getMovespeed()
	{
		return movespeed;
	}
	
	public abstract void update();
	public abstract void render();
	
	public class Collision
	{
		public Direction[] centerRelative = {Direction.none,Direction.none};
		public Direction[] rectRelative = {Direction.none,Direction.none};
		public boolean collision = false;
		public boolean directionalCollision[] = {false,false};
	}
	@Override
	public String toString()
	{
		String rc = "";
		rc += this.getClass().getSimpleName()+": (X:"+position.x+",Y:"+position.y+")";
		return rc;
	}
}
