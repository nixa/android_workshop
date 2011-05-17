package com.infinum.todolist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		String calling_number = arg1.getExtras().getString(
				android.telephony.TelephonyManager.EXTRA_INCOMING_NUMBER);
		String call_state = arg1.getExtras().getString(
				TelephonyManager.EXTRA_STATE);
		
		Log.d("DORS/CLUC", calling_number + " is " + call_state);
	}
}
