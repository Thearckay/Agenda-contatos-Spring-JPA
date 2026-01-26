package com.thearckay.ContactsBackend.dto.responseAPI;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ResponseAPI<T> {
    private String status;
    private String message;
    private List<T> data = new ArrayList<>();
    private List<ResponseAPI.Error> errors = new ArrayList<>();

    public void success (List<T> data, String messageOptional){
        setStatus("200");
        if (messageOptional == null){
            setMessage("Sucesso!");
        } else {
            setMessage(messageOptional);
        }
        this.data.addAll(data);
    }

    public void error (String statusCode, List<ResponseAPI.Error> errors, String messageOptional){
        if (statusCode == null){
            setStatus("400");
        } else {
            setStatus(statusCode);
        }

        this.setErrors(errors);

        if (messageOptional == null){
            setMessage("Error");
        } else {
            setMessage(messageOptional);
        }

    }

    public void error (HttpStatus statusCode, List<ResponseAPI.Error> errors, String messageOptional){
        if (statusCode == null){
            setStatus("400");
        } else {
            setStatus(Integer.toString(statusCode.value()));
        }

        this.setErrors(errors);

        if (messageOptional == null){
            setMessage("Error");
        } else {
            setMessage(messageOptional);
        }

    }

    public static class Error{
         private Object field;
         private String message;

         public Error(Object fieldError, String messageError){
             this.field = fieldError;
             this.message = messageError;
         }

        public Object getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<ResponseAPI.Error> getErrors() {
        return errors;
    }

    public void setErrors(List<ResponseAPI.Error> errors) {
        this.errors = errors;
    }
}
