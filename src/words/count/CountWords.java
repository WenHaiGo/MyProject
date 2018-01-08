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

2018年1月6日20:39:30
 *这个代码写的太差劲了，发现读不懂了
 *
 *TODO
 */
public class CountWords {
	// 在遍历文档时判断字符是否为符号的方法
	static boolean isSymbol(char str) {
		List<? extends Object> symbol = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
		return !symbol.contains(str);
	}

	static int count = 0;

	static TreeMap<String, Integer> countWords(String string) {

		// 作为临时字符串，存放一个单词，后期加入map中
		String temp = "";

		TreeMap<String, Integer> countMap = new TreeMap<String, Integer>();
		// 将不重复放进集合里
		for (int i = 0; i < string.length(); i++) {
			// 只要不是符号 就把该字符累加起来构成一个字符串--单词
			if (!isSymbol(string.charAt(i))) {
				temp = temp + string.charAt(i);

			}
			//这里temp != ""是非常有必要的，因为这是循环来判断，每一个字母都要判断一次，如果temp一直没有添加进去，则temp一直为空串，就会添加到map里
			if (temp != "" && isSymbol(string.charAt(i))) {
				if (countMap.containsKey(temp)) {
					// System.out.println("wsd" + countMap.indexOf(temp));
					countMap.put(temp, countMap.get(temp) + 1);

				} else {
					countMap.put(temp, 1);
				}
				temp = "";
				count++;// 统计词频

			}
		}
		return countMap;
	}

	// 给treemap 按照值来排序
	static void orderCountMapByValue(TreeMap countMap) {
		// 将map.entrySet()转换成list
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\test\\80.txt")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(countMap.entrySet());
		// 通过比较器来实现排序
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				// 升序排序
				return -(o1.getValue().compareTo(o2.getValue()));
			}
		});
		for (Map.Entry mapping : list) {
			System.out.println(mapping.getKey() + "出现次数" + ":" + mapping.getValue());
			String a = mapping.getKey().toString();
			String b = mapping.getValue().toString();

			try {
				out.write(a + "出现次数" + b);
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
		// 读取文件
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\test\\345.txt")));
System.out.println("sun");
		String string = null;
		String string2 = "";
		while ((string = in.readLine()) != null) {
			// 将文本写到一个字符串
			string2 = string + string2;
		}                                                                                                                 

		in.close();
		// 将所有单词放到map里面
		TreeMap countMap = countWords(string2);
		// 通过值来将得到的存放了所有的单词，map排序
		orderCountMapByValue(countMap);
		
		//为了看这本书一共多少词汇
		Set s = countMap.keySet();
		int sum = 0;
		Iterator<String> iterator = s.iterator();
		while (iterator.hasNext()) {
			sum = sum + (int) countMap.get(iterator.next());
		}
		// 统计有多少单词
		System.out.println(sum);
	}
}
