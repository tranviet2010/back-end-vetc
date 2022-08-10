package com.vetc.manage.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class VerifyRuleConfigDto {

  private Long id;

  private String verifyMethod;

  private BigDecimal minAmount;

  private BigDecimal maxAmount;



}
