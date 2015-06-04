package com.smriti.controls;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class SMPlaceHolder extends LinearLayout {

	public SMPlaceHolder(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(com.smriti.R.layout.sm_place_holder, this, true);
	}

}
