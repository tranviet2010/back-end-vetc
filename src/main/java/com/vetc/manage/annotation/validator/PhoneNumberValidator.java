package com.vetc.manage.annotation.validator;

import com.vetc.manage.annotation.PhoneNumberConstraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {
	@Override
	public void initialize(PhoneNumberConstraint contactNumber) {
		//log.info("ContactNumberValidator initialize with info {} ",contactNumber.toString());
	}

	@Override
	public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
		return contactField != null && contactField.matches("[0-9]+") && (contactField.length() > 8)
				&& (contactField.length() < 14);
	}

}
