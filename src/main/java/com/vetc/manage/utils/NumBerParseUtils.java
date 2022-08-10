package com.vetc.manage.utils;

import lombok.extern.slf4j.Slf4j;
/** 
 * @Author HungVM
 */
@Slf4j
public class NumBerParseUtils {

	public static boolean parseDouble(String str) {
		try {
			double val = Double.parseDouble(str);
			return true;

		} catch (NumberFormatException e1) {
			//log.error("Error when parseDouble with number format: " + e1.getMessage());
			return false;
		}catch (NullPointerException e2) {
			//log.error("Error when parseDouble with number format: " + e2.getMessage());
			return false;
		}
	}
	
	
	public static int parseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			log.error("Error when parseInt with number format: " + e.getMessage());
			// TODO: handle exception
		}
		return -1;
	}
	

	

}
