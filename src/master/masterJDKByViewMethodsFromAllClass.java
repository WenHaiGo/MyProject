package master;

import java.io.IOException;

public class masterJDKByViewMethodsFromAllClass {

	public static void main(String[] args) throws IOException {
		System.out.println("hello world");
		byte[] buffer = new byte[1024];
		//����ֵ�Ǹ������ǵ��׶��˶��ٶ���
		int len = System.in.read(buffer);
		String string = new String(buffer, 0, len);//���㿪ʼ����һ���ֽ�
		System.out.println("������"+len+"�ֽ�");
		System.out.println(string);
		System.out.println("string�ĳ�����"+string.length());

	}
}
