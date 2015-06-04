package com.smriti.utilites;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import junit.framework.Assert;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Size;

@SuppressLint("NewApi")
public class BitmapUtil {
	
public static Bitmap loadBitmapToSize(String filePath, Size size) { 
		
		Assert.assertTrue(size.getHeight() != 0 && size.getWidth() != 0);
		
		FileInputStream fileIS = null;  
		BufferedInputStream bufferIS = null;  
		
		try {  
			fileIS = new FileInputStream(filePath);  
			bufferIS = new BufferedInputStream(fileIS);  
		    BitmapFactory.Options options = setBitmapOption(filePath, size.getWidth(), size.getHeight());  
		    return BitmapFactory.decodeStream(bufferIS, null, options);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		} finally {  
		    try {  
		    	bufferIS.close();  
		    	fileIS.close();  
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }  
		}  
		return null;  	
	}  
	
	public static Bitmap loadBitmapToWidth(String filePath, int width) { 
		
		Assert.assertTrue(width != 0);
		
		FileInputStream fileIS = null;  
		BufferedInputStream bufferIS = null;  
		
		try {  
			fileIS = new FileInputStream(filePath);  
			bufferIS = new BufferedInputStream(fileIS);  
		    BitmapFactory.Options options = setBitmapOption(filePath, width);  
		    return BitmapFactory.decodeStream(bufferIS, null, options);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		} finally {  
		    try {  
		    	bufferIS.close();  
		    	fileIS.close();  
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }  
		}  
		return null;  	
	}  

	private static BitmapFactory.Options setBitmapOption(String file, int width, int height) {
		
		BitmapFactory.Options opt = new BitmapFactory.Options();  
		opt.inJustDecodeBounds = true;  //设置只是解码图片的边距，此操作目的是度量图片的实际宽度和高度           
		BitmapFactory.decodeFile(file, opt);  //获得图片的实际高和宽  
		int outWidth = opt.outWidth; 
		int outHeight = opt.outHeight;  
		opt.inDither = false;  
		
		//设置加载图片的颜色数为16bit，默认是RGB_8888，表示24bit颜色和透明通道，但一般用不上  
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		//设置缩放比,1表示原比例，2表示原来的四分之一
		opt.inSampleSize = 1;    
		        
		if (outWidth != 0 && outHeight != 0) {  
			//计算缩放比  
		    int sampleSize = (outWidth / width + outHeight / height) / 2;  
		    opt.inSampleSize = sampleSize;  
		}  

		opt.inJustDecodeBounds = false;//最后把标志复原  
		return opt;  
	} 
	private static BitmapFactory.Options setBitmapOption(String file, int width) {
		
		BitmapFactory.Options opt = new BitmapFactory.Options();  
		opt.inJustDecodeBounds = true;           
		BitmapFactory.decodeFile(file, opt);  
		int outWidth = opt.outWidth; 
		int outHeight = opt.outHeight;  
		opt.inDither = false;  
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inSampleSize = 1;    
		        
		if (outWidth != 0 && outHeight != 0) {  
		    int sampleSize = outWidth / width;
		    opt.inSampleSize = sampleSize;  
		}  
		opt.inJustDecodeBounds = false;
		
		return opt;  
	} 
	
}
