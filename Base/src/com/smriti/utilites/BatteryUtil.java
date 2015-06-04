package com.smriti.utilites;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/*
 <receiver android:name="com.home.receiver.BatteryReceiver">  
 	<intent-filter>  
      <action android:name="android.intent.action.BATTERY_CHANGED" />  
      <action android:name="android.intent.action.BATTERY_OKAY"/>  
      <action android:name="android.intent.action.BATTERY_LOW"/>  
    </intent-filter>   
 </receiver>  
 */

public class BatteryUtil extends BroadcastReceiver {
	
	public interface BatteryListener{
		public void batteryChanged(float percentage);
		public void batteryLow();
		public void batteryOK();
	}
	
	protected List<BatteryListener> listeners;
	static BatteryUtil batteryUtil;
	
	static{
		batteryUtil = new BatteryUtil();
	}
	private BatteryUtil(){
		listeners = new ArrayList<BatteryListener>();
	}
	
	public static BatteryUtil getBatteryUtil(){
		return batteryUtil;
	}
	
	public void addListener(BatteryListener listener){
		listeners.add(listener);
	}
	public void removeListener(BatteryListener listener){
		listeners.remove(listener);
	}
	
	public void onReceive(Context context, Intent intent) { 
		
        if (Intent.ACTION_BATTERY_OKAY.equals(intent.getAction())) {  
        	for (Iterator<BatteryListener> iterator = listeners.iterator(); iterator.hasNext();) {
				BatteryListener listener = (BatteryListener) iterator.next();
				listener.batteryOK();
			}
        	return;
        }
        
        if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction())) {  
        	for (Iterator<BatteryListener> iterator = listeners.iterator(); iterator.hasNext();) {
				BatteryListener listener = (BatteryListener) iterator.next();
				listener.batteryLow();
			}
        	return;
        }  
        
        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {  
            Bundle bundle = intent.getExtras();  
            int current = bundle.getInt("level");  
            int total = bundle.getInt("scale");  
            float percentage = current / total;
            for (Iterator<BatteryListener> iterator = listeners.iterator(); iterator.hasNext();) {
				BatteryListener listener = (BatteryListener) iterator.next();
				listener.batteryChanged(percentage);
			}
            return;
        }  
  
    }  
}
