package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import thread.FileWriteThread;
import util.TemplateUtil;

/**
 * 100w 文件生成
 * @author ZhangJingtao
 *
 */
public class TestFile {
	public static Integer FILE_COUNT = 1000000;

	public static void main(String[] args) throws InterruptedException, IOException {
		test(100);
	}
	
	
	public static void test(Integer threadCount) throws IOException, InterruptedException{
		System.out.println("==============");
		Integer threadSize = threadCount;
		Integer fileCount = FILE_COUNT/threadSize;
		String template = FileUtils.readFileToString(new File("D:/work_luna/MultiThreadFileTest/src/template/revoke.template"), "utf-8");
		System.out.println("template:");
		System.out.println(template);
		List<String> paramName = TemplateUtil.getParamtersName(template);
		Long start = System.currentTimeMillis();
		List<Runnable> threads = new ArrayList<Runnable>();
		//20线程写文件
		for (int i = 0; i < threadSize; i++) {
			FileWriteThread thread = new FileWriteThread(fileCount,template,paramName);
			new Thread(thread).start();
		}
		System.out.println("==============");
		System.out.println("======耗时========"+(System.currentTimeMillis()-start));
	}
}
