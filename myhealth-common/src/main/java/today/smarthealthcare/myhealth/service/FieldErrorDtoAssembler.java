package today.smarthealthcare.myhealth.service;

import today.smarthealthcare.myhealth.exception.dto.FieldErrorDto;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

import today.smarthealthcare.myhealth.exception.dto.FieldErrorDto;

@Service
public class FieldErrorDtoAssembler {
	public FieldErrorDto assemble(FieldError fieldError) {
		if (fieldError != null) {
			FieldErrorDto fieldErrorDto = new FieldErrorDto();
			fieldErrorDto.setField(fieldError.getField());
			fieldErrorDto.setMessage(fieldError.getDefaultMessage());

			return fieldErrorDto;
		}

		return null;
	}

	public List<FieldErrorDto> assemble(List<FieldError> fieldErrors) {
		if (!CollectionUtils.isEmpty(fieldErrors)) {
			return fieldErrors.stream().map(this::assemble).collect(Collectors.toList());
		}

		return null;
	}
}
