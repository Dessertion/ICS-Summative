package com.dessertion.icssummative.engine.shader;

import static org.lwjgl.opengl.GL20.*;

/**
 * @author Dessertion
 */
public class EngineShader {
	
	private final int programId;
	private       int vertexShaderId;
	private       int fragmentShaderId;
	
	public EngineShader() throws Exception {
		programId = glCreateProgram();
		//make sure we can create the opengl program
		//also TODO: create custom exception for this?
		if (programId == GL_FALSE) throw new Exception("Could not create Shader");
	}
	
	public void createVertexShader(String shaderCode) throws Exception {
		vertexShaderId = createShader(shaderCode, GL_VERTEX_SHADER);
	}
	
	public void createFragmentShader(String shaderCode) throws Exception {
		fragmentShaderId = createShader(shaderCode, GL_FRAGMENT_SHADER);
	}
	
	
	/**
	 * Creates a shader and returns the shaderId of the shader
	 *
	 * @param shaderCode Shader Code
	 * @param shaderType Type of Shader
	 * @return Id of shader
	 * @throws Exception
	 */
	protected int createShader(String shaderCode, final int shaderType) throws Exception {
		int shaderId = glCreateShader(shaderType);
		if (shaderId == GL_FALSE) throw new Exception("Error creating shader; type: " + shaderType);
		
		glShaderSource(shaderId, shaderCode);
		glCompileShader(shaderId);
		
		if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == GL_FALSE)
			throw new Exception("Error compiling shader code: " + glGetShaderInfoLog(shaderId, 1024));
		glAttachShader(programId, shaderId);
		return shaderId;
	}
	
	public void link() throws Exception {
		glLinkProgram(programId);
		if (glGetProgrami(programId, GL_LINK_STATUS) == GL_FALSE)
			throw new Exception("Error linking shader code: " + glGetProgramInfoLog(programId, 1024));
		if (vertexShaderId != 0)glDetachShader(programId, vertexShaderId);
		if (fragmentShaderId != 0)glDetachShader(programId, fragmentShaderId);
		
		glValidateProgram(programId);
		if (glGetProgrami(programId, GL_VALIDATE_STATUS) == GL_FALSE)
			System.err.println("Warning validating shader code: " + glGetProgramInfoLog(programId, 1024));
	}
	
	public void bind() {
		glUseProgram(programId);
	}
	
	public void unbind() {
		glUseProgram(0);
	}
	
	public void release() {
		unbind();
		if (programId != 0) glDeleteProgram(programId);
	}
	
	
}

