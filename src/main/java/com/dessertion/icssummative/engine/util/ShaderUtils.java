package com.dessertion.icssummative.engine.util;

import static org.lwjgl.opengl.GL20.*;

/**
 * @author Dessertion
 */
public class ShaderUtils {

	public static int load(String vertPath, String fragPath){
		String vert = ResourceUtils.loadResourceAsString(vertPath);
		String frag = ResourceUtils.loadResourceAsString(fragPath);
		return createShaders(vert,frag);
	}
	
	/**
	 * Creates shaders and returns the program handle
	 * @param vertCode Vertex graphics code
	 * @param fragCode Fragment graphics code
	 * @return Program handle (pointer)
	 */
	public static int createShaders(String vertCode, String fragCode){
		int program = glCreateProgram();
		int vert = glCreateShader(GL_VERTEX_SHADER);
		int frag = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(vert, vertCode); glShaderSource(frag,fragCode); //set source
		
		//compile and check code
		glCompileShader(vert);
		if(!checkCompileStatus(vert))return -1;
		
		glCompileShader(frag);
		if(!checkCompileStatus(frag))return -1;
		
		//attach shaders
		glAttachShader(program,vert); glAttachShader(program,frag);
		
		glLinkProgram(program);
		glValidateProgram(program);
		
		//delete shaders
		glDeleteShader(vert); glDeleteShader(frag);
		
		return program;
	}
	
	private static boolean checkValidateStatus(int program) {
		if(glGetShaderi(program,GL_VALIDATE_STATUS)==GL_FALSE){
			System.err.println("Shader program failed to validate!");
			System.err.println(glGetShaderInfoLog(program));
			return true;
		}
		return false;
	}
	
	private static boolean checkCompileStatus(int id) {
		if(glGetShaderi(id,GL_COMPILE_STATUS)==GL_FALSE){
			System.err.println("Vertex graphics compilation failed!");
			System.err.println(glGetShaderInfoLog(id));
			return false;
		}
		return true;
	}
	
	
}

