package u.can.i.up.apimonitor.mod;

import android.content.pm.ApplicationInfo;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import u.can.i.up.apimonitor.hook.ApiMonitorHookManager;
import u.can.i.up.apimonitor.util.Logger;

//import com.android.reverse.mod.ModuleContext;
//import com.android.reverse.collecter.DexFileInfoCollecter;
//import com.android.reverse.collecter.LuaScriptInvoker;
//import com.android.reverse.collecter.ModuleContext;

public class MonitorXposedModule implements IXposedHookLoadPackage {

	private static final String MONITOR_PACKAGENAME = "com.android.monitor"; 
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		// TODO Auto-generated method stub
		if(lpparam.appInfo == null || 
				(lpparam.appInfo.flags & (ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)) !=0){
			return;
		}else if(lpparam.isFirstApplication && !MONITOR_PACKAGENAME.equals(lpparam.packageName)){
//		  Logger.PACKAGENAME = lpparam.packageName;
		  Logger.log(MonitorXposedModule.class.getName(), "Package: "+lpparam.packageName +" has hook");
		  Logger.log(MonitorXposedModule.class.getName(), "the app target id = "+android.os.Process.myPid());
		  PackageMetaInfo pminfo = PackageMetaInfo.fromXposed(lpparam);
//		  ModuleContext.getInstance().initModuleContext(pminfo);
		  Logger.log_behavior(MonitorXposedModule.class.getName(), "pminfo" + pminfo.getPackageName());
		  ApiMonitorHookManager.getInstance().startMonitor();
		}else{
			
		}
	}

}
