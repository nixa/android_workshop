package com.infinum.todolist;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;

public class ArticleClient {
	private static HttpClient httpClient = null;

	public static HttpClient getHttpClient() {
		if (httpClient == null)
			httpClient = new DefaultHttpClient();
		return httpClient;
	}

	public static InputStream HttpGetRequest(HttpGet request)
			throws IOException {
		HttpResponse response = null;
		InputStream stream = null;
		try {
			response = getHttpClient().execute(request);
			Log.d("nixa", request.getURI().toASCIIString() + " "
					+ response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() != 200)
				response = null;
			else
				stream = response.getEntity().getContent();
		} catch (ClientProtocolException e) {
			response = null;
			stream = null;
		} catch (IllegalStateException e) {
			response = null;
			stream = null;
		}
		return stream;
	}
}