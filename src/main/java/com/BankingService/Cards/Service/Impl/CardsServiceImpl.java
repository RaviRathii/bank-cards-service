package com.BankingService.Cards.Service.Impl;

import com.BankingService.Cards.Constants.CardConstants;
import com.BankingService.Cards.Entity.Cards;
import com.BankingService.Cards.Service.ICardsService;
import com.BankingService.Cards.dto.CardsDto;
import com.BankingService.Cards.mapper.CardsMapper;
import com.BankingService.Cards.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
import java.util.Optional;
import java.util.Random;

@Service @AllArgsConstructor
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
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                RuntimeException::new
        );
        CardsMapper.mapToCards(cardsDto,cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                RuntimeException::new
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
