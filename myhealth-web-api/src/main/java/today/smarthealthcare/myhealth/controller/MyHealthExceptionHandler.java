package today.smarthealthcare.myhealth.controller;

import today.smarthealthcare.myhealth.exception.FieldValidationException;
import today.smarthealthcare.myhealth.exception.MyHealthException;
import today.smarthealthcare.myhealth.exception.dto.ErrorDto;
import today.smarthealthcare.myhealth.exception.dto.ErrorResponseDto;
import today.smarthealthcare.myhealth.service.FieldErrorDtoAssembler;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyHealthExceptionHandler {
	@Autowired
	private FieldErrorDtoAssembler fieldErrorDtoAssembler;

	@ExceptionHandler(FieldValidationException.class)
	public ResponseEntity<?> handleFieldValidationException(FieldValidationException exception) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		errorResponseDto.setFieldErrors(fieldErrorDtoAssembler.assemble(exception.getFieldErrors()));
		return new ResponseEntity<>(errorResponseDto, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(MyHealthException.class)
	public ResponseEntity<?> handleMyHealthException(MyHealthException exception) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		ErrorDto errorDto = new ErrorDto(exception.getMessage());
		errorResponseDto.setErrors(Lists.newArrayList(errorDto));
		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	}
}
