package com.vetc.manage.utils;

import java.net.InetAddress;
/** 
 * @Author HungVM
 */
public class DNSUtil {
	public static String getHostName() {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			return ip.getHostName();
		} catch (Exception e) {
			// TODO: handle exception
			return "unknow";
		}
	}
	
	public static String getHostIP() {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			return ip.getHostAddress();
		} catch (Exception e) {
			// TODO: handle exception
			return "127.0.0.1";
		}
	}
}
