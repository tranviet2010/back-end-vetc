package com.vetc.manage.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
/**
 * @Author HungVM
 */
@Slf4j
public class StringUtil {
  public static boolean isNullOrEmpty(String input) {
    return StringUtils.isEmpty(input);
  }

  public static boolean equalsIgnoreCase(String source, String target) {
    if (isNullOrEmpty(source)) return false;
    if (isNullOrEmpty(target)) return false;

    return source.equalsIgnoreCase(target);
  }

  public static boolean checkStringNotEmpty(String strValue) {
    if ( StringUtils.isNotEmpty(strValue) && !strValue.equalsIgnoreCase("null"))
      return true;
    return false;
  }
}
