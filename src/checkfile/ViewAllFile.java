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

	// 判断路径是否合法
	boolean isExistDirectory(String directory) {
		File file = new File(directory);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	LinkedList<File> fileList = new LinkedList<File>();// 由于每次都需要递归，所以每次都会更新这个集合，看来是没办法把这个集合放到方法内部

	LinkedList<File> getCertainTypeFiles(String fileDirectory, String fileType) throws NoSuchFileException {

		File file = new File(fileDirectory);
		// 将所有文件放到File数组里面
		File[] sum = file.listFiles();

		// 递归遍历所有file，过滤所需要的文件
		for (File i : sum) {
			if (i.isFile()) {
				if (i.getName().endsWith(fileType)) {
					// 存入了集合中，便于后续处理 比如输出一共有多少个文件
					fileList.add(i);
				}
			}
			// 利用了递归的思想，如果是文件夹的话就应该继续查找直到找到了文件为止
			if (i.isDirectory()) {
				// 因为要求传入字符串，所以利用""转换为字符串
				getCertainTypeFiles("" + i.getAbsoluteFile(), fileType);
			}
		}

		return fileList;

	}

	public static void main(String[] args) {
		ViewAllFile viewAllFile = new ViewAllFile();
		System.out.println("请输入要检索的地址");
		Scanner in = new Scanner(System.in);
		String fileDirectory = in.next();
		// 判断不合法 如果路径无效就不要让我在往下执行了 最好输完地址之后就进行判断
		if (!viewAllFile.isExistDirectory(fileDirectory)) {
			System.out.println("地址无效");
			return;
		}

		System.out.println("请输入要检索的文件类型");
		String fileType = in.next();
		LinkedList<File> allFiles = null;
		try {
			// 得到过滤后的文件集合
			allFiles = viewAllFile.getCertainTypeFiles(fileDirectory, fileType);
			// 根据集合是否有元素判断该路径下是否有该文件
			if (allFiles.isEmpty()) {
				System.out.println("无" + fileType + "文件 ");
				return;
			}
			// 输出所有过滤后的文件
			for (File file : allFiles) {
				System.out.println(file);
			}
			System.out.println(fileDirectory + "下共有" + fileType + "文件" + allFiles.size() + "个");
		} catch (NoSuchFileException e) {
			System.out.println(e.getMessage());
		}
	}

}
