package com.atguigu.rs.client;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class Invoker {

	public static void main(String[] args) {
		CloseableHttpClient client =null;
		HttpGet httpGet = null;
		InputStream is = null;
		try {
			// 1.创建可自行关闭的Http请求客户端
			client = HttpClientBuilder.create().build();
			// 2.打开http请求链接
			httpGet = new HttpGet("http://pc201608140721:8080/ws_service/BookService_WS/book/getMap");
			// 3.提交请求,获取响应对象
			CloseableHttpResponse response = client.execute(httpGet);
			// 4.抓取响应体对象
			HttpEntity entity = response.getEntity();
			// 5.准备缓冲区,提前有用信息
			StringBuilder strBuilder = new StringBuilder();
			byte[] by = new byte[1024]; //编译读取缓冲区
			int len = 0; // 每次读取长度
			is = entity.getContent(); // 获取rs请求输入流
			while((len=is.read(by))!=-1){ // 循环读取
				strBuilder.append(new String(by, 0, len));
			}
			System.out.println(strBuilder.toString()); // 打印响应json串
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(is!=null){ // 关闭输入流
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(httpGet!=null){ // 关闭链接
				httpGet.completed(); // 关闭链接
			}
			if(client!=null){ // 关闭客户端
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
