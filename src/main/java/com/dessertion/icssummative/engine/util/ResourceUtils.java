package com.dessertion.icssummative.engine.util;

import java.io.*;
import java.util.Scanner;

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
		Scanner       sc     = new Scanner(ResourceUtils.class.getResourceAsStream(path));
		while(sc.hasNext())result.append(sc.nextLine() + "\n");
		return result.toString();
	}
}

