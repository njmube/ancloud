/*
 * 
 */
package org.ancloud.fw.presentation.response;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "status", "payload", "errors"})
public class ResponseWrapper<T> {

    /**
     * The indicator of request processing. There are three types:
     * success,fail,error
     */
    private Status status;

    /** The payload contains return response data */
    private T payload;

    /** Errors list */
    private Collection<BusinessError> errors;
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public Collection<BusinessError> getErrors() {
        return errors;
    }

    public void setErrors(Collection<BusinessError> errors) {
        this.errors = errors;
    }

    public ResponseWrapper(Status status, T payload,Collection<BusinessError> errors) {
        this.status = status;
        this.payload = payload;
        this.errors = errors;
    }

    public ResponseWrapper(Status status, T payload) {
        this.status = status;
        this.payload = payload;
    }

    public static <T>ResponseWrapper<T> createSuccess(T payload) {
        return ResponseWrapper.<T>create(Status.Success,payload,null);
    }
    public static <T>ResponseWrapper<T> createError(Collection<BusinessError> errors) {
        return ResponseWrapper.<T>create(Status.Error,null,errors);
    }
    public static <T>ResponseWrapper<T> createFail(Collection<BusinessError> errors) {
        return ResponseWrapper.<T>create(Status.Fail,null,errors);
    }
    
    public static <T>ResponseWrapper<T> create(Status status,T payload,Collection<BusinessError> errors) {
        return new ResponseWrapper<T>(status,payload,errors);
    }
    
    public static <T>ResponseEntity<ResponseWrapper<T>> success(HttpStatus status,T payload) {
        return ResponseEntity.status(status).body(ResponseWrapper.<T>create(Status.Success,payload,null));
    }
    public static <T>ResponseEntity<ResponseWrapper<T>> success(T payload) {
        return ResponseWrapper.success(HttpStatus.OK,payload);
    }
    public static <T>ResponseEntity<ResponseWrapper<T>> success() {
        return ResponseWrapper.success(HttpStatus.OK,null);
    }
    public static <T>ResponseEntity<ResponseWrapper<T>> error(HttpStatus status,Collection<BusinessError> errors) {
        return ResponseEntity.status(status).body(ResponseWrapper.<T>create(Status.Error,null,errors));
    }

    public static <T>ResponseEntity<ResponseWrapper<T>> error(Collection<BusinessError> errors) {
        return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR,errors);
    }
    public static <T>ResponseEntity<ResponseWrapper<T>> fail(HttpStatus status,Collection<BusinessError> errors) {
        return ResponseEntity.status(status).body(ResponseWrapper.<T>create(Status.Fail,null,errors));
    }
    public static <T>ResponseEntity<ResponseWrapper<T>> fail(Collection<BusinessError> errors) {
        return ResponseWrapper.fail(HttpStatus.BAD_REQUEST,errors);
    }
    
    public static <T>ResponseEntity<ResponseWrapper<T>> fail(List<ObjectError> errors) {
        return ResponseWrapper.fail(HttpStatus.BAD_REQUEST
                                    ,errors.parallelStream()
                                    .map(n->{
                                        BusinessError error = new BusinessError();
                                        if(n.getArguments().length>1){
                                            if(n.getArguments()[0].getClass().equals(ErrorCode.class)){
                                                error.setErrorCode((ErrorCode)n.getArguments()[0]);
                                                
                                            }
                                            if(n.getArguments()[1].getClass().equals(String.class)){
                                                error.setErrorMessage((String)n.getArguments()[1]);
                                            }
                                            return error;
                                        }
                                        return null;
                                    })
                                    .collect(Collectors.toList())
                              );
    }
    

}
