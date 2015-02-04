package u.can.i.up.apimonitor.apimonitor;

import java.lang.reflect.Method;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;


public class AccountManagerHook extends ApiMonitorHook {

	
	@Override
	public void startHook() {
		
		Method getAccountsMethod = RefInvoke.findMethodExact(
                "android.accounts.AccountManager", ClassLoader.getSystemClassLoader(),
                "getAccounts");
		hookhelper.hookMethod(getAccountsMethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior(AccountManagerHook.class.getName(), "android.accounts.AccountManager.GetAccounts");
			}
		});	
		
		Method getAccountsByTypeMethod = RefInvoke.findMethodExact(
				"android.accounts.AccountManager", ClassLoader.getSystemClassLoader(),
				"getAccountsByType",String.class);
		hookhelper.hookMethod(getAccountsByTypeMethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				String type = (String) param.args[0];
				Logger.log_behavior(AccountManagerHook.class.getName(), "android.accounts.AccountManager.getAccountsByType");
                Logger.log_behavior(AccountManagerHook.class.getName(), "type:" + type);
			}
		});	
	}

}
