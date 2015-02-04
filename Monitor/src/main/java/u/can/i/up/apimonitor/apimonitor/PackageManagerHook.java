package u.can.i.up.apimonitor.apimonitor;

import java.lang.reflect.Method;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.net.Uri;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;

public class PackageManagerHook extends ApiMonitorHook {

	@Override
	public void startHook() {

		Method setComponentEnableSettingmethod = RefInvoke.findMethodExact("android.app.ApplicationPackageManager",
                ClassLoader.getSystemClassLoader(), "setComponentEnabledSetting", ComponentName.class, int.class, int.class);
		hookhelper.hookMethod(setComponentEnableSettingmethod, new AbstractBehaviorHookCallBack() {

			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				ComponentName cn = (ComponentName) param.args[0];
				int newState = (Integer) param.args[1];
				Logger.log_behavior(PackageManagerHook.class.getName(), "Set ComponentEnabled ->");
				Logger.log_behavior(PackageManagerHook.class.getName(), "The Component ClassName: " + cn.getPackageName() + "/" + cn.getClassName());
				if (newState == PackageManager.COMPONENT_ENABLED_STATE_DISABLED)
					Logger.log_behavior(PackageManagerHook.class.getName(), "Component New State = " + "COMPONENT_ENABLED_STATE_DISABLED");
				if (newState == PackageManager.COMPONENT_ENABLED_STATE_ENABLED)
					Logger.log_behavior(PackageManagerHook.class.getName(), "Component New State = " + "COMPONENT_ENABLED_STATE_ENABLED");
				if (newState == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT)
					Logger.log_behavior(PackageManagerHook.class.getName(), "Component New State = " + "COMPONENT_ENABLED_STATE_DEFAULT");
			}
		});

		Method installPackagemethod = null;
		try {
			installPackagemethod = RefInvoke.findMethodExact("android.app.ApplicationPackageManager", ClassLoader.getSystemClassLoader(),
					"installPackage", Uri.class, Class.forName("android.content.pm.IPackageInstallObserver"), int.class, String.class);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hookhelper.hookMethod(installPackagemethod, new AbstractBehaviorHookCallBack() {

			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Uri uri = (Uri) param.args[0];
				Logger.log_behavior(PackageManagerHook.class.getName(), "Slient Install APK ->");
				Logger.log_behavior(PackageManagerHook.class.getName(), "The APK URL = " + uri.toString());
			}
		});

		Method deletePackagemethod = null;
		try {
			deletePackagemethod = RefInvoke.findMethodExact("android.app.ApplicationPackageManager", ClassLoader.getSystemClassLoader(),
					"deletePackage", String.class, Class.forName("android.content.pm.IPackageDeleteObserver"), int.class);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hookhelper.hookMethod(deletePackagemethod, new AbstractBehaviorHookCallBack() {

			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				String packagename = (String) param.args[0];
				Logger.log_behavior(PackageManagerHook.class.getName(), "Slient UnInstall APK ->");
				Logger.log_behavior(PackageManagerHook.class.getName(), "The APK PackageName = " + packagename);
			}
		});

		Method getInstalledPackagesMethod = RefInvoke.findMethodExact("android.app.ApplicationPackageManager",
				ClassLoader.getSystemClassLoader(), "getInstalledPackages", int.class, int.class);
		hookhelper.hookMethod(getInstalledPackagesMethod, new AbstractBehaviorHookCallBack() {
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior(PackageManagerHook.class.getName(), "Query Installed Apps ->");
			}
		});
	}

}
