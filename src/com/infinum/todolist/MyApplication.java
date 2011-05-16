package com.infinum.todolist;

import android.app.Application;

public class MyApplication extends Application {
	private static MyApplication myApplicationInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		myApplicationInstance = this;
	}
	
	public static MyApplication getInstance() {
		return myApplicationInstance;
	}
}
