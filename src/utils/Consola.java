package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Consola {
	public static char readChar() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return (char) br.read();
	}
	
	public static int readInt() throws IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		return Integer.valueOf(br.readLine());
	}
	
	public static long readLong() throws IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		return Long.valueOf(br.readLine());
	}
	
	public static float readFloat() throws IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		return Float.valueOf(br.readLine());
	}
	
	public static double readDouble() throws IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		return Double.valueOf(br.readLine());
	}
	
	public static String readString() throws IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
}
