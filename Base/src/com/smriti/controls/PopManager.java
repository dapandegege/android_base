package com.smriti.controls;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow.OnDismissListener;


public final class PopManager implements OnDismissListener {
	
	private static PopManager instance;  
	private Activity activity;
    public static synchronized PopManager getInstance() {  
    	if (instance == null) {  
            instance = new PopManager();  
        }  
        return instance; 
    }  
    
	public PopupWindow popAsDropDown(Activity activity, View locationView, ViewGroup contentView, int offX, int offY){
		this.activity = activity;
		PopupWindow popupWindow = new PopupWindow(contentView, 
									LayoutParams.MATCH_PARENT, 
									LayoutParams.MATCH_PARENT, true);
		popupWindow.showAsDropDown(locationView, offX, offY);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setOnDismissListener(instance);
		return popupWindow;
	}
	
	public PopupWindow popAsTranslucentDropDown(Activity activity, 
									View locationView, 
									float alpha,
									ViewGroup contentView, 
									int offX, int offY){
		this.activity = activity;
		PopupWindow popupWindow = new PopupWindow(contentView, 
									LayoutParams.MATCH_PARENT, 
									LayoutParams.MATCH_PARENT, true);
		popupWindow.showAsDropDown(locationView, offX, offY);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setOnDismissListener(instance);
		this.setBackgroundAlpha(0.8f);
		return popupWindow;
	}
	
	public void setBackgroundAlpha(float bgAlpha)  
    {  
        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();  
        layoutParams.alpha = bgAlpha; //0.0-1.0  
        activity.getWindow().setAttributes(layoutParams);  
    }

	@Override
	public void onDismiss() {
		// TODO Auto-generated method stub
		/*
		 * popupwindow.isShowing()
		 * popupwindow.dismiss();
		 */
		setBackgroundAlpha(1f);
	} 

}
