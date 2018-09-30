package com.texoit.worstmovie.entity.dto;

import com.texoit.worstmovie.exception.BusinessExceptionEnum;

import java.io.Serializable;

public class ErrorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String message;

    public ErrorDTO(BusinessExceptionEnum businessEnum) {
        this.code = businessEnum.getCode();
        this.message = businessEnum.getMessage();
    }

    public ErrorDTO(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
