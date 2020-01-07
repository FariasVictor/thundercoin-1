package com.invillia.thundercoin.controller.advice;

import com.invillia.thundercoin.exception.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    @ExceptionHandler(ObjectNotFoundException.class)
    public HttpEntity<StandardError> objectNotFoundException(final ObjectNotFoundException e,
                                                             final HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(
                formatter.format(System.currentTimeMillis()),
                status.value(),
                "Não Encontrado!",
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(CPFNotValidException.class)
    public HttpEntity<StandardError> cpfNotValidException(final CPFNotValidException e,
                                                          final HttpServletRequest request){

        StandardError error = new StandardError(
                formatter.format(System.currentTimeMillis()),
                e.getStatus().value(),
                "CPF Inválido",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(e.getStatus()).body(error);
    }

    @ExceptionHandler(OriginTypeNotFoundException.class)
    public HttpEntity<StandardError> originTypeNotFoundException(final OriginTypeNotFoundException e,
                                                                 final HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError(
                formatter.format(System.currentTimeMillis()),
                status.value(),
                "Não Encontrado!",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataAlreadyRegistred.class)
    public HttpEntity<StandardError> dataAlreadyRegistredException(final DataAlreadyRegistred e, final HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;

        StandardError error = new StandardError(
                formatter.format(System.currentTimeMillis()),
                status.value(),
                "Dado já cadastrado!",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ValueNotAllowed.class)
    public HttpEntity<StandardError> valueNotAllwed(final ValueNotAllowed e, final HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError(
                formatter.format(System.currentTimeMillis()),
                status.value(),
                "Valor inválido",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataDisableException.class)
    public HttpEntity<StandardError> dataDisableException(final DataDisableException e, final HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError(
                formatter.format(System.currentTimeMillis()),
                status.value(),
                "Desativado",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        final Map<String, List<String>> result = e.getBindingResult().getAllErrors()
                .stream()
                .map(it -> (FieldError) it)
                .collect(
                        Collectors.groupingBy(
                                FieldError::getField,
                                Collectors.mapping(
                                        DefaultMessageSourceResolvable::getDefaultMessage,
                                        Collectors.toList()
                                )
                        )
                );

        return ResponseEntity.badRequest().body(result);
    }
}
