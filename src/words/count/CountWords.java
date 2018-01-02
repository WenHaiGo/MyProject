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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
 * 2017年12月3日08:05:16
 * 考察的还是字符串的处理，
 * 3. 请写出Java代码实现如下功能：读取一篇英文文章，输出其中出现次数最多的单词。
 *	先实现分割单词 统计词频 
 *实现工具：ArrayList 因为可以跟踪下标 
 *弄俩个ArrayList就够了    h
 *实现步骤
 *先去实现以空格分割的字符串
 *利用正则表达式处理这个是不会好一点？
 *
 *后期发现使用map更好
 *问题如何实现按照value排序
 *
 *方法实现汇总：
 *判断是否为符号 isSymbol
 *将统计结果输出到一张TreeMap里countWords
 *给treemap排序顺便输出统计结果print
 *
 */
public class CountWords {
	// 在遍历文档时判断字符是否为符号的方法
	static boolean isSymbol(char str) {
		ArrayList<Character> symbol = new ArrayList<Character>();
		symbol.add(' ');
		symbol.add('	');
		symbol.add(',');
		symbol.add('.');
		symbol.add('，');
		symbol.add('-');
		symbol.add('?');
		

		return symbol.contains(str);
	}

	static int count = 0;

	static TreeMap<String, Integer> countWords(String string) {
		// 待检验字符
		while (isSymbol(string.charAt(0))) {
			string = string.substring(1, string.length());
			
		}
		// 作为临时字符串，存放一个单词，后期加入字符串集合里
		String temp = "";

		TreeMap<String, Integer> countMap = new TreeMap<String, Integer>();
		// 将不重复放进集合里
		for (int i = 0; i < string.length(); i++) {
			// 只要不是符号 就把该字符累加起来构成一个字符串--单词
			if (!isSymbol(string.charAt(i))) {
				temp = temp + string.charAt(i);
				
			}
			// 如果是符号则认为是一个单词的结束，应该避免第一个字符是符号的问题或者当字符串遍历到结尾时而且结尾不是符号时是一个单词的结束
			// 一个棘手的问题是如果前边的字符是符号怎么办
			//还有棘手的问题是如果字符连续出现怎么办？？
			if (temp!=""&&isSymbol(string.charAt(i)) || ((i == string.length() - 1) && !isSymbol(string.charAt(i)))) {
				if (countMap.containsKey(temp)) {
					// System.out.println("wsd" + countMap.indexOf(temp));
					countMap.put(temp, (int) countMap.get(temp) + 1);

				} else {
					countMap.put(temp, 1);
				}

				temp = "";
				count++;// 统计词频

			}
		}
		return countMap;
	}

	static void orderCountMapByValue(TreeMap countMap) {
		// 将map.entrySet()转换成list
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\test\\90.txt")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String, Integer>>(countMap.entrySet());
		// 通过比较器来实现排序
		Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String,Integer> o2) {
				// 升序排序
				return -(o1.getValue().compareTo(o2.getValue()));
			}
		});
		for (Map.Entry mapping : list) {
			System.out.println(mapping.getKey()+"出现次数" + ":" + mapping.getValue());
			String a = mapping.getKey().toString();
			String b = mapping.getValue().toString();
			
			try {
				out.write(a+"出现次数"+b);
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
		//读取文件
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\test\\123.txt")));
		
		String string  = null;
		String string2  = "";
		while ((string=in.readLine()) != null) {
			//out.write(string);
			// bw.newLine();
			//将文本写到一个字符串
			string2 = string + string2;
		}

		in.close();
		//将所有单词放到map里面
		TreeMap countMap = countWords(string2);
		//通过值来将得到的存放了所有的单词，map排序
		orderCountMapByValue(countMap);
		//print(countMap);
		Set s = countMap.keySet();
		
		int sum = 0;
		Iterator<String > iterator = s.iterator();
		while(iterator.hasNext())
		{
			sum = sum + (int)countMap.get(iterator.next());
		}
		//统计有多少单词
		System.out.println(sum);
		

	}
}
