/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.postgresdemo.util;

import org.springframework.http.HttpStatus;

/**
 *
 * @author saddam.husein
 */
public class HttpStatusUtil {
    
    public static HttpStatus getHttpStatusByCode(int code){
        if(code == 0){
            return HttpStatus.OK;
        }else if(code >= 90){
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }else if(code >= 80){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }else if(code >= 70){
            return HttpStatus.EXPECTATION_FAILED;
        }else if(code >= 60){
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.BAD_REQUEST;
    }
    
}
