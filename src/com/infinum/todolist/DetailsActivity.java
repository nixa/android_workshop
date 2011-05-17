package com.infinum.todolist;

import java.io.IOException;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);

		Intent intent = getIntent();
		ImageView image = (ImageView) findViewById(R.id.imageView1);
		String image_url = intent.getExtras().getString("url");

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(image_url);
		try {
			HttpResponse response = httpClient.execute(get);
			image.setImageBitmap(BitmapFactory.decodeStream(response
					.getEntity().getContent()));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
