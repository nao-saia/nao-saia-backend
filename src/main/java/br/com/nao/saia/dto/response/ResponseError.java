package br.com.nao.saia.dto.response;

public class ResponseError {

    private String message;

    public ResponseError(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}