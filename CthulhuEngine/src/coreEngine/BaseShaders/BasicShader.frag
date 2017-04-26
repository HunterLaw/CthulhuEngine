#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
	vec3 position;
} fs_in;

struct Light
{
	vec2 pos;
	float size;
	float lowLightValue;
};

uniform Light lights[100];
uniform vec4 col;
uniform int enabled =0;

float high = 0;
vec4 highcol = vec4(0);


bool greater(vec4 l, vec4 r)
{
	float lbright = sqrt(0.2126*pow(l.r,2))+(0.7152*pow(l.g,2))+(0.0722*pow(l.b,2));
	float rbright = sqrt(0.2126*pow(r.r,2))+(0.7152*pow(r.g,2))+(0.0722*pow(r.b,2));
	if(lbright > rbright)
	{
		return true;
	}
	return false;
}

void main()
{
	color = col;
	if(enabled == 1)
		{
			float len = length(fs_in.position.xy-lights[0].pos);
			float lenr = lights[0].size/len;
			float llv = lights[0].lowLightValue;
			color *= lenr+llv;
	//		vec4 color2;
			
			for(int i = 0;i < lights.length();i++)
			{
				
				if(lights[i].lowLightValue != 0)
				{
					float len = length(fs_in.position.xy-lights[i].pos);
					float lenr = lights[i].size/len;
					float llv = lights[i].lowLightValue;
					if((1/lenr) > 1)
					{
						lenr = 0;
					}
					float col = (lenr*llv)+llv;
					vec4 ncol = color*col;
					if(greater(ncol,highcol))
					{
						highcol = ncol;
					}
					//if(col>high)
					//{
					//	high = col;
					//}
				}
				else
				{
					break;
				}
			}
	//		color = highcol;
		}
}