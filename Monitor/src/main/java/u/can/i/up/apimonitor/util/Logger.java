package u.can.i.up.apimonitor.util;

import android.util.Log;

public class Logger {
	
	public static String LOGTAG_COMMAN = "UCanIUp-";
//	public static String LOGTAG_WORKFLOW = "gobler-apimonitor-";
	public static boolean DEBUG_ENABLE = true;
//	public static String PACKAGENAME;
	
	public static void log(String packageName, String message){
		if(DEBUG_ENABLE)
			Log.d(LOGTAG_COMMAN + packageName, "--------------<" + message + ">--------------");
	}
	
	public static void log_behavior(String packageName, String message){
		if(DEBUG_ENABLE)
			Log.d(LOGTAG_COMMAN + packageName, "--------------<" + message + ">--------------");
	}
	
}
