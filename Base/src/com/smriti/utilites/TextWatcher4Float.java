package com.smriti.utilites;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


import junit.framework.Assert;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class TextWatcher4Float extends TextWatcher4 {

	public interface TextWatcher4FloatListener{
		public void textWatcher4FloatLeftError();
		public void textWatcher4FloatRightError();
		public void textWatcher4FloatSuccess();
	}
	
	private TextWatcher4FloatListener listener;
	private int partInt;
	private int partFloat;
		
	public TextWatcher4Float(EditText editText, int partInt, int partFloat, TextWatcher4FloatListener listener){
		super(editText);
		
		Assert.assertTrue(partInt >= 0 && partFloat >= 0 && partInt+partFloat > 0);
		
		this.partInt = partInt;
		this.partFloat = partFloat;
		this.listener = listener;
	}

	
    @Override
    public void beforeTextChanged(CharSequence s, int arg1, int arg2,
            int arg3) {
        temp = s;
        editTextWF.get().setSelection(s.length());
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
        
        if (editEnd > 0) {
        	char[] ch = new char[1];
            s.getChars(editStart-1, editEnd, ch, 0);
            if (ch[0] == '.') {
    			Log.w("info99", ch[0]+"");
    		}else {
				if (ch[0] > '9' || ch[0] <'0') {
					s.delete(editStart-1, editEnd);
				}
			}
		}
    }

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		
		Log.w("inf99", "match text = "+ editTextWF.get().getText());
		
		if (!hasFocus) {
			String regEx = "";  
			
			if (this.partFloat == 0) {
				
				if(editTextWF.get().getText().length() > this.partInt){
					this.listener.textWatcher4FloatLeftError();
					return;
				}
				regEx="\\.";  
				 Pattern pattern = Pattern.compile(regEx);  
				 Matcher matcher = pattern.matcher(editTextWF.get().getText());  
				 if(!matcher.find()){  
					 this.listener.textWatcher4FloatLeftError();
				 }else {
					 this.listener.textWatcher4FloatSuccess();
				}
				 
			}else 
			{
				int upint = this.partInt + 1;
				int upfloat = this.partFloat + 1;
				
				 regEx="[\\d]{"+upint+"}\\.{1}"; 
				 Pattern pattern = Pattern.compile(regEx);  
				 Matcher matcher = pattern.matcher(editTextWF.get().getText());  
			       
				 if(!matcher.find()){  
					 regEx="\\.{1}[\\d]{" + upfloat +"}"; 
					 pattern = Pattern.compile(regEx);  
					 matcher = pattern.matcher(editTextWF.get().getText());  
					 if (!matcher.find()) {
						 this.listener.textWatcher4FloatSuccess();
					}else {
						this.listener.textWatcher4FloatRightError();
					}
			     }else {
			    	 this.listener.textWatcher4FloatLeftError();
				}
			}
		}
	}


	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		
	}

}
