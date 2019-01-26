#version 330 core //opengl ver 3.3

layout (location = 0) out vec4 color; //rgba value

in data{ //data struct
    vec2 tc;
} fs_in;

uniform sampler2D tex;

void main()
{
    color = texture(tex,fs_in.tc);
}