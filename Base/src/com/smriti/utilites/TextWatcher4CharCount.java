package com.smriti.utilites;

import java.lang.ref.WeakReference;

import junit.framework.Assert;
import android.R.bool;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

@SuppressWarnings("unused")

public class TextWatcher4CharCount extends TextWatcher4 {

	public interface TextWatcher4CharCountListener{
		public void textWatcher4CurrentNumber(int number);
	}
	
	private TextWatcher4CharCountListener listener;
	private int down;
	private int up;
	
	
	public TextWatcher4CharCount(EditText editText, int down, int up, TextWatcher4CharCountListener listener){
		super(editText);
		
		Assert.assertTrue(up >= 0 && down >= 0 && down+up > 0 && up >= down);
				
		this.down = down;
		this.up = up;
		this.listener = listener;
	}
	
	public boolean isCharNumberRight(){
		int count = editTextWF.get().length();
		return count >= down && count <= up;
	}

	public boolean isCharNumberRight(EditText editText){
		int count = editText.length();
		return count >= down && count <= up;
	}
    
    @Override
    public void beforeTextChanged(CharSequence s, int arg1, int arg2,
            int arg3) {
        temp = s;
    }
   
    @Override
    public void onTextChanged(CharSequence s, int arg1, int arg2,
            int arg3) {
    }
   
    @Override
    public void afterTextChanged(Editable s) {
        editStart = editTextWF.get().getSelectionStart();
        editEnd = editTextWF.get().getSelectionEnd();
        
        Log.w("info99", "editStart = " + editStart);
        Log.w("info99", "editEnd = " + editEnd);
        
        if (temp.length() > up) {
           
            s.delete(editStart-1, editEnd);
            int tempSelection = editStart;
            editTextWF.get().setText(s);
            editTextWF.get().setSelection(tempSelection);
        }
        if (this.listener != null) {
			this.listener.textWatcher4CurrentNumber(editTextWF.get().length());
		}
    }

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		
	}

}
