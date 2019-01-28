#version 330 core

layout (location = 0) out vec4 color;

in data{
    vec2 tc;
} fs_in;

uniform sampler2D tex;
uniform int grey = 0;

void main(){
    color = texture(tex,fs_in.tc);
    if(color.a==0)discard;
    if(grey!=0){
            float t = color.r*0.2989+color.g*0.5870+color.b*0.1140;
            color.r=t;color.g=t;color.b=t;
        }
}