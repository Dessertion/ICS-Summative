package com.dessertion.icssummative.engine.graphics;

import com.dessertion.icssummative.engine.util.ResourceUtils;

import static org.lwjgl.opengl.GL20.*;

/**
 * @author Dessertion
 */
public class ShaderUtil {
	
	private final int programId;
	private int vertexShaderId;
	private int fragmentShaderId;
	
	public ShaderUtil() throws Exception {
		programId = glCreateProgram();
		//make sure we can create the opengl program
		//also TODO: create custom exception for this?
		if (programId == GL_FALSE) throw new Exception("Could not create Shader");
	}
	
	public ShaderUtil(String vertexFilePath, String fragFilePath) throws Exception {
		this();
		createVertexShader(ResourceUtils.loadResourceAsString(vertexFilePath));
		createFragmentShader(ResourceUtils.loadResourceAsString(fragFilePath));
	}
	
	public void createVertexShader(String shaderCode) throws Exception {
		vertexShaderId = createShader(shaderCode, GL_VERTEX_SHADER);
	}
	
	public void createFragmentShader(String shaderCode) throws Exception {
		fragmentShaderId = createShader(shaderCode, GL_FRAGMENT_SHADER);
	}
	
	
	/**
	 * Creates a graphics and returns the shaderId of the graphics
	 *
	 * @param shaderCode Shader Code
	 * @param shaderType Type of Shader
	 * @return Id of graphics
	 * @throws Exception
	 */
	protected int createShader(String shaderCode, final int shaderType) throws Exception {
		int shaderId = glCreateShader(shaderType);
		if (shaderId == GL_FALSE) throw new Exception("Error creating graphics; type: " + shaderType);
		
		glShaderSource(shaderId, shaderCode);
		glCompileShader(shaderId);
		
		if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == GL_FALSE)
			throw new Exception("Error compiling graphics code: " + glGetShaderInfoLog(shaderId, 1024));
		glAttachShader(programId, shaderId);
		return shaderId;
	}
	
	/**
	 * Links the graphics to the program
	 * @throws Exception
	 */
	public void link() throws Exception {
		glLinkProgram(programId);
		if (glGetProgrami(programId, GL_LINK_STATUS) == GL_FALSE)
			throw new Exception("Error linking graphics code: " + glGetProgramInfoLog(programId, 1024));
		
		if (vertexShaderId != 0)glDetachShader(programId, vertexShaderId);
		if (fragmentShaderId != 0)glDetachShader(programId, fragmentShaderId);
		
		glValidateProgram(programId);
		if (glGetProgrami(programId, GL_VALIDATE_STATUS) == GL_FALSE)System.err.println("Warning validating graphics code: " + glGetProgramInfoLog(programId, 1024));
	}
	
	public void enable() {
		glUseProgram(programId);
	}
	
	public void disable() {
		glUseProgram(0);
	}
	
	public void release() {
		disable();
		if (programId != 0) glDeleteProgram(programId);
	}
	
	public int getProgramId() {
		return programId;
	}
	
	public int getVertexShaderId() {
		return vertexShaderId;
	}
	
	public void setVertexShaderId(int vertexShaderId) {
		this.vertexShaderId = vertexShaderId;
	}
	
	public int getFragmentShaderId() {
		return fragmentShaderId;
	}
	
	public void setFragmentShaderId(int fragmentShaderId) {
		this.fragmentShaderId = fragmentShaderId;
	}
	
}

