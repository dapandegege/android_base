package com.smriti.image;

import java.util.Map;
import android.graphics.Bitmap;

public class ImageUpPara {
	
	public ImageUpPara(String url, String filePath, Bitmap bitmap, Map<String, String> keyValues){
		this.url = url;
		this.filePath = filePath;
		this.bitmap = bitmap;
		this.keyValues = keyValues;
	}
	
	String url;
	String filename;
	String filePath;
	Bitmap bitmap;
	
	Map<String, String> keyValues;

}
