package com.vetc.manage.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER_INFOS")
public class CustomerInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_INFO_ID")
  @SequenceGenerator(name = "CUSTOMER_INFO_ID", sequenceName = "CUSTOMER_INFO_SEQ", allocationSize = 1)
  @Column(name = "AUTOID", nullable = false)
  private Long id;


  @Column(name = "CUS_ID", nullable = false)
  private Long cusId;

  @Column(name = "DOC_ID", nullable = false)
  private Long docId;

  @Column(name = "DOC_TYPE", nullable = false, length = 50)
  private String docType;

  @Column(name = "CREATED_AT")
  private Date createdAt;

  @Column(name = "MODIFIED_AT")
  private Date modifiedAt;

  @Column(name = "STATE")
  private Long state;

  @Column(name = "URL", nullable = false)
  private String url;

  @Column(name = "FILE_TYPE", length = 50)
  private String fileType;

}
