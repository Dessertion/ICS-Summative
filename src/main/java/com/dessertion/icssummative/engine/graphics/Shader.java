package com.dessertion.icssummative.engine.graphics;

import com.dessertion.icssummative.engine.util.BufferUtils;
import com.dessertion.icssummative.engine.util.ShaderUtils;
import org.joml.*;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

/**
 * @author Dessertion
 */
public class Shader {
	
	private final int program;
	
	private Map<String,Integer> cache = new HashMap<>(); //bit of dp here to prevent bottlenecking b/n gpu and cpu
	
	public Shader(String vertPath, String fragPath){
		program = ShaderUtils.load(vertPath,fragPath);
	}
	
	public int getUniformLocation(String varName){
		if(cache.containsKey(varName))return cache.get(varName); //dp lol
		
		int ret = glGetUniformLocation(program,varName);
		if(ret==-1)System.err.println("Uniform variable \"" +varName + "\" not found!");
		else cache.put(varName,ret); //if first time call cache it
		
		return ret;
	}
	
	public void setUniform1i(String varName, int val){
		glUniform1i(getUniformLocation(varName),val);
	}
	
	public void setUniform2i(String varName, int x, int y){
		glUniform2i(getUniformLocation(varName),x,y);
	}
	
	public void setUniform2i(String varName, Vector2i v){
		setUniform2i(varName,v.x,v.y);
	}
	
	public void setUniform3i(String varName, Vector3i v){
		glUniform3i(getUniformLocation(varName),v.x,v.y,v.z);
	}
	
	public void setUniform2f(String varName,float x, float y){
		glUniform2f(getUniformLocation(varName),x,y);
	}
	
	public void setUniform2f(String varName, Vector2f v){
		glUniform2f(getUniformLocation(varName),v.x,v.y);
	}
	
	public void setUniform3f(String varName, Vector3f v){
		glUniform3f(getUniformLocation(varName),v.x,v.y,v.z);
	}
	
	public void setUniformMat4f(String varName, Matrix4f mat){
		glUniformMatrix4fv(getUniformLocation(varName),false,BufferUtils.createFloatBuffer(mat.get(new float[4*4])));
	}
	
	
	public void enable(){
		glUseProgram(program);
	}
	
	public void disable(){
		glUseProgram(0);
	}
	
	
}

