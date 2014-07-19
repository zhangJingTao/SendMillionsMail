package thread;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import util.TemplateUtil;

public class FileWriteThread implements Runnable{
	private Integer count;
	private String template;
	private List<String> paramsName;
	
	public FileWriteThread(){}
	
	public FileWriteThread(Integer count,String template,List<String> paramsName){
		this.count = count;
		this.template = template;
		this.paramsName = paramsName;
	}
	
	@Override
	public void run() {
		HashMap<String, String> params = new HashMap<String, String>();
		for (int i = 0; i < count; i++) {
			for (String name : paramsName) {
				params.put(name, UUID.randomUUID().toString());
			}
			try {
				String content = TemplateUtil.generateContent(template, params);
				FileUtils.writeStringToFile(new File("D:/ssd_file_test/"+UUID.randomUUID().toString()+".test"), content, "utf-8");
				System.out.println(Thread.currentThread().getName()+"生成的第"+(i+1)+"个文件");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
