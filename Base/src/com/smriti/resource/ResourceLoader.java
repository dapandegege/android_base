package com.smriti.resource;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public final class ResourceLoader {
	
	public static LayoutInflater getLayoutInflater(Context context){
		return LayoutInflater.from(context);
	}
	
	public static ViewGroup getViewGroupLayout(Context context, int layout, ViewGroup root){
		return getViewGroupLayout(context, layout, root, root!=null);
	}
	
	public static ViewGroup getViewGroupLayout(Context context, int layout, 
													ViewGroup root, 
													boolean isAttachToRoot)
	{
		LayoutInflater inflater = getLayoutInflater(context);
		ViewGroup viewGroup = (ViewGroup) inflater.inflate(layout, root, isAttachToRoot);
		return viewGroup;
	}

}
