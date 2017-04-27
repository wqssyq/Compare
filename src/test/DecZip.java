package test;
import java.io.*;
import java.util.jar.*;
import java.util.zip.*;

public class DecZip {
	public static void decZip(String fn, String dir){
		try {
			ZipInputStream Zin=new ZipInputStream(new FileInputStream(fn));//输入源zip路径
			BufferedInputStream Bin=new BufferedInputStream(Zin);
			String Parent=dir; //输出路径（文件夹目录）
			File Fout=null;
			ZipEntry entry;
			try {
				while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
					String entName = entry.getName();
					System.out.println(entName);
				
//				while(true){
//					entry = Zin.getNextEntry();
//					String entName = entry.getName();
//					System.out.println(entName);
//					if(entry!=null && !entry.isDirectory()){
//						break;
//					}

					Fout=new File(Parent,entry.getName());
					if(!Fout.exists()){
						(new File(Fout.getParent())).mkdirs();
					}
					FileOutputStream out=new FileOutputStream(Fout);
					BufferedOutputStream Bout=new BufferedOutputStream(out);
					int b;
					while((b=Bin.read())!=-1){
						Bout.write(b);
					}
					Bout.close();
					out.close();
					System.out.println(Fout+"解压成功");	
				}
				Bin.close();
				Zin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void decJar(String fn, String dir){
		long startTime=System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		try {
			JarInputStream jin=new JarInputStream(new FileInputStream(fn));//输入源zip路径
			BufferedInputStream Bin=new BufferedInputStream(jin);
			String Parent=dir; //输出路径（文件夹目录）
			File Fout=null;
			JarEntry entry;
			//while((entry = jin.getNextJarEntry())!=null && !entry.isDirectory()){
			
			int fnIdx = 0;
			
			while(true){
				entry = jin.getNextJarEntry();
				if(entry==null){
					break;
				}
				String entName = entry.getName();
				//System.out.println( "71 entName:" + entName);

				if(entry.isDirectory()){
					continue;
				}

				int idx = entName.lastIndexOf("/") + 1;
				if(idx>0){
					String path = entName.substring(0, idx);
					String fName = entName.substring(idx);
					if(fName.length()>100){
						sb.append(entName).append("\r\n");
						//System.out.println("81 path:" + path);
						//System.out.println("82 fName:" + fName);
						//entName = path + "c_" + fnIdx + ".class";
						entName = "c_" + fnIdx + ".class";
						sb.append(entName).append("\r\n");
						//System.out.println("85 entName:" + entName);
						fnIdx++;
					}
				}
				
				Fout=new File(Parent,entName);
				if(!Fout.exists()){
					(new File(Fout.getParent())).mkdirs();
				}
				FileOutputStream out=new FileOutputStream(Fout);
				BufferedOutputStream Bout=new BufferedOutputStream(out);
				int b;
				while((b=Bin.read())!=-1){
					Bout.write(b);
				}
				Bout.close();
				out.close();
				//System.out.println(Fout+"解压成功");	
			}
			Bin.close();
			jin.close();
			
			
			Fout=new File(Parent,"files.txt");
			if(!Fout.exists()){
				(new File(Fout.getParent())).mkdirs();
			}
			FileOutputStream out=new FileOutputStream(Fout);
			out.write(sb.toString().getBytes());
			out.flush();
			out.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime=System.currentTimeMillis();		
		System.out.println(fn + "  haofei 耗费时间： "+(endTime-startTime)+" ms");
	}
	
	public static void testJar(){
		String fn = "D:/Aisino/A6V6.2/web/WEB-INF/lib/AOS6.3.1-main-1793_150921.jar";
		                                            //AOS6.3.1-main-1793_150921.jar
		String dir = "D:/Aisino/A6V6.2/libs/AOS6.3.1-main-1793_150921.jar";
		decJar(fn, dir);
	}

	public static void testJars(){
		String libDir = "D:/Aisino/A6V6.2/web/WEB-INF/lib";
		String decDir = "D:/Aisino/A6V6.2/libs/";
		File f = new File(libDir);
		File[] ff = f.listFiles();
		for(int i=0; i<ff.length; i++){
			//System.out.println(ff[i].getName());
			//System.out.println(ff[i].getAbsolutePath());
			decJar(ff[i].getAbsolutePath(), decDir + ff[i].getName());
		}
	}
	
	
	public static void testZip(){
		String fn = "F:/soft/jdk/jdk-8u77-windows-x64-demos/jdk1.8.0_77/demo/jfc/SwingSet2/src.zip";
		String dir = "D:/Aisino/A6V6.2/libs/SwingSet2";
		decZip(fn, dir);
	}

	public static void main(String[] args) {

		testJar();
		//testZip();
		//testJars();
	}

}
