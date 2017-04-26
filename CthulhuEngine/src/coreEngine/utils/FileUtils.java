package coreEngine.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	private FileUtils() {
	}
	
	public static String loadAsString(String file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String buffer = "";
			while ((buffer = reader.readLine()) != null) {
				result.append(buffer + '\n');
			}
			reader.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			if(e instanceof FileNotFoundException)
			{
				System.err.println("Cannot find : "+file);
				System.exit(0);
			}
			else
			{
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	public static String loadAsString(File file)
	{
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String buffer = "";
			while ((buffer = reader.readLine()) != null) {
				result.append(buffer + '\n');
			}
			reader.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			if(e instanceof FileNotFoundException)
			{
				System.err.println("Cannot find : "+file);
				System.exit(0);
			}
			else
			{
				e.printStackTrace();
			}
		}
		return result.toString();
	}
}
