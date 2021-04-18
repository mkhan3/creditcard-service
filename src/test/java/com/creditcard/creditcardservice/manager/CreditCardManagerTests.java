package com.creditcard.creditcardservice.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import com.creditcard.creditcardservice.repository.CreditCardRepository;
import com.creditcard.creditcardservice.repository.entity.CreditCardEntity;
import com.creditcard.creditcardservice.valueobject.CreditCardValue;

@ExtendWith(MockitoExtension.class)
public class CreditCardManagerTests {

	@Mock
	private CreditCardRepository creditCardRepository;
	private CreditCardValue creditCardValue1;
	@InjectMocks
	private CreditCardManager creditCardManager;

	@BeforeEach
	public void setUp() {
		creditCardValue1 = new CreditCardValue("ABCDEF", "12345678", 0.0, 5000.0);

	}
	
	@Test
	public void testSave() {
		
		CreditCardEntity entity = new CreditCardEntity();
		BeanUtils.copyProperties(creditCardValue1, entity);
		entity.setId(100L);
		Mockito.when(creditCardRepository.save(Mockito.any())).thenReturn(entity);
		assertEquals(100L, creditCardManager.saveCreditCard(creditCardValue1));

	}
	
	public void testgetAllCreditCards() {
		
		CreditCardEntity entity1 = new CreditCardEntity();
		BeanUtils.copyProperties(creditCardValue1, entity1);
		CreditCardEntity entity2 = new CreditCardEntity();
		BeanUtils.copyProperties(creditCardValue1, entity2);
		List<CreditCardEntity> entities = Arrays.asList(entity1, entity2);  
		Mockito.when(creditCardRepository.findAll()).thenReturn(entities);
		assertEquals(2, creditCardManager.getAllCreditCards().size());
		
	}

}
