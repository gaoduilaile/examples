/*
Created by Min@suzhou 2018.07.07
Email: jimmy.huang@ifcloud.top
 */


package com.anyu.armmodule.utils;

import java.io.OutputStream;

public class RootShellCmd {

	private static RootShellCmd rootShel;
	private OutputStream os;

	/**
	 * 获取实例
	 * @return
     */
	public static RootShellCmd getInstance() {
		if(rootShel ==null){
			synchronized (RootShellCmd.class) {
				if(rootShel ==null){
					rootShel = new RootShellCmd();
				}
			}
		}
		return rootShel;
	}


	/**
	 * 设置屏幕亮度
	 * @param bright
	 * @return
     */
	public boolean setBrightness(int bright) {
		
		try {
			String[] command;
				command = new String[] {
						"su",
						"-c",
						"echo "
								+ bright
								+ " > /sys/class/leds/lcd-backlight/brightness" };
			exec(command);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 隐藏导航栏及状态栏
	 * @return
     */
	public boolean hideNavigation() {
		try {
			String[] command = new String[] {
					"su",
					"-c",
					"am",
					"broadcast",
					"-a",
					"ifcloud.systemui.hide_StatusBarAndNavigationBar"
				};
			exec(command);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 显示导航栏及状态栏
	 * @return
     */
	public boolean showNavigation() {

		try {
			String[] command = new String[] {
					"su",
					"-c",
					"am",
					"broadcast",
					"-a",
					"ifcloud.systemui.show_StatusBarAndNavigationBar"
			};
			exec(command);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * 动态关机
	 */
	public void shutDown(){
		try {
			String[] command = new String[] {"su", "-c", "busybox halt -f\n"};
			exec(command);
		} catch (Exception e) {

		}
	}

	/**
	 * 动态重启
	 */
	public void reboot(){
		try {
			String[] command = new String[] { "su", "-c","reboot\n"};
			exec(command);
		} catch (Exception e) {

		}
	}
	
	/**
	 * back键
	 */
	public void back(){
		try {
			String[] command = new String[] {"su", "-c","input keyevent 4\n"};
			exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * home键
	 */
	public void home(){
		try {
			String[] command = new String[] {"su", "-c","input keyevent 3\n"};
			exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 音量加键
	 */
	public void volumeUp(){
		try {
			String[] command = new String[] {"su", "-c","input keyevent 24\n"};
			exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 音量减键
	 */
	public void volumeDown(){
		try {
			String[] command = new String[] {"su", "-c","input keyevent 25\n"};
			exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 静音
	 */
	public void volumeMute(){
		try {
			String[] command = new String[] {"su", "-c","input keyevent 164\n"};
			exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * execute command
	 * 
	 * @param command
	 * @throws Exception
	 */
	private void exec(final String[] command) throws Exception {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Process proc;
				try {
					proc = Runtime.getRuntime().exec(command);
					proc.waitFor();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}
		}).start();
		
	}

	/**
	 * 执行shell指令
	 * @param cmd 指令
	 */
	public final void exec(String cmd) {
		try {
			if (os == null) {
				os = Runtime.getRuntime().exec("su").getOutputStream();
			}
			os.write(cmd.getBytes());
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
