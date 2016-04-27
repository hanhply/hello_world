package com.primeto.zyzx.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UserInfoClient {
	public static final String BASE_URI = "http://localhost:80/jerseyDemo/rest";
	public static final String PATH_NAME = "/UserInfoService/name/";
	public static final String PATH_AGE = "/UserInfoService/age/";
	public static final String PATH_AGES = "/UserInfoService/age";
	public static void main(String[] args) {
		String name = "Pavithra";
		int age = 25;
		String ages="25";
		String nameTargetURL = BASE_URI + PATH_NAME + name;
		String ageTargetURL = BASE_URI + PATH_AGE + age;
		String ageTargetURLS = BASE_URI + PATH_AGES ;
//		String nameTargetURL = "app_key="+app_key;
		try {
			URL targetUrl = new URL(nameTargetURL);// 完整的URL
			HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Content-Type", "application/json");
			System.out.print("请求方法:\n" + httpConnection.getRequestMethod() + "\n");
			System.out.print("请求路径:\n" + httpConnection.getURL() + "\n");
			System.out.print("返回状态码:\n" + httpConnection.getResponseCode() + "\n");
			System.out.print("返回状态:\n" + httpConnection.getResponseMessage() + "\n");
			System.out.println("==============="+httpConnection.getResponseCode());
			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code:" + httpConnection.getResponseCode());
			}
			BufferedReader responseBuffer = new BufferedReader(
					new InputStreamReader((httpConnection.getInputStream())));
			String output;
			while ((output = responseBuffer.readLine()) != null) {
				System.out.println("返回请求结果:" + output);
			}
			httpConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}