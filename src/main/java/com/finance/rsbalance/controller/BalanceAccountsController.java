package com.finance.rsbalance.controller;


import com.finance.rsbalance.model.BalanceAccounts;
import com.finance.rsbalance.model.BalanceAccountsId;
import com.finance.rsbalance.repository.BalanceAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import javax.persistence.IdClass;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/balances")
public class BalanceAccountsController {

    @Autowired
    BalanceAccountsRepository balanceAccountsRepository;

    /* BalanceAccounts _balance;  */

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
    public boolean postBalanceAccounts(@Valid @RequestBody Map<String , Object> balanceAccountsMap) {

        List<Map> _result;

        System.out.println("realizar consulta...."+balanceAccountsMap.toString());

        Long _period = new Long(balanceAccountsMap.get("period").toString());
        Integer _user_business = new Integer(balanceAccountsMap.get("user_business").toString());
        Integer _account = new Integer(balanceAccountsMap.get("account").toString());
        Double _amount = new Double(balanceAccountsMap.get("amount").toString());

        _result = balanceAccountsRepository.findBalanceAccountsByPeriodAndBusinessAndAccount( _period , _user_business , _account );



        if (_result !=null && _result.size() > 0) {
            /*  update amount of Accounts Balance  */

            System.out.println("update amount of Accounts Balance");
            try {
                balanceAccountsRepository.updateAmountOfBalanceAccountsByPeriodAndBusinessAndAccount( _period, _user_business, _account, _amount );

                return true;

            } catch (Exception e) {
                return false;
            }
        } else {
            /*  insert record of Accounts Balance  */
            System.out.println("Insert record of Accounts Balance");
            try {

                balanceAccountsRepository.insertBalanceAccounts( _period, _user_business, _account, _amount );

                return true;

            } catch (Exception e) {
                return false;
            }

        }

        }

}