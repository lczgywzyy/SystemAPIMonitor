package u.can.i.up.apimonitor.hook;

import u.can.i.up.apimonitor.hook.HookHelperFacktory;
import u.can.i.up.apimonitor.hook.HookHelperInterface;

public abstract class ApiMonitorHook {
	
   protected HookHelperInterface hookhelper = HookHelperFacktory.getHookHelper();
   public abstract void startHook();
    
}
