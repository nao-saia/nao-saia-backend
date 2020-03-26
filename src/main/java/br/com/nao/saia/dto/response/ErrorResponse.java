package br.com.nao.saia.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ErrorResponse {

    private Long timestamp;
    private String path;
    private Integer status;
    private String error;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> fieldErrors;

    public ErrorResponse(Long timestamp, String path, Integer status, String error, String message) {
        new ErrorResponse(timestamp, path, status, error, message, null);
    }

    public ErrorResponse(Long timestamp, String path, Integer status, String error, String message, List<FieldError> fieldErrors) {
        this.timestamp = timestamp;
        this.path = path;
        this.status = status;
        this.error = error;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
