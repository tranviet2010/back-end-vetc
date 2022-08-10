package com.vetc.manage.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;


/**
 * @Author HungVM
 */
@Slf4j
public class DateUtil {

  private static String dateFormatPattern;
  private static String datetimeFormatPattern;

  public static String getTimespanSequence() {
    Date now = new Date();
    return String.format("{s}{s}{s}{s}", now.getHours(), now.getMinutes(), now.getSeconds(),
        now.getTime());
  }

  public static String getNow() {
    try {
      String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
      return date;
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTrace(e));
      // TODO: handle exception
    }
    return null;
  }

  public static Date convertDate(String value) {
    try {
      if (StringUtils.isEmpty(dateFormatPattern)) {
        dateFormatPattern = "dd-MM-yyyy";
      }
      Date date = new SimpleDateFormat(dateFormatPattern).parse(value);
      return date;
    } catch (Exception e) {
      // TODO: handle exception
    }
    return null;
  }

  public static Date convertDateTime(String value) {
    try {
      if (StringUtils.isEmpty(datetimeFormatPattern)) {
        datetimeFormatPattern = "dd-MM-yyyy HH:mm:ss";
      }
      Date date = new SimpleDateFormat(datetimeFormatPattern).parse(value);
      return date;
    } catch (Exception e) {
      // TODO: handle exception
    }
    return null;
  }

  public static String formatDate(Date dateInput, String inputFormatDate, String outputFormatDate)
      throws Exception {
    SimpleDateFormat outputFormat = new SimpleDateFormat(outputFormatDate);
    SimpleDateFormat inputFormat = new SimpleDateFormat(inputFormatDate);
    Date date = inputFormat.parse(inputFormat.format(dateInput));
    return outputFormat.format(date);
  }

  public static String formatDate(String dateInput, String inputFormatDate, String outputFormatDate)
      throws Exception {
    SimpleDateFormat outputFormat = new SimpleDateFormat(outputFormatDate);
    SimpleDateFormat inputFormat = new SimpleDateFormat(inputFormatDate);
    Date date = inputFormat.parse(dateInput);
    return outputFormat.format(date);
  }
}
