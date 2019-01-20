#version 330 core //opengl 3.3

layout (location = 0) in vec4 position; //position in a vector w/ 3 values
layout (location = 1) in vec2 tc;

uniform mat4 proj_mat;

out data{ //data struct
    vec2 tc;
} vs_out;

void main()
{
	gl_Position = proj_mat*position;
	vs_out.tc = tc;
}