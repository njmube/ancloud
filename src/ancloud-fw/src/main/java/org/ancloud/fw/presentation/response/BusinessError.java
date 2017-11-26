package org.ancloud.fw.presentation.response;

public class BusinessError {
    private ErrorCode errorCode;
    private String errorMessage;
    private String description;
    
    public BusinessError(){
    }
    
    public BusinessError(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getMessage();
    }
    
    public BusinessError(ErrorCode errorCode,String message){
        this.errorCode = ErrorCode.NONE;
        this.errorMessage = errorCode.getMessage();
        this.setDescription(message);
    }
    
    public ErrorCode getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
