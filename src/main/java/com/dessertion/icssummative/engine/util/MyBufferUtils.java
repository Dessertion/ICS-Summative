package com.dessertion.icssummative.engine.util;

import java.nio.*;

/**
 * @author Dessertion
 */
public final class MyBufferUtils {
	
	/**
	 * Returns a byte buffer from a byte array
	 * @param array
	 * @return
	 */
	public static ByteBuffer createByteBuffer(byte[] array){
		ByteBuffer ret = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
		ret.put(array).flip();
		return ret;
	}
	
	/**
	 * Returns a float buffer from a float array
	 * @param array
	 * @return
	 */
	public static FloatBuffer createFloatBuffer(float[] array){
		FloatBuffer ret = ByteBuffer.allocateDirect(array.length<<2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		ret.put(array).flip();
		return ret;
	}
	
	/**
	 * Returns an int buffer from an int array
	 * @param array
	 * @return
	 */
	public static IntBuffer createIntBuffer(int[] array){
		IntBuffer ret = ByteBuffer.allocateDirect(array.length<<2).order(ByteOrder.nativeOrder()).asIntBuffer();
		ret.put(array).flip();
		return ret;
	}
	
}

