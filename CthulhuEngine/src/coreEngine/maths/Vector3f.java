package coreEngine.maths;

public class Vector3f {

	public float x, y, z;
	public float r, g, b;

	public Vector3f() {
		r = x = 0.0f;
		g = y = 0.0f;
		b = z = 0.0f;
	}

	public Vector3f(float x, float y, float z) {
		r = this.x = x;
		g = this.y = y;
		b = this.z = z;
	}

	public Vector3f setX(float x)
	{
		r = this.x = x;
		return this;
	}
	
	public Vector3f setY(float y)
	{
		g = this.y = y;
		return this;
	}

	public Vector3f setZ(float z)
	{
		b = this.z = z;
		return this;
	}
	
	public Vector3f clone()
	{
		return new Vector3f(x,y,z);
	}
	
	public boolean equals(Vector3f pos)
	{
		boolean x = Math.abs(this.x-pos.x) <=0.001f;
		boolean y = Math.abs(this.y-pos.y) <= 0.001f;
		return x&&y;
	}
	
	public String toString()
	{
		String rc;
		rc = "("+x+","+y+","+z+")";
		return rc;
	}
}
