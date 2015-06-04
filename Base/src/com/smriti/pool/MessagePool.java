package com.smriti.pool;

import android.os.Message;

public class MessagePool {
	
	public static Message getMessage(){
		Message message = new Message();
		return message;
	}
}
