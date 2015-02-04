package u.can.i.up.apimonitor.hook;

import java.lang.reflect.Member;


public interface HookHelperInterface {
	
	public abstract void hookMethod(Member method, MethodHookCallBack callback);

}
