package test;

import java.io.FileInputStream;

public class TestUrlDecode {

	public static void test01(){
		try {
			String data = "cConsigCompany%22%3A%22%E5%8C%97%E4%BA%AC%E7%89%A9%E7%BE%8E%22%2C%22cFreightGUID";
			String decStr = java.net.URLDecoder.decode(data,   "utf-8");
			System.out.println(decStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void test02(){
		try {
			String cls = "D:/Aisino/A6V6.2/logs/submit_data.txt";
			FileInputStream fis = new FileInputStream(cls);
			byte[] buf = new byte[1024*64];
			int len = fis.read(buf);
			String data = new String(buf, 0, len);
			String decStr = java.net.URLDecoder.decode(data,   "utf-8");
			System.out.println(decStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		//test01();
		test02();
	}

}
