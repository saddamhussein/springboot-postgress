/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.postgresdemo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.lang.Nullable;

/**
 *
 * @author saddam.husein
 */
@JsonPropertyOrder(alphabetic=true)
@JsonInclude(Include.NON_NULL) 
public class BaseResponseVo<T> {
    
    private T entity;
    
    private int code = 0;
    private String message = "SUCCESS";
    private String requestId;
    
    public BaseResponseVo(){};
    public BaseResponseVo(int code, String message, String requestId){
        this.code = code;
        this.message = message;
        this.requestId = requestId;
    };

    public BaseResponseVo(@Nullable T body, String requestId) {
        this.entity = body;
        this.requestId = requestId;
    }
    
    public BaseResponseVo(String requestId) {
        this.requestId = requestId;
    }
    

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
    
}
