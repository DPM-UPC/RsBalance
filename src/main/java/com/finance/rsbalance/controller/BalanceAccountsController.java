package com.finance.rsbalance.controller;

import com.finance.rsbalance.exception.ResourceNotFoundException;
import com.finance.rsbalance.model.BalanceAccounts;
import com.finance.rsbalance.repository.BalanceAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BalanceAccountsController {

    @Autowired
    BalanceAccountsRepository balanceAccountsRepository;


    @GetMapping(value = "/balance", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<BalanceAccounts> getAllBalanceAccounts() {
        return balanceAccountsRepository.findAll();
    }



    @GetMapping(value = "/balance/balanceByPeriodAndBusiness/{period}/{business}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Map>  getBalanceAccountsByPeriodAndBusiness(@PathVariable(value = "period") Long period,
                                                            @PathVariable(value = "business") Integer business) {

        List<Map> _result = balanceAccountsRepository.findBalanceByPeriodAndBusiness(period ,business );
        return _result;
    }

    @GetMapping(value = "/balance/balanceAccountsByPeriodAndBusinessAndAccount/{period}/{business}/{account}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Map>  getBalanceAccountsByPeriodAndBusinessAndAccount(@PathVariable(value = "period") Long period,
                                                                      @PathVariable(value = "business") Integer business,
                                                                      @PathVariable(value = "account")  Integer account){

        List<Map> _result = balanceAccountsRepository.findBalanceAccountsByPeriodAndBusinessAndAccount(period ,business, account);
        return _result;
    }


    @PostMapping(value = "/balance", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BalanceAccounts createBalanceAccounts(@Valid @RequestBody BalanceAccounts balanceAccounts) {
        return balanceAccountsRepository.save(balanceAccounts);
    }



    @GetMapping(value = "/balance/{monthly_period}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BalanceAccounts getBalanceAccountsById(@PathVariable(value = "monthly_period") Long balancePeriod) {
        return balanceAccountsRepository.findById(balancePeriod).orElseThrow( ()-> new ResourceNotFoundException("BalanceAccounts", "monthly_period", balancePeriod));
    }



    @PutMapping(value = "/balance/{monthly_period}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BalanceAccounts updateBalanceAccounts(@PathVariable(value = "monthly_period") Long balancePeriod, @Valid @RequestBody BalanceAccounts balanceDetails) {

        BalanceAccounts balanceAccounts = balanceAccountsRepository.findById(balancePeriod).orElseThrow(() -> new ResourceNotFoundException("BalanceAccounts", "monthly_period", balancePeriod));

       /* balanceAccounts.setMonthly_period(balanceDetails.getMonthly_period());
        balanceAccounts.setUser_business_id(balanceDetails.getUser_business_id());
        balanceAccounts.setAccount_id(balanceDetails.getAccount_id());
        balanceAccounts.setAmount(balanceDetails.getAmount());*/


        BalanceAccounts updateBalance = balanceAccountsRepository.save(balanceAccounts);
        return updateBalance;
    }



    @PutMapping(value = "/balance/balanceAccountsByPeriodAndBusinessAndAccount/{period}/{business}/{account}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean putBalanceAccountsByPeriodAndBusinessAndAccount(@PathVariable(value = "period") Long period,
                                                                   @PathVariable(value = "business") Integer business,
                                                                   @PathVariable(value = "account") Integer account,
                                                        /*         @PathVariable(value = "operation_amount") double operation_amount,   */
                                                                   @Valid @RequestBody BalanceAccounts balanceDetails) {

        try {
            balanceAccountsRepository.updateBalanceAccountsByPeriodAndBusinessAndAccount(period, business, account);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return true;
    }


    @DeleteMapping(value = "/balance/{monthly_period}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteNote(@PathVariable(value = "monthly_period") Long balancePeriod) {

        BalanceAccounts balanceAccounts = balanceAccountsRepository.findById(balancePeriod).orElseThrow(() -> new ResourceNotFoundException("BalanceAccounts", "monthly_period", balancePeriod));

        balanceAccountsRepository.delete(balanceAccounts);
        return ResponseEntity.ok().build();
    }


}
