package br.com.stoom.restaddress.configuracao.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@Autowired
	private MessageSource source;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationError> handler(MethodArgumentNotValidException exception) {
		 List<FieldError> errors = exception.getBindingResult().getFieldErrors();
		 List<ValidationError> validationErros = new ArrayList<ValidationError>();
		 
		 errors.forEach(e -> {
			 String mensagem = source.getMessage(e, LocaleContextHolder.getLocale());
			 ValidationError validationErro = new ValidationError(e.getField(), mensagem);
			 validationErros.add(validationErro);
		 });
		 
		 return validationErros;
	}
}
