package com.finance.rsbalance.controller;


import com.finance.rsbalance.model.BalanceAccounts;
import com.finance.rsbalance.repository.BalanceAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/balances")
public class BalanceAccountsController {

    @Autowired
    BalanceAccountsRepository balanceAccountsRepository;

    BalanceAccounts _balance;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Map> getBalanceAccountsByPeriodAndBusinessAndAccount(@RequestParam(value = "period")   Long period,
                                                                     @RequestParam(value = "business") Integer business,
                                                                     @RequestParam(value = "account", required = false) Integer account){

        List<Map> _result;

        if(account!=null){
            _result = balanceAccountsRepository.findBalanceAccountsByPeriodAndBusinessAndAccount(period, business, account);
        }else{
            _result = balanceAccountsRepository.findBalanceAccountsByPeriodAndBusiness(period, business);
        }
        return _result;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean postBalanceAccounts(@Valid @RequestBody BalanceAccounts balanceAccounts) {

        List<Map> _result;

        System.out.println("realizar consulta....");

        _result = balanceAccountsRepository.findBalanceAccountsByPeriodAndBusinessAndAccount(balanceAccounts.getId().getMonthly_period(),
                                                                                             balanceAccounts.getId().getUser_business_id(),
                                                                                             balanceAccounts.getId().getAccount_id() );

        if (_result !=null) {
            /*  update amount of Accounts Balance  */
            System.out.println("update amount of Accounts Balance");
            try {
                balanceAccountsRepository.updateAmountOfBalanceAccountsByPeriodAndBusinessAndAccount(balanceAccounts.getId().getMonthly_period(),
                                                                                                     balanceAccounts.getId().getUser_business_id(),
                                                                                                     balanceAccounts.getId().getAccount_id(),
                                                                                                     balanceAccounts.getAmount());

                return true;

            } catch (Exception e) {
                return false;
            }
        } else {
            /*  insert record of Accounts Balance  */
            System.out.println("Insert record of Accounts Balance");
            try {

                balanceAccountsRepository.insertBalanceAccounts(balanceAccounts.getId().getMonthly_period(),
                                                                balanceAccounts.getId().getUser_business_id(),
                                                                balanceAccounts.getId().getAccount_id(),
                                                                balanceAccounts.getAmount());


                /* balanceAccountsRepository.save(balanceAccounts); */

                return true;

            } catch (Exception e) {
                return false;
            }

        }

        }

}