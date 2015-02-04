package u.can.i.up.apimonitor.apimonitor;

import java.lang.reflect.Method;
import android.app.Notification;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;

public class NotificationManagerHook extends ApiMonitorHook {

	@Override
	public void startHook() {
		// TODO Auto-generated method stub
		Method notifyMethod = RefInvoke.findMethodExact("android.app.NotificationManager", ClassLoader.getSystemClassLoader(), "notify", int.class, Notification.class);
		hookhelper.hookMethod(notifyMethod, new AbstractBehaviorHookCallBack() {
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Notification notification = (Notification) param.args[1];
				Logger.log_behavior(NotificationManagerHook.class.getName(), "Send Notification ->");
				Logger.log_behavior(NotificationManagerHook.class.getName(), notification.toString());
			}
		});
	}

}
