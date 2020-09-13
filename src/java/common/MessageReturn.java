/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author ASUS
 */
public class MessageReturn {
    
    public boolean result;
    
    public String message;
    
    public String messageCode;

    public MessageReturn() {
    }

    public MessageReturn(boolean result, String message, String messageCode) {
        this.result = result;
        this.message = message;
        this.messageCode = messageCode;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
    
    @Override
    public String toString() {
        return "{\"result\": \"" + result + "\", \"message\": \"" + message + "\", \"messageCode\": \"" + messageCode + "\"}";
    }
}
