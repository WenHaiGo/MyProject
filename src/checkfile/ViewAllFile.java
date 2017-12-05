package checkfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.NoSuchFileException;
import java.sql.Time;
import java.util.*;

import javax.rmi.CORBA.Tie;

public class ViewAllFile {

	// �ж�·���Ƿ�Ϸ�
	boolean isExistDirectory(String directory) {
		File file = new File(directory);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	LinkedList<File> fileList = new LinkedList<File>();// ����ÿ�ζ���Ҫ�ݹ飬����ÿ�ζ������������ϣ�������û�취��������Ϸŵ������ڲ�

	LinkedList<File> getCertainTypeFiles(String fileDirectory, String fileType) throws NoSuchFileException {

		File file = new File(fileDirectory);
		// �������ļ��ŵ�File��������
		File[] sum = file.listFiles();

		// �ݹ��������file����������Ҫ���ļ�
		for (File i : sum) {
			if (i.isFile()) {
				if (i.getName().endsWith(fileType)) {
					// �����˼����У����ں������� �������һ���ж��ٸ��ļ�
					fileList.add(i);
				}
			}
			// �����˵ݹ��˼�룬������ļ��еĻ���Ӧ�ü�������ֱ���ҵ����ļ�Ϊֹ
			if (i.isDirectory()) {
				// ��ΪҪ�����ַ�������������""ת��Ϊ�ַ���
				getCertainTypeFiles("" + i.getAbsoluteFile(), fileType);
			}
		}

		return fileList;

	}

	public static void main(String[] args) {
		ViewAllFile viewAllFile = new ViewAllFile();
		System.out.println("������Ҫ�����ĵ�ַ");
		Scanner in = new Scanner(System.in);
		String fileDirectory = in.next();
		// �жϲ��Ϸ� ���·����Ч�Ͳ�Ҫ����������ִ���� ��������ַ֮��ͽ����ж�
		if (!viewAllFile.isExistDirectory(fileDirectory)) {
			System.out.println("��ַ��Ч");
			return;
		}

		System.out.println("������Ҫ�������ļ�����");
		String fileType = in.next();
		LinkedList<File> allFiles = null;
		try {
			// �õ����˺���ļ�����
			allFiles = viewAllFile.getCertainTypeFiles(fileDirectory, fileType);
			// ���ݼ����Ƿ���Ԫ���жϸ�·�����Ƿ��и��ļ�
			if (allFiles.isEmpty()) {
				System.out.println("��" + fileType + "�ļ� ");
				return;
			}
			// ������й��˺���ļ�
			for (File file : allFiles) {
				System.out.println(file);
			}
			System.out.println(fileDirectory + "�¹���" + fileType + "�ļ�" + allFiles.size() + "��");
		} catch (NoSuchFileException e) {
			System.out.println(e.getMessage());
		}
	}

}
