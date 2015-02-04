package u.can.i.up.apimonitor.apimonitor;

import java.lang.reflect.Method;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;


public class WebViewHook extends ApiMonitorHook {
	@Override
	public void startHook(){
		Method loadUrlMethod= RefInvoke.findMethodExact("android.webkit.WebView", ClassLoader.getSystemClassLoader(), "loadUrl", String.class);
		hookhelper.hookMethod(loadUrlMethod, new AbstractBehaviorHookCallBack(){
			@Override
			public void descParam(HookParam param){
				Logger.log_behavior(WebViewHook.class.getName(), "Connect to WebView ->");
//				WebView webView = (WebView) param.thisObject;
				if (param.args.length > 0 && param.args[0] instanceof String){				
					for(int i = 0; i < param.args.length; i ++){
						Logger.log_behavior(WebViewHook.class.getName(), "" + i + ": " + param.args[i]);
						}			
				}		
			}
			
		});
		
		
	}
	
	
	
	
	

}
