package com.creditcard.creditcardservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creditcard.creditcardservice.repository.entity.CreditCardEntity;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long>{

	
}
