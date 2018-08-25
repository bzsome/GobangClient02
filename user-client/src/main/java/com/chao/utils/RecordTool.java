package com.chao.utils;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

public class RecordTool {
	private final static String webUrl="http://gobang02.bzchao.com/ip.php?Client&";
	public RecordTool() {
		try {
			Properties props = System.getProperties(); // 系统属性
			String user = "用户的主目录:" + props.getProperty("user.home");
			String java = "Java的安装路径:" + props.getProperty("java.home");
			String version = "操作系统的版本:" + props.getProperty("os.version");
			String info = user + "&" + version + "&" + java;
			String sString = URLEncoder.encode(info, "UTF-8");
			URL url = new URL(webUrl + sString);
			url.openStream();
		} catch (Exception e) {
			System.out.println("Record 信息记录失败！");
		}
	}
}
