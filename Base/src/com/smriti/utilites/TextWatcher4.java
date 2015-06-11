package com.smriti.utilites;

import java.lang.ref.WeakReference;

import junit.framework.Assert;

import android.annotation.SuppressLint;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewTreeObserver.OnWindowFocusChangeListener;
import android.widget.EditText;

@SuppressLint("NewApi")
public abstract class TextWatcher4 implements TextWatcher, OnFocusChangeListener, OnWindowFocusChangeListener {

	protected WeakReference<EditText> editTextWF;
	
	protected CharSequence temp;
	protected int editStart ;
	protected int editEnd ;
	
	public TextWatcher4(final EditText editText){
		Assert.assertTrue( editText != null);
		
		this.editTextWF = new WeakReference<EditText>(editText);
		
		editText.setOnFocusChangeListener(this);
	}

	
}
