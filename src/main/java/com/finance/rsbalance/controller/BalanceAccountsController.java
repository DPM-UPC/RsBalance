package com.finance.rsbalance.controller;

import com.finance.rsbalance.exception.ResourceNotFoundException;
import com.finance.rsbalance.model.BalanceAccounts;
import com.finance.rsbalance.repository.BalanceAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/balances")
public class BalanceAccountsController {

    @Autowired
    BalanceAccountsRepository balanceAccountsRepository;


    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Map> getBalanceAccountsByPeriodAndBusiness(@RequestParam(value = "period") Long period,
                                                           @RequestParam(value = "business") Integer business) {

        List<Map> _result = balanceAccountsRepository.findBalanceAccountsByPeriodAndBusiness(period, business );
        return _result;
    }


    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Map> getBalanceAccountsByPeriodAndBusinessAndAccount(@RequestParam(value = "period")   Long period,
                                                                     @RequestParam(value = "business") Integer business,
                                                                     @RequestParam(value = "account")  Integer account){

        List<Map> _result = balanceAccountsRepository.findBalanceAccountsByPeriodAndBusinessAndAccount(period, business, account);
        return _result;
    }


}
