package test;

import java.io.FileInputStream;

/**
 * 直接读取 .class 文件，可以将中文字符串变量值从 utf-8 转成 gbk。
 * 貌似没什么作用。
 * @author user
 *
 */
public class ReadCls {
	
	public static void test01(){
		try {
			String cls = "D:/hx/workspace/TestA66.2/bin/test/DecZip.class";
			FileInputStream fis = new FileInputStream(cls);
			byte[] buf = new byte[1024*64];
			int len = fis.read(buf);
			String s = new String(buf, 0, len);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		test01();

	}

}
