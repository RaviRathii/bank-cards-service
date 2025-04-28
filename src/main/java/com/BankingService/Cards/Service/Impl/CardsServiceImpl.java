package com.BankingService.Cards.Service.Impl;

import com.BankingService.Cards.Constants.CardConstants;
import com.BankingService.Cards.Entity.Cards;
import com.BankingService.Cards.Service.ICardsService;
import com.BankingService.Cards.dto.CardsDto;
import com.BankingService.Cards.mapper.CardsMapper;
import com.BankingService.Cards.repository.CardsRepository;

import javax.smartcardio.Card;
import java.util.Optional;
import java.util.Random;

public class CardsServiceImpl implements ICardsService {
    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Cards card = new Cards();
        long randomNumber  = 100000000L + new Random().nextInt(90000000);
        card.setCardNumber(Long.toString(randomNumber));
        card.setMobileNumber(mobileNumber);
        card.setCardType(CardConstants.CREDIT_CARD);
        card.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        card.setAmountUsed(0);
        cardsRepository.save(card);
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new RuntimeException()
        );
        return CardsMapper.mapToCardsDto(card,new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        return false;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        return false;
    }
}
