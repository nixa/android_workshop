package com.infinum.todolist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class TodoActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ListView articlesList = (ListView) findViewById(R.id.listView1);
		ArrayList<Article> articles = Article.getArticles();
		ArticlesAdapter articleAdapter = new ArticlesAdapter(this,
				android.R.layout.simple_list_item_1, articles);
		articlesList.setAdapter(articleAdapter);
		articleAdapter.notifyDataSetChanged();

		articlesList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent intent = new Intent(TodoActivity.this,
						DetailsActivity.class);
				Article article = (Article) arg0.getItemAtPosition(arg2);
				intent.putExtra("url", article.getUrl());
				startActivity(intent);
			}
		});
	}

	private class ArticlesAdapter extends ArrayAdapter<Article> {
		int resourceId;

		public ArticlesAdapter(Context context, int textViewResourceId,
				List<Article> objects) {
			super(context, textViewResourceId, objects);
			resourceId = textViewResourceId;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;

			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(resourceId, parent, false);
			}

			Log.d("nixa", getItem(position).getTitle());

			TextView title = (TextView) v.findViewById(android.R.id.text1);
			title.setText(getItem(position).getTitle());

			return v;
		}

	}
}