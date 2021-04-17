package com.creditcard.creditcardservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidCreditCardNumberException extends RuntimeException{

	public InvalidCreditCardNumberException(String message) {
		super(message);
		
	}

	
}
