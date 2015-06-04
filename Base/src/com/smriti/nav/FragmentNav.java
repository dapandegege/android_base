package com.smriti.nav;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentNav extends Nav {
	
	private static FragmentNav fragmentNav = new FragmentNav();
	public static FragmentNav defaultInstance() {
		return fragmentNav;
	}
	
	private FragmentActivity activity = null;
	private int rootLayoutID = 0;
	
	public void setActivity(FragmentActivity activity){
		this.activity = activity;
	}
	public FragmentActivity getActivity(){
		return activity;
	}
	public int getRootLayoutID() {
		return rootLayoutID;
	}
	public void setRootLayoutID(int rootLayoutID) {
		this.rootLayoutID = rootLayoutID;
	}
	
	
	public FragmentManager getFragmentManager(){
		return activity.getSupportFragmentManager();
	}

	public void replaceFragment(Fragment target, Bundle bundle, TransitType type) {
		if (bundle != null) {
			target.setArguments(bundle);
		}
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.setTransition(type.get());
		System.out.println("replaceFragment class name == "+target.getClass().getName());
		String tag = target.getClass().getName();
		transaction.replace(rootLayoutID, target,tag);
		transaction.commit();
	}
	public void pushFragment(Fragment target, Bundle bundle, TransitType type) {
		if (bundle != null) {
			target.setArguments(bundle);
		}
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.setTransition(type.get());
		System.out.println("pushFragment class name == "+target.getClass().getName());
		String tag = target.getClass().getName();
		transaction.replace(rootLayoutID, target,tag);
		transaction.addToBackStack(tag);
		transaction.commit();
	}
	
	public void popFragment() {
		getFragmentManager().popBackStack();
	}
	
	
}
