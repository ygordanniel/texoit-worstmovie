package com.texoit.worstmovie.entity.dto;

import com.texoit.worstmovie.exception.BusinessExceptionEnum;

import java.io.Serializable;

public class ResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T content;
    private ErrorDTO error;

    public ResponseDTO(BusinessExceptionEnum businessEnum) {
        this.error = new ErrorDTO(businessEnum);
    }

    public ResponseDTO(T content) {
        this.content = content;
    }

    public ResponseDTO(String message) {
        this.error = new ErrorDTO(message);
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }
}
