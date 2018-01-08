package words.count;

import java.util.TreeMap;
import java.util.Map.Entry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*

2018��1��6��20:39:30
 *�������д��̫��ˣ����ֶ�������
 *
 *TODO
 */
public class CountWords {
	// �ڱ����ĵ�ʱ�ж��ַ��Ƿ�Ϊ���ŵķ���
	static boolean isSymbol(char str) {
		List<? extends Object> symbol = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
		return !symbol.contains(str);
	}

	static int count = 0;

	static TreeMap<String, Integer> countWords(String string) {

		// ��Ϊ��ʱ�ַ��������һ�����ʣ����ڼ���map��
		String temp = "";

		TreeMap<String, Integer> countMap = new TreeMap<String, Integer>();
		// �����ظ��Ž�������
		for (int i = 0; i < string.length(); i++) {
			// ֻҪ���Ƿ��� �ͰѸ��ַ��ۼ���������һ���ַ���--����
			if (!isSymbol(string.charAt(i))) {
				temp = temp + string.charAt(i);

			}
			//����temp != ""�Ƿǳ��б�Ҫ�ģ���Ϊ����ѭ�����жϣ�ÿһ����ĸ��Ҫ�ж�һ�Σ����tempһֱû����ӽ�ȥ����tempһֱΪ�մ����ͻ���ӵ�map��
			if (temp != "" && isSymbol(string.charAt(i))) {
				if (countMap.containsKey(temp)) {
					// System.out.println("wsd" + countMap.indexOf(temp));
					countMap.put(temp, countMap.get(temp) + 1);

				} else {
					countMap.put(temp, 1);
				}
				temp = "";
				count++;// ͳ�ƴ�Ƶ

			}
		}
		return countMap;
	}

	// ��treemap ����ֵ������
	static void orderCountMapByValue(TreeMap countMap) {
		// ��map.entrySet()ת����list
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\test\\80.txt")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(countMap.entrySet());
		// ͨ���Ƚ�����ʵ������
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				// ��������
				return -(o1.getValue().compareTo(o2.getValue()));
			}
		});
		for (Map.Entry mapping : list) {
			System.out.println(mapping.getKey() + "���ִ���" + ":" + mapping.getValue());
			String a = mapping.getKey().toString();
			String b = mapping.getValue().toString();

			try {
				out.write(a + "���ִ���" + b);
				out.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		try {

			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		// ��ȡ�ļ�
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\test\\345.txt")));
System.out.println("sun");
		String string = null;
		String string2 = "";
		while ((string = in.readLine()) != null) {
			// ���ı�д��һ���ַ���
			string2 = string + string2;
		}                                                                                                                 

		in.close();
		// �����е��ʷŵ�map����
		TreeMap countMap = countWords(string2);
		// ͨ��ֵ�����õ��Ĵ�������еĵ��ʣ�map����
		orderCountMapByValue(countMap);
		
		//Ϊ�˿��Ȿ��һ�����ٴʻ�
		Set s = countMap.keySet();
		int sum = 0;
		Iterator<String> iterator = s.iterator();
		while (iterator.hasNext()) {
			sum = sum + (int) countMap.get(iterator.next());
		}
		// ͳ���ж��ٵ���
		System.out.println(sum);
	}
}
