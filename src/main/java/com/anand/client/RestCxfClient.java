/**
 * 
 */
package com.anand.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * @author Anand
 *
 */
public class RestCxfClient {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		new RestCxfClient().getBook(5);
		// new RestCxfClient().getApache();
	}

	public void getBook(int number)
	{
		String output = "";
		String url = "http://localhost:8080/RestfulWsCxfServer/rest/prime/";
		try {
			url = url + URLEncoder.encode(String.valueOf(number), "UTF-8");

			HttpClient client = new HttpClient();
			GetMethod post = new GetMethod(url);
			// PostMethod post = new PostMethod(url);
			client.executeMethod(post);
			Header header = new Header();
			header.setName("content-type");
			header.setValue("application/x-www-form-urlencoded");
			// header.setName("accept");
			// header.setValue("text/plain");
			post.addRequestHeader(header);
			client.executeMethod(post);
			output = post.getResponseBodyAsString();
			post.releaseConnection();
			System.out.println("Ooutput - " + output);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getApache()
	{
		String url = "http://www.apache.org/";
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();

		// Create a method instance.
		GetMethod method = new GetMethod(url);

		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			System.out.println(new String(responseBody));

		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}

}
