#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 tc;

uniform mat4 proj_mat;
uniform mat4 view_mat;

out data{
    vec2 tc;
} vs_out;

void main() {
    gl_Position = proj_mat*view_mat*position;
    vs_out.tc=tc;
}
