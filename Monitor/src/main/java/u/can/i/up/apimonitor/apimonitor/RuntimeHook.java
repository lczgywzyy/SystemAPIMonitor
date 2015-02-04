package u.can.i.up.apimonitor.apimonitor;

import java.io.File;
import java.lang.reflect.Method;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;

public class RuntimeHook extends ApiMonitorHook {

	@Override
	public void startHook() {

		Method execmethod = RefInvoke.findMethodExact(
                "java.lang.Runtime", ClassLoader.getSystemClassLoader(),
                "exec", String[].class, String[].class, File.class);
		hookhelper.hookMethod(execmethod, new AbstractBehaviorHookCallBack() {
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior(RuntimeHook.class.getName(), "Create New Process ->");
				String[] progs = (String[]) param.args[0];
				for(int i=0 ;i <progs.length; i++){
				   Logger.log_behavior(RuntimeHook.class.getName(), "Command" + i + " = "+progs[i]);
				}
			}
		});
		
	}

}
