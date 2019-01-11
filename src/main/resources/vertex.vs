#version 330 //gl version 3.3


layout (location=0) in vec3 position;

void main(){
    gl_Position = vec4(position,1.0);
}