package com.vetc.manage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author HungVM
 */
@Getter
@AllArgsConstructor
public enum ApParamEnum {
  CUSTOMER_STATE("CUSTOMER_STATE"),
  VEHICLE("VEHICLE");
  private String value;
}
