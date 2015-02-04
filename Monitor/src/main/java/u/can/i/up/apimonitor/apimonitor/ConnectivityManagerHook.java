package u.can.i.up.apimonitor.apimonitor;


import java.lang.reflect.Method;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;

public class ConnectivityManagerHook extends ApiMonitorHook {

    @Override
    public void startHook() {

        Method setMobileDataEnabledmethod = RefInvoke.findMethodExact(
                "android.net.ConnectivityManager", ClassLoader.getSystemClassLoader(),
                "setMobileDataEnabled", boolean.class);
        hookhelper.hookMethod(setMobileDataEnabledmethod, new AbstractBehaviorHookCallBack() {

            @Override
            public void descParam(HookParam param) {
                boolean status = (Boolean) param.args[0];
                Logger.log(ConnectivityManagerHook.class.getName(), "Set MobileDataEnabled = " + status);
            }
        });

    }

}
