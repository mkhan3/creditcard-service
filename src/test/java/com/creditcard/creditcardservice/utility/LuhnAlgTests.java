package com.creditcard.creditcardservice.utility;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class LuhnAlgTests {
	
	@BeforeEach
	public void setUp() {
		
	}
	@Test
	public void testValidNumbers() {
		assertTrue(LuhnAlg.checkLuhn("79927398713"));
		assertTrue(LuhnAlg.checkLuhn("1358954993914435"));
		assertTrue(LuhnAlg.checkLuhn("12345678903555"));
		assertTrue(LuhnAlg.checkLuhn("61789372994"));
		assertTrue(LuhnAlg.checkLuhn("379354508162306"));
		
	}
	
	@Test
	public void testInvalidNumbers() {
		
		assertFalse(LuhnAlg.checkLuhn("1234567890"));
		assertFalse(LuhnAlg.checkLuhn("12345678912345678"));
		assertFalse(LuhnAlg.checkLuhn("13579131719"));
		
	}
	
	
}
