package io.salary.ExceptionController;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

@ExceptionHandler(value = NoSuchElementException.class)
public ResponseEntity<Object> exception (NoSuchElementException e){
	return new ResponseEntity<Object>("Data not found.",HttpStatus.NOT_FOUND);
}
}
