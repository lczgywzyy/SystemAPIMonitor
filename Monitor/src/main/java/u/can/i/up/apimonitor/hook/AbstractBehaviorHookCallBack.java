package u.can.i.up.apimonitor.hook;

import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.hook.MethodHookCallBack;
import u.can.i.up.apimonitor.util.Logger;

public abstract class AbstractBehaviorHookCallBack extends MethodHookCallBack {

	@Override
	public void beforeHookedMethod(HookParam param) {
		// TODO Auto-generated method stub
		Logger.log_behavior(AbstractBehaviorHookCallBack.class.getName(), "Invoke " + param.method.getDeclaringClass().getName() + "->" + param.method.getName());
		this.descParam(param);
		//this.printStackInfo();
	}

	@Override
	public void afterHookedMethod(HookParam param) {
		// TODO Auto-generated method stub
		//Logger.log_behavior("End Invoke "+ param.method.toString());
	}
	
	private void printStackInfo(){
		Throwable ex = new Throwable();
		StackTraceElement[] stackElements = ex.getStackTrace();
		if(stackElements != null){
			StackTraceElement st;
			for(int i=0; i<stackElements.length; i++){
				st = stackElements[i];
				if(st.getClassName().startsWith("com.android.monitor")||st.getClassName().startsWith("de.robv.android.xposed.XposedBridge"))
					continue;
				Logger.log_behavior(AbstractBehaviorHookCallBack.class.getName(), st.getClassName()+":"+st.getMethodName()+":"+st.getFileName()+":"+st.getLineNumber());
			}
		}
	}
	
	public abstract void descParam(HookParam param);


}
