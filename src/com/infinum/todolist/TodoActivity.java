package com.infinum.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

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

		myEditText.setOnEditorActionListener(new OnEditorActionListener() {

			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {

				if (actionId == EditorInfo.IME_ACTION_DONE) {
					todoItems.add(myEditText.getText().toString());
					todoItemsAdapter.notifyDataSetChanged();

					myEditText.setText("");

					return true;
				}

				return false;
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				String text = (String) arg0.getItemAtPosition(arg2);
				
				Toast.makeText(TodoActivity.this, String.format("Kliknuli ste na '%s'", text), Toast.LENGTH_SHORT).show();
				
				return true;
			}
			
		});

//		myEditText.setOnKeyListener(new OnKeyListener() {
//
//			public boolean onKey(View v, int keyCode, KeyEvent event) {
//				if (event.getAction() == KeyEvent.ACTION_DOWN)
//					if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
//						todoItems.add(myEditText.getText().toString());
//						todoItemsAdapter.notifyDataSetChanged();
//
//						myEditText.setText("");
//
//						return true;
//					}
//
//				return false;
//			}
//		});
	}
}