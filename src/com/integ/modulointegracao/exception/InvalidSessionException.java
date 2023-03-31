/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integ.modulointegracao.exception;

/**
 *
 * @author CLAUDIO
 */
public class InvalidSessionException extends Exception{

    public InvalidSessionException() {
    }
   
    
    public InvalidSessionException(String message) {
        super(message);        
    }
    
    public InvalidSessionException(Throwable cause) {
        super(cause);
    }  
   
}
