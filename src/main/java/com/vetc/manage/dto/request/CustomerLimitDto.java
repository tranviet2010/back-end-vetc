package com.vetc.manage.dto.request;

import java.util.Date;
import javax.validation.constraints.Email;
import lombok.Data;

@Data
public class CustomerLimitDto {

  private Long custId;

  @Email(message = "Định dạng email không đúng")
  private String email;


  private Date idIssueDate;

  private String idIssuePlace;

}
