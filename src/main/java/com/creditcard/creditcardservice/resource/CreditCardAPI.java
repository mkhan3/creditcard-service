package com.creditcard.creditcardservice.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.creditcard.creditcardservice.exception.InvalidCreditCardNumberException;
import com.creditcard.creditcardservice.manager.CreditCardManager;
import com.creditcard.creditcardservice.utility.LuhnAlg;
import com.creditcard.creditcardservice.valueobject.CreditCardValue;

/**
 * Credit card rest controller which exposes one get and one post endpoint
 * @author Administrator
 *
 */
@RestController
public class CreditCardAPI {

	@Autowired
	private CreditCardManager creditCardManager;
	
	/**
	 * Create credit card with the user given credit card information.
	 * @param creditCard
	 * @return ResponseEntity with the location of the newly created resource
	 */
	@PostMapping("/creditcards")
	public ResponseEntity<Object> createCreditCard(@Valid @RequestBody CreditCardValue  creditCard) {
		
		// validating creditcard number by Luhn10 algorithm
		if(!LuhnAlg.checkLuhn(creditCard.getCardNumber())) {
			throw new InvalidCreditCardNumberException(creditCard.getCardNumber() + " - Invalid CreditCard Number");
		}
		
		Long savedCreaditCardId = creditCardManager.saveCreditCard(creditCard);
		//the uri location for the newly created resource
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCreaditCardId).toUri();
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * 
	 * @return list of saved credit cards with the http staus OK
	 */
	@GetMapping("/creditcards")
	public ResponseEntity<List<CreditCardValue>> retrieveAllCreditCards() {
		
		return ResponseEntity.ok(creditCardManager.getAllCreditCards());

	}
}

