package com.smriti.nav;


import java.util.Iterator;
import java.util.Stack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentNav extends Nav {
	
	public enum FragmentPopType{
		FragmentPopType_Exclusive(0),FragmentPopType_Inclusive(1);
		private int value = 0;
	    private FragmentPopType(int value) {   
	        this.value = value;
	    }
	    public static FragmentPopType valueOf(int value) {    
	        switch (value) {
	        case 0:
	            return FragmentPopType_Exclusive;
	        case 1:
	            return FragmentPopType_Inclusive;
	        default:
	            return null;
	        }
	    }
	    public int value() {
	        return this.value;
	    }
	}
	
	private Stack<String> stackParas;
	private FragmentActivity activity = null;
	private int containerViewId = 0;
	
	private static FragmentNav fragmentNav = new FragmentNav();
	public static FragmentNav defaultInstance() {
		return fragmentNav;
	}
	
	
	public FragmentNav(){
		stackParas = new Stack<String>();
	}
	
	private FragmentManager getFragmentManager(){
		return activity.getSupportFragmentManager();
	}
	
	/*
	 * getters and setters
	 */
	public void setActivity(FragmentActivity activity){
		this.activity = activity;
	}
	public FragmentActivity getActivity(){
		return activity;
	}
	
	public int getcontainerViewId() {
		return containerViewId;
	}
	public void setcontainerViewId(int containerViewId) {
		this.containerViewId = containerViewId;
	}
	
	/*
	 * pop and push
	 */
	public void replaceFragment(Fragment target, Bundle bundle, TransitType type) {
		if (bundle != null) {
			target.setArguments(bundle);
		}
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.setTransition(type.get());
		String tag = target.getClass().getName();
		transaction.replace(containerViewId, target,tag);
		transaction.commit();
	}
	
	public void pushFragment(Fragment target, Bundle bundle, TransitType type) {
		if (bundle != null) {
			target.setArguments(bundle);
		}
		
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		transaction.setTransition(type.get());
		String tag = target.getClass().getName();
		transaction.replace(containerViewId, target,tag);
		transaction.addToBackStack(tag);
		transaction.commit();
		
		stackParas.push(tag);
	}
	
	public void popFragment() {
		getFragmentManager().popBackStack();
	}
	public void popFragmentContain(String tag){
		popFragment(tag, FragmentPopType.FragmentPopType_Inclusive);
	}
	public void popFragmentOnTop(String tag){
		popFragment(tag, FragmentPopType.FragmentPopType_Exclusive);
	}
	public void popFragment(String tag, FragmentPopType popType){
		if (tag == null) {
			return;
		}
		for (Iterator<String> iterator = stackParas.iterator(); iterator.hasNext();) {
			String ltag = (String) iterator.next();
			if (ltag.equals(tag)) {
				getFragmentManager().popBackStack(tag, popType.value);
				 stackParas.pop();
				break;
			}
		}
	}
	
}
