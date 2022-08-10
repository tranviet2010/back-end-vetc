package com.vetc.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "CUST_VEHICLE")
/**
 * Thông tin biển số xe của khách hàng
 */
public class CustVehicle {

  @Id
  @Column(name = "AUTOID")
  private Long autoid;

  @Column(name = "CUST_ID")
  private Long custId;

  @Column(name = "PLATE", length = 100)
  private String plate;

  @Column(name = "STATUS", length = 1)
  private String status;

  @Column(name = "ETC_VEHICLE_ID", length = 20)
  private String etcVehicleId;

  @Column(name = "VEHICLE_TYPE", length = 20)
  private String vehicleType;

  @Transient
  private String vehicleTypeRef;

}
