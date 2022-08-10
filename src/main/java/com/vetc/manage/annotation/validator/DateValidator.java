package com.vetc.manage.annotation.validator;

import com.vetc.manage.annotation.DateConstraint;
import com.vetc.manage.utils.StringUtil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateValidator implements ConstraintValidator<DateConstraint, Date> {
  private final String REGULAR = "^(0[1-9]|1\\d|2[0-8]|29(?=-\\d\\d-(?!1[01345789]00|2[1235679]00)\\d\\d(?:[02468][048]|[13579][26]))|30(?!-02)|31(?=-0[13578]|-1[02]))-(0[1-9]|1[0-2])-([12]\\d{3}) ([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)";

  @Override
  public void initialize(DateConstraint date) {
    //log.info("ContactNumberValidator initialize with info {} ",contactNumber.toString());
  }

  @Override
  public boolean isValid(Date date, ConstraintValidatorContext cxt) {
    DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String dateFiled = sdf.format(date);
    if (StringUtil.isNullOrEmpty(dateFiled) || !dateFiled.matches(REGULAR)) {
      return false;
    }

    sdf.setLenient(false);
    try {
      sdf.parse(dateFiled);
    } catch (ParseException e) {
      return false;
    }
    return true;
  }
}
