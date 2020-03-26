package br.com.nao.saia.controller;

import br.com.nao.saia.dto.response.ErrorResponse;
import br.com.nao.saia.dto.response.FieldError;
import br.com.nao.saia.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.currentTimeMillis;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(BusinessException exc, ServerWebExchange exchange) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ServerHttpRequest request = exchange.getRequest();

        return new ResponseEntity<>(new ErrorResponse(
                currentTimeMillis(),
                request.getPath().value(),
                status.value(),
                status.getReasonPhrase(),
                exc.getMessage()
        ), status);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exc, ServerWebExchange exchange) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ServerHttpRequest request = exchange.getRequest();

        return new ResponseEntity<>(new ErrorResponse(
                currentTimeMillis(),
                request.getPath().value(),
                status.value(),
                status.getReasonPhrase(),
                exc.getMessage()
        ), status);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(WebExchangeBindException exc, ServerWebExchange exchange) {
        HttpStatus status = exc.getStatus();
        ServerHttpRequest request = exchange.getRequest();

        List<FieldError> fieldErrors = exc.getFieldErrors().stream()
                .map(fieldError -> new FieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorResponse(
                currentTimeMillis(),
                request.getPath().value(),
                status.value(),
                status.getReasonPhrase(),
                exc.getMessage(),
                fieldErrors
        ), status);
    }

}