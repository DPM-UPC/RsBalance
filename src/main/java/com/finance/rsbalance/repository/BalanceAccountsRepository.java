package com.finance.rsbalance.repository;

import com.finance.rsbalance.model.BalanceAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BalanceAccountsRepository extends JpaRepository<BalanceAccounts, Long> {

    @Query("SELECT ba.id.monthly_period as period, ba.id.user_business_id as business, ba.id.account_id as account, amount as amount  FROM BalanceAccounts ba WHERE ba.id.monthly_period = :period and ba.id.user_business_id = :business")
    public List<Map> findBalanceByPeriodAndBusiness(@Param("period") Long period, @Param("business") Integer business);

    @Query("SELECT ba.id.monthly_period as period, ba.id.user_business_id as business, ba.id.account_id as account, amount as amount  FROM BalanceAccounts ba WHERE ba.id.monthly_period = :period and ba.id.user_business_id = :business and id.account_id = :account")
    public List<Map> findBalanceAccountsByPeriodAndBusinessAndAccount(@Param("period") Long period, @Param("business") Integer business, @Param("account") Integer account);

    @Query("UPDATE BalanceAccounts SET amount = amount WHERE id.monthly_period = :period and id.user_business_id = :business and id.account_id = :account")
    void updateBalanceAccountsByPeriodAndBusinessAndAccount(@Param("period") Long period,
                                                            @Param("business") Integer business,
                                                            @Param("account") Integer account);
                                                /*            @Param("operation_amount") double operation_amount);    */

}
