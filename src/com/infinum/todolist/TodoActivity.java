package com.infinum.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
					final int arg2, long arg3) {

				String text = (String) arg0.getItemAtPosition(arg2);
				
				AlertDialog.Builder dialogBuilder = new Builder(TodoActivity.this);
				dialogBuilder.setCancelable(true);
				dialogBuilder.setMessage(String.format("Jeste li sigurni da želite obrisati '%s'?", text));
				dialogBuilder.setTitle("Obriši Todo stavku");
				dialogBuilder.setPositiveButton("DA", new OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						todoItems.remove(arg2);
						todoItemsAdapter.notifyDataSetChanged();
					}
				});
				dialogBuilder.setNegativeButton("NE", new OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});				
				dialogBuilder.create().show();

				return true;
			}
			
		});
	}
}