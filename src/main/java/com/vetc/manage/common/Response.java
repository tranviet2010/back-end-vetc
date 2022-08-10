package com.vetc.manage.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class Response implements Serializable {

    public static final String STATUS_SUCCESS = "0";
    public static final String STATUS_FAILED ="1";
    public static final String STATUS_INPUT_INVALID = "2";
    public static final String STATUS_DATA_NOTFOUND = "3";

    @JsonInclude(Include.NON_EMPTY)
    private String errorcode;
    @JsonInclude(Include.NON_EMPTY)
    private String cause;
    @JsonInclude(Include.NON_EMPTY)
    private String description;

    private String status;
    private String message;
    private Object result;

    @JsonInclude(Include.NON_EMPTY)
    private Integer rs_total_size;
    @JsonInclude(Include.NON_EMPTY)
    private Integer rs_page_size;
    @JsonInclude(Include.NON_EMPTY)
    private Integer rs_offset;
    @JsonInclude(Include.NON_EMPTY)
    private Integer rs_limit;

    public Response() {
        super();
    }

    public Response(Object result, String message) {
        this.status = STATUS_FAILED;
        this.result = result;
        this.message = message;
    }

    public Response(Object result, String status, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public Response(String errorcode, String status, String message, Object result) {
        super();
        this.errorcode = errorcode;
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRs_total_size() {
        return rs_total_size;
    }

    public void setRs_total_size(Integer rs_total_size) {
        this.rs_total_size = rs_total_size;
    }

    public Integer getRs_page_size() {
        return rs_page_size;
    }

    public void setRs_page_size(Integer rs_page_size) {
        this.rs_page_size = rs_page_size;
    }

    public Integer getRs_offset() {
        return rs_offset;
    }

    public void setRs_offset(Integer rs_offset) {
        this.rs_offset = rs_offset;
    }

    public Integer getRs_limit() {
        return rs_limit;
    }

    public void setRs_limit(Integer rs_limit) {
        this.rs_limit = rs_limit;
    }

}
