package com.infinum.todolist;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Article {
	private static String AUTHOR_FIELD = "author";
	private static String TITLE_FIELD = "title";

	private String author = null;
	private String title = null;
	private String url = null;

	public Article(JSONObject json) {
		try {
			author = json.getString(AUTHOR_FIELD);
			title = json.getString(TITLE_FIELD);
			url = json.getJSONArray("media").getJSONObject(0).getString("url");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public static ArrayList<Article> getArticles() {
		ArrayList<Article> articles = new ArrayList<Article>();
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(
				"http://www.24sata.hr/ws/v1/article?category_id=show");
		try {
			HttpResponse response = httpClient.execute(get);
			InputStream data = response.getEntity().getContent();
			JSONArray articlesJsonArray = new JSONArray(
					IOUtils.convertStreamToString(data));

			for (int i = 0; i < articlesJsonArray.length(); i++) {
				articles.add(new Article(articlesJsonArray.getJSONObject(i)));
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return articles;
	}
}