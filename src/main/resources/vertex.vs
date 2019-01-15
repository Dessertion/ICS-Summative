#version 330 //opengl 3.3

layout (location =0) in vec3 position; //position in a vector w/ 3 values

void main()
{
	gl_Position = vec4(position, 1.0);
}