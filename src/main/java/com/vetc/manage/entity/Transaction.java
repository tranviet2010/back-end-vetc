package com.vetc.manage.entity;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

  @Id
  @Column(name = "TRANS_ID", nullable = false)
  private Integer id;

  @Column(name = "CHANNEL_CODE", nullable = false, length = 50)
  private String channelCode;

  @Column(name = "ACTOR", nullable = false, length = 100)
  private String actor;

  @Column(name = "ACTION_AT", nullable = false)
  private Instant actionAt;

  @Column(name = "PRODUCT_ID", nullable = false)
  private Integer productId;

  @Column(name = "PRODUCT_CODE", length = 50)
  private String productCode;

  @Column(name = "\"DATA\"", nullable = false, length = 1000)
  private String data;

  @Column(name = "AMOUNT", nullable = false)
  private Long amount;

  @Column(name = "TRANS_DATETIME", nullable = false)
  private Instant transDatetime;

  @Column(name = "CREATED_AT")
  private Instant createdAt;

  @Column(name = "MODIFIED_AT")
  private Instant modifiedAt;

  @Column(name = "STEP", length = 50)
  private String step;

  @Column(name = "FROM_CUST_ID", nullable = false)
  private Integer fromCustId;

  @Column(name = "FROM_WALLET", nullable = false)
  private Integer fromWallet;

  @Column(name = "TO_WALLET")
  private Integer toWallet;

  @Column(name = "TO_CUST_ID")
  private Integer toCustId;

  @Column(name = "FEE")
  private Long fee;

  @Column(name = "FEE_ID")
  private Integer feeId;

  @Column(name = "VAT")
  private Long vat;

  @Column(name = "MERCHANT_ID")
  private Integer merchantId;

  @Column(name = "PROM_ID")
  private Integer promId;

  @Column(name = "PROM_VALUE")
  private Integer promValue;

  @Column(name = "APPCODE", length = 50)
  private String appcode;

  @Column(name = "VAT_CODE", length = 10)
  private String vatCode;

  @Column(name = "TRANFER_CODE", length = 50)
  private String tranferCode;

  @Column(name = "STATE", length = 50)
  private String state;

  @Column(name = "RECONCILE_STATUS", length = 20)
  private String reconcileStatus;

  @Column(name = "PAYMENT_SOURCE_CHANNEL", length = 20)
  private String paymentSourceChannel;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getChannelCode() {
    return channelCode;
  }

  public void setChannelCode(String channelCode) {
    this.channelCode = channelCode;
  }

  public String getActor() {
    return actor;
  }

  public void setActor(String actor) {
    this.actor = actor;
  }

  public Instant getActionAt() {
    return actionAt;
  }

  public void setActionAt(Instant actionAt) {
    this.actionAt = actionAt;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Instant getTransDatetime() {
    return transDatetime;
  }

  public void setTransDatetime(Instant transDatetime) {
    this.transDatetime = transDatetime;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(Instant modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public String getStep() {
    return step;
  }

  public void setStep(String step) {
    this.step = step;
  }

  public Integer getFromCustId() {
    return fromCustId;
  }

  public void setFromCustId(Integer fromCustId) {
    this.fromCustId = fromCustId;
  }

  public Integer getFromWallet() {
    return fromWallet;
  }

  public void setFromWallet(Integer fromWallet) {
    this.fromWallet = fromWallet;
  }

  public Integer getToWallet() {
    return toWallet;
  }

  public void setToWallet(Integer toWallet) {
    this.toWallet = toWallet;
  }

  public Integer getToCustId() {
    return toCustId;
  }

  public void setToCustId(Integer toCustId) {
    this.toCustId = toCustId;
  }

  public Long getFee() {
    return fee;
  }

  public void setFee(Long fee) {
    this.fee = fee;
  }

  public Integer getFeeId() {
    return feeId;
  }

  public void setFeeId(Integer feeId) {
    this.feeId = feeId;
  }

  public Long getVat() {
    return vat;
  }

  public void setVat(Long vat) {
    this.vat = vat;
  }

  public Integer getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(Integer merchantId) {
    this.merchantId = merchantId;
  }

  public Integer getPromId() {
    return promId;
  }

  public void setPromId(Integer promId) {
    this.promId = promId;
  }

  public Integer getPromValue() {
    return promValue;
  }

  public void setPromValue(Integer promValue) {
    this.promValue = promValue;
  }

  public String getAppcode() {
    return appcode;
  }

  public void setAppcode(String appcode) {
    this.appcode = appcode;
  }

  public String getVatCode() {
    return vatCode;
  }

  public void setVatCode(String vatCode) {
    this.vatCode = vatCode;
  }

  public String getTranferCode() {
    return tranferCode;
  }

  public void setTranferCode(String tranferCode) {
    this.tranferCode = tranferCode;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getReconcileStatus() {
    return reconcileStatus;
  }

  public void setReconcileStatus(String reconcileStatus) {
    this.reconcileStatus = reconcileStatus;
  }

  public String getPaymentSourceChannel() {
    return paymentSourceChannel;
  }

  public void setPaymentSourceChannel(String paymentSourceChannel) {
    this.paymentSourceChannel = paymentSourceChannel;
  }

}
