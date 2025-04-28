package com.BankingService.Cards.Controller;

import com.BankingService.Cards.Constants.CardConstants;
import com.BankingService.Cards.Entity.Cards;
import com.BankingService.Cards.Service.ICardsService;
import com.BankingService.Cards.dto.CardsDto;
import com.BankingService.Cards.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class CardsController {
    private ICardsService iCardsService;
    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber){
        iCardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardConstants.STATUS_201,CardConstants.MESSAGE_201));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(String mobileNumber){
        CardsDto cardsDto = iCardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@RequestBody CardsDto cardsDto){
        boolean isUpdated = iCardsService.updateCard(cardsDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardConstants.STATUS_200,CardConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardConstants.STATUS_417,CardConstants.MESSAGE_417_UPDATE));
    }
    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam String mobileNumber){
        boolean isDeleted = iCardsService.deleteCard(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardConstants.STATUS_200,CardConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardConstants.STATUS_417,CardConstants.MESSAGE_417_DELETE));
    }
}
