package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author WQSSYQ
 *
 */
public class CompareTaxcode {
	
	public static ArrayList<String> readFile(String fn) throws Exception{
		ArrayList<String> arr01 = new ArrayList<String>();
		File f1 = new File(fn);
		FileReader fr01 = new FileReader(f1); 
		BufferedReader br = new BufferedReader(fr01);
		while(true){
			String s = br.readLine();
			if(s==null){
				break;
			}
			s = s.trim();
			int idx = s.indexOf(".");
			if(idx>0){
				s = s.substring(0, idx);
			}
			arr01.add(s);
		}
		
		Collections.sort(arr01);
		br.close();
		
		return arr01;
	}
	
	public static void comp(ArrayList<String> arrSj, ArrayList<String> arrSjk, String fnOut) throws Exception{
		int sj = 0;
		int sjk = 0;
		File f1 = new File(fnOut);
		FileOutputStream fos = new FileOutputStream(f1); 
		while(true){
			String sSj = arrSj.get(sj);
			String sSjk = arrSjk.get(sjk);
			int c = sSj.compareTo(sSjk);
			if(c==0)
			{
				fos.write((sSj+"\r\n").getBytes());
				sj++;
				sjk++;
			}else if(c<0){
				sj++;
			}else if(c>0){
				sjk++;
			}
			if(sj>arrSj.size()-1){
				break;
			}
			if(sjk>arrSjk.size()-1){
				break;
			}
		}
		fos.flush();
		fos.close();
	}

	public static void preComp(String fnSj, String fnSjk, String fnOut) throws Exception{
		ArrayList<String> arrSj = readFile(fnSj);
		ArrayList<String> arrSjk = readFile(fnSjk);
		comp(arrSj, arrSjk, fnOut);
	}
	
//	public static void testReadFile() throws Exception{
//		String fnSj = "F:/tmp/升级比对/test.txt";
//		ArrayList<String> arrSj = readFile(fnSj);
//	}

	public static void main(String[] args) {
		try {
			//testReadFile();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("第一个文件的路径：");
			String fnSj = br.readLine();
			System.out.println("第二个文件的路径：");
			String fnSjk = br.readLine();
			System.out.println("所生成文件的路径：");
			String fnOut = br.readLine();
			preComp(fnSj, fnSjk, fnOut);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
