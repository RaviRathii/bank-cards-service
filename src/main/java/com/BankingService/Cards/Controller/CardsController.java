package com.BankingService.Cards.Controller;

import com.BankingService.Cards.Constants.CardConstants;
import com.BankingService.Cards.Service.ICardsService;
import com.BankingService.Cards.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class CardsController {
    private ICardsService iCardsService;
    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber){
        iCardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardConstants.STATUS_201,CardConstants.MESSAGE_201));
    }
}
