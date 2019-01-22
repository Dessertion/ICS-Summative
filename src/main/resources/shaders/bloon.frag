#version 330 core

layout (location = 0) out vec4 color;

in data{
    vec2 tc;
} fs_in;

uniform sampler2D tex;

void main(){
    color = texture(tex,fs_in.tc);
    if(color.a<1)discard;
}