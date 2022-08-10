package com.vetc.manage.utils;

import org.apache.commons.lang3.StringUtils;
/** 
 * @Author HungVM
 */

public class SequenceUtils {

	private static int foSequence;

	public synchronized static String GetSequence() {

		foSequence = foSequence + 1;
		return StringUtils.leftPad(String.valueOf(foSequence), 8, "0");

	}
	
	   public static String GetFOSequence()
       {
           {
        	   
        	  return String.format("API{0}{1}", DateUtil.getTimespanSequence(), GetSequence());
                   
               
           }
       }
}
