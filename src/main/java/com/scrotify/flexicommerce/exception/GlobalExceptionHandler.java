package com.scrotify.flexicommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.scrotify.flexicommerce.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> userNotFoundException(UserNotFoundException exception) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		errorResponseDto.setMessage(exception.getMessage());
		errorResponseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
	}

	@ExceptionHandler(CommonException.class)
	public ResponseEntity<ErrorResponseDto> commonException(Exception exception) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		errorResponseDto.setMessage(exception.getMessage());
		errorResponseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	}

}