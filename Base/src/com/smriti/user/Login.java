package com.smriti.user;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import android.util.Log;

import com.smriti.thread.PLLRunnable.SPLLRunnable;
import com.smriti.thread.RunnableLauncher;
import com.smriti.thread.RunnableListener;
import com.smriti.utilites.GsonUtil;

public class Login extends SPLLRunnable{
	
//		protected String iPAdress = "http://192.168.1.118:7080/carmall-inspect";
	protected String iPAdress = "http://192.168.1.250:7080/carmall-inspect";
	protected String loginPath = "/messenger/login?";
	
	public static class LoginModel{
		
		static LoginModel loginModel = null;
		static {
			loginModel = new LoginModel();
		};
		public static LoginModel get()
		{
			return loginModel;
		}
		public String username;
		public String password;
		public String sessionId; //sessionId
	}
	
	public static class LoginBean{
		public String code;
		public String msg;
		public String data; //sessionId
	}
	
	public Login(RunnableLauncher launcher, RunnableListener listener) {
		super(launcher, listener);
		Assert.assertTrue(launcher != null && listener != null);
		// TODO Auto-generated constructor stub
	}
	
	public Login(RunnableListener listener) {
		super(null, listener);
		Assert.assertTrue(listener != null);
		// TODO Auto-generated constructor stub
	}
	
//	{"code":10000,"msg":null,"data":"ecaf9a6274aea4d7fab2e130996f961a"}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String uriAPI =  iPAdress+loginPath+ "username=15810427330&password=123456";  
        HttpGet httpRequest = new HttpGet(uriAPI);  
        HttpResponse httpResponse = null;
        try {
        	
        	httpResponse = new DefaultHttpClient().execute(httpRequest);  
        	if(httpResponse.getStatusLine().getStatusCode() == 200 
        			|| 10000==httpResponse.getStatusLine().getStatusCode() )   
            {  
              String json = EntityUtils.toString(httpResponse.getEntity()); 
              Log.w("Base", "json =" + json.toString());
              
              LoginBean  bean = GsonUtil.beanFromString(json, LoginBean.class);
              LoginModel model = LoginModel.get();
              model.sessionId = bean.data;
              Log.w("Base", "sessionId =" + model.sessionId);
            } 
		} catch (Exception e) {
			// TODO: handle exception
			Log.w("Base",e.toString());
		}finally{
		}
       
	}

//	public RequestParams getloginParams(String username,String password) {
//		
//		RequestParams params = new RequestParams();
//		//String timestamp=System.currentTimeMillis()+"";
//		//System.out.println(timestamp+"timestamp");
//		//String key=MD5Utils.getMD5(username+MD5Utils.getMD5(password)+timestamp);
//		//System.out.println(key+"key");
//		params.addQueryStringParameter("username", username);
//		params.addQueryStringParameter("password", password);
//	LogUtils.w("username="+username+";password="+ password);
//		//params.addQueryStringParameter("key", key);
//		return params;
//	};
	
}
