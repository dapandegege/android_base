package com.smriti.image;

import com.smriti.thread.LLRunnable;
import com.smriti.thread.RunnableLauncher;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/*
 * ImageView as a RunnableLauncher
 */

public class ImageViewL extends ImageView implements RunnableLauncher {


	protected String uri;
	
	public ImageViewL(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public ImageViewL(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public ImageViewL(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see com.smriti.thread.RunnableLauncher#setData(java.lang.Object)
	 */
	@Override
	public void setData(Object object) {
		this.uri = (String) object;
	}
	
	@Override
	public Object getData(LLRunnable runnable) {
		// TODO Auto-generated method stub
		return uri;
	}


}
