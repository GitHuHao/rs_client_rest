package com.atguigu.rs.util;

import org.codehaus.jettison.json.JSONException;
import com.google.gson.Gson;
/*
  		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>2.8.1</version>
		</dependency>
 */
public class JsonUtil {

	private static Gson gson = new Gson();
	
	public static <clazz> clazz getPojo(String json,Class clazz) throws JSONException{
		return (clazz)gson.fromJson(json, clazz);
	}
	
	public static String getGson(Object obj) throws JSONException{
		return gson.toJson(obj);
	}
	
	public static void main(String[] args) throws JSONException {
		String json = "{id:1,title:'tomcat',price:11.2}";
		Book book = getPojo(json,Book.class);
		System.out.println(book); // Book [id=1, title=tomcat, price=11.2]
		
		String gson = getGson(book);
		System.out.println(gson); // {"id":1,"title":"tomcat","price":11.2}
	}
	
	
}

class Book{
	private int id;
	private String title;
	private double price;
	public Book(int id, String title, double price) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
	}
	public Book() {
		super();
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", price=" + price + "]";
	}
}


