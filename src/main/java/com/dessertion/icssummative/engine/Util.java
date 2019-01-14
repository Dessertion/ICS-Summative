package com.dessertion.icssummative.engine;

import java.io.*;
import java.util.Scanner;

/**
 * @author Dessertion
 */
public class Util {
	
	/**
	 * Loads a file and returns its contents as a string
	 * @param path Path of the given file
	 * @return Contents of the file as string
	 * @throws Exception
	 */
	public static String loadResourceAsString(String path) throws Exception {
		String result = "";
		File   file   = new File(path);
		//lol black magicks :D
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String temp = br.readLine(); temp != null; ) result += temp;
		}
		return result;
	}
}

