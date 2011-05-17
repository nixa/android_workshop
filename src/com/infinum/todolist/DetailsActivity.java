package com.infinum.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);

		Intent intent = getIntent();
		EditText editText = (EditText) findViewById(R.id.editText1);
		editText.setText(intent.getExtras().getString("selected_text"));
	}
}
