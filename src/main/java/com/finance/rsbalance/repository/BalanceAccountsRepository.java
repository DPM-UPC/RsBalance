package com.finance.rsbalance.repository;

import com.finance.rsbalance.model.Accounts;
import com.finance.rsbalance.model.BalanceAccounts;
import com.finance.rsbalance.model.BalanceAccountsId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public interface BalanceAccountsRepository extends JpaRepository<BalanceAccounts, BalanceAccountsId> {


    @Query("SELECT ba.id.monthly_period as period, ba.id.user_business_id as business, ba.id.account_id as account, ba.amount as amount  FROM BalanceAccounts ba WHERE ba.id.monthly_period = :period and ba.id.user_business_id = :business")
    public List<Map> findBalanceAccountsByPeriodAndBusiness(@Param("period")   Long period,
                                                            @Param("business") Integer business);


    @Query("SELECT ba.id.monthly_period as period, ba.id.user_business_id as business, ba.id.account_id as account, ba.amount as amount  FROM BalanceAccounts ba WHERE ba.id.monthly_period = :period and ba.id.user_business_id = :business and ba.id.account_id = :account")
    public List<Map> findBalanceAccountsByPeriodAndBusinessAndAccount(@Param("period")   Long period,
                                                                      @Param("business") Integer business,
                                                                      @Param("account")  Integer account);


    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE BalanceAccounts ba SET ba.amount =:amount + ba.amount WHERE ba.id.monthly_period =:period AND ba.id.user_business_id =:business AND ba.id.account_id =:account")
    public void updateAmountOfBalanceAccountsByPeriodAndBusinessAndAccount(@Param("period")   Long period,
                                                                           @Param("business") Integer business,
                                                                           @Param("account")  Integer account,
                                                                           @Param("amount")   double amount);

    @Modifying
    @Query(value ="INSERT INTO balance_accounts (monthly_period, user_business_id, account_id, amount, creation_date, update_date) VALUES (:period, :business, :account, :amount, now(), now())", nativeQuery = true)
    public void insertBalanceAccounts(@Param("period")   Long period,
                                      @Param("business") Integer business,
                                      @Param("account")  Integer account,
                                      @Param("amount")   double amount);


    }

