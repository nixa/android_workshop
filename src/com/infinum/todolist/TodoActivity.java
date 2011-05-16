package com.infinum.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class TodoActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final EditText myEditText = (EditText) findViewById(R.id.editText1);
		ListView listView = (ListView) findViewById(R.id.listView1);

		final ArrayList<String> todoItems = new ArrayList<String>();
		final ArrayAdapter<String> todoItemsAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, todoItems);
		listView.setAdapter(todoItemsAdapter);

		myEditText.setOnKeyListener(new OnKeyListener() {

			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN)
					if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
						todoItems.add(myEditText.getText().toString());
						todoItemsAdapter.notifyDataSetChanged();

						myEditText.setText("");

						return true;
					}

				return false;
			}
		});
	}
}