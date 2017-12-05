package words.count;
import java.util.TreeMap;
import java.util.Map.Entry;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
 * 2017��12��3��08:05:16
 * ����Ļ����ַ����Ĵ���
 * 3. ��д��Java����ʵ�����¹��ܣ���ȡһƪӢ�����£�������г��ִ������ĵ��ʡ�
 *	��ʵ�ַָ�� ͳ�ƴ�Ƶ 
 *ʵ�ֹ��ߣ�ArrayList ��Ϊ���Ը����±� 
 *Ū����ArrayList�͹���    h
 *ʵ�ֲ���
 *��ȥʵ���Կո�ָ���ַ���
 *����������ʽ��������ǲ����һ�㣿
 *
 *���ڷ���ʹ��map����
 *�������ʵ�ְ���value����
 *
 *����ʵ�ֻ��ܣ�
 *�ж��Ƿ�Ϊ���� isSymbol
 *��ͳ�ƽ�������һ��TreeMap��countWords
 *��treemap����˳�����ͳ�ƽ��print
 *
 */
public class CountWords {
	// �ڱ����ĵ�ʱ�ж��ַ��Ƿ�Ϊ���ŵķ���
	static boolean isSymbol(char str) {
		ArrayList<Character> symbol = new ArrayList<Character>();
		symbol.add(' ');
		symbol.add('	');
		symbol.add(',');
		symbol.add('.');
		symbol.add('��');
		symbol.add('-');
		symbol.add('?');
		

		return symbol.contains(str);
	}

	static int count = 0;

	static TreeMap<String, Integer> countWords(String string) {
		// �������ַ�
		while (isSymbol(string.charAt(0))) {
			string = string.substring(1, string.length());
			
		}
		// ��Ϊ��ʱ�ַ��������һ�����ʣ����ڼ����ַ���������
		String temp = "";

		TreeMap countMap = new TreeMap<String, Integer>();
		// �����ظ��Ĳ��ҷŽ�������
		for (int i = 0; i < string.length(); i++) {
			// ֻҪ���Ƿ��� �ͰѸ��ַ��ۼ���������һ���ַ���--����
			if (!isSymbol(string.charAt(i))) {
				temp = temp + string.charAt(i);
				
			}
			// ����Ƿ�������Ϊ��һ�����ʵĽ�����Ӧ�ñ����һ���ַ��Ƿ��ŵ�������ߵ��ַ�����������βʱ���ҽ�β���Ƿ���ʱ��һ�����ʵĽ���
			// һ�����ֵ����������ǰ�ߵ��ַ��Ƿ�����ô��
			//���м��ֵ�����������ַ�����������ô�죿��
			if (temp!=""&&isSymbol(string.charAt(i)) || ((i == string.length() - 1) && !isSymbol(string.charAt(i)))) {
				if (countMap.containsKey(temp)) {
					// System.out.println("wsd" + countMap.indexOf(temp));
					countMap.put(temp, (int) countMap.get(temp) + 1);

				} else {
					countMap.put(temp, 1);
				}

				temp = "";
				count++;// ͳ�ƴ�Ƶ

			}
		}
		return countMap;
	}

	static void orderCountMapByValue(TreeMap countMap) {
		// ��map.entrySet()ת����list
		List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String, Integer>>(countMap.entrySet());
		// ͨ���Ƚ�����ʵ������
		Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String,Integer> o2) {
				// ��������
				return -(o1.getValue().compareTo(o2.getValue()));
			}
		});
		for (Map.Entry mapping : list) {
			System.out.println(mapping.getKey()+"���ִ���" + ":" + mapping.getValue());
		}

	}

	

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\test\\123.txt")));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\test\\90.txt")));
		String string  = null;
		String string2  = "";
		while ((string=in.readLine()) != null) {
			out.write(string);
			// bw.newLine();
			string2 = string + string2;
		}

		in.close();
		out.close();
		TreeMap countMap = countWords(string2);
		orderCountMapByValue(countMap);
		//print(countMap);

	}
}
