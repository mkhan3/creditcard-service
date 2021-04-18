package com.creditcard.creditcardservice.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creditcard.creditcardservice.repository.CreditCardRepository;
import com.creditcard.creditcardservice.repository.entity.CreditCardEntity;
import com.creditcard.creditcardservice.valueobject.CreditCardValue;

/**
 * An extra layer between API and repository. This manager is used to invoke creditcard repositories.
 * This layer will deal with entities and repositories.
 * 
 * @author Administrator
 *
 */
@Component
public class CreditCardManager {
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	public Long saveCreditCard(CreditCardValue creditCardValue) {
		
		CreditCardEntity savedCreditCard = creditCardRepository.save(new CreditCardEntity(creditCardValue.getNameOnCard(), 
				creditCardValue.getCardNumber(), creditCardValue.getBalance(), creditCardValue.getCreditLimit()));
		
		return savedCreditCard.getId();
		
		
	}
	
	public List<CreditCardValue> getAllCreditCards(){
		
		List<CreditCardEntity> creditCardEntities = creditCardRepository.findAll();
		List<CreditCardValue> creditCardValues = new ArrayList<CreditCardValue>();
		for(CreditCardEntity source : creditCardEntities) {
			CreditCardValue target = new CreditCardValue();
			BeanUtils.copyProperties(source, target);
			creditCardValues.add(target);
		}
		
		return creditCardValues;
	}

}
