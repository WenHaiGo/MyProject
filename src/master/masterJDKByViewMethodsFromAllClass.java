package master;

import java.io.IOException;

public class masterJDKByViewMethodsFromAllClass {

	public static void main(String[] args) throws IOException {
		System.out.println("hello world");
		byte[] buffer = new byte[1024];
		//返回值是告诉我们到底读了多少东西
		int len = System.in.read(buffer);
		String string = new String(buffer, 0, len);//从零开始构造一个字节
		System.out.println("读到了"+len+"字节");
		System.out.println(string);
		System.out.println("string的长度是"+string.length());

	}
}
