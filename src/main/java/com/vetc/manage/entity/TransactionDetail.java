package com.vetc.manage.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION_DETAIL")
public class TransactionDetail {

  @Id
  @Column(name = "AUTOID", nullable = false)
  private Integer id;

  @Column(name = "STEP", length = 50)
  private String step;

  @Column(name = "BANK_ACCOUNT", length = 100)
  private String bankAccount;

  @Column(name = "BANK_ID")
  private Integer bankId;

  @Column(name = "BANK_CODE", length = 100)
  private String bankCode;

  @Column(name = "BANK_FEE")
  private Long bankFee;

  @Column(name = "BANK_VAT")
  private Long bankVat;

  @Column(name = "ORD")
  private Integer ord;

  @Column(name = "CREATED_AT")
  private LocalDate createdAt;

  @Column(name = "MERCHANT_ID", nullable = false)
  private Integer merchantId;

  @Column(name = "REQUEST_TIME")
  private LocalDate requestTime;

  @Column(name = "RESPONSE_TIME")
  private LocalDate responseTime;

  @Column(name = "MODIFIED_AT")
  private LocalDate modifiedAt;

  @Column(name = "REQUEST", length = 500)
  private String request;

  @Column(name = "RESPONSE", length = 1000)
  private String response;

  @Column(name = "STATE", length = 50)
  private String state;

  @Column(name = "INVOICE_ID")
  private Integer invoiceId;

  @Column(name = "AMOUNT")
  private Long amount;

  @Column(name = "PROCESS", length = 200)
  private String process;

  @Column(name = "CARD_OWNER_NAME", length = 100)
  private String cardOwnerName;

  @Column(name = "CARD_NO", length = 100)
  private String cardNo;

  @Column(name = "CARD_OPEN_DATE")
  private LocalDate cardOpenDate;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "TRANS_ID", nullable = false)
  private Transaction trans;

  @Column(name = "ORG_BANK_REQUEST_ID")
  private Integer orgBankRequestId;

  @Column(name = "BANK_REQUEST_ID", length = 100)
  private String bankRequestId;

  @Column(name = "BANK_BRANCH", length = 200)
  private String bankBranch;

  @Column(name = "TO_BANK_ID")
  private Integer toBankId;

  @Column(name = "TO_BANK_CODE", length = 100)
  private String toBankCode;

  @Column(name = "TO_ACCOUNT_NO", length = 100)
  private String toAccountNo;

  @Column(name = "REMARK", length = 300)
  private String remark;

  @Column(name = "TOKEN", length = 100)
  private String token;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStep() {
    return step;
  }

  public void setStep(String step) {
    this.step = step;
  }

  public String getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(String bankAccount) {
    this.bankAccount = bankAccount;
  }

  public Integer getBankId() {
    return bankId;
  }

  public void setBankId(Integer bankId) {
    this.bankId = bankId;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public Long getBankFee() {
    return bankFee;
  }

  public void setBankFee(Long bankFee) {
    this.bankFee = bankFee;
  }

  public Long getBankVat() {
    return bankVat;
  }

  public void setBankVat(Long bankVat) {
    this.bankVat = bankVat;
  }

  public Integer getOrd() {
    return ord;
  }

  public void setOrd(Integer ord) {
    this.ord = ord;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public Integer getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(Integer merchantId) {
    this.merchantId = merchantId;
  }

  public LocalDate getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(LocalDate requestTime) {
    this.requestTime = requestTime;
  }

  public LocalDate getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(LocalDate responseTime) {
    this.responseTime = responseTime;
  }

  public LocalDate getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(LocalDate modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Integer getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(Integer invoiceId) {
    this.invoiceId = invoiceId;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getProcess() {
    return process;
  }

  public void setProcess(String process) {
    this.process = process;
  }

  public String getCardOwnerName() {
    return cardOwnerName;
  }

  public void setCardOwnerName(String cardOwnerName) {
    this.cardOwnerName = cardOwnerName;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  public LocalDate getCardOpenDate() {
    return cardOpenDate;
  }

  public void setCardOpenDate(LocalDate cardOpenDate) {
    this.cardOpenDate = cardOpenDate;
  }

  public Transaction getTrans() {
    return trans;
  }

  public void setTrans(Transaction trans) {
    this.trans = trans;
  }

  public Integer getOrgBankRequestId() {
    return orgBankRequestId;
  }

  public void setOrgBankRequestId(Integer orgBankRequestId) {
    this.orgBankRequestId = orgBankRequestId;
  }

  public String getBankRequestId() {
    return bankRequestId;
  }

  public void setBankRequestId(String bankRequestId) {
    this.bankRequestId = bankRequestId;
  }

  public String getBankBranch() {
    return bankBranch;
  }

  public void setBankBranch(String bankBranch) {
    this.bankBranch = bankBranch;
  }

  public Integer getToBankId() {
    return toBankId;
  }

  public void setToBankId(Integer toBankId) {
    this.toBankId = toBankId;
  }

  public String getToBankCode() {
    return toBankCode;
  }

  public void setToBankCode(String toBankCode) {
    this.toBankCode = toBankCode;
  }

  public String getToAccountNo() {
    return toAccountNo;
  }

  public void setToAccountNo(String toAccountNo) {
    this.toAccountNo = toAccountNo;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

}
