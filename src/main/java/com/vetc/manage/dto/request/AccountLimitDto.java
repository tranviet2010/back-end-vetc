package com.vetc.manage.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vetc.manage.annotation.NumberConstraint;
import lombok.Data;

@Data
public class AccountLimitDto {

  private Long productId;

  private Long custId;

  private String channel;

  @NumberConstraint()
  private String hmNgay;

  @NumberConstraint()
  private String hmGg;

  @NumberConstraint()
  private String hmThang;

//
//  private Long walletId;
//
//  private String walletType;


}
