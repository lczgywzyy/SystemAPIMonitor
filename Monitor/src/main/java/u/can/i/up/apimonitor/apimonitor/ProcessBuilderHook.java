package u.can.i.up.apimonitor.apimonitor;

import java.lang.reflect.Method;
import java.util.List;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;


public class ProcessBuilderHook extends ApiMonitorHook {

	@Override
	public void startHook() {
		// TODO Auto-generated method stub
		Method execmethod = RefInvoke.findMethodExact(
                "java.lang.ProcessBuilder", ClassLoader.getSystemClassLoader(),
                "start");
		hookhelper.hookMethod(execmethod, new AbstractBehaviorHookCallBack() {
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior(ProcessBuilderHook.class.getName(), "Create New Process ->");
				ProcessBuilder pb = (ProcessBuilder) param.thisObject;
				List<String> cmds = pb.command();
				StringBuilder sb = new StringBuilder();
				for(int i=0 ;i <cmds.size(); i++){
				   sb.append("CMD"+i+":"+cmds.get(i)+" ");
				}
				Logger.log_behavior(ProcessBuilderHook.class.getName(), "Command" + sb.toString());
			}
		});
	}

}
