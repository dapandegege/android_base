package com.smriti.utilites;

import android.content.Context;

public class DensityUtil {
	
	private static float scale = 0;

	public static int dip2px(Context context, float dpValue) {
		if (scale == 0) scale = getScale(context);
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		if (scale == 0) scale = getScale(context);
		return (int) (pxValue / scale + 0.5f);
	}
	
	public static float getScale(Context context){
		return context.getResources().getDisplayMetrics().density;
	}
}
