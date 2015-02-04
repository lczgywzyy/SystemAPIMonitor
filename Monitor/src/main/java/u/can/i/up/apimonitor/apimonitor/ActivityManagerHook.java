package u.can.i.up.apimonitor.apimonitor;

import java.lang.reflect.Method;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;

public class ActivityManagerHook extends ApiMonitorHook {

	@Override
	public void startHook() {

		Method killBackgroundProcessesmethod = RefInvoke.findMethodExact(
                "android.app.ActivityManager", ClassLoader.getSystemClassLoader(),
                "killBackgroundProcesses", String.class);
		hookhelper.hookMethod(killBackgroundProcessesmethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				String packageName = (String) param.args[0];
				Logger.log_behavior(ActivityManagerHook.class.getName(), "android.app.ActivityManager.killBackgroundProcesses");
				Logger.log_behavior(ActivityManagerHook.class.getName(), "packageName:" + packageName);
			}
		});
		
		Method forceStopPackagemethod = RefInvoke.findMethodExact(
				"android.app.ActivityManager", ClassLoader.getSystemClassLoader(),
				"forceStopPackage", String.class);
		hookhelper.hookMethod(forceStopPackagemethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				String packageName = (String) param.args[0];
                Logger.log_behavior(ActivityManagerHook.class.getName(), "android.app.ActivityManager.forceStopPackage");
                Logger.log_behavior(ActivityManagerHook.class.getName(), "packageName:" + packageName);
			}
		});
	}

}
