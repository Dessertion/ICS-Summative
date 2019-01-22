package com.dessertion.icssummative.engine.util;

import java.io.*;

/**
 * @author Dessertion
 */
public class ResourceUtils {
	
	/**
	 * Loads a file and returns its contents as a string
	 * @param path Path of the given file
	 * @return Contents of the file as string
	 */
	public static String loadResourceAsString(String path){
		StringBuilder result = new StringBuilder();
		File   file   = new File(ResourceUtils.class.getResource(path).getFile());
		//lol black magicks :D
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String temp = ""; temp != null; temp=br.readLine()) result.append(temp+"\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}

