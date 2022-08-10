package com.vetc.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INVOICE")
public class Invoice {

  @Id
  @Column(name = "AUTOID", nullable = false)
  private Integer id;

  @Column(name = "INVOICE_ID", length = 50)
  private String invoiceId;

  @Column(name = "PARTNER", length = 100)
  private String partner;

  @Column(name = "AMOUNT")
  private Long amount;

  @Column(name = "FEE")
  private Long fee;

  @Column(name = "DESCRIPTION", length = 500)
  private String description;

  @Column(name = "\"DATA\"", length = 500)
  private String data;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }

  public String getPartner() {
    return partner;
  }

  public void setPartner(String partner) {
    this.partner = partner;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getFee() {
    return fee;
  }

  public void setFee(Long fee) {
    this.fee = fee;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

}
