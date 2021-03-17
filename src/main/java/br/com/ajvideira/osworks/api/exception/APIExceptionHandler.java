package br.com.ajvideira.osworks.api.exception;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.ajvideira.osworks.api.exception.Problema.Campo;
import br.com.ajvideira.osworks.exception.DomainException;
import br.com.ajvideira.osworks.exception.EntityNotFoundException;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(DomainException.class)
  public ResponseEntity<Object> handleDomainException(DomainException ex, WebRequest request) {
    var status = HttpStatus.BAD_REQUEST;

    var problema = new Problema();
    problema.setStatus(status.value());
    problema.setTitulo(ex.getMessage());
    problema.setDataHora(OffsetDateTime.now());

    return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(DomainException ex, WebRequest request) {
    var status = HttpStatus.NOT_FOUND;

    var problema = new Problema();
    problema.setStatus(status.value());
    problema.setTitulo(ex.getMessage());
    problema.setDataHora(OffsetDateTime.now());

    return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, 
    WebRequest request) {
    
      var problema = new Problema();
      problema.setStatus(status.value());
      problema.setTitulo("Um ou mais campos estão inválidos. Corrija o preenchimento.");
      problema.setDataHora(OffsetDateTime.now());

      List<Campo> campos = new ArrayList<>();
      for (ObjectError error : ex.getBindingResult().getAllErrors()) {
        String nome = ((FieldError)error).getField();
        String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

        campos.add(new Problema.Campo(nome, mensagem));
      }
      problema.setCampos(campos);

    return super.handleExceptionInternal(ex, problema, headers, status, request);
  }
}
