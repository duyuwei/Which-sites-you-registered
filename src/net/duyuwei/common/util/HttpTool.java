package net.duyuwei.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import net.sf.json.JSONObject;

//com.weixin.manager.XmlTool;

/**
 * http工具类
 * (功能详细描述)
 * @author       duyuwei
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        你注册过哪些网站1.0, 2015年10月20日
 */
public class HttpTool {

	/**
	* 向指定 URL 发送POST方法的请求
	* 
	* @param url
	*            发送请求的 URL
	* @param param
	*            请求参数，map会将结果转化为 name1=value1&name2=value2 的形式。
	* @return 所代表远程资源的响应结果(JSON)
	 * @throws Exception 
	*/
	public static JSONObject sendPost(String url, Map<String, Object> map) throws Exception {
		JSONObject jsonObject = JSONObject.fromObject(sendPostStr(url, map));
		return jsonObject;
	}

//	/**
//	 * 向指定 URL 发送POST方法的请求
//	 * (一句话功能简述)
//	 * (功能详细描述)
//	 * @author       huangqingjian/0050
//	 * @see          相关函数,对于重要的函数建议注释
//	 * @since        清清网系统, 2015年9月10日
//	 * @param url 发送请求的 URL
//	 * @param map 请求参数，map会将结果转化为 name1=value1&name2=value2 的形式。
//	 * @return 所代表远程资源的响应结果(xml)
//	 * @throws Exception
//	 */
//	public static Map<String, Object> sendPostXml(String url, Map<String, Object> map) throws Exception {
//		Map<String, Object> result = null;
//		result = XmlTool.parseXml(sendPostStr(url, map));
//		return result;
//	}

	/**
	* 向指定 URL 发送POST方法的请求
	* 
	* @param url
	*            发送请求的 URL
	* @param map
	*            请求参数，map会将结果转化为 name1=value1&name2=value2 的形式。
	* @return 所代表远程资源的响应结果字符串类型
	 * @throws Exception 
	*/
	public static String sendPostStr(String url, Map<String, Object> map) throws Exception {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			StringBuilder builder = new StringBuilder();
			if (null != map) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					if (entry.getValue() != "") {
						builder.append(entry.getKey() + "=" + entry.getValue() + "&");
					}
				}
			}
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(builder.toString());
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}

		} catch (Exception e) {
			throw e;
		}
		//使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;

	}

	/**
	* 向指定 URL 发送get方法的请求
	* 
	* @param url
	*            发送请求的 URL
	* @param param
	*            请求参数，map会将结果转化为 name1=value1&name2=value2 的形式。
	* @return 所代表远程资源的响应结果(JSON)
	 * @throws Exception 
	*/
	public static JSONObject sendGet(String url, Map<String, Object> map) throws Exception {
		JSONObject jsonObject = JSONObject.fromObject(sendGetStr(url, map));
		return jsonObject;
	}

//	/**
//	 * 向指定 URL 发送get方法的请求
//	 * (一句话功能简述)
//	 * (功能详细描述)
//	 * @author       huangqingjian/0050
//	 * @see          相关函数,对于重要的函数建议注释
//	 * @since        清清网系统, 2015年9月10日
//	 * @param url 发送请求的 URL
//	 * @param map 请求参数，map会将结果转化为 name1=value1&name2=value2 的形式。
//	 * @return 所代表远程资源的响应结果(xml)
//	 * @throws Exception
//	 */
//	public static Map<String, Object> sendGetXml(String url, Map<String, Object> map) throws Exception {
//		Map<String, Object> result = null;
//		result = XmlTool.parseXml(sendGetStr(url, map));
//		return result;
//	}

	/**
	* 向指定 URL 发送GET方法的请求
	* 
	* @param url
	*            发送请求的 URL
	* @param map
	*            请求参数，map会将结果转化为 name1=value1&name2=value2 的形式。
	* @return 所代表远程资源的响应结果(字符串类型)
	 * @throws Exception 
	*/
	public static String sendGetStr(String url, Map<String, Object> map) throws Exception {
		String result = "";
		BufferedReader in = null;
		try {
			StringBuilder builder = new StringBuilder();
			if (null != map) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					if (entry.getValue() != "") {
						builder.append(entry.getKey() + "=" + entry.getValue() + "&");
					}
				}
			}
			String urlNameString = url + "?" + builder.toString();
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			throw e;
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}
