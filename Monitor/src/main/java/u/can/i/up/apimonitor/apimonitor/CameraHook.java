package u.can.i.up.apimonitor.apimonitor;

import java.lang.reflect.Method;

import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;

import u.can.i.up.apimonitor.hook.AbstractBehaviorHookCallBack;
import u.can.i.up.apimonitor.hook.ApiMonitorHook;
import u.can.i.up.apimonitor.hook.HookParam;
import u.can.i.up.apimonitor.util.Logger;
import u.can.i.up.apimonitor.util.RefInvoke;

public class CameraHook extends ApiMonitorHook {

	@Override
	public void startHook() {
		// TODO Auto-generated method stub
		Method takePictureMethod = RefInvoke.findMethodExact(
                "android.hardware.Camera", ClassLoader.getSystemClassLoader(),
                "takePicture", ShutterCallback.class, PictureCallback.class, PictureCallback.class, PictureCallback.class);
		hookhelper.hookMethod(takePictureMethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior(CameraHook.class.getName(), "Candroid.hardware.Camera.takePicture");
			}
		});
		
		Method setPreviewCallbackMethod = RefInvoke.findMethodExact(
				"android.hardware.Camera", ClassLoader.getSystemClassLoader(),
				"setPreviewCallback",PreviewCallback.class);
		hookhelper.hookMethod(setPreviewCallbackMethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
                Logger.log_behavior(CameraHook.class.getName(), "Candroid.hardware.Camera.setPreviewCallback");
			}
		});
		
		Method setPreviewCallbackWithBufferMethod = RefInvoke.findMethodExact(
				"android.hardware.Camera", ClassLoader.getSystemClassLoader(),
				"setPreviewCallbackWithBuffer",PreviewCallback.class);
		hookhelper.hookMethod(setPreviewCallbackWithBufferMethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
                Logger.log_behavior(CameraHook.class.getName(), "Candroid.hardware.Camera.setPreviewCallbackWithBuffer");
			}
		});
		
		Method setOneShotPreviewCallbackMethod = RefInvoke.findMethodExact(
				"android.hardware.Camera", ClassLoader.getSystemClassLoader(),
				"setOneShotPreviewCallback",PreviewCallback.class);
		hookhelper.hookMethod(setOneShotPreviewCallbackMethod, new AbstractBehaviorHookCallBack() {
			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
                Logger.log_behavior(CameraHook.class.getName(), "Candroid.hardware.Camera.setOneShotPreviewCallback");
			}
		});
	}

}
