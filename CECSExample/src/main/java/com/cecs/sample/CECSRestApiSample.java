package com.cecs.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class CECSRestApiSample {
	private static String BaseUrl = "https://storecertificates-kingoldcloud.uscom-east-1.oraclecloud.com/documents/api/1.2/files/";
	private static String Authorization = "Basic aGVsaXgubGl1QG9yYWNsZS5jb206QWJjZDEyMzQ=";

	/**
	 * 
	 * @param filePath 要上传的文件路径
	 * @param parentId CECS上父目录的ID
	 * @return 文件ID
	 * @throws ParseException
	 * @throws IOException
	 */
	public String uploadPDF(String filePath, String parentId) throws ParseException, IOException {
		File file = new File(filePath);
		FileInputStream inStream = new FileInputStream(file);
		CloseableHttpClient client = HttpClientBuilder.create().build();

		String url = BaseUrl + "data";
		HttpPost post = new HttpPost(url);
		post.addHeader("Authorization", Authorization);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();

		StringBuffer json = new StringBuffer();
		json.append("{").append("\"parentID\":\"").append(parentId).append("\"}");
		builder.addTextBody("jsonInputParameters", json.toString(), ContentType.APPLICATION_JSON);

		builder.addBinaryBody("primaryFile", inStream, ContentType.create("application/pdf"), "Oct.pdf");// 文件流

		HttpEntity entity = builder.build();
		post.setEntity(entity);
		HttpResponse response = client.execute(post);// 执行提交
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity responseEntity = response.getEntity();
		if ((200 <= statusCode && statusCode < 300) && responseEntity != null) {
			// 将响应内容转换为字符串
			String result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
			JSONObject jsonObj = JSONObject.parseObject(result);
			return jsonObj.getString("id");
		}

		return null;
	}

	public void downloadPDF(String FileId, String StorePath) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClientBuilder.create().build();

		String url = BaseUrl + FileId + "/data?version=1";
		HttpGet get = new HttpGet(url);
		get.addHeader("Authorization", Authorization);
		
		HttpResponse response = client.execute(get);// 执行提交
		System.out.println(response);
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity responseEntity = response.getEntity();
		if ((200 <= statusCode && statusCode < 300) && responseEntity != null) {
			// 将响应内容转换为字符串
			FileOutputStream out = new FileOutputStream(StorePath);
			InputStream in = responseEntity.getContent();
			int intByte;
			while((intByte=in.read()) != -1) {
				out.write(intByte);
			}
			out.close();
		}
	}

	public static void main(String[] args) throws ParseException, IOException {
		CECSRestApiSample sample = new CECSRestApiSample();
		//String fileId = sample.uploadPDF("D:\\报销\\Oct.pdf", "FAA9AD7C727B99E4AB7A74E2E6D54B9268C663A874D9");
		//System.out.println(fileId);
		
		sample.downloadPDF("DFADC5F0C0E0CF2B2059593045511E6DF44EDEF69FC8", "D:\\Oct.pdf");
	}

}
