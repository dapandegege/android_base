package com.smriti.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BFragment extends Fragment {
	
	protected Object lockForObj = new Object();
	protected static Object lockForCls = new Object();
	
	private Context context;
	private View view;
	
	public Context getContext() {
		synchronized(lockForObj){
			if (context == null) {
				setContext(getActivity());
			}
		}
		return context;
	}
	public void setContext(Context context) {
		synchronized(lockForObj){
			this.context = context;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view==null){
			view = getRootView(inflater);
			viewWillAppear();
		}else{
			((ViewGroup)view.getParent()).removeView(view);
			viewWillReappear();
		}
		
		return view;
	}
	public abstract View getRootView(LayoutInflater inflater);
	public abstract void viewWillAppear();
	public abstract void viewWillReappear();
}
