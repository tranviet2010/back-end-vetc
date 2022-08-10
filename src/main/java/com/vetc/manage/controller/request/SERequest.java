package com.vetc.manage.controller.request;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
/** 
 * @Author HungVM
 */
@Getter
@Setter
public class SERequest implements Serializable {
    private String accountNo;
    private String secType;
    private String symbol;
}
