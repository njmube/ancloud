package org.ancloud.fw.presentation.response;

public enum ErrorCode {
    
    NONE("No error code available."),
    
    FAIL("Bad request."),
    FAIL_WRONG_MAC_FORMAT("Wrong mac address format."),
    
    
    NOT_EXISTED("Entity doesn't exist."),
    NOT_CONSISTENT("Object's state isn't consistent. Try to fetch new one and make another submission."),
    NOT_EXISTED_TOPOLOGY("Topology's root doesn't exist."), 
    NOT_EXISTED_PROXY("Proxy doesn't exist."),
    NO_RANDOM_PROXY("No random proxy to hop."),
    NOT_EXISTED_MAC("Mac address doesn't exist"),
    NOT_EXISTED_IP("Ip address doesn't exist"), 
    NOT_DELETABLE("Delete is not allowed."), 
    NOT_EXISTED_TARGET_MAC("Target mac doesn't exist.");
    
    
    private String message;
    private ErrorCode(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
}
