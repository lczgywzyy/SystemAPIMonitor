package u.can.i.up.apimonitor.apimonitor;

import android.app.PendingIntent;
import android.util.Base64;

import java.lang.reflect.Method;
import java.util.ArrayList;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;


public class SmsManagerHook extends ApiMonitorHook {

	@Override
	public void startHook() {
		Logger.log_behavior(SmsManagerHook.class.getName(), "The app may be trying to send text.");
		Method sendTextMessagemethod = RefInvoke.findMethodExact(
                "android.telephony.SmsManager", ClassLoader.getSystemClassLoader(),
                "sendTextMessage", String.class, String.class, String.class, PendingIntent.class, PendingIntent.class);
		hookhelper.hookMethod(sendTextMessagemethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior(SmsManagerHook.class.getName(), "Send SMS ->");
				String dstNumber = (String)param.args[0];
				String content = (String)param.args[2];
			    Logger.log_behavior(SmsManagerHook.class.getName(), "SMS DestNumber:"+dstNumber);
				Logger.log_behavior(SmsManagerHook.class.getName(), "SMS Content:"+content);
				param.setResult(null);    //hook, prevents the call to the original method.

				
				
				//				dstNumber="111";
//				content ="Cyrus";
//				Context context=getApplicationContext();
//				Toast.makeText(context,"�����صĶ�����",2000).show();					
			}
			
//			@Override
//			public void afterHookedMethod(HookParam param) {
//				String dstNumber = (String)param.args[0];
//				String content = (String)param.args[2];
//				dstNumber="111";
//				content ="Cyrus";
//				
//			}
		});
//		Logger.log_behavior(SmsManagerHook.class.getName(), "SMS11111111111111111111111111");
		Method getAllMessagesFromIccmethod = RefInvoke.findMethodExact(
				"android.telephony.SmsManager", ClassLoader.getSystemClassLoader(),
				"getAllMessagesFromIcc");
		hookhelper.hookMethod(getAllMessagesFromIccmethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior(SmsManagerHook.class.getName(), "Read SMS From Icc ->");
			}
		});
		
//		Logger.log_behavior(SmsManagerHook.class.getName(), "SMS2222222222222222222222222");
		Method sendDataMessagemethod = RefInvoke.findMethodExact(
				"android.telephony.SmsManager", ClassLoader.getSystemClassLoader(),
				"sendDataMessage",String.class,String.class,short.class,byte[].class,PendingIntent.class,PendingIntent.class);
		hookhelper.hookMethod(sendDataMessagemethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior(SmsManagerHook.class.getName(), "Send Data SMS ->");
				String dstNumber = (String)param.args[0];
				short port = (Short)param.args[2];
				String content = Base64.encodeToString((byte[]) param.args[3],0);
				Logger.log_behavior(SmsManagerHook.class.getName(), "SMS DestNumber:"+dstNumber);
				Logger.log_behavior(SmsManagerHook.class.getName(), "SMS destinationPort:"+port);
				Logger.log_behavior(SmsManagerHook.class.getName(), "SMS Base64 Content:"+content);
			}
		});
		
//		Logger.log_behavior(SmsManagerHook.class.getName(), "SMS3333333333333333333333333333");
		Method sendMultipartTextMessagemethod = RefInvoke.findMethodExact(
				"android.telephony.SmsManager", ClassLoader.getSystemClassLoader(),
				"sendMultipartTextMessage",String.class,String.class,ArrayList.class,ArrayList.class,ArrayList.class);
		hookhelper.hookMethod(sendMultipartTextMessagemethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior(SmsManagerHook.class.getName(), "Send Multipart SMS ->");
				String dstNumber = (String)param.args[0];
				ArrayList<String> sms = (ArrayList<String>) param.args[2];
				StringBuilder sb = new StringBuilder();
				for(int i=0; i<sms.size(); i++){
					sb.append(sms.get(i));
				}
				Logger.log_behavior(SmsManagerHook.class.getName(), "SMS DestNumber:"+dstNumber);
				Logger.log_behavior(SmsManagerHook.class.getName(), "SMS Content:"+sb.toString());
			}
		});
//		Logger.log_behavior(SmsManagerHook.class.getName(), "SMS44444444444444444444444444");
		
	}

}
