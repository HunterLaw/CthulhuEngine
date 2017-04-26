package coreEngine.paths;

import coreEngine.maths.Vector3f;
import coreEngine.objects.NonTexturedObject2d;

public class Node extends NonTexturedObject2d
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5746483242817317948L;
	static Node start = null,end = null;
	Node hn = null;
	static int nsize =0;
	int tx,ty,f,g,h;
	public Node(int x,int y,boolean tile,double nsiz)
	{
		super(new Vector3f(0f,0f,0f),0,0);
//		super(x,y,32,32,true);
		if(nsiz != Double.NaN && nsize != 0)
		{
			setNodeSize((int)nsiz);
		}
		if(tile)
		{
			this.tx = x;
			this.ty = y;
//			this.x = x*nsiz;
//			this.y = y*nsiz;
		}
		else
		{
			this.tx = x/nsize;
			this.ty = y/nsize;
//			this.x = x;
//			this.y = y;
		}
	}
	
	public void setNodeSize(int size)
	{
		nsize = size;
	}
		
	public void setHostNode(Node hns)
	{
		hn = hns;
	}
	
	public void setStart(Node s)
	{
		start = s;
		int difx = Math.abs(start.tx-tx);
		int dify = Math.abs(start.ty-ty);
		g = difx+dify;
	}
	
	public void setEnd(Node e)
	{
		end = e;
		int difx = Math.abs(end.tx-tx);
		int dify = Math.abs(end.ty-ty);
		h = difx+dify;
	}
	
	public int compareTo(Node n)
	{
		return n.g-g;
	}
	
	public boolean equals(Node e)
	{
//		if(x == e.x && y == e.y)
//		{
//			return true;
//		}
		return false;
	}
	
	public Node getHostNode()
	{
		return hn;
	}
	
	public int getF()
	{
		f = g+h;
		return f;
	}
	
	public int getH()
	{
		return h;
	}
	
	public int getG()
	{
		return g;
	}
	@Override
	public String toString()
	{
		return String.format("Node (%d,%d,%d,%d,%d)",tx,ty,g,h,f);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render()
	{
		// TODO Auto-generated method stub
		
	}
}
