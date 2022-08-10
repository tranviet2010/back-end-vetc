package com.vetc.manage.dto.request;

import com.vetc.manage.annotation.PhoneNumberConstraint;
import java.util.Date;
import javax.validation.constraints.Email;
import lombok.Data;

@Data
public class WalletDto {

  private String name;

  @PhoneNumberConstraint
  private String mobiNumber;

  @Email(message = "Định dạng email không đúng")
  private String email;

  private Integer gender;

  private Date dob;

  private String idNoFont;

  private String idNoBack;

  private Long walletTypeId;

  private String idNo;

  private Date idIssueDate;

  private String idIssuePlace;

  private String address;

  private String homeTow;

  private String nationality;

  private String avatar;

}
