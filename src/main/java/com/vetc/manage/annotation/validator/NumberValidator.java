package com.vetc.manage.annotation.validator;

import com.vetc.manage.annotation.NumberConstraint;
import com.vetc.manage.utils.StringUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberValidator implements
    ConstraintValidator<NumberConstraint, String> {

  @Override
  public void initialize(NumberConstraint date) {
    //log.info("ContactNumberValidator initialize with info {} ",contactNumber.toString());
  }

  @Override
  public boolean isValid(String number, ConstraintValidatorContext cxt) {
    if (!StringUtil.isNullOrEmpty(number)) {
      return false;
    }
    return number.matches("[-]?[0-9]+[,.]?[0-9]*([\\/][0-9]+[,.]?[0-9]*)*");
  }
}
