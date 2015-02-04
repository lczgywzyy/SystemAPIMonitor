package u.can.i.up.apimonitor.apimonitor;

import java.io.FileDescriptor;
import java.lang.reflect.Method;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;

public class MediaRecorderHook extends ApiMonitorHook {

	@Override
	public void startHook() {

		Method startmethod = RefInvoke.findMethodExact(
                "android.media.MediaRecorder", ClassLoader.getSystemClassLoader(),
                "start");
		hookhelper.hookMethod(startmethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior(MediaRecorderHook.class.getName(), "Media Record: Start ->");
				String mPath = (String)RefInvoke.getFieldOjbect("android.media.MediaRecorder", param.thisObject, "mPath");
				if(mPath != null)
				   Logger.log_behavior(MediaRecorderHook.class.getName(), "Save Path: ->" +mPath);
				else{
					FileDescriptor mFd = (FileDescriptor) RefInvoke.getFieldOjbect("android.media.MediaRecorder", param.thisObject, "mFd");
					Logger.log_behavior(MediaRecorderHook.class.getName(), "Save Path: ->" +mFd.toString());
				}
			}
		});
		
		Method stopmethod = RefInvoke.findMethodExact(
				"android.media.MediaRecorder", ClassLoader.getSystemClassLoader(),
				"stop");
		hookhelper.hookMethod(stopmethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior(MediaRecorderHook.class.getName(), "Media Record: Stop ->");
			}
		});
		
	}

}
