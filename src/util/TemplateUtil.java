package util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class TemplateUtil {
	/**
	 * ����ʼ�
	 * 
	 * @param modelId
	 * @param parames
	 * @return
	 * @throws Exception
	 */
	public static String generateContent(String templateContent,
			HashMap<String, String> parames) throws Exception {
		try {
			String finalString = templateContent;
			for (String keyValue : getParamtersName(finalString)) {
				String value = parames.get(keyValue);
				if (value == null)
					value = "";
				finalString = finalString.replaceAll("\\$\\{" + keyValue
						+ "\\}", value);
			}
			return finalString;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * ƥ�� ffffx${a_B_c}ddd${bbb} ���� a_B_c bbb��list
	 * 
	 * @param lineStr
	 * @return
	 */
	public static List<String> getParamtersName(String lineStr) {
		List<String> result = new ArrayList<String>();
		Pattern p = Pattern.compile("\\$\\{[^\\}]*\\}");
		Matcher m = p.matcher(lineStr);
		while (m.find()) {
			String get = m.group();
			result.add(get.replace("$", "").replace("{", "").replace("}", ""));
		}
		return result;
	}
}
