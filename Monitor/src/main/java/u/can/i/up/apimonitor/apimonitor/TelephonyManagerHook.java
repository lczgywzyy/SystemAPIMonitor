package u.can.i.up.apimonitor.apimonitor;

import android.telephony.PhoneStateListener;

import java.lang.reflect.Method;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;

public class TelephonyManagerHook extends ApiMonitorHook {

    @Override
    public void startHook() {

        Method getLine1Numbermethod = RefInvoke.findMethodExact(
                "android.telephony.TelephonyManager", ClassLoader.getSystemClassLoader(),
                "getLine1Number");
        hookhelper.hookMethod(getLine1Numbermethod, new AbstractBehaviorHookCallBack() {

            @Override
            public void descParam(HookParam param) {
                Logger.log_behavior(TelephonyManagerHook.class.getName(), "Read PhoneNumber ->");
            }
        });

        Method listenMethod = RefInvoke.findMethodExact(
                "android.telephony.TelephonyManager", ClassLoader.getSystemClassLoader(),
                "listen", PhoneStateListener.class,int.class);
        hookhelper.hookMethod(listenMethod, new AbstractBehaviorHookCallBack() {

            @Override
            public void descParam(HookParam param) {
                // TODO Auto-generated method stub
                Logger.log_behavior(TelephonyManagerHook.class.getName(), "Listen Telephone State Change ->");
                Logger.log_behavior(TelephonyManagerHook.class.getName(), "PhoneStateListener ClassName = "+param.args[0].getClass().getName());
                int event =  (Integer) param.args[1];
                if((event&PhoneStateListener.LISTEN_CELL_LOCATION) != 0){
                    Logger.log_behavior(TelephonyManagerHook.class.getName(), "Listen Enent = "+"LISTEN_CELL_LOCATION");
                }
                if((event&PhoneStateListener.LISTEN_SIGNAL_STRENGTHS) != 0){
                    Logger.log_behavior(TelephonyManagerHook.class.getName(), "Listen Enent = "+"LISTEN_SIGNAL_STRENGTHS");
                }
                if((event&PhoneStateListener.LISTEN_CALL_STATE) != 0){
                    Logger.log_behavior(TelephonyManagerHook.class.getName(), "Listen Enent = "+"LISTEN_CALL_STATE");
                }
                if((event&PhoneStateListener.LISTEN_DATA_CONNECTION_STATE) != 0){
                    Logger.log_behavior(TelephonyManagerHook.class.getName(), "Listen Enent = "+"LISTEN_DATA_CONNECTION_STATE");
                }
                if((event&PhoneStateListener.LISTEN_CELL_LOCATION) != 0){
                    Logger.log_behavior(TelephonyManagerHook.class.getName(), "Listen Enent = "+"LISTEN_SERVICE_STATE");
                }

            }
        });

    }

}
