package u.can.i.up.apimonitor.apimonitor;

import java.lang.reflect.Method;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;

public class AudioRecordHook extends ApiMonitorHook {

	@Override
	public void startHook() {
		// TODO Auto-generated method stub
		Method startRecordingMethod = RefInvoke.findMethodExact(
                "android.media.AudioRecord", ClassLoader.getSystemClassLoader(),
                "startRecording");
		hookhelper.hookMethod(startRecordingMethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior(AudioRecordHook.class.getName(), "android.media.AudioRecord.startRecording");
			}
		});
		
	}

}
