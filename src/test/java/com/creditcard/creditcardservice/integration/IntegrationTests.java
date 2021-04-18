package com.creditcard.creditcardservice.integration;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creditcard.creditcardservice.CreditcardServiceApplication;
import com.creditcard.creditcardservice.valueobject.CreditCardValue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CreditcardServiceApplication.class)
public class IntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void postTestNotAcceptable() {
		CreditCardValue creditCrad = new CreditCardValue("errerr", "10009999999999", 0.0, 5000.0);
		ResponseEntity<Object> resEntity = restTemplate.postForEntity("/creditcards", creditCrad, Object.class);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, resEntity.getStatusCode());
		
	}
	
	
	@Test
	public void postTestCreated() {
		CreditCardValue creditCrad = new CreditCardValue("errerr", "79927398713", 0.0, 5000.0);
		ResponseEntity<Object> resEntity = restTemplate.postForEntity("/creditcards", creditCrad, Object.class);
		assertEquals(HttpStatus.CREATED, resEntity.getStatusCode());
		
	}
	
	@Test
	public void postTestConflict() {
		CreditCardValue creditCrad = new CreditCardValue("errerr", "1358954993914435", 0.0, 5000.0);
		restTemplate.postForEntity("/creditcards", creditCrad, Object.class);
		ResponseEntity<Object> resEntity = restTemplate.postForEntity("/creditcards", creditCrad, Object.class);
		assertEquals(HttpStatus.CONFLICT, resEntity.getStatusCode());
		
	}
	
	@Test
	public void postTestValidationError() {
		CreditCardValue creditCrad = new CreditCardValue("", "1234567890123", 0.0, 5000.0);
		restTemplate.postForEntity("/creditcards", creditCrad, Object.class);
		ResponseEntity<Object> resEntity = restTemplate.postForEntity("/creditcards", creditCrad, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, resEntity.getStatusCode());
		
	} 
	
	@Test
	public void getTest() {
		CreditCardValue creditCrad = new CreditCardValue("", "1234567890123", 0.0, 5000.0);
		restTemplate.postForEntity("/creditcards", creditCrad, Object.class);
		ResponseEntity<Object> resEntity = restTemplate.getForEntity("/creditcards", Object.class);
		assertEquals(HttpStatus.OK, resEntity.getStatusCode());
	}
}
