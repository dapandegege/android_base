package com.smriti.utilites;

import com.google.gson.Gson;

public class GsonUtil {

	public static <T> T string2Bean(String result,Class<T> clazz){
		 Gson gson = new Gson();
		 T t = gson.fromJson(result, clazz);
		 return t;
	}
	public static <T> T beanFromString(String result,Class<T> clazz){
		 Gson gson = new Gson();
		 T t = gson.fromJson(result, clazz);
		 return t;
	}
}
